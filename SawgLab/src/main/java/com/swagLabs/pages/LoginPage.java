package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage {
    private final GUIDriver driver;

    //Constructor
    public LoginPage(GUIDriver driver)
    {
        this.driver=driver;

    }

    //Locators
    private final  By username= By.id("user-name");
    private final By password =By.id("password");
    private final By loginButton=By.id("login-button");
    private final By errorMessage=By.cssSelector("[data-test='error']");


    //Actions
    @Step("Navigate to Login page")
    public LoginPage navigate()
    {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Enter username")
    public LoginPage enterUsername(String username)
    {
        driver.element().type(this.username,username);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password)
    {
        driver.element().type(this.password,password);
        return this;
    }

    @Step("Click on login button")
    public LoginPage clickOnLoginButton()
    {
        driver.element().click(loginButton);
        return this;
    }




    //Validation
    @Step("Verify successful login")
    public LoginPage verifySuccessfulLogin(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }

    @Step("Verify error message")
    public LoginPage verifyErrorMessage(String expectedErrorMessage)
    {
        String actualErrorMessage=driver.element().getText(errorMessage);
        driver.verification().Equals(actualErrorMessage,expectedErrorMessage,"Login error message is not expected");
        return this;
    }






}
