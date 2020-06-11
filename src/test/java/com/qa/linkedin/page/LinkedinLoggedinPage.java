package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.qa.linkedin.base.TestBase;

public class LinkedinLoggedinPage extends TestBase{
	
	private Logger log = Logger.getLogger(LinkedinLoggedinPage.class);
	
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[contains(@class,'profile-rail-card')]")
	private WebElement profileRailCard;
	
	@FindBy(xpath="//*[@role='combobox' and @placeholder='Search']")
	private WebElement searchEditbox;
	
	@FindBy(xpath="//*[contains(@class,'nav-item__profile-member-photo nav-item__icon')]")
	private WebElement profileIcon;
	
	@FindBy(xpath="//*[@data-control-name='nav.settings_signout']")
	private WebElement signoutLink;
	
	@FindBy(xpath="//*[@type='chevron-up-icon']")
	private WebElement upIcon;
	
	public void clickUpIcon() {
		log.debug("click on up icon");
		wait.until(ExpectedConditions.visibilityOf(upIcon));
		wait.until(ExpectedConditions.elementToBeClickable(upIcon));
		upIcon.click();
	}
	
	public void verifyProfileRailCard() {
		log.debug("wait and verify the profileRaildcard in HomePage");
		wait.until(ExpectedConditions.visibilityOf(profileRailCard));
		Assert.assertTrue(profileRailCard.isDisplayed(),"profileRailCard element is not present");
	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("started Executing he doPeopleSearch() for "+keyword);
		log.debug("clear the content in search editbox");
		searchEditbox.clear();
		log.debug("type the search keyword in editbox: "+keyword);
		searchEditbox.sendKeys(keyword);
		log.debug("press the Enter key on search editbox");
		searchEditbox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return new SearchResultsPage(); 
	}
	
	public void doLogout() {
		log.debug("wait for the visibility of profile image icon");
		wait.until(ExpectedConditions.visibilityOf(profileIcon));
		log.debug("click on profile icon");
		profileIcon.click();
		log.debug("wait for the visibility of signout link");
		wait.until(ExpectedConditions.visibilityOf(signoutLink));
		log.debug("click on signoutLink");
		signoutLink.click();
	}
	
}
