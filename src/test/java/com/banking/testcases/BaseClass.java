package com.banking.testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.banking.utilities.Constants;
import com.banking.utilities.ReadConfig;

public class BaseClass {

	public String baseURL = ReadConfig.getPropertyValue(Constants.BASE_URL);
	public String userName = ReadConfig.getPropertyValue(Constants.USERNAME);
	public String password = ReadConfig.getPropertyValue(Constants.PASSWORD);
	public static WebDriver driver;
	public static Logger log;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {

		switch (browser.toLowerCase()) {

		case "chrome":
			System.setProperty("webdriver.chrome.driver", ReadConfig.getChromeDriverPath());
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", ReadConfig.getGeckoDriverPath());
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "ie":
			System.setProperty("webdriver.ie.driver", ReadConfig.getIEDriverPath());
//			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//			ieOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			driver = new InternetExplorerDriver();
			break;

		default:
			Assert.assertFalse(false, "Invalid browser provided");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		log = Logger.getLogger(getClass());
		PropertyConfigurator.configure("log4j.properties");
		driver.get(baseURL);
		log.info("Opening URL");

	}

	@AfterClass
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	public static void captureScreenshot(WebDriver driver, String tName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		try {
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);
			File targetFile = new File(
					System.getProperty("user.dir") + ReadConfig.getPropertyValue(Constants.SCREENSHOT_PATH) + tName + ".png");
			FileUtils.copyFile(sourceFile, targetFile);
			log.info("Screenshot captured");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
