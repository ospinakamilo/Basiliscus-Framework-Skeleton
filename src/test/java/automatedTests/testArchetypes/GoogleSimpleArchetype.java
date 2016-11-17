package automatedTests.testArchetypes;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import automatedTests.testData.GoogleSimpleData;
import basiliscus.helpers.BrowserHelper;

public class GoogleSimpleArchetype{
	
	private static final String SEARCH_INPUT_ID = "lst-ib";
	private static final String IMAGES_LINK_XPATH = "//*[@id='hdtb-msb']/div[2]";
	
	private static BrowserHelper localBrowser;
	
	public static void setBrowser(BrowserHelper browser){
		localBrowser = browser;
	}
	
	public static void goToGoogle(){
		localBrowser.loadUrl("https://www.google.com.co");
	}
	
	public static void searchData(){
		WebElement searchInput = localBrowser.findElementById(SEARCH_INPUT_ID);
		searchInput.sendKeys(GoogleSimpleData.SEARCH_DATA);
		searchInput.sendKeys(Keys.ENTER);
	}
	
	public static void clickOnImages() throws Exception{
		WebElement imagesLink = localBrowser.findElementByXpath(IMAGES_LINK_XPATH);
		localBrowser.click(imagesLink);
	}
	
	public static void finishAlert(){
		JavascriptExecutor js =(JavascriptExecutor)localBrowser.getDriver();
        js.executeScript("alert('Hello World Test Finished')");
	}
	
}
