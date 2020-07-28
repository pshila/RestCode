package jiraApi;

import java.io.IOException;
import org.testng.annotations.Test;
import Assertion1.PostRequest;
import Assertion1.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class CreateIssue {
	
	
	@Test
	public void jiratest() throws IOException {
		PostRequest.propTest();
		RestAssured.baseURI=PostRequest.getProp().getProperty("jirahost");	
		 Response res=given().
		    header("Content-Type","application/json").
		    header("Cookie","JSESSIONID="+ResuableMethods.getjiraSessionKey()+"").
		    body("{\"+\r\n" + 
		    		"    \"\\\"fields\\\": {\"+\r\n" + 
		    		"       \"\\\"project\\\":{\"+\r\n" + 
		    		"          \"\\\"key\\\": \\\"SIP\\\"\"+\r\n" +             //for body we will mention project key so we can create issue under particular project
		    		"       \"},\"+\r\n" + 
		    		"       \"\\\"summary\\\": \\\"Issue 5 for adding comment\\\",\"+\r\n" + 
		    		"       \"\\\"description\\\": \\\"Creating my second bug\\\",\"+\r\n" + 
		    		"       \"\\\"issuetype\\\": {\"+\r\n" + 
		    		"          \"\\\"name\\\": \\\"Bug\\\"\"+\r\n" + 
		    		"       \"}\"+\r\n" + 
		    		"   \"}}").
		    when().
                  post("/rest/api/2/issue").
                  
            then().assertThat().statusCode(201).and().contentType(ContentType.JSON).extract().response();   
		 JsonPath js=ResuableMethods.rawToJson(res);
		String issueId= js.get("id");
		System.out.println("issueid:"+issueId);
		
	}

}
