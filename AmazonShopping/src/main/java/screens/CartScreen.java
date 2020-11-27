package screens;

import tools.Report;
import tools.ReusableUtilities;

public class CartScreen extends ReusableUtilities {

	String productName = "(//*[@resource-id='activeCartViewForm']//*[@class='android.widget.TextView'])[1]";

	/*
	 * Function to compare details of product in cart. 
	 * Attributes-	cartReport-Report variable to update the proceedings in report
	 *				searchListItem- String text of item selected from search list 				
	 * @author Aditya
	 */
	public void compareCartScreenProductDetails(String searchListItem, Report cartReport) {
		String productInCart = getText("xpath", productName, cartReport);
		stringContains(productInCart.substring(0, productInCart.length() - 3), searchListItem, cartReport);
	}
}
