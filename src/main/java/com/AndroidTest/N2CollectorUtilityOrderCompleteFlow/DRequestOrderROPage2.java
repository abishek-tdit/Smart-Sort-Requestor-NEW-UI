package com.AndroidTest.N2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.D2OrderDataReferEarn;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DRequestOrderROPage2 extends BaseClassMobile {

    private static final int LONG_WAIT = 120;

    public void completeOrder() {

        try {

            // ==========================================================
            // 1. Wait For Request Orders
            // ==========================================================

            ExtentTestListener.logStep(
                    "Waiting for Request Orders"
            );

            WebElement requestOrders =
                    waitForElement(
                            AppiumBy.accessibilityId(
                                    "Request Orders (RO)"
                            ),
                            LONG_WAIT
                    );

            requestOrders.click();

            ExtentTestListener.logStep(
                    "Request Orders (RO) Clicked"
            );

            // ==========================================================
            // 2. Order Number
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
                    "Searching Saved Order : " + orderNumber
            );

            // ==========================================================
            // 3. Find Order
            // ==========================================================

            By orderLocator = AppiumBy.xpath(
                    "//*[contains(@content-desc,\"" + orderNumber + "\") " +
                            "or contains(@text,\"" + orderNumber + "\")]"
            );

            WebElement order =
                    findOrder(orderLocator);

            if (order == null) {

                throw new RuntimeException(
                        "Saved order not found : " +
                                orderNumber
                );
            }

            // ==========================================================
            // 4. Click Order
            // ==========================================================

            wait.until(
                    ExpectedConditions.elementToBeClickable(order)
            );

            order.click();

            ExtentTestListener.logStep(
                    "Saved Order Clicked : " + orderNumber
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Request Order Page 2 Failed : " +
                            e.getMessage()
            );

            throw new RuntimeException(
                    "Unable to open order : " +
                            e.getMessage(),
                    e
            );
        }
    }

    // ==============================================================
    // Find Order
    // ==============================================================

    private WebElement findOrder(By locator) {

        // First check current screen
        try {

            return waitForElement(
                    locator,
                    30
            );

        } catch (Exception ignored) {
        }

        // Scroll until found
        for (int i = 1; i <= 20; i++) {

            try {

                WebElement order =
                        driver.findElement(locator);

                if (order.isDisplayed()) {
                    return order;
                }

            } catch (Exception ignored) {
            }

            swipeUp();

            ExtentTestListener.logStep(
                    "Searching Order - Scroll " + i
            );

            try {

                return waitForElement(
                        locator,
                        5
                );

            } catch (Exception ignored) {
            }
        }

        return null;
    }

    // ==============================================================
    // Wait
    // ==============================================================

    private WebElement waitForElement(
            By locator,
            int timeoutSeconds) {

        return new org.openqa.selenium.support.ui.WebDriverWait(
                driver,
                java.time.Duration.ofSeconds(timeoutSeconds)
        )
                .pollingEvery(
                        java.time.Duration.ofMillis(500)
                )
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator
                        )
                );
    }

    // ==============================================================
    // Swipe
    // ==============================================================

    private void swipeUp() {

        driver.executeScript(
                "mobile: swipeGesture",
                java.util.Map.of(
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