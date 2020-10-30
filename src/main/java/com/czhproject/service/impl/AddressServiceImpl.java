package com.czhproject.service.impl;

import com.czhproject.entity.BuyerAddress;
import com.czhproject.form.AddressForm;
import com.czhproject.repository.BuyerAddressRepository;
import com.czhproject.service.AddressService;
import com.czhproject.vo.AddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private BuyerAddressRepository buyerAddressRepository;

    //查询所有地址
    @Override
    public List<AddressVo> findAll() {
        //用lamda表达式赋值给AddressVo中的各个变量
        //固定规则写法
        List<AddressVo> list = buyerAddressRepository.findAll().stream().map(e->new AddressVo(
                e.getAddressId(),
                e.getAreaCode(),
                e.getBuyerName(),
                e.getBuyerPhone(),
                e.getBuyerAddress()
        )).collect(Collectors.toList());
        return list;
    }

    @Override
    public void saveOrUpdate(AddressForm addressForm) {
        BuyerAddress buyerAddress;

        if(addressForm.getId() == null){//如果从表单中找不到用户id
            //说明此用户不存在，可能需要添加操作
            buyerAddress = new BuyerAddress();
        }else{//如果从表单中能够查找到用户id，说明此时用户存在,说明进行更新操作
            //先将用户从数据库中根据id查找出来
            buyerAddress = buyerAddressRepository.findById(addressForm.getId()).get();
        }
        //进行更新操作
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
       // buyerAddress.setAreaCode(addressForm.getAreaCode());
        //在进行地址添加的时候要将国家-省-市-区进行拼接
        StringBuffer buyerAddr = new StringBuffer();
        buyerAddr.append(addressForm.getProvince())
                .append(addressForm.getCity()).append(addressForm.getAddressDetail());
        buyerAddress.setBuyerAddress(buyerAddr.toString());
        //再存进数据库
        buyerAddressRepository.save(buyerAddress);
    }

}
