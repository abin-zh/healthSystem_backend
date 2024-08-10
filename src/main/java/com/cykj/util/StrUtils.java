package com.cykj.util;

/**
 * 字符串工具类
 * @author abin
 * @date 2024/7/19 15:09
 */
public class StrUtils {

    /**
     * 字符串判空(不为null且不为'')
     * @param str 字符串
     * @return 是否为空字符串
     */
    public static boolean isEmpty(String str){
        if(str == null || str.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 多个字符串判空
     * @param strs 字符串数组
     * @return 数组中是否存在空字符串
     */
    public static boolean hasEmpty(String... strs){
        for (String str : strs) {
            if (isEmpty(str)){
                return true;
            }
        }
        return false;
    }

}
