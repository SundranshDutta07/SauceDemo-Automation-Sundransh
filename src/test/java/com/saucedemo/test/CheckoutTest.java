package com.saucedemo.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.*;
import com.saucedemo.utils.ScreenshotUtil;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckoutTest extends BaseTest{
    private CartPage cartPage;
    private CheckoutYourInfoPage checkoutInfoPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;
    
    private static final Logger log = LogManager.getLogger(CheckoutTest.class);

    @Test
    public void testValidCheckoutProcess() throws InterruptedException {
    	try {
    	loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutInfoPage = new CheckoutYourInfoPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        
        log.info("Selecting product: Sauce Labs Bolt T-Shirt");
        test.log(Status.INFO, "Selecting product: Sauce Labs Bolt T-Shirt");
        
        String product = "Sauce Labs Bolt T-Shirt";
        String expectedName = productPage.getProductName(product);
        String expectedPrice = productPage.getProductPrice(product);
        String expectedDescription = productPage.getProductDescription(product);
        
        Thread.sleep(2000);
        
        productPage.clickAddToCard(product);
        test.log(Status.INFO, "Product added to cart");
        
        String AddtoCartSS= ScreenshotUtil.captureScreenshot(driver, "testValidCheckoutProcess","Click on add to cart");
        test.pass("Click on cart", MediaEntityBuilder.createScreenCaptureFromPath(AddtoCartSS).build());
        
        Thread.sleep(2000);

        productPage.clickCart();
        productPage.scrollToCart();
        Assert.assertEquals(cartPage.getProductName(product), expectedName);
        Assert.assertEquals(cartPage.getProductPrice(product), expectedPrice);
        Assert.assertEquals(cartPage.getProductDescription(product), expectedDescription);
        
        log.info("Cart verification successful");
        test.log(Status.PASS, "Cart verification successful");
        String CartSS= ScreenshotUtil.captureScreenshot(driver, "testValidCheckoutProcess","Verify Cart & Product in it");
        test.pass("Verify Cart & Product in it", MediaEntityBuilder.createScreenCaptureFromPath(CartSS).build());

        Thread.sleep(2000);

        cartPage.clickCheckout();
        log.info("Checkout information page");
        test.log(Status.INFO, "Checkout information page");
        String CheckoutInfoSS= ScreenshotUtil.captureScreenshot(driver, "testValidCheckoutProcess","Checkout information page");
        test.pass("Checkout information page", MediaEntityBuilder.createScreenCaptureFromPath(CheckoutInfoSS).build());
        
        checkoutInfoPage.enterFirstName("John");
        checkoutInfoPage.enterLastName("Doe");
        checkoutInfoPage.enterZipCode("12345");
        
        Thread.sleep(2000);
       log.info("Checkout information entered");       
        test.log(Status.INFO, "Checkout information entered");
        String InfoEnteredSS= ScreenshotUtil.captureScreenshot(driver, "testValidCheckoutProcess","Checkout information entered");
        test.pass("Checkout information entered", MediaEntityBuilder.createScreenCaptureFromPath(InfoEnteredSS).build());
        
        checkoutInfoPage.scrollToContinueButton();

        log.info("Scroll to continue");       
        test.log(Status.INFO, "Scroll to continue");        
        String scrollContinuess= ScreenshotUtil.captureScreenshot(driver, "testValidCheckoutProcess","Scroll to continue button");
        test.pass("Scroll to continue button", MediaEntityBuilder.createScreenCaptureFromPath(scrollContinuess).build());
        
        checkoutInfoPage.clickContinue();
        
        String CheckoutOverviewSS= ScreenshotUtil.captureScreenshot(driver, "testValidCheckoutProcess","Checkout Overview Page");
        test.pass("Checkout Overview Page", MediaEntityBuilder.createScreenCaptureFromPath(CheckoutOverviewSS).build());
        
        productPage.scrollToCart();
        log.info("scroll to top");
        
        Assert.assertEquals(checkoutOverviewPage.getPaymentInfo(), "SauceCard #31337");
        Assert.assertEquals(checkoutOverviewPage.getShippingInfo(), "Free Pony Express Delivery!");
        
        checkoutOverviewPage.scrollToPaymentInfo();
        log.info("scroll to Price");
        Assert.assertTrue(checkoutOverviewPage.isTotalCorrect());
        
        log.info("Checkout Overview verified");
        test.log(Status.PASS, "Checkout Overview verified");
        
        Thread.sleep(2000);

        checkoutOverviewPage.clickFinish();
       
        Assert.assertEquals(checkoutCompletePage.getSuccessMessage(), "Thank you for your order!");
        test.log(Status.PASS, "Order placed successfully");
        log.info("Order placed successfully");
        
        Thread.sleep(2000);

        log.info("Scroll to top"); 
        productPage.scrollToCart();
        
        String CheckoutCompleteSS= ScreenshotUtil.captureScreenshot(driver, "testValidCheckoutProcess","Checkout Complete");
        test.pass("Checkout Complete", MediaEntityBuilder.createScreenCaptureFromPath(CheckoutCompleteSS).build());
                 
        checkoutCompletePage.clickBackHome();
        log.info("Click on BackHome button");
        
        Thread.sleep(2000);

        if (productPage != null && productPage.getProductPageTitle()) {
            Assert.assertTrue(productPage.getProductPageTitle(), "Login failed: Product page title is incorrect");
            test.log(Status.PASS, "Landed on Product Page");
            log.info("Landed on Product Page");

            String dashboardSS = ScreenshotUtil.captureScreenshot(driver, "testValidCheckoutProcess", "Dashboard success screen");
            test.pass("Dashboard success screen", MediaEntityBuilder.createScreenCaptureFromPath(dashboardSS).build());
            } else {
              test.log(Status.FAIL, "Did not landed on product page");
              log.info("Did not landed on product page");

              String failureSS = ScreenshotUtil.captureScreenshot(driver, "testValidCheckoutProcess", "Dashboard screen fail");
              test.fail("Dashboard screen fail", MediaEntityBuilder.createScreenCaptureFromPath(failureSS).build());
              Assert.fail("Login failed! Product page was not loaded.");
           }
    	} catch (IOException e) {
    		    test.log(Status.FAIL, "Test case failed due to exception: " + e.getMessage());
    		    log.error("Test failed due to exception", e);
    		    Assert.fail("Test failed due to exception: " + e.getMessage());
    		}
    	}
    }       