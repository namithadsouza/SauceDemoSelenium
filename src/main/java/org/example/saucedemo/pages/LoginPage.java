package org.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    public static final String URL = "https://www.saucedemo.com/";

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    public InventoryPage login(String user, String pass) {
        type(usernameInput, user);
        type(passwordInput, pass);
        click(loginButton);
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
