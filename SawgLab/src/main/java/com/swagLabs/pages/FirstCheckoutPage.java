package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class FirstCheckoutPage {
    private final GUIDriver driver;

    //Constructor
    public FirstCheckoutPage(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private final By firstname = By.id("first-name");
    private final By lastname = By.id("last-name");
    private final By postalcode = By.id("postal-code");
    private final By cancel_button = By.id("cancel");
    private final By continue_button = By.id("continue");
    private final By errorMessage = By.className("error-message-container");

    //Actions
    @Step("Enter First Name")
    public FirstCheckoutPage enterfirstname(String firstname)
    {
        driver.element().type(this.firstname,firstname);
        return this;
    }

    @Step("Enter Last Name")
    public FirstCheckoutPage enterlastname(String lastname)
    {
        driver.element().type(this.lastname,lastname);
        return this;
    }

    @Step("Enter Zip/Postal code")
    public FirstCheckoutPage enterpostalcode(String postalcode)
    {
        driver.element().type(this.postalcode,postalcode);
        return this;
    }

    @Step("Click on Continue Button")
    public FirstCheckoutPage clickOnContinueButton()
    {
        driver.element().click(continue_button);
        return this;
    }

    @Step("Click on Continue Button")
    public FirstCheckoutPage clickOnCancelButton()
    {
        driver.element().click(cancel_button);
        return this;
    }


    //Validation
    @Step("Verify 'Continue' Button Navigates to Second Checkout Page")
    public FirstCheckoutPage VerifyNavigateToSecondCheckout(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }

    @Step("Verify 'Cancel' Button Navigates back To Cart Page")
    public FirstCheckoutPage VerifyNavigateBackToCartPage(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }

    @Step("Verify error message")
    public FirstCheckoutPage VerifyErrorMessage(String expectedErrorMessage)
    {
        String actualErrorMessage=driver.element().getText(errorMessage);
        driver.verification().Equals(actualErrorMessage,expectedErrorMessage,"error message is not as expected");
        return this;
    }

    @Step("Verify error message is visible")
    public FirstCheckoutPage VerifyTheVisibilityofErrorMessage()
    {
        boolean isnotPresent = driver.get().findElements(errorMessage).isEmpty();

        if (isnotPresent) {
            fail("error message should appear! This field Can`t be less than 3 characters or more Than 30 Characters");
        }

        return this;
    }

}
