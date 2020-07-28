package LibraryAPI;

import org.testng.annotations.Test;
import Assertion1.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticPostRequestJson {
		@Test
		public void addLibraryAPItest() throws IOException
		{ 

			RestAssured.baseURI="http://216.10.245.166";
			Response res=given().
		    		
			body(generateStringFromResource("C:\\Users\\shila\\Documents\\bodyData.json")).          //  select all pay-load then go on re-factor then extract method
			when().
			post("/Library/Addbook.php").
			
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON). 
			extract().response();
			res.prettyPrint();
			
			 JsonPath jsondata=ResuableMethods.rawToJson(res);
			  String id=jsondata.get("ID");
			 System.out.println(id);
			 
			 //For delete book
			 Response res2= given().
			  body("{\r\n" + 
			  		" \r\n" + 
			  		"\"ID\" : \""+id+"\"\r\n" + 
			  		" \r\n" + 
			  		"}").
			  when().
			    post("/Library/DeleteBook.php"). 
			     
			    then(). 
			       assertThat().statusCode(200).and().contentType(ContentType.JSON). 
			       extract().response();
			  res2.prettyPrint();

			
		}
		public static String generateStringFromResource(String Path) throws IOException {
			
			return new String(Files.readAllBytes(Paths.get(Path))); 
			
		}
	}



