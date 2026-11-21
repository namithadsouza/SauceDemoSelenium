package org.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage {

    public static final String STEP_ONE_URL = "https://www.saucedemo.com/checkout-step-one.html";
    public static final String STEP_TWO_URL = "https://www.saucedemo.com/checkout-step-two.html";
    public static final String COMPLETE_URL = "https://www.saucedemo.com/checkout-complete.html";

    private final By firstName= By.cssSelector("input[name='firstName']");
    private final By lastName = By.cssSelector("input[name='lastName']");
    private final By postalCode = By.cssSelector("input[name='postalCode']");
    private final By continueButton = By.cssSelector("input[name='continue']");
    private final By finishButton = By.cssSelector("button[name='finish']");
    private final By completeHeader = By.cssSelector("h2[class = 'complete-header']");

    protected CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage checkOutPageOne(String fName, String lName, String zip){
        type(firstName,fName);
        type(lastName, lName);
        type(postalCode,zip);
        click(continueButton);
        return this;
    }

    public CheckOutPage finish() {
        click(finishButton);
        return this;
    }

    public String getConfirmationMessage() {
        return getText(completeHeader);
    }
}
