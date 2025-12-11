package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.drivers.WebDriverProvider;
import com.swagLabs.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected  JsonReader test_data;
    protected  JsonReader login_data;
    protected  JsonReader products_data;
    protected  JsonReader secondcheckout_data;
    protected JsonReader firstcheckout_data;
    protected JsonReader cart_data;
    protected  JsonReader  confirmation_data;


    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }

}
