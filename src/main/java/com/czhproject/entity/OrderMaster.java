package com.czhproject.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
//动态修改
@DynamicUpdate
//动态添加
@DynamicInsert
public class OrderMaster {
    @Id
    //此处id用String类型不用Integer的原因是，因为订单量很大没有上限，Integer有上限
    //String就没有自增字段
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private Integer PhoneId;
    private String phoneName;
    private Integer phoneQuantity;
    private String phoneIcon;
    private Integer specsId;
    private String specsName;
    private BigDecimal specsPrice;
    private BigDecimal orderAmount;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
}
