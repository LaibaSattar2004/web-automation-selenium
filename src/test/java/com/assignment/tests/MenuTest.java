package com.assignment.tests;

import com.assignment.base.BaseTest;
import com.assignment.pages.LoginPage;
import com.assignment.pages.MenuPage;
import com.assignment.utils.ConfigReader;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {

    private MenuPage menuPage;

    @Test
    public void openAboutPageTest() {
        new LoginPage(driver)
                .open(ConfigReader.get("url"))
                .login(
                        ConfigReader.get("username"),
                        ConfigReader.get("password")
                );

        menuPage = new MenuPage(driver);
        menuPage.openMenu();
        menuPage.clickAbout();
        // optionally assert URL or page title
    }
}
