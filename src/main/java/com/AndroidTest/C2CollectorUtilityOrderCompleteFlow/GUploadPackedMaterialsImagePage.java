package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.StepTimer;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GUploadPackedMaterialsImagePage extends BaseClassMobile {

    public void uploadImage() throws InterruptedException {

        // =========================================================
        // Enter Weight
        // =========================================================

        StepTimer.startStep();

        WebElement weightField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.className("android.widget.EditText")));

        weightField.click();

        Thread.sleep(800);

        weightField.clear();
        weightField.sendKeys("10");

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Weight entered: 10 KG | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Hide Keyboard
        StepTimer.startStep();

        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Keyboard Hidden | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        StepTimer.startStep();

        Thread.sleep(2000);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Wait After Weight Entry | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // Manual Photo
        // =========================================================

        StepTimer.startStep();

        ExtentTestListener.logStep(
                "Please manually take photo and return to app...");

        Thread.sleep(10000);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Manual Photo Wait Completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // Check Appium Session
        // =========================================================

        boolean sessionRestarted = false;

        StepTimer.startStep();

        try {

            ExtentTestListener.logStep(
                    "Package = " + driver.getCurrentPackage());

            driver.getPageSource();

            ExtentTestListener.logStep("UiAutomator2 Alive");

        } catch (Exception e) {

            ExtentTestListener.logStep("UiAutomator2 Crashed");

            BaseClassMobile.restartSession();

            sessionRestarted = true;

            ExtentTestListener.logStep(
                    "Appium session restarted successfully");
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Appium Session Check Completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // Select Reason - OPTIONAL
        // =========================================================

        StepTimer.startStep();

        try {

            List<WebElement> reasonElements = driver.findElements(
                    AppiumBy.accessibilityId("Select Reason"));

            if (!reasonElements.isEmpty()
                    && reasonElements.get(0).isDisplayed()) {

                // Click Select Reason
                reasonElements.get(0).click();

                ExtentTestListener.logStep(
                        "Select Reason Clicked");

                // Select Technical Issue
                wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        AppiumBy.accessibilityId("Technical issue")))
                        .click();

                ExtentTestListener.logStep(
                        "Reason Selected : Technical issue");

                // Scroll down to Submit
                scrollDown();

            } else {

                ExtentTestListener.logStep(
                        "Select Reason not visible - Skipping Reason selection");
            }

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Select Reason not available - Moving to Submit button");
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Reason Selection Completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // Click Submit
        // =========================================================

        StepTimer.startStep();

        try {

            wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    AppiumBy.accessibilityId("Submit")))
                    .click();

            duration = StepTimer.endStep();

            if (sessionRestarted) {

                ExtentTestListener.logStep(
                        "Submit Clicked After Restart | Time Taken : "
                                + String.format("%.2f", duration)
                                + " sec");

            } else {

                ExtentTestListener.logStep(
                        "Submit Clicked | Time Taken : "
                                + String.format("%.2f", duration)
                                + " sec");
            }

        } catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Submit button not found | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            throw new RuntimeException(
                    "Submit button not found after session recovery", e);
        }


        // =========================================================
        // Wait After Submit
        // =========================================================

        StepTimer.startStep();

        Thread.sleep(5000);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Wait After Submit Completed | Time Taken : "
                        + String.format("%.2f", duration) + " sec");


        // =========================================================
        // Go To Dashboard
        // =========================================================

        StepTimer.startStep();

        wait.until(ExpectedConditions.elementToBeClickable
                (AppiumBy.accessibilityId(
                        "Go to Dashboard"))).click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Dashboard Clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");
    }
}