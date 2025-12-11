package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.*;
import com.swagLabs.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Swag lab")
@Feature("UI User Management")
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Fatma Eltahan and Mahmoud Amir")
public class SecondCheckoutTests extends BaseTest {

    //Tests
    @Description("Verify That 'Finish' Button Navigates To order Confirmation Page")
    @Test(groups = "valid")
    public void SuccessfullyNavigatesToOrderConfirmationPage()
    {
        new SecondCheckoutPage(driver).clickOnFinishButton()
                .VerifyNavigatesToOrderConfirmationPage(test_data.getJsonData("confirmationUrl"));
    }

    @Description("Verify That Cart Is Reset After Confirmation")
    @Test(groups = "valid")
    public void CartIsReset()
    {
        new SecondCheckoutPage(driver).clickOnFinishButton()
                .VerifyCartCountDisappears();
    }

    @Description("Verify That 'Cancel' Button Navigates Back To inventory Page")
    @Test(groups = "valid")
    public void SuccessfullyNavigatesBackToInventoryPage()
    {
        new SecondCheckoutPage(driver).clickOnCancelButton()
                .VerifyNavigatesBackToInventoryPage(test_data.getJsonData("inventoryUrl"));
    }


    //Configurations
    @Step("Setting up preconditions and Second-Checkout Test data")
    @BeforeMethod(alwaysRun = true)
    protected void preconditions()
    {
        test_data = new JsonReader("secondcheckout-data");
        firstcheckout_data=new JsonReader("firstcheckout-data");
        login_data=new JsonReader("login-data");
        products_data= new JsonReader("products-data");

        driver=new GUIDriver();
        new LoginPage(driver).navigate()
                .enterUsername(login_data.getJsonData("validUsername"))
                .enterPassword(login_data.getJsonData("validPassword"))
                .clickOnLoginButton();

        new ProductsPage(driver).clickOnAddToCartButton(products_data.getJsonData("backpack"))
                .scrollToProduct(products_data.getJsonData("fleeceJacket"))
                .clickOnAddToCartButton(products_data.getJsonData("fleeceJacket"))
                .scrollToProduct(products_data.getJsonData("redTShirt"))
                .clickOnAddToCartButton(products_data.getJsonData("redTShirt"));


        new CartPage(driver).navigateToCartPage()
                .clickOnCheckoutButton();

        new FirstCheckoutPage(driver).enterfirstname(firstcheckout_data.getJsonData("validFirstName"))
                .enterlastname(firstcheckout_data.getJsonData("validLastName"))
                .enterpostalcode(firstcheckout_data.getJsonData("validPostalCode"))
                .clickOnContinueButton();
    }

    @Step("Tearing down the driver")
    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.quitDriver();
    }
}
