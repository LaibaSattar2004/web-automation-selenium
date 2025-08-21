package com.assignment.pages.automationstore;

import com.assignment.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Comparator;
import java.util.List;

public class CartPage extends BasePage {
    private final By cartRows = By.cssSelector("#cart tr:has(td)");
    private final By subtotalCell = By.cssSelector("#totals_table .total, .cart_total .total");
    private final By priceCells = By.cssSelector("td.total, td.price");
    private final By removeBtn = By.cssSelector("a.cart_remove, a[title='Remove']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return $$(cartRows).size();
    }

    public double getSubtotal() {
        List<WebElement> totals = $$(subtotalCell);
        if (!totals.isEmpty()) {
            String val = totals.get(0).getText().replaceAll("[^0-9.]", "");
            return val.isEmpty() ? 0.0 : Double.parseDouble(val);
        }

        return $$(priceCells).stream()
                .mapToDouble(e -> {
                    String val = e.getText().replaceAll("[^0-9.]", "");
                    return val.isEmpty() ? 0.0 : Double.parseDouble(val);
                })
                .sum();
    }

    public void removeHighestPriced() {
        WebElement highestRow = $$(cartRows).stream()
                .max(Comparator.comparingDouble(r -> {
                    String val = r.findElement(priceCells).getText().replaceAll("[^0-9.]", "");
                    return val.isEmpty() ? 0.0 : Double.parseDouble(val);
                }))
                .orElseThrow();

        highestRow.findElement(removeBtn).click();
    }

    public void ensureTotalUnder(double threshold) {
        while (getSubtotal() > threshold && getItemCount() > 0) {
            removeHighestPriced();
        }
    }
}
