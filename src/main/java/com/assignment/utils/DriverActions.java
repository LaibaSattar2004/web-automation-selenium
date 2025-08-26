package com.assignment.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverActions {
    private WebDriver driver;

    public DriverActions(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator, String stepName) {
        WebElement element = driver.findElement(locator);
        element.click();
        ScreenshotUtil.takeScreenshot(driver, stepName);
    }

    public void type(By locator, String text, String stepName) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
        ScreenshotUtil.takeScreenshot(driver, stepName);
    }

    public void navigateTo(String url, String stepName) {
        driver.get(url);
        ScreenshotUtil.takeScreenshot(driver, stepName);
    }
}
