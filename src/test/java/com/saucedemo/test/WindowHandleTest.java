package com.saucedemo.test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.MenuAndFooterPage;
import com.saucedemo.pages.NewWindowPage;
import com.saucedemo.utils.ScreenshotUtil;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WindowHandleTest extends BaseTest {
	private NewWindowPage newWindowPage;
	private MenuAndFooterPage menuAndFooterPage;
	private static final Logger log = LogManager.getLogger(WindowHandleTest.class);

	@Test
	public void testTwitterWindowHandle() throws InterruptedException {
		try {
			newWindowPage = new NewWindowPage(driver);
			menuAndFooterPage = new MenuAndFooterPage(driver);

			Thread.sleep(2000);
			log.info("Scroll to Twitter icon");
			test.log(Status.INFO, "Scroll to Twitter icon");
			menuAndFooterPage.scrollToTwitter();

			Thread.sleep(2000);

			String twitterSS = ScreenshotUtil.captureScreenshot(driver, "testTwitterWindow", "Twitter scroll Verification");
			test.pass("Twitter scroll Verification", MediaEntityBuilder.createScreenCaptureFromPath(twitterSS).build());
			
			log.info("Clicking on Twitter link...");
			test.log(Status.INFO, "Clicking on Twitter link...");
			menuAndFooterPage.clickTwitter();

			test.log(Status.INFO, "Switching to new tab...");
			newWindowPage.switchToNewTab();

			String twitterPageSS = ScreenshotUtil.captureScreenshot(driver, "testTwitterWindow", "Twitter Page Verification");
			test.pass("Twitter Page Verification", MediaEntityBuilder.createScreenCaptureFromPath(twitterPageSS).build());

			boolean isTwitterPage = newWindowPage.verifyWindowSwitchTwitter();
			Assert.assertTrue(isTwitterPage, "Twitter page verification failed!");

			log.info("Twitter page verification successful");
			test.log(Status.PASS, "Twitter page verification successful");

			test.log(Status.INFO, "Closing Twitter tab and switching back...");
			newWindowPage.closeCurrentTabAndSwitch();
			
			String DashboardSS = ScreenshotUtil.captureScreenshot(driver, "testTwitterWindow", "Dashboard Page switch");
			test.pass("Dashboard Page switch", MediaEntityBuilder.createScreenCaptureFromPath(DashboardSS).build());
		} catch (IOException e) {
			test.log(Status.FAIL, "Test case failed due to exception: " + e.getMessage());
			log.error("Test failed due to exception", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}
}