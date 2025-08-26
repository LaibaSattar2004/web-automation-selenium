package com.assignment.tests;

import com.assignment.base.BaseTest;
import com.assignment.pages.InventoryPage;
import com.assignment.pages.LoginPage;
import com.assignment.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test
    public void validLoginTest() {
        loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage
                .open(ConfigReader.get("url"))
                .login(
                        ConfigReader.get("username"),
                        ConfigReader.get("password")
                );

        Assert.assertEquals(
                inventoryPage.getHeader(),
                "Products",
                "Header mismatch after login"
        );
    }

    @Test
    public void invalidLoginTest() {
        loginPage = new LoginPage(driver);
        loginPage.open(ConfigReader.get("url"));
        loginPage.login("invalid_user", "invalid_pass");

        String error = loginPage.getErrorMessage();
        Assert.assertTrue(
                error.contains("do not match"),
                "Expected error message was not shown. Actual: " + error
        );
    }
}
