package RestAssured.Phase3;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredTest2 {
	
	@Before
	public void beforetest() {
		
		//create base path
        RestAssured.baseURI="https://reqres.in";

	}
	
	@Test
	public void getRequest() {
		
        RequestSpecification request= RestAssured.given();
        
        request.get("/api/users?page=2").then().log().headers();
        
	}
	
	@Test
	public void getRequest1() {
		
        RequestSpecification request= RestAssured.given();
        
        request.get("/api/users?page=2").then().log().body();
        //get response values using String variable
        //String respBody = request.get("/api/users?page=2").getBody().asString();
        //System.out.println("Request Body = " + respBody);
	}
	
	@Test
	public void PostRequest() {
		
		RequestSpecification request= RestAssured.given();
		String requestBody = " {\"name\": \"Sridhar\", \"job\": \"Leader\"} ";
		
		//setup and executing post request
		
		request.header("Content-Type", "Application/json")
					  .header("header2", "value2")
					  .header("header3", "value3")
					  .body(requestBody)
					  .log().all()
					  .post("/api/users").then().log().all();		
	}
	
	@Test
	public void PostRequestwithHeaders2() {
		
		RequestSpecification request= RestAssured.given();
		String headers = "header1=value1;header2=value2;header3=value3";
		String requestBody = " {\"name\": \"Sridhar\", \"job\": \"Leader\"} ";
		
		String[] headerArray = headers.split(";");
		
		for(String header:headerArray) {
			
			String headerKeyValue[] = header.split("=");
			request.header(headerKeyValue[0], headerKeyValue[1]);
		}
		request.body(requestBody)
		       .log().all()
		       .post("/api/users").then().log().all();	
	}
	
	@Test
	public void forAuthURI() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		RequestSpecification request= RestAssured.given();
		
		String username = "postman";
		String password = "password";
		
		request.auth().preemptive().basic(username, password)
		       .when().get("basic-auth")
		   //    .then().log().ifError(); -- Prints log only if there is error
		       .then().log().all();
	}
	
	@Test
	public void forAuthURI1() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		RequestSpecification request= RestAssured.given();
		
		String username = "postman";
		String password = "password";
		
		request.auth().preemptive().basic(username, password)
		       .when().get("basic-auth")
		       .then().log().ifError();// -- Prints log only if there is error
		       
	}
	
	@Test
	public void forAuthURI2() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		RequestSpecification request= RestAssured.given();
		
		String username = "postman";
		String password = "password";
		
		request.auth().preemptive().basic(username, password)
		       .when().get("basic-auth")
		       .then().log().ifStatusCodeIsEqualTo(200);// -- Prints log only if status code matches
		       
	}

}
