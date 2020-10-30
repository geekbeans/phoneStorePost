package com.czhproject.service;

import com.czhproject.dto.OrderDto;
import com.czhproject.vo.OrderDetailVo;

//创建订单操作
public interface OrderService {
    //创建订单接口
    public OrderDto create(OrderDto orderDto);
    //订单详情接口
    public OrderDetailVo findOrderDetailByOrderId(String orderId);
    //支付状态接口
    public String pay(String orderId);
}
