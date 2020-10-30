package com.czhproject.repository;

import com.czhproject.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerAddressRepositoryTest {

    @Autowired
    private BuyerAddressRepository buyerAddressRepository;

    @Test
    void findAll(){
        List<BuyerAddress> list = buyerAddressRepository.findAll();
        for (BuyerAddress buyerAddress : list) {
            System.out.println(buyerAddress);
        }
    }

    @Test
    void save(){
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setBuyerName("张小红");
        buyerAddress.setAreaCode("084388");
        buyerAddress.setBuyerAddress("北京市海淀区中关村");
        buyerAddress.setBuyerPhone("13978786788");
        buyerAddressRepository.save(buyerAddress);
    }

    @Test
    void update(){
        //因为返回类型是Option类，所以.get()防止报异常
        BuyerAddress buyerAddress = buyerAddressRepository.findById(1).get();
        buyerAddress.setBuyerName("王哈哈");
        buyerAddressRepository.save(buyerAddress);
    }
}