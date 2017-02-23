package com.library.util;

public class InputJudgeUtil {
	/**
	 * 输入判断
	 * 
	 * @param str
	 *            接收String类型的数据
	 * @return
	 */
	public static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}
	public static boolean isNumOrSpecialChars(String str) {
		String regex =" ^[^\\/\\:\\*\\?\"<>]+$";
		boolean flag = str.matches(regex);
		String s = "helloworld123";
		System.out.println(s.matches(regex));
		return flag;
	}
}
