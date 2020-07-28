package Assertion1;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import requests.AllRequests;
import requests.Payload;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;



public class PostRequest {
	public static Properties prop= new Properties();
	@BeforeTest
	public static void propTest() throws IOException {
		
		FileInputStream fis= new FileInputStream("C:\\Users\\shila\\eclipse-api\\RestAssured\\src\\test\\resources\\resource.properties");
	     prop.load(fis);
	}
	
	public static Properties getProp() {
		return prop;
	}
	
	@Test
	public void postrequesttest() {
		
		RestAssured.baseURI=prop.getProperty("host");
		Response res=given().
		         queryParam("key",prop.getProperty("key")).
		         body(Payload.getPayLoadDData()).
		          when().
		                  post(AllRequests.placePostData()).
		         then(). 
		                 assertThat().statusCode(200).and().contentType(ContentType.JSON).and(). 
		                 body("status",equalTo("OK")).
		                 extract().response();
		     res.prettyPrint();
		     
		 // capture the response
		     
		      String resposestring= res.asString();
		      System.out.println(resposestring);
		      
		/*
		 * JsonPath js= new JsonPath(resposestring); 
		 * String placeid=js.get("place_id");
		 * System.out.println("place id="+placeid);
		 */
		       JsonPath js=new JsonPath(resposestring);
		       String placeid=js.get("place_id");
		       System.out.println("place id:"+placeid);
		       
		 // delete the place which is wrapped 
		       
		       given().
		             queryParam("key", prop.getProperty("key")).
		             body("{"+
		            		  "\"place_id\": \""+placeid+"\""+
		            		"}").
		         when().
                           post(AllRequests.placeDeleteData()).

                 then(). 
                         assertThat().statusCode(200).and().contentType(ContentType.JSON).and(). 
                         body("status",equalTo("OK"));
		       
		        
	}

	

}
