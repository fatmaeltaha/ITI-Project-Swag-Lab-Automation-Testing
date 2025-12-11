package com.swagLabs.pages.componets;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.ProductsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SideMenu {
    private final GUIDriver driver;
    private ProductsPage productsPage;
    private CartBadge cartBadge;

    //Constructor
    public SideMenu(GUIDriver driver)
    {
        this.driver=driver;
        productsPage=new ProductsPage(driver);
        cartBadge=new CartBadge(driver);
    }

    //Locators
    private final By sideMenuButton= By.id("react-burger-menu-btn");
    private final By inventoryLink=By.id("inventory_sidebar_link");
    private final By aboutLink=By.id("about_sidebar_link");
    private final By logoutLink=By.id("logout_sidebar_link");
    private final By resetLink=By.id("reset_sidebar_link");
    private final By closeMenuButton=By.id("react-burger-cross-btn");

    //Actions
    @Step("Open side menu")
    public  SideMenu opensideMenu()
    {
        driver.element().click(sideMenuButton);
        return this;
    }

    @Step("Click on All Items Link")
    public SideMenu clickOnAllItemsLink()
    {
        driver.element().click(inventoryLink);
        return this;
    }

    @Step("Click on About Link")
    public SideMenu clickOnAboutLink()
    {
        driver.element().click(aboutLink);
        return this;
    }

    @Step("Click on Logout Link")
    public SideMenu clickLogoutLink()
    {
        driver.element().click(logoutLink);
        return this;
    }

    @Step("Click on Rest App State Link")
    public SideMenu clickRestAppStateLink()
    {
        driver.element().click(resetLink);
        return this;
    }

    @Step("Close side menu")
    public SideMenu closeSideMenu()
    {
        driver.element().click(closeMenuButton);
        return this;
    }



    //Validations
    @Step("Verify navigation to Products Page")
    public SideMenu verifyNavigationToProductsPage(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }

    @Step("Verify navigation to About Page")
    public SideMenu verifyNavigationToAboutPage(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        driver.browser().navigateToBack();
        return this;
    }

    @Step("Verify logging out")
    public SideMenu verifyLoggingOut(String expectedUrl)
    {
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }




    @Step("Verify remove button convert to Add to cart button after resetting app state")
    public SideMenu verifyRemoveButtonConvertToAddToCardButtonAfterResetAPPState(String... productNames)
    {

        // 2. Verify all previously added products return to 'Add to cart'
        for (String productName : productNames) {
            String actualBtnText = driver.element().getText((productsPage.removeButton(productName)));

            driver.verification().Equals(
                    actualBtnText,
                    "Add to cart",
                    "Button did not reset to 'Add to cart' for product: " + productName
            );
        }
        return this;
    }






}
