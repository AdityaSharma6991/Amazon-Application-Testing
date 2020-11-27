package screens;

import tools.Report;
import tools.ReusableUtilities;

public class SearchResultScreen extends ReusableUtilities {

	String itemId = "com.amazon.mShop.android.shopping:id/item_title";
	public String randomItemName;

	/*
	 *	Function to select a random item from the search results .
	 *	Attributes-searchResultReport- Report variable to update the proceedings in report
	 *	@author Aditya
	 */
	public void selectRandomSearchItem(Report searchResultReport) {
		randomItemName = selectRandomData("id", itemId, searchResultReport);
	}

	
	/*
	 *	Function to get name of item selected from search list
	 *	@author Aditya.
	 */
	public String getRandomItemName() {
		return randomItemName;
	}
}
