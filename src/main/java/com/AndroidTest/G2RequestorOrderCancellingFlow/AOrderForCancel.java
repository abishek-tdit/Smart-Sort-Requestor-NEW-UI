package com.AndroidTest.G2RequestorOrderCancellingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.COrderDataCancel;

import java.time.Duration;

public class AOrderForCancel extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(30));

    // Orders
    private final By orders =
            AppiumBy.accessibilityId("Orders");

    // Cancel
    private final By cancel =
            AppiumBy.accessibilityId("Cancel");

    // Changed My Mind
    private final By changedMyMind =
            AppiumBy.accessibilityId("Changed My Mind");

    // Submit
    private final By submit =
            AppiumBy.accessibilityId("Submit");

    // OK
    private final By ok =
            AppiumBy.accessibilityId("OK");


    // =========================================================
    // COMPLETE CANCEL FLOW
    // =========================================================

    public void cancelSavedOrder() throws InterruptedException {

        // 1. Click Orders
        wait.until(ExpectedConditions.elementToBeClickable(orders))
                .click();

        ExtentTestListener.logStep("Orders clicked successfully");


        // 2. Click saved order dynamically
        String orderNumber = COrderDataCancel.orderNumberCancel;

        if (orderNumber == null || orderNumber.isBlank()) {
            throw new RuntimeException(
                    "Saved order number is empty in COrderDataCancel"
            );
        }

        By savedOrder = AppiumBy.xpath(
                "//android.widget.ImageView[contains(@content-desc,'"
                        + orderNumber +
                        "')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(savedOrder))
                .click();

        ExtentTestListener.logStep(
                "Saved Order clicked : " + orderNumber
        );


        // 3. Click Cancel
        scrollDown();
        scrollDown();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(cancel))
                .click();

        ExtentTestListener.logStep("Cancel clicked successfully");


        // 4. Select Ordered by Mistake
        By orderedByMistake = AppiumBy.accessibilityId("Ordered by Mistake");

        wait.until(ExpectedConditions.elementToBeClickable(orderedByMistake))
                .click();

        ExtentTestListener.logStep("Ordered by Mistake selected");


        // 5. Click Submit
        wait.until(ExpectedConditions.elementToBeClickable(submit))
                .click();

        ExtentTestListener.logStep("Submit clicked successfully");


        // 6. Click OK popup
        wait.until(ExpectedConditions.elementToBeClickable(ok))
                .click();

        ExtentTestListener.logStep("OK popup clicked");


        System.out.println(
                "Order cancelled successfully : " + orderNumber);
    }
}