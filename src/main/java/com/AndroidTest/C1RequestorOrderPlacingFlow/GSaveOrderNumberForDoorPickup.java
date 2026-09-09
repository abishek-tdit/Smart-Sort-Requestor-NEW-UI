package com.AndroidTest.C1RequestorOrderPlacingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
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

    private final By ORDERS = AppiumBy.accessibilityId("Orders");
    private final By PENDING = AppiumBy.accessibilityId("Pending");

    private final By FIRST_PENDING_ORDER = By.xpath(
            "(//android.widget.ImageView[contains(@content-desc,'AB-RO-')])[1]"
    );

    private static final Pattern ORDER_PATTERN =
            Pattern.compile("AB-RO-\\d+");

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

            ExtentTestListener.logStep(
                    "Orders Page Opened Successfully"
            );

            // =====================================================
            // STEP 2 - WAIT FOR PENDING TAB
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

            // =====================================================
            // CLICK PENDING
            // =====================================================

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
            // STEP 3 - FIND FIRST PENDING ORDER
            // =====================================================

            StepTimer.startStep();

            WebElement firstOrder = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            FIRST_PENDING_ORDER
                    )
            );

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Find First Pending Order | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println("First Pending Order Found");

            // =====================================================
            // STEP 4 - GET ORDER CONTENT-DESC
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
                    "Order Content Description : " + contentDescription
            );

            // =====================================================
            // VALIDATE CONTENT-DESC
            // =====================================================

            if (contentDescription == null ||
                    contentDescription.trim().isEmpty()) {

                throw new RuntimeException(
                        "Order content-desc is empty or null"
                );
            }

            // =====================================================
            // STEP 5 - EXTRACT ORDER NUMBER
            // =====================================================

            StepTimer.startStep();

            Matcher matcher =
                    ORDER_PATTERN.matcher(contentDescription);

            if (!matcher.find()) {
                throw new RuntimeException(
                        "Order number AB-RO-xxxxx not found in content-desc : "
                                + contentDescription
                );
            }

            String orderNumber = matcher.group();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Extract Order Number | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println(
                    "Order Number Found : " + orderNumber
            );

            // =====================================================
            // STEP 6 - SAVE ORDER NUMBER
            // =====================================================

            StepTimer.startStep();

            AOrderDataDoorPickup.orderNumberDoorPickup = orderNumber;

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Save Order Number | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println(
                    "================================================"
            );

            System.out.println(
                    "Saved Order Number : "
                            + AOrderDataDoorPickup.orderNumberDoorPickup
            );

            System.out.println(
                    "================================================"
            );

            ExtentTestListener.logStep(
                    "Order Number Saved : "
                            + AOrderDataDoorPickup.orderNumberDoorPickup
            );

            // =====================================================
            // STEP 7 - NAVIGATE BACK
            // =====================================================

            StepTimer.startStep();

            driver.navigate().back();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Navigate Back | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

            System.out.println("Navigated back successfully");

            ExtentTestListener.logStep(
                    "Navigated Back Successfully"
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