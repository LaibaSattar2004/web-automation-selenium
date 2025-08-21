package com.assignment.pages.saucedemo;

import com.assignment.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open(String url) {
        driver.get(url);
        return this;
    }

    public InventoryPage login(String username, String password) {
        $(usernameInput).sendKeys(username);
        $(passwordInput).sendKeys(password);
        $(loginButton).click();
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        if (isPresent(errorMessage)) {
            return $(errorMessage).getText();
        }
        return "";
    }
}
