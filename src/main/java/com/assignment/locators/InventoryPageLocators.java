package com.assignment.locators;

import org.openqa.selenium.By;

public class InventoryPageLocators {
    public static final By sortDropdown = By.cssSelector(".product_sort_container");
    public static final By addToCartButtons = By.cssSelector(".btn_inventory");
    public static final By cartBadge = By.cssSelector(".shopping_cart_badge");
    public static final By inventoryItems = By.cssSelector(".inventory_item");
    public static final By headerTitle = By.cssSelector(".title");
    public static final By cartIcon = By.id("shopping_cart_container");
}
