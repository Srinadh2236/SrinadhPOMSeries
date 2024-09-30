package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	ElementUtil eleUtil;
	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By serachBtn = By.xpath("//div[@id='search']/span/button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}

	public String getAccountsPageTitle() {
		String accTitle = eleUtil.waitForTitleContainsReturn(AppConstants.ACCOUNT_PAGE_TITLE,
				AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		System.out.println("Accounts page title :" + accTitle);
		return accTitle;
	}

	public boolean isLogoutLinkIsExists() {
		return eleUtil.isElementDisplayed(logoutLink);
	}
	
	public int accPageHeaderCount() {
		return eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();

	}

	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> headersValue = new ArrayList<String>();

		for (WebElement e : headerList) {
			String header = e.getText();
			headersValue.add(header);
		}

		return headersValue;
	}
	
	public ResultsPage doSearch(String searchKey ) {
	WebElement searchEle=	eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIME_OUT);
	searchEle.clear();
	searchEle.sendKeys(searchKey);
		eleUtil.doClick(serachBtn);
		return new ResultsPage(driver);
		
	}
	
	
	
	
	

}
