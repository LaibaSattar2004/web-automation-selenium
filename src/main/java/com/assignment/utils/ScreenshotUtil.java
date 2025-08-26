package com.assignment.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    /**
     * Takes a screenshot of the current page and saves it to test-output/screenshots/
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @return relative path of the screenshot for ExtentReports
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.err.println("WebDriver is null. Cannot take screenshot.");
            return null;
        }

        // Ensure driver supports screenshots
        if (!(driver instanceof TakesScreenshot)) {
            System.err.println("Driver does not support screenshots!");
            return null;
        }

        // Sanitize test name to avoid illegal filename characters
        String safeTestName = testName.replaceAll("[:\\\\/*?|<>$]", "_");

        // Timestamp for unique file names
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = "test-output/screenshots";
        String screenshotPath = screenshotDir + "/" + safeTestName + "_" + timestamp + ".png";

        try {
            // Create directories if they don't exist
            Path dirPath = Paths.get(screenshotDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Copy to destination
            Path destPath = Paths.get(screenshotPath);
            Files.copy(srcFile.toPath(), destPath);

            return screenshotPath;

        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
