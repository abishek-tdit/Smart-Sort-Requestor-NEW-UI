package com.AndroidTest.C1RequestorOrderPlacingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.AOrderDataDoorPickup;

import java.time.Duration;

public class HRequestorToCollectorChat extends BaseClassMobile {

    // =========================================================
    // LOCATORS
    // =========================================================

    // Saved Order Number
    private By orderNumber;

    // Chat button
    private final By CHAT_BUTTON =
            AppiumBy.xpath("//android.widget.ScrollView/android.widget.Button[2]");

    // Quick Message
    private final By QUICK_MESSAGE = AppiumBy.accessibilityId("Are you coming?");

    // Popup Go Back
    private final By GO_BACK = AppiumBy.accessibilityId("go back");


    // =========================================================
    // CUSTOM WAIT
    // =========================================================
    private final WebDriverWait
            CHAT_WAIT = new WebDriverWait(driver, Duration.ofSeconds(40));


    // =========================================================
    // WAIT UNTIL VISIBLE AND CLICK
    // =========================================================

    private void waitAndClick(By locator, String stepName) {

        ExtentTestListener.logStep("Waiting for " + stepName + "...");

        WebElement element =
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        wait.until(ExpectedConditions.elementToBeClickable(locator));

        element.click();

        ExtentTestListener.logStep(stepName + " clicked successfully");
    }

    // =========================================================
    // OPEN SAVED ORDER
    // =========================================================

    public void openSavedOrder() {

        String savedOrder = AOrderDataDoorPickup.orderNumberDoorPickup;

        if (savedOrder == null || savedOrder.trim().isEmpty()) {

            throw new RuntimeException("Saved order number is empty");
        }

        orderNumber = AppiumBy.xpath(
                        "//android.widget.ImageView[contains(@content-desc,\""
                        + savedOrder
                        + "\")]");

        waitAndClick(orderNumber, "Saved order : " + savedOrder);

        ExtentTestListener.logStep("Saved order opened successfully : " + savedOrder);
    }


    // =========================================================
    // OPEN CHAT
    // =========================================================

    private void openChat() throws InterruptedException {

        // -----------------------------------------------------
        // WAIT FOR CHAT BUTTON
        // -----------------------------------------------------

        Thread.sleep(6000);
        waitAndClick(CHAT_BUTTON, "Chat button");

        ExtentTestListener.logStep("Chat button clicked. Waiting for Chat screen to load...");

        Thread.sleep(6000);
        WebElement quickMessage = CHAT_WAIT.until
                (ExpectedConditions.visibilityOfElementLocated
                        (QUICK_MESSAGE));

        CHAT_WAIT.until(ExpectedConditions.elementToBeClickable(quickMessage));

        ExtentTestListener.logStep("Chat screen loaded successfully");


        // -----------------------------------------------------
        // CLICK QUICK MESSAGE
        // -----------------------------------------------------

        quickMessage.click();

        ExtentTestListener.logStep("Quick message 'Are you coming?' clicked successfully");

        if (ExtentTestListener.getTest() != null) {

            ExtentTestListener.getTest().pass("Quick message 'Are you coming?' sent successfully");
        }
    }


    // =========================================================
    // RETURN FROM CHAT
    // =========================================================

    private void returnFromChat() throws InterruptedException {

        // -----------------------------------------------------
        // WAIT FOR CHAT SCREEN TO BE READY
        // -----------------------------------------------------
        Thread.sleep(3000);
        ExtentTestListener.logStep("Waiting for Chat screen before returning...");

        CHAT_WAIT.until(ExpectedConditions.visibilityOfElementLocated(QUICK_MESSAGE));


        // -----------------------------------------------------
        // ANDROID BACK
        // -----------------------------------------------------

        driver.navigate().back();

        ExtentTestListener.logStep("Chat screen back navigation performed");

        // -----------------------------------------------------
        // WAIT FOR POPUP
        // -----------------------------------------------------

        ExtentTestListener.logStep("Waiting for Popup 'Go Back'...");

        WebElement goBack =
                CHAT_WAIT.until(ExpectedConditions.visibilityOfElementLocated(GO_BACK));

        CHAT_WAIT.until(ExpectedConditions.elementToBeClickable(goBack));


        // -----------------------------------------------------
        // CLICK POPUP GO BACK
        // -----------------------------------------------------

        goBack.click();

        ExtentTestListener.logStep("Popup 'Go Back' button clicked successfully");

        if (ExtentTestListener.getTest() != null) {

            ExtentTestListener.getTest().pass("Popup Go Back button clicked successfully");
        }
    }


    // =========================================================
    // CHAT WITH COLLECTOR
    // =========================================================

    public void openChatAndReturn() throws InterruptedException {

        openChat();
        returnFromChat();
    }


    // =========================================================
    // COMPLETE FLOW
    // =========================================================

    public void executeChatFlow() throws InterruptedException {

        ExtentTestListener.logStep("Requestor to Collector Chat flow started");

        openSavedOrder();
        openChatAndReturn();

        ExtentTestListener.logStep("Requestor to Collector Chat flow completed");
    }
}
