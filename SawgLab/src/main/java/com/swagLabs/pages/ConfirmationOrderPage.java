package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ConfirmationOrderPage {
    private final GUIDriver driver ;

    //Constructor
    public ConfirmationOrderPage(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private final By backhome_button = By.id("back-to-products");

    //Actions
    @Step ("Click On 'Back Home' Button")
    public ConfirmationOrderPage clickOnBackHome()
    {
        driver.element().click(backhome_button);
        return this;
    }

    //Validation
    @Step ("Verify 'Back Home' Navigates To Products Page ")
    public ConfirmationOrderPage VerifyNavigatesToProductsPage(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }
}
