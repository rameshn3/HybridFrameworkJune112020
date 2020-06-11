package com.qa.linkedin.test;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkedinHomePage;
import com.qa.linkedin.page.LinkedinLoggedinPage;
import com.qa.linkedin.page.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class SearchDDTest extends TestBase {
	private Logger log = Logger.getLogger(SearchDDTest.class);
	private LinkedinHomePage lhmPg = null;
	private LinkedinLoggedinPage llPg = null;
	private SearchResultsPage srPg = null;
	private String excelpath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\com\\qa\\linkedin\\data\\LinkedinTestData.xlsx";

	@BeforeClass
	public void beforeClass() throws IOException {
		
		try{
			log.debug("started initilizing the page class objects");
	
		lhmPg = new LinkedinHomePage();
		llPg = new LinkedinLoggedinPage();
		srPg = new SearchResultsPage();
		log.debug("Verifying the linkedin home page title");
		lhmPg.verifyLinkedinHomePageTitle();
		log.debug("Verify the linkedin logo");
		lhmPg.verifyLinkedinLogo();
		log.debug("click on sign in link in the home page");
		lhmPg.clickSignInLink();
		log.debug("verify the linkedin login page");
		lhmPg.verifyLinkedinLoginPageTitle();
		log.debug("verify the linkedin loginwelcome back text page");
		lhmPg.verifyWelcomeBackHeaderText();
		Thread.sleep(3000);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		log.debug("perform the login once only");
		llPg = lhmPg.doLogin(readProperty("email"), readProperty("pwd"));
		}
		
	}

	@Test(dataProvider = "testData")
	public void searchPeopleTest(String keyword) throws InterruptedException {
		log.debug("Started execuitng the searchPeopleTest...");
		llPg.verifyProfileRailCard();
		llPg.clickUpIcon();
		log.debug("performing the search operation for :" + keyword);
		srPg = llPg.doPeopleSearch(keyword);
		log.debug("fetch the results count for :" + keyword);
		long count = srPg.getResultsCount();
		log.debug("the results count for :" + keyword + " is : " + count);
		log.debug("click on Hometab");
		srPg.clickHomeTab();
		Thread.sleep(2000);
	}

	@DataProvider
	public Object[][] testData() throws InvalidFormatException, IOException {

		// create Two Dimensional Object
		Object[][] data = new ExcelUtils().getTestData(excelpath, "Sheet1");
		return data;
	}

	@AfterClass
	public void logoutTest() {
		log.debug("perform the logout operation");
		llPg.doLogout();
	}
	
	@AfterTest
	public void verifyHomePageTileTest() {
		lhmPg.verifyLinkedinHomePageTitle();
		Assert.fail("TestCase failed");
	}

/*@Test(dependsOnMethods= {"logverifyHomePageTileTest"})
	public void verifyLinkedinLogoTest() {
		lhmPg.verifyLinkedinLogo();
		Assert.fail("TestCase failed");
	}*/
	
}
