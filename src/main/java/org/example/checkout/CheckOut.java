package org.example.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class CheckOut {
    //login
    //cart
    //checkout
    //logout

   @DataProvider(name="loginData")
   public Object[][] getData(){
       return new Object[][]{
               {"standard_user", "secret_sauce"}
       };
   }

    @DataProvider(name="checkoutData")
    public Object[][] getCheckoutData(){
        return new Object[][]{
                {"namitha", "dsouza","574509"}
        };
    }

   WebDriver driver;

    @BeforeClass
    public void setup(){
        driver= new ChromeDriver();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test(dataProvider = "loginData")
    public void login(String username, String password){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }



    @Test(dependsOnMethods = "login")
    public void addToCart(){
        Optional<WebElement> firstItem = driver.findElements(By.cssSelector(".inventory_item")).stream().findFirst();
        firstItem.ifPresent(webElement -> webElement.findElement(By.cssSelector("button")).click());
    }

    @Test(dataProvider ="checkoutData", dependsOnMethods = "addToCart")
    public void checkOut(String fName, String lName, String postalCode){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/cart.html");
        driver.findElement(By.cssSelector("#checkout")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html");
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(fName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lName);
        driver.findElement(By.cssSelector("input[name='postalCode']")).sendKeys(postalCode);
        driver.findElement(By.cssSelector("input[name='continue']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");
        driver.findElement(By.cssSelector("button[name='finish']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html");


    }

    @Test(dependsOnMethods = "checkOut")
    public void logOut(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("button[id='react-burger-menu-btn']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout"))).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");

    }
}
