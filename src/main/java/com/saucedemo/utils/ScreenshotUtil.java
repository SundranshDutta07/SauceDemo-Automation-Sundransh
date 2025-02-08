package com.saucedemo.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testCaseName, String screenshotName) {
        //screenshot directory inside test-output
        String baseScreenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
        String testCaseDir = baseScreenshotDir + testCaseName + "/";

        File directory = new File(testCaseDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //timestamp for filenames
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = testCaseDir + screenshotName + "_" + timestamp + ".png";

        //Take Screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);

        try {
        	//return path for ExtentReport
            FileUtils.copyFile(srcFile, destFile);
            return destFile.getAbsolutePath(); 
        } catch (IOException e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
            return null;
        }
    }
}