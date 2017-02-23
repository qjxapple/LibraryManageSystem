package com.library.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.library.util.DaysBetweenUtil;
import com.library.util.InputJudgeUtil;

public class TempTest {

	/**
	 * SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String str="20110823";
        Date dt=sdf.parse(str);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR,-1);//日期减1年
        rightNow.add(Calendar.MONTH,3);//日期加3个月
        rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
        Date dt1=rightNow.getTime();
        String reStr = sdf.format(dt1);
        System.out.println(reStr);
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
//		String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		String temp = "2017-02-01";
//		System.out.println(DaysBetweenUtil.daysBetween(temp, now));
//		
		String str = "123aaa";
		System.out.println(InputJudgeUtil.isNumOrSpecialChars(str));
		
	}

}
