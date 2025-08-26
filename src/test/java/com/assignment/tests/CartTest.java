package com.assignment.tests;

import com.assignment.base.BaseTest;
import com.assignment.pages.CartPage;
import com.assignment.pages.InventoryPage;
import com.assignment.pages.LoginPage;
import com.assignment.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private CartPage cartPage;

    @Test
    public void setupCart() {
        InventoryPage inventoryPage = new LoginPage(driver)
                .open(ConfigReader.get("url"))
                .login(
                        ConfigReader.get("username"),
                        ConfigReader.get("password")
                );

        int itemCount = Integer.parseInt(ConfigReader.get("cartItemCount", "2"));
        inventoryPage.addFirstNItems(itemCount);

        cartPage = inventoryPage.goToCart();
    }

    @Test(dependsOnMethods = "setupCart")
    public void cartItemsCountTest() {
        int expected = Integer.parseInt(ConfigReader.get("cartItemCount", "2"));
        int actual = cartPage.getItemCount();
        Assert.assertEquals(actual, expected, "Cart item count mismatch");
    }

    @Test(dependsOnMethods = "setupCart")
    public void cartTotalPriceTest() {
        double totalPrice = cartPage.getTotalPrice();
        Assert.assertTrue(totalPrice > 0, "Total price should be greater than zero");
    }
}
