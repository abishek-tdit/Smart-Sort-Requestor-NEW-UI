package com.AndroidTest.H1SupportCenterSmartBotFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AChatWithAI extends BaseClassMobile {

    private final By SUPPORT_CENTER =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'Support Center') or contains(@text,'Support Center')]"
            );

    private final By CHAT_NOW =
            AppiumBy.accessibilityId("Chat Now");

    public void openAIChat() {

        ExtentTestListener.logStep("========== SMARTBOT TEST STARTED ==========");

        // STEP 1
        System.out.println("STEP 1 : Waiting for Support Center page to load...");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.className("android.view.View")
        ));

        ExtentTestListener.logStep("STEP 1 : Page loaded successfully.");

        // STEP 2
        ExtentTestListener.logStep("STEP 2 : Scrolling to Support Center...");

        for (int i = 0; i < 4; i++) {

            if (!driver.findElements(SUPPORT_CENTER).isEmpty()) {
                break;
            }

            scrollDown();
        }

        ExtentTestListener.logStep("STEP 2 : Support Center located.");

        // STEP 3
        WebElement supportCenter = wait.until(
                ExpectedConditions.visibilityOfElementLocated(SUPPORT_CENTER)
        );

        supportCenter.click();

        ExtentTestListener.logStep("STEP 3 : Support Center clicked successfully.");

        // STEP 4
        ExtentTestListener.logStep("STEP 4 : Waiting for Chat Now...");

        WebElement chatNow = wait.until(
                ExpectedConditions.visibilityOfElementLocated(CHAT_NOW)
        );

        chatNow.click();

        ExtentTestListener.logStep("STEP 4 : Chat Now clicked successfully.");
    }
}