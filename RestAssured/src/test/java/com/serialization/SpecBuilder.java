package com.serialization;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	@Test
	public static RequestSpecification requestTest() {
		RequestSpecification res=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				                   .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		return res;
	}
	
	@Test
	public static ResponseSpecification responseTest() {
		ResponseSpecification res2 =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return res2;
	}



}
