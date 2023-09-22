package com.ninjha.baseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.ninja.util.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public Base() {
		try {
		FileInputStream fis=new FileInputStream("C:\\Users\\Hp\\eclipse-workspace\\vishnu\\NinjhaProJ\\src\\main\\java\\com\\ninjha\\conflig\\conflig.properties");
		prop=new Properties();
		prop.load(fis);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis2=new FileInputStream("C:\\Users\\Hp\\eclipse-workspace\\vishnu\\NinjhaProJ\\src\\main\\java\\com\\ninjha\\testData\\testData.properties");
			dataProp=new Properties();
			dataProp.load(fis2);
		}
		catch(IOException e) {
			e.printStackTrace();

		}

	}

	public WebDriver intializeBrowserAndOpenApplicationURL(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions option=new ChromeOptions();
			option.setBrowserVersion("116");
			//option.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(option);
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			driver=new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.Implicit_Wait_Time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.Page_Load_Time));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
