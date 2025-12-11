package com.swagLabs.tests;


import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.*;
import com.swagLabs.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Swag lab")
@Feature("UI User Management")
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Fatma Eltahan and Mahmoud Amir")

public class ConfirmationOrderTest extends BaseTest {

    //Tests
    @Description("Verify That 'Back Home' Button Navigates Back To Products Page")
    @Test(groups = "valid")
    public void SuccessfullyNavigatesToProductsPage()
    {
        new ConfirmationOrderPage(driver).clickOnBackHome()
                .VerifyNavigatesToProductsPage(test_data.getJsonData("ProductsUrl"));
    }

    //Configurations
    @Step("Setting up preconditions and Confirmation Page Test data")
    @BeforeClass(alwaysRun = true)
    protected void preconditions()
    {
        test_data = new JsonReader("confirmationOrder-data");
        secondcheckout_data = new JsonReader("secondcheckout-data");
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

        new SecondCheckoutPage(driver).clickOnFinishButton();
    }

    @Step("Tearing down the driver")
    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        driver.quitDriver();
    }
}




