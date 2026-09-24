package com.AndroidTest.H1SupportCenterSmartBotFlow;

import Base.BasePage;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class BStartNewChat extends BasePage {

    private final By startNewChat =
            AppiumBy.accessibilityId("Start new chat");

    public BStartNewChat(AndroidDriver driver) {
        super(driver);
    }



    public void startNewChat() {

        click(startNewChat);

        log("Clicked Start New Chat");

        delay(2);

        ExtentTestListener.logStep("Start New Chat");
    }

    public void openSmartBotAndStartChat() {

        startNewChat();

        log("New Chat Started Successfully");

        ExtentTestListener.logStep("New Chat Started");
    }
}