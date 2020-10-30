package com.czhproject.vo;

import lombok.Data;

import java.util.Map;

//根据文档结构，大体分为goods集合和sku类集合
@Data
public class SpecsPackageVo {
    private Map<String,String> goods;
    private SkuVo sku;
}
