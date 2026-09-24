package com.AndroidTest.MOrderDetailsFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BCompletedPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final int MAX_ORDERS = 5;
    private static final int MAX_SCROLL_ATTEMPTS = 15;

    // ============================================================
    // LOCATORS
    // ============================================================

    private final By completedTab =
            AppiumBy.accessibilityId("Completed");

    // Do NOT require "Received Points"
    private final By completedOrders =
            AppiumBy.xpath(
                    "//android.view.View[" +
                            "contains(@content-desc,'AB-RO-')" +
                            " and contains(@content-desc,'Completed')" +
                            "]"
            );

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public BCompletedPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    // ============================================================
    // MAIN FLOW
    // ============================================================

    public void validateFirstFiveCompletedOrders() {

        System.out.println();
        System.out.println("==========================================");
        ExtentTestListener.logStep("START : Completed Orders Validation");
        System.out.println("==========================================");

        clickCompleted();
        waitForCompletedOrders();
        validateFiveOrders();

        System.out.println();
        System.out.println("==========================================");
        ExtentTestListener.logStep("END : Completed Orders Validation");
        System.out.println("==========================================");
    }

    // ============================================================
    // CLICK COMPLETED
    // ============================================================

    private void clickCompleted() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        completedTab
                )
        ).click();

        ExtentTestListener.logStep(
                "SUCCESS : Completed clicked"
        );
    }

    // ============================================================
    // WAIT FOR COMPLETED ORDERS
    // ============================================================

    private void waitForCompletedOrders() {

        wait.until(driver ->
                !driver.findElements(
                        completedOrders
                ).isEmpty()
        );

        ExtentTestListener.logStep(
                "SUCCESS : Completed orders loaded"
        );
    }

    // ============================================================
    // VALIDATE FIRST 5
    // ============================================================

    private void validateFiveOrders() {

        Set<String> processedOrders =
                new HashSet<>();

        int validated = 0;
        int scrollAttempts = 0;

        while (validated < MAX_ORDERS &&
                scrollAttempts < MAX_SCROLL_ATTEMPTS) {

            System.out.println();
            ExtentTestListener.logStep(
                    "========== Looking for Completed Order "
                            + (validated + 1)
                            + " =========="
            );

            List<WebElement> orders =
                    driver.findElements(
                            completedOrders
                    );

            boolean newOrderFound = false;

            for (WebElement order : orders) {

                try {

                    if (!order.isDisplayed()) {
                        continue;
                    }

                    String description =
                            order.getAttribute(
                                    "content-desc"
                            );

                    String orderNumber =
                            extractOrderNumber(
                                    description
                            );

                    if (orderNumber == null ||
                            processedOrders.contains(
                                    orderNumber
                            )) {
                        continue;
                    }

                    System.out.println(
                            "Completed Order "
                                    + (validated + 1)
                                    + " : "
                                    + orderNumber
                    );

                    System.out.println(
                            "Details : "
                                    + description.replace(
                                    "\n",
                                    " | "
                            )
                    );

                    // ------------------------------------------------
                    // CLICK
                    // ------------------------------------------------

                    clickOrder(orderNumber);

                    ExtentTestListener.logStep(
                            "SUCCESS : Clicked Completed Order : "
                                    + orderNumber
                    );

                    // ------------------------------------------------
                    // VALIDATE DETAILS
                    // ------------------------------------------------

                    validateOrderDetails(
                            orderNumber
                    );

                    // ------------------------------------------------
                    // BACK
                    // ------------------------------------------------

                    goBackToCompletedList();

                    // ------------------------------------------------
                    // MARK PROCESSED
                    // ------------------------------------------------

                    processedOrders.add(
                            orderNumber
                    );

                    validated++;

                    ExtentTestListener.logStep(
                            "SUCCESS : Completed Order "
                                    + orderNumber
                                    + " validated"
                    );

                    newOrderFound = true;

                    // Start searching fresh
                    scrollAttempts = 0;

                    break;

                } catch (Exception e) {

                    throw new RuntimeException(
                            "Failed while validating Completed Order",
                            e
                    );
                }
            }

            if (validated >= MAX_ORDERS) {
                break;
            }

            // --------------------------------------------------------
            // NO NEW ORDER -> SCROLL
            // --------------------------------------------------------

            if (!newOrderFound) {

                System.out.println(
                        "INFO : No new Completed order visible."
                );

                if (scrollCompletedOrders()) {

                    scrollAttempts++;

                    System.out.println(
                            "Scroll attempt : "
                                    + scrollAttempts
                    );

                } else {

                    scrollAttempts++;

                    System.out.println("WARNING : Scroll attempt failed : " + scrollAttempts);
                }
            }
        }

        // ============================================================
        // FINAL RESULT
        // ============================================================

        System.out.println();
        ExtentTestListener.logStep("TOTAL VALIDATED COMPLETED ORDERS : "
                        + validated);

        if (validated < MAX_ORDERS) {

            throw new RuntimeException(
                    "Only "
                            + validated
                            + " Completed orders were validated. "
                            + "Expected "
                            + MAX_ORDERS
            );
        }

        System.out.println();
        ExtentTestListener.logStep(
                "SUCCESS : First 5 Completed Orders validated"
        );
    }

    // ============================================================
    // EXTRACT ORDER NUMBER
    // ============================================================

    private String extractOrderNumber(
            String description) {

        if (description == null ||
                description.isBlank()) {
            return null;
        }

        Matcher matcher =
                Pattern.compile("[A-Za-z]+-RO-\\d+").matcher(description);

        return matcher.find()
                ? matcher.group()
                : null;
    }

    // ============================================================
    // CLICK ORDER
    // ============================================================

    private void clickOrder(
            String orderNumber) {

        By orderLocator = AppiumBy.xpath(
                        "//android.view.View[" +
                                "contains(@content-desc,'"
                                + orderNumber
                                + "')" +
                                "]");

        wait.until(ExpectedConditions.elementToBeClickable(orderLocator)).click();
    }

    // ============================================================
    // VALIDATE DETAILS
    // ============================================================

    private void validateOrderDetails(String orderNumber) {

        ExtentTestListener.logStep("VALIDATING ORDER DETAILS : "
                        + orderNumber);

        wait.until(driver -> driver.getPageSource().contains(orderNumber));

        ExtentTestListener.logStep("SUCCESS : Order Details validated : "
                        + orderNumber);
    }

    // ============================================================
    // BACK TO COMPLETED LIST
    // ============================================================

    private void goBackToCompletedList() {

        ExtentTestListener.logStep("ACTION : Going back to Completed Orders");

        driver.navigate().back();

        // Wait until Completed tab is available again
        wait.until(ExpectedConditions.presenceOfElementLocated(completedTab));

        // Wait for a fresh completed order element.
        wait.until(driver ->
                !driver.findElements(completedOrders).isEmpty());

        ExtentTestListener.logStep("SUCCESS : Returned to Completed Orders");
    }

    // ============================================================
    // SCROLL COMPLETED ORDERS
    // ============================================================

    private boolean scrollCompletedOrders() {

        try {

            ExtentTestListener.logStep("ACTION : Scrolling Completed orders...");

            driver.executeScript("mobile: swipeGesture", java.util.Map.of(
                            "left", 50,
                            "top", 500,
                            "width", 620,
                            "height", 800,
                            "direction", "up",
                            "percent", 0.75));

            ExtentTestListener.logStep("SUCCESS : Completed orders scrolled down");

            return true;

        }
        catch (Exception e)
        {
            System.out.println("WARNING : Swipe failed : " + e.getMessage());

            return false;
        }
    }
}