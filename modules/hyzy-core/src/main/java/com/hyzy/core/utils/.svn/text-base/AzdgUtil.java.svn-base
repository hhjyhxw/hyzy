package com.hyzy.core.utils;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class AzdgUtil {
	 /**
	  * @author fwx215666
	  * @param txt 加密内容  key 用来加密的KEY
     * 加密算法
     */
    public static String encrypt(String txt, String key) {
       String encrypt_key = "0f9cfb7a9acced8a4167ea8006ccd098";
       int ctr = 0;
       String tmp = "";
       int i;
       for (i = 0; i < txt.length(); i++) {
           ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
           tmp = tmp + encrypt_key.charAt(ctr)
                  + (char)(txt.charAt(i) ^ encrypt_key.charAt(ctr));
           ctr++;
       }
       return base64_encode(key(tmp, key));
    }
   
    /**
     * @author fwx215666
     * @param  cipherText 加密内容  key 加密秘钥
     * 解密算法
     */
    public static String decrypt(String cipherText, String key) {
       // base64解码
       cipherText = base64_decode(cipherText);
       cipherText = key(cipherText, key);
       String tmp = "";
       for (int i = 0; i < cipherText.length(); i++) {
           int c = cipherText.charAt(i) ^ cipherText.charAt(i + 1);
           String x = "" + (char) c;
 
           tmp += x;
           i++;
       }
       return tmp;
    }
    
    /**
     * @author fwx215666
     * @param txt 加密的内容
     * 加密的内容解密后获得一个Map　 
     * 规则key=value&key=value
     */
    public static Map<String,String> getCipherTextMap(String cipherText){
    	Map<String,String> cipherTextMap = new HashMap<String, String>();
    	String str[] = cipherText.split("&"); 
    	String val[];
    	for(int i =0;i<str.length;i++){
    		val = str[i].split("=");
        	for(int k =0;k<val.length;k++){
        		cipherTextMap.put(val[k++],val[k++]);
        	}
    	}
    	return cipherTextMap;
    }
 
    public static String key(String txt, String encrypt_key) {
       encrypt_key = strMD5(encrypt_key);
       int ctr = 0;
       String tmp = "";
       for (int i = 0; i < txt.length(); i++) {
           ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
           int c = txt.charAt(i) ^ encrypt_key.charAt(ctr);
           String x = "" + (char) c;
           tmp = tmp + x;
           ctr++;
       }
       return tmp;
    }
 
    @SuppressWarnings("restriction")
	public static String base64_encode(String str) {
       return new sun.misc.BASE64Encoder().encode(str.getBytes());
    }
   
    @SuppressWarnings("restriction")
	public static String base64_decode(String str) {
       sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
       if (str == null)
           return null;
       try {
           return new String(decoder.decodeBuffer(str));
       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }
    }
 
    public static final String strMD5(String s) {
       char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
              'a', 'b', 'c', 'd', 'e', 'f' };
       try {
           byte[] strTemp = s.getBytes();
           MessageDigest mdTemp = MessageDigest.getInstance("MD5");
           mdTemp.update(strTemp);
           byte[] md = mdTemp.digest();
           int j = md.length;
           char str[] = new char[j * 2];
           int k = 0;
           for (int i = 0; i < j; i++) {
              byte byte0 = md[i];
              str[k++] = hexDigits[byte0 >>> 4 & 0xf];
              str[k++] = hexDigits[byte0 & 0xf];
           }
           return new String(str);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }
}
