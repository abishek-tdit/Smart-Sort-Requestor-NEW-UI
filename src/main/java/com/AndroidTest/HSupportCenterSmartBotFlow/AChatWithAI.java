package com.AndroidTest.HSupportCenterSmartBotFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;

public class AChatWithAI extends BaseClassMobile {

    public void clickChatNow() {

        ExtentTestListener.logStep("Scrolling to bottom...");

        while (driver.findElements(AppiumBy.accessibilityId("Chat Now")).isEmpty()) {
            driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
                    )
            );
        }

        driver.findElement(AppiumBy.accessibilityId("Chat Now")).click();

        ExtentTestListener.logStep("Chat Now Clicked Successfully");
    }
}