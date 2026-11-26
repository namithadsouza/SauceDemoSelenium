package org.example.saucedemo.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.saucedemo.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

import io.qameta.allure.Allure;

public class TestListener implements ITestListener {

    protected final Logger log = LogManager.getLogger(getClass());

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test Passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test Failed: {}", result.getName());

        WebDriver driver = DriverFactory.getDriver();
        log.info("Driver on thread {} is {}", Thread.currentThread().getName(), driver);

        if (driver == null) {
            log.warn("Driver is null, cannot capture screenshot for {}", result.getName());
            return;
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    result.getName() + " - Failure Screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    "png");                      // no leading dot
            log.info("Screenshot attached to Allure: {}", result.getName());
        } catch (Exception e) {
            log.error("Unable to capture screenshot", e);   // pass the exception to see the stack trace
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Testcase is skipped: {}"
                , result.getName());
    }
}
