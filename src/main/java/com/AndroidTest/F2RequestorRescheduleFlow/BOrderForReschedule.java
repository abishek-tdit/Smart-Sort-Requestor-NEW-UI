package com.AndroidTest.F2RequestorRescheduleFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BOrderDataForReschedule;

import java.time.Duration;

public class BOrderForReschedule extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(40));

    // =========================================================
    // ORDERS
    // =========================================================

    private final By ORDERS_BUTTON =
            AppiumBy.accessibilityId("Orders");

    // =========================================================
    // PENDING
    // =========================================================

    private final By PENDING_BUTTON =
            AppiumBy.accessibilityId("Pending");

    // =========================================================
    // RESCHEDULE
    // =========================================================

    private final By RESCHEDULE_BUTTON =
            AppiumBy.accessibilityId("Reschedule");


    // =========================================================
    // CLICK ORDERS
    // =========================================================

    public void clickOrders() {

        ExtentTestListener.logStep("Waiting for Orders...");

        WebElement orders =
                wait.until(ExpectedConditions.elementToBeClickable(
                        ORDERS_BUTTON
                ));

        orders.click();

        ExtentTestListener.logStep(
                "Orders clicked successfully"
        );
    }


    // =========================================================
    // CLICK PENDING
    // =========================================================

    public void clickPending() {

        ExtentTestListener.logStep("Waiting for Pending...");

        WebElement pending =
                wait.until(ExpectedConditions.elementToBeClickable(
                        PENDING_BUTTON
                ));

        pending.click();

        ExtentTestListener.logStep(
                "Pending clicked successfully"
        );
    }


    // =========================================================
    // CLICK SAVED RESCHEDULE ORDER
    // =========================================================

    public void clickSavedRescheduleOrder() {

        String orderNumber =
                BOrderDataForReschedule.orderNumberForReschedule;

        if (orderNumber == null || orderNumber.trim().isEmpty()) {

            throw new IllegalStateException(
                    "Reschedule order number is not saved in " +
                            "BOrderDataForReschedule.orderNumberForReschedule"
            );
        }

        ExtentTestListener.logStep(
                "Searching saved Reschedule Order : " + orderNumber
        );

        // Dynamic locator using only the saved order number
        By savedOrder =
                AppiumBy.xpath(
                        "//android.widget.ImageView[contains(@content-desc,'" +
                                orderNumber +
                                "')]"
                );

        WebElement order =
                wait.until(ExpectedConditions.elementToBeClickable(
                        savedOrder
                ));

        order.click();

        ExtentTestListener.logStep(
                "Saved Reschedule Order clicked : " + orderNumber
        );
    }


    // =========================================================
    // CLICK RESCHEDULE
    // =========================================================

    public void clickReschedule() throws InterruptedException {
        Thread.sleep(5000);
        ExtentTestListener.logStep("Waiting for Reschedule...");

        scrollDown();

        WebElement reschedule = wait.until
                (ExpectedConditions.elementToBeClickable(RESCHEDULE_BUTTON));
        reschedule.click();

        ExtentTestListener.logStep("Reschedule clicked successfully");
    }


    // =========================================================
    // COMPLETE FLOW
    // =========================================================

    public void openSavedOrderForReschedule() throws InterruptedException {

        clickOrders();

        clickPending();

        clickSavedRescheduleOrder();

        clickReschedule();
    }
}