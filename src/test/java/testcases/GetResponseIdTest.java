package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataProviders.ExcelFileReader;
import generics.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Iterator;

public class GetResponseIdTest extends BaseTest{
	
	@Test(dataProvider="TestData")
	public void getResponseId(String expectedId,String resource) {
		
		logger = extent.createTest("getResponseID", "Verify the Response Id attribute");
		logger.info("Base URI: "+configFileReader.getEndPointUrl());
		logger.info("Resource: "+resource);
		String response=given()
		.when()
		.get(resource)
		.then().log().all().extract().response().asString();
		JsonPath js=new JsonPath(response);
		String actualId=js.get("id");
		logger.info("Actual ID: "+actualId);
		logger.info("Expected ID: "+expectedId);
		Assert.assertEquals(actualId, expectedId);
		
		
	}
	
	@DataProvider(name = "TestData")
	public Iterator<Object[]> getTestData() {
		
		ArrayList<Object[]> testdata = new ArrayList<Object[]>();
		ExcelFileReader excelFileReader = new ExcelFileReader("GetResponseIdTest");
		int rowCount = excelFileReader.getRowCount();
		for (int rowNumber = 1; rowNumber < rowCount; rowNumber++) {
			String expectedId = excelFileReader.getCellData("expectedId", rowNumber);
			String resource = excelFileReader.getCellData("resource", rowNumber);
			
			Object data[] = {expectedId,resource};
			testdata.add(data);	

		}
		
		return testdata.iterator();

	}
}
