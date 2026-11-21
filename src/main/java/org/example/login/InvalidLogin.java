package org.example.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidLogin {
    WebDriver driver;
    @BeforeMethod
    public void getChromeDriver(){
        driver= new ChromeDriver();
    }

    @AfterMethod
    public void quitChromeDriver(){
        driver.quit();
    }

    @Test
    public void lockedOutUser() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("locked_out_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        String msg= driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(msg, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void problemUser() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("problem_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("namitha");
        driver.findElement(By.cssSelector("#login-button")).click();
        String msg= driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(msg, "Epic sadface: Username and password do not match any user in this service");
    }


}
