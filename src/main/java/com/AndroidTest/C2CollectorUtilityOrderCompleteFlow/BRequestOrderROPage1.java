package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.AOrderDataDoorPickup;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.StepTimer;

public class BRequestOrderROPage1 extends BaseClassMobile {

    public void openLatestRequestOrder() throws InterruptedException {

        // Click Request Orders
        ExtentTestListener.logStep("Navigating to Request Orders...");
        StepTimer.startStep();

        WebElement requestOrdersButton = wait.until
                (ExpectedConditions.elementToBeClickable
                        (AppiumBy.accessibilityId("Request Orders (RO)")));

        requestOrdersButton.click();

        double duration = StepTimer.endStep();
        ExtentTestListener.logStep("Request Orders Clicked Successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        // Click ORDER NO
        Thread.sleep(4000);
        String orderNumber = AOrderDataDoorPickup.orderNumberDoorPickup;
        try {
            StepTimer.startStep();

            WebElement order = driver.findElement
                    (AppiumBy.androidUIAutomator
                            ("new UiScrollable(new UiSelector().scrollable(true))"
                                    + ".scrollIntoView(new UiSelector().descriptionContains(\""
                                    + orderNumber + "\"))"));

            order.click();

            duration = StepTimer.endStep();
            ExtentTestListener.logStep("Clicked Order : " + orderNumber
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep(
                    "Order not found : " + orderNumber);
        }
    }
}