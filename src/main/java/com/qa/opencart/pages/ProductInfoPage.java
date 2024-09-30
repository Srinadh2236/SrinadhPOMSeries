package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private Map<String,String> productMap;
	
	private By productHeader= By.xpath("//div[@id='content']//h1");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private By productImages=By.xpath("//ul[@class='thumbnails']//img");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	public String getProductHeader() {
	String productHeaderValue=	eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
	System.out.println("Product header ====> "+productHeaderValue);
	return productHeaderValue;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForElementsVisible(productMetaData,AppConstants.DEFAULT_MEDIUM_TIME_OUT);

		productMap = new HashMap<String, String>();
		for (WebElement meta : metaList) {
			String metaText = meta.getText();
			String metaData[] = metaText.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();

			productMap.put(metaKey, metaValue);// key Brad : value is Apple when we give key as brand we will get Apple

		}
	}
	 
//  $2,000.00
//	Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData,AppConstants.DEFAULT_MEDIUM_TIME_OUT);

	String price=	priceList.get(0).getText();
	String exTaxPrice=	priceList.get(1).getText().split(":")[1].trim(); // here only  spliting "Ex Tax: $2,000.00" are there so im giving the index of 1 is $2000; 
	productMap.put("productprice",price);// storing anf there is no key and par value for this so we are hotcoded ad KEY is"productprice" value is getting from split index[1]
	productMap.put("extaxprice", exTaxPrice);
	
	}
	
//	MacBook Pro
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
//	$2,000.00
//	Ex Tax: $2,000.00
	public Map<String, String> getProductData() {
		productMap = new HashMap<String, String>(); //  no order maintain
		
//		productMap = new LinkedHashMap<String, String>(); // it will maintain insertion order only
//		productMap = new TreeMap<String, String>(); /// it will maintain oreders in alphabetical 

		productMap.put("productheader", getProductHeader());
		getProductMetaData(); //calling above methods get the details total 8 
		getProductPriceData();
		
		System.out.println("Product  data :" + productMap);
		return productMap;
	}
	
    public int getProductImagesCount() {
    int imagesCount=	eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
    System.out.println("Images Count :"+imagesCount);
    return imagesCount;
    }
	
	
	
	
	
}
