package com.assignment.pages;
import com.assignment.utils.ScreenshotUtil;

import com.assignment.locators.CartPageLocators;
import com.assignment.utils.DriverActions;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private DriverActions actions;

    public CartPage(WebDriver driver) {
        super(driver);
        actions = new DriverActions(driver);
    }

    public int getItemCount() {
        int count = $$(CartPageLocators.cartItems).size();
        ScreenshotUtil.takeScreenshot(driver, "Cart Items Count: " + count);
        return count;
    }

    public int getTotalQuantity() {
        int total = $$(CartPageLocators.cartQuantity)
                .stream()
                .mapToInt(e -> Integer.parseInt(e.getText().trim()))
                .sum();
        ScreenshotUtil.takeScreenshot(driver, "Cart Total Quantity: " + total);
        return total;
    }

    public double getTotalPrice() {
        double total = $$(CartPageLocators.cartPrice)
                .stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().replace("$", "")))
                .sum();
        ScreenshotUtil.takeScreenshot(driver, "Cart Total Price: $" + total);
        return total;
    }
}
