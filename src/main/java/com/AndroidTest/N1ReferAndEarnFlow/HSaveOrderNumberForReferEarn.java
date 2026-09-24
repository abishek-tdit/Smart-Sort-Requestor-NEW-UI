package com.AndroidTest.N1ReferAndEarnFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.D2OrderDataReferEarn;
import utilities.StepTimer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HSaveOrderNumberForReferEarn extends BaseClassMobile {

    private final By ORDERS =
            AppiumBy.accessibilityId("Orders");

    private final By PENDING =
            AppiumBy.accessibilityId("Pending");

    // Find order number from ANY element
    private final By FIRST_PENDING_ORDER =
            By.xpath("(//*[contains(@content-desc,'CH-RO-')])[1]");

    private static final Pattern ORDER_PATTERN =
            Pattern.compile("CH-RO-\\d+");

    // =========================================================
    // SAVE RECENTLY PLACED ORDER NUMBER
    // =========================================================

    public void saveRecentlyPlacedOrderNumber() {

        try {

            System.out.println("================================================");
            System.out.println("Saving Recently Placed Order Number");
            System.out.println("================================================");

            // =====================================================
            // STEP 1 - CLICK ORDERS
            // =====================================================

            StepTimer.startStep();

            WebElement ordersElement = wait.until(
                    ExpectedConditions.elementToBeClickable(ORDERS)
            );

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Wait For Orders Button | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            StepTimer.startStep();

            ordersElement.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Orders Click | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println("Orders clicked successfully");

            // =====================================================
            // STEP 2 - CLICK PENDING
            // =====================================================

            StepTimer.startStep();

            WebElement pendingElement = wait.until(
                    ExpectedConditions.elementToBeClickable(PENDING)
            );

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Pending Tab Available | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            StepTimer.startStep();

            pendingElement.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Pending Tab Click | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println("Pending tab clicked successfully");

            ExtentTestListener.logStep(
                    "Pending Tab Selected Successfully"
            );

            // =====================================================
            // STEP 3 - WAIT FOR FIRST PENDING ORDER
            // =====================================================

            StepTimer.startStep();

            WebElement firstOrder = wait
                    .withTimeout(java.time.Duration.ofSeconds(30))
                    .until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    FIRST_PENDING_ORDER
                            )
                    );

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "First Pending Order Found | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println("First Pending Order Found");

            // =====================================================
            // STEP 4 - GET CONTENT DESCRIPTION
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
                    "Order Content Description : "
                            + contentDescription
            );

            // =====================================================
            // STEP 5 - EXTRACT ORDER NUMBER
            // =====================================================

            if (contentDescription == null ||
                    contentDescription.trim().isEmpty()) {

                throw new RuntimeException(
                        "Order content-desc is empty or null"
                );
            }

            StepTimer.startStep();

            Matcher matcher =
                    ORDER_PATTERN.matcher(contentDescription);

            if (!matcher.find()) {

                throw new RuntimeException(
                        "CH-RO order number not found in content-desc : "
                                + contentDescription
                );
            }

            String orderNumber = matcher.group();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Order Number Extracted : "
                            + orderNumber
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );

            System.out.println(
                    "Order Number Found : " + orderNumber
            );

            // =====================================================
            // STEP 6 - SAVE ORDER NUMBER
            // =====================================================

            D2OrderDataReferEarn.orderNumberReferEarn =
                    orderNumber;

            System.out.println("================================================");
            System.out.println(
                    "Saved Refer & Earn Order Number : "
                            + D2OrderDataReferEarn.orderNumberReferEarn
            );
            System.out.println("================================================");

            ExtentTestListener.logStep(
                    "Refer & Earn Order Number Saved : "
                            + D2OrderDataReferEarn.orderNumberReferEarn
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to save recently placed order number"
            );

            System.out.println(
                    "Error : " + e.getMessage()
            );

            ExtentTestListener.logStep(
                    "Failed To Save Order Number : "
                            + e.getMessage()
            );

            throw new RuntimeException(
                    "Failed to save recently placed order number",
                    e
            );
        }
    }
}