package com.mtautomation.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.google.common.base.Splitter;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
/*
*********************************************************************************
*			Author: Mohammad Parvez												*
*																				*
*********************************************************************************
*/
//This is code is to verify the Node status and verify the version deployed

public class PostProduction
{
	@Test
	public void checkNodes() throws IOException, ParseException 
	{
		//WebDriver driver = new FirefoxDriver();
		String str1= "abc.xyz.com";
		String str="10.xx.yy.zz";

		List<String> list = Arrays.asList(str.split(","));
		int count = list.size();
		System.out.println("Count: " + count);

		// Loop through elements.
		for (int i = 0; i < list.size(); i++) {
			
				String value = list.get(i);
				// System.out.println("Element: " + value);
				String url1="http://"+value;
				String url2= url1+"/aa/bb/cc";
				System.out.println("****************************************");
				System.out.println("URL "+url2);
				//driver.get(url);
				//System.out.println(httpResponse(url));
				
				System.out.println(httpResponse(url2));
		String version = responseBody(url2);
		System.out.println(version);
		//System.out.println("****************************************");


		}
	}

	}


private  String httpResponse(String value) throws ClientProtocolException, IOException
	{
		// Getting the status code.
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(value);
		HttpResponse resp = client.execute(httpGet);

		int statusCode = resp.getStatusLine().getStatusCode();
		String statusbody = resp.getStatusLine().getReasonPhrase();
		System.out.print("Status code is:"+statusCode+": ");
		//System.out.println(statusbody);
		return statusbody;

	}

public String responseBody(String urlvalue) throws IOException, ParseException
	{
		URL url = new URL(urlvalue);
		URLConnection con = url.openConnection();
		InputStream in = con.getInputStream();
		String myString = IOUtils.toString(in, "UTF-8");
		//	System.out.println(myString);
		int location = myString.indexOf("Build version");
		int location2 = myString.lastIndexOf("Build");
		//System.out.println(myString.substring(location, location2+8));
		String myVersion= myString.substring(location, location2);
		return myVersion;

		
	}

	}
