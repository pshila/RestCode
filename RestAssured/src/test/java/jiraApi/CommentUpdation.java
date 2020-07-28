package jiraApi;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import org.testng.annotations.Test;
import Assertion1.PostRequest;
import Assertion1.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommentUpdation {

	@Test
	public void commentupdateTest() throws IOException {
		PostRequest.propTest();
		RestAssured.baseURI=PostRequest.getProp().getProperty("jirahost");
	     Response res=given().
	        header("Content-Type","application/json").
	        header("Cookie","JSESSIONID="+ResuableMethods.getjiraSessionKey()+"").
	        pathParams("commentid","10103").
	        body("{ \\\"body\\\": \\\"Updating comment from the automation code\\\",\"+\r\n" + 
	        		"    \"\\\"visibility\\\": {\"+\r\n" + 
	        		"        \"\\\"type\\\": \\\"role\\\",\"+\r\n" + 
	        		"        \"\\\"value\\\": \\\"Administrators\\\" }\"+\r\n" + 
	        		"\"}").
	     when().
	        put("/rest/api/2/issue/10207/comment/{commentid}").                                         //path parameter 
	    then().assertThat().statusCode(201).and().contentType(ContentType.JSON).extract().response();
	     JsonPath js=ResuableMethods.rawToJson(res);
	     String commentupdateId=js.get("id");
	     System.out.println(commentupdateId);

	}
}
