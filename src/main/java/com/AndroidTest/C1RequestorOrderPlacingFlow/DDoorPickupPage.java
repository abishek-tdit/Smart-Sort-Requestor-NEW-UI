package com.AndroidTest.C1RequestorOrderPlacingFlow;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class DDoorPickupPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // LOCATORS
    // =========================================================

    // Doorstep Pickup - Home Screen
    private final By requestNow =
            AppiumBy.accessibilityId("Request Now");

    // Checkbox
    private final By materialCheckBox =
            AppiumBy.className("android.widget.CheckBox");

    // Continue
    private final By continueButton =
            AppiumBy.accessibilityId("Continue");


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DDoorPickupPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );
    }


    // =========================================================
    // MAIN FLOW
    // =========================================================

    public void selectDoorstepPickupFlow() {

        System.out.println("==============================================");
        System.out.println("Doorstep Pickup Flow Started");
        System.out.println("==============================================");

        // =====================================================
        // 1. WAIT FOR HOME SCREEN
        // =====================================================

        System.out.println("Waiting for Home screen to load...");

        //REQUEST NOW
        WebElement requestNowButton = wait.until(
                ExpectedConditions.elementToBeClickable(requestNow)
        );

        requestNowButton.click();

        System.out.println("Request Now clicked successfully");


        // =====================================================
        // 4. CLICK CHECKBOX
        // =====================================================

        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        materialCheckBox
                )
        );

        checkbox.click();

        System.out.println("Checkbox Selected");


        // =====================================================
        // 5. CLICK CONTINUE
        // =====================================================

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueButton
                )
        );

        continueBtn.click();

        System.out.println("Continue clicked");


        System.out.println("==============================================");
        System.out.println("Doorstep Pickup Flow Completed");
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