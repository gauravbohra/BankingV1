package com.banking.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCustomerPage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jsx;

	@FindBy(xpath = "//a[text()='New Customer']")
	private WebElement addNewCustomerLink;

	@FindBy(how = How.XPATH, using = "//label[@id='message']//preceding::input")
	private WebElement inputCustomerName;

	@FindBy(how = How.XPATH, using = "//input[@value='m']")
	private WebElement maleRadioButton;

	@FindBy(how = How.XPATH, using = "//input[@value='f']")
	private WebElement femaleRadioButton;
	
	@FindBy(xpath="//input[@name='rad1']")
	private List<WebElement> genderRadioButton;

	@FindBy(id = "dob")
	private WebElement inputDateOfBirth;

	@FindBy(name = "addr")
	private WebElement inputAddress;

	@FindBy(name = "city")
	private WebElement inputCity;

	@FindBy(name = "state")
	private WebElement inputState;

	@FindBy(xpath = "//input[@name='pinno']")
	private WebElement inputPinNum;

	@FindBy(name = "telephoneno")
	private WebElement inputMobileNum;

	@FindBy(name = "emailid")
	private WebElement inputEmailId;

	@FindBy(how = How.NAME, using = "password")
	private WebElement inputPassword;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//input[@value='Reset']")
	private WebElement resetButton;

	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		jsx = (JavascriptExecutor) driver;
	}

	public void clickAddNewCustomer() {
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='New Customer']")));
		jsx.executeScript("arguments[0].scrollIntoView();", addNewCustomerLink);
		wait.until(ExpectedConditions.elementToBeClickable(addNewCustomerLink));
		addNewCustomerLink.click();
	}

	public void enterCustomerName(String name) {
		inputCustomerName.sendKeys(name);
	}

	public void selectMaleGender() {
		jsx.executeScript("arguments[0].click();", maleRadioButton);
	}

	public void selectFemaleGender() {
		jsx.executeScript("arguments[0].click()", femaleRadioButton);
	}
	

	public void enterDateOfBirth(String dateOfBirth) {
		String date = dateOfBirth.split("-")[0];
		String month = dateOfBirth.split("-")[1];
		String year = dateOfBirth.split("-")[2];

		inputDateOfBirth.sendKeys(date);
		inputDateOfBirth.sendKeys(month);
		inputDateOfBirth.sendKeys(year);
	}

	public void enterAddress(String address) {
		inputAddress.sendKeys(address);
	}

	public void enterCityName(String city) {
		inputCity.sendKeys(city);
	}

	public void enterStateName(String state) {
		inputState.sendKeys(state);
	}

	public void enterPIN(String pin) {
		inputPinNum.sendKeys(pin);
	}

	public void enterMobileNum(String mobileNum) {
		inputMobileNum.sendKeys(mobileNum);
	}

	public void enterEmailId(String emailId) {
		inputEmailId.sendKeys(emailId);
	}

	public void enterPassword(String password) {
//		jsx.executeScript("document.getElementsByName('password').value='" + password + "'");
		inputPassword.sendKeys(password);
	}

	public void clickSumbit() {
		submitButton.click();
	}

	public void clickReset() {
		resetButton.click();
	}

}
