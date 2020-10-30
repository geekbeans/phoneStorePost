package com.czhproject.service.impl;

import com.czhproject.dto.OrderDto;
import com.czhproject.entity.OrderMaster;
import com.czhproject.entity.PhoneInfo;
import com.czhproject.entity.PhoneSpecs;
import com.czhproject.enums.PayStatusEnum;
import com.czhproject.enums.ResultEnum;
import com.czhproject.exception.PhoneException;
import com.czhproject.repository.OrderMasterRepository;
import com.czhproject.repository.PhoneInfoRepository;
import com.czhproject.repository.PhoneSpecsRepository;
import com.czhproject.service.OrderService;
import com.czhproject.service.PhoneService;
import com.czhproject.util.KeyUtil;
import com.czhproject.vo.OrderDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

//前端数据传入后端操作（创建订单）
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;
    @Autowired
    private PhoneInfoRepository phoneInfoRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PhoneService phoneService;

    @Override
    public OrderDetailVo findOrderDetailByOrderId(String orderId) {
        //将所需要转化的两个对象new出来
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        //查询前判断
        if (orderMaster == null){
            log.error("[查询订单]订单为空,orderMaster={}"+orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster,orderDetailVo);
        //再进行specsPrice的传入，为什么要单独进行传，因为specsPrice在orderMaster中定义是BigDecimal类型
        //而在orderDetailVo中定义的是String类型，并且数据库中以分为单位要转化为元为单位,除以100，再拼接为字符串
        orderDetailVo.setSpecsPrice(orderMaster.getSpecsPrice().divide(new BigDecimal(100))+".00");
        return orderDetailVo;
    }

    @Override
    public String pay(String orderId) {
        //先将支付的手机信息找出来
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        //先进行支付前判断,如果查询不出来手机信息，那么支付订单肯定为空
        if (orderMaster == null){
            log.error("[支付订单]支付订单为空,orderMaster={}"+orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        //再来进行支付状态判断
        //如果支付状态为未支付
        if(orderMaster.getPayStatus().equals(PayStatusEnum.UNPAIED.getCode())){
            //那么订单创建以后支付状态就改为已支付
            orderMaster.setPayStatus(PayStatusEnum.PAIED.getCode());
            //将支付状态保存进数据库
            orderMasterRepository.save(orderMaster);
        }else {//如果已支付，那么记录日志信息
            log.error("[支付订单]订单已经支付,orderMaster={}"+orderMaster);
            throw new PhoneException(ResultEnum.ORDER_EXIST);
        }
        return orderId;
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        //填充的是buyername,buyerphone,buyeraddress这三个字段
        //先new一个ordermaster对象出来进行装载
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        //再填充specsId相关的字段
        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(orderDto.getSpecsId()).get();
        if (phoneSpecs == null){
            log.error("[手机规格]规格为空,phoneSpecs={}"+phoneSpecs);
            throw new PhoneException(ResultEnum.SPECS_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneSpecs,orderMaster);
        //再填充phoneid,phonename相关字段
        PhoneInfo phoneInfo = phoneInfoRepository.findById(orderDto.getSpecsId()).get();
        if (phoneInfo == null){
            log.error("[支付订单]手机为空,phoneInfo={}"+phoneInfo);
            throw new PhoneException(ResultEnum.PHONE_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneInfo,orderMaster);

        //计算总价，运用BigDecimal方法，新建订单数量对象
        BigDecimal orderAmount = new BigDecimal(0);
        //然后进行总价计算
        //先除以100，因为specs_price是以分为单位，除以100化成元
        //再乘以库存数量
        //再进行订单数量累加
        //再加上运费10元
        orderAmount = phoneSpecs.getSpecsPrice().divide(new BigDecimal(100))
                .multiply(new BigDecimal(orderDto.getPhoneQuantity()))
                .add(orderAmount).add(new BigDecimal(10));

        orderMaster.setOrderAmount(orderAmount);

        //生成orderId,创建一个util工具包
        orderMaster.setOrderId(KeyUtil.createUniqueKey());
        //然后存储到orderDto
        orderDto.setOrderId(orderMaster.getOrderId());

        //生成支付状态paystatus,先利用枚举写好状态码
        orderMaster.setPayStatus(PayStatusEnum.UNPAIED.getCode());

        //将以上订单信息保存
        orderMasterRepository.save(orderMaster);

        //保存后订单中的手机数量会发生变化，所以要调用改库存方法
        phoneService.subStock(orderDto.getSpecsId(),orderDto.getPhoneQuantity());
        return orderDto;
    }
}
