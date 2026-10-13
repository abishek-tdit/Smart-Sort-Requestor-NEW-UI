package com.AndroidTest.C1RequestorOrderPlacingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.AOrderDataDoorPickup;
import utilities.StepTimer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GSaveOrderNumberForDoorPickup extends BaseClassMobile {

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By ORDERS =
            AppiumBy.accessibilityId("Orders");

    private final By PENDING =
            AppiumBy.accessibilityId("Pending");

    private final By FIRST_PENDING_ORDER =
            By.xpath("(//android.widget.ImageView[contains(@content-desc,'AB-RO-')])[1]");

    private static final Pattern ORDER_PATTERN =
            Pattern.compile("AB-RO-\\d+");

    private static final int MAX_RETRIES = 3;

    // =========================================================
    // CLICK ELEMENT WITH STALE ELEMENT RETRY
    // =========================================================

    private void clickWithRetry(By locator, String elementName) {

        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {

            try {

                WebElement element = wait.until(
                        ExpectedConditions.refreshed(
                                ExpectedConditions.elementToBeClickable(locator)
                        )
                );

                element.click();

                System.out.println(elementName + " clicked successfully");

                ExtentTestListener.logStep(
                        elementName + " clicked successfully"
                );

                return;

            } catch (StaleElementReferenceException e) {

                System.out.println(
                        elementName + " became stale. Retry "
                                + attempt + "/" + MAX_RETRIES
                );

                ExtentTestListener.logStep(
                        elementName + " became stale. Retry "
                                + attempt + "/" + MAX_RETRIES
                );
            }
        }

        throw new RuntimeException(
                "Failed to click " + elementName
                        + " after " + MAX_RETRIES + " attempts"
        );
    }

    // =========================================================
    // SAVE RECENTLY PLACED ORDER NUMBER
    // =========================================================

    public void saveRecentlyPlacedOrderNumber() {

        try {

            System.out.println("================================================");
            System.out.println("Saving Recently Placed Order Number");
            System.out.println("================================================");

            ExtentTestListener.logStep(
                    "Saving Recently Placed Order Number"
            );

            double duration;

            // =====================================================
            // STEP 1 - CLICK ORDERS
            // =====================================================

            System.out.println("STEP 1: Waiting for Orders button");

            StepTimer.startStep();

            clickWithRetry(ORDERS, "Orders Button");

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Orders Button Click | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println("Orders page navigation initiated");

            // =====================================================
            // STEP 2 - CLICK PENDING TAB
            // =====================================================

            System.out.println("STEP 2: Waiting for Pending tab");

            StepTimer.startStep();

            clickWithRetry(PENDING, "Pending Tab");

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Pending Tab Click | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println("Pending tab selected successfully");

            // =====================================================
            // STEP 3 - FIND FIRST PENDING ORDER
            // =====================================================

            System.out.println("STEP 3: Waiting for first pending order");

            StepTimer.startStep();

            WebElement firstOrder = wait.until(
                    ExpectedConditions.refreshed(
                            ExpectedConditions.visibilityOfElementLocated(
                                    FIRST_PENDING_ORDER
                            )
                    )
            );

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Find First Pending Order | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println("First pending order found successfully");

            // =====================================================
            // STEP 4 - GET ORDER CONTENT DESCRIPTION
            // =====================================================

            StepTimer.startStep();

            String contentDescription =
                    firstOrder.getAttribute("content-desc");

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Get Order Content Description | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println(
                    "Order Content Description: " + contentDescription
            );

            // =====================================================
            // STEP 5 - VALIDATE CONTENT DESCRIPTION
            // =====================================================

            if (contentDescription == null
                    || contentDescription.trim().isEmpty()) {

                throw new RuntimeException(
                        "Order content-desc is empty or null"
                );
            }

            // =====================================================
            // STEP 6 - EXTRACT ORDER NUMBER
            // =====================================================

            StepTimer.startStep();

            Matcher matcher =
                    ORDER_PATTERN.matcher(contentDescription);

            if (!matcher.find()) {

                StepTimer.endStep();

                throw new RuntimeException(
                        "Order number matching AB-RO-12345 was not found. "
                                + "Content description: " + contentDescription
                );
            }

            String orderNumber = matcher.group();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Extract Order Number | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println(
                    "Order Number Found: " + orderNumber
            );

            // =====================================================
            // STEP 7 - SAVE ORDER NUMBER
            // =====================================================

            StepTimer.startStep();

            AOrderDataDoorPickup.orderNumberDoorPickup = orderNumber;

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Save Order Number | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            // =====================================================
            // STEP 8 - CONFIRM SAVED ORDER NUMBER
            // =====================================================

            System.out.println("================================================");
            System.out.println(
                    "Saved Order Number: "
                            + AOrderDataDoorPickup.orderNumberDoorPickup
            );
            System.out.println("================================================");

            ExtentTestListener.logStep(
                    "Order Number Saved Successfully: "
                            + AOrderDataDoorPickup.orderNumberDoorPickup
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to save recently placed order number"
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );

            ExtentTestListener.logStep(
                    "Failed To Save Order Number: " + e.getMessage()
            );

            throw new RuntimeException(
                    "Failed to save recently placed order number",
                    e
            );
        }
    }
}