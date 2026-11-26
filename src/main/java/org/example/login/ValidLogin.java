package org.example.login;

import org.example.saucedemo.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class ValidLogin {


    WebDriver driver ;
    @BeforeMethod
    public void getChromeDriver(){
        //driver= new ChromeDriver(); - Its failing to take screenshot. so use DriverFactory as TestListenr is using it
        DriverFactory.initDriver();
    }

    @Test
    public void goToSauceDemo() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void quitChromeDriver(){
        DriverFactory.quitDriver();
    }
}
