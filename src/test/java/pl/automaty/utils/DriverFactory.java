package pl.automaty.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import pl.automaty.listeners.AdCleaningWebDriverListener;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        WebDriver baseDriver;


        if (browser.equalsIgnoreCase("edge")) {

            // Setup edge WebDriver using Web driver manager
            WebDriverManager.edgedriver().setup();

            // Configure edge options
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-extensions");
            options.addArguments("--incognito");
            baseDriver = new EdgeDriver(options);

            // Store driver for access in SeleniumHelper
            SeleniumHelper.setDriver(baseDriver);
        } else {

            // Setup chrome WebDriver using Web driver manager
            WebDriverManager.chromedriver().setup();

            // Configure chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-web-security");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-features=site-per-process");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-logging");
            options.addArguments("--disable-cookie-encryption");
            options.addArguments("--incognito");
            baseDriver = new ChromeDriver(options);

            // Store driver for access in SeleniumHelper
            SeleniumHelper.setDriver(baseDriver);
        }
        // Add listener to webdriver to watch what the browser is doing
        // Needed to run extra code (removing ads)
        return new EventFiringDecorator<>(new AdCleaningWebDriverListener()).decorate(baseDriver);
    }
}