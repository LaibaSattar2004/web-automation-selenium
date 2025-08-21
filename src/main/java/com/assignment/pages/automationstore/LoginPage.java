package com.assignment.pages.automationstore;

import com.assignment.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By emailField = By.id("loginFrm_loginname");
    private final By passwordField = By.id("loginFrm_password");
    private final By loginBtn = By.cssSelector("#loginFrm button[type='submit']");
    private final By errorMsg = By.cssSelector(".alert-danger, .alert-error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        type(emailField, username);
        type(passwordField, password);
        click(loginBtn);
    }

    public boolean hasError() {
        return isPresent(errorMsg);
    }
}
