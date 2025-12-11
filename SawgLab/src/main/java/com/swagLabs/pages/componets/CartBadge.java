package com.swagLabs.pages.componets;

import com.swagLabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartBadge {
    private final GUIDriver driver;

    //Constructor
    public CartBadge(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private final By cartLinkLocator= By.cssSelector("[data-test='shopping-cart-link']");
    public final By cartBadgeLocator= By.cssSelector("[data-test='shopping-cart-badge']");

    //Actions
    @Step("Click on cart badge")
    public CartBadge clickOnCartLink()
    {
        driver.element().click(cartLinkLocator);
        return this;
    }

    @Step("Get cart item count")
    public String getCartItemCount()
    {
        return driver.element().getText(cartBadgeLocator);

    }


}
