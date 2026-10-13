package com.AndroidTest.ERequestorRedeemFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.StepTimer;

import java.time.Duration;

public class A1LocationPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Fast normal wait
    private static final int FAST_WAIT = 8;

    // Fallback wait for slow loading
    private static final int FALLBACK_WAIT = 20;

    public A1LocationPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(FALLBACK_WAIT)
        );

        this.wait.pollingEvery(
                Duration.ofMillis(300)
        );
    }

    public void selectLocation() throws Exception {

        // =====================================================
        // 1. WAIT FOR HOME PAGE
        // =====================================================

        StepTimer.startStep();

        WebElement exploreBtn =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                AppiumBy.accessibilityId(
                                        "Explore Other Locations"
                                )
                        )
                );

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Home page loading completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        // =====================================================
        // 2. CLICK EXPLORE OTHER LOCATIONS
        // =====================================================

        StepTimer.startStep();

        exploreBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Explore Other Locations clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        // =====================================================
        // 3. SELECT BQAIQ
        // =====================================================

        StepTimer.startStep();

        WebElement locationBtn =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                AppiumBy.accessibilityId("Bqaiq")
                        )
                );

        locationBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Location Selected : Bqaiq | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        // =====================================================
        // 4. WAIT FOR NEXT SCREEN
        // =====================================================

        waitForLocationPage();

        ExtentTestListener.logStep(
                "Location loading completed"
        );
    }

    // =========================================================
    // WAIT FOR LOCATION PAGE
    // =========================================================

    private void waitForLocationPage() {

        try {

            new WebDriverWait(
                    driver, Duration.ofSeconds(FAST_WAIT))

                    .pollingEvery(Duration.ofMillis(300))
                    .until(driver -> !driver.findElements
                            (AppiumBy.accessibilityId("Explore Other Locations")).isEmpty());

        }
        catch (Exception ignored) {
        }
    }
}