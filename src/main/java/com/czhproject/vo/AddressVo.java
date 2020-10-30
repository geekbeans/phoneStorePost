package com.czhproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressVo {
    private Integer id;
    private String areaCode;
    private String name;
    private String tel;
    private String address;
}
