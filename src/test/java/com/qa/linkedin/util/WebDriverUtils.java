package com.qa.linkedin.util;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
public class WebDriverUtils{


 public WebDriver driver;
 public WebDriverUtils(WebDriver driver) {
  this.driver=driver;
 }
//File upload by Robot Class
  /**
   * below method will upload given file location using Java Robot class
   * @param filePath
   */
     public static void uploadFileWithRobot (String filePath) {
      //create object for StringSelection
         StringSelection stringSelection = new StringSelection(filePath);
         //copying the file location to system clipboard
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(stringSelection, null);
  //Create Object for robot class
         Robot robot = null;
 
         try {
             robot = new Robot();
         } catch (AWTException e) {
             e.printStackTrace();
         }
 
         robot.delay(250);
         //pressing the ENTER key and releasing
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
         //pressing the CONTROL+V key and releasing the CONTROL+V
         robot.keyPress(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_CONTROL);
       //pressing the ENTER key and releasing
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.delay(150);
         robot.keyRelease(KeyEvent.VK_ENTER);
     }
  /**
   * this method downloads the files in firefox browser
   * @param path
   */
    
    public static FirefoxOptions downloadFileUsingFirefox(String path) {
     //create object for FirefoxOptions class
     FirefoxOptions options = new FirefoxOptions();
     options.addPreference("browser.download.folderList", 2);
     options.addPreference("browser.download.dir", path);

     // File type of the downloaded file
     options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip");
     options.addPreference( "browser.download.manager.showWhenStarting", false );
     options.addPreference( "pdfjs.disabled", true );
   return options;
    }
    
    
 /**
  * this method download the file using chrome browse
  * @param path
  * @return
  */
    
     public static ChromeOptions downloadFileUsingChrome(String path) {
      //DOWLOADD CODE
      //craete object for ChromeOptions class
        ChromeOptions options = new ChromeOptions();
        //create HashMap object
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups",true);
        prefs.put("download.default_directory", path);
        options.setExperimentalOption("prefs", prefs);
        // set the chromedriver.exe path
        return options;
     }
    
     /**
   * this method highlights the given element
   * @param driver
   * @param element
   * @throws InterruptedException
   */
  public static void highLightElement1(WebDriver driver, WebElement element) throws InterruptedException
  {
  JavascriptExecutor js=(JavascriptExecutor) driver; //downcasting

  js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 5px red')", element);

  Thread.sleep(5000);
  
  js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);

  }
  /**
   * this method types given keyword without using sendKeys()
   * @param element
   * @param attributeName
   * @param value
   */
  public static void setAttribute(WebElement element, String attributeName, String value)
  {
  WrapsDriver wrappedElement = (WrapsDriver) element;
  JavascriptExecutor js = (JavascriptExecutor)wrappedElement.getWrappedDriver();
  js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, attributeName, value);
  }
  
    public static void scrollForElement(WebDriver driver,WebElement elementname) {
     //scroll for this element
       JavascriptExecutor jsx=(JavascriptExecutor) driver;
       jsx.executeScript("arguments[0].scrollIntoView(true);", elementname);
    }
   
   
public static void safeJavaScriptClick(WebDriver driver,WebElement element){
  
  try{
   if(element.isEnabled()&&element.isDisplayed()){
   JavascriptExecutor js=  (JavascriptExecutor) driver;
   js.executeScript("arguments[0].click();", element);
   }
   
  }catch(StaleElementReferenceException se){
   System.out.println("Element is no more attached to the DOM:");
   se.printStackTrace();
  }catch(NoSuchElementException ne){
   System.out.println("No Element present in the DOM:");
   ne.printStackTrace();
   
  }catch(Exception e){
   System.out.println("Exception is different:");
   e.printStackTrace();
  }
  
 }

}
