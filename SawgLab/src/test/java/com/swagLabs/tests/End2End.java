package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.*;
import com.swagLabs.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.*;

public class End2End  extends BaseTest {


    //Tests

    @Description("Verify that the user can log in with valid data")
    @Test(priority = 1)
    public void loginWithValidDataTC()
    {
        new LoginPage(driver).navigate()
                .enterUsername(login_data.getJsonData("validUsername"))
                .enterPassword(login_data.getJsonData("validPassword"))
                .clickOnLoginButton()
                .verifySuccessfulLogin(login_data.getJsonData("inventoryUrl"));

    }

    @Description("Verify user can add  items to the cart")
    @Test(priority = 2)
    public void addItemsToCartTC()
    {
        new ProductsPage(driver)
                .clickOnAddToCartButton(products_data.getJsonData("backpack"))
                .scrollToProduct(products_data.getJsonData("fleeceJacket"))
                .clickOnAddToCartButton(products_data.getJsonData("fleeceJacket"));
    }

    @Description("Verify 'Checkout' button navigates to Checkoutpage")
    @Test(priority = 3)
    public void Checkout()
    {
        new CartPage(driver).navigateToCartPage()
                .clickOnCheckoutButton()
                .verifyNavigateToCheckout(cart_data.getJsonData("checkoutUrl"));
    }

    @Description("Verify That The user can navigate to second checkout page")
    @Test(priority = 4)
    public void CheckoutWithValidDataTc()
    {
        new FirstCheckoutPage(driver).enterfirstname(firstcheckout_data.getJsonData("validFirstName"))
                .enterlastname(firstcheckout_data.getJsonData("validLastName"))
                .enterpostalcode(firstcheckout_data.getJsonData("validPostalCode"))
                .clickOnContinueButton()
                .VerifyNavigateToSecondCheckout(firstcheckout_data.getJsonData("SecondCheckoutPageUrl"));
    }

    @Description("Verify That 'Finish' Button Navigates To order Confirmation Page")
    @Test(priority = 5)
    public void SuccessfullyNavigatesToOrderConfirmationPage()
    {
        new SecondCheckoutPage(driver).clickOnFinishButton()
                .VerifyNavigatesToOrderConfirmationPage(secondcheckout_data.getJsonData("confirmationUrl"));
    }

    @Description("Verify That 'Back Home' Button Navigates Back To Products Page")
    @Test(priority = 6)
    public void SuccessfullyNavigatesToProductsPage()
    {
        new ConfirmationOrderPage(driver).clickOnBackHome()
                .VerifyNavigatesToProductsPage( confirmation_data.getJsonData("ProductsUrl"));
    }



    //Configurations
    @Step("Setting up preconditions and Load Test data")
    @BeforeClass(alwaysRun = true)
    protected void preconditions()
    {
        login_data=new JsonReader("login-data");
        products_data = new JsonReader("products-data");
        cart_data =new JsonReader("cart-data");
        firstcheckout_data=new JsonReader("firstcheckout-data");
        secondcheckout_data=new JsonReader("secondcheckout-data");
        confirmation_data=new JsonReader("confirmationOrder-data");

        driver=new GUIDriver();

    }


    @Step("Tearing down the driver")
    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        driver.quitDriver();

    }

}
