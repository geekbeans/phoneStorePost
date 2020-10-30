package com.czhproject.dto;

import lombok.Data;

//前端获取到的数据
@Data
public class OrderDto {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private Integer specsId;
    private Integer phoneQuantity;
}
