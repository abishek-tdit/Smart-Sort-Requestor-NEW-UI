package com.AndroidTest.F1PlaceOrderForRescheduleFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class BDoorPickupPage extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(30));

    // =========================================================
    // LOCATORS
    // =========================================================

    // Request Now - Home Screen
    private final By requestNow =
            AppiumBy.accessibilityId("Request Now");

    // Material Checkbox
    private final By materialCheckBox =
            AppiumBy.className("android.widget.CheckBox");

    // Continue
    private final By continueButton =
            AppiumBy.accessibilityId("Continue");


    // =========================================================
    // MAIN FLOW
    // =========================================================

    public void selectDoorstepPickupFlow() {

        System.out.println("==============================================");
        System.out.println("Doorstep Pickup Flow Started");
        System.out.println("==============================================");

        // 1. Wait for Home Screen
        System.out.println("Waiting for Home screen to load...");

        WebElement requestNowButton = wait.until(
                ExpectedConditions.elementToBeClickable(requestNow)
        );

        requestNowButton.click();

        ExtentTestListener.logStep("Request Now clicked successfully");


        // 2. Select Material Checkbox
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        materialCheckBox
                )
        );

        checkbox.click();

        ExtentTestListener.logStep("Checkbox Selected");


        // 3. Click Continue
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueButton
                )
        );

        continueBtn.click();

        ExtentTestListener.logStep("Continue clicked");


        System.out.println("==============================================");
        ExtentTestListener.logStep("Doorstep Pickup Flow Completed");
        System.out.println("==============================================");
    }


    // =========================================================
    // SMALL SCROLL
    // =========================================================

    private void scrollLittleDown() {

        driver.executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", 50,
                        "top", 300,
                        "width", 620,
                        "height", 900,
                        "direction", "down",
                        "percent", 0.35
                )
        );
    }
}
