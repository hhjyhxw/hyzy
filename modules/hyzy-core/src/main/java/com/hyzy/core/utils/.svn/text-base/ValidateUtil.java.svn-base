package com.hyzy.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用的校验
 * @author zhanghaitao
 *
 */
public class ValidateUtil {

	/**
	 * 是否满足email格式
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str){
		String check = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";  
		 Pattern regex = Pattern.compile(check);  
		 Matcher matcher = regex.matcher(str);  
		 return matcher.matches();  
	}
}
