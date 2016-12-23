package com.hyzy.core.utils;

import java.util.Random;
/**
 * @filename      : VerifyCodeGenerator.java
 * @description   : 获取随机码
 * @author        : zhanghaitao
 * @create        : 
 * @copyright     : hyzy Corporation 2014
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 * 
 */
public class VerifyCodeGenerator {
	 private static final VerifyCodeGenerator generator = new VerifyCodeGenerator(); 

	    private final int CODE_LENGTH = 6; 

	    private final String RAND_RANGE = "1234567890" ;

	     
	    private final char[] CHARS = RAND_RANGE.toCharArray(); 
	     
	    private Random random = new Random(); 
	     
	    private VerifyCodeGenerator(){ } 
	     
	    public static VerifyCodeGenerator getInstance(){ 
	        return generator; 
	    } 
	     
	    /**
	     * 获取默认的CODE_LENGTH长度
	     * @autor zhanghaitao
	     * @return
	     */
	    public  String getRandString(){ 
	        StringBuffer sb = new StringBuffer(); 
	        for (int i = 0; i < CODE_LENGTH; i++) 
	            sb.append(CHARS[random.nextInt(CHARS.length)]); 
	        return sb.toString(); 
	    } 
	    
	    /**
	     * 获取指定长度的随机码
	     * @autor zhanghaitao
	     * @param specifiedLength
	     * @return
	     */
	    public  String getRandString(int specifiedLength){ 
	        StringBuffer sb = new StringBuffer(); 
	        for (int i = 0; i < specifiedLength; i++) 
	            sb.append(CHARS[random.nextInt(CHARS.length)]); 
	        return sb.toString(); 
	    } 
}
