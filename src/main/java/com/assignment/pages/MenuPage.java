package com.assignment.pages;

import com.assignment.locators.MenuPageLocators;
import com.assignment.utils.DriverActions;
import org.openqa.selenium.WebDriver;

public class MenuPage extends BasePage {
    private DriverActions actions;

    public MenuPage(WebDriver driver) {
        super(driver);
        actions = new DriverActions(driver);
    }

    public void openMenu() {
        actions.click(MenuPageLocators.menuButton, "Open Menu");
    }

    public void clickAbout() {
        actions.click(MenuPageLocators.aboutLink, "Click About Link");
    }
}
