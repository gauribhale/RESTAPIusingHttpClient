package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RESTClient;

import Base.TestBase;

public class DELETEAPITest extends TestBase {
	TestBase testbase;
	String url;
	String delUrl;
	RESTClient client;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setUp() {
		testbase = new TestBase();
		url = prop.getProperty("URL");
		delUrl = prop.getProperty("delURL");
	}

	@Test(priority = 4)
	public void testDELETEMethod() throws ClientProtocolException, IOException {
		System.out.println("---------DELETE METHOD TEST-------");
		client = new RESTClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		httpResponse = client.delete(url+delUrl, headerMap);

		// 1. status code
		int statusCode=httpResponse.getStatusLine().getStatusCode();
		System.out.println("status code: "+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_CODE_204);
	}

}