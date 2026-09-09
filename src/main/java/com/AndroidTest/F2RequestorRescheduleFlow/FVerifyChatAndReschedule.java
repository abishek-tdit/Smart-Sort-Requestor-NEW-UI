package com.AndroidTest.F2RequestorRescheduleFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class FVerifyChatAndReschedule extends BaseClassMobile {

    //=========================================================
// Open Orders
//=========================================================

    public void openOrders() throws Exception {

        try {

            WebElement ordersBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.ImageView[@content-desc='Orders']")));

            ordersBtn.click();

            ExtentTestListener.logStep("Orders clicked successfully");

            if (ExtentTestListener.getTest() != null) {
                ExtentTestListener.getTest().pass("Clicked Orders");
            }

        } catch (Exception e) {

            throw new RuntimeException("Orders button not found or not clickable", e);
        }
    }

    //=========================================================
    // Select Order
    //=========================================================

    public void selectOrderToReschedule(String orderNumber) throws Exception {

        boolean found = false;

        By orderXpath = By.xpath(
                "//android.widget.ImageView[contains(@content-desc,'" + orderNumber + "')]");

        for (int i = 0; i < 6; i++) {

            try {

                WebElement order = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(orderXpath));

                wait.until(ExpectedConditions.elementToBeClickable(order));

                String orderDetails = order.getAttribute("content-desc");

                ExtentTestListener.logStep("Order Found : " + orderDetails);

                order.click();

                ExtentTestListener.logStep(
                        "Order Clicked Successfully : " + orderNumber);

                if (ExtentTestListener.getTest() != null) {
                    ExtentTestListener.getTest().pass(
                            "Order selected successfully : " + orderNumber);
                }

                found = true;
                break;

            } catch (Exception e) {

                driver.executeScript("mobile: swipeGesture",
                        Map.of(
                                "left", 100,
                                "top", 300,
                                "width", 500,
                                "height", 1000,
                                "direction", "up",
                                "percent", 0.80));

                Thread.sleep(2000);
            }
        }

        if (!found) {
            throw new RuntimeException("Order not found : " + orderNumber);
        }
    }

    //=========================================================
    // Open Chat
    //=========================================================

    public void openChatAndReturn() throws Exception {

        WebElement chatBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Chat")));

        chatBtn.click();

        ExtentTestListener.logStep("Chat button clicked");

        Thread.sleep(5000);

        driver.navigate().back();

        // Click Go Back
        WebElement goBackBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("go back")));
        goBackBtn.click();

        ExtentTestListener.logStep("Go Back button clicked");
    }

    public void clickRescheduleButton() throws Exception {

        // Scroll down a little
        driver.executeScript("mobile: swipeGesture",
                java.util.Map.of(
                        "left", 100,
                        "top", 400,
                        "width", 500,
                        "height", 900,
                        "direction", "up",
                        "percent", 0.30 ));

        Thread.sleep(1500);
        WebElement rescheduleBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Reschedule")));

        rescheduleBtn.click();

        ExtentTestListener.logStep("Reschedule button clicked");
    }
}