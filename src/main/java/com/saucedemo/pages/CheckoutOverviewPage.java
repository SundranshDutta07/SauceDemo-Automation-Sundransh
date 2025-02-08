package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage {
    private WebDriver driver;
    private CommonElements commonElements;

    //Objects
    private By overviewTitle = By.xpath("//span[@class='title']");
    private By paymentInfo = By.xpath("//div[contains(text(),'SauceCard #31337')]");
    private By shippingInfo = By.xpath("//div[contains(text(),'Free Pony Express Delivery!')]");
    private By itemTotal = By.xpath("//div[@class='summary_subtotal_label']");
    private By taxAmount = By.xpath("//div[@class='summary_tax_label']");
    private By totalAmount = By.xpath("//div[@class='summary_total_label']");
    private By finishButton = By.id("finish");
    private By cancelButton = By.id("cancel");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.commonElements = new CommonElements(driver);    
    }

    //Get page title
    public String getOverviewTitle() {
        return driver.findElement(overviewTitle).getText();
    }

    //Get payment info
    public String getPaymentInfo() {
        return driver.findElement(paymentInfo).getText();
    }

  //scroll to payment info
    public void scrollToPaymentInfo() {
    	WebElement PayInfo = driver.findElement(By.xpath("//div[contains(text(),'SauceCard #31337')]"));
    	commonElements.scrollToElement(PayInfo);
    }
    
    //Get shipping info
    public String getShippingInfo() {
        return driver.findElement(shippingInfo).getText();
    }

    //Get item total price
    public double getItemTotal() {
        String priceText = driver.findElement(itemTotal).getText().replace("Item total: $", "");
        return Double.parseDouble(priceText);
    }

    //Get tax amount
    public double getTaxAmount() {
        String taxText = driver.findElement(taxAmount).getText().replace("Tax: $", "");
        return Double.parseDouble(taxText);
    }

    //Get total amount (removing prefix)
    public double getTotalAmount() {
        String totalText = driver.findElement(totalAmount).getText().replace("Total: $", "");
        return Double.parseDouble(totalText);
    }

    //Validate total price = item total + tax
    public boolean isTotalCorrect() {
        return getTotalAmount() == (getItemTotal() + getTaxAmount());
    }

    //Click finish button
    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    //Click cancel button
    public void clickCancel() {
        driver.findElement(cancelButton).click();
        }
    }