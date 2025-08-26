package com.assignment.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public static final By usernameInput = By.id("user-name");
    public static final By passwordInput = By.id("password");
    public static final By loginButton   = By.id("login-button");
    public static final By errorMessage  = By.cssSelector("h3[data-test='error']");
}
