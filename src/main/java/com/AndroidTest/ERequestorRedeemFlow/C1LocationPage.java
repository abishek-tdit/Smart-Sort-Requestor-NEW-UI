package com.AndroidTest.ERequestorRedeemFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.StepTimer;

import java.time.Duration;

public class C1LocationPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public C1LocationPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void selectLocation() throws Exception {

        // Wait until Home page loading is completed
        waitForPageToBeStable();
        ExtentTestListener.logStep("Home page loading completed");

        // Explore Other Locations
        StepTimer.startStep();

        WebElement exploreBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Explore Other Locations")));

        exploreBtn.click();

        double duration = StepTimer.endStep();
        ExtentTestListener.logStep("Explore Other Locations clicked | Time Taken : "
                + String.format("%.2f", duration)
                + " sec");

        // Bqaiq Location
        StepTimer.startStep();

        WebElement locationBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Bqaiq")));

        locationBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Location Selected : Bqaiq | Time Taken : "
                + String.format("%.2f", duration)
                + " sec");

        // Wait for loading
        waitForPageToBeStable();

        ExtentTestListener.logStep("Location loading completed");
    }

    private void waitForPageToBeStable() {

        String previousSource = " ";
        int stableCount = 0;
        long endTime = System.currentTimeMillis() + 60000;
        while (System.currentTimeMillis() < endTime) {
            String currentSource = driver.getPageSource();
            if (currentSource.equals(previousSource))
            {
                stableCount++;
                if (stableCount >= 3) {
                    return;
                }
            }
            else
            {
                stableCount = 0;
                previousSource = currentSource;
            }
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}