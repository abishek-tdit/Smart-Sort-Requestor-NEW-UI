package com.AndroidTest.N1ReferAndEarnFlow;

import Base.BasePage;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GSlotPage extends BasePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public GSlotPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================================================
    // MATERIAL CATEGORY
    // =========================================================
    private final By mixedMaterials =
            AppiumBy.accessibilityId("Mixed-Materials");

    public void completeSlotFlow() {

        // =====================================================
        // 1. MIXED-MATERIALS
        // =====================================================
        WebElement material = wait.until(
                ExpectedConditions.elementToBeClickable(mixedMaterials)
        );

        material.click();

        ExtentTestListener.logStep(
                "Mixed-Materials selected successfully"
        );

        // =====================================================
        // 2. SELECT FIRST AVAILABLE SLOT
        // =====================================================
        By slot = By.xpath(
                "(//android.widget.ImageView[" +
                        "contains(@content-desc,'AM') and " +
                        "contains(@content-desc,'PM')" +
                        "])[1]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(slot)
        ).click();

        ExtentTestListener.logStep(
                "First available slot selected"
        );

        // =====================================================
        // 3. CONFIRM ORDER
        // =====================================================
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Confirm Order")
                )
        ).click();

        ExtentTestListener.logStep(
                "Confirm Order clicked"
        );

        // =====================================================
        // 4. OK POPUP
        // =====================================================
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("OK")
                )
        ).click();

        ExtentTestListener.logStep(
                "OK clicked"
        );
    }
}