package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.qa.linkedin.base.TestBase;

public class SearchResultsPage extends TestBase{
private Logger log = Logger.getLogger(SearchResultsPage.class);
	

 public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(@class,'search-results__total')]")
	private WebElement search_results_text;
	
	@FindBy(xpath="//*[@id='feed-tab-icon']")
	private WebElement homeTab;
	
	public void validateSearchResultsTitle() {
		log.debug("waiting for the search results page title -- Search | LinkedIn");
		wait.until(ExpectedConditions.titleContains("Search | LinkedIn"));
		Assert.assertTrue(driver.getPageSource().contains(driver.getTitle()));
		
	}
	
	public long getResultsCount() {
		validateSearchResultsTitle();
		log.debug("wait for the search results text");
		wait.until(ExpectedConditions.visibilityOf(search_results_text));
		log.debug("getting the results text from webpage");
		String txt=search_results_text.getText();
		//txt="Showing 40,938 results";
		String[] str=txt.split(" ");
		//str[]=["Showing","40,938","results"]
		//			0			1		2
		log.debug("results count in string format is-->"+str[1]);
		String finalStringcnt=str[1].replace(",", "").trim();
		//convert the String into long format
		long count=Long.parseLong(finalStringcnt);
		//convert the String into integer format
		//int cnt=Integer.parseInt(finalStringcnt);
		
		return count;
	}
	
	public void clickHomeTab() {
		log.debug("wait for the home tab");
		wait.until(ExpectedConditions.visibilityOf(homeTab));
		log.debug("check whether homeTab is clickable or not ");
		wait.until(ExpectedConditions.elementToBeClickable(homeTab));
		homeTab.click();
	}
	
}
