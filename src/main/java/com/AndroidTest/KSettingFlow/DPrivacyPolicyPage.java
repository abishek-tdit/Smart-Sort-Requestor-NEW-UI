package com.AndroidTest.KSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DPrivacyPolicyPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Privacy Policy
    private final By privacyPolicy =
            AppiumBy.accessibilityId("Privacy Policy");

    public DPrivacyPolicyPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickPrivacyPolicyAndGoBack() {

        // Wait until Privacy Policy is visible and clickable
        wait.until(ExpectedConditions.elementToBeClickable(privacyPolicy))
                .click();

        System.out.println("Privacy Policy clicked successfully");

        // Wait 3 seconds after opening Privacy Policy
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Privacy Policy page opened - waiting completed");

        // Go back
        driver.navigate().back();

        // Wait until Privacy Policy option is visible again
        wait.until(ExpectedConditions.visibilityOfElementLocated(privacyPolicy));

        System.out.println("Returned to Settings page");
    }
}