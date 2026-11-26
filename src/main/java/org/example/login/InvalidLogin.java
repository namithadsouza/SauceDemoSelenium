package org.example.login;

import org.example.saucedemo.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidLogin {
    WebDriver driver ;
    @BeforeMethod
    public void getChromeDriver(){
        //driver= new ChromeDriver(); - Its failing to take screenshot. so use DriverFactory as TestListenr is using it
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void quitChromeDriver(){
        DriverFactory.quitDriver();
    }

    @Test
    public void lockedOutUser() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("locked_out_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        String msg= driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(msg, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void problemUser() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("problem_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("namitha");
        driver.findElement(By.cssSelector("#login-button")).click();
        String msg= driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(msg, "Epic sadface: Username and password do not match any user in this service");
    }


}
