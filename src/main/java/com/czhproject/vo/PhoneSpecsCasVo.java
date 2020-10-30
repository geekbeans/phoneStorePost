package com.czhproject.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

//此为list的数据结构
@Data
public class PhoneSpecsCasVo {
    @JsonProperty("s1")
    private Integer specsId;
    @JsonProperty("price")
    private BigDecimal specsPrice;
    @JsonProperty("stock_num")
    private Integer specsStock;
}
