package com.AndroidTest.KSettingFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ETermsAndConditionsPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Terms & Conditions
    private final By termsAndConditions =
            AppiumBy.accessibilityId("Terms & Conditions");

    // Settings page
    private final By settings =
            AppiumBy.accessibilityId("Settings");

    public ETermsAndConditionsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // =========================================================
    // COMPLETE TERMS & CONDITIONS FLOW
    // =========================================================
    public void openValidateAndGoBack() {

        ExtentTestListener.logStep("===== TERMS & CONDITIONS FLOW STARTED =====");

        // -----------------------------------------------------
        // 1. Scroll down until Terms & Conditions is visible
        // -----------------------------------------------------
        scrollDownUntilTermsVisible();

        // -----------------------------------------------------
        // 2. Click Terms & Conditions
        // -----------------------------------------------------
        wait.until(ExpectedConditions.elementToBeClickable(termsAndConditions))
                .click();

        ExtentTestListener.logStep("Terms & Conditions clicked successfully");

        // -----------------------------------------------------
        // 3. Wait for Terms & Conditions page to load
        // -----------------------------------------------------
        wait.until(driver ->
                driver.getPageSource().contains("Terms")
                        || driver.getPageSource().contains("Conditions")
        );

        ExtentTestListener.logStep("Terms & Conditions page loaded successfully");

        // -----------------------------------------------------
        // 4. Scroll down inside Terms & Conditions page
        // -----------------------------------------------------
        scrollPageDown();

        ExtentTestListener.logStep("Terms & Conditions page scrolled down");

        // -----------------------------------------------------
        // 5. Scroll back up
        // -----------------------------------------------------
        scrollPageUp();

        ExtentTestListener.logStep("Terms & Conditions page scrolled back up");

        // -----------------------------------------------------
        // 6. Go back to Settings
        // -----------------------------------------------------
        driver.navigate().back();

        // -----------------------------------------------------
        // 7. Validate Settings page
        // -----------------------------------------------------
        wait.until(ExpectedConditions.visibilityOfElementLocated(settings));

        ExtentTestListener.logStep("Returned to Settings page successfully");
        ExtentTestListener.logStep("===== TERMS & CONDITIONS FLOW COMPLETED =====");
    }

    // =========================================================
    // SCROLL DOWN UNTIL TERMS & CONDITIONS IS FOUND
    // =========================================================
    private void scrollDownUntilTermsVisible() {

        int maxScrolls = 6;

        for (int i = 0; i < maxScrolls; i++) {

            if (!driver.findElements(termsAndConditions).isEmpty()) {

                System.out.println(
                        "Terms & Conditions found after " + i + " scroll(s)"
                );

                return;
            }

            scrollPageDown();
        }

        // Final wait/check
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                termsAndConditions
        ));

        ExtentTestListener.logStep("Terms & Conditions is visible");
    }

    // =========================================================
    // SCROLL DOWN
    // =========================================================
    private void scrollPageDown() {

        Map<String, Object> params = new HashMap<>();

        params.put("left", 50);
        params.put("top", 250);
        params.put("width", 620);
        params.put("height", 1050);
        params.put("direction", "down");
        params.put("percent", 0.75);

        driver.executeScript("mobile: scrollGesture", params);
    }

    // =========================================================
    // SCROLL UP
    // =========================================================
    private void scrollPageUp() {

        Map<String, Object> params = new HashMap<>();

        params.put("left", 50);
        params.put("top", 250);
        params.put("width", 620);
        params.put("height", 1050);
        params.put("direction", "up");
        params.put("percent", 0.75);

        driver.executeScript("mobile: scrollGesture", params);
    }
}
