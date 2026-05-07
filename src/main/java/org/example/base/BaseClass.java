package org.example.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utility.ConfigReader;
import org.example.utility.DriverFactory;
import org.example.utility.ScreenshotUtil;
import org.example.utility.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;


import java.time.Duration;

public class BaseClass {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static ExtentReports extent =
            ExtentReportManager.getReportInstance();
    protected ExtentTest test;

    protected static final Logger log =
            LogManager.getLogger(BaseClass.class);

    @BeforeMethod
    public void setUp(ITestResult result) {

        log.info("========== TEST SETUP STARTED ==========");

        test = extent.createTest(result.getMethod().getMethodName());
        driver = DriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String baseUrl = ConfigReader.get("baseUrl");
        log.info("Launching application: {}", baseUrl);
        test.info("Launching application: " + baseUrl);

        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("TEST FAILED: {}", testName);
            test.fail("Test Failed");

            ScreenshotUtil.takeScreenshot(driver, testName + "_FAILED");
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            log.info("TEST PASSED: {}", testName);
            test.pass("Test Passed");

            ScreenshotUtil.takeScreenshot(driver, testName + "_PASSED");
        }
        else {
            log.warn("TEST SKIPPED: {}", testName);
            test.skip("Test Skipped");
        }
        if (driver != null) {
            log.info("Closing browser for test: {}", testName);
            driver.quit();
        }
        log.info("========== TEST TEARDOWN COMPLETED ==========");
    }

    @AfterMethod
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}