package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	private static Logger log = Logger.getLogger(TestBase.class);
	public static WebDriver driver = null;
	public static WebDriverWait wait = null;

	public static String readProperty(String key) throws IOException {
		log.debug("createObject for properties class");
		Properties prop = new Properties();

		log.debug("read the properties file");
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");

		log.debug("load the properties");
		prop.load(fis);
		log.debug("read the given property value");
		return prop.getProperty(key);
	}

	@BeforeSuite
	public void setup() throws IOException {
		log.debug("fetch the browser value:");
		String browser = readProperty("browser");
		log.debug("launching the browser : " + browser);
		if (browser.equalsIgnoreCase("firefox")) {
			log.debug("launching firefox browser:");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			log.debug("launching chrome browser:");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			log.debug("launching ie browser:");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			log.debug("launching edge browser:");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		log.debug("launching application url :" + readProperty("url"));
		driver.get(readProperty("url"));
		log.debug("add implicitwait time:");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(readProperty("implicitWait")), TimeUnit.SECONDS);
		log.debug("create object for WebDriverWait class -- explicitwait setting");
		wait = new WebDriverWait(driver, Long.parseLong(readProperty("explicitWait")));

	}

	@AfterSuite
	public void tearDown() {

		try {
			log.debug("closing the browser -- after all my testcases execution ");
		} finally {
			if (driver != null) {
				driver.close();
			}
		}
	}

}
