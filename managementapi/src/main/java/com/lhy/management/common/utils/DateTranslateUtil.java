package com.lhy.management.common.utils;

import com.lhy.management.common.exception.TimeTransferException;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 时间转换工具类
 *
 * @author Angel
 * @since 2019/9/16 16:24
 */
public class DateTranslateUtil {

    public static String HH_MM_SS = "HH:mm:ss";

    public static String HH_MM = "HH:mm";

    /**
     * Date 转 String
     * @param date 需要转换的时间
     * @param formatForm 转换格式
     * @return 转换后的String
     */
    public static String dateToString( Date date , String formatForm){
        SimpleDateFormat formatter = new SimpleDateFormat(formatForm);
        return formatter.format(date);
    }

    /**
     * String 转 TimeStamp
     * Timestamp format must be yyyy-mm-dd hh:mm:ss
     * @param timeStr 需要转换的字符串
     * @return 转换后的TimeStamp
     */
    public static Timestamp stringToTimestamp(String timeStr){
        try {
            return Timestamp.valueOf(timeStr);
        }catch (Exception e){
            e.printStackTrace();
            throw new TimeTransferException("时间转换异常");
        }
    }

    /**
     * TimeStamp 转 String
     * @param ts 需要转换的时间戳
     * @param formatForm 转换的格式
     * @return 转换后的字符串
     */
    public static String timeStampToString(Timestamp ts , String formatForm){
        DateFormat sdf = new SimpleDateFormat(formatForm);
        try {
            return sdf.format(ts);
        }catch (Exception e){
            throw new TimeTransferException("时间转换异常");
        }
    }

    /**
     * String 转 Date
     * @param timeStr 需要转换的时间字符串
     * @param formatForm 转换格式
     * @return 转换后的Date
     */
    public static Date stringToDate(String timeStr, String formatForm){
        DateFormat sdf = new SimpleDateFormat(formatForm);
        try {
            return sdf.parse(timeStr);
        }catch (Exception e){
            throw new TimeTransferException("时间转换异常");
        }
    }

    public static Time stringToTime(String timeStr, String formatForm){
        SimpleDateFormat sdf = new SimpleDateFormat(formatForm);
        try {
            return new Time(sdf.parse(timeStr).getTime());
        }catch (Exception e){
            throw new TimeTransferException("时间转换异常");
        }
    }
}
