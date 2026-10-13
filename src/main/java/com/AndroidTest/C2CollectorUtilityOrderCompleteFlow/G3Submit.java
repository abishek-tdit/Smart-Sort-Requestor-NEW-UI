package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.StepTimer;

import java.time.Duration;
import java.util.List;

public class G3Submit extends BaseClassMobile {

    // =========================================================
    // SUBMIT ORDER
    // =========================================================

    public void submitOrder(boolean sessionRestarted) {

        double duration;

        // =========================================================
        // STEP 1 - VERIFY / RECOVER APPIUM SESSION
        // =========================================================

        StepTimer.startStep();

        try {

            if (!isAppiumSessionAlive()) {

                ExtentTestListener.logStep(
                        "UiAutomator2 Session Not Alive - Restarting SS Utility Session");

                restartUtilitySession();

                ExtentTestListener.logStep(
                        "SS Utility Appium Session Restarted Successfully");
            }
            else {

                ExtentTestListener.logStep(
                        "UiAutomator2 Session Alive - Continuing");
            }

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Appium Session Verification Completed | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        }
        catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Appium Session Recovery Failed | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            throw new RuntimeException(
                    "Unable to create a healthy SS Utility Appium session",
                    e);
        }


        // =========================================================
        // STEP 2 - SCROLL DOWN
        // =========================================================

        StepTimer.startStep();

        try {

            scrollDown();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Scrolled To Submit Section | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        }
        catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Scroll Failed | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            throw new RuntimeException(
                    "Unable to scroll after Appium session recovery",
                    e);
        }


        // =========================================================
        // STEP 3 - SELECT REASON - OPTIONAL
        // =========================================================

        StepTimer.startStep();

        try {

            List<WebElement> reasonElements =
                    driver.findElements(
                            AppiumBy.accessibilityId("Select Reason"));

            if (!reasonElements.isEmpty()
                    && reasonElements.get(0).isDisplayed()) {

                // -------------------------------------------------
                // Click Select Reason
                // -------------------------------------------------

                reasonElements.get(0).click();

                ExtentTestListener.logStep(
                        "Select Reason Clicked");


                // -------------------------------------------------
                // Select Technical Issue
                // -------------------------------------------------

                wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        AppiumBy.accessibilityId("Technical issue")))
                        .click();

                ExtentTestListener.logStep(
                        "Reason Selected : Technical issue");


                // -------------------------------------------------
                // Scroll Down To Submit
                // -------------------------------------------------

                scrollDown();

            }
            else {

                ExtentTestListener.logStep(
                        "Select Reason Not Visible - Skipping Reason Selection");
            }

        }
        catch (Exception e) {

            ExtentTestListener.logStep(
                    "Select Reason Not Available - Moving To Submit");
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Reason Selection Completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // STEP 4 - CLICK SUBMIT
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

            }
            else {

                ExtentTestListener.logStep(
                        "Submit Clicked | Time Taken : "
                                + String.format("%.2f", duration)
                                + " sec");
            }

        }
        catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Submit Button Not Found | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            throw new RuntimeException(
                    "Submit button not found after session recovery",
                    e);
        }


        // =========================================================
        // STEP 5 - WAIT FOR GO TO DASHBOARD
        // =========================================================

        StepTimer.startStep();

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId(
                                    "Go to Dashboard")));

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Go To Dashboard Button Displayed | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        }
        catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Go To Dashboard Button Not Found | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            throw new RuntimeException(
                    "Go to Dashboard button not found after Submit",
                    e);
        }


        // =========================================================
        // STEP 6 - CLICK GO TO DASHBOARD
        // =========================================================

        StepTimer.startStep();

        try {

            wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    AppiumBy.accessibilityId(
                                            "Go to Dashboard")))
                    .click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Dashboard Clicked | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        }
        catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Dashboard Click Failed | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            throw new RuntimeException(
                    "Failed to click Go to Dashboard",
                    e);
        }
    }


    // =========================================================
    // CHECK WHETHER APPIUM SESSION IS REALLY ALIVE
    // =========================================================

    private boolean isAppiumSessionAlive() {

        try {

            if (driver == null) {
                return false;
            }

            if (driver.getSessionId() == null) {
                return false;
            }
            driver.getPageSource();

            return true;

        }
        catch (Exception e) {

            return false;
        }
    }
}