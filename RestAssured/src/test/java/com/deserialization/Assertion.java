package com.deserialization;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import Assertion1.PostRequest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import requests.AllRequests;
public class Assertion {

	@Test
	public void test() throws IOException {
		String[] resultsName= {"Sydney","ibis Sydney Darling Harbour","Novotel Sydney on Darling Harbour","Oaks Goldsbrough Apartments","The Grace Hotel","QT Sydney","The Sebel Quay West Suites Sydney","Fraser Suites Sydney","Four Seasons Sydney","PARKROYAL Darling Harbour","Radisson Hotel and Suites Sydney","Sydney Harbour YHA - The Rocks","Amora Hotel Jamison Sydney","The Little Snail Restaurant","Adina Apartment Hotel Sydney Darling Harbour","Hotel Swissôtel Sydney","The Star Grand Residences","Harbour Rocks Hotel Sydney - MGallery by Sofitel","Sofitel Sydney Wentworth","Sydney"};
		PostRequest.propTest();
		RestAssured.baseURI = PostRequest.prop.getProperty("gethost");

		Root gr = given().param("location", PostRequest.prop.getProperty("location"))
				.param("key", PostRequest.prop.getProperty("getkey"))
				.param("radius", PostRequest.prop.getProperty("redius")).expect().defaultParser(Parser.JSON).

				when().get(AllRequests.getPlaceData()).as(Root.class);
		    ArrayList<String> actualname=new ArrayList<String>();
		     List<Results> resultcount=gr.getResults();
		     for(int i = 0;i<resultcount.size();i++) {
		     actualname.add(gr.getResults().get(i).getName());
		     
		     }
		    List<String> expectedlist= Arrays.asList(resultsName);
		    Assert.assertTrue(actualname.equals(expectedlist), "few is not matching");
		
		
		/*
		 * List<Results> resultcount=gr.getResults(); 
		 * for(int i=0;i<resultcount.size();i++) 
		 * { //System.out.println(gr.getResults().get(i).getName());
		 * if(gr.getResults().get(i).getName().equalsIgnoreCase("Hotel Swissôtel Sydney")) 
		 * {
		 * System.out.println(gr.getResults().get(i).getId()); 
		 * } 
		 * }
		 */
		 //-------------------------------------------
		/*
		 * System.out.println(gr.getNext_page_token());
		 * System.out.println("get Result id");
		 * System.out.println(gr.getResults().get(1).getId());
		 * System.out.println("get opening_hours");
		 * System.out.println(gr.getResults().get(1).getOpening_hours().getOpen_now());
		 * 
		 * System.out.println("get getLat");
		 * System.out.println(gr.getResults().get(1).getGeometry().getLocation().getLat(
		 * ));
		 * 
		 * System.out.println("get getHeight");
		 * //System.out.println(gr.getResults().get(1).getPhotos()); Photos[]
		 * photos=gr.getResults().get(1).getPhotos();
		 * 
		 * System.out.println(photos[0].getHeight());
		 */
		

	}
}
