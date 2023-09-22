package com.ninja.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateExtentReport(){
		ExtentReports extentReport=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Ninjha Test Automation Results Report");
		sparkReporter.config().setDocumentTitle("Ninjha Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		Properties confligProp=new Properties();
		try {
		FileInputStream fis=new FileInputStream("C:\\Users\\Hp\\eclipse-workspace\\vishnu\\NinjhaProJ\\src\\main\\java\\com\\ninjha\\conflig\\conflig.properties");
		confligProp.load(fis);
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL",confligProp.getProperty("url"));
		extentReport.setSystemInfo("BrowserName",confligProp.getProperty("browser"));
		extentReport.setSystemInfo("Email", confligProp.getProperty("validEmail"));
		extentReport.setSystemInfo("password", confligProp.getProperty("validPassword"));
		
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", System.getenv("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		return extentReport;
	
	}
	

}
