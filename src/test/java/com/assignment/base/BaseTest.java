package com.assignment.base;

import com.assignment.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.getDriver();
    }

    @AfterClass
    public void teardown() {
        DriverFactory.quitDriver();
    }
}
