package com.assignment.tests.saucedemo;

import com.assignment.pages.saucedemo.InventoryPage;
import com.assignment.pages.saucedemo.LoginPage;
import com.assignment.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceSortAndCartTest extends BaseTest {

    @Test
    public void sortLowToHighAndAddTwoItems() {
        driver.get(SAUCE_URL);

        // Login
        new LoginPage(driver).login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);

        // Sort and add first 2 items
        inventory.sortLowToHigh();
        inventory.addFirstNItems(2);

        // Verify cart count
        int cartCount = inventory.getCartCount();
        Assert.assertEquals(cartCount, 2, "Two items should be in the cart");
    }
}
