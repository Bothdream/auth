package com.zte.auth.utils;


import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Objects;

/**
 * 时间字符串转换工具
 * @date 2021/2/27
 * @author zsq
 */
public class DateUtil {
    private DateUtil() throws IllegalAccessException {
        throw new IllegalAccessException("illegal init");
    }
    public static final String YEAR = "yyyy";

    public static final String MONTH = "yyyy-MM";

    public static final String DATE = "yyyy-MM-dd";

    public static final String HOUR = "yyyy-MM-dd HH:00";

    public static final String MINUTE = "yyyy-MM-dd HH:mm";

    public static final String SEC = "yyyy-MM-dd HH:mm:ss";


    /**
     *将时间转换为指定格式的时间字符串
     * @date 2021/2/27
     * @param time 时间
     * @param timeFormat 转换时间字符串的格式
     * @return 时间字符串
     */
    public static String date2TimeStr(Date time,String timeFormat){
        if (Objects.isNull(time) || StringUtils.isBlank(timeFormat)) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        Instant instant = time.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 将时间字符转为时间类型
     * @date 2021/2/27
     * @param timeStr 时间字符串
     * @param timeFormat 转换时间字符串的格式
     * @return 时间
     */
    public static Date timeStr2Date(String timeStr,String timeFormat){
        if (StringUtils.isAnyBlank(timeStr,timeFormat)) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(timeFormat);
        LocalDateTime localDateTime = LocalDateTime.parse(timeStr, df);
        return localDateTime2Date(localDateTime);
    }

    /**
     * 时间字符转为时间类型
     * 解决：2020-12-01 --> date
     *      2020-12-01 08:00 --> date
     * 报错的转换方法
     * @date 2021/2/27
     * @param timeStr 时间字符安串
     * @return 时间
     */
    public static Date timeStr2Date(String timeStr){
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd[' '[HH][:mm][:ss]]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                .toFormatter();
        LocalDateTime localDateTime = LocalDateTime.parse(timeStr, formatter);
        return localDateTime2Date(localDateTime);
    }

    /**
     * LocalDateTime -> Date
     * @date 2021/2/27
     * @return Date
     */
    private static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
