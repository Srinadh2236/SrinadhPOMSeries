package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void setUp() {
		accPage = LoginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String accTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(accTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void isLogoutLinkIsExistsTest() {
		Assert.assertTrue(accPage.isLogoutLinkIsExists());
	}

	@Test
	public void  getAccPageHeadersTest() {
	List<String> headerList=accPage.getAccPageHeaders();
	Assert.assertEquals(headerList, AppConstants.EXPECTED_ACC_PAGE_HEADER_LIST);
	}

	@Test
	public void accPageHeaderCountTest() {
		int headerCount = accPage.accPageHeaderCount();
		Assert.assertEquals(headerCount, AppConstants.ACCOUNTS_PAGE_HEADER_COUNT);

	}
	
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2}
		};
	}
	
	@Test(dataProvider = "getSearchKey")
	public void searchCountTest(String searchKey,int searchCount) {
	resultPage =accPage.doSearch(searchKey);
	  Assert.assertEquals( resultPage.getSearchResultsCount(),searchCount);  
	}
	
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"macbook","MacBook"},
			{"imac","iMac"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"samsung","Samsung SyncMaster 941BW"},

		};
	}
	
	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey,String productName) {
		resultPage =accPage.doSearch(searchKey);
		productInfoPage =	resultPage.selectProduct(productName);
	Assert.assertEquals(productInfoPage.getProductHeader(), productName);	
	}
	
}
