package com.qccr.sh.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author chenguowan
 * @date 2016-03-23
 */
public class Arith {


    /**
     * 默认保留小数两位
     */
    private static final int DEFAULT_SCALE = 2;

    /**
     * 默认小数保留策略：直接删除多余的小数位
     */
    private static final int DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_DOWN;


    /** 将金额从分转为元 */
    public static double convertCentToYuan(long cent) {
        return cent / 100.0;
    }

    /** 将金额从元转为分*/
    public static int convertYuanToCent(double yuan) {
        return BigDecimal.valueOf(yuan).movePointRight(2)
                .setScale(0, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 保留的小数精度
     *
     * @param v 值
     * @return
     */
    public static String scale(double v) {
        return scale(v, DEFAULT_SCALE);
    }

    /**
     * 保留的小数精度
     *
     * @param v     值
     * @param scale 小数位数
     * @return
     */
    public static String scale(double v, int scale) {
        return scale(v, scale, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 保留的小数精度
     *
     * @param v            值
     * @param scale        小数位数
     * @param roundingMode 策略
     * @return
     */
    public static String scale(double v, int scale, int roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(String.valueOf(v));
        return b.setScale(scale, roundingMode).toString();
    }

}
