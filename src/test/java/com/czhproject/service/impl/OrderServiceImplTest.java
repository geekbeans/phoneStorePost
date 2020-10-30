package com.czhproject.service.impl;

import com.czhproject.dto.OrderDto;
import com.czhproject.service.OrderService;
import com.czhproject.vo.OrderDetailVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @Test
    void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("哇哈哈");
        orderDto.setBuyerPhone("17777777777");
        orderDto.setBuyerAddress("湖南省岳阳市雨湖区同心大街123号");
        orderDto.setSpecsId(1);
        orderDto.setPhoneQuantity(1);

        OrderDto result = orderService.create(orderDto);
        System.out.println(result);
    }

    @Test
    void findOrderDetailByOrderId() {
        OrderDetailVo orderDetailVo = orderService.findOrderDetailByOrderId("1603935200090919041");
        int id = 10;
    }

    @Test
    void pay(){
        System.out.println(orderService.pay("1603935200090919041"));
    }
}