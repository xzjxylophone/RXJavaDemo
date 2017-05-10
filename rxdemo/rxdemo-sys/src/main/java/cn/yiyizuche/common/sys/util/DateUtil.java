package cn.yiyizuche.common.sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.sys.util
 * @FileName : DateUtil.java
 * @Description : 日期工具类
 * @author : lipeng
 * @CreateDate : 2017/3/14 10:48
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String EXCEL_DATE_YEAR_MONTH = "yyyy/MM";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_YEAR_MONTH = "yyyy-MM";
    public static final String FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String FORMAT_HOUR_MINUTE_SECOND = "HH:mm:ss";
    public static final String CN_DATE_FORMAT = "yyyy年MM月dd日";

    public static final String PURE_FORMATE_YEAR = "yyyy";
    public static final String PURE_FORMATE_MONTH = "yyyyMM";
    public static final String PURE_FORMATE_DAY = "yyyyMMdd";

    /**
     * @Method: getNow
     * @Description: 获取当前时间
     * @param
     * @return java.util.Date
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 10:49
     */
    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }

    /**
     * @Method: str2Date
     * @Description: 按指定格式将字符串转换为日期
     * @param dateStr 时间字符串
     * @param pattern 格式模板
     * @return java.util.Date
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 10:49
     */
    public static Date str2Date(String dateStr, String pattern)
            throws Exception {
        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }
        if (null == pattern) {
            pattern = DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(pattern);
        return format.parse(dateStr);
    }

    /**
     * @Method: str2DateTime
     * @Description: 按指定格式将字符串转换为日期时间
     * @param dateStr 时间字符串
     * @param pattern 格式模板
     * @return java.util.Date
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 10:51
     */
    public static Date str2DateTime(String dateStr, String pattern)
            throws ParseException {
        if (null == dateStr) {
            return null;
        }
        if (null == pattern) {
            pattern = DEFAULT_DATE_TIME_FORMAT;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(pattern);
        return format.parse(dateStr);
    }

    /**
     * @Method: str2DateTime
     * @Description: 将日期格式化为字符串
     * @param date 日期对象
     * @param pattern 格式模板
     * @return java.util.Date
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 10:51
     */
    public static String date2Str(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        if (null == pattern) {
            pattern = DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(pattern);
        return format.format(date);
    }

    /**
     * @Method: dateTime2Str
     * @Description: 将日期时间格式化为字符串
     * @param date 日期对象
     * @param pattern 格式模板
     * @return java.lang.String
     * @author : lipeng
     * @CreateDate : 2017/3/14 10:52
     */
    public static String dateTime2Str(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        if (null == pattern) {
            pattern = DEFAULT_DATE_TIME_FORMAT;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(pattern);
        return format.format(date);
    }

    /**
     * @Method: getCurrentTime
     * @Description: 取得当前时间戳字符串
     * @return java.lang.String 返回yyyyMMddHHmmssSSS格式字符串
     * @author : lipeng
     * @CreateDate : 2017/3/14 10:53
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    /**
     * @Method: thisDate
     * @Description: 取得当前日期
     * @return java.lang.String 返回yyyy-MM-dd格式字符串
     * @author : lipeng
     * @CreateDate : 2017/3/14 10:54
     */
    public static String thisDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(calendar
                .getTime());
    }

    /**
     * @Method: thisTime
     * @Description: 取得当前时间
     * @return java.lang.String 返回HH:mm:ss格式时间字符串
     * @author : lipeng
     * @CreateDate : 2017/3/14 10:59
     */
    public static String thisTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(calendar
                .getTime());
    }

    /**
     * @Method: thisDateTime
     * @Description: 取得当前完整日期时间
     * @return java.lang.String 返回yyyy-MM-dd HH:mm:ss格式字符串
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:00
     */
    public static String thisDateTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).format(calendar
                .getTime());
    }

    /**
     * @Method: getLastDayOfMonth
     * @Description: 获取某月最后一天的日期数字
     * @param firstDate 第一天日期
     * @return int 最后一天日期数字
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:01
     */
    public static int getLastDayOfMonth(Date firstDate) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(firstDate);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * @Method: getDateFormat
     * @Description: 获取SimpleDateFormat
     * @param parttern 日期格式
     * @return java.text.SimpleDateFormat SimpleDateFormat对象
     * @throws RuntimeException
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:04
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    /**
     * @Method: getTodayMin
     * @Description: 取得今天的最小时间
     * @return java.util.Date
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:05
     */
    public static Date getTodayMin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * @Method: getTodayMax
     * @Description: 取得今天的最大时间
     * @return java.util.Date
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:05
     */
    public static Date getTodayMax() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
    }

    /**
     * @Method: getTomorrowMin
     * @Description: 取得明天的最小时间
     * @return java.util.Date
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:06
     */
    public static Date getTomorrowMin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);

        return cal.getTime();
    }

    /**
     * @Method: getTomorrowMax
     * @Description: 取得明天的最大时间
     * @return java.util.Date
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:06
     */
    public static Date getTomorrowMax() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        cal.add(Calendar.DATE, 1);

        return cal.getTime();
    }

    /**
     * @Method: genDiffDate
     * @Description: 由指定时间、时间域、数额，计算时间值
     * @param standard 指定时间
     * @param type 时间域
     * @param amount 数额
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:06
     */
    public static Date genDiffDate(Date standard, int type, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(standard);
        cal.add(type, amount);

        return cal.getTime();
    }

    /**
     * @Method: genHourStart
     * @Description: 生成指定时间所在的小时段（清空：分钟、秒、毫秒）
     * @param standard 指定时间
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:11
     */
    public static Date genHourStart(Date standard) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(standard);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * @Method: getBeforeDayMin
     * @Description: 取得当前天后，减去指定天数后的最小时间
     * @param date 指定日期
     * @param beforeDay 指定天数
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:11
     */
    public static Date getBeforeDayMin(Date date, int beforeDay) {

        return getDayMin(date, -beforeDay);
    }

    /**
     * @Method: getBeforeDayMax
     * @Description: 取得当前天后，减去指定天数后的最大时间
     * @param date 指定日期
     * @param beforeDay 指定天数
     * @return java.util.Date
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:12
     */
    public static Date getBeforeDayMax(Date date, int beforeDay) {

        return getDayMax(date, -beforeDay);
    }

    /**
     * @Method: getAfterDayMin
     * @Description: 取得当前天后，加上指定天数后的最小时间
     * @param date 指定日期
     * @param afterDay 指定天数
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:13
     */
    public static Date getAfterDayMin(Date date, int afterDay) {

        return getDayMin(date, afterDay);
    }

    /**
     * @Method: getAfterDayMax
     * @Description: 取得当前天后，加上指定天数后的最大时间
     * @param date 指定日期
     * @param afterDay 指定天数
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:14
     */
    public static Date getAfterDayMax(Date date, int afterDay) {

        return getDayMax(date, afterDay);
    }

    /**
     * @Method: getDayMin
     * @Description: 取得当前天后，加上指定天数后的最小时间
     * @param date 指定日期
     * @param addDay 指定天数
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:14
     */
    public static Date getDayMin(Date date, int addDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, addDay);

        return cal.getTime();
    }

    /**
     * @Method: getDayMax
     * @Description: 取得当前天 ,加上指定天数后的最大时间
     * @param date 指定日期
     * @param addDay 指定天数
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:15
     */
    public static Date getDayMax(Date date, int addDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        cal.add(Calendar.DATE, addDay);

        return cal.getTime();
    }

    /**
     * @Method: addDays
     * @Description: 取得当前天 ,加上指定天数后的时间
     * @param date 指定日期
     * @param addDay 指定天数
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:15
     */
    public static Date addDays(Date date, int addDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, addDay);
        return cal.getTime();
    }

    /**
     * @Method: addMonths
     * @Description: 取得当前天 ,加上指定月份数后的时间
     * @param date 指定日期
     * @param months 指定月份数
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:16
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * @Method: diff
     * @Description: 日期差天数(按照时间比较,如果不足一天会自动补足)
     * @param date1 日期1
     * @param date2 日期2
     * @return int
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:17
     */
    public static int diff(Date date1, Date date2) throws Exception {
        long day = 24L * 60L * 60L * 1000L;
        String str1 = date2Str(date1, "yyyy-MM-dd");
        date1 = str2Date(str1, "yyyy-MM-dd");
        String str2 = date2Str(date2, "yyyy-MM-dd");
        date2 = str2Date(str2, "yyyy-MM-dd");

        return (int) (((date2.getTime() - date1.getTime()) / day));
        // return (int) Math.ceil((((date2.getTime() - date1.getTime()) / (24 *
        // 60 * 60 * 1000d))));
    }

    /**
     * @Method: diff
     * @Description: 日期差天数(和当前时间比)
     * @param date 比较日期
     * @return int 和当前日期差天数
     * @throws  Exception
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:18
     */
    public static int diff(Date date) throws Exception {
        return diff(new Date(), date);
    }

    /**
     * @Method: compareDate
     * @Description: 按固定格式比较两个日期
     * @param date1 日期1
     * @param date2 日期2
     * @param pattern 日期格式
     * @return int
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:19
     */
    public static int compareDate(Date date1, Date date2, String pattern) {
        String d1 = date2Str(date1, pattern);
        String d2 = date2Str(date2, pattern);
        return d1.compareTo(d2);
    }

    /**
     *
     */
    /**
     * @Method: compareDateTime
     * @Description: 按固定格式比较两个日期+时间
     * @param time1 时间日期1
     * @param time2 时间日期2
     * @param pattern 时间日期模板
     * @return int
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:20
     */
    public static int compareDateTime(Date time1, Date time2, String pattern) {
        String d1 = dateTime2Str(time1, pattern);
        String d2 = dateTime2Str(time2, pattern);
        return d1.compareTo(d2);
    }

    /**
     * @Method: isLeapyear
     * @Description: 判断是否是闰年
     * @param date
     * @return boolean
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:21
     */
    public static boolean isLeapyear(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.isLeapYear(gregorianCalendar
                .get(Calendar.YEAR));
    }

    /**
     * @Method: getLastDateOfMonth
     * @Description: 根据传入日期得到本月月末
     * @param date 指定日期
     * @return java.util.Date 月末日期
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:21
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getLastDateOfMonth(c);
    }

    /**
     * @Method: getFirstDateOfMonth
     * @Description: 根据传入日期得到本月月初
     * @param date 指定日期
     * @return java.util.Date
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:22
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getFirstDateOfMonth(c);
    }

    /**
     * @Method: getFirstDateOfMonth
     * @Description: 根据传入日期得到本月月初
     * @param calendar Calendar对象
     * @return java.util.Date 月末日期
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:23
     */
    public static Date getFirstDateOfMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * @Method: getLastDateOfMonth
     * @Description: 根据传入日期得到本月月末
     * @param calendar Calendar对象
     * @return java.util.Date 月末日期
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:24
     */
    public static Date getLastDateOfMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * @Method: isLastDateOfMonth
     * @Description: 判断是否为月末日期
     * @param date 指定日期
     * @return boolean
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:25
     */
    public static boolean isLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return isLastDateOfMonth(c);
    }

    /**
     * @Method: isLastDateOfMonth
     * @Description: 判断是否为月末日期
     * @param calendar Calendar对象
     * @return boolean
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:27
     */
    public static boolean isLastDateOfMonth(Calendar calendar) {
        if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar
                .get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }

    /**
     * @Method: getYear
     * @Description: 根据日期得到年份
     * @param date 指定日期
     * @return int
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:28
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * @Method: getMonth
     * @Description: 根据日期得到月份
     * @param date 指定日期
     * @return int
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:29
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * @Method: getDay
     * @Description: 根据日期得到日
     * @param date 指定日期
     * @return int
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:29
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @Method: formatMilliseconds
     * @Description: 时间格式化
     * @param millonSeconds 时间毫秒数
     * @param language “chinese”字符串 和 非“chinese”字符串
     * @return java.lang.String
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:30
     */
    public static String formatMilliseconds(long millonSeconds, String language) {
        String v = "";
        long second = millonSeconds / 1000;// 转换为秒
        long millonSecond = millonSeconds - second * 1000;// 多出的不足一秒的毫秒数
        boolean isChinese = language.equalsIgnoreCase("chinese");
        if (isChinese) {
            v += millonSecond + "毫秒";
        } else {
            v += millonSecond + "ms.";
        }
        if (second > 0)// 如果还有秒
        {
            long minutes = second / 60;// 分钟取整
            second = second - minutes * 60;// 不足一分钟的秒数
            if (isChinese) {
                v = second + "秒" + v;
            } else {
                v = second + "s" + v;
            }
            if (minutes > 0)// 如果还有分钟
            {
                long hours = minutes / 60;// 小时取整
                minutes = minutes - hours * 60;// 不足一小时的分钟数
                if (isChinese) {
                    v = minutes + "分" + v;
                } else {
                    v = minutes + "minutes " + v;
                }
                if (hours > 0) {
                    long days = hours / 24;// 天取整
                    hours = hours - days * 24;// 不足一天的小时数
                    if (isChinese) {
                        v = hours + "小时" + v;
                    } else {
                        v = hours + "hours " + v;
                    }
                    if (days > 0) {
                        if (isChinese) {
                            v += days + "天" + v;
                        } else {
                            v += days + " days " + v;
                        }
                    }
                }
            }
        }
        return v;
    }

    /**
     * @Method: formatMilliseconds
     * @Description: 时间格式化
     * @param millonSeconds 时间格式化
     * @return java.lang.String
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/14 11:32
     */
    public static String formatMilliseconds(long millonSeconds) {
        return formatMilliseconds(millonSeconds, "CHINESE");
    }
    
    /**
     * 
     * @Method: getWeekOfDate  
     * @Description: 获取日期的星期, 对应关系如下
     * 				{ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
     * 				{ "0", "1", "2", "3", "4", "5", "6" };
     * @param date
     * @return int (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月15日 下午5:45:28
     */
    public static int getWeekOfDate(Date date) {
    	Calendar calendar = Calendar.getInstance(); 
    	calendar.setTime(date); 
    	int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    	return intWeek;
    }
    
    /**
     * 
     * @Method: getWeekStringOfDate  
     * @Description:获取日期是星期几
     * @param date
     * @return String (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月16日 下午5:29:02
     */
    public static String getWeekStringOfDate(Date date) {
    	String[] valueString = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    	int intWeek = DateUtil.getWeekOfDate(date);
    	return valueString[intWeek];
    }
    
    /**
     * 
     * @Method: isWeekdayOfDate  
     * @Description: 判断日期是否是周六或者周日
     * @param date
     * @return boolean (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月15日 下午5:46:55
     */
    public static boolean isWeekdayOfDate(Date date) {
    	int week = DateUtil.getWeekOfDate(date);
    	return week == 6 || week == 0;
    }
    
    /**
     * 
     * @Method: isSameDate  
     * @Description:判断2个日期是否相等,
     * @param d1
     * @param d2
     * @param format:  yyyyMMdd  或者 yyyyMMdd hhmmss 等等
     * @return boolean (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月15日 下午6:33:36
     */
    public static boolean isSameDate(Date d1, Date d2, String format){
    		if (d1 == null || d2 == null) {
			return false;
		}
        SimpleDateFormat fmt = new SimpleDateFormat(format);  
        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置  
        return fmt.format(d1).equals(fmt.format(d2));  
    }  
    
    
    /**
     * 
     * @Method: isAfter  
     * @Description:d1 是否在 d2 之后
     * @param d1
     * @param d2
     * @param format
     * @return
     * @throws Exception boolean (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月30日 上午11:55:30
     */
    public static boolean isAfter(Date d1, Date d2, String format) throws Exception {
    		if (d1 == null || d2 == null) {
			return false;
		}
    		String s1 = DateUtil.date2Str(d1, format);
    		String s2 = DateUtil.date2Str(d2, format);
    		Date newD1 = DateUtil.str2Date(s1, format);
    		Date newD2 = DateUtil.str2Date(s2, format);
        return newD1.after(newD2);
    }
    
    /**
     * 
     * @Method: isBefore  
     * @Description:d1 是否在 d2 之前
     * @param d1
     * @param d2
     * @param format
     * @return
     * @throws Exception boolean (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月30日 上午11:55:45
     */
    public static boolean isBefore(Date d1, Date d2, String format) throws Exception {
    		if (d1 == null || d2 == null) {
			return false;
		}
    		String s1 = DateUtil.date2Str(d1, format);
    		String s2 = DateUtil.date2Str(d2, format);
    		Date newD1 = DateUtil.str2Date(s1, format);
    		Date newD2 = DateUtil.str2Date(s2, format);
        return newD1.before(newD2);
    }
    public static boolean isBeforeOrEqual(Date d1, Date d2, String format) throws Exception {
    		return DateUtil.isBefore(d1, d2, format) || DateUtil.isEqual(d1, d2, format);
    }
    

    public static boolean isAfterOrEqual(Date d1, Date d2, String format) throws Exception {
    		return DateUtil.isAfter(d1, d2, format) || DateUtil.isEqual(d1, d2, format);
    }
    
    /**
     * 
     * @Method: isEqual  
     * @Description:d1 和 d2 是否是一样
     * @param d1
     * @param d2
     * @param format
     * @return boolean (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月30日 上午11:55:58
     */
    public static boolean isEqual(Date d1, Date d2, String format) {
    		return DateUtil.isSameDate(d1, d2, format);
    }
    
}
