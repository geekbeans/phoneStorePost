package com.czhproject.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderForm {
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "电话不能为空")
    private String tel;
    @NotBlank(message = "地址不能为空")
    private String address;
    @NotNull(message = "规格不能为空")
    private Integer specsId;
    @NotNull(message = "数量不能为空")
    private Integer quantity;
}
