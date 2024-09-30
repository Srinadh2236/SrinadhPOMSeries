package com.qa.opencart.utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public String getPageTitle() {
		return js.executeScript(" return document.title;").toString();

	}

	public String getPageURL() {
		return js.executeScript("return document.URL").toString();
	}
	
	public void generateJsAlert(String mesg) {
		js.executeScript("alert('"+mesg+"')");
	}
	
	public String getPageInnerText() {
	return	js.executeScript("return document.documentElement.innerText;").toString();
	}

	public void goBackWithJs() {
		js.executeScript("history.go(-1);");
	}
	public void goForwordWithJs() {
		js.executeScript("history.go(1);");
	}
	
	public void pageRefreshWithJS() {
		js.executeScript("history.go(0);");
	}
	public void zoomChromeEdgeFirefox(String zoomPercentage) {
		js.executeScript("document.body.style.zoom='"+zoomPercentage+"';");
	}
	public void scrollPageDown(String height) {
		js.executeScript("window.scrollTo(0,"+height+");");
	}
	public void scrollBottomPage() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}
	public void ScrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0);");
	}
	public void ScrollMiddlePage() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2);");
	}
	
	public  void scrollToElemet(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	
	public void flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for (int i=0;i<10;i++) {
			changeColor("rgb(0,200,0)",element);//green
			changeColor(bgColor, element);//white
			
		}
	}
	
	public void changeColor(String color,WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);//G--W--G--W---G
		try {
			Thread.sleep(20);		
		}
		catch (InterruptedException e) {
			
		}
	}
	
	
	
	
	
	
	
	
	
}
