package com.linln.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.*;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * 日期处理类
 */
public class DateUtil {

    private static final int[] DAY_OF_MONTH = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static final String[] DATE_FORMATS = new String[]{
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy-MM",
            "yyyy-MM-dd HH:mm:ss.S", "yyyy年MM月dd日", "yyyy年MM月dd日 HH:mm", "yyyyMMdd", "yyyy年MM月dd日 HH:mm:ss", "yyyy年M月"};

    /**
     * 将传入的日期转化为"yyyy-MM-dd"形式的字符串
     *
     * @param dt 日期
     * @return 指定日期格式的字符串
     */
    public static String formatDate(Date dt) {

        return formatDate("yyyy-MM-dd", dt);
    }

    /**
     * 将传入的日期转化为"yyyy-MM-dd HH:mm:ss"形式的字符串
     *
     * @param dt 日期
     * @return 指定日期格式的字符串
     */
    public static String formatDateYMDHMS(Date dt) {

        return formatDate("yyyy-MM-dd HH:mm:ss", dt);
    }

    /**
     * 将传入的日期转化为"yyyy-MM-dd HH:mm"形式的字符串
     *
     * @param dt 日期
     * @return 指定日期格式的字符串
     */
    public static String formatDateYMDHM(Date dt) {

        return formatDate("yyyy-MM-dd HH:mm", dt);
    }

    /**
     * 将传入的日期以指定格式转成字符串
     *
     * @param format
     * @param dt
     * @return
     */
    public static String formatDate(String format, Date dt) {
        if (dt == null) {
            return "";
        }
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }

        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(dt);
    }

    /**
     * 获取今天的日期
     *
     * @return
     */
    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * 得到传入日期n天后的日期,如果传入日期为null，则表示当前日期n天后的日期
     *
     * @param days 可以为任何整数，负数表示前days天，正数表示后days天
     * @return Date
     */
    public static Date getAddDayDate(Date dt, int days) {
        Calendar cal = Calendar.getInstance();
        if (dt != null) {
            cal.setTime(dt);
        }
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 得到传入日期n天后的日期list
     *
     * @param days 可以为任何整数，负数表示前days天，正数表示后days天
     * @return Date
     */
    public static List<String> getAddDayDateList(Date dt, int days) {
        List<String> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        if (dt != null) {
            cal.setTime(dt);
        }
        for (int i = 0; i <= days; i++) {
            cal.setTime(dt);
            cal.add(Calendar.DATE, i);
            list.add(formatDate(cal.getTime()));
        }
        return list;
    }

    /**
     * 得到当前日期几天后（plusDays>0）or 几天前（plusDays<0）的指定格式的字符串日期
     *
     * @param plusDays
     * @param dateFormat
     * @return
     */
    public static String getAddDayDateFromToday(int plusDays, String dateFormat) {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_MONTH, plusDays);

        return formatDate(dateFormat, cal.getTime());
    }

    /**
     * 给定的时间再加上指定小时数,如果传入日期为null，能以当前时间计算
     *
     * @param dt
     * @param hours
     * @return
     * @author Alex Zhang
     */
    public static Date getAddHourDate(Date dt, int hours) {

        if (dt == null)
            dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.HOUR, hours);

        return cal.getTime();
    }

    /**
     * 给定的时间再加上指定分钟数
     *
     * @param dt
     * @param minutes
     * @return
     * @author Alex Zhang
     */
    public static Date getAddMinuteDate(Date dt, int minutes) {
        if (dt == null)
            dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MINUTE, minutes);

        return cal.getTime();
    }

    /**
     * 给定的时间再加上指定月份数
     *
     * @param dt
     * @param months
     * @return
     * @author wei suicun
     */
    public static Date getAddMonthDate(Date dt, int months) {

        if (dt == null)
            dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MONTH, months);

        return cal.getTime();
    }

    /**
     * 获得某天的零点时刻0:0:0
     *
     * @param date 日期
     * @return
     */
    public static Date getDayBegin(Date date) {

        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得某天的截至时刻23:59:59
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {

        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 某月的起始时间,eg：param:2011-11-10 12:10:50.999, return：2011-11-1 00:00:00.000
     */
    public static Date getMonthBeginTime(Date dt) {

        if (dt == null)
            dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 某月的截止时间,eg：param:2011-11-10 12:10:50.999, return：2011-11-30 23:59:59.999
     */
    public static Date getMonthEndTime(Date dt) {

        if (dt == null)
            dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }

    /**
     * 获得传入日期的年\月\日,以整型数组方式返回
     *
     * @param dt
     * @return int[]
     */
    public static int[] getTimeArray(Date dt) {

        if (dt == null)
            dt = new Date(System.currentTimeMillis());
        int[] timeArray = new int[3];
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        timeArray[0] = cal.get(Calendar.YEAR);
        timeArray[1] = cal.get(Calendar.MONTH) + 1;
        timeArray[2] = cal.get(Calendar.DAY_OF_MONTH);
        return timeArray;
    }

    /**
     * 获得传入日期的年\月\日\小时\分,以整型数组方式返回
     *
     * @param dt
     * @return
     */
    public static int[] timeArray(Date dt) {

        if (dt == null)
            dt = new Date(System.currentTimeMillis());
        int[] timeArray = new int[5];
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        timeArray[0] = cal.get(Calendar.YEAR);
        timeArray[1] = cal.get(Calendar.MONTH) + 1;
        timeArray[2] = cal.get(Calendar.DAY_OF_MONTH);
        timeArray[3] = cal.get(Calendar.HOUR_OF_DAY);
        timeArray[4] = cal.get(Calendar.MINUTE);
        return timeArray;
    }

    /**
     * 根据年月日得到Date类型时间
     *
     * @param year
     * @param month
     * @param day
     * @return Date
     * @author Alan he <alan.he@jongogroup.com>
     */
    public static Date getTime(Integer year, Integer month, Integer day) {

        Calendar cal = Calendar.getInstance();
        if (year != null)
            cal.set(Calendar.YEAR, year);
        if (month != null)
            cal.set(Calendar.MONTH, month - 1);
        if (day != null)
            cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 计算两个日期间相隔的小时
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return
     */
    public static int getHourBetween(Date d1, Date d2) {

        long m = d1.getTime();
        long n = d2.getTime();
        return (int) ((m - n) / 3600000);
    }

    /**
     * 取得两个时间之间的天数，可能是负数(第二个时间的日期小于第一个时间的日期)。如果两个时间在同一天，则返回0
     *
     * @param d1 第一个时间
     * @param d2 第二个时间
     * @return
     * @author Derek
     * @version 1.0 2009-10-14
     */
    public static int getDayBetween(Date d1, Date d2) {

        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return (int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / 86400000);
    }

    /**
     * 计算两个日期间相隔的秒数
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return
     */
    public static long getSecondBetweem(Date d1, Date d2) {

        return (d1.getTime() - d2.getTime()) / 1000;
    }

    /**
     * 计算两个日期间相隔的月份数 (endDate - beginDate)
     *
     * @param endDate   结束日期
     * @param beginDate 开始日期
     * @return
     */
    public static int getMonthBetween(Date endDate, Date beginDate) {

        Calendar endCal = Calendar.getInstance();
        Calendar beginCal = Calendar.getInstance();
        endCal.setTime(endDate);
        beginCal.setTime(beginDate);

        return (endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR)) * 12 + (endCal.get(Calendar.MONTH) - beginCal.get(Calendar.MONTH));
    }

    /**
     * 通过生日得到当前年龄
     *
     * @param birthDate 以日期表示的生日
     * @return 返回以以字符串表示的年龄, 最小为0
     */
    public static String getAge(Date birthDate) {

        if (birthDate == null) {
            return "未知";
        }

        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDate)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDate);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                }
            } else {
                age--;
            }
        } else {
        }
        return age + "";
    }


    /**
     * 计算周岁
     *
     * @param brithday 出生日期
     * @return
     */
    public static int getIntAge(Date brithday) {
        Date currentDate = Calendar.getInstance().getTime();
        return getIntAge(brithday, currentDate);
    }

    /**
     * 计算周岁
     *
     * @param brithday 出生日期
     * @param curDate
     * @return
     */
    public static int getIntAge(Date brithday, Date curDate) {

        if (brithday != null) {
            int dateMiss = curDate.getDate()
                    - brithday.getDate();//日差距
            int monthMiss = curDate.getMonth()
                    - brithday.getMonth();// 月份差距
            int yearMiss = curDate.getYear()
                    - brithday.getYear();// 年份差距
            if (monthMiss > 0 || (monthMiss == 0 && dateMiss >= 0)) {
                return yearMiss;
            } else {
                return yearMiss - 1;// 周岁少两岁，SO在去掉一年
            }
        }
        return 0;
    }


    /**
     * 计算开始日期不能大于结束日期(只校验到月）
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean afterUntilMonth(Date start, Date end) {
        if (start != null && end != null) {
            int monthMiss = end.getMonth() - start.getMonth();// 月份差距
            int yearMiss = end.getYear() - start.getYear();// 年份差距
            if (yearMiss == 0) {
                if (monthMiss >= 0) {
                    return true;
                } else {
                    return false;
                }
            }else if (yearMiss > 0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * 根据生日计算当前周岁数
     */
    public int getCurrentAge(Date birthday) {
        // 当前时间
        Calendar curr = Calendar.getInstance();
        // 生日
        Calendar born = Calendar.getInstance();
        born.setTime(birthday);
        // 年龄 = 当前年 - 出生年
        int age = curr.get(Calendar.YEAR) - born.get(Calendar.YEAR);
        if (age <= 0) {
            return 0;
        }
        // 如果当前月份小于出生月份: age-1
        // 如果当前月份等于出生月份, 且当前日小于出生日: age-1
        int currMonth = curr.get(Calendar.MONTH);
        int currDay = curr.get(Calendar.DAY_OF_MONTH);
        int bornMonth = born.get(Calendar.MONTH);
        int bornDay = born.get(Calendar.DAY_OF_MONTH);
        if ((currMonth < bornMonth) || (currMonth == bornMonth && currDay <= bornDay)) {
            age--;
        }
        return age < 0 ? 0 : age;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }


    /**
     * 根据周几的数字标记获得周几的汉字描述
     */
    public static String getCnWeekDesc(int weekNum) {
        String strWeek = "";
        switch (weekNum) {
            case 1:
                strWeek = "周一";
                break;
            case 2:
                strWeek = "周二";
                break;
            case 3:
                strWeek = "周三";
                break;
            case 4:
                strWeek = "周四";
                break;
            case 5:
                strWeek = "周五";
                break;
            case 6:
                strWeek = "周六";
                break;
            case 7:
                strWeek = "周日";
                break;
        }
        return strWeek;
    }


    /**
     * 获得'上下午'标识
     *
     * @param date
     * @return
     */
    public static String getCnAMPM(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (Calendar.AM == cal.get(Calendar.AM_PM))
            return "上午";
        else
            return "下午";

    }

    /**
     * 判断两个日期是否相等
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return
     */
    public static boolean isTimeEquals(Date d1, Date d2) {

        if (d1 == null || d2 == null)
            return false;
        return Math.abs(d1.getTime() - d2.getTime()) < 50;
    }

    /**
     * 获取一个日期的年份
     *
     * @param date 日期
     * @return
     */
    public static int getYear(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取一个日期的月份
     *
     * @param date 日期
     * @return
     */
    public static int getMonthOfYear(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    /**
     * 获取一个日期的天数
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一个日期的小时数
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取一个日期的分钟
     *
     * @param date 日期
     * @return
     */
    public static int getMinute(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取一个日期的秒数
     *
     * @param date 日期
     * @return
     */
    public static int getSecond(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取一个月的最大天数
     *
     * @param year  年份
     * @param month 月份
     * @return
     */
    public static int getMaxDayOfMonth(int year, int month) {

        if (month == 1 && isLeapYear(year)) {
            return 29;
        }
        return DAY_OF_MONTH[month];
    }

    /**
     * 判断是否是润年
     *
     * @param year 年份
     * @return
     */
    public static boolean isLeapYear(int year) {

        Calendar calendar = Calendar.getInstance();
        return ((GregorianCalendar) calendar).isLeapYear(year);
    }


    /**
     * 得到本周的起始时间
     *
     * @param currentDate
     * @return
     */
    public static Date getBeginDateofThisWeek(Date currentDate) {
        Calendar current = Calendar.getInstance();
        current.setTime(currentDate);
        int dayOfWeek = current.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) { //如果是星期天，星期一则往前退6天
            current.add(Calendar.DAY_OF_MONTH, -6);
        } else {
            current.add(Calendar.DAY_OF_MONTH, 2 - dayOfWeek);
        }

        current.set(Calendar.HOUR_OF_DAY, 0);
        current.set(Calendar.MINUTE, 0);
        current.set(Calendar.SECOND, 0);
        current.set(Calendar.MILLISECOND, 0);

        return current.getTime();
    }


    /**
     * 转化时间从指定格式日期为长整形
     *
     * @param format
     * @param time
     * @return
     */
    public static Long convertDateStringToDateLong(String format, String time)
            throws ParseException {
        if (time == null || time.trim().equals("")) {
            return null;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        Date d = fmt.parse(time);
        return d.getTime();
    }

    /**
     * 获取从今天开始未来一周的星期和日期的映射表
     * 1-星期一:2014-05-12，2-星期二:2014-05-13.....
     *
     * @return
     */
    public static Map<String, Date> getDateForWeekDay() {
        Map<String, Date> weekDayDateMap = new HashMap<String, Date>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 1; i <= 7; i++) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (dayOfWeek == 0) {
                dayOfWeek = 7;
            }
            weekDayDateMap.put(dayOfWeek + "", calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return weekDayDateMap;
    }

    /**
     * 获得本日星期数,星期一:1,星期日:7
     * 如果传入null则默认为本日
     *
     * @return
     */
    public static int getDayOfWeek(Calendar calendar) {
        int today;
        if (calendar != null) {
            today = calendar.get(Calendar.DAY_OF_WEEK);
        } else {
            today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        }
        if (today == 1)
            return 7;
        else
            return today - 1;
    }

    /**
     * 获取日期的中国式星期几（1-7分别代表周一至周日）
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getDayOfWeek(cal);
    }

    /**
     * 判断两个日期是否为同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);

        if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }

        return false;
    }

    public static LocalDateTime utilDateToLocalDateTime(Date date) {
        if (date == null) return null;
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }


    /**
     * 获取当前系统时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime() {
        DateTime dt = new DateTime();
        String time = dt.toString(DATE_FORMATS[0]);
        return time;
    }

    /**
     * 获取系统当前时间按照指定格式返回
     *
     * @param pattern yyyy/MM/dd hh:mm:a
     * @return
     */
    public static String getCurrentTimePattern(String pattern) {
        DateTime dt = new DateTime();
        String time = dt.toString(pattern);
        return time;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        DateTime dt = new DateTime();
        String date = dt.toString(DATE_FORMATS[0]);
        return date;
    }

    /**
     * 获取当前日期按照指定格式
     *
     * @param pattern
     * @return
     */
    public static String getCurrentDatePattern(String pattern) {
        DateTime dt = new DateTime();
        String date = dt.toString(pattern);
        return date;
    }

    /**
     * 按照时区转换时间
     *
     * @param date
     * @param timeZone 时区
     * @param parrten
     * @return
     */
    @Nullable
    public static String format(Date date, TimeZone timeZone, String parrten) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(parrten);
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    /**
     * 获取指定时间
     *
     * @param year    年
     * @param month   月
     * @param day     天
     * @param hour    小时
     * @param minute  分钟
     * @param seconds 秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getPointTime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer seconds) {
        DateTime dt = new DateTime(year, month, day, hour, minute, seconds);
        String date = dt.toString(DATE_FORMATS[0]);
        return date;
    }

    /**
     * @param year    年
     * @param month   月
     * @param day     天
     * @param hour    小时
     * @param minute  分钟
     * @param seconds 秒
     * @param parrten 自定义格式
     * @return parrten
     */
    public static String getPointTimePattern(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer seconds, String parrten) {
        DateTime dt = new DateTime(year, month, day, hour, minute, seconds);
        String date = dt.toString(parrten);
        return date;
    }

    /**
     * 获取指定日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getPointDate(Integer year, Integer month, Integer day) {
        LocalDate dt = new LocalDate(year, month, day);
        String date = dt.toString(DATE_FORMATS[0]);
        return date;
    }

    /**
     * 获取指定日期 返回指定格式
     *
     * @param year
     * @param month
     * @param day
     * @param parrten
     * @return
     */
    public static String getPointDatParrten(Integer year, Integer month, Integer day, String parrten) {
        LocalDate dt = new LocalDate(year, month, day);
        String date = dt.toString(parrten);
        return date;
    }

    /**
     * 获取当前是一周星期几
     *
     * @return
     */
    public static String getWeek() {
        DateTime dts = new DateTime();
        String week = null;
        switch (dts.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                week = "星期日";
                break;

            case DateTimeConstants.MONDAY:
                week = "星期一";
                break;

            case DateTimeConstants.TUESDAY:
                week = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                week = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                week = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                week = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                week = "星期六";
            default:
                break;
        }
        return week;
    }

    /**
     * 获取指定时间是一周的星期几
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getWeekPoint(Integer year, Integer month, Integer day) {
        LocalDate dts = new LocalDate(year, month, day);
        String week = null;
        switch (dts.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                week = "星期日";
                break;
            case DateTimeConstants.MONDAY:
                week = "星期一";
                break;
            case DateTimeConstants.TUESDAY:
                week = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                week = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                week = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                week = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                week = "星期六";
                break;

            default:
                break;
        }
        return week;
    }


    /**
     * 获取当前时间前几天时间,按指定格式返回
     *
     * @param days
     * @return
     */
    public static String forwardDay(Integer days, String format) {
        DateTime dt = new DateTime();
        DateTime y = dt.minusDays(days);
        return y.toString(format);
    }

    /**
     * 获取当前时间前几天时间
     *
     * @param days
     * @return
     */
    public static Date forwardDay(Integer days) {
        DateTime dt = new DateTime();
        DateTime y = dt.minusDays(days);
        return y.toDate();
    }

    /**
     * 获取指定时间之后或者之前的某一天00:00:00 默认返回当天
     *
     * @param days
     * @return
     */
    public static Date day00(Integer days, String date, String zimeZone) throws Throwable {
        DateTime dt;
        TimeZone timeZone;
        try {
            if (StringUtils.isBlank(zimeZone)) {
                timeZone = TimeZone.getDefault();
            } else {
                timeZone = TimeZone.getTimeZone(zimeZone);
            }
            if (StringUtils.isBlank(date)) {
                dt = new DateTime().withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            } else {
                dt = new DateTime(date).withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            }
        } catch (Exception e) {
            throw new Throwable(e);
        }

        DateTime y = dt.minusDays(days).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        return y.toDate();
    }

    /**
     * 获取指定时间之后或者之前的某一天23:59:59 默认返回当天
     *
     * @param days 偏移量
     */
    public static Date day59(Integer days, String date, String zimeZone) throws Throwable {
        DateTime dt;
        TimeZone timeZone;
        try {
            if (StringUtils.isBlank(zimeZone)) {
                timeZone = TimeZone.getDefault();
            } else {
                timeZone = TimeZone.getTimeZone(zimeZone);
            }
            if (StringUtils.isBlank(date)) {

                dt = new DateTime().withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            } else {
                dt = new DateTime(date).withZone(DateTimeZone.forTimeZone(timeZone)).toLocalDateTime().toDateTime();
            }
        } catch (Exception e) {
            throw new Throwable(e);
        }
        DateTime y = dt.minusDays(days).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return y.toDate();
    }

    /**
     * 计算两个时间相差多少天
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     */
    @Nullable
    public static Integer diffDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        DateTime dt1 = new DateTime(startDate);
        DateTime dt2 = new DateTime(endDate);
        int day = Days.daysBetween(dt1, dt2).getDays();
        return Math.abs(day);
    }

    /**
     * 获取某月之前,之后某一个月最后一天,24:59:59
     */
    public static Date lastDay(Date date, Integer month) {
        DateTime dt1;
        if (month == null) {
            month = 0;
        }
        if (date == null) {
            dt1 = new DateTime().minusMonths(month);
        } else {
            dt1 = new DateTime(date).minusMonths(month);
        }
        DateTime lastDay = dt1.dayOfMonth().withMaximumValue().
                withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return lastDay.toDate();
    }

    /**
     * 获取某月月之前,之后某一个月第一天,00:00:00
     */
    public static Date firstDay(Date date, Integer month) {
        DateTime dt1;
        if (month == null) {
            month = 0;
        }
        if (date == null) {
            dt1 = new DateTime().minusMonths(month);
        } else {
            dt1 = new DateTime(date).minusMonths(month);
        }
        DateTime lastDay = dt1.dayOfMonth().withMinimumValue().
                withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        return lastDay.toDate();
    }

    public static Date addDay(Date date, int offset) {
        DateTime dt1;
        if (date == null) {
            dt1 = new DateTime().plusDays(offset);
            return dt1.toDate();
        }
        dt1 = new DateTime(date).plusDays(offset);
        return dt1.toDate();

    }

    /**
     * 传入日期时间与当前系统日期时间的比较,
     * 若日期相同，则根据时分秒来返回 ,
     * 否则返回具体日期
     *
     * @return 日期或者 xx小时前||xx分钟前||xx秒前
     */
    @Nullable
    public static String getNewUpdateDateString(Date now, Date createDate) {
        if (now == null || createDate == null) {
            return null;
        }
        Long time = (now.getTime() - createDate.getTime());
        if (time > (24 * 60 * 60 * 1000)) {
            return time / (24 * 60 * 60 * 1000) + "天前";
        } else if (time > (60 * 60 * 1000)) {
            return time / (60 * 60 * 1000) + "小时前";
        } else if (time > (60 * 1000)) {
            return time / (60 * 1000) + "分钟前";
        } else if (time >= 1000) {
            return time / 1000 + "秒前";
        }
        return "刚刚";
    }

    /**
     * 判断时间是否在区间内
     *
     * @param begin   开始时间
     * @param end     结束时间
     * @param dateStr yyyy-MM-dd
     * @return
     */
    public static Boolean isContains(DateTime begin, DateTime end, String dateStr) {
        Interval i = new Interval(begin, end);
        return i.contains(new DateTime(dateStr));
    }



    public static void main(String[] args) {

//        Map<String, Date> map = getDateForWeekDay();
//        for (String key : map.keySet()) {
//            System.out.println(key + ":" + map.get(key));
//        }

      /*  Boolean contains = isContains(new DateTime("2019-05-01"), new DateTime("2019-10-01"), new DateTime("2019-08-01"));
        System.out.println(contains);*/

    }


}
