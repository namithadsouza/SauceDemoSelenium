package org.example.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.saucedemo.pages.CartPage;
import org.example.saucedemo.pages.CheckOutPage;
import org.example.saucedemo.pages.InventoryPage;
import org.example.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTest extends SessionBaseTest{
    protected final Logger log= LogManager.getLogger(CheckOutTest.class);
    @Test
    public void login() {
        log.info("Checkout test thread:{} ", Thread.currentThread().getName());
        log.info("Checkout driver:{}" , getDriver());
        InventoryPage inventory = new LoginPage(getDriver())
                .open()
                .login("standard_user", "secret_sauce");
        inventory.waitForUrl(InventoryPage.URL);
        Assert.assertEquals(inventory.getCurrentUrl(), InventoryPage.URL);
    }

    @Test(dependsOnMethods = "login")
    public void addToCart() {
        InventoryPage inventory = new InventoryPage(getDriver());
        inventory.addFirstItemToCart();
        Assert.assertEquals(inventory.getCartCount(), "1");
    }

    @Test(dependsOnMethods = "addToCart")
    public void checkOut() {
        CartPage cart = new InventoryPage(getDriver()).openCart();
        cart.waitForUrl(CartPage.URL);

        CheckOutPage checkout = cart.checkOut();
        checkout.waitForUrl(CheckOutPage.STEP_ONE_URL);

        checkout.checkOutPageOne("namitha", "dsouza", "574509");
        checkout.waitForUrl(CheckOutPage.STEP_TWO_URL);
        checkout.finish();
        checkout.waitForUrl(CheckOutPage.COMPLETE_URL);
        Assert.assertEquals(checkout.getConfirmationMessage(), "Thank you for your order!");
    }

    @Test(dependsOnMethods = "checkOut")
    public void logOut() {
        LoginPage loginPage = new InventoryPage(getDriver()).logout();
        loginPage.waitForUrl(LoginPage.URL);
        Assert.assertEquals(loginPage.getCurrentUrl(), LoginPage.URL);
    }
}
