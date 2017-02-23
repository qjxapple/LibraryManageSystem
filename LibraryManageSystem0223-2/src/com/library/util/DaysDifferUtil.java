package com.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DaysDifferUtil {
	/**
	 * 用来计算多少天后过期的方法
	 * 
	 * @param time
	 *            接收预约时间或者借阅时间
	 * @param num
	 *            接收多少天为过期时间
	 * @return 返回到期时间的string类型
	 * @throws ParseException
	 */
	public static String daysCount(String time, int num) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date dt = sdf.parse(time);
		Calendar rightnow = Calendar.getInstance();
		rightnow.setTime(dt);
		rightnow.add(Calendar.DAY_OF_YEAR, num);
		Date dt1 = rightnow.getTime();
		String renow = sdf.format(dt1);
		return renow;
	}
}
