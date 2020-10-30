package com.czhproject.phoneutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//定义工具类方法，来将tag标签进行分割
public class PhoneUtil {
    public static List<Map<String,String>> createTag(String tag){
        //将tag字符串通过&分割，再存入字符数组
        String[] tags = tag.split("&");
        //新建一个集合
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map;

        //将所有名字分别取出放入list集合中
        for (String s : tags) {
            map = new HashMap<>();
            map.put("name",s);
            list.add(map);
        }
        return list;
    }
}
