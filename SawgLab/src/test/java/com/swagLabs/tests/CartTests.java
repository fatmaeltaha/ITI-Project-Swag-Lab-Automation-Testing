package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.CartPage;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.pages.ProductsPage;
import com.swagLabs.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Swag lab")
@Feature("UI User Management")
@Story("Cart")
@Owner("Fatma Eltahan and Mahmoud Amir")
public class CartTests extends BaseTest{
    private int expectedCartCount=0;
    //Tests

    @Description("Verify 'Continue Shopping' button navigates back to Inventory")
    @Test(groups = "valid")
    public void ContinueShopping()
    {
        new CartPage(driver).navigateToCartPage()
                .clickOnContinueShoppingButton()
                .verifyNavigateBackToInventory(test_data.getJsonData("inventoryUrl"))
                .navigateToCartPage();
    }

    @Description("Verify 'Checkout' button navigates to Checkoutpage")
    @Test(groups = "valid")
    public void Checkout()
    {
        new CartPage(driver).navigateToCartPage()
                .clickOnCheckoutButton()
                .verifyNavigateToCheckout(test_data.getJsonData("checkoutUrl"))
                .navigateToCartPage();
    }

    @Description("Verify products exist in cart page")
    @Test(groups = "valid")
    public void checkingProductsIsFoundedInCardPage()
    {
        new CartPage(driver).navigateToCartPage()
                .verifyProductsExistInCartPage(products_data.getJsonData("backpack"),
                        products_data.getJsonData("fleeceJacket"),
                        products_data.getJsonData("redTShirt"));

    }

    @Description("Verify that the user can navigate to item page by clicking on the product name in the cart")
    @Test(groups = "valid")
    public void checkingProductsIsRemovedOnCardPage()
    {
        new CartPage(driver).navigateToCartPage()
                .clickOnRemoveButton(products_data.getJsonData("redTShirt"))
                .verifyProductsAreRemovedFromCart(expectedCartCount,products_data.getJsonData("redTShirt"));
    }





    //Configurations

    @Step("Setting up preconditions and Load Test data")
    @BeforeClass(alwaysRun = true)
    protected void preconditions()
    {
        test_data=new JsonReader("cart-data");
        login_data=new JsonReader("login-data");
        products_data= new JsonReader("products-data");

        driver=new GUIDriver();
        new LoginPage(driver).navigate()
                .enterUsername(login_data.getJsonData("validUsername"))
                .enterPassword(login_data.getJsonData("validPassword"))
                .clickOnLoginButton();

        expectedCartCount =  new ProductsPage(driver)
            .clickOnAddToCartButton(products_data.getJsonData("backpack"))
            .scrollToProduct(products_data.getJsonData("fleeceJacket"))
            .clickOnAddToCartButton(products_data.getJsonData("fleeceJacket"))
            .scrollToProduct(products_data.getJsonData("redTShirt"))
                .clickOnAddToCartButton(products_data.getJsonData("redTShirt"))
            .verifyProductsAreAddedToCart(expectedCartCount,products_data.getJsonData("backpack"),
                    products_data.getJsonData("fleeceJacket"),
                    products_data.getJsonData("redTShirt"));

    }



    @Step("Tearing down the driver")
    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        driver.quitDriver();

    }

}
