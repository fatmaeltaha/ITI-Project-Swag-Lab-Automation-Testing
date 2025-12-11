package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.pages.componets.BottomBar;
import com.swagLabs.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Swag lab")
@Feature("UI botton bar Management")
@Story("Bottom Bar Tests")
@Severity(SeverityLevel.CRITICAL)
@Owner("Fatma Eltahan and Mahmoud Amir")
public class BottomBarTests extends BaseTest{



    //Tests
    @Description("Verify that the user can navigate to the Twitter page by clicking on the Twitter icon")
    @Test(groups = "valid")
    public void navigateToTwitterTC()
    {
        new BottomBar(driver)
               .scrollToBottomBar()
                .clickOnTwitterIcon()
                .switchToTab(Integer.parseInt(test_data.getJsonData("nextTab")))
                .verifyNavigationToNextPage(test_data.getJsonData("twitterUrl"))
                .closeCurrentTab()
                .switchToTab(Integer.parseInt(test_data.getJsonData("mainTab")));
    }

    @Description("Verify that the user can navigate to the Facebook page by clicking on the Facebook icon")
    @Test(groups = "valid")
    public void navigateToFacebookTC()
    {
        new BottomBar(driver)
                .scrollToBottomBar()
                .clickOnFacebookIcon()
                .switchToTab(Integer.parseInt(test_data.getJsonData("nextTab")))
                .verifyNavigationToNextPage(test_data.getJsonData("facebookUrl"))
                .closeCurrentTab()
                .switchToTab(Integer.parseInt(test_data.getJsonData("mainTab")));
    }

    @Description("Verify that the user can navigate to the Linkedin page by clicking on the Linkedin icon")
    @Test(groups = "valid")
    public void navigateToLinkedinTC()
    {
        new BottomBar(driver)
                .scrollToBottomBar()
                .clickOnLinkedinIcon()
                .switchToTab(Integer.parseInt(test_data.getJsonData("nextTab")))
                .verifyNavigationToNextPage(test_data.getJsonData("linkedinUrl"))
                .closeCurrentTab()
                .switchToTab(Integer.parseInt(test_data.getJsonData("mainTab")));
    }



    //Configurations
    @Step("Setting up preconditions and Load Test data")
    @BeforeClass(alwaysRun = true)
    protected void preconditions()
    {
        test_data=new JsonReader("bottomBar-data");
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
