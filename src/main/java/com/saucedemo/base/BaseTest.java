package com.saucedemo.base;

import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.saucedemo.pages.*;
import com.saucedemo.utils.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected MenuAndFooterPage menuAndFooterPage;
    protected ProductPage productPage;
    protected ExtentTest test;
    protected ExtentReports extent;

    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @Parameters({"username", "password"})
    @BeforeMethod
    public void setup(String username, String password, ITestContext context) throws InterruptedException {
        extent = ExtentManager.getInstance("SauceDemo Test Suite");
        test = extent.createTest("Login Test");
        try { 
        //Path for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sundransh Dutta\\chromedriver\\chromedriver-win64\\chromedriver.exe");
     //   System.setProperty("webdriver.chrome.verboseLogging", "true"); 
        System.setProperty("webdriver.chrome.silentOutput", "false");
        System.setProperty("webdriver.chrome.logfile", "chromedriver.log");

        System.out.println("Starting ChromeDriver...");

        //Chrome Portable
        ChromeOptions options = new ChromeOptions();
     //   options.setCapability("goog:loggingPrefs", Map.of("browser", "ALL", "driver", "ALL"));
        // **Enable detailed logging**
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        
        options.addArguments("--disable-web-security", "--disable-features=NetworkService", "--remote-allow-origins=*");
        options.setBinary("E:\\SauceDemo\\chromePortable\\App\\Chrome-bin\\chrome.exe");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //WebDriver to Test Context for Listeners
        context.setAttribute("WebDriver", driver);

        //Navigate to SauceDemo
        driver.get("https://www.saucedemo.com");
        test.log(Status.INFO, "Navigated to SauceDemo Login Page");
        log.info("Navigated to SauceDemo Login Page");

        //Screenshot for Login Page        
        String loginPageSS = ScreenshotUtil.captureScreenshot(driver, "Login", "SauceDemo LoginPage");
   //     test.addScreenCaptureFromPath(loginPageSS);        
        test.pass("Login Page", MediaEntityBuilder.createScreenCaptureFromPath(loginPageSS).build());
        
        Thread.sleep(2000);

        //Perform Login
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        test.log(Status.INFO, "Entered login credentials");
        log.info("Entered login credentials");

        String credentialsSS = ScreenshotUtil.captureScreenshot(driver, "Login", "Login credentials Added");
   //   test.addScreenCaptureFromPath(credentialsSS);   
        test.pass("Credential Added", MediaEntityBuilder.createScreenCaptureFromPath(credentialsSS).build());

        Thread.sleep(2000);
        loginPage.clickLogin();

        //Verify login success
        productPage = new ProductPage(driver);
        if (productPage != null && productPage.getProductPageTitle()) {
        Assert.assertTrue(productPage.getProductPageTitle(), "Login failed: Product page title is incorrect");
        test.log(Status.PASS, "Login successful");
        log.info("Login successful");

        String dashboardSS = ScreenshotUtil.captureScreenshot(driver, "Login", "Dashboard screen");
//		test.addScreenCaptureFromPath(dashboardSS);
        test.pass("Logged In Pass", MediaEntityBuilder.createScreenCaptureFromPath(dashboardSS).build());
        } else {
          test.log(Status.FAIL, "Login failed");
          log.info("Login failed");

          String failureSS = ScreenshotUtil.captureScreenshot(driver, "Login", "Login_Failure");
          test.fail("Login Failed", MediaEntityBuilder.createScreenCaptureFromPath(failureSS).build());
          Assert.fail("Login failed! Product page was not loaded.");
       }
		} catch (IOException e) {
            test.log(Status.WARNING, "Failed to attach one or more screenshot: " + e.getMessage());
		}
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        try {
            menuAndFooterPage = new MenuAndFooterPage(driver);
            Thread.sleep(2000);

            //Capture failure screenshot(only if the test fails)
            if (result.getStatus() == ITestResult.FAILURE) {
                String failureSS = ScreenshotUtil.captureScreenshot(driver, result.getName(), "Failure_Screen");
                test.fail("Test Failed!", MediaEntityBuilder.createScreenCaptureFromPath(failureSS).build());
                test.log(Status.FAIL, "Test failed due to: " + result.getThrowable());
                log.error("Test failed due to assertion or exception: ", result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, "Test passed successfully");
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.log(Status.SKIP, "Test was skipped");
            }

            //Perform logout(only if test passed)
            if (result.getStatus() == ITestResult.SUCCESS && menuAndFooterPage.isMenuButtonPresent()) {
                menuAndFooterPage.clickMenuOpen();
                test.log(Status.INFO, "Click on Menu->Opened Menu Bar");
                log.info("Opened Menu Bar");

                Thread.sleep(2000);
                
                String menuSS = ScreenshotUtil.captureScreenshot(driver, "Logout", "MenuBar_Open");
                test.pass("Menu", MediaEntityBuilder.createScreenCaptureFromPath(menuSS).build());                

                menuAndFooterPage.clickLogout();
                test.log(Status.PASS, "Click on Logout->Logout successful");
                log.info("Click on Logout->Logout successful");

                String logoutSS = ScreenshotUtil.captureScreenshot(driver, "Logout", "LoggedOut");
                test.pass("Logged Out", MediaEntityBuilder.createScreenCaptureFromPath(logoutSS).build());
            } else {
                test.log(Status.FAIL, "Menu button not found or test failed, skipping logout");
                log.info("Menu button not found or test failed, skipping logout");
            }
        } catch (Exception e) {
            test.log(Status.ERROR, "An error occurred during logout: " + e.getMessage());
            log.error("An error occurred during logout", e);
        } finally {
            //close the browser
            if (driver != null) {
                driver.quit();
                test.log(Status.INFO, "Browser closed");
            }
            extent.flush();
        }
    }
}