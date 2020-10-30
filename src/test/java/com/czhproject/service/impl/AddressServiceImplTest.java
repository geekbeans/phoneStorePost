package com.czhproject.service.impl;

import com.czhproject.form.AddressForm;
import com.czhproject.service.AddressService;
import com.czhproject.vo.AddressVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {
    @Autowired
    private AddressService addressService;

    @Test
    void findAll() {
        List<AddressVo> list = addressService.findAll();
        int id = 0;
    }

    @Test
    void saveOrUpdate(){
        AddressForm addressForm = new AddressForm();
        //进行更新买家信息的时候就要用setId方法，必须要传入对应的买家id，不能是没有的id
        //addressForm.setId(36);
        addressForm.setName("小李飞刀");
        //addressForm.setAreaCode("166677");
        //addressForm.setCountry("中国");
        addressForm.setProvince("福建省");
        addressForm.setCity("福州市");
        addressForm.setAddressDetail("胜利街678号");
        addressForm.setTel("18888888888");
        addressService.saveOrUpdate(addressForm);
    }
}