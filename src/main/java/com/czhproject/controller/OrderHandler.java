package com.czhproject.controller;

import com.czhproject.dto.OrderDto;
import com.czhproject.exception.PhoneException;
import com.czhproject.form.OrderForm;
import com.czhproject.service.OrderService;
import com.czhproject.service.PhoneService;
import com.czhproject.util.ResultVoUtil;
import com.czhproject.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderHandler {
    @Autowired
    private OrderService orderService;

    //创建订单方法
    @PostMapping("/createOrder")
    public ResultVo createOrder(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){//先进行判断，如果校验存在错误
            //日志记录错误
            log.error("[创建订单]参数错误，orderForm={}",orderForm);
            //抛出相应日常
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        //没有错误，那么就创建订单，依次赋值
        OrderDto orderDto = new OrderDto();
        orderDto.setPhoneQuantity(orderForm.getQuantity());
        orderDto.setSpecsId(orderForm.getSpecsId());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerPhone(orderForm.getTel());
        orderDto.setBuyerName(orderForm.getName());
        //返回一个result值方便封装data数据,create()方法中存在减库存方法
        //所以在后序操作中不需要再进行减库存方法调用
        OrderDto result = orderService.create(orderDto);
        //定义一个map方法存储data数组
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVoUtil.success(map);
    }

    //查询订单详情方法,根据orderId查询
    @GetMapping("/detail/{orderId}")
    public ResultVo findOrderDetail(@PathVariable("orderId") String orderId){
        return ResultVoUtil.success(orderService.findOrderDetailByOrderId(orderId));
    }

    //支付订单
    @PutMapping("/pay/{orderId}")
    public ResultVo pay(@PathVariable("orderId") String orderId){
        //data数组可以表示map形式
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderService.pay(orderId));
        return ResultVoUtil.success(map);
    }
}
