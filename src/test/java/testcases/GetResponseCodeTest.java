package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataProviders.ConfigFileReader;
import dataProviders.ExcelFileReader;
import generics.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Iterator;

public class GetResponseCodeTest extends BaseTest{
	
	@Test(dataProvider="TestData")
	public void getResponseCode(String resource) {
		logger = extent.createTest("getResponseCode", "Get the Response Code");
		
		logger.info("Base URI: "+configFileReader.getEndPointUrl());
		logger.info("Resource: "+resource);
		
		int actualStatusCode= given()
		.when()
		.get(resource)
		.then().log().all().extract().response().getStatusCode();
		int expectedStatusCode=Integer.parseInt("200");
		
		
		
		logger.info("Actual Status Code: "+actualStatusCode);
		Assert.assertEquals(actualStatusCode, expectedStatusCode);
		
		
	}
	
	@DataProvider(name = "TestData")
	public Iterator<Object[]> getTestData() {
		
		ArrayList<Object[]> testdata = new ArrayList<Object[]>();
		ExcelFileReader excelFileReader = new ExcelFileReader("GetResponseCodeTest");
		int rowCount = excelFileReader.getRowCount();
		for (int rowNumber = 1; rowNumber < rowCount; rowNumber++) {
			
			String resource = excelFileReader.getCellData("resource", rowNumber);
			
			Object data[] = {resource};
			testdata.add(data);	

		}
		
		return testdata.iterator();

	}
}
