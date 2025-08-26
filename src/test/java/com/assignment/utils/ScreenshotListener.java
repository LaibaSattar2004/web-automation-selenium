package com.assignment.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver(); // get your driver
        String path = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
        System.out.println("Screenshot saved at: " + path);
    }

    // other methods can be empty
}
