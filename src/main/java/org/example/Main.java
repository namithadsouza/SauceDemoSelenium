package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    @Test
    public void goToSauceDemo() {
        WebDriver  driver= new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.quit();
        }

}
