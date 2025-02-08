package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private WebDriver driver;
    private CommonElements commonElements;

    //Ojects
    private By completeTitle = By.xpath("//span[@class='title']");
    private By successMessage = By.xpath("//h2[contains(text(),'Thank you for your order!')]");
    private By ponyExpressImage = By.xpath("//img[@class='pony_express']");
    private By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.commonElements = new CommonElements(driver);
    }

    //Get title text
    public String getCompleteTitle() {
        return driver.findElement(completeTitle).getText();
    }

    //Get success message
    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    //Verify Pony Express image is displayed
    public boolean isPonyExpressImageDisplayed() {
        return commonElements.isElementDisplayed(ponyExpressImage);
    }

    //Click Back Home button
    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }
}