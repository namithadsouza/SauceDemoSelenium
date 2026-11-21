package org.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage{

    public static final String URL = "https://www.saucedemo.com/inventory.html";

    private final By firstItemAddButton = By.cssSelector(".inventory_item button");
    private final By cartBadge          = By.cssSelector(".shopping_cart_badge");
    private final By cartLink           = By.cssSelector(".shopping_cart_link");
    private final By menuButton         = By.id("react-burger-menu-btn");
    private final By logoutLink         = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage addFirstItemToCart() {
        click(firstItemAddButton);
        return this;
    }

    public String getCartCount() {
        return getText(cartBadge);
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public LoginPage logout() {
        click(menuButton);
        click(logoutLink);
        return new LoginPage(driver);
    }
}
