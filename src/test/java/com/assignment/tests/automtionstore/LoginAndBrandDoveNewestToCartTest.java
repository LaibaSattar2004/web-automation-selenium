package com.assignment.tests.automtionstore;

import com.assignment.pages.automationstore.ProductListPage;
import com.assignment.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAndBrandDoveNewestToCartTest extends BaseTest {

    @Test
    public void loginAndAddDoveItem() {
        driver.get(ATS_URL);

        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("loginFrm_loginname")).sendKeys("testuser");
        driver.findElement(By.id("loginFrm_password")).sendKeys("testpass");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        // Case-insensitive match for "Dove"
        WebElement dove = driver.findElement(By.xpath(
                "//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'dove')]"
        ));
        dove.click();

        ProductListPage dovePage = new ProductListPage(driver);
        dovePage.sortNewestFirst();
        dovePage.addFirstItem();

        driver.findElement(By.cssSelector("#top_cart a")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Cart page should open after adding Dove item");
    }
}
