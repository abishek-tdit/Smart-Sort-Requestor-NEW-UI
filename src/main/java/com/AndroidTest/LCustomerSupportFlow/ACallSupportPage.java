package com.AndroidTest.LCustomerSupportFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class ACallSupportPage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================
    // Locators
    // =========================

    private final By SUPPORT_CENTER =
            AppiumBy.xpath(
                    "//android.view.View[" +
                            "contains(@content-desc,'Customer Support') " +
                            "and contains(@content-desc,'Support Center')" +
                            "]"
            );


    private final By CALL_NOW =
            AppiumBy.accessibilityId("Call Now");

    private final By CALL_BUTTON =
            AppiumBy.accessibilityId("Call");

    // =========================
    // Constructor
    // =========================

    public ACallSupportPage() {

        this.driver = BaseClassMobile.driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    // =========================
    // Click Support Center
    // =========================

    public void clickSupportCenter() {

        System.out.println(
                "========== SUPPORT CENTER FLOW STARTED =========="
        );

        int maxScrolls = 6;
        int scrollCount = 0;

        // Scroll until Support Center is visible
        while (
                driver.findElements(SUPPORT_CENTER).isEmpty()
                        && scrollCount < maxScrolls
        ) {

            driver.executeScript(
                    "mobile: scrollGesture",
                    Map.of(
                            "left", 50,
                            "top", 300,
                            "width", 620,
                            "height", 1000,
                            "direction", "down",
                            "percent", 0.8
                    )
            );

            scrollCount++;

            System.out.println(
                    "Scrolled down - attempt "
                            + scrollCount
            );
        }

        // Click Support Center
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        SUPPORT_CENTER
                )
        ).click();

        System.out.println(
                "Support Center clicked successfully"
        );

        ExtentTestListener.logStep(
                "Support Center clicked successfully"
        );
    }

    // =========================
    // Click Call Now
    // =========================

    public void clickCallNow() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        CALL_NOW
                )
        ).click();

        System.out.println(
                "Call Now clicked successfully"
        );

        ExtentTestListener.logStep(
                "Call Now clicked successfully"
        );
    }

    // =========================
    // Confirm Call Popup
    // =========================

    public void confirmCall() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        CALL_BUTTON
                )
        ).click();

        System.out.println(
                "Call button clicked successfully"
        );

        ExtentTestListener.logStep(
                "Call button clicked successfully"
        );
    }

    // =========================
    // Go Back From Call Log
    // =========================

    public void returnToApp() {

        System.out.println("Going back from Call Log...");

        // BACK - 1
        driver.pressKey(
                new KeyEvent(AndroidKey.BACK)
        );

        System.out.println("Android BACK - 1 pressed");

        // BACK - 2
        driver.pressKey(
                new KeyEvent(AndroidKey.BACK)
        );

        System.out.println("Android BACK - 2 pressed");

        ExtentTestListener.logStep(
                "Android BACK pressed 2 times"
        );
    }

    // =========================
    // Complete Call Support Flow
    // =========================

    public void callSupportCenter() {

        clickSupportCenter();

        clickCallNow();

        confirmCall();

        returnToApp();
    }
}
