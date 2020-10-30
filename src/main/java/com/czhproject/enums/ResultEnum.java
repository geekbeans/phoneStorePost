package com.czhproject.enums;

import lombok.Getter;

//创建枚举对象，说明原因
@Getter
public enum  ResultEnum {
    PHONE_STOCK_ERROR(0,"库存不足"),
    ORDER_NOT_EXIST(1,"订单不存在"),
    SPECS_NOT_EXIST(2,"规格不存在"),
    PHONE_NOT_EXIST(3,"手机不存在"),
    ORDER_EXIST(4,"订单已支付");

    private Integer code;
    private String msg;

    ResultEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
