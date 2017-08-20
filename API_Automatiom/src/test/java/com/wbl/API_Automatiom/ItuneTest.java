package com.wbl.API_Automatiom;

import org.testng.annotations.BeforeClass;

import base.BaseAPITest;

public class ItuneTest extends BaseAPITest {
	
	BaseApi api;
	
	@BeforeClass
	
	public void beforeClass(){
		api = new BaseApi(endpoint);	
	}

}
