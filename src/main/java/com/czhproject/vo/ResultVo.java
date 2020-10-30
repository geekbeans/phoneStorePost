package com.czhproject.vo;

import lombok.Data;

@Data
public class ResultVo<T> {
    private Integer code;
    private String msg;
    //根据大纲显示data为一个数值有多层数据，所以传一个泛型T
    //不传Object是因为后面还需要转换
    private T data;
}
