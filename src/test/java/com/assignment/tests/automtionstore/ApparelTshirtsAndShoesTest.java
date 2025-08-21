package com.assignment.tests.automtionstore;

import com.assignment.pages.automationstore.ProductListPage;
import com.assignment.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApparelTshirtsAndShoesTest extends BaseTest {

    @Test
    public void tshirtsAndShoesFlow() {
        driver.get(ATS_URL);

        // Navigate to Apparel → T-shirts
        WebElement apparel = driver.findElement(By.xpath(
                "//a[contains(translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'apparel')]"
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", apparel);

        driver.findElement(By.linkText("T-shirts")).click();

        ProductListPage tshirts = new ProductListPage(driver);
        tshirts.sortLowToHigh();
        tshirts.addTopNLowest(3);

        goToCartIfExists();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout/cart"),
                "Cart page should open after adding T-shirts");

        // Navigate again to Apparel → Shoes
        driver.navigate().to(ATS_URL);

        WebElement apparelAgain = driver.findElement(By.xpath(
                "//a[contains(translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'apparel')]"
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", apparelAgain);

        driver.findElement(By.linkText("Shoes")).click();

        ProductListPage shoes = new ProductListPage(driver);
        shoes.addHighestPriced(2);

        goToCartIfExists();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout/cart"),
                "Cart page should open after adding shoes");
    }

    /**
     * Helper method to safely click cart if the icon exists
     */
    private void goToCartIfExists() {
        if (driver.findElements(By.cssSelector("#top_cart a")).size() > 0) {
            driver.findElement(By.cssSelector("#top_cart a")).click();
        }
    }
}
