package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RESTClient;
import com.qa.data.Users;

import Base.TestBase;

public class PUTAPITest extends TestBase {
	RESTClient client;
	TestBase testbase;
	String url;
	String apiUrl;
	CloseableHttpResponse httpResponse;
	
	@BeforeMethod
	public void setUp() {
		
		testbase=new TestBase();
		url=prop.getProperty("URL");
		apiUrl=prop.getProperty("putURL");		
	}
	
	@Test(priority = 3)
	public void testPUTMethod() throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("-------PUT METHOD TEST-------");
		client=new RESTClient();
		
		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson api to convert POJO to JSONObject
		ObjectMapper mapper=new ObjectMapper();
		//expected user data
		Users users=new Users("John","Developer");
		mapper.writeValue(new File("D:\\RestAPIAutomation\\APIAutomation\\src\\main\\java\\com\\qa\\data\\Users.json"), users);
		
		//JSON Object to JSON String
		String jsonRequestString=mapper.writeValueAsString(users);
		System.out.println("Request Payload: "+jsonRequestString);
		httpResponse=client.put(url+apiUrl, jsonRequestString, headerMap);
		
		//1. Status code
		int statusCode=httpResponse.getStatusLine().getStatusCode();
		System.out.println("status code: "+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_CODE_200);
		
		//2. Response json string
		String responseString=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		JSONObject responseJSON=new JSONObject(responseString);
		System.out.println("Response: "+responseString);
		
		//actual user data
		Users userObj=mapper.readValue(responseString, Users.class);
		Assert.assertEquals(userObj.getName(), users.getName());
		Assert.assertEquals(userObj.getJob(), users.getJob());
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
