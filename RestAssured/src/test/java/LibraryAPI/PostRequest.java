package LibraryAPI;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Assertion1.ResuableMethods;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import requests.Payload;



public class PostRequest {
		
		@Test(dataProvider = "Addbook")
		public void addBookAPI(String isbn,String aisle)
		{
			RestAssured.baseURI="http://216.10.245.166";
			Response res=given().
			body(Payload.payloadDataForLibrary(isbn,aisle)).          //  select all pay-load then go on re-factor then extract method
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
		
		@DataProvider(name="Addbook")
	   public Object[][] getData(){
		// collect of array is multidimensional Array {array1,array2,array3,array4,array5.....}
			return new Object[][] {{"bhani","7875"},{"njn","78575"},{"hdhf","7785"},{"hjdf","4758"},{"djjv","7575"},{"hfjn","4758"}};
			
		}

		
	}



