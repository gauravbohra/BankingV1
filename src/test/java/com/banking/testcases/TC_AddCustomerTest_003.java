package com.banking.testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.banking.pageObject.AddCustomerPage;
import com.banking.pageObject.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() {

		LoginPage login = new LoginPage(driver);

		login.setUserName(userName);
		login.setPassword(password);
		login.clickLogin();
		log.info("User logged in :" + userName);

		AddCustomerPage addCustomer = new AddCustomerPage(driver);

		addCustomer.clickAddNewCustomer();
		addCustomer.enterCustomerName("Gaurav");
		addCustomer.enterDateOfBirth("18-10-1991");
		addCustomer.enterAddress("House Num 52 Thwalkera");
		addCustomer.enterCityName("Tanakpur");
		addCustomer.enterStateName("Uttarakhand");
		addCustomer.enterPIN("262309");
		addCustomer.enterEmailId(this.generateEmailID());
		addCustomer.enterMobileNum("7043243232");
		addCustomer.enterPassword("LearnFramework");
		addCustomer.clickSumbit();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean result = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if (result == true) {
			Assert.assertTrue(true);
			log.info("Customer registered successfully.");
		} else {
			captureScreenshot(driver, "addNewCustomer");
			Assert.assertTrue("Custmoer not registered", false);
		}
	}

	public String generateEmailID() {
		String generatedString = RandomStringUtils.randomAlphabetic(10);
		String emailID = generatedString + "@gmail.com";
		return emailID;
	}
	
	public static String randomNumber() {
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
}
