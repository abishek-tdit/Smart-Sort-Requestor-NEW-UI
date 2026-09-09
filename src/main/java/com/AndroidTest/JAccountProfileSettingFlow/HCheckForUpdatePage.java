package com.AndroidTest.JAccountProfileSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class HCheckForUpdatePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Use only the stable part of content-desc
    private final By checkForUpdate = AppiumBy.xpath(
            "//*[contains(@content-desc,'Check for updated')]"
    );

    public HCheckForUpdatePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void checkForUpdate() {

        // Scroll until Check for updated is found
        boolean found = false;

        for (int i = 0; i < 6; i++) {

            if (!driver.findElements(checkForUpdate).isEmpty()) {
                found = true;
                System.out.println("Check for updated found.");
                break;
            }

            driver.executeScript("mobile: scrollGesture", Map.of(
                    "left", 50,
                    "top", 250,
                    "width", 620,
                    "height", 1100,
                    "direction", "down",
                    "percent", 0.7
            ));
        }

        if (!found) {
            throw new RuntimeException(
                    "Check for updated was not found after scrolling."
            );
        }

        // Wait until visible
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(checkForUpdate)
        );

        System.out.println("Check for updated is visible.");

        // Click using Appium element click
        try {
            element.click();
        } catch (Exception e) {

            System.out.println(
                    "Normal click failed. Trying coordinate click..."
            );

            int x = element.getRect().getX()
                    + element.getRect().getWidth() / 2;

            int y = element.getRect().getY()
                    + element.getRect().getHeight() / 2;

            driver.executeScript("mobile: clickGesture", Map.of(
                    "x", x,
                    "y", y
            ));
        }

        System.out.println("Check for updated clicked successfully.");

        // Wait for update result
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath(
                        "//*[contains(@content-desc,'No updates available')]"
                )
        ));

        System.out.println("Update status displayed: No updates available.");

        // Go back
//        driver.navigate().back();

        System.out.println("Returned to Account/Profile Settings.");
    }
}