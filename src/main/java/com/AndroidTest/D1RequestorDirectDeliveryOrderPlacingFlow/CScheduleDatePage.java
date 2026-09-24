package com.AndroidTest.D1RequestorDirectDeliveryOrderPlacingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.StepTimer;

public class CScheduleDatePage extends BaseClassMobile {

    // Next hour arrow
    private final By NEXT_HOUR = AppiumBy.xpath(
            "//android.view.View[@content-desc='Hour']" +
                    "/following-sibling::android.view.View[1]" +
                    "/android.view.View[1]"
    );

    // Confirm button
    private final By CONFIRM_BUTTON =
            AppiumBy.accessibilityId("Confirm");

    // OK popup
    private final By OK_BUTTON =
            AppiumBy.accessibilityId("OK");


    public void clickNextHour() {
        scrollDown();
        scrollDown();
        StepTimer.startStep();

        WebElement nextHour =
                waitUntilClickable(NEXT_HOUR, 10);

        nextHour.click();

        ExtentTestListener.getTest().info(
                "Next hour selected | Time Taken : "
                        + StepTimer.endStep()
                        + " sec"
        );
    }


    public void clickConfirm() {

        StepTimer.startStep();

        WebElement confirm =
                waitUntilClickable(CONFIRM_BUTTON, 10);

        confirm.click();

        ExtentTestListener.getTest().info(
                "Confirm button clicked | Time Taken : "
                        + StepTimer.endStep()
                        + " sec"
        );
    }


    public void clickOK() {

        StepTimer.startStep();

        WebElement ok =
                waitUntilClickable(OK_BUTTON, 10);

        ok.click();

        ExtentTestListener.getTest().info(
                "OK button clicked | Time Taken : "
                        + StepTimer.endStep()
                        + " sec"
        );
    }
}