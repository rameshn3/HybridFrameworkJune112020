package com.qa.linkedin.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;

public class LinkedinHomePage extends TestBase{

	private Logger log = Logger.getLogger(LinkedinHomePage.class);
	
	private String homePageTitle = "LinkedIn: Log In or Sign Up";
	private String loginPageTitle ="LinkedIn Login, Sign in | LinkedIn";
	public LinkedinHomePage() {
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[@class='nav__logo-link']")
	private WebElement linkedinLogo;
	
	@FindBy(linkText="Sign in")
	private WebElement signInLink;
	
	@FindBy(css="h1.header__content__heading")
	private WebElement welcomeBackHeading;
	
	@FindBy(id="username")
	private WebElement emailEditbox;
	
	@FindBy(name="session_password")
	private WebElement passwordEditbox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	private WebElement signInBtn;
	
	
	public void verifyLinkedinHomePageTitle() {
		log.debug("wait for the Linkeidn home page title");
		wait.until(ExpectedConditions.titleContains(homePageTitle));
		Assert.assertEquals(driver.getTitle(), homePageTitle);
	}
	
	public void verifyLinkedinLogo() {
		log.debug("wait and verify the linkedin logo in HomePage");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed(),"LinkedinLogo element is not present");
	}
	
	public void clickSignInLink() {
		if(signInLink.isDisplayed() && signInLink.isEnabled()) {
		log.debug("click on singinLink");
		wait.until(ExpectedConditions.elementToBeClickable(signInLink));
		signInLink.click();
		}
	}
	
	public void verifyWelcomeBackHeaderText() {
		log.debug("wait and verify the welcomeBack header text in LoginPage");
		wait.until(ExpectedConditions.visibilityOf(welcomeBackHeading));
		Assert.assertTrue(welcomeBackHeading.isDisplayed(),"LinkedinLogo element is not present");
	}
	
	public void verifyLinkedinLoginPageTitle() {
		log.debug("wait for the Linkeidn Login page title");
		wait.until(ExpectedConditions.titleContains(loginPageTitle));
		Assert.assertEquals(driver.getTitle(), loginPageTitle);
	}
	public void clickSignInBtn() {
		if(signInBtn.isDisplayed() && signInBtn.isEnabled()) {
		log.debug("click on signInBtn");
		wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
		signInBtn.click();
		}
	}
	
	public LinkedinLoggedinPage doLogin(String email,String pwd) {
		log.debug("started executing the doLogin() ");
		log.debug("clear the content in email editbox");
		emailEditbox.clear();
		log.debug("type the email in emailEditbox: "+email);
		emailEditbox.sendKeys(email);
		log.debug("clear the content in password editbox");
		passwordEditbox.clear();
		log.debug("type the password in passwordEditbox: "+pwd);
		passwordEditbox.sendKeys(pwd);
		log.debug("perform the click on SigninButton");
		clickSignInBtn();
		return new LinkedinLoggedinPage();
	}
	
	
}
