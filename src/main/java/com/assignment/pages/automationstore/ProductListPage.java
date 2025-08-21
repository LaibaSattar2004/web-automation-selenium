package com.assignment.pages.automationstore;

import com.assignment.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductListPage extends BasePage {

    private final By sortDropdown = By.cssSelector("select#sort, select[name='sort']");
    private final By productItems = By.cssSelector(".thumbnails .thumbnail");
    private final By productPrice = By.cssSelector(".price, .oneprice, .pricenew");

    // multiple possible add-to-cart locators
    private final By[] addToCartLocators = new By[]{
            By.cssSelector(".productcart"),
            By.cssSelector("a[title='Add to Cart']"),
            By.cssSelector(".btn-cart"),
            By.cssSelector(".fa-shopping-cart")
    };

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public void sortLowToHigh() {
        if (isPresent(sortDropdown)) {
            new Select($(sortDropdown)).selectByVisibleText("Price Low > High");
        }
    }

    public void sortNewestFirst() {
        if (isPresent(sortDropdown)) {
            new Select($(sortDropdown)).selectByVisibleText("Newest Items");
        }
    }

    public void addTopNLowest(int n) {
        List<WebElement> products = $$(productItems).stream()
                .sorted(Comparator.comparingDouble(this::getPrice))
                .limit(n)
                .collect(Collectors.toList());

        for (WebElement product : products) {
            clickAddToCart(product);
        }
    }

    public void addHighestPriced(int quantity) {
        WebElement highest = $$(productItems).stream()
                .max(Comparator.comparingDouble(this::getPrice))
                .orElseThrow();

        for (int i = 0; i < quantity; i++) {
            clickAddToCart(highest);
        }
    }

    public void addFirstItem() {
        List<WebElement> products = $$(productItems);
        if (!products.isEmpty()) {
            clickAddToCart(products.get(0));
        }
    }

    private void clickAddToCart(WebElement product) {
        for (By locator : addToCartLocators) {
            List<WebElement> buttons = product.findElements(locator);
            if (!buttons.isEmpty()) {
                WebElement btn = buttons.get(0);
                try {
                    // scroll into view first
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
                    btn.click();
                } catch (Exception e) {
                    // fallback JS click
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                }
                return;
            }
        }
        System.out.println("⚠ No Add to Cart button found for product: " + product.getText());
    }

    private double getPrice(WebElement product) {
        String p = product.findElement(productPrice).getText().replaceAll("[^0-9.]", "");
        return p.isEmpty() ? 0.0 : Double.parseDouble(p);
    }
}
