package org.example.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.saucedemo.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class SessionBaseTest {
    protected final Logger log =
            LogManager.getLogger(SessionBaseTest.class);
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        DriverFactory.initDriver();
        log.info("Driver initialized on thread: {} "
                ,Thread.currentThread().getName());

        log.info("Driver instance: {} "
                , DriverFactory.getDriver());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
