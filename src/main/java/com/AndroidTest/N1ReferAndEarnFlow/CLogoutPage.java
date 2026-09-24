package com.AndroidTest.N1ReferAndEarnFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CLogoutPage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public CLogoutPage() {
        driver = getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void logout() {

        // =========================================================
        // Click Account
        // =========================================================
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Account")
        )).click();

        ExtentTestListener.logStep("Clicked Account");

        // =========================================================
        // Scroll Down to Logout
        // =========================================================
        scrollDown();

        // =========================================================
        // Click Logout
        // =========================================================

        WebElement logoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Logout")
                )
        );

        logoutButton.click();

        ExtentTestListener.logStep("Clicked Logout");


        // =========================================================
        // Click Yes on Confirmation Popup
        // =========================================================
        WebElement yesButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Yes")
                )
        );

        yesButton.click();

        ExtentTestListener.logStep("Clicked Yes");

        System.out.println("========== LOGOUT COMPLETED ==========");
    }
}
