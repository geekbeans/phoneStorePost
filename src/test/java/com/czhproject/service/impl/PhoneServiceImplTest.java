package com.czhproject.service.impl;

import com.czhproject.entity.PhoneInfo;
import com.czhproject.service.PhoneService;
import com.czhproject.vo.DataVo;
import com.czhproject.vo.PhoneInfoVo;
import com.czhproject.vo.PhoneSpecsCasVo;
import com.czhproject.vo.SpecsPackageVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneServiceImplTest {
@Autowired
private PhoneService phoneService;

    @Test
    void findDataVo() {
        DataVo dataVo = phoneService.findDataVo();
        int id = 0;
    }

    @Test
    void findPhoneInfoVoByCategoryType(){
        List<PhoneInfoVo> list = phoneService.findPhoneInfoVoByCategoryType(2);
         int id = 0;
    }

    @Test
    void findSku(){
       SpecsPackageVo packageVo = phoneService.findSpecsByPhoneId(1);
        int id = 0;
    }

    @Test
    void subStock(){
        phoneService.subStock(1,2);
    }
}