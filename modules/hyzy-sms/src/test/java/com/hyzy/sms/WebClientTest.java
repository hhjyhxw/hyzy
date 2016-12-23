package com.hyzy.sms;

import java.net.URL;

import javax.xml.namespace.QName;

import com.techown.client.SendSmsService;
import com.techown.client.SendSmsServiceService;
import com.techown.client.service.SendSmsUtil;

public class WebClientTest {

	private static final QName SERVICE_NAME = new QName("http://service.techown.com/", "SendSmsServiceService");
	
	//群发测试
	public void massSendTest()
	{
	    URL wsdlURL = SendSmsServiceService.WSDL_LOCATION;
	    SendSmsServiceService ss = new SendSmsServiceService(wsdlURL, SERVICE_NAME);
	    SendSmsService port = ss.getSendSmsServicePort();  
	    
	    String result = port.massSend("roni", 
	    			"E6AE58017B08FC05115BA783A99483C89336B46A", 
	    			"短信平台已接入，项目中有短信接口调用需求的请找产品部 - 【动态电商】", 
	    			"13410325735", 
	    			"", 
					"", 
	    			"7");
	    System.out.println(result);
	}
	
	//固定模版发送
	public void batchSendTest()
	{
		URL wsdlURL = SendSmsServiceService.WSDL_LOCATION;
	    SendSmsServiceService ss = new SendSmsServiceService(wsdlURL, SERVICE_NAME);
	    SendSmsService port = ss.getSendSmsServicePort();  
	    
	    String result = port.batchSend("liuyongping", 
				"6B85707451A7ADAA444850E2E55170C0ED226D88", 
				"固定模版", 
	    		"100000_1 (10).txt", 
				"", 
				"", 
	    		"");
	    System.out.println(result);
	}
	
	//变量发送
	public void variableSendTest()
	{
		URL wsdlURL = SendSmsServiceService.WSDL_LOCATION;
	    SendSmsServiceService ss = new SendSmsServiceService(wsdlURL, SERVICE_NAME);
	    SendSmsService port = ss.getSendSmsServicePort();  
	    String result = port.variableSend("liuyongping", 
	    		"6B85707451A7ADAA444850E2E55170C0ED226D88", 
	    		"元旦祝福变量", 
	    		"1variablled.txt", 
	    		"", 
	    		"", 
	    		"");
    	System.out.println(result);
	}

	public static void main(String[] args) {
//		WebClientTest wt = new WebClientTest();
//		wt.massSendTest();
		SendSmsUtil.sendMessage("13410325735", "测试短信", "1");
	}
}
