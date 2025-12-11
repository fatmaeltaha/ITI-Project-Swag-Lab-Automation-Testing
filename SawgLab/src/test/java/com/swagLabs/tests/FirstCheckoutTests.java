package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.CartPage;
import com.swagLabs.pages.FirstCheckoutPage;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.pages.ProductsPage;
import com.swagLabs.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Swag lab")
@Feature("UI User Management")
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Fatma Eltahan and Mahmoud Amir")
public class FirstCheckoutTests extends BaseTest {

    //Tests

    @Description("Verify That The user can navigate to second checkout page")
    @Test(groups = "valid")
    public void CheckoutWithValidDataTc()
    {
        new FirstCheckoutPage(driver).enterfirstname(test_data.getJsonData("validFirstName"))
                .enterlastname(test_data.getJsonData("validLastName"))
                .enterpostalcode(test_data.getJsonData("validPostalCode"))
                .clickOnContinueButton()
                .VerifyNavigateToSecondCheckout(test_data.getJsonData("SecondCheckoutPageUrl"));
    }

    @Description("Verify Checkout with First Name Less Than 3 Characters with valid other data")
    @Test(groups = "invalid")
    public void CheckoutWithInValidFirstName_LessThan3Chars_Tc()
    {
        new FirstCheckoutPage(driver).enterfirstname(test_data.getJsonData("invalidFirstName_LessThan3Chars"))
                .enterlastname(test_data.getJsonData("validLastName"))
                .enterpostalcode(test_data.getJsonData("validPostalCode"))
                .clickOnContinueButton()
                .VerifyTheVisibilityofErrorMessage();
    }

    @Description("Verify Checkout with First Name Exceeds 30 Characters with valid other data")
    @Test(groups = "invalid")
    public void CheckoutWithInvalidFirstName_Exceeds30Chars_TC()
    {
        new FirstCheckoutPage(driver).enterfirstname(test_data.getJsonData("invalidFirstName_Exceeds30Chars"))
                .enterlastname(test_data.getJsonData("validLastName"))
                .enterpostalcode(test_data.getJsonData("validPostalCode"))
                .clickOnContinueButton()
                .VerifyTheVisibilityofErrorMessage();
    }

    @Description("Verify Checkout with empty First Name with valid other data")
    @Test(groups = "invalid")
    public void CheckoutWithEmptyFirstNameTc()
    {
        new FirstCheckoutPage(driver).enterlastname(test_data.getJsonData("validLastName"))
                .enterpostalcode(test_data.getJsonData("validPostalCode"))
                .clickOnContinueButton()
                .VerifyErrorMessage(test_data.getJsonData("ErrorMessageforEmptyFirstName"));
    }

    @Description("Verify Checkout with Last Name Less Than 3 Characters with valid other data")
    @Test(groups = "invalid")
    public void CheckoutWithInValidLastName_LessThan3Chars_Tc()
    {
        new FirstCheckoutPage(driver).enterfirstname(test_data.getJsonData("validFirstName"))
                .enterlastname(test_data.getJsonData("invalidLastName_LessThan3Chars"))
                .enterpostalcode(test_data.getJsonData("validPostalCode"))
                .clickOnContinueButton()
                .VerifyTheVisibilityofErrorMessage();
    }

    @Description("Verify Checkout with Last Name Exceeds 30 Characters with valid other data")
    @Test(groups = "invalid")
    public void CheckoutWithInvalidLastName_Exceeds30Chars_TC()
    {
        new FirstCheckoutPage(driver).enterfirstname(test_data.getJsonData("validFirstName"))
                .enterlastname(test_data.getJsonData("invalidLastName_Exceeds30Chars"))
                .enterpostalcode(test_data.getJsonData("validPostalCode"))
                .clickOnContinueButton()
                .VerifyTheVisibilityofErrorMessage();
    }

    @Description("Verify Checkout with empty Last Name with valid other data")
    @Test(groups = "invalid")
    public void CheckoutWithEmptyLastNameTc()
    {
        new FirstCheckoutPage(driver).enterfirstname(test_data.getJsonData("validFirstName"))
                .enterpostalcode(test_data.getJsonData("validPostalCode"))
                .clickOnContinueButton()
                .VerifyErrorMessage(test_data.getJsonData("ErrorMessageforEmptyLastName"));
    }

    @Description("Verify Checkout with empty Postal Code with valid other data")
    @Test(groups = "invalid")
    public void CheckoutWithEmptyPostalCodeTc()
    {
        new FirstCheckoutPage(driver).enterfirstname(test_data.getJsonData("validFirstName"))
                .enterlastname(test_data.getJsonData("validLastName"))
                .clickOnContinueButton()
                .VerifyErrorMessage(test_data.getJsonData("ErrorMessageforEmptyPostalCode"));
    }

    @Description("Verify That The user can navigate back To cart page")
    @Test(groups = "valid")
    public void VerifyCancelation()
    {
        new FirstCheckoutPage(driver).clickOnCancelButton()
                .VerifyNavigateBackToCartPage(test_data.getJsonData("CartPageUrl"));
    }

    //Configurations
    @Step("Setting up preconditions and Checkout Test data")
    @BeforeMethod(alwaysRun = true)
    protected void preconditions()
    {
        test_data=new JsonReader("firstcheckout-data");
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
    }

    @Step("Tearing down the driver")
    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.quitDriver();
    }

}
