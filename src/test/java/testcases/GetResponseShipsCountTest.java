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
import java.util.List;

public class GetResponseShipsCountTest extends BaseTest{
	
	@Test(dataProvider="TestData")
	public void getResponseShipsCount(int expectedShipCount,String resource) {
		logger = extent.createTest("getResponseShipsCount", "Verify the count of Ships");
		logger.info("Base URI: "+configFileReader.getEndPointUrl());
		logger.info("Resource: "+resource);
		
		String response=given()
		.when()
		.get(resource)
		.then().log().all().extract().response().asString();
		JsonPath js=new JsonPath(response);
		List<String> ships=js.get("ships");
		logger.info("Actual Ship count: "+ships.size());
		logger.info("Expected  Ship count: "+expectedShipCount);
		Assert.assertEquals(ships.size(),expectedShipCount);
		
		
	}
	
	@DataProvider(name = "TestData")
	public Iterator<Object[]> getTestData() {
		
		ArrayList<Object[]> testdata = new ArrayList<Object[]>();
		ExcelFileReader excelFileReader = new ExcelFileReader("GetResponseShipsCountTest");
		int rowCount = excelFileReader.getRowCount();
		for (int rowNumber = 1; rowNumber < rowCount; rowNumber++) {
			String expectedShipCount = excelFileReader.getCellData("expectedShipCount", rowNumber);
			String resource = excelFileReader.getCellData("resource", rowNumber);

			int intExpectedShipCount=Integer.parseInt(expectedShipCount);
			Object data[] = {intExpectedShipCount,resource};
			testdata.add(data);	

		}
		
		return testdata.iterator();

	}
}
