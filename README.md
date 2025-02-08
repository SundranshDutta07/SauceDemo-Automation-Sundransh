SauceDemo Automation Framework

#Project Overview

This is a Selenium-based automation framework for testing the SauceDemo website. The framework follows the Page Object Model (POM) structure and uses TestNG for test execution. It also integrates ExtentReports for detailed reporting and Screenshot Utility for capturing screenshots during test execution.


## Project Structure

SauceDemo/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.saucedemo.base/
│   │   │   │   ├── BaseTest.java                    # WebDriver Setup & Teardown
│   │   │   ├── com.saucedemo.pages/
│   │   │   │   ├── LoginPage.java                   # Page Object for Login
│   │   │   │   ├── MenuAndFooter.java               # Page Object for Menu and Footer Links
│   │   │   │   ├── CartPage.java                    # Page Object for Cart
│   │   │   │   ├── CheckoutYourInformationPage.java # Page Object for Checkout Your Information
│   │   │   │   ├── CheckoutOverviewPage.java        # Page Object for Checkout Overview
│   │   │   │   ├── CheckoutCompletePage.java        # Page Object for Checkout Success
│   │   │   │   ├── ProductPage.java                 # Page Object for Product/Dashboard Page
│   │   │   │   ├── ProductDetailPage.java           # Page Object for Product Details of a single product
│   │   │   ├── com.saucedemo.utils/
│   │   │   │   ├── ScreenshotUtil.java              # Screenshot Capture Utility
│   │   │   │   ├── TestListener.java     
│   │   │   │   ├── ExtentManager.java               # ExtentReports Configuration
│   ├── test/
│   │   ├── java/
│   │   │   ├── com.saucedemo.tests/
│   │   │   │   ├── LoginTest.java                   # Test Case for Login
│   │   │   │   ├── CheckoutTest.java                # Test Case for Checkout
│   │   │   │   ├── WindowHandleTest.java            # Test Case for WindowHandle
│   ├── main/
│   │   ├── resources/
│   │   │   ├── log4j2.xml/                          # For generating the logs in console
│── reports/                                         # Test Execution Reports (ExtentReports)
│── test-output/                                     # TestNG Output Reports
│── pom.xml                                          # Maven Dependencies
│── testng.xml                                       # TestNG Test Suite Configuration
│── README.md                                        # Project Documentation



## Technologies Used
Programming Language: Java
Automation Tool: Selenium WebDriver
Test Framework: TestNG
Build Tool: Maven
Reporting: ExtentReports
Design Pattern: Page Object Model (POM)


## Prerequisites
### Install Required Tools
-Java 11+
-Eclipse
-Maven
-Google Chrome

### Clone the Repository
git clone https://github.com/SundranshDutta07/SauceDemo-Automation-Sundransh.git
cd SauceDemo


### Configure WebDriver
Download the ChromeDriver for your Chrome version and place it inside the drivers/ folder.


## How to Run the Tests
### Using TestNG in Eclipse
Right-click on testng.xml → Run As → TestNG Suite

### Using Maven Command Line
Run all tests using:
mvn clean test

## Reports & Logs
### ExtentReports
Location: reports/ExtentReport.html
Generates detailed test execution reports with screenshots.

### TestNG Reports
Location: test-output/
Includes execution results, logs, and failures.


## Test Scenarios Covered
### Login Tests
#### Verify successful login(Added to basetest-used for every test case as BeforeMethod)
#### Verify successful logout(Added to basetest-used for every test case as AfterMethod)
#### Verify login with invalid credentials
#### Verify login for LogedOut User

###Menu and Footer Tests
#### Verifying Scroll to footer elements (Twitter, Facebook, LinkedIn) then Window Handling and Validate new tab opening for social media links

### CheckoutTest
#### Verifying end to end test for valid product checkout


## Author: Sundransh Dutta
