package com.saucedemo.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuAndFooterPage {
    private WebDriver driver;
    private CommonElements commonElements;

    //Objects for Menu
    public By menuOpen = By.id("react-burger-menu-btn");
    private By menuClose = By.id("react-burger-cross-btn");
    private By logout = By.xpath("//a[contains(text(),'Logout')]");
    private By about = By.xpath("//a[contains(text(),'About')]");
    private By allItems = By.xpath("//a[contains(text(),'All Items')]");
    private By resetAppState = By.xpath("//a[contains(text(),'Reset App State')]");

    //Objects for Footer
    private By twitter = By.xpath("//a[contains(text(),'Twitter')]");
    private By facebook = By.xpath("//a[contains(text(),'Facebook')]");
    private By linkedIn = By.xpath("//a[contains(text(),'LinkedIn')]");
    
    public MenuAndFooterPage(WebDriver driver) {
        this.driver = driver;
        this.commonElements = new CommonElements(driver);
    }

    //Actions
    public void clickTwitter() {
        driver.findElement(twitter).click();
    }

    public void clickFacebook() {
        driver.findElement(facebook).click();
    }

    public void clickLinkedIn() {
        driver.findElement(linkedIn).click();
    }

  //scroll to twitter
    public void scrollToTwitter() {
    	WebElement twitter = driver.findElement(By.xpath("//a[contains(text(),'Twitter')]"));
    	commonElements.scrollToElement(twitter);
    }
    
  //scroll to facebook
    public void scrollToFacbook() {
    	WebElement facebook = driver.findElement(By.xpath("//a[contains(text(),'Facebook')]"));
    	commonElements.scrollToElement(facebook);
    }
    
  //scroll to linkedIn
    public void scrollToLinkedIn() {
    	WebElement linkedIn = driver.findElement(By.xpath("//a[contains(text(),'LinkedIn')]"));
    	commonElements.scrollToElement(linkedIn);
    }
    
    public boolean isMenuButtonPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.presenceOfElementLocated(menuOpen));
            return true; // Found the menu button
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public void clickMenuOpen() {
   	 driver.findElement(menuOpen).click();
   }
    
    public void clickMenuClose() {
    	 driver.findElement(menuClose).click();
    }

    public void clickAbout() {
   	 driver.findElement(about).click();
   }

    public void clickAllItems() {
      	 driver.findElement(allItems).click();
    }
    
    public void clickResetAppState() {
     	 driver.findElement(resetAppState).click();
    }
    
    public void clickLogout() {
    	  driver.findElement(logout).click();
    }
}