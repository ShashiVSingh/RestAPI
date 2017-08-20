package com.wbl.API_Automatiom;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


import java.io.IOException;
import static org.testng.Assert.*;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.http.HttpParameters;

public class Twitter extends ApiBase //
{
	private TwitterAccount _twitterAccount;
	private TwitterUser _twitterUser;
	public Twitter()
	{
		_twitterAccount=new TwitterAccount();
		_twitterUser=new TwitterUser();
	}
	
	public void executeTest()
	{
		executeAccountResource();
		executeUserResource();
	}
	
	@Test
	private void executeUserResource()
	{
		_twitterUser.createUser();
		_twitterUser.updateUser();
	}
	
	private void executeAccountResource()
	{
		_twitterAccount.postUserSettingsTest();
		_twitterAccount.getUserSettingsTest();
		_twitterAccount.postProfileUpdateTest();
		
		
	}
		
}