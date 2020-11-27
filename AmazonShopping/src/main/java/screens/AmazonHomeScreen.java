package screens;

import java.io.IOException;

import org.testng.Assert;

import tools.Report;
import tools.ReusableUtilities;

public class AmazonHomeScreen extends ReusableUtilities{
	String searchTextBox="com.amazon.mShop.android.shopping:id/rs_search_src_text";
	
/* Function to search for an element and click enter
 * Attributes- searchReport- Report variable to update the proceedings in report
 */
	public void searchItem(Report searchReport) {
		try {
			propFile = loadPropertyFile(System.getProperty("user.dir")+"\\src\\main\\resources\\PropertiesFiles\\TestData.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		waitForElementToBeClickable("id", searchTextBox, searchReport);
		click("id", searchTextBox, searchReport);
		inputDataAndPressEnter("id", searchTextBox, searchReport, propFile.getProperty("ItemToSearch"));
	}
	
}
