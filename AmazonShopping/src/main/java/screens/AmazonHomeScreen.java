package screens;

import tools.Report;
import tools.ReusableUtilities;

public class AmazonHomeScreen extends ReusableUtilities{
	String searchTextBox="com.amazon.mShop.android.shopping:id/rs_search_src_text";
	
/*  Function to search for an element and click enter.
 *  Attributes- searchReport- Report variable to update the proceedings in report
 *  @author Aditya
 */
	public void searchItem(Report searchReport, String productToSearch) {
		waitForElementToBeClickable("id", searchTextBox, searchReport);
		click("id", searchTextBox, searchReport);
		inputDataAndPressEnter("id", searchTextBox, searchReport, productToSearch);
	}
	
}
