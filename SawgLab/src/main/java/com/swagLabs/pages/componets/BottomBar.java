package com.swagLabs.pages.componets;

import com.swagLabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class BottomBar {
    private final GUIDriver driver;

    //Constructors
    public BottomBar(GUIDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private final By twitterIcon= By.cssSelector("[data-test='social-twitter']");
    private final By facebookIcon= By.cssSelector("[data-test='social-facebook']");
    private final By linkedinIcon= By.cssSelector("[data-test='social-linkedin']");
    private final By bottomBar=By.cssSelector("[data-test='footer']");


    //Actions

    @Step("Scroll to Bottom Bar")
    public BottomBar scrollToBottomBar()
    {
        driver.element().scrollToElementJS(bottomBar);
        return this;
    }
    @Step("Click on Twitter Icon in Bottom Bar")
    public BottomBar clickOnTwitterIcon()
    {
        driver.element().click(twitterIcon);
        return this;
    }

    @Step("Click on Facebook Icon in Bottom Bar")
    public BottomBar clickOnFacebookIcon()
    {
        driver.element().click(facebookIcon);
        return this;
    }

    @Step("Click on Linkedin Icon in Bottom Bar")
    public BottomBar clickOnLinkedinIcon()
    {
        driver.element().click(linkedinIcon);
        return this;
    }

    @Step("Switch to  tab{index}")
    public BottomBar switchToTab(int index)
    {
        driver.browser().switchToWindow(index);
        return this;
    }

    @Step("Close current tab")
    public BottomBar closeCurrentTab()
    {
        driver.browser().closeCurrentWindow();
        return this;
    }




    //Validations

    @Step("Verify navigation to next Page")
    public BottomBar verifyNavigationToNextPage(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }





}
