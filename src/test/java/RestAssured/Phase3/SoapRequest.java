package RestAssured.Phase3;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.CoreMatchers.equalTo;

public class SoapRequest {
	
	@Before
	public void beforetest() {
		
		//create base path
        RestAssured.baseURI="https://www.dataaccess.com";

	}
	
	@Test
	public void soapRequest() {
		
		String payload = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>unsignedLong</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		
        RequestSpecification request= RestAssured.given();
        
        //Content-Type: text/xml; charset=utf-8
        
        request.header("Content-Type","text/xml; charset=utf-8")
               .and().body(payload)
               .when().post("/webservicesserver/NumberConversion.wso").then().log().body();
        
	}
	
	@Test
	public void soapRequestwithAssertion() {
		
		String payload = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>1002</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		
        RequestSpecification request= RestAssured.given();
        
        //Content-Type: text/xml; charset=utf-8
        
        Response response = request.header("Content-Type","text/xml; charset=utf-8")
               .and().body(payload)
               .when().post("/webservicesserver/NumberConversion.wso");
        response.then().assertThat().body("Envelope.Body.NumberToWordsResponse.NumberToWordsResult", equalTo("one thousand two"));
	}

}
