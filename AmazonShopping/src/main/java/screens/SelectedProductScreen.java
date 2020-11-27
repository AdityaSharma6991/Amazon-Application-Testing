package screens;

import io.appium.java_client.MobileBy;
import tools.Report;
import tools.ReusableUtilities;

public class SelectedProductScreen extends ReusableUtilities {
	String enterPincodeButton = "com.amazon.mShop.android.shopping:id/loc_ux_gps_enter_pincode";
	String pinCodeTextbox = "com.amazon.mShop.android.shopping:id/loc_ux_pin_code_text_pt1";
	String applyPinCodeButton = "com.amazon.mShop.android.shopping:id/loc_ux_update_pin_code";
	String cartButton= "com.amazon.mShop.android.shopping:id/action_bar_cart_image";

	/*
	 *  Function to add product to cart .
	 *  Attributes- productReport- Report variable to update the proceedings in report
	 *	@author Aditya
	 */
	public void addProductToCart(Report productReport, String pinCode) {
		if(verifyElementPresence(enterPincodeButton)) {
			waitForElementToBeClickable("id", enterPincodeButton, productReport);
			click("id", enterPincodeButton, productReport);
			waitForElementToBeClickable("id", pinCodeTextbox, productReport);
			inputDataAndPressEnter("id", pinCodeTextbox, productReport, pinCode);
			click("id", applyPinCodeButton, productReport);
		}

		//scrolling to "Add to cart" button and clicking when found using uiAutomator
		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().className(\"android.view.View\")).getChildByText("
						+ "new UiSelector().resourceId(\"add-to-cart-button\"), \"Add to Cart\")"))
				.click();
	}
	
	
	/*
	 *  Function to navigate to cart .
	 *  Attributes- productReport- Report variable to update the proceedings in report
	 *	@author Aditya
	 */
	public void navigateToCart(Report productReport) {
		click("id", cartButton, productReport);
	}
}
