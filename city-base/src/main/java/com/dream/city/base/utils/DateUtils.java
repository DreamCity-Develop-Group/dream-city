package com.dream.city.base.utils;

import org.apache.commons.lang.StringUtils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtils {

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_NUMBER = "yyyyMMddHHmmss";
    public static final String DAY_PATTERN = "yyyy-MM-dd";
    public static final String YEAR_MONTH_DAY_EN_SECOND  = "yyyy/MM/dd HH:mm:ss";
    public static final String YEAR_MONTH_DAY_CN_SECOND = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String YEAR_MONTH_DAY_CN = "yyyy年MM月dd日";

    /**
     * 采用 ThreadLocal 避免 SimpleDateFormat 非线程安全的问题
     * <p>
     * Key —— 时间格式
     * Value —— 解析特定时间格式的 SimpleDateFormat
     */
    private static ThreadLocal<Map<String, SimpleDateFormat>> sThreadLocal = new ThreadLocal<>();


    /**
     * 获取解析特定时间格式的 SimpleDateFormat
     *
     * @param pattern 时间格式
     */
    private static SimpleDateFormat getDateFormat(String pattern) {
        if (StringUtils.isBlank(pattern)){
            pattern = DEFAULT_FORMAT;
        }
        Map<String, SimpleDateFormat> strDateFormatMap = sThreadLocal.get();

        if (strDateFormatMap == null) {
            strDateFormatMap = new HashMap<>();
        }

        SimpleDateFormat simpleDateFormat = strDateFormatMap.get(pattern);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
            strDateFormatMap.put(pattern, simpleDateFormat);
            sThreadLocal.set(strDateFormatMap);
        }

        return simpleDateFormat;
    }


    public static Date toDate(LocalDateTime dateTime) {
        if(dateTime == null) return null;
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String format(LocalDateTime dateTime, String format){
        if(dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime parse(String dateTimeStr, String format){
        if (StringUtils.isBlank(format)){
            format = DEFAULT_FORMAT;
        }
        if(dateTimeStr == null) return null;
        DateTimeFormatter sf = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, sf);
        return dateTime;
    }

    public static String format(LocalDateTime dateTime){
        return format(dateTime, DEFAULT_FORMAT);
    }

    public static LocalDateTime parse(String dateTimeStr){
        return parse(dateTimeStr, DEFAULT_FORMAT);
    }

    // #############################################################################

    /**
     * 时间格式转换为字符串格式
     *
     * @param date   时间
     * @param format 格式 如("yyyy-MM-dd hh:mm:ss")
     * @return String
     */
    public static String date2Str(Date date, String format) {
        if (StringUtils.isBlank(format)){
            format = DEFAULT_FORMAT;
        }
        if (date == null) {
            return null;
        }
        return getDateFormat(format).format(date);
    }
    public static String date2Str(Date date) {
        if (date == null) {
            return null;
        }
        return getDateFormat(DEFAULT_FORMAT).format(date);
    }

    /**
     * 字符串格式转换为时间格式
     *
     * @param dateStr 字符串
     * @param format  格式 如("yyyy-MM-dd HH:mm:ss")
     * @return Date
     */
    public static Date str2Date(String dateStr, String format) {
        if (StringUtils.isBlank(format)){
            format = DEFAULT_FORMAT;
        }
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            Date dateTemp = getDateFormat(format).parse(dateStr);
            return dateTemp;
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }
    public static Date str2Date(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            Date dateTemp = getDateFormat(DEFAULT_FORMAT_NUMBER).parse(dateStr);
            return dateTemp;
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param timestamp：时间
     */
    public static Date parse(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 获取当前日期
     *
     * @param pattern 格式如:yyyy-MM-dd
     * @return
     */
    public static String getCurrentDate(String pattern) {
        return getDateFormat(pattern).format(new Date());
    }

    /**
     * get cuurent Date return java.util.Date type
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * get current Date return java.sql.Date type
     *
     * @return
     */
    public static java.sql.Date getNowSqlDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * get the current timestamp return java.sql.Timestamp
     *
     * @return
     */
    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * time1>time2 返回正数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getOffsetBetweenTimes(String time1, String time2) {
        return str2Date(time1, DEFAULT_FORMAT).getTime() - str2Date(time2, DEFAULT_FORMAT).getTime();
    }

    /**
     * 对指定日期滚动指定天数,负数时,则往前滚,正数时,则往后滚
     *
     * @param date Date
     * @param days int
     * @return String
     */
    public static String rollDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return date2Str(calendar.getTime(), DEFAULT_FORMAT);
    }

    /**
     * 对指定日期滚动指定分钟,负数时,则往前滚,正数时,则往后滚
     *
     * @param date Date
     * @param minutes int
     * @return String
     */
    public static String rollMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return date2Str(calendar.getTime(), DEFAULT_FORMAT);
    }

    /**
     * 对指定日期滚动指定分钟,负数时,则往前滚,正数时,则往后滚
     *
     * @param date Date
     * @param seconds int
     * @return String
     */
    public static String rollSeconds(Date date, int seconds, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return date2Str(calendar.getTime(), format);
    }

    /**
     * 返回  2013-01-16T06:24:26.829Z 时间
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static XMLGregorianCalendar getXmlDatetime(Date date) throws Exception {
        if (null == date) {
            date = new Date();
        }
        GregorianCalendar nowGregorianCalendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
        // XMLGregorianCalendar ->GregorianCalendar
        nowGregorianCalendar = xmlDatetime.toGregorianCalendar();
        nowGregorianCalendar.setTime(date);
        return xmlDatetime;
    }

    public static boolean checkDateBettwenBoth(Date checkDate, Date date1, Date date2) {
        boolean temp = false;
        if (checkDate == null || date1 == null || date2 == null) {
            temp = false;
            return temp;
        }

        if (checkDate.equals(date1) || checkDate.equals(date2)) {
            temp = true;
        }

        if (checkDate.after(date1) && checkDate.before(date2)) {
            temp = true;
        }

        return temp;
    }

    public static String getFormatDatetime()
            throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String strDateTime;
        try {
            strDateTime = getDateFormat(DEFAULT_FORMAT).format(gCalendar.getTime());
        } catch (Exception ex) {
            System.out.println("Error Message:".concat(String.valueOf(String.valueOf(ex.toString()))));
            String s = null;
            return s;
        }
        return strDateTime;
    }


    /**
     * 判断给定的日期是一年中的第几天
     *
     * @param dateTimeStr
     * @return
     */
    public static int dayOfYear(String dateTimeStr) {
        int day = 0;
        if (StringUtils.isBlank(dateTimeStr)) {
            return day;
        }
        Date dateTime = str2Date(dateTimeStr,DEFAULT_FORMAT);
        return dayOfYear(dateTime);
    }


    /**
     * 判断给定的日期是一年中的第几天
     *
     * @param dateTime
     * @return
     */
    public static int dayOfYear(Date dateTime) {
        int year = 0, month = 0, date = 0, day = 0;
        if (null == dateTime) {
            return day;
        }
        String dateTimeStr = date2Str(dateTime, null);
        LocalDateTime localDateTime = parse(dateTimeStr);
        year = localDateTime.getYear();
        month = localDateTime.getMonthValue();
        date = localDateTime.getDayOfMonth();
        return dayOfYear(year, month, date);
    }


    /**
     * 判断给定的日期是一年中的第几天
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static int dayOfYear(int year, int month, int date) {
        int[] days = {0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int day = 0;
        //判断是不是闰年，然后设置二月的天数
        if ((year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0))) {
            days[2] = 29;
        } else {
            days[2] = 28;
        }
        for (int i = 1; i < month; i++) {
            day += days[i];
        }
        day += date;
        return day;
    }


}
