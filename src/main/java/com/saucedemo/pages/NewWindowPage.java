package com.saucedemo.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucedemo.base.BaseTest;

public class NewWindowPage {
    private WebDriver driver;
    public String mainTab;
    private CommonElements commonElements;

    //Objects
    private By new_SauceLab = By.xpath("//button[contains(text(),'Sign in')]");
    private By newWindow_Twitter = By.xpath("//button[@aria-label='Provides details about verified accounts.']/preceding::span[contains(text(),'Sauce Labs')][1]");
    private By newWindow_Facebook = By.xpath("//a[contains(text(),'Sauce Labs')]");
    private By newWindow_LinkedIn = By.xpath("//h1[@title='Sauce Labs']");

    public NewWindowPage(WebDriver driver) {
        this.driver = driver;
        this.mainTab = driver.getWindowHandle();
        this.commonElements = new CommonElements(driver);
    }

    
    //Actions
    public boolean verifyRedirectionSauceLab() {
        return commonElements.isElementDisplayed(new_SauceLab);
    }

    public boolean verifyWindowSwitchTwitter() {
        return commonElements.isElementDisplayed(newWindow_Twitter);
    }    
    
    public boolean verifyWindowSwitchFacebook() {
        return commonElements.isElementDisplayed(newWindow_Facebook);
    }

    public boolean verifyWindowSwitchLinkedIn() {
        return commonElements.isElementDisplayed(newWindow_LinkedIn);
    }
    
    //WindowHandle
    public void switchToNewTab() {
    Set<String> windowHandles = driver.getWindowHandles();
    for(String handle: windowHandles) {
    	if(!handle.equals(mainTab)) {
    		driver.switchTo().window(handle);
    		return;
    	}
    }	
    }
    
    public void closeCurrentTabAndSwitch() {
    driver.close();
    driver.switchTo().window(mainTab);
    	}
    }