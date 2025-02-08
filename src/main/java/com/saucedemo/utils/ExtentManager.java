package com.saucedemo.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance(String testCaseName) {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportFolderPath = System.getProperty("user.dir") + "/test-output/" + testCaseName + "/";
            new File(reportFolderPath).mkdirs();

//            String reportPath = reportFolderPath + "ExtentReport.html";
            String reportPath = reportFolderPath + "ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            //System Info
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }
    
    public static String getScreenshotPath(String testCaseName) {
        String screenshotFolderPath = System.getProperty("user.dir") + "/test-output/" + testCaseName + "/screenshots/";
        new File(screenshotFolderPath).mkdirs();

        // Return path where screenshots should be saved
        return screenshotFolderPath;
    }
}