package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners({AnnotationTransformer.class , ExtentReportListener.class})
@Epic("Epic 100:design open cart login page.")
@Feature("feature 101 :login feature")
@Story("User Story :120 : all features related to the open cart login page ")
@Owner("Srinadh Automantion labs")
@Link(name="login" ,url="https://naveenautomationlabs.com/opencart/index.php?route=account/login")
public class LoginPageTest extends BaseTest {

	@Severity(SeverityLevel.MINOR)
	@Description("login page title test...")
	@Feature("Feature 401:title test feature")
	@Test
	public void loginPageTitleTest() {
	String actualTitle= LoginPage.getLoginPageTitle();
	//Assert.assertEquals(actualTitle, "Account Login");
	Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	@Severity(SeverityLevel.MINOR)
	@Description("login page URL test...")
	@Feature("Feature 402:url test feature")
	@Test
	public void loginPageURLTest() {
	String actualURL = LoginPage.getLoginPageURL();
	//Assert.assertTrue(actualURL.contains(actualURL), "route=account/login");
	Assert.assertTrue(actualURL.contains(actualURL),AppConstants.LOGIN_PAGE_FRACTION_URL);
	}
	@Severity(SeverityLevel.CRITICAL)
	@Description("login page forgot password link exist test...")
	@Feature("Feature 403:forgot password link test feature")
	@Issue("bug:123")
	@Test
	public void isforgotPwdLinkExitTest() {
	boolean flag = LoginPage.isforgotPwdLinkExit();
	Assert.assertTrue(flag);
	
	}
	@Severity(SeverityLevel.CRITICAL)
	@Description("login page logo test...")
	@Test
	public void isLogoIsExistTest() {
	boolean flag = LoginPage.isLogoIsExist();
	Assert.assertTrue(flag);
	
	}
	@Severity(SeverityLevel.MINOR)
	@Description("user able to login.")
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
	accPage=	LoginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	//Assert.assertEquals(actPafeTitle, "My Account");
	Assert.assertEquals(accPage.getAccountsPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	
		
	}
	
}
