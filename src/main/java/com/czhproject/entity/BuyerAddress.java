package com.czhproject.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
//@DynamicInsert，@DynamicUpdate这两注解是为了解决插入时createtime没有赋值的情况
//设置这两个注解可以进行动态插入和更新，也就是说时间会自动赋值
@DynamicInsert
@DynamicUpdate
public class BuyerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String areaCode;
    private Date createTime;
    private Date updateTime;
}
