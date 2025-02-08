package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends ProductPage{
    private WebDriver driver;
    private CommonElements commonElements;

    //Objects
    private By cartTitle = By.xpath("//span[@class='title' and text()='Your Cart']");
     private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
       this.driver = driver;
        this.commonElements = new CommonElements(driver);    
    }

    // Verify Cart Page Loaded
    public boolean isCartPageDisplayed() {
        return commonElements.isElementDisplayed(cartTitle);
    }

    //Get Product Name in Cart
    public String getProductNameCart(String productName) {
    	return getProductName(productName);
    }

    //Get Product Price in Cart
    public String getProductPriceCart(String productName) {
    	return getProductPrice(productName);
    }

    //Get Product Description in Cart
    public String getProductDescCart(String productName) {
    	return getProductDescription(productName);
    }

    //Click Remove Button
    public void removeProductCart(String productName) {
        clickRemove(productName);
}

    //Click Checkout Button
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    // Click Continue Shopping Button
    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
        }
}