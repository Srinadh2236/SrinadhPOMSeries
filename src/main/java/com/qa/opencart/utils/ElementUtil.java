package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.FrameWorkException;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {

	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil jsUtil;

	// constructor
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		jsUtil= new JavaScriptUtil(driver);
	}

	/**
	 * 
	 * @param locator:we have only By locator id
	 * @param value:     store the data which we entered in text field.
	 * @getAttribute :is the attributes to get data in placeholder and value and
	 *               hrefs
	 * @return the String value of text field which we entered.
	 */
	public String doElementGetattribute(By locator, String AttriName) {

		return getElement(locator).getAttribute(AttriName);
	}

	/**
	 * @param locator
	 * @return boolean if its displayed true or false
	 */
	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not Displayed " + locator);
			return false;
		}
	}

	/**
	 * 
	 * @param locator to getText
	 * @return text of element
	 */
	public String getElementText(By locator) {
		String eleText = getElement(locator).getText();
		if (eleText != null) {
			return eleText;
		} else {
			System.out.println("Element Text is null");
			return null;
		}
	}

	/**
	 * @param locator to click peticular links and buttons and if its clickable
	 */
	@Step("clicking on element using locator: {0}")
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * doClick with wait for specific element and with click
	 * 
	 * @param locator:by locator
	 * @param timeOut:   wait time with sec
	 */
	public void doClick(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}

	/**
	 * @param locator is By and value is data
	 * @param value   to send in particular fields
	 */
	@Step("entering the value:{1}")
	public void doSendkeys(By locator, String value) {

		getElement(locator).sendKeys(value);
	}
	
	public void doSendkeys(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * dosendkeys with wait for specific element and with sendkeys
	 * 
	 * @param locator:by locator
	 * @param value:     passing value
	 * @param timeOut:   wait time with secs
	 */
	public void doSendKeys(By locator, String value, int timeOut) {
		waitForElementVisible(locator, timeOut).sendKeys(value);

	}

	/**
	 * @overload method Char sequence
	 * @param locator
	 * @param value   we can add by , separated (naveen,automation,labs,selenium)
	 */
	public void doSendkeys(By locator, CharSequence... value) {

		getElement(locator).sendKeys(value);

	}

	
	private void checkElementHighligh(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.isHighlight)) {
			jsUtil.flash(element);
			}
	}
	/**
	 * 
	 * @param this is base for find.Element
	 * @return webElement
	 */
	public WebElement getElement(By locator) {

		WebElement element= driver.findElement(locator);
		checkElementHighligh(element);
		return element;
	}

	/**
	 * to maximize the window size
	 */
	public void windowMax() {

		driver.manage().window().maximize();
	}

	/**
	 * @param Print the list of string of elements based on by locator
	 *              (links,images..etc..)
	 */
	public void printElementsTextList(By loactor) {
		List<String> eletext = getElementsTextList(loactor);
		for (String e : eletext) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @param locator
	 * @return Elements list of String
	 */
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);

		List<String> eleTextList = new ArrayList<String>();// fresh array list to store one bye one

		for (WebElement e : eleList) {
			String eleText = e.getText();
			if (eleText.length() != 0) {
				eleTextList.add(eleText);// here new array list called and added to one bye one. stored in eleTextList
			}
		}
		return eleTextList;
	}

	/**
	 * 
	 * @param locator
	 * @return the list elements count in integer
	 */
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	/**
	 * 
	 * @param locator
	 * @return if element is present it return true , it same as is displayed
	 *         method.
	 */
	public boolean isElementPresent(By locator) {
		// getElemetnsCunt(locator); calling count method retuns the count based on
		// checking .
		if (getElementsCount(locator) == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param locator
	 * @param expectedElementCount: enter Expected count and then return true if
	 *                              matched
	 * @return
	 */
	public boolean isElementPresent(By locator, int expectedElementCount) {
		if (getElementsCount(locator) == expectedElementCount) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param locator: is not correct
	 * @return true
	 */
	public boolean isElementNotPresent(By locator) {
		if (getElementsCount(locator) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param locator:should be there more than one and then return true.
	 * @return
	 */
	public boolean isElementPresentMultipleTimes(By locator) {// should be there more than one so it return true

		if (getElementsCount(locator) > 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param this is base for Find.Elements
	 * @return the list of WebElements
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public boolean doSearch(By searchField, By suggestions, String searchKey, String matchValue)
			throws InterruptedException {

		// driver.findElement(searchField).sendKeys(searchKey);
		boolean flag = false;
		doSendkeys(searchField, searchKey);

		Thread.sleep(3000);

		// List<WebElement> SuggList = driver.findElements(suggestions);
		List<WebElement> suggList = getElements(suggestions);
		int totalSuggestions = suggList.size();
		System.out.println("Total no of suggestions =" + totalSuggestions);

		if (totalSuggestions == 0) {
			System.out.println("No suggestions found......");
			throw new FrameWorkException("==================No Suggestions Found============");
		}

		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(matchValue)) {
				e.click();
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println(matchValue + " is found");
			return true;
		} else {
			System.out.println(matchValue + " is not found");
			return false;
		}

	}

	// *********************Select Drop Down utils**************************//

	private Select getSelect(By locator) {
		return new Select(getElement(locator));

	}

	public int getDropDownOptionsCount(By locator) {
//		Select select = new Select(getElement(locator)); // repated code so created common method for all select 
//		return select.getOptions().size();

		return getSelect(locator).getOptions().size();

	}

	public void selectDropDownByVisbleText(By locator, String visibleTxt) {
//		Select select = new Select(getElement(locator));
//		select.selectByVisibleText(visibleTxt);

		getSelect(locator).selectByVisibleText(visibleTxt);
	}

	public void selectDropDownByIndex(By locator, int index) {
//		Select select = new Select(getElement(locator));
//		select.selectByIndex(index);
		getSelect(locator).selectByIndex(index);
	}

	public void selectDropDownByValue(By locator, String value) {

//		Select select = new Select(getElement(locator));
//		select.selectByVisibleText(value);

		getSelect(locator).selectByValue(value);
	}

	/*
	 * select drop down with out select class by using getElements
	 */
	public void slectDropDownValue(By loactor, String value) {

		List<WebElement> optionsList = getElements(loactor);
		selectDropDown(optionsList, value); // bellow method called and both functionality same so.it in private

//		System.out.println("total no of options" + optionsList.size());
//
//		for (WebElement e : optionsList) {
//			String text = e.getText();
//			System.out.println(text);
//			if (text.contains(value)) {
//				e.click();
//				break;
//			}
//
//		}
	}

	/*
	 * select drop Down with using select class
	 */

	public void slectDropDownValueUsingSelect(By loactor, String value) {

		// Select select = new Select(getElement(loactor));///method calling getSelect
		List<WebElement> optionsList = getSelect(loactor).getOptions();

		selectDropDown(optionsList, value); // created one more method below its actually common for both

//		System.out.println("total no of options" + optionsList.size());
//
//		for (WebElement e : optionsList) {
//			String text = e.getText();
//			System.out.println(text);
//			if (text.contains(value)) {
//				e.click();
//				break;
//			}
//
//		}
	}

	public List<String> getDropDownOptionsTextList(By loactor) {

		// Select select = new Select(getElement(loactor));//method calling getSelect

		List<WebElement> countList = getSelect(loactor).getOptions();

		List<String> optionsTextList = new ArrayList<String>();
		System.out.println(countList.size());
		for (WebElement e : countList) {
			String text = e.getText();
			optionsTextList.add(text);

		}
		return optionsTextList;
	}

	private void selectDropDown(List<WebElement> optionsList, String value) {

		System.out.println("total no of options" + optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(value)) {
				e.click();
				break;
			}

		}
	}

	// **************************Actions Methods******************************
	public void doActionsClick(By locator) {
		// Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	public void doActionSendkeys(By locator, String value) {
		// Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsSendkeysWithPause(By locator, String value, long pauseTime) {
		char ch[] = value.toCharArray();
		for (char c : ch) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(pauseTime).perform();
		}

	}

	/*
	 * Two level of parent and child menu on basis of by locator.
	 */
	public void parentChildMenu(String parentMenu, String childMenu) throws InterruptedException {

//		By addOns =By.xpath("//div[text()='Add-ons']");
//		By visaServices= By.xpath("//div[text()='Visa Services']");
		act.moveToElement(getElement(By.xpath("//*[text()='" + parentMenu + "']"))).perform();
		Thread.sleep(1500);
		doClick(By.xpath("//*[text()='" + childMenu + "']"));

	}

	// OverLoad method by locators
	public void parentChildMenu(By parentMenu, By childMenu) throws InterruptedException {
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(1500);
		// getElement(childMenu).click();
		doClick(childMenu);
	}

	/*
	 * multilevel parent and child
	 */
	public void parentChildMenu(By level1, By level2, By level3, By level4) throws InterruptedException {

		doClick(level1);
		Thread.sleep(1500);
		act.moveToElement(getElement(level2)).perform();
		Thread.sleep(1500);
		act.moveToElement(getElement(level3)).perform();
		Thread.sleep(1500);
		doClick(level4);

	}

	// ===========================Wait Utils===================================

//	public void doClick(By locator,int timeOut) {
//		waitForElementVisible(locator, timeOut).click();
//	}
//	
//	public void doSendKeys(By locator,String value,int timeOut) {
//		waitForElementVisible(locator, timeOut).sendKeys(value);
//		
//	}
	/**
	 * * An expectation for checking that an element is present on the DOM of a
	 * page. (Dom will be load first) This does not necessarily mean that the
	 * element is visible on the page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		checkElementHighligh(element);
		return element;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible on the page also. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0. default
	 * poling time /500 milli sec or intervaltime
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	@Step("waiting for webelement using locator:{0} with in timeout:{1}")
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		checkElementHighligh(element);
		return element;
	}

	public WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		checkElementHighligh(element);
		return element;
	}

	/**
	 * wait for the element visible on the page with fluent features
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	
	public WebElement waitForElementVisibleWithFluentFeatures(By locator,int timeOut,int pollingTime) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
									.withTimeout(Duration.ofSeconds(timeOut))
									.pollingEvery(Duration.ofSeconds(pollingTime))
									.ignoring(NoSuchElementException.class)
									.ignoring(StaleElementReferenceException.class)
									.ignoring(ElementNotInteractableException.class)
									.withMessage("===========Element is not found=========");
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	
	
	
	
	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */

	public void waitForElementAndClick(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */

	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public String getPageTitleIs(String expectedTitle, int timeOut) {
		if (waitForTitle(expectedTitle, timeOut)) {
			return driver.getTitle();
		} else {
			return "-1";
		}
	}

	public String getPageTitleContains(String expectedTitle, int timeOut) {
		if (waitForTitleContains(expectedTitle, timeOut)) {
			return driver.getTitle();
		} else {
			return "-1";
		}
	}

	public boolean waitForTitle(String expectedTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		// boolean flag= false;
		try {
			return wait.until(ExpectedConditions.titleIs(expectedTitle));// if title not matched it will throw timeout
																			// exception, if matched return true
		} catch (TimeoutException e) {
			System.out.println("title is not matched");
			return false;
		}
	}

	public boolean waitForTitleContains(String frationTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		// boolean flag= false;
		try {
			return wait.until(ExpectedConditions.titleContains(frationTitle));// if title not matched it will throw
																				// timeout exception, if matched return
																				// true
		} catch (TimeoutException e) {
			System.out.println("title is not matched");
			return false;
		}
	}

	public String waitForTitleContainsReturn(String fractionTitle,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		wait.until(ExpectedConditions.titleContains(fractionTitle));
		return driver.getTitle();
		
		}catch (TimeoutException e) {
			System.out.println("title is not matched");
			return "-1";
		}
	}
	
	
	public String getPageURLContains(String fractionURL, int timeOut) {
		if (waitForURLContains(fractionURL, timeOut)) {
			return driver.getCurrentUrl();
		} else {
			return "-1";
		}
	}
	
	public String waitForURLContainsReturn(String fractionURL,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		wait.until(ExpectedConditions.titleContains(fractionURL));
		return driver.getCurrentUrl();
		
		}catch (TimeoutException e) {
			System.out.println("URL is not matched");
			return "-1";
		}
	}
	

	public boolean waitForURLContains(String frationURL, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		// boolean flag= false;
		try {
			return wait.until(ExpectedConditions.urlContains(frationURL));// if url not matched it will throw timeout
																			// exception, if matched return true
		} catch (TimeoutException e) {
			System.out.println("URL is not matched");
			return false;
		}
	}

	public Alert waitForAlertAndSwitch(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());

	}
	
	
	public Alert waitForAlertUsingFluentWaitAndSwitch(int timeOut) {
		Wait<WebDriver>   wait = new FluentWait<WebDriver>(driver)
								.withTimeout(Duration.ofSeconds(timeOut))
								.ignoring(NoAlertPresentException.class)
								.withMessage("==================Js Alert is not present =============");
	
		return wait.until(ExpectedConditions.alertIsPresent());

	}
	

	public String getAlertText(int timeOut) {
		return waitForAlertAndSwitch(timeOut).getText();
	}

	public void acceptAlert(int timeOut) {
		waitForAlertAndSwitch(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlertAndSwitch(timeOut).dismiss();
	}

	public void enterValueOnAlert(int timeOut, String value) {
		waitForAlertAndSwitch(timeOut).sendKeys(value);

	}

	// wait for frame
	public void waitForFrameUsingLocatorAndSwitchToIt(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameUsingLocatorAndSwitchToIt(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameUsingLocatorAndSwitchToIt(int timeOut, String idOrName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrameUsingLocatorAndSwitchToIt(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	// wait for Window/Tab

	public boolean waitForNewWindowOrTab(int expectedNumberOfWindows, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows))) {
				return true;
			}
		} catch (TimeoutException e) {
			System.out.println("number of windows not matched....");
		}
		return false;
	}

}
