package com.AndroidTest.C4CollectorNavigateToCollectionCenterFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.FSaveDONumberPage;
import utilities.StepTimer;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BCollectorNavigateToCollectionCenterDOPage extends BaseClassMobile {

    public void openAcceptedDeliveryOrders() throws Exception {

        try {

            // =========================================================
            // Click Delivery Orders
            // =========================================================
            StepTimer.startStep();

            ExtentTestListener.logStep(
                    "Clicking Delivery Orders (DO)...");

            WebElement deliveryOrders = waitUntilClickable(
                    AppiumBy.accessibilityId("Delivery Orders (DO)"), 20);

            deliveryOrders.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Delivery Orders (DO) Clicked | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =========================================================
            // Click Accepted Tab
            // =========================================================
            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking Accepted Tab...");

            WebElement acceptedTab = waitUntilClickable(AppiumBy.androidUIAutomator(
                            "new UiSelector().descriptionContains(\"Accepted\")"), 20);

            acceptedTab.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Accepted Tab Clicked Successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =========================================================
            // Click Saved Delivery Order
            // =========================================================
            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking Saved Delivery Order...");

            // Get saved DO number
            String savedDONumber = FSaveDONumberPage.getDONumber();

            if (savedDONumber == null || savedDONumber.trim().isEmpty()) {

                ExtentTestListener.logStep("Saved DO Number is not available");

                throw new RuntimeException("Saved DO Number is null or empty");
            }

            ExtentTestListener.logStep("Saved DO Number : " + savedDONumber);


            // Find DO Card Using Saved DO Number
            String doXpath = "//android.view.View[contains(@content-desc,\""
                            + savedDONumber
                            + "\")]";

            ExtentTestListener.logStep("Searching DO Card using saved DO Number");


            // Click DO Card
            WebElement orderDetails = waitUntilClickable(AppiumBy.xpath(doXpath), 20);

            orderDetails.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Saved Delivery Order Clicked Successfully : "
                            + savedDONumber + " | Time Taken : "
                            + String.format("%.2f", duration) + " sec");


            // =========================================================
            // First Start Navigation
            // =========================================================
            StepTimer.startStep();

            WebElement startNavigation = waitUntilClickable(
                    AppiumBy.accessibilityId("Start Navigation"), 20);

            startNavigation.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("First Start Navigation Clicked | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // Wait until first button disappears
            StepTimer.startStep();

            wait.until(ExpectedConditions.stalenessOf(startNavigation));

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("First Start Navigation Disappeared | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =========================================================
            // Second Start Navigation
            // =========================================================
            StepTimer.startStep();

            WebElement secondStartNavigation = waitUntilClickable(
                    AppiumBy.accessibilityId("Start Navigation"), 20);

            secondStartNavigation.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Second Start Navigation Clicked Successfully | Time Taken : "
                            + String.format("%.2f", duration) + " sec");


            // =========================================================
            // Reached Destination
            // =========================================================
            StepTimer.startStep();

            ExtentTestListener.logStep("Waiting for Reached Destination...");

            WebElement reachedDestination = waitUntilClickable(
                    AppiumBy.accessibilityId("Reached Destination"), 20);

            reachedDestination.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Reached Destination Clicked | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            Thread.sleep(4000);

            // =========================================================
            // OK Popup
            // =========================================================
            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking OK Popup...");

            WebElement okButton = waitUntilClickable(
                    AppiumBy.accessibilityId("OK"), 20);

            okButton.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("OK Popup Clicked | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            ExtentTestListener.logStep("Navigation completed successfully.");


            // =========================================================
            // Back Button
            // =========================================================
            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking Back Button...");

            WebElement backButton = waitUntilClickable(AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.view.View\").instance(6)"), 20);

            backButton.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Back Button Clicked Successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        } catch (Exception e) {

            ExtentTestListener.logStep("Error while opening Accepted Delivery Orders: "
                            + e.getMessage());

            throw e;
        }
    }
}