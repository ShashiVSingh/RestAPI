package com.wbl.API_Automatiom;

import org.testng.annotations.Test;

public class TestManager {
	private Twitter _twitter;
	//private Facebook _facebook;
	public TestManager()
	{
		_twitter=new Twitter();
		// _facebook=new Facebook();
	}
	
	@Test
	public void executeTwitterTest()
	{
		_twitter.executeTest();
	}
	
	public void executeFacebookTest()
	{
		// _facebook.executeTest();
	}
}
