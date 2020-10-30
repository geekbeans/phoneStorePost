package com.czhproject.vo;

import lombok.Data;

import java.util.List;

//VO包全称view object,是视图对象，目的是对entity中的实体对象进一步封装成所需要的类
//也就意味着vo包中存的是封装后的entity实体类
//根据对应文档去看
@Data
public class DataVo {
    private List<PhoneCategoryVo> categories;
    private List<PhoneInfoVo> phones;
}
