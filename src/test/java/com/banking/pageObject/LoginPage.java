package com.banking.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;

	WebDriverWait wait;

	JavascriptExecutor jsx;

	@FindBy(how = How.NAME, using = "uid") // Syntax 1
	private WebElement inputUserID;

	@FindBy(name = "password") // Syntax 2
	private WebElement inputPassword;

	@FindBy(how = How.NAME, using = "btnLogin")
	private WebElement loginButton;

	@FindBy(name = "btnReset")
	private WebElement resetButton;

	@FindBy(xpath = "//a[text()='Log out']")
	private WebElement logoutButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, 30);
		this.jsx = (JavascriptExecutor) driver;
	}

	public void setUserName(String userName) {
		inputUserID.sendKeys(userName);
	}

	public void setPassword(String password) {
		inputPassword.sendKeys(password);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public void clickLogout() {
		jsx.executeScript("arguments[0].scrollIntoView(true);", logoutButton);
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		logoutButton.click();
	}

}
