package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
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

public class POSTAPITest extends TestBase {

	TestBase testbase;
	RESTClient client;
	String url;
	String apiUrl;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testbase = new TestBase();
		url = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");

	}

	@Test(priority = 2)
	public void testPOSTMethod() throws JsonGenerationException, JsonMappingException, IOException {
	
		System.out.println("-------POST METHOD TEST---------");
		
		client = new RESTClient();
		// headers
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username", "test");
		// headerMap.put("password", "test123");

		// JackSON API to convert POJO TO JSON OBJECT
		ObjectMapper mapper = new ObjectMapper();
		// expected users object
		Users users = new Users("Thomas", "Leader");
		// Object to JSON Conversion
		mapper.writeValue(new File("D:\\RestAPIAutomation\\APIAutomation\\src\\main\\java\\com\\qa\\data\\Users.json"),
				users);

		// JSON OBJET TO JSON STRING
		String usersJSONString = mapper.writeValueAsString(users);
		System.out.println(usersJSONString);
		httpResponse = client.post(url + apiUrl, usersJSONString, headerMap);

		// 1. status code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code: " + statusCode);
		Assert.assertEquals(RESPONSE_CODE_201, statusCode);

		// 2. json string
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("response: " + responseJSON);

		// actual users object
		Users userObj = mapper.readValue(responseString, Users.class);
		// System.out.println("User response: "+userObj);
		Assert.assertEquals(userObj.getName(), users.getName());
		Assert.assertEquals(userObj.getJob(), users.getJob());
	}
}
