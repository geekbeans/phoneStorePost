package com.czhproject.repository;

import com.czhproject.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    void findAll(){
        List<OrderMaster> list = orderMasterRepository.findAll();
        for (OrderMaster orderMaster : list) {
            System.out.println(orderMaster);
        }
    }

    @Test
    void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123565");
        orderMaster.setBuyerName("何兴成");
        orderMaster.setBuyerPhone("1876889555");
        orderMaster.setBuyerAddress("湖南省雨花区胜利街001号");
        orderMaster.setPhoneId(1);
        orderMaster.setPhoneName("iphone12");
        orderMaster.setPhoneQuantity(12);
        orderMaster.setPhoneIcon("1d65227f-b7ce-4175-bae3-0010d83b2e9a.jpg");
        orderMaster.setSpecsId(1);
        orderMaster.setSpecsName("256GB");
        orderMaster.setSpecsPrice(new BigDecimal(8888));
        orderMaster.setOrderAmount(new BigDecimal(3200));
        orderMaster.setPayStatus(0);
        orderMasterRepository.save(orderMaster);
    }

    @Test
    void findById(){
        //返回的式Option类，需要加.get()才能获取到
        OrderMaster orderMaster = orderMasterRepository.findById("123565").get();
        System.out.println(orderMaster);
    }

    @Test
    void pay(){
        //首先要在数据库中根据id找到需要修改订单状态的手机对象，然后再做修改和保存
        OrderMaster orderMaster = orderMasterRepository.findById("123565").get();
        orderMaster.setPayStatus(1);
        orderMasterRepository.save(orderMaster);
    }
}