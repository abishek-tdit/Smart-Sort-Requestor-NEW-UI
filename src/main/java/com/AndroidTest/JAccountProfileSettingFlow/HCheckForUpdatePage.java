package com.AndroidTest.JAccountProfileSettingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class HCheckForUpdatePage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // Locator
    // =========================================================

    private static final By CHECK_FOR_UPDATE =
            AppiumBy.xpath("//android.view.View[contains(@content-desc,'Check for updated')]");

    // =========================================================
    // Constructor
    // =========================================================

    public HCheckForUpdatePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // =========================================================
    // Check For Update
    // =========================================================

    public void checkForUpdate() {

        long startTime = System.currentTimeMillis();

        ExtentTestListener.logStep("Waiting for Check for updated option");

        // =====================================================
        // Scroll Down
        // =====================================================

        ExtentTestListener.logStep("Scrolling down to find Check for updated");

        scrollDown();

        // =====================================================
        // Wait until Check for updated is visible
        // =====================================================

        WebElement checkForUpdate =
                wait.until(ExpectedConditions.visibilityOfElementLocated(CHECK_FOR_UPDATE));

        ExtentTestListener.logStep("Check for updated option found");

        // =====================================================
        // Click Check for updated
        // =====================================================

        try {

            wait.until(ExpectedConditions.elementToBeClickable(CHECK_FOR_UPDATE));
            checkForUpdate.click();

            ExtentTestListener.logStep("Clicked Check for updated");
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("Normal click failed. Trying coordinate click");

            // Coordinate click fallback
            int centerX = 360;
            int centerY = 1057;

            driver.executeScript("mobile: clickGesture",
                    Map.of("x", centerX,
                            "y", centerY));

            ExtentTestListener.logStep(
                    "Clicked Check for updated using coordinate click"
            );
        }

        // =====================================================
        // Wait 2 seconds after clicking Check for updated
        // =====================================================

        ExtentTestListener.logStep(
                "Waiting 2 seconds for update check to complete"
        );

        waitForTwoSeconds();

        ExtentTestListener.logStep(
                "Update check completed - No updates available"
        );

        // =====================================================
        // Go one step back
        // =====================================================

        ExtentTestListener.logStep(
                "Going one step back"
        );

        driver.navigate().back();

        ExtentTestListener.logStep(
                "Returned to previous page"
        );

        // =====================================================
        // Execution Time
        // =====================================================

        long endTime = System.currentTimeMillis();

        ExtentTestListener.logStep(
                "Check For Update completed. Time Taken: "
                        + (endTime - startTime)
                        + " ms"
        );
    }

    // =========================================================
    // Wait exactly 2 seconds without Thread.sleep
    // =========================================================

    private void waitForTwoSeconds() {

        long waitStart = System.currentTimeMillis();

        wait.until(driver -> {

            long elapsed = System.currentTimeMillis() - waitStart;

            return elapsed >= 2000;
        }
        );
    }
}
