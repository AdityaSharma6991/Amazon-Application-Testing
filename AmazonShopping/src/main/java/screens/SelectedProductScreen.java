package screens;

import java.io.IOException;

import org.testng.Assert;

import io.appium.java_client.MobileBy;
import tools.Report;
import tools.ReusableUtilities;

public class SelectedProductScreen extends ReusableUtilities {
	String addToCartButton = "add-to-cart-button";
	String enterPincodeButton = "com.amazon.mShop.android.shopping:id/loc_ux_gps_enter_pincode";
	String pinCodeTextbox = "com.amazon.mShop.android.shopping:id/loc_ux_pin_code_text_pt1";
	String applyPinCodeButton = "com.amazon.mShop.android.shopping:id/loc_ux_update_pin_code";
	String cartButton= "com.amazon.mShop.android.shopping:id/action_bar_cart_image";

	/*
	 * Function to add product to cart 
	 * Attributes- productReport- Report variable to update the proceedings in report
	 */
	public void addProductToCart(Report productReport) {
		try {
			propFile = loadPropertyFile(
					System.getProperty("user.dir") + "\\src\\main\\resources\\PropertiesFiles\\TestData.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		waitForElementToBeClickable("id", enterPincodeButton, productReport);
		click("id", enterPincodeButton, productReport);
		waitForElementToBeClickable("id", pinCodeTextbox, productReport);
		inputDataAndPressEnter("id", pinCodeTextbox, productReport, propFile.getProperty("Pin-code"));
		click("id", applyPinCodeButton, productReport);

		//scrolling to Add to cart button
		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().className(\"android.view.View\")).getChildByText("
						+ "new UiSelector().resourceId(\"add-to-cart-button\"), \"Add to Cart\")"))
				.click();
	}
	
	public void navigateToCart(Report productReport) {
		click("id", cartButton, productReport);
	}
}
