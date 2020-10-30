package com.czhproject.service.impl;

import com.czhproject.entity.PhoneCategory;
import com.czhproject.entity.PhoneInfo;
import com.czhproject.entity.PhoneSpecs;
import com.czhproject.enums.ResultEnum;
import com.czhproject.exception.PhoneException;
import com.czhproject.phoneutil.PhoneUtil;
import com.czhproject.repository.PhoneCategoryRepository;
import com.czhproject.repository.PhoneInfoRepository;
import com.czhproject.repository.PhoneSpecsRepository;
import com.czhproject.service.PhoneService;
import com.czhproject.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    //减库存
    @Override
    public void subStock(Integer specsId, Integer quantity) {
        //手机规格数量总数=手机库存总数
        //拿到手机规格数量
        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(specsId).get();
        //拿到手机库存,phoneSpecs中就存在有id
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        //扣手机规格数量结果
        Integer result = phoneSpecs.getSpecsStock()-quantity;

        //进行规格数量判断，如果库存不足提示信息
        if(result < 0){
            //打印日志
            log.error("[扣库存] 库存不足");
            //抛出异常
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        //如果手机规格数量充足，那么就直接更并保存
        phoneSpecs.setSpecsStock(result);
        phoneSpecsRepository.save(phoneSpecs);

        //再对手机库存数量进行扣减
        result = phoneInfo.getPhoneStock()-quantity;
        //进行库存数量判断，如果库存不足提示信息
        if(result < 0){
            //打印日志
            log.error("[扣库存] 库存不足");
            //抛出异常
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        phoneInfo.setPhoneStock(result);
        phoneInfoRepository.save(phoneInfo);

    }

    //根据手机id查询手机规格
    @Override
    public SpecsPackageVo findSpecsByPhoneId(Integer phoneId) {
        //拿到phoneInfo原材料
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneId).get();
        // 查找手机规格
        List<PhoneSpecs> phoneSpecsList = phoneSpecsRepository.findAllByPhoneId(phoneId);

        //组装tree结构（拿到数据结构表更直观）

        //此为tree中K和V的数据结构
        List<PhoneSpecsVo> phoneSpecsVoList = new ArrayList<>();
        //此为List的数据结构
        List<PhoneSpecsCasVo> phoneSpecsCasVoList = new ArrayList<>();

        PhoneSpecsVo phoneSpecsVo;
        PhoneSpecsCasVo phoneSpecsCasVo;

        for (PhoneSpecs phoneSpecs: phoneSpecsList) {
            phoneSpecsVo = new PhoneSpecsVo();
            phoneSpecsCasVo = new PhoneSpecsCasVo();
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsVo);
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsCasVo);
            phoneSpecsVoList.add(phoneSpecsVo);
            phoneSpecsCasVoList.add(phoneSpecsCasVo);
        }

        //组装List结构
        TreeVo treeVo = new TreeVo();
        treeVo.setV(phoneSpecsVoList);
        List<TreeVo> treeVoList = new ArrayList<>();
        treeVoList.add(treeVo);

        //再来组装剩余的price和stock_num
        SkuVo skuVo = new SkuVo();
        //将价格转化为整型
        Integer price = phoneInfo.getPhonePrice().intValue();
        //再将价格转化为字符串进行存储
        skuVo.setPrice(price+".00");
        //设置库存
        skuVo.setStock_num(phoneInfo.getPhoneStock());
        //再将tree结构和list结构组装到一起
        skuVo.setTree(treeVoList);
        skuVo.setList(phoneSpecsCasVoList);

        //再来进行最外层封装SpecsPackageVo
        SpecsPackageVo specsPackageVo = new SpecsPackageVo();
        specsPackageVo.setSku(skuVo);
        //先将goods封装
        Map<String,String> goods = new HashMap<>();
        goods.put("picture",phoneInfo.getPhoneIcon());
        //再将goods添加到外层封装中
        specsPackageVo.setGoods(goods);

        return specsPackageVo;
    }

    //查询所有手机信息
    @Override
    public DataVo findDataVo() {
        //返回的式DataVo,所以需要先创建一个DataVo对象
        DataVo dataVo = new DataVo();
        //1.封装类型
        //先用phoneCategoryRepository.findAll找到数据库中所有entity类型数据
        List<PhoneCategory> phoneCategoryList = phoneCategoryRepository.findAll();
        //再转成PhoneCategoryVo类型的,便于传向前端
        //常规做法
        //新建一个PhoneCategoryVo类型的集合phoneCategoryVoList
//        List<PhoneCategoryVo> phoneCategoryVoList = new ArrayList<>();
//        for (PhoneCategory phoneCategory : phoneCategoryList) {
            //然后再将phoneCategory中的name和type元素赋给phoneCategoryVo
//            PhoneCategoryVo phoneCategoryVo = new PhoneCategoryVo();
//            phoneCategoryVo.setCategoryName(phoneCategory.getCategoryName());
//            phoneCategoryVo.setCategoryType(phoneCategory.getCategoryType());
            //再将赋值好的phoneCategoryVo装进集合phoneCategoryVoList
//            phoneCategoryVoList.add(phoneCategoryVo);
//        }

        //用stream流,利用lamda表达式，最后转换成集合返回
        List<PhoneCategoryVo> phoneCategoryVoList = phoneCategoryList.stream()
                .map(e -> new PhoneCategoryVo(
                e.getCategoryName(),
                e.getCategoryType()
        )).collect(Collectors.toList());
        //然后存进DataVo里面
        dataVo.setCategories(phoneCategoryVoList);

        //2.封装手机
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findByCategoryType(phoneCategoryList
                .get(0).getCategoryType());
        //常规写法
//        List<PhoneInfoVo> phoneInfoVoList = new ArrayList<>();
//
//        for (PhoneInfo phoneInfo : phoneInfoList) {
//            PhoneInfoVo phoneInfoVo = new PhoneInfoVo();
//            //用工具类BeanUtils进行大规模复制
//            //条件是只有当源数据和目标数据类型一致的时候才行
//            BeanUtils.copyProperties(phoneInfo,phoneInfoVo);
//            //tag单独赋值
//            phoneInfoVo.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
//            phoneInfoVoList.add(phoneInfoVo);
 //       }

        //stream流的方式
        List<PhoneInfoVo> phoneInfoVoList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVo(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());

        dataVo.setPhones(phoneInfoVoList);

        return dataVo;
    }

    //根据手机类型查询手机信息
    @Override
    public List<PhoneInfoVo> findPhoneInfoVoByCategoryType(Integer categoryType) {
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findByCategoryType(categoryType);
        List<PhoneInfoVo> phoneInfoVoList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVo(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());

        return phoneInfoVoList;
    }
}
