package com.assignment.tests;

import com.assignment.core.Config;
import com.assignment.core.DriverFactory;
import com.assignment.core.DB;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected WebDriver driver;
    protected String SAUCE_URL = Config.get("base.sauce", "https://www.saucedemo.com/");
    protected String ATS_URL = Config.get("base.ats", "https://automationteststore.com/");

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.getDriver();
        DB.log("Driver started");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DB.log("Driver quit");
        DriverFactory.quitDriver();
    }
}
