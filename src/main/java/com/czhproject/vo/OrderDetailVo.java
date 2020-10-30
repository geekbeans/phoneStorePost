package com.czhproject.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

//创建订单详情页面，要求将后端数据库中数据转到前端进行展示
@Data
public class OrderDetailVo {
    private String orderId;
    private String buyerName;
    @JsonProperty("tel")
    private String buyerPhone;
    @JsonProperty("address")
    private String buyerAddress;
    @JsonProperty("num")
    private Integer phoneQuantity;
    private String phoneName;
    @JsonProperty("specs")
    private String specsName;
    @JsonProperty("price")
    private String specsPrice;
    @JsonProperty("icon")
    private String phoneIcon;
    @JsonProperty("amount")
    private BigDecimal orderAmount;
    private Integer payStatus;
    private Integer freight = 10;
}
