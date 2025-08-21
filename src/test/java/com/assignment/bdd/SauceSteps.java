package com.assignment.bdd;

import com.assignment.core.Config;
import com.assignment.core.DriverFactory;
import com.assignment.pages.saucedemo.InventoryPage;
import com.assignment.pages.saucedemo.LoginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SauceSteps {
    private final WebDriver driver = DriverFactory.getDriver();
    private final String SAUCE_URL = Config.get("base.sauce", "https://www.saucedemo.com/");

    @Given("I open SauceDemo")
    public void iOpenSauceDemo() {
        new LoginPage(driver).open(SAUCE_URL);
    }

    @When("I login as {string} with password {string}")
    public void iLoginAsWithPassword(String user, String pass) {
        new LoginPage(driver).login(user, pass);
    }

    @Then("I should see the dashboard title {string}")
    public void iShouldSeeDashboardTitle(String title) {
        Assert.assertEquals(new InventoryPage(driver).getHeader(), title);
    }

    @Then("I should see an error containing {string}")
    public void iShouldSeeErrorContaining(String expected) {
        String err = new LoginPage(driver).getErrorMessage();
        Assert.assertTrue(err.contains(expected),
                "Expected error to contain: " + expected + " but was: " + err);
    }
}
