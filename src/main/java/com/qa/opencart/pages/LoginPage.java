package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
// this is pageClass
	// every page method return somthing note this point

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1.private By locators :Page Objects
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgorPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img.img-responsive");
	
	private By registationLink = By.linkText("Register");
	

	// 2.public page constructors...

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil =  new ElementUtil(driver);
		
	}

	// 3.public page Actions/methods
	@Step("getting login page title value...")
	public String getLoginPageTitle() {
		//String title = driver.getTitle(); //Using util
		String title = eleUtil.waitForTitleContainsReturn(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login Page title : " + title);
		return title;
	}
	@Step("getting login page URL value...")

	public String getLoginPageURL() {
		//String url = driver.getCurrentUrl();
		String url =eleUtil.waitForURLContainsReturn(AppConstants.LOGIN_PAGE_FRACTION_URL,AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login Page url :" + url);
		return url;
	}

	@Step("checking forgot pwd link is avaialble or not...")

	public boolean isforgotPwdLinkExit() {
		//return driver.findElement(forgorPwdLink).isDisplayed();
	return	eleUtil.isElementDisplayed(forgorPwdLink);
	}
	@Step("login with username:{0} and password :{1}")
	public AccountsPage doLogin(String userName,String pwd) {
//		driver.findElement(username).sendKeys(userName);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//		
		System.out.println("userName :"+ userName +"password :"+pwd);
		eleUtil.waitForElementVisible(username, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(userName);
		eleUtil.doSendkeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
//		String actPageTitle= eleUtil.waitForTitleContainsReturn(AppConstants.ACCOUNT_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIME_OUT);
//		System.out.println("Account page title: "+actPageTitle);
//		return actPageTitle;
		
		//for accounts page chaining  once login button it will pass the same driver to the accounts page returning the accountspage
		
		return new AccountsPage(driver);
		
		
	}
	
	@Step("checking logo exit on the login page ....")
	public boolean isLogoIsExist() {
	//return	driver.findElement(logo).isDisplayed();	
	return	eleUtil.isElementDisplayed(logo);
	}
	
	@Step("navigating to the registration page....")
	public RegisterPage registrationLink() {
		eleUtil.waitForElementAndClick(registationLink, AppConstants.DEFAULT_SHORT_TIME_OUT);
		return new RegisterPage(driver);
	}
}
