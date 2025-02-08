package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private CommonElements commonElements;

    //Objects
    private By heading = By.xpath("//div[contains(text(),'Swag Labs')]");
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By lockedoutUser_Message = By.xpath("//h3[contains(text(),'locked out')]");
    private By invalidoutUser_Message = By.xpath("//h3[contains(text(),'do not match any user')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.commonElements = new CommonElements(driver);
    }

    //Actions
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
        
    }
    
    public boolean verifyLoginButtonVisible() {
        return commonElements.isElementDisplayed(loginButton);

    }
    public boolean verifyHeadingisVisible() {
        return commonElements.isElementDisplayed(heading);
    }
    
    public boolean lockedoutUserErrorisVisible() {
        return commonElements.isElementDisplayed(lockedoutUser_Message);
    }
    
    public boolean invalidUserErrorisVisible() {
        return commonElements.isElementDisplayed(invalidoutUser_Message);
    }
}