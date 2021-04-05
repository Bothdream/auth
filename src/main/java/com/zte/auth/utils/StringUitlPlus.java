package com.zte.auth.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串转换工具
 * @date 2021/2/27
 * @author zsq
 */
public class StringUitlPlus {
    private StringUitlPlus() throws IllegalAccessException {
        throw new IllegalAccessException("illegal init");
    }

    /**
     * 转换数据查询的字段：支持查询特殊字符
     * 如：_test_ ==> 转义为：/_test/_
     * @param fieldVal 属性的值
     * @param specialChar 特殊通配字符（_、%、$）
     * @return String
     */
    public static String escapeQueryFieldVal(String fieldVal, String specialChar) {
        String escapeChar = SpecialConstants.SLASH;
        if (StringUtils.isBlank(fieldVal)) {
            return StringUtils.EMPTY;
        }
        StringBuilder strAfterEscaped = new StringBuilder(fieldVal);
        if ( fieldVal.startsWith(specialChar) || fieldVal.endsWith(specialChar) ) {
            if (fieldVal.length() == 1) {
                strAfterEscaped.insert(0, escapeChar);
            } else {
                // 字符串开头有特殊字符
                if (fieldVal.charAt(0) == specialChar.charAt(0)) {
                    strAfterEscaped.insert(0,escapeChar);
                }
                // 字符串结尾有特殊字符
                if (fieldVal.charAt(fieldVal.length() - 1) == specialChar.charAt(0)) {
                    strAfterEscaped.insert(strAfterEscaped.length() - 1,escapeChar);
                }
            }
        }
        return strAfterEscaped.toString();
    }
}
