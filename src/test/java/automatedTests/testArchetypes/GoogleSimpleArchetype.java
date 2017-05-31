package automatedTests.testArchetypes;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import automatedTests.testData.GoogleSimpleData;
import basiliscus.templates.FrameworkArchetype;

public class GoogleSimpleArchetype extends FrameworkArchetype{
	
	private static final String SEARCH_INPUT_ID = "lst-ib";
	private static final String IMAGES_LINK_XPATH = "//*[@id='hdtb-msb-vis']/div[2]/a";
	
	
	public static void goToGoogle(){
		localBrowser.loadUrl("https://www.google.com.co/?gws_rd=ssl");
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
		try {
			JavascriptExecutor js =(JavascriptExecutor)localBrowser.getDriver();
			js.executeScript("window.hola ='Hello World';");
			js.executeScript("alert(hola)");
		} catch (WebDriverException e) {
			// TODO: handle exception
		}
	}
	
}
