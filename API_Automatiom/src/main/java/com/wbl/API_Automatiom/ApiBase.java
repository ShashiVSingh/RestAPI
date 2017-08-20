package com.wbl.API_Automatiom;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.json.Property;
import org.omg.CORBA.portable.ApplicationException;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

public abstract class ApiBase {
	private Property _property;
	public ApiBase()
	{
		_property=new Property();
	}
	
	
	public HttpResponse apiGetMethod(String uri,String accessToken) throws Exception{
	     // creating HttpClient object Appache ppl have given HttpClient Builder class & Creat , Build is a method.
		HttpClient httpclient = HttpClientBuilder.create().build();
		//creat required httpmethod Object
		if(uri.isEmpty())
			throw new Exception("Uri can not be null or empty");
		URI _uri=new URI(uri);
		//HttpGet get = new HttpGet("https://api.twitter.com/1.1/account/settings.json");
		HttpGet get = new HttpGet(_uri);
		
		/*CommonsHttpOAuthConsumer cosumer=new CommonsHttpOAuthConsumer("OF6ZctKtdJbeZSJQJ1pfd9CQa",	"tAsiEPFHgnM3vOmKVauUl4mX6O6O20cZIkCJEzN75lJwbav7nG");
		cosumer.setTokenWithSecret("145875251-DsrNCv6F1vSnJJM9329C8FuZjDHcyYm6Siz0SJ2k", "01jA0ZtkW39DjhJqONUizcsBMU7ojTpZPErnFxUdYw84H");*/
		
		get.addHeader("Authorization", accessToken);
		HttpResponse response = httpclient.execute(get);
		return response;
	}
	
	public HttpResponse apiGetMethod(String uri,OAuthConsumer oauthConsumer) throws Exception{
		// creating HttpClient object Appache ppl have given HttpClient Builder class & Creat , Build is a method.
		HttpClient httpclient = HttpClientBuilder.create().build();
		//creat required httpmethod Object
				
		//HttpGet get = new HttpGet("https://api.twitter.com/1.1/account/settings.json");
		HttpGet get = new HttpGet(getValidUri(uri));
		oauthConsumer.sign(get);
		HttpResponse response = httpclient.execute(get);
		return response;
					
	}
	
	
	public HttpResponse apiPostMethod(String uri,OAuthConsumer oAuthConsumer,List<NameValuePair> urlParameters) throws Exception
	{
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost post=new HttpPost(getValidUri(uri));
		if( urlParameters!=null &&  urlParameters.size()>0)
		{
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		}
		oAuthConsumer.sign(post);
		return httpclient.execute(post);
	}
	
	private URI getValidUri(String uri) throws Exception
	{
		if(uri.isEmpty())
			throw new Exception("Uri can not be null or empty");
		return new URI(uri);
	}
	
	
	public JSONObject getJSONObject(HttpResponse response)
	{
		if(response==null)
			return null;
		String jsonData = null;
		try {
			jsonData = IOUtils.toString(response.getEntity().getContent());
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonData!=null ? new JSONObject(jsonData):null;
	}
}
