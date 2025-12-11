package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static org.testng.Assert.fail;


public class SecondCheckoutPage {
   private final GUIDriver driver ;

   //Constructor
    public SecondCheckoutPage(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private final By finish_button = By.id("finish");
    private final By cancel_button = By.id("cancel");
    private final By cart_count = By.className("shopping_cart_badge");

    //Actions
    @Step ("Click On finish Button")
    public SecondCheckoutPage clickOnFinishButton()
    {
      driver.element().click(finish_button);
      return this;
    }

    @Step ("Click On cancel Button")
    public SecondCheckoutPage clickOnCancelButton()
    {
        driver.element().click(cancel_button);
        return this;
    }

    //Validation
    @Step ("Verify 'finish' Button Navigates To Order Confirmation Page")
    public SecondCheckoutPage VerifyNavigatesToOrderConfirmationPage(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        return this ;
    }

    @Step ("Verify Cart Count Disappears After The Confirmation Of Order")
    public SecondCheckoutPage VerifyCartCountDisappears()
    {
        boolean isPresent = !driver.get().findElements(cart_count).isEmpty();

        if (isPresent) {
            fail("Cart Count is still visible! It should have disappeared.");
        }

        return this;
    }

    @Step ("Verify 'Cancel' Button Navigates Back To inventory Page")
    public SecondCheckoutPage VerifyNavigatesBackToInventoryPage(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        return this ;
    }



}
