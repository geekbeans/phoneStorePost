package com.czhproject.controller;

import com.czhproject.exception.PhoneException;
import com.czhproject.form.AddressForm;
import com.czhproject.service.AddressService;
import com.czhproject.util.ResultVoUtil;
import com.czhproject.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressHandler {
    @Autowired
    private AddressService addressService;
    //查找所有地址方法
    @GetMapping("/list")
    public ResultVo findAddress(){
        return ResultVoUtil.success(addressService.findAll());
    }

    //创建地址方法，是从前端视图层传到后端数据库
    //所以要进行解析才能传入,所以要加@RequestBody注解
    @PostMapping("/createAddress")
    public ResultVo createAddress(@Valid @RequestBody AddressForm addressForm,
                                  BindingResult bindingResult){//在创建订单时要和AddressForm相关联
        //进行判断，看是否有报错
        if(bindingResult.hasErrors()){
            //记录日志参数
            log.error("[参数报错]，addressForm报错，addressForm={}",addressForm);
            //抛出异常,再在Exception中增加一个msg方法
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        //没有异常，则更新数据库
        addressService.saveOrUpdate(addressForm);
        //data=null则传入空参（大纲）
        return ResultVoUtil.success(null);
    }

    //更改地址信息方法，是从前端视图层传到后端数据库
    //所以要进行解析才能传入,所以要加@RequestBody注解
    @PutMapping("/updateAddress")
    public ResultVo updateAddress(@Valid @RequestBody AddressForm addressForm,
                                  BindingResult bindingResult){//在创建订单时要和AddressForm相关联
        //进行判断，看是否有报错
        if(bindingResult.hasErrors()){
            //记录日志参数
            log.error("[参数报错]，addressForm报错，addressForm={}",addressForm);
            //抛出异常,再在Exception中增加一个msg方法
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        //没有异常，则更新数据库
        addressService.saveOrUpdate(addressForm);
        //data=null则传入空参（大纲）
        return ResultVoUtil.success(null);
    }
}
