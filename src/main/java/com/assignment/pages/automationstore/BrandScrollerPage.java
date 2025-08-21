package com.assignment.pages.automationstore;

import com.assignment.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrandScrollerPage extends BasePage {
    private final By brandLinks = By.cssSelector(".brand-scroller a");
    // Adjust selector if brand scroller has a different class/id

    public BrandScrollerPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks a brand from the scroller by visible name.
     */
    public void selectBrand(String brandName) {
        List<WebElement> brands = $$(brandLinks);
        for (WebElement brand : brands) {
            if (brand.getText().trim().equalsIgnoreCase(brandName)) {
                brand.click();
                return;
            }
        }
        throw new RuntimeException("Brand not found in scroller: " + brandName);
    }
}
