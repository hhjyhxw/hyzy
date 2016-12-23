/**
 * Copyright: Copyright (c) 2012
 * Company:深圳市云软信息技术有限公司
 * @author chengkunxf
 * @date 2013-5-16 下午4:48:30
 * @version V1.0
 */
package com.techown.client.service;

import java.net.URL;

import javax.xml.namespace.QName;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techown.client.SendSmsService;
import com.techown.client.SendSmsServiceService;

/**
 * @filename      : SmsSendService.java
 * @description   : TODO(一句话描述该类做什么)
 * @author        : chengkunxf
 * @create        : 2013-5-16 下午4:48:30
 * @copyright     : hyzy Corporation 2014
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 * 2013-5-16 下午4:48:30
 */
public class SendSmsUtil {
	private static final QName SERVICE_NAME = new QName("http://service.techown.com/", "SendSmsServiceService");
	
	/**
	 * 短信
	 * @autor chengkunxf
	 * @param moble
	 * @param message
	 * @param smsId
	 */
	public  static String sendMessage(String moble,String message,String smsId){
		URL wsdlURL = SendSmsServiceService.WSDL_LOCATION;
	    SendSmsServiceService ss = new SendSmsServiceService(wsdlURL, SERVICE_NAME);
	    SendSmsService port = ss.getSendSmsServicePort();
	    String result = port.massSend("roni", 
    			"E6AE58017B08FC05115BA783A99483C89336B46A", 
    			message, 
    			moble, 
    			"", 
				"", 
				smsId);
	    return result;
	} 
}
