package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.pages.ProductsPage;
import com.swagLabs.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Swag lab")
@Feature("UI products  Management")
@Story("products Page Tests")
@Severity(SeverityLevel.CRITICAL)
@Owner("Fatma Eltahan and Mahmoud Amir")
public class ProductsTests extends BaseTest{

    //variables
    private int expectedCartCount = 0;

    @Description("Verify user can add  items to the cart")
    @Test(groups = "valid")
    public void addItemsToCartTC()
    {
        expectedCartCount =  new ProductsPage(driver)
                .clickOnAddToCartButton(test_data.getJsonData("backpack"))
                .scrollToProduct(test_data.getJsonData("fleeceJacket"))
                .clickOnAddToCartButton(test_data.getJsonData("fleeceJacket"))
                .verifyProductsAreAddedToCart(expectedCartCount,test_data.getJsonData("backpack"),test_data.getJsonData("fleeceJacket"));
    }


    @Description("Verify user can remove  items from the cart")
    @Test(groups = "valid",dependsOnMethods = {"addItemsToCartTC"})
    public void removeItemsFromCartTC()
    {
        expectedCartCount =  new ProductsPage(driver)
                .clickOnRemoveButton(test_data.getJsonData("backpack"))
                .verifyProductsAreRemovedFromCart(expectedCartCount,test_data.getJsonData("backpack"));
    }

    @Description("Verify user can sort items by  name (Z to A)")
    @Test(groups = "valid")
    public void sortItemsByNameZToATC()
    {
        new ProductsPage(driver)
                .selectSortOption(test_data.getJsonData("sortOption.Z-A"))
                .verifySortingByNameDescending();
    }

    @Description("Verify user can sort items by price (low to high)")
    @Test(groups = "valid")
    public void sortItemsByPriceLowToHighTC()
    {
        new ProductsPage(driver)
                .selectSortOption(test_data.getJsonData("sortOption.low-high"))
                .verifySortingByPriceAscending();
    }



    //Configurations

    @Step("Setting up preconditions and Load Test data")
    @BeforeClass(alwaysRun = true)
    protected void preconditions()
    {
        test_data=new JsonReader("products-data");
        login_data=new JsonReader("login-data");

        driver=new GUIDriver();
        new LoginPage(driver).navigate()
                .enterUsername(login_data.getJsonData("validUsername"))
                .enterPassword(login_data.getJsonData("validPassword"))
                .clickOnLoginButton();


    }



    @Step("Tearing down the driver")
    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        driver.quitDriver();

    }


}
