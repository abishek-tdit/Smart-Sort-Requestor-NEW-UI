package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.StepTimer;

import java.time.Duration;
import java.util.Map;

public class DPickupOTPVerificationPage extends BaseClassMobile {

    public void verifyPickup() throws Exception {

        // Proceed with PIN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        StepTimer.startStep();

        WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable
                (AppiumBy.accessibilityId("Proceed with PIN")));

        proceedBtn.click();

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep("Proceed with PIN Clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        Thread.sleep(15000);


        // Verify
        StepTimer.startStep();

        WebElement verifyBtn = wait.until
                (ExpectedConditions.visibilityOfElementLocated
                        (AppiumBy.accessibilityId("Verify")));
        try {
            verifyBtn.click();
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("Normal click failed. Trying clickGesture");

            // Refresh the element
            verifyBtn = driver.findElement(AppiumBy.accessibilityId("Verify"));
            // Appium native click
            verifyBtn = driver.findElement(AppiumBy.accessibilityId("Verify"));

            verifyBtn.click();
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Verify Button Clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Proceed popup
        StepTimer.startStep();

        ((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
                Map.of("x", 360,
                        "y", 995));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Proceed Clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Wait for popup
        StepTimer.startStep();

        WebElement popup = wait.until
                (ExpectedConditions.presenceOfElementLocated
                        (AppiumBy.xpath("//android.view.View[contains(@content-desc,'Status updated successfully')]")));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Popup Detected | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        Rectangle rect = popup.getRect();

        int centerX = rect.getX() + rect.getWidth() / 2;
        int startY = rect.getY();

        // Scan multiple Y positions (CRITICAL FIX)
        int[] yOffsets = {
                (int) (rect.getHeight() * 0.60),
                (int) (rect.getHeight() * 0.65),
                (int) (rect.getHeight() * 0.70),
                (int) (rect.getHeight() * 0.75)
        };

        boolean clicked = false;

        StepTimer.startStep();

        for (int offset : yOffsets) {

            int tapY = startY + offset;

            ExtentTestListener.logStep("Trying tap at: " + centerX + ", " + tapY);

            ((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
                    Map.of("x", centerX,
                            "y", tapY));

            // CHECK IF POPUP CLOSED
            if (driver.findElements(AppiumBy.xpath
                    ("//android.view.View[contains(@content-desc,'Status updated successfully')]")).isEmpty()) {

                System.out.println("Successfully clicked Return to Dashboard");

                clicked = true;
                break;
            }
        }

        duration = StepTimer.endStep();
        ExtentTestListener.logStep("Return to Dashboard Tap Search | Time Taken : "
                        + String.format("%.2f", duration) + " sec");
        if (!clicked) {

            throw new RuntimeException(
                    "Could not click Return to Dashboard button");
        }
    }
}