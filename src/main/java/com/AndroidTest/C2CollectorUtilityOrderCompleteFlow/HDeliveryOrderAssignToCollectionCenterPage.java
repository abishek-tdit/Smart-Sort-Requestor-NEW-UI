package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.StepTimer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Map;

import static utilities.AOrderDataDoorPickup.orderNumberDoorPickup;

public class HDeliveryOrderAssignToCollectionCenterPage extends BaseClassMobile {

    private final AndroidDriver driver;

    public HDeliveryOrderAssignToCollectionCenterPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void openRequestOrdersAndRODO() {

        double duration;

        // CLICK REQUEST ORDERS (RO)
        StepTimer.startStep();

        WebElement requestOrders =
                waitUntilClickable(AppiumBy.accessibilityId("Request Orders (RO)"), 20);

        requestOrders.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Clicked Request Orders (RO) | Time Taken : "
                + String.format("%.2f", duration)
                + " sec");


        // =====================================================
        // CLICK RO - DO TAB
        // =====================================================

        StepTimer.startStep();

        WebElement roDoTab = waitUntilClickable
                (AppiumBy.accessibilityId("RO - DO\nTab 3 of 3"), 20);

        roDoTab.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Clicked RO - DO Tab | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =====================================================
        // CLICK ORDER CHECKBOX
        // =====================================================

        StepTimer.startStep();

        WebElement firstOrderCheckbox =
                waitUntilClickable(AppiumBy.androidUIAutomator
                                ("new UiSelector()" +
                                ".className(\"android.widget.CheckBox\")" +
                                ".instance(0)"), 20);

        firstOrderCheckbox.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Checkbox clicked for Order : "
                        + orderNumberDoorPickup
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =====================================================
        // CLICK ASSIGN TO CC
        // =====================================================

        StepTimer.startStep();

        WebElement assignToCC =
                waitUntilClickable(AppiumBy.accessibilityId("Assign to CC"), 20);

        assignToCC.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Clicked Assign to CC | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =====================================================
        // SELECT COLLECTION CENTER
        // =====================================================

        StepTimer.startStep();

        WebElement cc = waitUntilClickable(AppiumBy.androidUIAutomator
                ("new UiSelector()" +
                        ".descriptionContains(\"TTFVaasan\")"), 20);

        driver.executeScript("mobile: clickGesture",
                Map.of("elementId", ((RemoteWebElement) cc).getId()));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Selected Collection Center - TTFVaasan | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =====================================================
        // CLICK ASSIGN
        // =====================================================

        StepTimer.startStep();

        WebElement assignBtn =
                waitUntilClickable(AppiumBy.accessibilityId("Assign"), 20);

        assignBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Clicked Assign | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =====================================================
        // CLICK OK POPUP
        // =====================================================

        StepTimer.startStep();

        WebElement okBtn =
                waitUntilVisible(AppiumBy.androidUIAutomator
                        ("new UiSelector().description(\"OK\")"),20);

        okBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Clicked OK Popup | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =====================================================
        // BACK TO PREVIOUS SCREEN
        // =====================================================

        StepTimer.startStep();

        try {
            WebElement backBtn = waitUntilClickable(AppiumBy.androidUIAutomator
                    ("new UiSelector()"
                            + ".className(\"android.view.View\")"
                            + ".instance(6)"), 10);

            backBtn.click();

            ExtentTestListener.getTest().info("App Back Button clicked successfully");
        }
        catch (Exception e)
        {
            ExtentTestListener.getTest().fail
                    ("Unable to click App Back Button: " + e.getMessage());
            throw e;
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Returned to previous Utility screen | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        ExtentTestListener.getTest().info
                ("Delivery Order Assignment Completed Successfully");

        ExtentTestListener.getTest().info("Utility App remains open");
    }
}