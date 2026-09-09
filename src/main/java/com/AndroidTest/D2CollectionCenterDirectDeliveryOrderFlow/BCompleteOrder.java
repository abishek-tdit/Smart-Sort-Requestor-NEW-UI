package com.AndroidTest.D2CollectionCenterDirectDeliveryOrderFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.StepTimer;

public class BCompleteOrder extends BaseClassMobile {

    // Submit Button
    By submitButton = AppiumBy.accessibilityId("Submit");

    // Proceed Button
    By proceedButton = AppiumBy.accessibilityId("PROCEED");

    // OK Popup Button
    By okButton = AppiumBy.accessibilityId("OK");

    // =========================================================
    // Click Submit
    // =========================================================
    public void clickSubmit() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Scrolling Until Submit Button Is Visible...");

            driver.findElement(AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true))" +
                                    ".scrollIntoView(new UiSelector().description(\"Submit\"))"));

            ExtentTestListener.logStep("Clicking Submit...");

            WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Submit")));

            submit.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep
                    ("Submit Clicked Successfully" + " | Time Taken : "
                            + String.format("%.2f", duration) + " sec");

        }
        catch (Exception e) {

            ExtentTestListener.logStep("Failed To Click Submit : " + e.getMessage());

            throw new RuntimeException(e);
        }
    }

    // =========================================================
    // Click Proceed
    // =========================================================
    public void clickProceed() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking PROCEED...");

            WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(proceedButton));

            proceed.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("PROCEED Clicked Successfully"
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        } catch (Exception e) {

            ExtentTestListener.logStep("Failed To Click PROCEED : " + e.getMessage());

            throw new RuntimeException(e);
        }
    }

    // =========================================================
    // Click OK Popup
    // =========================================================
    public void clickOkPopup() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Waiting 6 Seconds For Popup...");

            Thread.sleep(6000);

            ExtentTestListener.logStep("Clicking OK Popup...");

            WebElement ok = wait.until(ExpectedConditions.elementToBeClickable
                                                       (okButton));ok.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("OK Popup Clicked Successfully"
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        }
        catch (Exception e) {

            ExtentTestListener.logStep("Failed To Click OK Popup : " + e.getMessage());

            throw new RuntimeException(e);
        }
    }
}