package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

public class ReadTestData {

	Properties testDataProperties;
	/*
	 *	Function to load test data from properties file
	 */
	public ReadTestData() {
		testDataProperties= new Properties();
		String path=System.getProperty("user.dir")+"\\src\\main\\resources\\TestDataFiles\\AmazonShopping_TestCaseData\\TestData.properties";;
		try
		{
			FileInputStream fs=new FileInputStream(path);
			testDataProperties.load(fs);
		}
		catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public String getData(String data) {
		return testDataProperties.getProperty(data);
	}
}
