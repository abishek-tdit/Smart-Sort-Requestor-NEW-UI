package com.AndroidTest.H2SupportCenterSmartBotTicketManagementFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AChatWithAI extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(20));

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By chatNow = AppiumBy.xpath
            ("//*[contains(@content-desc,'Chat Now') "
                    + "or contains(@text,'Chat Now')]");

    private final By startNewChat = AppiumBy.xpath
                    ("//*[contains(@content-desc,'Start new chat') " +
                            "or contains(@text,'Start new chat')]");

    // =========================================================
    // START CHAT WITH AI
    // =========================================================

    public void startChatWithAI() {

        ExtentTestListener.logStep("========== CHAT WITH AI STARTED ==========");

        // =====================================================
        // STEP 1 - WAIT FOR CHAT NOW
        // =====================================================

        System.out.println("STEP 1 : Waiting for Chat Now...");

        WebElement chatNowElement = wait.until
                (ExpectedConditions.visibilityOfElementLocated(chatNow));

        ExtentTestListener.logStep("STEP 1 : Chat Now located successfully");

        // =====================================================
        // STEP 2 - CLICK CHAT NOW
        // =====================================================

        wait.until(ExpectedConditions.elementToBeClickable(chatNow)).click();

        ExtentTestListener.logStep("STEP 2 : Chat Now clicked successfully");

        // =====================================================
        // STEP 3 - WAIT FOR START NEW CHAT
        // =====================================================

        System.out.println("STEP 3 : Waiting for Start new chat...");

        wait.until(ExpectedConditions.visibilityOfElementLocated(startNewChat));

        ExtentTestListener.logStep("STEP 3 : Start new chat located successfully");

        // =====================================================
        // STEP 4 - CLICK START NEW CHAT
        // =====================================================

        wait.until(ExpectedConditions.elementToBeClickable(startNewChat)).click();

        ExtentTestListener.logStep("STEP 4 : Start new chat clicked successfully");
    }
}