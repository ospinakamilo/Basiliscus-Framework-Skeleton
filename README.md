# Basiliscus Framework Skeleton
This is a Functional Web Testing Java Framework built on top of Selenium, that helps keeping in an organized way Functional Test Flows, Reusable Methods and Data, while providing a set of own methods to make a little bit easier working with Selenium.

Explanation Video and Demo in Spanish: https://www.youtube.com/watch?v=YB55yULiY1I

> **Installation:**
> - First of all start by cloning or downloading the skeleton of the project.   
```sh
git clone  https://github.com/ospinakamilo/BasiliscusFrameworkSkeleton.git
```
or
```sh
wget https://github.com/ospinakamilo/BasiliscusFrameworkSkeleton/archive/master.zip
```

> - Now import as Maven project on your favorite IDE.
> - To start using the framework you must first add "basiliscus.jar" dependency to your project. You can achieve this in 3 different ways:

>>1. The first way is to add the jar located at "/src/test/resources/Jars" to the build path of the project
>>2. Another option is to edit the POM.xml to add an external dependency as follows (This way comes preconfigured in the skeleton project):
>> 
    ```xml
        <dependency>
            <groupId>basiliscus</groupId>
            <artifactId>basiliscus</artifactId>
             <scope>system</scope>
             <version>1.0.1</version>
             <systemPath>${basedir}/src/test/resources/Jars/basiliscus.jar</systemPath>
        </dependency> 
    ```
>>3. The last option, and the recommended one, is to install the dependency on your local Maven repository and then add the dependency to the POM.xml

>>> To install the jar in your local Maven repository run:
>>
```sh
mvn install:install-file -Dfile=CompletePathTo/src/test/resources/Jars/basiliscus.jar -DgroupId=basiliscus -DartifactId=basiliscus -Dversion=1.0.1 -Dpackaging=jar -DgeneratePom=true
```
>>>After installing the dependency include it in the POM.xml as follows:
>>>
```xml
<dependency>
    <groupId>basiliscus</groupId>
    <artifactId>basiliscus</artifactId>
    <version>1.0.1</version>
</dependency>
```

#### Using the Framework
The structure of the framework is pretty plain, divided in 3 layers that allow a much easier reuse of methods, data and tests.

```c
src/test/java/automatedTests	
|  #Class to configure some pararameters.
│	SettingsParameters.java
|	
└───testArchetypes
│  #Classes with the Selenium Methods used on the test flows.			
│   │   GoogleArchetype.java
│   │   OutlookArchetype.java
│   │   XXXXArchetype...
│   
└───testData
│  #Classes containing the Data used on the Archetypes Methods.
│   │  UsersData.java
│   │  FormsData.java
│   │  XXXXData...
│   
└───testFlows
   #Workflows that give an order to the methods of the archetypes.
    │  GoogleAcceptanceTest.java
    │  OutlookSmokeTest.java
```

##### Framework communication layers and purpose
Although there is nothing that establishes how layers communicate between each other, a good practice is that each layer communicates in the next way:
```c

+--------------+	# Test Flows should only interact with Archetypes
|              |	# giving a sequence in wich the methods should be
|  Test Flows  |	# executed. This creates test flows with methods
|              |	# that can be reused as many times as needed.
+------+-------+	# Note: An Archetype method can be used in many TestFlows.
       |
       v
+--------------+	# Test Archetypes should only interact with Data.
|    Test      |	# Archetypes are the abstraction of Entities, Pages or
|  Archetypes  |	# Functionalities that can be represented through 
|              |	# a group of methods.
+------+-------+	
       |
       v
+--------------+	# The Test Data layer is where data for the Archetypes
|              |	# is provided or stored. As some ideas for this layer you
|  Test Data   |	# could use: properties files, SQL DAOS and DTOS or simply
|              |	# have your data written on Java classes.
+--------------+	
```

##### Examples

###### SettingsParameters.java
```java
package automatedTests;

import basiliscus.values.*;


/**
 * This class is used to set the default parameters of the framework.
 * e.g: The Default Browser, Resolution, Implicit wait time, and anything else
 * you consider parameterizable on your tests .
 */
public class SettingsParameters{
	
	
	public static Resolution RESOLUTION = Resolution.DESKTOP_LARGE; //Default Resolution to use unless parameters are given.
	public static Driver DRIVER = Driver.CHROME; //Default driver to use, unless parameters are given.
	public static Integer IMPLICIT_WAIT_SECONDS=7; //Time to wait till page loads
	
	

}

```

###### GoogleSimpleData.java
```java
package automatedTests.testData;


public class GoogleSimpleData{
	
	public static final  String SEARCH_DATA = "Hello World";

}
```

###### GoogleSimpleArchetype.java
```java
package automatedTests.testArchetypes;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import automatedTests.testData.GoogleSimpleData;
import basiliscus.helpers.BrowserHelper;

public class GoogleSimpleArchetype{
	
	private static final String SEARCH_INPUT_ID = "lst-ib";
	private static final String IMAGES_LINK_XPATH = "//*[@id='hdtb-msb']/div[2]";
	
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
```

###### GoogleSimpleTest.java
```java
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
```
