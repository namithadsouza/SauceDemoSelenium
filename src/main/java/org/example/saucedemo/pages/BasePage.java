package org.example.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Logger log = LogManager.getLogger(getClass());

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.debug("{} initialised", getClass().getSimpleName());
    }

    protected void click(By locator) {
        log.info("Clicking {}", locator);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (TimeoutException e) {
            log.error("Element not clickable within timeout: {}", locator, e);
            throw e;
        }
    }

    protected void type(By locator, String text) {
        log.info("Typing '{}' into {}", text, locator);
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            el.clear();
            el.sendKeys(text);
        } catch (TimeoutException e) {
            log.error("Element not visible within timeout: {}", locator, e);
            throw e;
        }
    }

    /** Same as type(), but never writes the value to the log (passwords, tokens). */
    protected void typeSecret(By locator, String text) {
        log.info("Typing [hidden] into {}", locator);
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            el.clear();
            el.sendKeys(text);
        } catch (TimeoutException e) {
            log.error("Element not visible within timeout: {}", locator, e);
            throw e;
        }
    }

    protected String getText(By locator) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
            log.debug("Text of {} = '{}'", locator, text);
            return text;
        } catch (TimeoutException e) {
            log.error("Could not read text, element not visible: {}", locator, e);
            throw e;
        }
    }

    protected List<WebElement> findAll(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        log.debug("Found {} element(s) for {}", elements.size(), locator);
        return elements;
    }

    public void waitForUrl(String url) {
        log.info("Waiting for URL: {}", url);
        try {
            wait.until(ExpectedConditions.urlToBe(url));
        } catch (TimeoutException e) {
            log.error("URL did not become {}. Current URL: {}", url, driver.getCurrentUrl(), e);
            throw e;
        }
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        log.debug("Current URL: {}", url);
        return url;
    }
}