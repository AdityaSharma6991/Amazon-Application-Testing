package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import test.AmazonTest;

public class Report extends AmazonTest {
	public ExtentHtmlReporter extentReport;
	public ExtentReports report;
	public ExtentTest logger;

	/*
	 * Function to initialize/start reporting variables of current and parent class.
	 * @author Aditya
	 */
	public void extentReportInit() {
		extentReport = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/TestResult.html");
		report = new ExtentReports();
		report.attachReporter(extentReport);
		System.out.println("Reporting started...");
	}

	/*
	 * Function to Capture Screenshot.
	 * @author Aditya
	 */
	public String captureScreen() {
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenShotDestination = null;
		FileInputStream fileInputStreamReader = null;
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			fileInputStreamReader = new FileInputStream(sourceFile);
			byte[] bytes = new byte[(int) sourceFile.length()];
			fileInputStreamReader.read(bytes);
			screenShotDestination = System.getProperty("user.dir")+ "/Reports/screenshots/" + dateName + ".jpeg";
			File destination = new File(screenShotDestination);
			FileUtils.copyFile(sourceFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShotDestination;
	}

	/*
	 * Function to Add passed step in report .
	 * Attribute; desc- Description to be printed in the report
	 * @author Aditya
	 */

	public void passReporting(String desc) {
		String tempSS = new Report().captureScreen();
		try {
			this.logger.pass(desc, MediaEntityBuilder.createScreenCaptureFromPath(tempSS).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Function to Add Failed step in report .
	 * Attribute; desc- Description to be printed in the report
	 * @author Aditya
	 */
 
	public void failReporting(String desc) {
		String tempSS = new Report().captureScreen();
		try {
			this.logger.fail(desc, MediaEntityBuilder.createScreenCaptureFromPath(tempSS).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
