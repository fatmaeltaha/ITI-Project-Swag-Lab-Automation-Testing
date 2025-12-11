package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.componets.CartBadge;
import com.swagLabs.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Set;

public class CartPage {
    private final GUIDriver driver;
    private ProductsPage productsPage;
    private CartBadge cartbadge;

    //Constructor
    public CartPage(GUIDriver driver) {
        this.driver = driver;
        productsPage = new ProductsPage(driver);
        cartbadge = new CartBadge(driver);
    }

    //Locators
    private final By continue_shopping = By.id("continue-shopping");
    private final By checkout = By.id("checkout");
    private final By inventory_item_description = By.className("inventory_item_desc");
    private final By inventory_item_price = By.className("inventory_item_price");
    private final By inventory_item_quantity = By.className("cart_quantity");


    //Actions
    @Step("navigate To Cart Page")
    public CartPage navigateToCartPage() {
        cartbadge.clickOnCartLink();
        return this;
    }

    @Step("Click on Continue Shopping")
    public CartPage clickOnContinueShoppingButton() {
        driver.element().click(continue_shopping);
        return this;
    }

    @Step("Click on Checkout")
    public CartPage clickOnCheckoutButton() {
        driver.element().click(checkout);
        return this;
    }

    @Step("Click on remove  button for product: {productName}")
    public CartPage clickOnRemoveButton(String productName) {
        driver.element().click(productsPage.removeButton(productName));
        return this;
    }

    @Step("Click on {productName}")
    public CartPage clickOnProductName(String productName) {
        driver.element().click(productsPage.productNameLocator(productName));
        return this;
    }


    //Validation
    @Step("Verify 'Continue Shopping' button navigates back to Inventory page")
    public CartPage verifyNavigateBackToInventory(String expectedUrl) {
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }

    @Step("Verify 'Checkout' button navigates To Checkout Page")
    public CartPage verifyNavigateToCheckout(String expectedUrl) {
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }

    @Step("Verify products exist in cart page")
    public void verifyProductsExistInCartPage(String... productNames) {

        for (String productName : productNames) {

            // Verify product name
            String actualProductName = driver.element().getText(productsPage.productNameLocator(productName));
            driver.verification().Equals(
                    actualProductName,
                    productName,
                    "Product name '" + productName + "' is not displayed correctly in the cart page."
            );

        }

    }

    @Step("Verify products are removed from cart")
    public int verifyProductsAreRemovedFromCart(int currentCount, String... productNames) {


        String actualCartCount = cartbadge.getCartItemCount();
        int expectedCartCount = currentCount - productNames.length;
        driver.verification().Equals(
                actualCartCount,
                String.valueOf(expectedCartCount),
                "Cart count does not match expected count after removal"
        );

        return expectedCartCount;
    }


    @Step("Verify that the user can navigate to item page by clicking on the product name in the cart")
    public CartPage verifyNavigateToItemPage(String... productNames) {

        for (String productName : productNames) {

            // Verify product name
            String actualProductName = driver.element().getText(productsPage.productNameLocator(productName));
            driver.verification().Equals(
                    actualProductName,
                    productName,
                    "Product name '" + productName + "' is not displayed correctly in the cart page."
            );

        }

return this;
    }
}