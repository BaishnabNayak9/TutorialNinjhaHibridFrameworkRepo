package com.ninjha.testScript;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ninja.util.Utilities;
import com.ninjha.baseClass.Base;
import com.ninjha.pom.AccountPage;
import com.ninjha.pom.HomePage;
import com.ninjha.pom.LoginPage;

public class LoginTest extends Base{
	HomePage homepage;
	LoginPage loginPage;
	AccountPage accountPage;
	public LoginTest() {
		super();
	}
	public WebDriver driver;
	@BeforeMethod
	public void setup() { 
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		/*driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();*/
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority = 1,dataProvider = "validCredentialSupplyer")
	public void verifyWithValidCredetials(String email,String password) {
		/*driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));*/
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		accountPage=new AccountPage(driver);
		Assert.assertTrue((accountPage.getDisplayStatusOfYourAccountInformationOption()),"Edit your Account information option is not dispayed");
	}
	@DataProvider(name="validCredentialSupplyer")
	public Object[][] supplyTestData() {
		Object[][] data= {{"baishnabnayak969@gmail.com","96924840"},//  we did not call excel test data
					{"vishnunayak969@gmail.com","96924840"},
					{"vishnunayak969@gmail.com","96924840"}};
				
		return data;
	}
	@Test(priority = 2)
	public void loginWithInvalidCredetials() {
		loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
				
		String actualWarningMessage = loginPage.retrieveEmailAndPasswordNotWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "expected message is not displayed");
		
	}
	@Test(priority = 3)
	public void verifyWithInvalidEmailAndValidPassord() {
		loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retrieveEmailAndPasswordNotWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "expected message is not displayed");
		
	}
	@Test(priority = 4)
	public void verifyValidEmailAndInvalidPassword() {
		loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retrieveEmailAndPasswordNotWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "expected message is not displayed");
		
	}
	@Test(priority = 5)
	public void verifyLoginWithoutProvindingCredential() {
		loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress("");
		loginPage.enterPassword("");
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retrieveEmailAndPasswordNotWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "expected message is not displayed");
		
	}

}
