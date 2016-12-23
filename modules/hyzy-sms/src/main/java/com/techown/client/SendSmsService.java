package com.techown.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.4
 * 2013-05-08T19:17:43.320+08:00
 * Generated source version: 2.7.4
 * 
 */
@WebService(targetNamespace = "http://service.techown.com/", name = "SendSmsService")
@XmlSeeAlso({ObjectFactory.class})
public interface SendSmsService {

    @WebResult(name = "batchSend", targetNamespace = "")
    @RequestWrapper(localName = "batchSend", targetNamespace = "http://service.techown.com/", className = "com.techown.client.BatchSend")
    @WebMethod
    @ResponseWrapper(localName = "batchSendResponse", targetNamespace = "http://service.techown.com/", className = "com.techown.client.BatchSendResponse")
    public java.lang.String batchSend(
        @WebParam(name = "userName", targetNamespace = "")
        java.lang.String userName,
        @WebParam(name = "passWord", targetNamespace = "")
        java.lang.String passWord,
        @WebParam(name = "smsContent", targetNamespace = "")
        java.lang.String smsContent,
        @WebParam(name = "fileName", targetNamespace = "")
        java.lang.String fileName,
        @WebParam(name = "sendTime", targetNamespace = "")
        java.lang.String sendTime,
        @WebParam(name = "smsPriority", targetNamespace = "")
        java.lang.String smsPriority,
        @WebParam(name = "sendAppId", targetNamespace = "")
        java.lang.String sendAppId
    );

    @WebResult(name = "variableSend", targetNamespace = "")
    @RequestWrapper(localName = "variableSend", targetNamespace = "http://service.techown.com/", className = "com.techown.client.VariableSend")
    @WebMethod
    @ResponseWrapper(localName = "variableSendResponse", targetNamespace = "http://service.techown.com/", className = "com.techown.client.VariableSendResponse")
    public java.lang.String variableSend(
        @WebParam(name = "userName", targetNamespace = "")
        java.lang.String userName,
        @WebParam(name = "passWord", targetNamespace = "")
        java.lang.String passWord,
        @WebParam(name = "variableTemplateContent", targetNamespace = "")
        java.lang.String variableTemplateContent,
        @WebParam(name = "fileName", targetNamespace = "")
        java.lang.String fileName,
        @WebParam(name = "sendTime", targetNamespace = "")
        java.lang.String sendTime,
        @WebParam(name = "smsPriority", targetNamespace = "")
        java.lang.String smsPriority,
        @WebParam(name = "sendAppId", targetNamespace = "")
        java.lang.String sendAppId
    );

    @WebResult(name = "querySendAppLog", targetNamespace = "")
    @RequestWrapper(localName = "querySendAppLog", targetNamespace = "http://service.techown.com/", className = "com.techown.client.QuerySendAppLog")
    @WebMethod
    @ResponseWrapper(localName = "querySendAppLogResponse", targetNamespace = "http://service.techown.com/", className = "com.techown.client.QuerySendAppLogResponse")
    public java.lang.String querySendAppLog(
        @WebParam(name = "userName", targetNamespace = "")
        java.lang.String userName,
        @WebParam(name = "passWord", targetNamespace = "")
        java.lang.String passWord,
        @WebParam(name = "sendTime", targetNamespace = "")
        java.lang.String sendTime
    );

    @WebResult(name = "massSend", targetNamespace = "")
    @RequestWrapper(localName = "massSend", targetNamespace = "http://service.techown.com/", className = "com.techown.client.MassSend")
    @WebMethod
    @ResponseWrapper(localName = "massSendResponse", targetNamespace = "http://service.techown.com/", className = "com.techown.client.MassSendResponse")
    public java.lang.String massSend(
        @WebParam(name = "userName", targetNamespace = "")
        java.lang.String userName,
        @WebParam(name = "passWord", targetNamespace = "")
        java.lang.String passWord,
        @WebParam(name = "smsContent", targetNamespace = "")
        java.lang.String smsContent,
        @WebParam(name = "cellPhone", targetNamespace = "")
        java.lang.String cellPhone,
        @WebParam(name = "sendTime", targetNamespace = "")
        java.lang.String sendTime,
        @WebParam(name = "smsPriority", targetNamespace = "")
        java.lang.String smsPriority,
        @WebParam(name = "sendAppId", targetNamespace = "")
        java.lang.String sendAppId
    );
}