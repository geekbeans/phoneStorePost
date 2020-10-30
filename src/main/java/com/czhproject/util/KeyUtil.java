package com.czhproject.util;

import java.util.Random;

public class KeyUtil {
    public static synchronized String createUniqueKey(){ //同时创建多个订单要保证线程安全，所以用同步方法机制
        Random random = new Random();
        //转成6位随机数
        Integer key = random.nextInt(900000) + 100000;
        //利用系统时间加上随机数就能生成唯一码
        return System.currentTimeMillis()+String.valueOf(key);
    }
}
