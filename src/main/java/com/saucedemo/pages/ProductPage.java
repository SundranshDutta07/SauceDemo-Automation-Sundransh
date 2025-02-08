package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    private WebDriver driver;
    private CommonElements commonElements;

    //Objects
    private By sortDropdown =By.className("product_sort_container");
    private By textProducts =By.xpath("//span[@class='title' and contains(text(),'Product')]");
    private By cart =By.id("shopping_cart_container");

    public ProductPage(WebDriver driver) {
       // super(driver);
        this.driver = driver;
        this.commonElements = new CommonElements(driver);

    }

    //To get Product text from the page
    public boolean getProductPageTitle() {
        return driver.findElement(textProducts).isDisplayed();
    }
    
   //Click on cart
    public void clickCart() {
         driver.findElement(cart).click();
    }
    
    //scroll to cart button
    public void scrollToCart() {
    	WebElement cart = driver.findElement(By.id("shopping_cart_container"));
    	commonElements.scrollToElement(cart);
    }
    
    //Dynamic method to get product name
    public String getProductName(String productName) {
        String xpath = "//div[@data-test='inventory-item-name' and contains(text(),'"+productName+"')]";
        return driver.findElement(By.xpath(xpath)).getText();
    }
    
    //Dynamic method to navigate to product description page
    public void clickProduct(String productName) {
        String xpath = "//div[@data-test='inventory-item-name' and contains(text(),'"+productName+"')]";
         driver.findElement(By.xpath(xpath)).click();
    }
    
    //Dynamic method to get product price
    public String getProductPrice(String productName) {
        String xpath = "//div[contains(text(),'"+productName+"')]/following::div[@class='inventory_item_price'][1]";
        return driver.findElement(By.xpath(xpath)).getText();
    }

    //Dynamic method to get product description
    public String getProductDescription(String productName) {
        String xpath = "//div[contains(text(),'"+productName+"')]/following::div[@class='inventory_item_desc'][1]";
        return driver.findElement(By.xpath(xpath)).getText();
    }

    //Dynamic method to click "Add to Cart" button
    public void clickAddToCard(String productName) {
        String xpath = "//div[contains(text(),'"+productName+"')]/following::button[contains(text(),'Add to cart')][1]";
        driver.findElement(By.xpath(xpath)).click();
        }
 
    //Dynamic method to click "Remove" button
    public void clickRemove(String productName) {
        String xpath = "//div[contains(text(),'"+productName+"')]/following::button[contains(text(),'Remove')][1]";
        driver.findElement(By.xpath(xpath)).click();
    }
    
    //Method to select a sorting option- Select option by visible text
    public void selectSortingOption(String option) {
        WebElement dropdown = driver.findElement(sortDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
        }
    
 //Method to check if product names are sorted (A to Z or Z to A)
    public boolean isProductNameSorted(boolean ascending) {
        List<WebElement> productElements = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        List<String> productNames = new ArrayList<String>();

     //Store product names
        for (WebElement product : productElements) {
            productNames.add(product.getText()); 
        }

        //Copy of original list
        List<String> sortedList = new ArrayList<String>(productNames);
        if (ascending) {
            Collections.sort(sortedList); // Sort A to Z
        } else {
            Collections.sort(sortedList, Collections.reverseOrder()); // Sort Z to A
        }
     //Compare original and sorted lists
        return productNames.equals(sortedList); 
    }

    //Method to check if product prices are sorted (Low to High or High to Low)
    public boolean isProductPriceSorted(boolean lowToHigh) {
        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        List<Double> productPrices = new ArrayList<Double>();

        for (WebElement price : priceElements) {
            productPrices.add(Double.parseDouble(price.getText().replace("$", "")));
        }

        //Copy of the original list
        List<Double> sortedPrices = new ArrayList<Double>(productPrices);
        if (lowToHigh) {
            Collections.sort(sortedPrices); // Sort Low to High
        } else {
            Collections.sort(sortedPrices, Collections.reverseOrder()); // Sort High to Low
        }
     //Comparing original and sorted lists
        return productPrices.equals(sortedPrices);
        }

}