package com.assignment.pages.saucedemo;

import com.assignment.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage extends BasePage {
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By aboutLink = By.id("about_sidebar_link");

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void openMenu() {
        click(menuButton);
    }

    public void clickAbout() {
        click(aboutLink);
    }
}
