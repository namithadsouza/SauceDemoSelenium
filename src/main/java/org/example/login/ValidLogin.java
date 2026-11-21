package org.example.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class ValidLogin {
    WebDriver driver;
    @BeforeMethod
    public void getChromeDriver(){
        driver= new ChromeDriver();
    }

    @Test
    public void goToSauceDemo() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
    }

    @AfterMethod
    public void quitChromeDriver(){
        driver.quit();
    }
}
