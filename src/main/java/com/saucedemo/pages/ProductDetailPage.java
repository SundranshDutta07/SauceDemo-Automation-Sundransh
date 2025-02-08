package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage {
    private WebDriver driver;

    //Objects
    private By productName = By.className("inventory_details_name large_size");
    private By productDescription = By.className("inventory_details_desc large_size");
    private By productPrice = By.className("inventory_details_price");
    private By addToCartButton = By.xpath("//button[contains(text(),'Add to cart')]");
    private By removeButton = By.xpath("//button[contains(text(),'Remove')]");
    private By backButton = By.id("back-to-products");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    //To get product name
    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    //To get product desc
    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    //To get product price
    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    //Click on Add to Cart button
    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    //Click on Remove button
    public void clickRemove() {
        driver.findElement(removeButton).click();
    }

    // Click on Back to Products button
    public void clickBackToProducts() {
        driver.findElement(backButton).click();
    }
}