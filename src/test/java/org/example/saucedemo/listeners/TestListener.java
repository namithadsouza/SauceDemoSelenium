package org.example.saucedemo.listeners;

import io.qameta.allure.Allure;
import org.example.saucedemo.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

/**
 * Captures a screenshot when a TestNG test fails and attaches it to Allure.
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver == null) {
            return;
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    result.getName() + " - failure screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );
        } catch (Exception exception) {
            // Do not hide the original test failure if screenshot capture itself fails.
            Allure.addAttachment(
                    "Screenshot capture error",
                    "text/plain",
                    exception.toString(),
                    ".txt"
            );
        }
    }
}
