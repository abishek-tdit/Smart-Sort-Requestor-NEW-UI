package com.AndroidTest.F2RequestorRescheduleFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class CChatWithCollectorPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public CChatWithCollectorPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    //=========================================================
    // Locators
    //=========================================================

    private final By ORDERS = AppiumBy.accessibilityId("Orders");

    //=========================================================
    // Click Orders
    //=========================================================

    public void clickChatWithCollector() {

        WebElement orders = wait.until(ExpectedConditions.elementToBeClickable(ORDERS));

        orders.click();

        ExtentTestListener.logStep("Orders clicked successfully");

        if (ExtentTestListener.getTest() != null) {
            ExtentTestListener.getTest().pass("Orders clicked successfully");
        }
    }

    //=========================================================
    // Select Order
    //=========================================================

    public void selectOrderToReschedule(String orderNumberReschedule) throws Exception {

        boolean found = false;

        By orderXpath = By.xpath
                ("//android.widget.ImageView[contains(@content-desc,'"
                        + orderNumberReschedule + "')]");

        for (int i = 0; i < 6; i++) {

            try {

                WebElement order = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(orderXpath));

                wait.until(ExpectedConditions.elementToBeClickable(order));

                String orderDetails = order.getAttribute("content-desc");
                ExtentTestListener.logStep("Order Found : " + orderDetails);

                order.click();

                ExtentTestListener.logStep(
                        "Order Clicked Successfully : " + orderNumberReschedule);

                if (ExtentTestListener.getTest() != null) {
                    ExtentTestListener.getTest().pass(
                            "Order selected successfully : " + orderNumberReschedule);
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
            throw new RuntimeException(
                    "Order not found : " + orderNumberReschedule);
        }
    }

    //=========================================================
    // Chat With Collector
    //=========================================================

    public void openChatAndReturn() throws Exception {

        WebElement chatBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Chat")));

        chatBtn.click();

        ExtentTestListener.logStep("Chat button clicked");

        Thread.sleep(5000);

        WebElement quickMsg = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Are you coming?")));

        quickMsg.click();

        ExtentTestListener.logStep(
                "Quick message 'Are you coming?' clicked");

        driver.navigate().back();

        WebElement goBackBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("go back")));

        goBackBtn.click();

        ExtentTestListener.logStep("Go Back button clicked");

        driver.navigate().back();
        driver.navigate().back();
    }
}