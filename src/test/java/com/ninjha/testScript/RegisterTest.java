package com.ninjha.testScript;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninja.util.Utilities;
import com.ninjha.baseClass.Base;

public class RegisterTest extends Base {
	public RegisterTest() {
		super();
	}
	public WebDriver driver;
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@BeforeMethod
	public void setup() {
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryField() {
		driver.findElement(By.id("input-firstname")).sendKeys("Baishnab");
		driver.findElement(By.id("input-lastname")).sendKeys("Nayak");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567891");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertTrue(actualSuccessHeading.contains("Your Account Has Been Created!"),"Account success page is not displayed");
	}
	@Test(priority = 2)
	public void rigistringAccountByProvindingAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys("Baishnab");
		driver.findElement(By.id("input-lastname")).sendKeys("Nayak");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567891");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertTrue(actualSuccessHeading.contains("Your Account Has Been Created!"),"Account success page is not displayed");
		driver.quit();
	}
	@Test(priority = 3)
	public void verifyRegistringAccoutwithExistingEmailAddress() {
		driver.findElement(By.id("input-firstname")).sendKeys("Baishnab");
		driver.findElement(By.id("input-lastname")).sendKeys("Nayak");
		driver.findElement(By.id("input-email")).sendKeys("vishnunayak969@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567891");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualWarning=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),"Email Allreay existing page is not displayed");
		
	}
	@Test(priority = 4)
	public void verifyRegistringAccountwithoutFillingAnyDetails() {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualPrivacyPolicyWarning=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"));
		String actualFirstNameWaring=driver.findElement(By.xpath("//div[text()='First Name must be between 1 and 32 characters!']")).getText();
		Assert.assertTrue(actualFirstNameWaring.contains("First Name must be between 1 and 32 characters!"),"First Name must be between 1 and 32 characters! is not displayed");
		String actualLastNameWarning=driver.findElement(By.xpath("//div[text()='Last Name must be between 1 and 32 characters!']")).getText();
		Assert.assertTrue(actualLastNameWarning.contains("Last Name must be between 1 and 32 characters!"),"First Name must be between 1 and 32 characters! is not displayed");
		String actualEmainWarning=driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText();
		Assert.assertTrue(actualEmainWarning.contains("E-Mail Address does not appear to be valid!"),"E-Mail Address does not appear to be valid! is not displayed");
		String actualTelephoneWarning=driver.findElement(By.xpath("//div[text()='Telephone must be between 3 and 32 characters!']")).getText();
		Assert.assertTrue(actualTelephoneWarning.contains("Telephone must be between 3 and 32 characters!"),"Telephone must be between 3 and 32 characters! is not displaye");
		String actualPasswordWarning=driver.findElement(By.xpath("//div[text()='Password must be between 4 and 20 characters!']")).getText();
		Assert.assertTrue(actualPasswordWarning.contains("Password must be between 4 and 20 characters!"),"Password must be between 4 and 20 characters! is not displayed");
		
	}

}
