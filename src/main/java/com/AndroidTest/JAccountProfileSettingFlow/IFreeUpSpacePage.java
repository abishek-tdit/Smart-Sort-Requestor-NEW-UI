package com.AndroidTest.JAccountProfileSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class IFreeUpSpacePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Use partial content-desc instead of the complete dynamic description
    private final By freeUpSpace = AppiumBy.xpath(
            "//android.view.View[contains(@content-desc,'Free up space')]"
    );

    private final By optimize = AppiumBy.accessibilityId("Optimize");

    private final By reset = AppiumBy.accessibilityId("Reset");

    private final By cancel = AppiumBy.accessibilityId("Cancel");


    public IFreeUpSpacePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    // =========================================================
    // SCROLL UNTIL FREE UP SPACE IS FOUND
    // =========================================================
    private void scrollToFreeUpSpace() {

        System.out.println("Searching for Free up space...");

        for (int i = 0; i < 10; i++) {

            try {
                WebElement element = driver.findElement(freeUpSpace);

                if (element.isDisplayed()) {
                    System.out.println(
                            "Free up space found after " + i + " scroll(s)."
                    );
                    return;
                }

            } catch (Exception ignored) {
                // Element not visible yet
            }

            System.out.println("Scrolling down... " + (i + 1));

            driver.executeScript(
                    "mobile: scrollGesture",
                    Map.of(
                            "left", 50,
                            "top", 300,
                            "width", 620,
                            "height", 1100,
                            "direction", "down",
                            "percent", 0.70
                    )
            );

            // Small wait for UI to settle
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        throw new RuntimeException(
                "Free up space was not found after scrolling."
        );
    }


    // =========================================================
    // CLICK FREE UP SPACE
    // =========================================================
    public void clickFreeUpSpace() {

        scrollToFreeUpSpace();

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(freeUpSpace)
        );

        element.click();

        System.out.println("Free up space clicked successfully.");
    }


    // =========================================================
    // CLICK OPTIMIZE
    // =========================================================
    public void clickOptimize() {

        wait.until(
                ExpectedConditions.elementToBeClickable(optimize)
        ).click();

        System.out.println("Optimize clicked.");

        // Required 3-second wait
        waitForSeconds(3);

        System.out.println("3 seconds wait completed.");
    }


    // =========================================================
    // CLICK RESET
    // =========================================================
    public void clickReset() {

        wait.until(
                ExpectedConditions.elementToBeClickable(reset)
        ).click();

        System.out.println("Reset clicked.");
    }


    // =========================================================
    // CLICK CANCEL
    // =========================================================
    public void clickCancel() {

        wait.until(
                ExpectedConditions.elementToBeClickable(cancel)
        ).click();

        System.out.println("Cancel clicked.");
    }


    // =========================================================
    // GO BACK
    // =========================================================
    public void goBack() {

        driver.navigate().back();

        System.out.println("Navigated back successfully.");
    }


    // =========================================================
    // 3 SECOND WAIT
    // =========================================================
    private void waitForSeconds(int seconds) {

        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    // =========================================================
    // COMPLETE FLOW
    // =========================================================
    public void completeFreeUpSpaceFlow() {

        clickFreeUpSpace();

        clickOptimize();

        clickReset();

        clickCancel();

        goBack();

        System.out.println(
                "========== FREE UP SPACE FLOW COMPLETED =========="
        );
    }
}