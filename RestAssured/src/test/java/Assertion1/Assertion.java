package Assertion1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import requests.AllRequests;

public class Assertion {

	@Test
    public void test() throws IOException {
    	PostRequest.propTest();
      RestAssured.baseURI=PostRequest.prop.getProperty("gethost");
		
		Response res=given().
		    param("location",PostRequest.prop.getProperty("location")).
		    param("key",PostRequest.prop.getProperty("getkey")).
		    param("radius",PostRequest.prop.getProperty("redius")).
		    
		when().
		       get(AllRequests.getPlaceData()).
		       
		then().
		        assertThat().statusCode(200).and().contentType(ContentType.JSON).and(). 
		        extract().response();
		   
	     //	res.prettyPrint();
		     JsonPath js=ResuableMethods.rawToJson(res);
		    int count= js.get("results.size()");
		    System.out.println(count);

		    for(int i=0;i<count;i++)          //or i+=2
		    {
			  System.out.println(js.get("results["+i+"].name"));
		  
		  }
		 
		     
		
		/*
		 * and().body("results[0].geometry.location.lat",equalTo("-33.86882"));
		 * body("results[0].name",equalTo("Sydney")).and().
		 * body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
		 * header("server" , "scaffolding on HTTPServer2");
		 */
		
		
    	
    	
    	
    }
}
