package com.AndroidTest.F2RequestorRescheduleFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EChatWithRequestor extends BaseClassMobile {

    public void openLatestRequestOrder() {

        // Click Request Orders
        ExtentTestListener.logStep("Navigating to Request Orders...");

        WebElement requestOrdersButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Request Orders (RO)")));
        requestOrdersButton.click();

        ExtentTestListener.logStep("Request Orders Clicked Successfully");
    }
    //=========================================================
    // Select Request Order
    //=========================================================

    public void selectRequestOrder(String orderNumberReschedule) {

        try {

            WebElement order = driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true))"
                                    + ".scrollIntoView("
                                    + "new UiSelector().descriptionContains(\""
                                    + orderNumberReschedule + "\"))"));

            wait.until(ExpectedConditions.elementToBeClickable(order));

            ExtentTestListener.logStep(
                    "Order Found : " + order.getAttribute("contentDescription"));

            order.click();

            ExtentTestListener.logStep(
                    "Order Clicked Successfully : " + orderNumberReschedule);

            if (ExtentTestListener.getTest() != null) {
                ExtentTestListener.getTest().pass(
                        "Selected Order : " + orderNumberReschedule);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Order not found : " + orderNumberReschedule, e);
        }
    }


    //=========================================================
    // Click Chat Icon
    //=========================================================

    public void clickChatIcon() {

        WebElement chatButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().className(\"android.widget.Button\").instance(0)")));

        chatButton.click();

        ExtentTestListener.logStep("Chat icon clicked successfully");
    }

    //=========================================================
    // Click I'm on the way
    //=========================================================

    public void clickImOnTheWay() throws InterruptedException {
        Thread.sleep(7000);
        WebElement onTheWayButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("I'm on the way")));

        onTheWayButton.click();

        ExtentTestListener.logStep("'I'm on the way' clicked successfully");
        driver.navigate().back();

        // Click Go Back
        WebElement goBackBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Go Back")));

        goBackBtn.click();

        ExtentTestListener.logStep("Go Back button clicked");

    }
}
