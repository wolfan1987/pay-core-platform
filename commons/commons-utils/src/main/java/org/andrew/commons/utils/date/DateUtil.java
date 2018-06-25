package org.andrew.commons.utils.date;


import org.andrew.commons.utils.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

public class DateUtil {

    private static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat TIME_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat TIME_SDF2 = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");
    public static final  SimpleDateFormat DATE8          = new SimpleDateFormat("yyyyMMdd");
    public static final  SimpleDateFormat TIME14         = new SimpleDateFormat("yyyyMMddHHmmss");

    public static final SimpleDateFormat TIME6 = new SimpleDateFormat("HHmmss");

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 比较两个日期相差的月数，取绝对值。
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 相差的月数
     * @throws ParseException ParseException
     */
    public static final int getMonthSpace(String date1, String date2) throws Exception {
        Assert.notNull(date1, "date1 can't not be null.");
        Assert.notNull(date2, "date2 can't not be null.");
        return DateUtil.getMonthSpace(DATE_SDF.parse(date1), DATE_SDF.parse(date2));
    }

    /**
     * 比较两个日期相差的月数，取绝对值。
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 相差的月数
     * @throws ParseException ParseException
     */
    public static final int getMonthSpace(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return Math.abs(result);
    }

    /**
     * 求两个日期相差的天数。
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 两天之差
     * @throws ParseException ParseException
     */
    public static final double daysBetween(String date1, String date2) throws Exception {
        Assert.notNull(date1, "date1 can't not be null.");
        Assert.notNull(date2, "date2 can't not be null.");
        return daysBetween(DATE_SDF.parse(date1), DATE_SDF.parse(date2));
    }

    /**
     * 求两个日期之差。
     *
     * @param early 日期1
     * @param late  日期2
     * @return 日期相差天数
     */
    public static final double daysBetween(Date early, Date late) {
        Assert.notNull(early, "date1 can't not be null.");
        Assert.notNull(late, "date2 can't not be null.");
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calStart.setTime(early);
        calEnd.setTime(late);
        //设置时间为0时
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        return ((calEnd.getTime().getTime() / 1000.0) - (calStart.getTime().getTime() / 1000.0)) /
               3600 / 24;
    }

    /**
     * 获取两个日期相差的分钟数。
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 分钟数
     */
    public static final double getMinuteSpace(String date1, String date2) throws Exception {
        Assert.notNull(date1, "date1 can't not be null.");
        Assert.notNull(date2, "date2 can't not be null.");
        return DateUtil.getMinuteSpace(TIME_SDF.parse(date1), TIME_SDF.parse(date2));
    }

    /**
     * 获取两个日期相差的分钟数。
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 分钟数
     */
    public static final double getMinuteSpace(Date date1, Date date2) {
        Assert.notNull(date1, "date1 can't not be null.");
        Assert.notNull(date2, "date2 can't not be null.");
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calStart.setTime(date1);
        calEnd.setTime(date2);
        return ((int) (calEnd.getTime().getTime() / 1000) -
                (int) (calStart.getTime().getTime() / 1000)) / 60;
    }

    /**
     * 获取两个日期间相差小时数。
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 返回两个日期间相差小时数
     * @throws Exception Exception
     */
    public static final double getHourSpace(String date1, String date2) throws Exception {
        Assert.notNull(date1, "date1 can't not be null.");
        Assert.notNull(date2, "date2 can't not be null.");
        return DateUtil.getHourSpace(TIME_SDF.parse(date1), TIME_SDF.parse(date2));
    }

    /**
     * 获取两个日期间相差小时数。
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 返回两个日期间相差小时数
     * @throws Exception Exception
     */
    public static final double getHourSpace(Date date1, Date date2) {
        Assert.notNull(date1, "date1 can't not be null.");
        Assert.notNull(date2, "date2 can't not be null.");
        double minute = DateUtil.getMinuteSpace(date1, date2);
        double hour = new BigDecimal(minute).divide(new BigDecimal(60d), 2,
                                                    ROUND_HALF_DOWN).doubleValue();
        BigDecimal bigDecimal = new BigDecimal(hour);
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        hour = Double.parseDouble(decimalFormat.format(bigDecimal));
        return hour;
    }


    /**
     * 时间字符串转换成long。
     *
     * @param timeStr 时间字符串
     * @return long 返回
     */
    public static long timeStrToLong(String timeStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long result = 0L;
        if (StringUtil.isNotBlank(timeStr)) {
            try {
                Date date = dateFormat.parse(timeStr);
                result = date.getTime() / 1000L;
            } catch (Exception exception) {
                logger.info("时间转换出错");
            }
        }
        return result;
    }

    /**
     * 时间字符串转日期。
     *
     * @param timeStr 时间字符串
     * @return long 返回
     */
    public static Date timeStrToDate(String timeStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        if (StringUtil.isNotBlank(timeStr)) {
            try {
                result = dateFormat.parse(timeStr);
            } catch (Exception exception) {
                logger.info("时间转换出错");
            }
        }
        return result;
    }

    /**
     * 时间转字符串。
     *
     * @param date 时间
     * @return str 返回字符串
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result = "";
        if (date != null) {
            try {
                result = dateFormat.format(date);
            } catch (Exception exception) {
                logger.info("时间转字符串过程出错");
            }
        }
        return result;
    }

    /**
     * 时间字符串根据日期格式转日期。
     *
     * @param timeStr 时间字符串
     * @param pattern 时间格式
     * @return long 返回
     */
    public static Date strToDateByPattern(String timeStr, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date result = null;
        if (StringUtil.isNotBlank(timeStr)) {
            try {
                result = dateFormat.parse(timeStr);
            } catch (Exception exception) {
                logger.info("时间转换出错");
            }
        }
        return result;
    }

    /**
     * 将14位日期时间字符串转为yyyy-MM-dd hh:mm:ss。
     *
     * @param dateStr date字符串
     * @return 日期对象
     */
    public static Date getDateFromString(String dateStr) {
        Date result = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (StringUtil.isNotBlank(dateStr)) {
            try {
                dateStr = dateStr.trim();
                String ydmhms = dateStr.substring(0, 4).concat("-").concat(
                    dateStr.substring(4, 6)).concat("-").concat(dateStr.substring(6, 8)).concat(
                    " ").concat(dateStr.substring(8, 10)).concat(":").concat(
                    dateStr.substring(10, 12)).concat(":").concat(dateStr.substring(12, 14));
                result = format.parse(ydmhms);
            } catch (Exception exception) {
                logger.info("时间转换出错");
            }
        }
        return result;
    }

    /**
     * 时间转换。
     *
     * @param millisSecond 时间字符串
     * @return 日期对象
     */
    public static Date millisTimeToDateTime(String millisSecond) {
        Date result = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS a");
        if (StringUtil.isNotBlank(millisSecond)) {
            try {
                result = format.parse(millisSecond);
            } catch (Exception exception) {
                logger.info("时间转换出错{}", exception);
            }
        }
        return result;
    }

    /**
     * 时间根据日期格式转字符串。
     *
     * @param date    时间
     * @param pattern 时间格式
     * @return str 返回字符串
     */
    public static String dateToStrByPattern(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String result = "";
        if (date != null) {
            try {
                result = dateFormat.format(date);
            } catch (Exception exception) {
                logger.info("时间转字符串过程出错");
            }
        }
        return result;
    }


    /**
     * 时间戳转换为date。
     *
     * @param dateTime 时间戳 单位毫秒
     * @throws ParseException 　异常
     */
    public static Date timestamp2Date(Long dateTime) throws ParseException {
        String dateStr = dateTimeFormat.format(dateTime);
        Date date = dateTimeFormat.parse(dateStr);
        return date;
    }

    /**
     * 根据输入时间转化为8位日期字符。
     *
     * @return 格式：yyyyMMdd
     */
    public static String timeToString8(Date date) {
        return DATE8.format(date);
    }

    /**
     * 根据输入时间转化为14位日期字符。
     *
     * @return 格式：yyyyMMddmmhhss
     */
    public static String timeToString14(Date date) {
        return TIME14.format(date);
    }

    /**
     * 获取当前系统时间。
     *
     * @return 格式：HHmmss
     */
    public static String currentTimeToString() {
        return TIME14.format(new Date());
    }

    public static String getHhmmss(Date date) {
        return TIME6.format(date);
    }


    /**
     * 测试方法。
     *
     * @param args 系统参数
     */
    public static void main(String[] args) {
        String str = "20180130121211";

        Date date = DateUtil.getDateFromString(str);

        System.out.println(date);
    }
}
