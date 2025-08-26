package com.assignment.pages;

import com.assignment.locators.LoginPageLocators;
import com.assignment.utils.DriverActions;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private DriverActions actions;

    public LoginPage(WebDriver driver) {
        super(driver);
        actions = new DriverActions(driver);
    }

    public LoginPage open(String url) {
        actions.navigateTo(url, "Open Login Page");
        return this;
    }

    public InventoryPage login(String username, String password) {
        actions.type(LoginPageLocators.usernameInput, username, "Enter Username");
        actions.type(LoginPageLocators.passwordInput, password, "Enter Password");
        actions.click(LoginPageLocators.loginButton, "Click Login Button");
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return isPresent(LoginPageLocators.errorMessage)
                ? text(LoginPageLocators.errorMessage)
                : "";
    }
}
