package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class ReusableUtilities {

	public static AndroidDriver<MobileElement> driver;
	public Properties propFile;
	
	/*
	 *  Function to initialize properties file.
	 *  @author Aditya
	 */
	public ReusableUtilities() {
		propFile=new Properties();
	}
	
	
	/*
	 *  Reusable Function to initialize/start driver.
	 *  @author Aditya
	 */
	public void driverSetup(Report extRep) {
		try {
			propFile = loadPropertyFile(System.getProperty("user.dir")+"\\src\\main\\resources\\PropertiesFiles\\capability.properties");
			DesiredCapabilities DesCap = new DesiredCapabilities();
			DesCap.setCapability(MobileCapabilityType.DEVICE_NAME, propFile.getProperty("deviceName"));
			DesCap.setCapability(MobileCapabilityType.UDID, propFile.getProperty("udid"));
			DesCap.setCapability(MobileCapabilityType.PLATFORM_NAME, propFile.getProperty("platformName"));
			DesCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, propFile.getProperty("platformVersion"));

			DesCap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, propFile.getProperty("appPackage"));
			DesCap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, propFile.getProperty("appActivity"));
			DesCap.setCapability(MobileCapabilityType.AUTOMATION_NAME, propFile.getProperty("automationName"));
			DesCap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
			

			URL url = new URL(propFile.getProperty("url"));
			driver = new AndroidDriver<MobileElement>(url, DesCap);

			System.out.println("Application Started...");
			extRep.passReporting("Driver Setup successfull");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			extRep.failReporting(e.getMessage());
			extRep.captureScreen();
			Assert.assertTrue(false, e.getMessage());
		}

	}
	
	
	/*  Function to load ".properties" file.
	 *  Attribute- path- Path of ".properties" file
	 *  @author Aditya
	 */
	public Properties loadPropertyFile(String path) throws IOException {
		try
		{
			FileInputStream fs=new FileInputStream(path);
			propFile.load(fs);
		}
		catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		return propFile;
	}

	
	/*  Function to wait for an element to be click-able.
	 *  Attributes- elementType- Element type String passed is an id or xpath
	 * 			  identifier- unique element identifier
	 * 			  extRep- Object of Report to generate extent report
	 * 	@author Aditya
	 */
	public void waitForElementToBeClickable(String elementType, String identifier, Report extRep) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			if (elementType == "id") {
				wait.until(ExpectedConditions.elementToBeClickable(By.id(identifier)));
			} else if (elementType.equalsIgnoreCase("xpath"))
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(identifier)));
		} catch (Exception e) {
			e.printStackTrace();
			extRep.failReporting(e.getMessage());
			extRep.captureScreen();
			Assert.assertTrue(false, e.getMessage());
		}
	}

	
	
	/*  Function to click on an element.
	 *	Attributes- elementType- Element type String passed is an id or xpath
	 *				identifier- unique element identifier
	 *				extRep- Object of Report to generate extent report
	 *  @author Aditya
	 */
	public void click(String elementType, String identifier, Report extRep) {
		try {
			if (elementType.equalsIgnoreCase("id"))
				driver.findElement(By.id(identifier)).click();
			else if (elementType.equalsIgnoreCase("xpath"))
				driver.findElement(By.xpath(identifier)).click();

			extRep.passReporting(identifier + " is clicked");
		} catch (Exception e) {
			e.printStackTrace();
			extRep.failReporting(e.getMessage());
			extRep.captureScreen();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	/*	Function to input data in a field and press enter
	 *	Attributes- elementType- Element type String passed is an id or xpath
	 *				identifier- unique element identifier
	 *				extRep- Object of Report to generate extent report
	 *				data- text to be entered
	 *	@author Aditya
	 */
	public void inputDataAndPressEnter(String elementType, String identifier, Report extRep, String data) {
		try {
			if (elementType.equalsIgnoreCase("id")) {
				driver.findElement(By.id(identifier)).click();
				driver.findElement(By.id(identifier)).clear();
				driver.findElement(By.id(identifier)).sendKeys(data);

				extRep.passReporting(data + " is entered in the field " + identifier);
			} else if (elementType.equalsIgnoreCase("xpath")) {
				driver.findElement(By.xpath(identifier)).click();
				driver.findElement(By.xpath(identifier)).clear();
				driver.findElement(By.xpath(identifier)).sendKeys(data);

				extRep.passReporting(data + " is entered in the field " + identifier);
			}
		} catch (Exception e) {
			e.printStackTrace();
			extRep.failReporting(e.getMessage());
			extRep.captureScreen();
			Assert.assertTrue(false, e.getMessage());
		}
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}

	
	/*	Function to select a random element from the list.
	 *	Attributes- elementType- Element type String passed is an id or xpath
	 *				identifier- unique element identifier
	 *				extRep- Object of Report to generate extent report
	 *	@author Aditya
	 */	
	public String selectRandomData(String elementType, String identifier, Report extRep) {
		MobileElement itemSelected = null;
		String selectedItem="";
		int min = 1;
		int max = 5;
		if(verifyElementPresence(identifier)) {
			max = driver.findElements(By.id(identifier)).size();
		}
		try {
			if (elementType.equalsIgnoreCase("id")) {
				itemSelected = driver.findElements(By.id(identifier)).get((int)(Math.random() * (max - min) + min));
				extRep.passReporting("Random option is selected");
			} else if (elementType.equalsIgnoreCase("xpath")) {
				itemSelected = driver.findElements(By.xpath(identifier)).get((int)(Math.random() * (max - min) + min));
				extRep.passReporting("Random option is selected");
			}
			selectedItem=itemSelected.getText();
			itemSelected.click();
		} catch (Exception e) {
			e.printStackTrace();
			extRep.failReporting(e.getMessage());
			extRep.captureScreen();
			Assert.assertTrue(false, e.getMessage());
		}
		return selectedItem;
	}

	
	/*	Function to fetch text from an element.
	 *	Attributes- elementType- Element type String passed is an id or xpath
	 *				identifier- unique element identifier
	 *				extRep- Object of Report to generate extent report
	 *	@author Aditya
	 */
	public String getText(String elementType, String identifier, Report extRep) {

		String text = "";
		try {
			if (elementType.equalsIgnoreCase("id"))
				text = driver.findElement(By.id(identifier)).getText();
			else if (elementType.equalsIgnoreCase("xpath"))
				text = driver.findElement(By.xpath(identifier)).getText();

			extRep.passReporting(text + " is retrieved from " + identifier);
		} catch (Exception e) {
			e.printStackTrace();
			extRep.failReporting(e.getMessage());
			extRep.captureScreen();
			Assert.assertTrue(false, e.getMessage());
		}
		return text;
	}
	
	/* Function to verify if an element is present on the screen
	 * Attribute: property- id value of the element to be identified
	 * @author Aditya
	 */
	public boolean verifyElementPresence(String property) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean presence = true;
		try {
			driver.findElement(By.id(property));
			return presence;
		} catch (Exception e) {
			presence = false;
			e.printStackTrace();
			return presence;
		}
	}
//	
//	
//	/*
//	 * Function to scroll and check an element is present in UI.
//	 * Attribute: property- id value of the element to be identified
//	 *	@author Aditya
//	 */
//	public void swipeToElement(String property) {
//		int count=0;
//		while (true) {
//			if (verifyElementPresence(property)) {
//				break;
//			}
////			swipeFullFromTopToBottom();
//			count++;
//			if(count>10)
//				Assert.assertTrue(false, "Element Not Visible");
//		}
//	}
//	
	
//	/*
//	 * Function to scroll and check an element is present in UI
//	 * Attribute: property- xpath value of the element to be identified
//	 * @author Aditya
//	 */
//	@SuppressWarnings("rawtypes")
//	public void swipeFullFromTopToBottom() {
//		try {
//			Thread.sleep(2000);
//			Dimension scrnSize = driver.manage().window().getSize();
//			int startx = (int) (scrnSize.width / 2);
//			int endy = (int) (scrnSize.height*0.9);
//			int starty = (int) (scrnSize.height * 0.2);
//
//
//			new TouchAction(driver).press(startx, endy).moveTo(startx,starty).release().perform();
//
//		} catch (InterruptedException e) {
//			Assert.assertTrue(false,e.getMessage());
//		}
//	}
	
	
	/*
	 *  Reusable function to compare 2 strings
	 *	Attribute: 	smallString-String value to be compared with
	 *				bigString- String value to be compared in
	 *				extReport- Class object of the Reporting class for generating extent report
	 *	@author Aditya
	 */
	public void stringContains(String smallString,String bigString,Report extRep)
	{
		if(bigString.contains(smallString))
		{
			Assert.assertTrue(true, "String comparison Successful");
			extRep.passReporting("String compared successfully");
		}
		else
		{
			Assert.assertTrue(false, "String comparison Failed expected:"+bigString+" contains "+smallString );
			extRep.failReporting("String comparison failed");
			extRep.captureScreen();
		}
	}
}
