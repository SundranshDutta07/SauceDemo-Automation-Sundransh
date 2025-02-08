package com.saucedemo.utils;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TestListener implements ITestListener {

    private static ExtentReports report;
    private static ExtentTest test;

    public void onTestStart(ITestResult result) {
        String testCaseName = result.getMethod().getMethodName();
        report = ExtentManager.getInstance(testCaseName);
        test = report.createTest(testCaseName);
    }

    public void onTestSuccess(ITestResult result) {
        if (test != null) {
            test.pass("Test passed");
        }
        WebDriver driver = getDriver(result);
        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName(), "Success");
            if (screenshotPath != null) {
                try {
					test.pass("Screenshot on success", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
    }

    public void onTestFailure(ITestResult result) {
        if (test != null) {
            test.fail("Test failed: " + result.getThrowable().getMessage());
        }
        WebDriver driver = getDriver(result);
        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName(), "Failure");
            if (screenshotPath != null) {
                try {
					test.fail("Screenshot on failure", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
    }

    public void onTestSkipped(ITestResult result) {
        if (test != null) {
            test.skip("Test skipped: " + result.getThrowable().getMessage());
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional: Handle partial failures
    }

    public void onStart(ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
    }

    public void onFinish(ITestContext context) {
        if (report != null) {
            report.flush();
        }
    }

    private WebDriver getDriver(ITestResult result) {
        Object driverObj = result.getTestContext().getAttribute("WebDriver");
        if (driverObj instanceof WebDriver) {
            return (WebDriver) driverObj;
        }
        System.out.println("Warning: WebDriver instance is null or not set in TestContext.");
        return null;
    }
}