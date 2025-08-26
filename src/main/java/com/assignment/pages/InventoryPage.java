package com.assignment.pages;
import com.assignment.utils.ScreenshotUtil;

import com.assignment.locators.InventoryPageLocators;
import com.assignment.utils.DriverActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BasePage {
    private DriverActions actions;

    public InventoryPage(WebDriver driver) {
        super(driver);
        actions = new DriverActions(driver);
    }

    public void sortBy(String option) {
        new Select($(InventoryPageLocators.sortDropdown)).selectByVisibleText(option);
        ScreenshotUtil.takeScreenshot(driver, "Sort Inventory by " + option);
    }

    public void addFirstNItems(int n) {
        List<WebElement> buttons = $$(InventoryPageLocators.addToCartButtons);
        for (int i = 0; i < Math.min(n, buttons.size()); i++) {
            WebElement button = buttons.get(i);
            if (button.isDisplayed()) {
                button.click();
                int expectedCount = i + 1;
                wait.until(ExpectedConditions.textToBePresentInElementLocated(
                        InventoryPageLocators.cartBadge,
                        String.valueOf(expectedCount)
                ));
                ScreenshotUtil.takeScreenshot(driver, "Add Item " + expectedCount + " to Cart");
            }
        }
    }

    public CartPage goToCart() {
        actions.click(InventoryPageLocators.cartIcon, "Go to Cart");
        return new CartPage(driver);
    }

    public int getCartCount() {
        return isPresent(InventoryPageLocators.cartBadge)
                ? Integer.parseInt(text(InventoryPageLocators.cartBadge))
                : 0;
    }

    public String getHeader() {
        return text(InventoryPageLocators.headerTitle);
    }
}
