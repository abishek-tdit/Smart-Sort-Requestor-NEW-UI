package com.AndroidTest.KSettingFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class FAboutUsPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // About Us
    private final By aboutUs =
            AppiumBy.accessibilityId("About Us");

    public FAboutUsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Click About Us from Settings.
     * Scrolls down if About Us is not currently visible.
     */
    public void clickAboutUs() {

        // Already visible
        if (!driver.findElements(aboutUs).isEmpty()) {
            WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(aboutUs)
            );

            element.click();
            System.out.println("About Us clicked successfully.");
            return;
        }

        // Scroll down until About Us is visible
        for (int i = 0; i < 5; i++) {

            if (!driver.findElements(aboutUs).isEmpty()) {
                WebElement element = wait.until(
                        ExpectedConditions.elementToBeClickable(aboutUs)
                );

                element.click();
                System.out.println("About Us clicked successfully.");
                return;
            }

            driver.executeScript(
                    "mobile: scrollGesture",
                    Map.of(
                            "left", 50,
                            "top", 250,
                            "width", 620,
                            "height", 1000,
                            "direction", "down",
                            "percent", 0.70
                    )
            );
        }

        throw new RuntimeException("About Us was not found.");
    }

    /**
     * Wait 2 seconds after opening About Us,
     * then go back to Settings.
     */
    public void waitAndGoBack() {

        wait.until(driver ->
                driver.getPageSource() != null
                        && !driver.getPageSource().isEmpty()
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        driver.navigate().back();

        ExtentTestListener.logStep("Returned to Settings from About Us.");
    }

    /**
     * Complete About Us flow.
     */
    public void openAboutUsAndBack() {
        clickAboutUs();
        waitAndGoBack();
    }
}