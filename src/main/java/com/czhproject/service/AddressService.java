package com.czhproject.service;

import com.czhproject.form.AddressForm;
import com.czhproject.vo.AddressVo;

import java.util.List;


public interface AddressService {
    public List<AddressVo> findAll();
    public void saveOrUpdate(AddressForm addressForm);
}
