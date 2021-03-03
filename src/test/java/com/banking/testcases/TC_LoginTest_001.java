package com.banking.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() {

		driver.get(baseURL);
		log.info("Opening URL");

		LoginPage login = new LoginPage(driver);
		login.setUserName(userName);
		log.info("Entered username: " + userName);

		login.setPassword(password);
		log.info("Entered password: " + password);

		login.clickLogin();

		if (driver.getTitle().trim().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			log.info("Login test passed");
		} else {
			BaseClass.captureScreenshot(driver, "loginTest");
			Assert.assertTrue(false);
		}

	}
}
