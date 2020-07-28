package Assertion1;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ResuableMethods {
	
	
	public static XmlPath rawToXml(Response res) {
		 String resdata= res.asString();
		 XmlPath x= new XmlPath(resdata);
		return x;
		
	}
	
	public static JsonPath rawToJson(Response res) {
		 String resdata= res.asString();
		 JsonPath x= new JsonPath(resdata);
		return x;
		
	}
	
	public static String getjiraSessionKey() throws IOException {
		PostRequest.propTest();
		RestAssured.baseURI=PostRequest.getProp().getProperty("jirahost");	
		Response res= given().
		    header("Content-Type","application/json").
		    body("{ \"username\": \"shila.p@transneuron.com\", \"password\": \"Shila@2020\" }").
		 when().
		    post("/rest/auth/1/session").
		    
		   then().
		   assertThat().statusCode(200).contentType(ContentType.JSON).
		  extract().response();
		JsonPath js=ResuableMethods.rawToJson(res);
		String sessionId=js.get("session.value");
		return sessionId;
		
	}

}
