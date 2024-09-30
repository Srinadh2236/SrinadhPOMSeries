package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	
	@BeforeTest
	public void registerSetUp() {
	 regPage=	LoginPage.registrationLink();
	}
	
	@DataProvider
	public Object[][] userTestData() {
		
		return new Object[][] {
			{"SrinadAutomation","Selenium","9999666655","Srindd@123"}		
		};
	}

	public String getRandomEmail() {
	return	"Srinad"+System.currentTimeMillis()+"@opencart.com";
	}
	
	@DataProvider
	public Object[][] getRegSheetData() {
	return	ExcelUtil.getTestData(AppConstants.REG_SHEET_NAME);
	}
	
	
	@Test(dataProvider = "getRegSheetData")
	public void regPageFillTest(String fstName,String lstName,String telPhone,String pwd) {
	String header=	regPage.regPageFill(fstName, lstName,getRandomEmail(),telPhone,pwd);
	Assert.assertEquals(header, AppConstants.USER_ACCOUNT_SUCCESS_MESG);
	}

}
