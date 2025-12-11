package com.swagLabs.drivers;

import com.swagLabs.utils.dataReader.PropertyReader;
import com.swagLabs.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class EdgeFactory extends AbstractDriver {
    private EdgeOptions getOptions() {
        EdgeOptions option = new EdgeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-popup-blocking");
        option.addArguments("--disable-notifications");
        option.addArguments("--start-maximized");
        option.addArguments("--disable-extensions");
        option.addArguments("--disable-gpu");
        option.addArguments("--disable-infobars");
        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless")||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote") )
        {
            option.addArguments("--headless");
        }
        option.setAcceptInsecureCerts(true);
        option.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return option;

    }

    @Override
    public WebDriver createDriver() {
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless") )
        {
            return new EdgeDriver(getOptions());
        }
        else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URI("http://"+ remoteHost+ ":" +remotePort + "/wd/hub").toURL(), getOptions()
                );
            }
            catch (Exception e) {
                LogsManager.error("Error creating RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create RemoteWebDriver", e);
            }

        }
        else {
            LogsManager.error("Invalid execution type: " + PropertyReader.getProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " + PropertyReader.getProperty("executionType"));
        }

    }
}
