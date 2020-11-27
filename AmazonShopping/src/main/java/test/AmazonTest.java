/**
 * 
 */
package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import screens.AmazonHomeScreen;
import screens.CartScreen;
import screens.SearchResultScreen;
import screens.SelectedProductScreen;
import screens.SignInScreen;
import tools.ReadTestData;
import tools.Report;
import tools.ReusableUtilities;

/**
 *  @author Aditya Sharma 
 *  Device id's- (RZ8MA27XANN, 192.168.1.12:5555, 100.74.174.62:5555)
 *
 */
public class AmazonTest extends ReusableUtilities {
	public Report extentReport;
	public WebDriverWait wait;
	public ReadTestData testData;

	
	/*
	 * Constructor variables of current and parent class.
	 * @author Aditya
	 */
	public AmazonTest() {
		super();
	}

	
	/*
	 *  Before method to initialize drivers and reporting in before test.
	 *  @author Aditya
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		// Starting reporting for test
		extentReport = new Report();
		extentReport.extentReportInit();
		testData= new ReadTestData();
		System.out.println(testData.getData("TestName") + " Started");
		extentReport.logger = extentReport.report.createTest(testData.getData("TestName"));

		driverSetup(extentReport);
	}

	
	/* 
	 *  Test method to open and test Amazon shopping application.
	 *  @author Aditya
	 */
	@Test
	public void amazonTest() {
		 
		new SignInScreen().signInPage(extentReport);
		new AmazonHomeScreen().searchItem(extentReport,testData.getData("ItemToSearch"));
		SearchResultScreen resultScreen = new SearchResultScreen();
		resultScreen.selectRandomSearchItem(extentReport);
		String searchListItem = resultScreen.getRandomItemName();
		
//		to check the test for failure un-comment the line below
//		searchListItem = "Data to check failure";
		
		new SelectedProductScreen().addProductToCart(extentReport,testData.getData("Pin-code"));
		new SelectedProductScreen().navigateToCart(extentReport);
		new CartScreen().compareCartScreenProductDetails(searchListItem, extentReport);;
	}

	
	/*
	 *  After Method to tear down the driver and check execution status.
	 *  Attribute:result - ITestResult object to get overall execution status
	 *  @author Aditya
	 */
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus())
			extentReport.failReporting(result.getThrowable().getMessage());
		driver.quit();
		extentReport.report.flush();
		System.out.println(testData.getData("TestName") + " Ended");
	}
}
