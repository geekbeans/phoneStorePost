package com.czhproject.util;

import com.czhproject.vo.ResultVo;

//封装一个ResultVo工具类
public class ResultVoUtil {
    //成功方法
    public static ResultVo success(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(data);
        return resultVo;
    }

    //失败方法
    public static ResultVo error(String error){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(1);
        resultVo.setMsg(error);
        resultVo.setData(null);
        return resultVo;
    }
}
