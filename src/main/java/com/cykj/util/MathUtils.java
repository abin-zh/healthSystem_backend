package com.cykj.util;

import java.math.BigDecimal;

/**
 * 数学和数字工具类
 * @author abin
 * @date 2024/7/20 16:40
 */
public class MathUtils {

    /**
     * 判断一个数否为整数
     * @param num 需要判断的数
     * @return 是否为整数
     */
    public static boolean isInteger(BigDecimal num){
            return num.stripTrailingZeros().scale() <= 0;
    }

}
