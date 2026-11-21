package org.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    public static final String URL= "https://www.saucedemo.com/cart.html";

    private final By checkOut = By.cssSelector("#checkout");

    public CartPage(WebDriver driver){
        super(driver);
    }

    public CheckOutPage checkOut(){
        click(checkOut);
        return new CheckOutPage(driver);
    }

}
