package generics;

import java.io.File;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import dataProviders.ConfigFileReader;
import io.restassured.RestAssured;

//import io.restassured.RestAssured;
import utility.Utility;

//import BeforeTest;

public class BaseTest {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ConfigFileReader configFileReader;

	@BeforeSuite
	public void extentReportSetUp() {
		String currentDateAndTime = Utility.getCurrentDateAndTime();
		File reporterPath = new File("./Reports/SpaxeXAutomation_" + currentDateAndTime + ".html");
		htmlReporter = new ExtentHtmlReporter(reporterPath);
		htmlReporter.config().setDocumentTitle("Space X Report");
		htmlReporter.config().setReportName("Space X API Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		
	}
	
	@BeforeMethod
	public void setUp() {
		configFileReader=new ConfigFileReader();
		RestAssured.baseURI=configFileReader.getEndPointUrl();
		
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			logger.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.pass(result.getName() + "-Test Passed");
		}

		if (result.getStatus() == ITestResult.SKIP) {

			logger.info(result.getName() + "-Test Skipped");
		}
		extent.flush();

	}

}
