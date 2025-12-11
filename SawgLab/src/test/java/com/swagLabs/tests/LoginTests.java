package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Swag lab")
@Feature("UI User Management")
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Fatma Eltahan and Mahmoud Amir")
public class LoginTests extends BaseTest{

    //Tests

    @Description("Verify that the user can log in with valid data")
    @Test(groups = "valid")
    public void loginWithValidDataTC()
    {
        new LoginPage(driver).navigate()
                .enterUsername(test_data.getJsonData("validUsername"))
                .enterPassword(test_data.getJsonData("validPassword"))
                .clickOnLoginButton()
                .verifySuccessfulLogin(test_data.getJsonData("inventoryUrl"));

    }

    @Description("Verify the login with an invalid username  with valid other data")
    @Test(groups = "invalid")
    public void loginWithInvalidUsernameTC()
    {
        new LoginPage(driver).navigate()
                .enterUsername(test_data.getJsonData("invalidUsername"))
                .enterPassword(test_data.getJsonData("validPassword"))
                .clickOnLoginButton()
                .verifyErrorMessage(test_data.getJsonData("message.errorForInvalidData"));

    }

    @Description("Verify the login with an invalid password  with valid other data")
    @Test(groups = "invalid")
    public void loginWithInvalidPasswordTC()
    {
        new LoginPage(driver).navigate()
                .enterUsername(test_data.getJsonData("validUsername"))
                .enterPassword(test_data.getJsonData("invalidPassword"))
                .clickOnLoginButton()
                .verifyErrorMessage(test_data.getJsonData("message.errorForInvalidData"));

    }

    @Description("Verify the login with an empty username field and valid password")
    @Test(groups = "invalid")
    public void loginWithEmptyUsernameTC()
    {
        new LoginPage(driver).navigate()
                .enterPassword(test_data.getJsonData("validPassword"))
                .clickOnLoginButton()
                .verifyErrorMessage(test_data.getJsonData("message.errorForEmptyUsername"));
    }

    @Description("Verify the login with an empty password field and valid username")
    @Test(groups = "invalid")
    public void loginWithEmptyPasswordTC()
    {
        new LoginPage(driver).navigate()
                .enterUsername(test_data.getJsonData("validUsername"))
                .clickOnLoginButton()
                .verifyErrorMessage(test_data.getJsonData("message.errorForEmptyPassword"));
    }




    //Configurations
    @Step("Setting up preconditions and Load Test data")
    @BeforeClass(alwaysRun = true)
    protected void preconditions()
    {
        test_data=new JsonReader("login-data");

    }

    @Step("Setting up the driver")
    @BeforeMethod(alwaysRun = true)
    public void setup()
    {
        driver=new GUIDriver();
    }

    @Step("Tearing down the driver")
    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.quitDriver();

    }



}
