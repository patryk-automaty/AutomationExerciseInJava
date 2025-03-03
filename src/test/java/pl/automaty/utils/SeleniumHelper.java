package pl.automaty.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
}

//    private static String takeScreenshot(WebDriver driver) throws IOException {
//        int randomNumber = (int) (Math.random()*1000);
//        TakesScreenshot screenshot = (TakesScreenshot) driver;
//        File file = screenshot.getScreenshotAs(OutputType.FILE);
//        String path = "src/test/resources/screenshots/screenshot" + randomNumber + ".png";
//        FileUtils.copyFile(file, new File(path));
//        return path;
//    }

//    public static MediaEntityModelProvider getScreenshot(WebDriver driver) throws IOException {
//        String path = takeScreenshot(driver);
//        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
//    }
