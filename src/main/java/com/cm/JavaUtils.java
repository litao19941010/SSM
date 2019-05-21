package com.cm;

import java.math.BigDecimal;

/**
 * 该类封装了常用的java方法
 */
public class JavaUtils {
    /**
     * 返回文件的大小，单位MB
     * @param size long类型
     * @return 保留两位小数，四舍五入
     */
    public static double getFileByMBInLong(long size){
        BigDecimal bd = new BigDecimal(size);
        BigDecimal divisor = new BigDecimal(1024);
        //KB
        BigDecimal kb = bd.divide(divisor);
        //MB
        BigDecimal mb = kb.divide(divisor);
        double value = mb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return value;
    }
    /**
     * 返回文件的大小，单位MB
     * @param size int类型
     * @return 保留两位小数，四舍五入
     */
    public static double getFileByMBInInt(int size){
        BigDecimal bd = new BigDecimal(size);
        BigDecimal divisor = new BigDecimal(1024);
        //KB
        BigDecimal kb = bd.divide(divisor);
        //MB
        BigDecimal mb = kb.divide(divisor);
        double value = mb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return value;
    }
}
