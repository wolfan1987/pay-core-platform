package org.andrew.commons.utils.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日历工具类。
 *
 * @author andrewliu
 */
public class CalendarUtils {

    public static final Logger logger = LoggerFactory.getLogger(CalendarUtils.class);

    private static final String yyyymmddStr = "yyyy-MM-dd";
    private static final String yearStr     = "yyyy";
    private static final String monthStr    = "MM";
    private static final String dayStr      = "dd";

    /**
     * 根据传入的年、月、日返回下一月的年月日日期。
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 返回下一月的日期
     */
    public static Date getNextMonthDate(Integer year, Integer month, Integer day) {
        if (day == 0) {
            day = getDateDay(new Date());
        }
        Calendar calendar = Calendar.getInstance();
        //如果下一月的日存在，则直接返回下一月的年月日
        if (isNextMonthDayExist(year, month, day)) {
            calendar.set(year, month, day);
        } else {
            //获取下一月的第一天
            calendar.set(year, month + 1, 1);
            //获取月份的最后一天
            calendar.add(Calendar.DATE, -1);
        }
        Date date = calendar.getTime();
        String dateStr = new SimpleDateFormat(yyyymmddStr).format(date);
        logger.info("返回的日期" + dateStr);
        return date;
    }

    /**
     * 根据传入的年月日返回间隔月份的年月日日期。
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 返回间隔月份后的日期
     */
    public static Date getNextRegularDate(
        Integer year, Integer month, Integer day, Integer fixMonth) {
        if (day == 0) {
            day = getDateDay(new Date());
        }
        if (fixMonth == null) {
            fixMonth = 1;
        }
        Calendar calendar = Calendar.getInstance();
        //如果间隔月的日存在，则直接返回间隔月的年月日
        if (isNextMonthDayExist(year, month + fixMonth - 1, day)) {
            calendar.set(year, month + fixMonth - 1, day);
        } else {
            //获取间隔月下一月的第一天
            calendar.set(year, month + fixMonth, 1);
            //获取间隔月份的最后一天
            calendar.add(calendar.DATE, -1);
        }
        Date date = calendar.getTime();
        String dateStr = new SimpleDateFormat(yyyymmddStr).format(date);
        logger.info("返回的日期" + dateStr);
        return date;
    }

    /**
     * 根据年月日判断日是否在月份上存在。
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return boolean 是否存在
     */
    public static boolean isNextMonthDayExist(Integer year, Integer month, Integer day) {
        Calendar calendar = Calendar.getInstance();
        //获取下一月的第一天
        calendar.set(year, month + 1, 1);
        //获取月份的最后一天
        calendar.add(calendar.DATE, -1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        logger.info("当前月的最大日期：" + maxDay);
        if (maxDay < day) {
            return false;
        }
        return true;
    }

    /**
     * 根据日期返回年。
     *
     * @param date 日期
     * @return 返回年份
     */
    public static int getDateYear(Date date) {
        Assert.notNull(date, "传入日期不能为空");
        String year = new SimpleDateFormat(yearStr).format(date);
        return Integer.parseInt(year);
    }

    /**
     * 根据日期返回月。
     *
     * @param date 日期
     * @return 返回月份
     */
    public static int getDateMonth(Date date) {
        Assert.notNull(date, "传入日期不能为空");
        String month = new SimpleDateFormat(monthStr).format(date);
        return Integer.parseInt(month);
    }

    /**
     * 根据日期返回日。
     *
     * @param date 日期
     * @return 返回日
     */
    public static int getDateDay(Date date) {
        Assert.notNull(date, "传入日期不能为空");
        String day = new SimpleDateFormat(dayStr).format(date);
        return Integer.parseInt(day);
    }

}
