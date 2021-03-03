package com.banking.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.banking.pageObject.LoginPage;
import com.banking.utilities.ExcelUtils;
import com.banking.utilities.ReadConfig;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "loginData")
	public void login(String user, String pwd) {

		LoginPage login = new LoginPage(driver);
		login.setUserName(user);
		log.info("Entered username: " + user);

		login.setPassword(pwd);
		log.info("Entered password: " + pwd);

		login.clickLogin();
		log.info("Clicking login button");

		if (this.isAlertPresent() == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			captureScreenshot(driver, "login");
			Assert.assertTrue(false, "Invalid login credentials");
		} else {
			Assert.assertTrue(true);
			login.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			log.info("Login test passed");
		}

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@DataProvider(name = "loginData")
	public String[][] getData() {
		String filePath = ReadConfig.getPropertyValue("testDataFilePath");
		String sheetName = ReadConfig.getPropertyValue("loginSheetName");
		int rowNum = ExcelUtils.getRowCount(filePath, sheetName);
		int colNum = ExcelUtils.getCellCount(filePath, sheetName, 1);
		String credentials[][] = new String[rowNum][colNum];
		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				credentials[i - 1][j] = ExcelUtils.getCellData(filePath, sheetName, i, j);
			}
		}

		return credentials;
	}

}
