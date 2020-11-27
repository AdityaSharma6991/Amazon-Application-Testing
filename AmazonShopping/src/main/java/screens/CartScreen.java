package screens;

import tools.Report;
import tools.ReusableUtilities;

public class CartScreen extends ReusableUtilities{
	
	String productName="(//*[@resource-id='activeCartViewForm']//*[@class='android.widget.TextView'])[1]";
	
	/*
	 * Function to get details of product in cart
	 * Attributes- cartReport- Report variable to update the proceedings in report
	 */
	public String getCartScreenProductDetails(Report cartReport) {
		String productInCart=getText("xpath",productName, cartReport);
		return productInCart.substring(0, productInCart.length() - 3);
	}
}
