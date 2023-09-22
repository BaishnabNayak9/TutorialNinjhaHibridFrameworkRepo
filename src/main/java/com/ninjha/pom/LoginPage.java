package com.ninjha.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public final class LoginPage {
	WebDriver driver;
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	@FindBy(id="input-password")
	private WebElement passwordField;
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement clickOnLoginButoon;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailPasswordNotMatching;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public void clickOnLoginButton() {
		clickOnLoginButoon.click();
	}
	public String retrieveEmailAndPasswordNotWarningMessageText() {
		String warningText = emailPasswordNotMatching.getText();
		return warningText;
	}
}
