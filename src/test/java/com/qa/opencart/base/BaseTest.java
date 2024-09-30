package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;

import io.qameta.allure.Step;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage LoginPage;
	protected AccountsPage accPage;
	protected RegisterPage regPage;
	protected ResultsPage resultPage;
	protected ProductInfoPage productInfoPage;
	
	protected SoftAssert  softassert;
	
	@Step("Setup with browser :{0} and intializing the properties..")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional("chrome") String broserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		//checking if browsername is coming from xml or not if it not coming it wnt update ing properties file as deful browser name is chrome
		if(broserName !=null) {
			prop.setProperty("browser", broserName);
		}
		driver = df.initDriver(prop);
		LoginPage = new LoginPage(driver);
		softassert = new SoftAssert();  
	}
@Step("closing the browser...")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
