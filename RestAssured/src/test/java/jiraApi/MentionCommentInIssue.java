package jiraApi;

import java.io.IOException;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import Assertion1.PostRequest;
import Assertion1.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MentionCommentInIssue {
	@Test
	public void commentTest() throws IOException {
		PostRequest.propTest();
		RestAssured.baseURI=PostRequest.getProp().getProperty("jirahost");
	     Response res=given().
	        header("Content-Type","application/json").
	        header("Cookie","JSESSIONID="+ResuableMethods.getjiraSessionKey()+"").
	        body("{ \"body\": \"Inserting comment from the automation code\",\"+\r\n" + 
	        		"    \"\"visibility\": {\"+\r\n" + 
	        		"        \"\"type\\\": \"role\",\"+\r\n" + 
	        		"        \"\"value\": \"Administrators\" }\"+\r\n" + 
	        		"\"}").
	     when().
	        post("/rest/api/2/issue/10207/comment/").
	    then().assertThat().statusCode(201).and().contentType(ContentType.JSON).extract().response();
	     JsonPath js=ResuableMethods.rawToJson(res);
	     String commentId=js.get("id");
	     System.out.println(commentId);

	}

}
