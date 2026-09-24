package com.AndroidTest.D1RequestorDirectDeliveryOrderPlacingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import utilities.StepTimer;

import java.util.Map;

public class BDirectDeliveryPage extends Base.BaseClassMobile {

    // =====================================================
    // LOCATORS
    // =====================================================

    private final By directDelivery =
            AppiumBy.accessibilityId(
                    "Direct Delivery\nDeliver your items directly\nLearn More");

    private final By startDirectDelivery =
            AppiumBy.accessibilityId("Start Direct Delivery");

    private final By ttfVaasan =
            AppiumBy.accessibilityId("TTFVaasan 0.02 KM away");

    private final By checkbox =
            AppiumBy.className("android.widget.CheckBox");


    // =====================================================
    // DIRECT DELIVERY FLOW
    // =====================================================

    public void selectDirectDelivery() throws InterruptedException {

        double duration;


        // =================================================
        // STEP 1 - DIRECT DELIVERY
        // =================================================

        StepTimer.startStep();

        scrollDown();

        WebElement directDeliveryElement =
                waitUntilClickable(directDelivery, 15);

        directDeliveryElement.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Direct Delivery Clicked Successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =================================================
        // STEP 2 - START DIRECT DELIVERY
        // =================================================

        StepTimer.startStep();

        WebElement startDeliveryElement =
                waitUntilClickable(startDirectDelivery, 15);

        startDeliveryElement.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Start Direct Delivery Clicked Successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =================================================
// STEP 3 - SELECT TTF VAASAN
// =================================================
        StepTimer.startStep();

        WebElement ttfVaasanElement =
                waitUntilClickable(ttfVaasan, 15);

        try {
            driver.executeScript(
                    "mobile: clickGesture",
                    Map.of(
                            "elementId",
                            ((RemoteWebElement) ttfVaasanElement).getId()
                    )
            );
        } catch (Exception e) {
            ttfVaasanElement.click();
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "TTFVaasan Selected Successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        // =================================================
        // STEP 4 - CHECKBOX
        // =================================================

        StepTimer.startStep();

        WebElement checkboxElement =
                waitUntilClickable(checkbox, 15);

        checkboxElement.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Checkbox Clicked Successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =================================================
        // COMPLETED
        // =================================================

        ExtentTestListener.getTest().info(
                "Direct Delivery Collection Center Selection Completed Successfully");
    }
}
