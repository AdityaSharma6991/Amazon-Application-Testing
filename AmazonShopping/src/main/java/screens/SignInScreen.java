package screens;

import tools.Report;
import tools.ReusableUtilities;

public class SignInScreen extends ReusableUtilities {
//	String signInText = "com.amazon.mShop.android.shopping:id/signin_to_yourAccount";
	String skipSignInButton = "com.amazon.mShop.android.shopping:id/skip_sign_in_button";

/*	Function to click on skip sign in button.
 *	Attributes- signInReport- Report variable to update the proceedings in report
 * 	@author Aditya
 */
	public void signInPage(Report signInReport) {
		waitForElementToBeClickable("id", skipSignInButton,signInReport);
		signInReport.passReporting("Login Page is displayed");
		click("id", skipSignInButton, signInReport);
	}
}
 