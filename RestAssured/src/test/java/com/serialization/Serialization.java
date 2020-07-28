package com.serialization;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Serialization {

	@Test
	public void serializationTest() {

		AddPlace p = new AddPlace();
		p.setAccuracy(60);
		p.setName("Drig");
		p.setLanguage("Hindi_English");
		p.setPhone_number("678965434378");
		p.setAddress("bangalore india");
		p.setWebsite("http://gmail.com");

		List<String> mylist = new ArrayList<String>();
		mylist.add("Deer");
		mylist.add("Rabbit");
		p.setTypes(mylist);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.447362);
		p.setLocation(l);

		RequestSpecification res1 = given().spec(SpecBuilder.requestTest()).body(p);
		Response res3 = res1.when().post("/maps/api/place/add/json").then().assertThat()
				.spec(SpecBuilder.responseTest()).extract().response();

		String sg = res3.asString();
		System.out.println(sg);

	}

}
