package com.AndroidTest.JAccountProfileSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class BQRCodePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public BQRCodePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By account =
            AppiumBy.accessibilityId("Account");

    // More stable than exact accessibilityId
    private final By userName =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'abishek251295@gmail.com')]"
            );

    // QR Code
    private final By qrCode =
            AppiumBy.xpath(
                    "//android.widget.ScrollView/android.view.View[2]"
            );


    // =========================================================
    // CLICK ACCOUNT
    // =========================================================

    public void clickAccount() {

        wait.until(
                ExpectedConditions.elementToBeClickable(account)
        ).click();

        System.out.println("Account clicked successfully");
    }


    // =========================================================
    // CLICK USER NAME
    // =========================================================

    public void clickUserName() {

        // First try without scrolling
        if (isElementVisible(userName)) {

            wait.until(
                    ExpectedConditions.elementToBeClickable(userName)
            ).click();

            System.out.println("User name clicked successfully");
            return;
        }

        // If not visible, scroll down
        for (int i = 0; i < 5; i++) {

            scrollDown();

            if (isElementVisible(userName)) {

                wait.until(
                        ExpectedConditions.elementToBeClickable(userName)
                ).click();

                System.out.println("User name clicked successfully");
                return;
            }
        }

        throw new RuntimeException(
                "User name was not found: abishek251295@gmail.com"
        );
    }


    // =========================================================
    // CLICK QR CODE
    // =========================================================

    public void clickQRCode() {

        wait.until(
                ExpectedConditions.elementToBeClickable(qrCode)
        ).click();

        System.out.println("QR Code clicked successfully");
    }


    // =========================================================
    // WAIT 2 SECONDS AND GO BACK
    // =========================================================

    public void waitAndGoBack() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        driver.navigate().back();

        System.out.println(
                "Waited 2 seconds and navigated back successfully"
        );
    }


    // =========================================================
    // SCROLL DOWN
    // =========================================================

    private void scrollDown() {

        driver.executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", 100,
                        "top", 300,
                        "width", 520,
                        "height", 1100,
                        "direction", "down",
                        "percent", 0.70
                )
        );
    }


    // =========================================================
    // CHECK ELEMENT VISIBILITY
    // =========================================================

    private boolean isElementVisible(By locator) {

        try {

            WebElement element =
                    driver.findElement(locator);

            return element.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }


    // =========================================================
    // COMPLETE QR CODE FLOW
    // =========================================================

    public void openQRCodeAndGoBack() {

        // Step 1
        clickAccount();

        // Step 2
        clickUserName();

        // Step 3
        clickQRCode();

        // Step 4
        // Wait 2 seconds and go back
        waitAndGoBack();
    }
}
