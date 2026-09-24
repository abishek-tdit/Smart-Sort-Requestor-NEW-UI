package com.AndroidTest.N2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.D2OrderDataReferEarn;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Map;

public class CNavigationPage extends BaseClassMobile {

    private static final int LONG_WAIT = 120;

    public void completeNavigation() {

        try {

            // ==========================================================
            // 1. Wait For Order
            // ==========================================================

            String orderNumber =
                    D2OrderDataReferEarn.orderNumberReferEarn;

            if (orderNumber == null ||
                    orderNumber.trim().isEmpty()) {

                throw new RuntimeException(
                        "Saved order number is empty"
                );
            }

            orderNumber = orderNumber.trim();

            ExtentTestListener.logStep(
                    "Waiting for Saved Order : " + orderNumber
            );

            By orderLocator = AppiumBy.xpath(
                    "//*[contains(@content-desc,\"" + orderNumber + "\") " +
                            "or contains(@text,\"" + orderNumber + "\")]"
            );

            waitForElement(orderLocator, LONG_WAIT);

            ExtentTestListener.logStep(
                    "Saved Order Loaded : " + orderNumber
            );

            // ==========================================================
            // 2. Find Start Navigation
            // ==========================================================

            ExtentTestListener.logStep(
                    "Waiting for Start Navigation"
            );

            WebElement startNavigation =
                    findStartNavigation();

            // ==========================================================
            // 3. Click Start Navigation
            // ==========================================================

            click(startNavigation);

            ExtentTestListener.logStep(
                    "Start Navigation Clicked"
            );

            // ==========================================================
            // 4. Second Start Navigation - If Application Shows It
            // ==========================================================

            waitForOptionalStartNavigation();

            // ==========================================================
            // 5. Wait For Reached
            // ==========================================================

            ExtentTestListener.logStep(
                    "Waiting for Reached"
            );

            WebElement reached = waitForElement(
                    AppiumBy.accessibilityId("Reached"),
                    LONG_WAIT
            );

            click(reached);

            ExtentTestListener.logStep(
                    "Reached Clicked"
            );

            // ==========================================================
            // 6. Wait For Success Popup
            // ==========================================================

            By successPopup = AppiumBy.xpath(
                    "//*[contains(@content-desc," +
                            "'Status updated successfully') " +
                            "or contains(@text," +
                            "'Status updated successfully')]"
            );

            waitForElement(
                    successPopup,
                    LONG_WAIT
            );

            ExtentTestListener.logStep(
                    "Status updated successfully displayed"
            );

            // ==========================================================
            // 7. Wait For Dashboard
            // ==========================================================

            waitForDashboard();

            ExtentTestListener.logStep(
                    "Returned to Dashboard"
            );

            ExtentTestListener.logStep(
                    "Navigation Flow Completed"
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Navigation Flow Failed : " +
                            e.getMessage()
            );

            throw new RuntimeException(
                    "Navigation Flow Failed",
                    e
            );
        }
    }

    // ==============================================================
    // Find Start Navigation
    // ==============================================================

    private WebElement findStartNavigation() {

        By locator =
                AppiumBy.accessibilityId("Start Navigation");

        // First wait
        try {

            return waitForElement(
                    locator,
                    30
            );

        } catch (Exception ignored) {
        }

        // Scroll search
        for (int attempt = 1; attempt <= 20; attempt++) {

            try {

                WebElement element =
                        driver.findElement(locator);

                if (element.isDisplayed()) {

                    ExtentTestListener.logStep(
                            "Start Navigation Found"
                    );

                    return element;
                }

            } catch (Exception ignored) {
            }

            swipeUp();

            ExtentTestListener.logStep(
                    "Searching Start Navigation - Scroll "
                            + attempt
            );

            try {

                return waitForElement(
                        locator,
                        5
                );

            } catch (Exception ignored) {
            }
        }

        throw new RuntimeException(
                "Start Navigation not found"
        );
    }

    // ==============================================================
    // Optional Second Start Navigation
    // ==============================================================

    private void waitForOptionalStartNavigation() {

        By locator =
                AppiumBy.accessibilityId("Start Navigation");

        try {

            WebElement second =
                    waitForElement(locator, 15);

            click(second);

            ExtentTestListener.logStep(
                    "Second Start Navigation Clicked"
            );

        } catch (Exception ignored) {

            ExtentTestListener.logStep(
                    "Second Start Navigation not displayed - continuing"
            );
        }
    }

    // ==============================================================
    // Dashboard
    // ==============================================================

    private void waitForDashboard() {

        // Give popup/page transition enough time.
        // We do not use Thread.sleep.

        By requestOrders =
                AppiumBy.accessibilityId("Request Orders (RO)");

        try {

            waitForElement(
                    requestOrders,
                    LONG_WAIT
            );

            return;

        } catch (Exception ignored) {
        }

        // If dashboard button is not immediately available,
        // wait through several controlled checks.

        for (int i = 1; i <= 10; i++) {

            try {

                WebElement dashboard =
                        driver.findElement(requestOrders);

                if (dashboard.isDisplayed()) {
                    return;
                }

            } catch (Exception ignored) {
            }

            try {

                driver.executeScript(
                        "mobile: swipeGesture",
                        Map.of(
                                "left", 60,
                                "top", 350,
                                "width", 600,
                                "height", 1000,
                                "direction", "down",
                                "percent", 0.30
                        )
                );

            } catch (Exception ignored) {
            }
        }

        throw new RuntimeException(
                "Dashboard not loaded after navigation"
        );
    }

    // ==============================================================
    // Wait For Element
    // ==============================================================

    private WebElement waitForElement(
            By locator,
            int timeoutSeconds) {

        return new org.openqa.selenium.support.ui.WebDriverWait(
                driver,
                Duration.ofSeconds(timeoutSeconds)
        )
                .pollingEvery(Duration.ofMillis(500))
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator
                        )
                );
    }

    // ==============================================================
    // Click
    // ==============================================================

    private void click(WebElement element) {

        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        ).click();
    }

    // ==============================================================
    // Swipe
    // ==============================================================

    private void swipeUp() {

        driver.executeScript(
                "mobile: swipeGesture",
                Map.of(
                        "left", 60,
                        "top", 350,
                        "width", 600,
                        "height", 1000,
                        "direction", "up",
                        "percent", 0.60
                )
        );
    }
}