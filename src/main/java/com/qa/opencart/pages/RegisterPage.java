package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage  {

  private WebDriver driver;
   ElementUtil eleUtil;
   
   
   private By firstName= By.id("input-firstname");
   private By lastName=By.id("input-lastname");
   private By email = By.id("input-email");
   private By telephone = By.id("input-telephone");
   private By password=By.id("input-password");
   private By confirmPwd = By.id("input-confirm");
   
   private By radioBtnYes=By.xpath(	"//label[normalize-space()='Yes']");
   private By radioBtnNo=By.xpath(	"//label[normalize-space()='No']");
   
   private By privcyCheckBox=By.xpath("//input[@name='agree']");
   private By continueBtn =By.xpath("//input[@value='Continue']");
   
   private By errorMesg= By.xpath("(//div[@id='account-register']/div)[1]");
   private By successMesg= By.xpath("//div[@id='content']/h1");
   
   

   
  public RegisterPage(WebDriver driver) {
	  this.driver=driver;
	  eleUtil = new ElementUtil(driver); 
  }
	
  public String regPageFill(String fstName,String lstName,String regEmail ,String tlePhone,String pwd) {
	 eleUtil.waitForElementVisible(firstName, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(fstName);
	 eleUtil.doSendkeys(lastName, lstName);
	 eleUtil.doSendkeys(email, regEmail);
	 eleUtil.doSendkeys(telephone, tlePhone );
	 eleUtil.doSendkeys(password, pwd);
	 eleUtil.doSendkeys(confirmPwd, pwd);
	 
	 eleUtil.doClick(privcyCheckBox);
	 eleUtil.doClick(continueBtn);
	 
	return eleUtil.waitForElementVisible(successMesg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
	  
  }
	
	
}
