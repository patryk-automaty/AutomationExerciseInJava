package pl.automaty.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class SeleniumHelper {

    private static final String SCREENSHOT_FOLDER = "src/test/resources/screenshots/";

    // Capture screenshot and return file path
    private static String takeScreenshot(WebDriver driver) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = SCREENSHOT_FOLDER + "screenshot_" + timestamp + ".png";

        try {
            // Ensure directory exists
            File screenshotDir = new File(SCREENSHOT_FOLDER);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Take and save screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(screenshotPath));

            return screenshotPath;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null; // Return null if screenshot fails
        }
    }

    // Capture screenshot and attach to ExtentReports
    public static MediaEntityModelProvider getScreenshot(WebDriver driver) {
        String path = takeScreenshot(driver);
        if (path != null) {
            try {
                return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
            } catch (IOException e) {
                System.err.println("Failed to attach screenshot to ExtentReports: " + e.getMessage());
            }
        }
        return null;
    }

    public static void removeAdIframe(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript(
                    "document.querySelectorAll('ins.adsbygoogle').forEach(e => e.remove());"
            );
        } catch (Exception ignored) {
        }
    }

    public static void waitForAndRemoveAds(WebDriver driver) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> {
                removeAdIframe(driver);
                return true;
            });
        } catch (Exception ignored) {
        }
    }
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

}