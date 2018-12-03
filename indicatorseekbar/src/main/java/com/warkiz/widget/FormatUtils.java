package com.warkiz.widget;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * by zhyhang
 * from https://blog.csdn.net/zhyhang/article/details/17973049
 */
class FormatUtils {

    private final static char[][] LEADING_DECIMALS = new char[][]{
        "0.".toCharArray(), "0.0".toCharArray(),
        "0.00".toCharArray(), "0.000".toCharArray(), "0.0000".toCharArray(),
        "0.00000".toCharArray(),
        "0.000000".toCharArray(), "0.0000000".toCharArray(), "0.00000000".toCharArray(),
        "0.000000000".toCharArray(), "0.0000000000".toCharArray(), "0.00000000000".toCharArray(),
        "0.000000000000".toCharArray(), "0.0000000000000".toCharArray(),
        "0.00000000000000".toCharArray(),
        "0.000000000000000".toCharArray()
    };

    /**
     * 快速格式化一个double，尾零去除（非对齐）<br>
     * 等同于:<br>
     * NumberFormat f = NumberFormat.getNumberInstance();<br>
     * f.setGroupingUsed(false);<br>
     * f.setMaximumFractionDigits(precision);<br>
     * f.format(d);<br>
     * 但一般情况效率高于NumberFormat一倍，且精度无丢失。<br>
     *
     * @param d         the double value
     * @param precision [0,16]
     */
    static String fastFormat(double d, int precision) {
        int posPrecision = Math.abs(precision);
        double roundUpVal = Math.abs(d) * Math.pow(10d, posPrecision) + 0.5d;
        if (roundUpVal > 999999999999999d || posPrecision > 16) {// double has max 16 precisions
            return bigDecFormat(d, posPrecision);
        }
        long longPart = (long) Math.nextUp(roundUpVal);
        if (longPart < 1) {
            return "0";
        }
        char[] longPartChars = Long.toString(longPart).toCharArray();
        char[] formatChars;
        if (longPartChars.length > posPrecision) {
            int end = longPartChars.length - 1;
            int decIndex = longPartChars.length - posPrecision;
            while (end >= decIndex && longPartChars[end] == '0') {
                end--;
            }
            if (end >= decIndex) {
                formatChars = new char[end + 2];
                System.arraycopy(longPartChars, 0, formatChars, 0, decIndex);
                formatChars[decIndex] = '.';
                System.arraycopy(longPartChars, decIndex, formatChars,
                    decIndex + 1, end - decIndex + 1);
            } else {
                formatChars = new char[decIndex];
                System.arraycopy(longPartChars, 0, formatChars, 0, decIndex);
            }
        } else {
            int end = longPartChars.length - 1;
            while (end >= 0 && longPartChars[end] == '0') {
                end--;
            }
            char[] leadings = LEADING_DECIMALS[posPrecision - longPartChars.length];
            formatChars = Arrays.copyOf(leadings, leadings.length + end + 1);
            System.arraycopy(longPartChars, 0, formatChars, leadings.length, end + 1);
        }
        return Math.signum(d) > 0 ? new String(formatChars) : "-" + new String(formatChars);
    }

    private static String bigDecFormat(double d, int precision) {
        String formatStr = new BigDecimal(Double.toString(d)).setScale(Math.abs(precision), RoundingMode.HALF_UP)
            .toString();
        if (precision == 0) {
            return formatStr;
        }
        int end = formatStr.length() - 1;
        while (end >= 0 && formatStr.charAt(end) == '0') {
            end--;
        }
        formatStr = formatStr.substring(0, end + 1);
        if (formatStr.charAt(formatStr.length() - 1) == '.') {
            formatStr = formatStr.substring(0, formatStr.length() - 1);
        }
        return formatStr;
    }

}
