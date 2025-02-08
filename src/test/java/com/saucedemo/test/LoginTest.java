package com.saucedemo.test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.saucedemo.base.*;
import com.saucedemo.pages.MenuAndFooterPage;
import com.saucedemo.utils.*;

public class LoginTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginTest.class);

	//Test for Invalid Login(Incorrect Credentials)
    @Test
    public void invalidLoginTest() throws InterruptedException {
    	try {
    	menuAndFooterPage = new MenuAndFooterPage(driver);

        Thread.sleep(1000);
    	menuAndFooterPage.clickMenuOpen();
        test.log(Status.INFO, "Clicked on Menu");
        log.info("Clicked on Menu");

        String MenuSS= ScreenshotUtil.captureScreenshot(driver, "LoginInvalidUser","Logout To Test InvalidLogin");
        test.pass("Logging out", MediaEntityBuilder.createScreenCaptureFromPath(MenuSS).build());

        Thread.sleep(2000);
        menuAndFooterPage.clickLogout();  // Log out first
        test.log(Status.INFO, "Clicked on Logout->Landed to Loginpage");
        log.info("Clicked on Logout->Landed to Loginpage");
       
        String logoutSS = ScreenshotUtil.captureScreenshot(driver, "LoginInvalidUser","Loged out");
        test.pass("Logged Out", MediaEntityBuilder.createScreenCaptureFromPath(logoutSS).build());
        
        loginPage.enterUsername("123");
        loginPage.enterPassword("321");
        
        test.log(Status.INFO, "Entered invalid credentials.");
        log.info("Entered invalid credentials");

        String InvalidCredentialsSS = ScreenshotUtil.captureScreenshot(driver, "LoginInvalidUser", "Entered Invalid Credentials");  
        test.pass("Entered Invalid Credentials", MediaEntityBuilder.createScreenCaptureFromPath(InvalidCredentialsSS).build());
        
        Thread.sleep(2000);
        loginPage.clickLogin();
        test.log(Status.INFO, "Performing click on Login");
        log.info("Performing click on Login");

        Assert.assertTrue(loginPage.invalidUserErrorisVisible(), "Error message is not displayed for invalid user.");
        String ErrorMessageSS =  ScreenshotUtil.captureScreenshot(driver, "LoginInvalidUser", "Invalid Login Error Message Displayed");
        test.pass("Invalid Login Error Message Displayed", MediaEntityBuilder.createScreenCaptureFromPath(ErrorMessageSS).build());
    }catch (IOException e) {
        test.fail("Failed to attach screenshot: " + e.getMessage());
    }
}

	//Test for LockdedOutUser
    @Test
    public void lockedOutUserTest() throws InterruptedException {
    	try {
        	menuAndFooterPage = new MenuAndFooterPage(driver);

            Thread.sleep(1000);
        	menuAndFooterPage.clickMenuOpen();
            test.log(Status.INFO, "Clicked on Menu");
            log.info("Clicked on Menu");

            Thread.sleep(2000);

            String MenuSS= ScreenshotUtil.captureScreenshot(driver, "LockedOutUser","Logout To Test LockedOutUser");
            test.pass("Logging out", MediaEntityBuilder.createScreenCaptureFromPath(MenuSS).build());

            menuAndFooterPage.clickLogout();  // Log out first
            test.log(Status.INFO, "Clicked on Logout->Landed to Loginpage");
            log.info("Clicked on Logout->Landed to Loginpage");

            String logoutSS = ScreenshotUtil.captureScreenshot(driver, "LockedOutUser", "Logged Out");
            test.pass("Logged Out", MediaEntityBuilder.createScreenCaptureFromPath(logoutSS).build());
            
            loginPage.enterUsername("locked_out_user");
            loginPage.enterPassword("secret_sauce");
            
            test.log(Status.INFO, "Entered lockedout credentials.");
            log.info("Entered lockedout credentials");
            
            String credentialsSS = ScreenshotUtil.captureScreenshot(driver, "LockedOutUser", "Credentials Entered");
            test.pass("Entered LockedOut Credentials", MediaEntityBuilder.createScreenCaptureFromPath(credentialsSS).build());
            
            Thread.sleep(2000);
            loginPage.clickLogin();
            test.log(Status.INFO, "Performing click on Login");
            log.info("Performing click on Login");

            Assert.assertTrue(loginPage.lockedoutUserErrorisVisible(), "Error message is not displayed for locked-out user.");
            
            String errorMsgSS = ScreenshotUtil.captureScreenshot(driver, "LockedOutUser", "Invalid Login Error Displayed");
            test.pass("Invalid Login Error Displayed", MediaEntityBuilder.createScreenCaptureFromPath(errorMsgSS).build());
        }catch (IOException e) {
            test.fail("Failed to attach screenshot: " + e.getMessage());
    }
}
    }