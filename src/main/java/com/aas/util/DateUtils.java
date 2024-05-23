package com.aas.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * DataUtils
 */
public class DateUtils {

    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strodate = formatter.parse(strDate, pos);
        return strodate;
    }


    /**
     * 格式为: yyyy-MM-dd
     */
    private static String defaultFormat = "yyyy-MM-dd";
    /**
     * 格式为: yyyy-MM-dd
     */
    public static String DATE_FORMAT_YMD = "yyyy-MM-dd";
    /**
     * 格式为: yyyyMMdd
     */
    public static String DATE_FORMAT_LX_YMD = "yyyyMMdd";
    /**
     * 格式为: yyyy-MM-dd HH
     */
    public static String DATE_FORMAT_YMDH = "yyyy-MM-dd HH";
    /**
     * 格式为: yyyy-MM-dd HH:mm
     */
    public static String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    /**
     * 格式为: yyyy-MM-dd HH:mm:ss
     */
    public static String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式为: yyyyMMddHHmmss
     */
    public static String DATE_FORMAT_LX_YMDHMS = "yyyyMMddHHmmss";
    /**
     * 格式为: yyyyMMddHHmm
     */
    public static String DATE_FORMAT_LX_YMDHM = "yyyyMMddHHmm";
    /**
     * 格式为: HHmmss
     */
    public static String DATE_FORMAT_LX_HMS = "HHmmss";
    /**
     * 格式为:  yyyyMM
     */
    public static String DATE_FORMAT_LX_YM = "yyyyMM";

    /**
     * 得到时间字符串
     */
    public static String getTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);// 时:分:秒.毫秒
        GregorianCalendar gc = new GregorianCalendar();
        return sdf.format(gc.getTime());

    }

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);
    }

    /***
     * 时间字符串比较大小
     *
     * @param date1 日期1
     * @param date2 日期2
     * @param format 时间格式 格式为：YYYY-MM-dd
     * @return 返回状态：1：日期1>日期2、0：日期1=日期2、-1：日期1<日期2
     */
    public static String compDateStr(String date1, String date2, String format) {
        String res = "";
        DateFormat df = new SimpleDateFormat(format);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {

            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));

            int result = c1.compareTo(c2);
            if (result == 0) {
                res = "0";
            } else if (result < 0) {
                res = "-1";
            } else {
                res = "1";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /***
     * 时间比较大小
     *
     * @param date1 日期1
     * @param date2 日期2
     * @param format 时间格式 格式为：YYYY-MM-dd
     * @return 返回状态：1：日期1>日期2、0：日期1=日期2、-1：日期1<日期2
     */
    public static String compDate(Date date1, Date date2, String format) {
        String res = "";

        DateFormat df = new SimpleDateFormat(format);
        String d1 = df.format(date1);
        String d2 = df.format(date2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {

            c1.setTime(df.parse(d1));
            c2.setTime(df.parse(d2));

            int result = c1.compareTo(c2);
            if (result == 0) {
                res = "0";
            } else if (result < 0) {
                res = "-1";
            } else {
                res = "1";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将指定日期按照指定格式转换成字串
     *
     * @param date   指定日期
     * @param format 指定格式
     * @return 日期字串
     */
    public static String dateToStr(Date date, String format) {
        if (date == null)
            return null;
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        String strDate = simpledateformat.format(date);
        return strDate;
    }

    /**
     * 将字串形式的指定日期按照指定格式转换成字串
     *
     * @param strDate 字串形式的指定日期
     * @param format  指定格式
     * @return 日期字串
     */
    public static String strToStr(String strDate, String format) {
        strDate = formatStr(strDate);
        if (strDate == null)
            return null;
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        ParsePosition parseposition = new ParsePosition(0);
        Date date = simpledateformat.parse(strDate, parseposition);

        strDate = simpledateformat.format(date);
        return strDate;
    }

    /**
     * 将字串形式的指定日期按照指定格式转换成日期
     *
     * @param strDate 字串形式的指定日期（格式：2006-10-16 15:10:03的部分）
     * @param format  指定格式
     * @return 日期
     */
    public static Date strToDate(String strDate, String format) {

        strDate = formatStr(strDate);
        if (strDate == null)
            return null;
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        ParsePosition parseposition = new ParsePosition(0);
        Date date = simpledateformat.parse(strDate, parseposition);
        return date;
    }

    /**
     * 将指定日期按照指定格式转换成日期
     *
     * @param date 指定日期
     * @param format    指定格式
     * @return 日期
     */
    public static Timestamp dateToTimestamp(Date date, String format) {
        // SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        // // Date date=new Date();
        // String time=simpledateformat.format(date);
        // Timestamp ts=Timestamp.valueOf(time);
        Date dt = dateToDate(date, format);
        Timestamp ts = new Timestamp(dt.getTime());

        return ts;
    }

    /**
     * 将指定日期按照指定格式转换成日期
     *
     * @param timet 指定日期
     * @param format    指定格式
     * @return 日期
     */
    public static Date timestampToDate(Timestamp timet, String format) {

        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        Timestamp time = timet;
        String str = simpledateformat.format(time);

        return strToDate(str, format);
    }

    /**
     * 将指定日期按照指定格式转换成日期
     *
     * @param date   指定日期
     * @param format 指定格式
     * @return 日期
     */
    public static Date dateToDate(Date date, String format) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        String strDate = simpledateformat.format(date);

        ParsePosition parseposition = new ParsePosition(0);
        date = simpledateformat.parse(strDate, parseposition);
        return date;
    }

    /**
     * 将日期字串格式化为"yyyy-MM-dd HH:mm:ss"形式
     *
     * @param strDate 日期字串（格式：2006-10-16 15:10:03的部分）
     * @return 格式化后的日期字串
     */
    private static String formatStr(String strDate) {
        if ("".equals(strDate) || strDate == null) {
            return null;
        }
        String strYear = "1900", strMonth = "01", strDay = "01";
        String strHour = "00", strMinute = "00", strSecond = "00";

        // 用" "分割年月日和时分秒
        String[] strs = strDate.split(" ");

        // 获取年月日
        String strYMD = strs[0];

        // 获取时分秒
        String strHMS;
        if (strs.length < 2) {
            strHMS = "00:00:00";
        } else {
            strHMS = strs[1];
        }

        // 用"-"分割年、月和日
        String[] strYMSs = strYMD.split("-");

        // 获取年
        if (strYMSs.length > 0 && !"".equals(strYMSs[0])) {
            strYear = strYMSs[0];
        }
        // 获取月
        if (strYMSs.length > 1) {
            strMonth = strYMSs[1].length() == 1 ? ("0" + String
                    .valueOf(strYMSs[1])) : String.valueOf(strYMSs[1]);
        }
        // 获取日
        if (strYMSs.length > 2) {
            strDay = strYMSs[2].length() == 1 ? ("0" + String
                    .valueOf(strYMSs[2])) : String.valueOf(strYMSs[2]);
        }

        // 用":"分割时分秒
        String[] strHMSs = strHMS.split(":");

        // 获取时
        if (strHMSs.length > 0) {
            strHour = strHMSs[0];
        }
        // 获取分
        if (strHMSs.length > 1) {
            strMinute = strHMSs[1].length() == 1 ? ("0" + String
                    .valueOf(strHMSs[1])) : String.valueOf(strHMSs[1]);
        }
        // 获取秒
        if (strHMSs.length > 2) {
            strSecond = strHMSs[2].length() == 1 ? ("0" + String
                    .valueOf(strHMSs[2])) : String.valueOf(strHMSs[2]);
        }

        strDate = strYear + "-" + strMonth + "-" + strDay + " " + strHour + ":"
                + strMinute + ":" + strSecond;

        return strDate;
    }

    /**
     * 将日期字串"yyyy-MM-dd"与"HH:mm"合并，作为date输出
     *
     * @param ymd 日期字串（格式：2006-10-16部分）
     * @param hm  日期字串（格式：12：22部分）
     * @return 合并后的date
     */
    public static Date combineYmdHm(String ymd, String hm) {
        String ymdhm = ymd + " " + hm;
        Date d = strToDate(ymdhm, DATE_FORMAT_YMDHM);
        return d;
    }

    /**
     * 将日期字串"yyyy-MM-dd"转换为date，并且是当前日期最后一秒
     *
     * @param ymd 日期字串（格式：2006-10-16部分）
     * @return 合并后的date
     */
    public static Date endOfDate(String ymd) {
        String ymdhms = ymd + " " + "23:59:59";
        Date d = strToDate(ymdhms, DATE_FORMAT_YMDHMS);
        return d;
    }

    /**
     * @param format
     * @return
     */
    public static String getCurrentDay(String format) {
        if (format == null) {
            format = defaultFormat;
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String sdate = sdf.format(date);
        return sdate;
    }

    /**
     * 转化日期格式
     *
     * @param dateStr   需要转化的日期
     * @return 转化后的日期
     */

    public static Date convertToDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }

        try {
            DateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD);
            return format.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符转时间格式化（yyyy-MM-dd）
     *
     * @param date 字符类型
     * @return 时间类型
     */
    public static Date DateFormat(String date) {
        Date fdate = null;
        if (date != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                fdate = df.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
            return fdate;
        }
        return null;
    }

    /**
     * 获取当前季度
     * @return
     */
    public static String examQuarter(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int quarter = 0;
        switch (month) {
            case 1:
            case 2:
            case 3:
                quarter = 1;
                break;
            case 4:
            case 5:
            case 6:
                quarter = 2;
                break;
            case 7:
            case 8:
            case 9:
                quarter = 3;
                break;
            case 10:
            case 11:
            case 12:
                quarter = 4;
                break;
            default:
                quarter = 1;
                break;
        }
        return year + "Q" + quarter;
    }

    /**
     * 获取日期的季度
     * @param date yyyy-mm-dd 字符串日期
     * @return
     */
    public static String examQuarter(String date){
        Calendar ca = Calendar.getInstance();
        ca.setTime(DateFormat(date));
        int month = ca.get(Calendar.MONTH);//第几个月
        int year = ca.get(Calendar.YEAR);//年份数值
        int quarter = 0;
        switch (month) {
            case 1:
            case 2:
            case 3:
                quarter = 1;
                break;
            case 4:
            case 5:
            case 6:
                quarter = 2;
                break;
            case 7:
            case 8:
            case 9:
                quarter = 3;
                break;
            case 10:
            case 11:
            case 12:
                quarter = 4;
                break;
            default:
                quarter = 1;
                break;
        }
        return year + "Q" + quarter;
    }

}
