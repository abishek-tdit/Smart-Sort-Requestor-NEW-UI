package com.AndroidTest.H2SupportCenterSmartBotTicketManagementFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AChatWithAI extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

    private final By chatNow =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().description(\"Chat Now\")");

    private final By startNewChat =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().description(\"Start new chat\")");

    public void startChatWithAI() {

        wait.until(ExpectedConditions.elementToBeClickable(chatNow)).click();
        ExtentTestListener.logStep("Chat Now clicked successfully");

        wait.until(ExpectedConditions.elementToBeClickable(startNewChat)).click();
        ExtentTestListener.logStep("Start new chat clicked successfully");
    }
}
