package org.example.saucedemo;

import org.example.saucedemo.pages.InventoryPage;
import org.example.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
    @DataProvider(name = "validUsers")
    public Object[][] validUsers() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidUsers")
    public Object[][] invalidUsers() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Sorry, this user has been locked out."},
                {"standard_user", "wrong_pass", "Username and password do not match any user in our service"},
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"}
        };
    }

    @Test(dataProvider = "validUsers")
    public void validLogin(String user, String pass) {
        InventoryPage inventory = new LoginPage(getDriver()).open().login(user, pass);
        inventory.waitForUrl(InventoryPage.URL);
        Assert.assertEquals(inventory.getCurrentUrl(), InventoryPage.URL);
    }

    @Test(dataProvider = "invalidUsers")
    public void invalidLogin(String user, String pass, String expectedError) {
        LoginPage login = new LoginPage(getDriver()).open();
        login.login(user, pass);
        Assert.assertTrue(login.getErrorMessage().contains(expectedError));
    }
}
