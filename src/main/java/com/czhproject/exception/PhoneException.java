package com.czhproject.exception;

import com.czhproject.enums.ResultEnum;

public class PhoneException extends RuntimeException {
    public PhoneException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }

    public PhoneException(String error){
        super(error);
    }
}
