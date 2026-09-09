package com.AndroidTest.F1PlaceOrderForRescheduleFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.BOrderDataForReschedule;
import utilities.StepTimer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ESaveOrderNumberForReschedule extends BaseClassMobile {

    // =========================================================
    // LOCATORS
    // =========================================================

    // Orders
    private final By ORDERS = AppiumBy.accessibilityId("Orders");
    // Pending Tab
    private final By PENDING = AppiumBy.accessibilityId("Pending");
    private final By FIRST_PENDING_ORDER = By.xpath(
            "(//android.widget.ImageView[contains(@content-desc,'AB-RO-')])[1]");


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
            Thread.sleep(10000);
            StepTimer.startStep();

            WebElement ordersElement = wait.until(ExpectedConditions.elementToBeClickable(ORDERS));

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Wait For Orders Button | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            StepTimer.startStep();

            ordersElement.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Orders Click | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            System.out.println("Orders clicked successfully");

            ExtentTestListener.logStep("Orders Page Opened Successfully");


            // =====================================================
            // WAIT 4 SECONDS
            // =====================================================

            StepTimer.startStep();

            Thread.sleep(4000);

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Wait After Orders | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            // =====================================================
            // STEP 2 - CLICK PENDING
            // =====================================================

            StepTimer.startStep();

            WebElement pendingElement = wait.until(ExpectedConditions.elementToBeClickable(PENDING));

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Wait For Pending Tab | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            StepTimer.startStep();

            pendingElement.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Pending Tab Click | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            System.out.println("Pending tab clicked successfully");

            ExtentTestListener.logStep("Pending Tab Selected Successfully");


            // =====================================================
            // WAIT 4 SECONDS
            // =====================================================

            StepTimer.startStep();

            Thread.sleep(4000);

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Wait After Pending Tab | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            // =====================================================
            // STEP 3 - FIND FIRST PENDING ORDER
            // =====================================================

            StepTimer.startStep();

            WebElement firstOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    FIRST_PENDING_ORDER));

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Find First Pending Order | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            System.out.println("First Pending Order Found");

            ExtentTestListener.logStep("Pending Order Selected Successfully");


            // =====================================================
            // WAIT 4 SECONDS
            // =====================================================

            StepTimer.startStep();

            Thread.sleep(4000);

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Wait Before Getting Order Number | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            // =====================================================
            // STEP 4 - GET CONTENT-DESC
            // =====================================================

            StepTimer.startStep();

            String contentDescription = firstOrder.getAttribute("content-desc");

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Get Order Content Description | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            System.out.println("Order Content Description : "
                    + contentDescription);


            // =====================================================
            // STEP 5 - EXTRACT ORDER NUMBER
            // =====================================================

            StepTimer.startStep();

            Pattern pattern = Pattern.compile("AB-RO-\\d+");

            Matcher matcher = pattern.matcher(contentDescription);

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Extract Order Number | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            // =====================================================
            // CHECK ORDER NUMBER
            // =====================================================

            if (matcher.find()) {

                String orderNumber = matcher.group();


                // =================================================
                // STEP 6 - SAVE ORDER NUMBER
                // =================================================

                StepTimer.startStep();

                BOrderDataForReschedule.orderNumberForReschedule = orderNumber;

                duration = StepTimer.endStep();

                ExtentTestListener.logStep("Save Order Number | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

                System.out.println("==================================================================");
                System.out.println("Saved Order Number : " + BOrderDataForReschedule.orderNumberForReschedule);
                System.out.println("==================================================================");


                ExtentTestListener.logStep("Order Number Saved : " + BOrderDataForReschedule.orderNumberForReschedule);
            }
            else
            {
                throw new RuntimeException("Order number AB-RO-xxxxx was not found in content-desc : " + contentDescription);
            }

            // =====================================================
            // STEP 7 - NAVIGATE BACK
            // =====================================================

            StepTimer.startStep();

            driver.navigate().back();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Navigate Back | Time Taken : "
                    + String.format("%.2f", duration)
                    + " sec");


            System.out.println("Navigated back successfully");

            ExtentTestListener.logStep("Navigated Back Successfully");

        }
        catch (Exception e) {

            System.out.println("Failed to save recently placed order number");

            ExtentTestListener.logStep("Failed to Save Order Number");

            throw new RuntimeException("Failed to save recently placed order number",e);
        }
    }
}