package com.ninjha.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginOPtion;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	public void selectLoginOption() {
		loginOPtion.click();
	}

}
