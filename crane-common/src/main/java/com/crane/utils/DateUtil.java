package com.crane.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.crane.po.enums.DateTimePatternEnum;

public class DateUtil {

	/** 锁对象 */
	private static final Object lockObj = new Object();

	/** 存放不同的日期模板格式的sdf的Map */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	private static final long ONE_MINUTE = 60000L;
	private static final long ONE_HOUR = 3600000L;
	private static final long ONE_DAY = 86400000L;

	private static final int AGO_DAY_0 = 0, AGO_DAY_1 = 1, AGO_DAY_2 = 2, AGO_DAY_7 = 7;

	private static final String JUST_NOW = "刚刚";
	private static final String ONE_MINUTE_AGO = "分钟前";
	private static final String ONE_HOUR_AGO = "小时前";
	private static final String ONE_DAY_AGO = "天前";
	private static final String TWO_DAY_AGO = "昨天";
	private static final String THTEE_DAY_AGO = "前天";

	private static final String[] WEEKCN = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

	/**
	 * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSdf(final String pattern) {
		ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

		// 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
		if (tl == null) {
			synchronized (lockObj) {
				tl = sdfMap.get(pattern);
				if (tl == null) {
					// 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
					// 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
					tl = new ThreadLocal<SimpleDateFormat>() {

						@Override
						protected SimpleDateFormat initialValue() {
							return new SimpleDateFormat(pattern);
						}
					};
					sdfMap.put(pattern, tl);
				}
			}
		}

		return tl.get();
	}

	/**
	 * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return getSdf(pattern).format(date);
	}

	public static Date parse(String dateStr, String pattern) {
		try {
			return getSdf(pattern).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 
	 * getTotalDayAndFirstDay4Month:(获取某年某月第多少天是周几，这个月总共多少天). <br/>
	 *
	 * @author 多多洛
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @since JDK 1.7
	 */
	public static Map<String, Integer> getTotalDayAndFirstWeekDay4Month(int year, int month, int day) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		// 获取一个月有多少天
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);// Java月份才0开始算 1代表上一个月
		int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
		// 获取当前日期是星期几
		cal.set(Calendar.DATE, day);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		result.put("totalDay", dateOfMonth);
		result.put("firstWeekDay", week);
		return result;
	}

	public static Date getDayYYYYMMDD(Date date) {
		String dateStr = format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern());
		return parse(dateStr, DateTimePatternEnum.YYYY_MM_DD.getPattern());
	}

	/**
	 * 
	 * beforeNowDate:(判断时间字符串是否在当天之前). <br/>
	 *
	 * @author 多多洛
	 * @param date
	 * @return
	 * @since JDK 1.7
	 */
	public static boolean beforeNowDate(String date) {
		try {
			Date d = getDayYYYYMMDD(new Date());
			boolean flag = new SimpleDateFormat(DateTimePatternEnum.YYYY_MM_DD.getPattern()).parse(date).before(d);
			return flag;
		} catch (ParseException e) {
			return false;
		}
	}

	public static int getWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int w = c.get(Calendar.DAY_OF_WEEK) - 1;
		return w;
	}

	public static String getWeekCN(Date date) {
		int w = getWeek(date);
		return WEEKCN[w];
	}

	public static String friendly_time(Date sourceDate) {
		Date curDate = new Date();
		Date sourceDateYMD = DateUtil.getDayYYYYMMDD(sourceDate);
		Date curDateYMD = DateUtil.getDayYYYYMMDD(curDate);
		//几天前
		long daysAgo = (curDateYMD.getTime() - sourceDateYMD.getTime()) / ONE_DAY;
		//当前
		if (daysAgo == AGO_DAY_0) {
			return getCurDayInfo(sourceDate, curDate);
		} else if (daysAgo == AGO_DAY_1) {
			//昨天
			return TWO_DAY_AGO + " " + getHourAndMin(sourceDate);
		} else if (daysAgo == AGO_DAY_2) {
			//前天
			return THTEE_DAY_AGO + " " + getHourAndMin(sourceDate);
		} else if (daysAgo > AGO_DAY_2 && daysAgo <= AGO_DAY_7) {
			//几天前
			return (daysAgo - 1) + ONE_DAY_AGO;
		} else {//超过一个星期，不格式化
			return DateUtil.format(sourceDate, DateTimePatternEnum.YYYY_MM_DD.getPattern());
		}
	}

	private static String getHourAndMin(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return String.format("%02d", c.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", c.get(Calendar.MINUTE));
	}

	private static String getCurDayInfo(Date sourceDate, Date curDate) {
		long secondsAgo = curDate.getTime() - sourceDate.getTime();
		if (secondsAgo / ONE_MINUTE <= 0) {//刚刚
			return JUST_NOW;
		} else if (secondsAgo / ONE_MINUTE > 0 && secondsAgo / ONE_HOUR == 0) {
			return secondsAgo / ONE_MINUTE + ONE_MINUTE_AGO;
		} else {
			return secondsAgo / ONE_HOUR + ONE_HOUR_AGO;
		}
	}

	public static boolean isNew(Date date) {
		Calendar c = Calendar.getInstance();
		long max = c.getTimeInMillis() - date.getTime();
		if (max / 86400000 <= 1) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws ParseException {
		String str = "2015-12-15 16:30:30";
		Date date = parse(str, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
		System.out.println(friendly_time(date));
	}

	public static String getNextMonthDay() {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.MONTH, 1);
		Date date = calender.getTime();
		return DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern());
	}
}