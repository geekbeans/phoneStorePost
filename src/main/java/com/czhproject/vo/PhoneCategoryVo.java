package com.czhproject.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

//针对视图层view的封装
@Data
@AllArgsConstructor
public class PhoneCategoryVo {
    //@JsonProperty("name")的意思是，在将categoryName字符串传到前端变为json数据时，会自动更名为name
    //这样便保证了视图层和前端命名一致
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
}
