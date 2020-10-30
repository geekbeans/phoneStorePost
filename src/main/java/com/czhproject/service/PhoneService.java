package com.czhproject.service;

import com.czhproject.vo.DataVo;
import com.czhproject.vo.PhoneInfoVo;
import com.czhproject.vo.SpecsPackageVo;

import java.util.List;


public interface PhoneService {
    //查询所有数据接口
    public DataVo findDataVo();
    //根据型号查询接口
    public List<PhoneInfoVo> findPhoneInfoVoByCategoryType(Integer categoryType);
    //通过手机编号找规格
    public SpecsPackageVo findSpecsByPhoneId(Integer phoneId);
    //买家买下卖家减库存
    public void subStock(Integer specsId, Integer quantity);
}
