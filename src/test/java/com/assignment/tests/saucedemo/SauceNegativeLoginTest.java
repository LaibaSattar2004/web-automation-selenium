package com.assignment.tests.saucedemo;

import com.assignment.pages.saucedemo.LoginPage;
import com.assignment.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceNegativeLoginTest extends BaseTest {
    @Test
    public void invalidPasswordShowsError() {
        LoginPage login = new LoginPage(driver).open(SAUCE_URL);
        login.login("standard_user", "wrong_pass");
        Assert.assertTrue(login.getErrorMessage().contains("Epic sadface"),
                "Error message should appear for invalid password");
    }
}
