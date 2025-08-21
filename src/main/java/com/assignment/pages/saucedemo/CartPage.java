package com.assignment.pages.saucedemo;

import com.assignment.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By cartItems = By.cssSelector(".cart_item");
    private final By cartQuantity = By.cssSelector(".cart_quantity");
    private final By cartPrice = By.cssSelector(".inventory_item_price");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return $$(cartItems).size();
    }

    public int getTotalQuantity() {
        return $$(cartQuantity).stream()
                .mapToInt(e -> Integer.parseInt(e.getText().trim()))
                .sum();
    }

    public double getTotalPrice() {
        return $$(cartPrice).stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().replace("$", "")))
                .sum();
    }
}
