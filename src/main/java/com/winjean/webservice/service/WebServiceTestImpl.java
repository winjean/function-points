package com.winjean.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class WebServiceTestImpl implements IWebServiceTest {

	@Override
	@WebMethod
	public String sayHello(String name) {
		System.out.println("WebService sayHello " + name);
		return "sayHello " + name;
	}

	@Override
	@WebMethod
	public String save(String name, String pwd) {
		System.out.println("WebService save " + name + "， " + pwd);
		return "save Success";
	}
}
