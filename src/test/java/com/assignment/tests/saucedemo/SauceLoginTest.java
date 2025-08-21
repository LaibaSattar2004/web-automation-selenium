package com.assignment.tests.saucedemo;

import com.assignment.pages.saucedemo.InventoryPage;
import com.assignment.pages.saucedemo.LoginPage;
import com.assignment.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceLoginTest extends BaseTest {
    @Test
    public void loginWithValidCredentials() {
        LoginPage login = new LoginPage(driver).open(SAUCE_URL);
        login.login("standard_user", "secret_sauce");

        InventoryPage inv = new InventoryPage(driver);
        Assert.assertEquals(inv.getHeader(), "Products", "User should land on dashboard");
    }
}
