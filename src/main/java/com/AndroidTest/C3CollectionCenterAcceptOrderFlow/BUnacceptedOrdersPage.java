package com.AndroidTest.C3CollectionCenterAcceptOrderFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.FSaveDONumberPage;
import utilities.StepTimer;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Collections;

public class BUnacceptedOrdersPage extends BaseClassMobile {

    // =========================================================
    // Click Unaccepted Orders
    // =========================================================
    public void clickUnacceptedOrders() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking Unaccepted Orders...");

            WebElement unacceptedOrders = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath("//android.view.View[contains(@content-desc,'Unaccepted Orders')]")));

            unacceptedOrders.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Unaccepted Orders clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        } catch (Exception e) {

            ExtentTestListener.logStep("Failed to click Unaccepted Orders: "
                            + e.getMessage());

            throw new RuntimeException("Unable to click Unaccepted Orders", e);
        }
    }


    // =========================================================
    // Click Saved Delivery Order
    // =========================================================
    public void clickOrder() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking saved Delivery Order...");


            // =====================================================
            // Get Saved DO Number
            // =====================================================
            String doNumber = FSaveDONumberPage.getDONumber();


            if (doNumber == null
                    || doNumber.trim().isEmpty()) {

                ExtentTestListener.logStep("Saved DO Number is empty.");

                throw new RuntimeException("Saved DO Number is null or empty.");
            }


            ExtentTestListener.logStep("Saved DO Number : " + doNumber);


            // =====================================================
            // Dynamic XPath
            // =====================================================
            String doXPath = "//android.view.View[contains(@content-desc,'"
                            + doNumber
                            + "')]";


            ExtentTestListener.logStep("Searching for DO : " + doNumber);


            // =====================================================
            // Wait For DO
            // =====================================================
            WebElement deliveryOrder = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath(doXPath)));


            // =====================================================
            // Click DO
            // =====================================================
            deliveryOrder.click();


            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Delivery Order clicked successfully : "
                            + doNumber
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        } catch (Exception e) {

            ExtentTestListener.logStep("Failed to click Delivery Order: " + e.getMessage());

            throw new RuntimeException("Unable to click saved Delivery Order", e);
        }
    }


    // =========================================================
    // Swipe To Accept
    // =========================================================
    public void swipeToAccept() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Swiping to Accept...");


            // =====================================================
            // Wait For Slider
            // =====================================================
            WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated
                    (AppiumBy.androidUIAutomator("new UiSelector()" +
                                            ".className(\"android.view.View\")" +
                                            ".instance(27)")));


            ExtentTestListener.logStep("Accept slider found successfully.");

            // =====================================================
            // Swipe Coordinates
            // =====================================================
            int startX = 90;
            int startY = 1425;

            int endX = 900;
            int endY = startY;

            // =====================================================
            // Create Touch Action
            // =====================================================
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            Sequence swipe = new Sequence(finger, 1);


            // Move to Start Position
            swipe.addAction(finger.createPointerMove(
                            Duration.ZERO,
                            PointerInput.Origin.viewport(),
                            startX,
                            startY));


            // Finger Down
            swipe.addAction(finger.createPointerDown(
                            PointerInput.MouseButton.LEFT.asArg()));


            // Move Finger
            swipe.addAction(finger.createPointerMove(
                            Duration.ofMillis(1000),
                            PointerInput.Origin.viewport(),
                            endX,
                            endY));


            // Finger Up
            swipe.addAction(finger.createPointerUp(
                            PointerInput.MouseButton.LEFT.asArg()));


            // Perform Swipe
            driver.perform(Collections.singletonList(swipe));

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Swipe to Accept completed successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        } catch (Exception e) {

            ExtentTestListener.logStep("Failed to swipe to Accept: " + e.getMessage());

            throw new RuntimeException("Unable to swipe to Accept", e);
        }
    }


    // =========================================================
    // Click OK Popup
    // =========================================================
    public void clickOkPopup() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Waiting for OK popup...");


            WebElement okButton = wait.until
                    (ExpectedConditions.elementToBeClickable
                            (AppiumBy.accessibilityId("OK")));


            okButton.click();


            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("OK popup clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        } catch (Exception e) {

            ExtentTestListener.logStep("Failed to click OK popup: " + e.getMessage());

            throw new RuntimeException("Unable to click OK popup", e);
        }
    }
}