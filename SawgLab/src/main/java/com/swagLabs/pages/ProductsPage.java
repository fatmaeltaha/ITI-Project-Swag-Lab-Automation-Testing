package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.pages.componets.CartBadge;
import com.swagLabs.utils.actions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ProductsPage {

    //variables
    private final GUIDriver driver;
    private CartBadge cartBadge;
   // private int expectedCartCount=0;


    //Constructor
    public ProductsPage(GUIDriver driver){
        this.driver=driver;
         cartBadge = new CartBadge(driver);
    }




    //Locators
    private final By products_sort_container=By.cssSelector("[data-test='product-sort-container']");
    private final By productNamesLocator = By.cssSelector(".inventory_item_name");
    private final By productPriceLocator =By.cssSelector(".inventory_item_price");

    //Dynamic Locators
    public By addToCart(String productName)
    {
        if(productName.equals("Test.allTheThings() T-Shirt (Red)")) {
           return By.id("add-to-cart-test.allthethings()-t-shirt-(red)");
        }
        return By.id("add-to-cart-"+productName.toLowerCase().replaceAll(" ","-").replaceAll("[()]", "").replaceAll("[.]",""));
    }

    public By removeButton(String productName)
    {
        if(productName.equals("Test.allTheThings() T-Shirt (Red)")) {
            return By.id("remove-test.allthethings()-t-shirt-(red)");
        }
        return By.id("remove-"+productName.toLowerCase().replaceAll(" ","-").replaceAll("[()]", "").replaceAll("[.]",""));
    }

    public By productNameLocator(String productName)
    {
        return By.xpath("//div[.='"+productName+"']");
    }



    //Actions
    @Step("Click on add to cart button for product: {productName}")
    public ProductsPage clickOnAddToCartButton(String productName)
    {
        driver.element().click(addToCart(productName));
        return this;
    }

    @Step("Click on remove  button for product: {productName}")
    public ProductsPage clickOnRemoveButton(String productName)
    {
        driver.element().click(removeButton(productName));
        return this;
    }

    @Step("Click on  {productName}")
    public ProductsPage clickOnProductName(String productName)
    {
        driver.element().click(productNameLocator(productName));
        return this;
    }

    @Step ("Select sort option: {sortOption}")
    public ProductsPage selectSortOption(String sortOption)
    {
        driver.element().selectFromDropdown(products_sort_container,sortOption);
        return this;
    }

    @Step("Scroll to {productName}")
    public ProductsPage scrollToProduct(String productName)
    {
        driver.element().scrollToElementJS(productNameLocator(productName));
        return this;
    }



    //Verifications
    @Step("Verify products are added to cart")
    public int verifyProductsAreAddedToCart(int currentCount,String... productNames) {

        for (String productName : productNames) {

            String actualButtonText = driver.element().getText(removeButton(productName));

            driver.verification().Equals(
                    actualButtonText,
                    "Remove",
                    "Button is not 'Remove' after adding product: " + productName
            );
        }

        String actualCartCount = cartBadge.getCartItemCount();
        int expectedCartCount =currentCount+productNames.length;
        driver.verification().Equals(
                actualCartCount,
                String.valueOf(expectedCartCount),
                "Cart count does not match number of added products."
        );

        return expectedCartCount;
    }


    @Step("Verify products are removed from cart")
    public int verifyProductsAreRemovedFromCart(int currentCount,String... productNames) {



        for (String productName : productNames) {

            String actualButtonText = driver.element().getText(addToCart(productName));

            driver.verification().Equals(
                    actualButtonText,
                    "Add to cart",
                    "Button is not 'Add to cart' after removing product: " + productName
            );
        }

        String actualCartCount = cartBadge.getCartItemCount();
       int expectedCartCount =currentCount-productNames.length;
        driver.verification().Equals(
                actualCartCount,
                String.valueOf(expectedCartCount),
                "Cart count does not match expected count after removal"
        );

        return expectedCartCount;
    }

    @Step("Verify sorting by name descending (Z to A)")
    public ProductsPage verifySortingByNameDescending()
    {
        // 1. Get all product names (not one product)
        List<String> uiNames = driver.element().getTexts(productNamesLocator);

        // 2. Sort copy in Desc order
        List<String> sortedNames = new ArrayList<>(uiNames);
        sortedNames.sort(Comparator.reverseOrder());

        // 3. Compare UI order with sorted list
        driver.verification().Equals(
                uiNames,
                sortedNames,
                "Products are NOT sorted correctly by name (Z → A)"
        );


        return this;
    }


    @Step("Verify sorting by price (low to high) ")
    public ProductsPage verifySortingByPriceAscending()
    {// 1. Get UI prices as Strings
        List<String> uiPricesString = driver.element().getTexts(productPriceLocator);

        // 2. Convert to Double (remove $ sign)
        List<Double> uiPrices = uiPricesString.stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .collect(Collectors.toList());

        // 3. Create sorted copy (ascending)
        List<Double> sortedPrices = new ArrayList<>(uiPrices);
        sortedPrices.sort(Comparator.naturalOrder());  // Low → High

        // 4. Compare
        driver.verification().Equals(
                uiPrices.toString(),
                sortedPrices.toString(),
                "Products are NOT sorted correctly by price (low to high)"
        );

        return this;
    }




}
