package com.czhproject.controller;

import com.czhproject.service.PhoneService;
import com.czhproject.util.ResultVoUtil;
import com.czhproject.vo.DataVo;
import com.czhproject.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phone")
public class PhoneHandler {
    @Autowired
    private PhoneService phoneService;
    //返回所有参数值
    @GetMapping("/index")
    public ResultVo index(){
        //在浏览器打印显示出来装jsonview插件
        return ResultVoUtil.success(phoneService.findDataVo());
    }

    //根据categoryId查询具体手机信息
    @GetMapping("/findByCategoryType/{categoryId}")
    public ResultVo findByCategoryType(@PathVariable("categoryId") Integer categoryId){
        return ResultVoUtil.success(phoneService.findPhoneInfoVoByCategoryType(categoryId));
    }

    //根据phoneId查询具体手机信息
    @GetMapping("/findSpecsByPhoneId/{phoneId}")
    public ResultVo findSpecsByPhoneId(@PathVariable("phoneId") Integer phoneId){
        return ResultVoUtil.success(phoneService.findSpecsByPhoneId(phoneId));
    }
}
