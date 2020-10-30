package com.czhproject.vo;

import lombok.Data;

import java.util.List;

//此为tree的数据结构
@Data
public class TreeVo {
    private String k = "规格";
    private List<PhoneSpecsVo> v;
    private String k_s = "s1";
}
