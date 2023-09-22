package com.ninjha.testScript;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninjha.baseClass.Base;
// updated comments
public class SearchTest extends Base{
	public SearchTest() {
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority = 1)
	public void searchingWithBalidProduct() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"valid product HP not displayed");
	}
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		String actualSearchMessage=driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).getText();
		Assert.assertTrue(actualSearchMessage.contains("Thered is no product that matches the search criteria."),"There is no product that matches the search criteria is not displayed");
	}
	@Test(priority = 3,dependsOnMethods = {"searchingWithBalidProduct","verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		String actualSearchMessage=driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).getText();
		Assert.assertTrue(actualSearchMessage.contains("There is no product that matches the search criteria."),"There is no product that matches the search criteria is not displayed");
		
	}

}
