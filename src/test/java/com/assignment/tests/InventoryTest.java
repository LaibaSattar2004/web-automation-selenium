package com.assignment.tests;

import com.assignment.base.BaseTest;
import com.assignment.pages.InventoryPage;
import com.assignment.pages.LoginPage;
import com.assignment.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {

    private InventoryPage inventoryPage;

    @Test
    public void loginAndSetupInventory() {
        inventoryPage = new LoginPage(driver)
                .open(ConfigReader.get("url"))
                .login(
                        ConfigReader.get("username"),
                        ConfigReader.get("password")
                );
    }

    @Test(dependsOnMethods = "loginAndSetupInventory")
    public void sortAndAddItemsTest() {
        inventoryPage.sortBy("Price (low to high)");

        int itemCount = Integer.parseInt(ConfigReader.get("cartItemCount", "2"));
        inventoryPage.addFirstNItems(itemCount);

        int cartCount = inventoryPage.getCartCount();
        Assert.assertEquals(cartCount, itemCount, "Cart count mismatch");
    }
}
