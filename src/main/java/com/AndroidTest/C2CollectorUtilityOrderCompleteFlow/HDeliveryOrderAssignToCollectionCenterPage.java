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

    AndroidDriver driver;

    public HDeliveryOrderAssignToCollectionCenterPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void openRequestOrdersAndRODO() {

        // Click Request Orders (RO)
        StepTimer.startStep();

        WebElement requestOrders = waitUntilClickable(
                AppiumBy.accessibilityId("Request Orders (RO)"), 20);
        requestOrders.click();

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Clicked Request Orders (RO) | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Click RO - DO Tab
        StepTimer.startStep();

        WebElement roDoTab = waitUntilClickable(
                AppiumBy.accessibilityId("RO - DO\nTab 3 of 3"), 20);
        roDoTab.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Clicked RO - DO Tab | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        ExtentTestListener.getTest().info(
                "Request Orders and RO-DO Tab clicked successfully");


        // TIC-CHECKBOX
        StepTimer.startStep();

        WebElement firstOrderCheckbox = waitUntilClickable(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.widget.CheckBox\").instance(0)"), 20);

        firstOrderCheckbox.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Checkbox clicked for Order : "
                        + orderNumberDoorPickup
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Click Assign to CC
        StepTimer.startStep();

        WebElement assignToCC = waitUntilClickable(
                AppiumBy.accessibilityId("Assign to CC"), 20);
        assignToCC.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Clicked Assign to CC | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Select Collection Center
        StepTimer.startStep();

        WebElement cc = waitUntilClickable(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().descriptionContains(\"TTFVaasan\")"), 20);

        driver.executeScript(
                "mobile: clickGesture",
                Map.of(
                        "elementId",
                        ((RemoteWebElement) cc).getId()));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Selected Collection Center - TTFVaasan | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Click Assign Button
        StepTimer.startStep();

        WebElement assignBtn = waitUntilClickable(
                AppiumBy.accessibilityId("Assign"), 20);
        assignBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Clicked Assign | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Click OK Popup
        StepTimer.startStep();

        WebElement okBtn = waitUntilVisible(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().description(\"OK\")"), 20);

        okBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Clicked OK Popup | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Back Button
        StepTimer.startStep();

        WebElement backBtn = waitUntilClickable(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.view.View\").instance(6)"), 20);

        backBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Clicked Back Button | Time Taken : "
                        + String.format("%.2f", duration) + " sec");


        StepTimer.startStep();

        driver.navigate().back();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("System Back Navigation Completed | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        ExtentTestListener.getTest().info("Order selected, assigned to Collection Center, and confirmed successfully");
    }
}