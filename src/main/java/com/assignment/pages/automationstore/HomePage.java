package com.assignment.pages.automationstore;

import com.assignment.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By loginLink = By.cssSelector("a[href*='account/login']");
    private final By currencySelect = By.cssSelector("form#currency select");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open(String url) {
        driver.get(url);
        return this;
    }

    public void goToLogin() {
        click(loginLink);
    }

    public void selectCurrency(String code) {
        if (isPresent(currencySelect)) {
            driver.findElement(By.cssSelector("form#currency option[value='" + code + "']")).click();
        }
    }
}
