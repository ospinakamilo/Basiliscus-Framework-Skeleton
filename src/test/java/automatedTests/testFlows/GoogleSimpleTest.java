package automatedTests.testFlows;


import org.junit.AfterClass;
import org.junit.Test;

import automatedTests.testArchetypes.GoogleSimpleArchetype;
import basiliscus.helpers.BrowserHelper;
import basiliscus.helpers.WaitHelper;
import basiliscus.templates.FrameworkTest;



public class GoogleSimpleTest extends FrameworkTest{
	
	BrowserHelper DEFAULT_BROWSER = this.newFrameworkDriver();
	

	@Test
	public void testSearchField() throws Exception{
		GoogleSimpleArchetype.setBrowser(DEFAULT_BROWSER);
		GoogleSimpleArchetype.goToGoogle();
		WaitHelper.waitTwoSeconds();
		GoogleSimpleArchetype.searchData();
		WaitHelper.waitOneSecond();
		GoogleSimpleArchetype.clickOnImages();
		WaitHelper.waitTwoSeconds();
		GoogleSimpleArchetype.finishAlert();
		WaitHelper.waitThreeSeconds();
	}
	
	
	@AfterClass
	public static void tearDown(){
		GoogleSimpleTest.quitFrameworkDrivers();		
	}


}
