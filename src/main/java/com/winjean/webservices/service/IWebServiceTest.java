package com.winjean.webservices.service;

//使用@WebService注解标注WebServiceI接口
//@WebService
public interface IWebServiceTest {
	//@WebMethod
	String sayHello(String name);

	//@WebMethod
	String save(String name, String pwd);
}
