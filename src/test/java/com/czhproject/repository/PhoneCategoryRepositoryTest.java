package com.czhproject.repository;

import com.czhproject.entity.PhoneCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneCategoryRepositoryTest {
    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;

    //测试查询全部
    @Test
    public void findAll(){
        List<PhoneCategory> list = phoneCategoryRepository.findAll();
        for (PhoneCategory phoneCategory:list
             ) {
            System.out.println(phoneCategory);
        }
    }

    //测试根据类型查询
    @Test
    public void findByCategoryType(){
        PhoneCategory phone = phoneCategoryRepository.findByCategoryType(1);
        System.out.println(phone);

    }
}