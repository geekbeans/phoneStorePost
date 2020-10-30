package com.czhproject.enums;

import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

//支付状态枚举
@Getter
public enum  PayStatusEnum {
    UNPAIED(0,"未支付"),
    PAIED(1,"已支付");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
