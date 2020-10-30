package com.czhproject.repository;

import com.czhproject.entity.PhoneInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneInfoRepositoryTest {
    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    //测试查询全部方法
    @Test
    void findAll(){
        List<PhoneInfo> list = phoneInfoRepository.findAll();
        for (PhoneInfo phoneInfo : list) {
            System.out.println(phoneInfo);
        }
    }

    //测试根据型号查询的方法
    @Test
    void findByCategoryType(){
        List<PhoneInfo> list = phoneInfoRepository.findByCategoryType(1);
        for (PhoneInfo phone : list) {
            System.out.println(phone);
        }
    }
}