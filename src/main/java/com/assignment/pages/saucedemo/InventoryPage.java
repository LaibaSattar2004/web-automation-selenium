package com.assignment.pages.saucedemo;

import com.assignment.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage extends BasePage {

    private final By sortDropdown = By.cssSelector(".product_sort_container");
    private final By addToCartButtons = By.cssSelector(".btn_inventory");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By headerTitle = By.cssSelector(".title");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Sort by any option
    public void sortBy(String option) {
        new Select($(sortDropdown)).selectByVisibleText(option);
    }

    public void sortLowToHigh() {
        sortBy("Price (low to high)");
    }

    public void addFirstNItems(int n) {
        List<WebElement> buttons = $$(addToCartButtons);
        int added = 0;
        for (WebElement btn : buttons) {
            if (added >= n) break;
            if (btn.isDisplayed() && btn.getText().equalsIgnoreCase("Add to cart")) {
                btn.click();
                added++;
            }
        }
    }

    public int getCartCount() {
        if (isPresent(cartBadge)) {
            return Integer.parseInt($(cartBadge).getText());
        }
        return 0;
    }

    public String getHeader() {
        return $(headerTitle).getText();
    }

    public WebElement waitForFirstItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf($$(inventoryItems).get(0)));
    }
}
