package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.pages.ProductsPage;
import com.swagLabs.pages.componets.SideMenu;
import com.swagLabs.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Swag lab")
@Feature("UI products  Management")
@Story("products Page Tests")
@Severity(SeverityLevel.CRITICAL)
@Owner("Fatma Eltahan and Mahmoud Amir")
public class SideMenuTests extends BaseTest{

    //Tests
    @Description("Verify the user can navigate to the about page by clicking on \"About\" in the side menu")
    @Test(groups = "valid")
    public void navigateToAboutPageTC()
    {
        new SideMenu(driver)
                .opensideMenu()
                .clickOnAboutLink()
                .verifyNavigationToAboutPage(test_data.getJsonData("aboutPageUrl"));
    }

    @Description("Verify the user can navigate to the products page by clicking on \"All Items\" in the side menu")
    @Test(groups = "valid")
    public void navigateToProductsPageTC()
    {
        new SideMenu(driver)
                .opensideMenu()
                .clickOnAllItemsLink()
                .verifyNavigationToAboutPage(test_data.getJsonData("productsPageUrl"))
                .closeSideMenu();
    }

    @Description("Verify the user can log out by clicking on \"Logout\" in the side menu")
    @Test(groups = "valid")
    public void logoutTC()
    {
        new SideMenu(driver)
                .opensideMenu()
                .clickLogoutLink()
                .verifyNavigationToAboutPage(test_data.getJsonData("loginPageUrl"));
    }

    @Description("Verify the user can reset state app by clicking on \"Reset App State\" in the side menu and remove button convert to add to cart button")
    @Test(groups = "valid")
    public void resetAppStateTC()
    {
       new ProductsPage(driver)
                .clickOnAddToCartButton(products_data.getJsonData("backpack"))
                .clickOnAddToCartButton(products_data.getJsonData("fleeceJacket"));
        new SideMenu(driver)
                .opensideMenu()
                .clickRestAppStateLink()
                .verifyRemoveButtonConvertToAddToCardButtonAfterResetAPPState(products_data.getJsonData("backpack"),
                        products_data.getJsonData("fleeceJacket") );
    }

    //Configurations

    @Step("Setting up preconditions and Load Test data")
    @BeforeClass(alwaysRun = true)
    protected void preconditions()
    {
        test_data=new JsonReader("sideMenu-data");
        login_data=new JsonReader("login-data");
        products_data= new JsonReader("products-data");

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

    @Step("Re-login after logout test")
    @AfterMethod(alwaysRun = true)
    public void afterSpecificTest(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        if (testName.equals("logoutTC")) {
            new LoginPage(driver).navigate()
                    .enterUsername(login_data.getJsonData("validUsername"))
                    .enterPassword(login_data.getJsonData("validPassword"))
                    .clickOnLoginButton();
        }

    }

}
