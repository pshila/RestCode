package Assertion1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PostRequestXml {
		@Test
		public void createPlaceAPItest() throws IOException
		{ 
			String postData=generateStringFromResource("C:\\Users\\shila\\Desktop\\PostData.xml");
			RestAssured.baseURI="http://216.10.245.166";
			Response res= given().
			queryParam("key","qaclick123").
			body(postData).
			when().
			post("/maps/api/place/add/xml").
			then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().
			//body("status",equalTo("OK"));
			extract().response();
			res.prettyPrint();
			  
			 XmlPath x=ResuableMethods.rawToXml(res);
			 System.out.println( "Place_id: "+x.get("response.place_id"));
			
		}
		

	/*
	 * 
	 * <response>
	 *  <status>OK</status>
	 * <place_id>35c195b774dd70c7868dec57f4e3f903</place_id> 
	 * <scope>APP</scope>
	 * <reference>ac49846dba5fb12cdb255b358252ae92ac49846dba5fb12cdb255b358252ae92</reference> 
	 * <id>ac49846dba5fb12cdb255b358252ae92</id> 
	 * </response>
	 */
		public static String generateStringFromResource(String Path) throws IOException {
			
			return new String(Files.readAllBytes(Paths.get(Path))); 
			
		}
	}



