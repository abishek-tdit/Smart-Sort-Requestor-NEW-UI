package com.AndroidTest.JAccountProfileSettingFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class G2DeleteAddress {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================
    // Locators
    // =========================

    // Three dots / Show Menu
    private static final By SHOW_MENU =
            AppiumBy.accessibilityId("Show menu");

    // Delete Address option
    private static final By DELETE_ADDRESS =
            AppiumBy.accessibilityId("Delete Address");

    // OK button - first confirmation popup
    private static final By OK_BUTTON =
            AppiumBy.accessibilityId("OK");

    // =========================
    // Constructor
    // =========================

    public G2DeleteAddress(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // =========================
    // Main Delete Address Flow
    // =========================

    public void deleteAddress() {

        ExtentTestListener.logStep("Starting Delete Address flow");

        // ---------------------------------
        // Step 1: Click three dots
        // ---------------------------------

        ExtentTestListener.logStep("Waiting for Show menu button");

        wait.until(ExpectedConditions.elementToBeClickable(SHOW_MENU));

        ExtentTestListener.logStep("Clicking three dots / Show menu");

        driver.findElement(SHOW_MENU).click();

        // ---------------------------------
        // Step 2: Click Delete Address
        // ---------------------------------

        ExtentTestListener.logStep("Waiting for Delete Address option");

        wait.until(ExpectedConditions.elementToBeClickable(DELETE_ADDRESS));

        ExtentTestListener.logStep("Clicking Delete Address");

        driver.findElement(DELETE_ADDRESS).click();

        // ---------------------------------
        // Step 3: Click first OK
        // ---------------------------------

        ExtentTestListener.logStep("Waiting for first OK confirmation popup");

        wait.until(ExpectedConditions.elementToBeClickable(OK_BUTTON));

        ExtentTestListener.logStep("Clicking first OK");

        driver.findElement(OK_BUTTON).click();

        // ---------------------------------
        // Step 4: Click second OK
        // ---------------------------------

        ExtentTestListener.logStep("Waiting for second OK confirmation popup");

        wait.until(ExpectedConditions.elementToBeClickable(OK_BUTTON));

        ExtentTestListener.logStep("Clicking second OK");

        driver.findElement(OK_BUTTON).click();

        // ---------------------------------
        // Step 5: Final Back
        // ---------------------------------

        ExtentTestListener.logStep("Returning to previous page");

        wait.until(driver -> driver != null);

        ExtentTestListener.logStep("Delete Address flow completed successfully");
    }
}