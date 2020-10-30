package com.czhproject.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

//前端表单传放到后端
@Data
public class AddressForm {
    //id不加非空验证，原因是，在进行添加操作时，不能将id字段添加进去（结构中没有id字段）
    private Integer id;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "电话不能为空")
    private String tel;
    @NotBlank(message = "详细地址不能为空")
    private String addressDetail;
    @NotBlank(message = "区编号不能为空")
    private String areaCode;
    @NotBlank(message = "省份不能为空")
    private String province;
    @NotBlank(message = "城市不能为空")
    private String city;
    //private String country;
}
