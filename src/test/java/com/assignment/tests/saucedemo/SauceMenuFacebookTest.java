package com.assignment.tests.saucedemo;

import com.assignment.pages.saucedemo.LoginPage;
import com.assignment.pages.saucedemo.MenuPage;
import com.assignment.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class SauceMenuFacebookTest extends BaseTest {

    @Test
    public void aboutAndFacebookLink() {
        // Login
        new LoginPage(driver)
                .open(SAUCE_URL)
                .login("standard_user", "secret_sauce");

        // Navigate to About
        MenuPage menu = new MenuPage(driver);
        menu.openMenu();
        menu.clickAbout();

        // Wait for URL to change
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("inventory.html")
        ));

        String aboutUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                aboutUrl.contains("saucelabs") || aboutUrl.contains("saucedemo"),
                "About page should open Sauce Labs site but got: " + aboutUrl
        );

        // Navigate back
        driver.navigate().back();

        // Open Facebook link
        String originalWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector("li.social_facebook a")).click();

        // Wait for new window
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        // Switch to new window
        Set<String> windows = driver.getWindowHandles();
        for (String win : windows) {
            if (!win.equals(originalWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }

        String fbUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                fbUrl.contains("facebook"),
                "Facebook URL should open but got: " + fbUrl
        );
    }
}
