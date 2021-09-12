package RestAssured.Phase3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredTest3 {
	
	@Before
	public void beforetest() {
		
		//create base path
        RestAssured.baseURI="https://reqres.in";

	}
	
	@Test
	public void getRequest() {
		
        RequestSpecification request= RestAssured.given();
        
        //Reading Response data
        
        Response response = request.get("/api/users?page=2");
        //response.then().log().all();
        JsonPath jp = response.jsonPath();
        System.out.println(jp.getInt("total"));
        System.out.println(jp.getString("data[0].email"));
        
	}
	
	@Test
	public void getRequestAssertion() {
		
        RequestSpecification request= RestAssured.given();
        
        //Reading Response data
        
        Response response = request.get("/api/users?page=2");
        //response.then().log().all();
        JsonPath jp = response.jsonPath();
        System.out.println(jp.getInt("total"));
        System.out.println(jp.getString("data[0].email"));
        
        String Actualvalue = "michael.lawson@reqres.in";
        Assert.assertEquals("Email", Actualvalue, jp.getString("data[0].email"));
	}
	
	@Test
	public void httpValidationErrors() {
		
		RestAssured.given().baseUri("https://www.picturematters.in")
		.get().then().log().all();
		
	}
	
	@Test
	public void httpValidationErrorsFixed() {
		
		RestAssured.given().relaxedHTTPSValidation()
		.baseUri("https://www.picturematters.in")
		.get().then().log().all();
		
	}
	
	@Test
	public void httpValidationErrorsFixed2() {
		
		RestAssured.baseURI = "https://www.picturematters.in";
		RequestSpecification request= RestAssured.given();
		request.relaxedHTTPSValidation()
		.get().then().log().all();
		
	}

}
