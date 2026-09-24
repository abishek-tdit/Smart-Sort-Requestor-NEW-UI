package com.AndroidTest.N2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.D2OrderDataReferEarn;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BRequestOrderROPage1 extends BaseClassMobile {

    private static final int LONG_WAIT = 120;

    public void openLatestRequestOrder() {

        try {

            ExtentTestListener.logStep("Waiting for Request Orders");

            // ==========================================================
            // 1. Click Request Orders
            // ==========================================================

            WebElement requestOrders = waitForElement(
                    AppiumBy.accessibilityId("Request Orders (RO)"),
                    LONG_WAIT
            );

            click(requestOrders);

            ExtentTestListener.logStep(
                    "Request Orders (RO) Clicked"
            );

            // ==========================================================
            // 2. Get Saved Order Number
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

            WebElement order = findOrder(orderNumber);

            if (order == null) {

                throw new RuntimeException(
                        "Saved order not found : " + orderNumber
                );
            }

            wait.until(
                    ExpectedConditions.elementToBeClickable(order)
            );

            order.click();

            ExtentTestListener.logStep(
                    "Saved Order Clicked : " + orderNumber
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Request Order Failed : " + e.getMessage()
            );

            throw new RuntimeException(
                    "Unable to open Request Order : " + e.getMessage(),
                    e
            );
        }
    }

    // ==============================================================
    // Find Order With Controlled Scrolling
    // ==============================================================

    private WebElement findOrder(String orderNumber) {

        By orderLocator = AppiumBy.xpath(
                "//*[contains(@content-desc,\"" + orderNumber + "\") " +
                        "or contains(@text,\"" + orderNumber + "\")]"
        );

        // First try without scrolling
        try {

            return waitForElement(orderLocator, 20);

        } catch (Exception ignored) {
        }

        // Controlled scrolling
        for (int i = 1; i <= 20; i++) {

            try {

                WebElement order =
                        driver.findElement(orderLocator);

                if (order.isDisplayed()) {

                    ExtentTestListener.logStep(
                            "Order Found : " + orderNumber
                    );

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
                        orderLocator,
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
        ).until(
                ExpectedConditions.visibilityOfElementLocated(locator)
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
    // Swipe Up
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
                        "percent", 0.65
                )
        );
    }
}