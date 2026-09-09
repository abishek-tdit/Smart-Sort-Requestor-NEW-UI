package com.AndroidTest.HSupportCenterSmartBotFlow;

import Base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class BOpenSmartBotPage extends BasePage {

    private final By startNewChat =
            AppiumBy.accessibilityId("Start new chat");

    public BOpenSmartBotPage(AndroidDriver driver) {
        super(driver);
    }

    public void openSmartBot() {

        log("========== OPEN SMARTBOT ==========");

        log("Clicked SmartBot Icon");

        waitVisible(startNewChat);

        log("SmartBot Screen Opened");
    }

    public void startNewChat() {

        click(startNewChat);

        log("Clicked Start New Chat");

        delay(2);
    }

    public void openSmartBotAndStartChat() {

        openSmartBot();

        startNewChat();

        log("New Chat Started Successfully");
    }
}