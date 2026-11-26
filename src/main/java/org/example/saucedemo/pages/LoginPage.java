package org.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage extends BasePage{
    public static final String URL = "https://www.saucedemo.com/";
    protected final Logger log = LogManager.getLogger(LoginPage.class);

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        log.info("Opening login page: {}", URL);
        driver.get(URL);
        return this;
    }

    public InventoryPage login(String user, String pass) {
        log.info("Logging in as user '{}'", user);
        type(usernameInput, user);
        typeSecret(passwordInput, pass);   // password is never written to the log
        click(loginButton);
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        String message = getText(errorMessage);
        log.info("Login error message displayed: '{}'", message);
        return message;
    }

}
