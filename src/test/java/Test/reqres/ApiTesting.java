package Test.reqres;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTesting {

	public String BaseUrl = "https://reqres.in/";
	public String Reg_Url = "api/resgister";
	public String Users_Url = "api/users";
	public String SpecificUserUrl = "api/users/23"; 
	public String RegUrl = "api/resgister"; 
	
	@Test
	void listUsers() {
		int expectedStatusCode = 200;
		RestAssured.baseURI = BaseUrl;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,Users_Url);
		String users = response.getBody().asString();
		System.out.println("List of users:\n" + users);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, expectedStatusCode);
	}
	
	@Test
	void SingleUserNotFound() {
		int expectedStatusCode = 404;
		RestAssured.baseURI = BaseUrl;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,SpecificUserUrl);
		String users = response.getBody().asString();
		Assert.assertEquals(users,"{}");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, expectedStatusCode);
		
	}
	
	@Test
	void SuccessfulReg() {
		int expectedStatusCode = 200;
		String email ="eslamaboutaleb@yahoo.com";
		String password = "password";
		RestAssured.baseURI = BaseUrl;
		RequestSpecification httpRequest = RestAssured.given();
		JsonObject requestParameters = new JsonObject();
		requestParameters.addProperty("email",email );
		requestParameters.addProperty("password", password);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParameters);
		
		Response response = httpRequest.request(Method.POST,RegUrl);
		System.out.println("response :"+response.asString());

		int statusCode = response.getStatusCode();
		System.out.println("status code :"+statusCode);
		Assert.assertEquals(statusCode, expectedStatusCode);
	}
	
	@Test
	void UnSuccessfulReg() {
		int expectedStatusCode = 400;
		String email ="eslamaboutaleb@yahoo.com";
		
		RestAssured.baseURI = BaseUrl;
		RequestSpecification httpRequest = RestAssured.given();
		JsonObject requestParameters = new JsonObject();
		requestParameters.addProperty("email",email );
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParameters);
		
		Response response = httpRequest.request(Method.POST,RegUrl);
		System.out.println("response :"+response.asString());

		int statusCode = response.getStatusCode();
		System.out.println("status code :"+statusCode);
		Assert.assertEquals(statusCode, expectedStatusCode);
	}
	
	
}
