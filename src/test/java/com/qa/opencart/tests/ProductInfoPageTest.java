package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {
		accPage = LoginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void productHeaderTest() {
		resultPage = accPage.doSearch("macbook");
		productInfoPage = resultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}

//	MacBook Pro
// Key     :Value	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
//	$2,000.00
//	Ex Tax: $2,000.00
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] {
			{"macbook","MacBook Pro","Apple","Product 18","800","In Stock","$2,000.00","$2,000.00"},	
			{"macbook","MacBook Air","Apple","Product 17","700","Out Of Stock","$1,202.00","$1,000.00"}

		};
	}
	@Test(dataProvider ="getProductInfoData" )
	public void productInfoTest(String searchKey,String productName,String brandName,String productCode,String rewardPoints,String availability,String pdtprive,String extPrice) {
		resultPage = accPage.doSearch(searchKey);
		productInfoPage = resultPage.selectProduct(productName);
		Map<String, String> actProductData = productInfoPage.getProductData();

		softassert.assertEquals(actProductData.get("Brand"), brandName);
		softassert.assertEquals(actProductData.get("Product Code"), productCode);
		softassert.assertEquals(actProductData.get("Reward Points"), rewardPoints);
		softassert.assertEquals(actProductData.get("Availability"), availability);
		softassert.assertEquals(actProductData.get("productprice"), pdtprive);
		softassert.assertEquals(actProductData.get("extaxprice"), extPrice);
		softassert.assertAll(); // if any assert fail it will goto the next assert . but when we use Assert
								// class it wnt go to the next assert it will be failed.\

	}
	@DataProvider
	public Object[][]  getProductImagesCountData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"samsung","Samsung SyncMaster 941BW",1},
			
			
		};
	}
	@Test(dataProvider ="getProductImagesCountData")
	public void productImagesCountTest(String searchKey,String ProductName,int ImageCount) {
		resultPage = accPage.doSearch(searchKey);
		productInfoPage = resultPage.selectProduct(ProductName);
	Assert.assertEquals(productInfoPage.getProductImagesCount(), ImageCount); 	
	}
	
}
