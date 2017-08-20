package com.wbl.API_Automatiom;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.testng.annotations.Test;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

public class TwitterAccount extends ApiBase {
	@Test
	public void postUserSettingsTest()
	{
		String apiEndpoint="https://api.twitter.com/1.1/account/settings.json?lang=fr";
		try
		{
			HttpResponse response=apiPostMethod(apiEndpoint,getOauthConsumer(),null);
			assertEquals(response.getStatusLine().getStatusCode(), 200);
			//assertTrue(response.getStatusMessage.contains("HTTP/1.1 200 OK"));
			JSONObject responseData = getJSONObject(response);
			assertEquals(responseData.getBoolean("protected"),true);
			//System.out.println(json.toString());
		}catch(IOException ioException)
		{
			
		}
		catch(Exception exception)
		{
			
		}
	}
	
	@Test
	public void getUserSettingsTest()
	{
		String apiEndpoint="https://api.twitter.com/1.1/account/settings.json";
		try{
			HttpResponse response=apiGetMethod(apiEndpoint,getOauthConsumer());
			assertEquals(response.getStatusLine().getStatusCode(), 200);
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(IOUtils.toString(response.getEntity().getContent()));
			JSONObject json = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
			System.out.println(json.get("screen_name"));
			assertEquals("apitest140",json.get("screen_name"));
			
		}catch(IOException ioException)
		{
			
		}
		catch(Exception exception)
		{
			
		}
	}
	
	@Test
	public void postProfileUpdateTest(){   //post have a bodytherefore they have payload
		String apiEndpoint = "https://api.twitter.com/1.1/account/update_profile.json";
		try
		{
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("screen_name", "shashi"));
			urlParameters.add(new BasicNameValuePair("location", "San Ramon"));
			
			HttpResponse response = apiPostMethod(apiEndpoint,getOauthConsumer(),urlParameters);
			JSONObject responseData = getJSONObject(response);
			assertEquals("shashi", responseData.get("screen_name"));
			assertEquals("San Ramon", responseData.get("location"));
		}
		catch(IOException ioException){
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private OAuthConsumer getOauthConsumer()
	{
		OAuthConsumer consumer=new CommonsHttpOAuthConsumer("UDp4DcFcO99w45Tl4OaEO9Cqs",	"67OFaUozxwB9CwH9ZJ0Ip4eROUwIG5kaue2pvdP1m4b98HotAl");
		consumer.setTokenWithSecret("897877266104860672-7lIWG357DE7XKe2jdeFAvU2HJxOLW14", "IzHEufW90PnvgD00hlhHrXkdjdPvatJrAvy6TM33P5Wx9");
		return consumer;
	}	

}
