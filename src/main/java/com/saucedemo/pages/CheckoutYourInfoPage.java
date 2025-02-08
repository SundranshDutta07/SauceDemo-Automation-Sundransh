package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutYourInfoPage {
    private WebDriver driver;
    private CommonElements commonElements;

    //Objects
    private By checkoutTitle = By.xpath("//span[@class='title']");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By cancelButton = By.id("cancel");
    private By errorMessage = By.xpath("//h3[@data-test='error']");

    public CheckoutYourInfoPage(WebDriver driver) {
        this.driver = driver;
        this.commonElements = new CommonElements(driver);
    }

    //Get checkout title text
    public String getCheckoutTitle() {
        return driver.findElement(checkoutTitle).getText();
    }

    //Enter First Name
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    //Enter Last Name
    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    //Enter Postal Code
    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }

    //scroll to continue button
    public void scrollToContinueButton() {
    	WebElement continueButton = driver.findElement(By.id("continue"));
    	commonElements.scrollToElement(continueButton);
    }
    
    //Click Continue button
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    //Click Cancel button
    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }

    //Get error message text
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    //Check if error message is displayed
    public boolean isErrorDisplayed() {
        List<WebElement> errors = driver.findElements(errorMessage);
        return !errors.isEmpty() && errors.get(0).isDisplayed();
    }
}