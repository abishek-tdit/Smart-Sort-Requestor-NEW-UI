package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.AOrderDataDoorPickup;

public class A2CollectorToRequestorChat extends BaseClassMobile {

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By requestOrdersRO =
            AppiumBy.accessibilityId("Request Orders (RO)");

    private final By chatButton = AppiumBy.androidUIAutomator
            ("new UiSelector()" +
                    ".className(\"android.widget.Button\")" +
                    ".instance(0)");

    private final By imOnTheWayButton =
            AppiumBy.accessibilityId("I'm on the way");

    private final By goBackButton =
            AppiumBy.accessibilityId("Go Back");


    // =========================================================
    // CLICK REQUEST ORDERS (RO)
    // =========================================================

    public void clickRequestOrdersRO() {

        ExtentTestListener.logStep("Waiting for Request Orders (RO)...");

        WebElement roButton = wait.until
                (ExpectedConditions.elementToBeClickable
                        (requestOrdersRO));

        roButton.click();

        ExtentTestListener.logStep("Request Orders (RO) clicked successfully");

        if (ExtentTestListener.getTest() != null) {
            ExtentTestListener.getTest().pass("Request Orders (RO) clicked successfully");
        }
    }


    // =========================================================
    // CLICK SAVED REQUEST ORDER
    // =========================================================

    public void clickSavedOrder() {

        String orderNumber = AOrderDataDoorPickup.orderNumberDoorPickup;

        if (orderNumber == null || orderNumber.isBlank()) {
            throw new RuntimeException("Saved Request Order number is empty");
        }

        ExtentTestListener.logStep("Searching saved Request Order : " + orderNumber);

        By savedOrder = AppiumBy.androidUIAutomator(
                        "new UiSelector()" +
                                ".className(\"android.view.View\")" +
                                ".descriptionContains(\"" +
                                orderNumber +
                                "\")");

        WebElement order =
                wait.until(ExpectedConditions.elementToBeClickable(savedOrder));

        order.click();

        ExtentTestListener.logStep("Saved Request Order clicked : " + orderNumber);

        if (ExtentTestListener.getTest() != null) {
            ExtentTestListener.getTest().pass("Saved Request Order clicked : " + orderNumber);
        }
    }


    // =========================================================
    // CLICK CHAT ICON
    // =========================================================

    public void clickChatIcon() throws InterruptedException {

        ExtentTestListener.logStep("Waiting for Chat icon...");

        Thread.sleep(5000);
        WebElement chat =
                wait.until(ExpectedConditions.elementToBeClickable(chatButton));

        chat.click();

        ExtentTestListener.logStep("Chat icon clicked successfully");

        if (ExtentTestListener.getTest() != null) {
            ExtentTestListener.getTest().pass("Chat icon clicked successfully");
        }
    }



// =========================================================
// CLICK I'M ON THE WAY
// =========================================================

    public void clickImOnTheWay() throws InterruptedException {

        ExtentTestListener.logStep("Waiting for 'I'm on the way'...");
        Thread.sleep(9000);
        try {

            WebElement button =
                    wait.until(ExpectedConditions.elementToBeClickable(imOnTheWayButton));

            ((io.appium.java_client.android.AndroidDriver) driver)
                    .executeScript("mobile: clickGesture", java.util.Map.of
                                   ("x", button.getRect().getX()
                                            + button.getRect().getWidth() / 2,
                                    "y", button.getRect().getY()
                                            + button.getRect().getHeight() / 2));

            ExtentTestListener.logStep("'I'm on the way' clicked successfully");

            if (ExtentTestListener.getTest() != null) {
                ExtentTestListener.getTest().pass("'I'm on the way' clicked successfully");
                driver.navigate().back();
            }

        } catch (Exception e) {

            ExtentTestListener.logStep("Failed to click 'I'm on the way': " + e.getMessage());

            if (ExtentTestListener.getTest() != null) {
                ExtentTestListener.getTest().fail("Failed to click 'I'm on the way'");
            }
            throw e;
        }
    }

// =========================================================
// GO BACK FROM CHAT
// =========================================================

    public void goBackFromChat() throws InterruptedException {
        Thread.sleep(3000);
        ExtentTestListener.logStep("Waiting for Go Back...");

        WebElement goBack =
                wait.until(ExpectedConditions.elementToBeClickable(goBackButton));

        goBack.click();

        ExtentTestListener.logStep("'Go Back' clicked successfully");
    }
}
