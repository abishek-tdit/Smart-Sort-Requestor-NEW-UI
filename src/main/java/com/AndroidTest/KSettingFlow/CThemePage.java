package com.AndroidTest.KSettingFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CThemePage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    private By themeMenu =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().descriptionContains(\"Theme\")");

    private By darkButton =
            AppiumBy.accessibilityId("Dark");

    private By lightButton =
            AppiumBy.accessibilityId("Light");

    public CThemePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void selectDarkTheme() throws InterruptedException {

        WebElement theme = wait.until(
                ExpectedConditions.visibilityOfElementLocated(themeMenu));

        theme.click();
        ExtentTestListener.logStep("Theme menu clicked");

        WebElement dark = wait.until(
                ExpectedConditions.elementToBeClickable(darkButton));

        dark.click();
        ExtentTestListener.logStep("Dark selected");

        Thread.sleep(2000);
    }

    public void selectLightTheme() throws InterruptedException {

        WebElement theme = wait.until(
                ExpectedConditions.visibilityOfElementLocated(themeMenu));

        theme.click();
        ExtentTestListener.logStep("Theme menu clicked");

        WebElement light = wait.until(
                ExpectedConditions.elementToBeClickable(lightButton));

        light.click();
        ExtentTestListener.logStep("Light selected");

        Thread.sleep(2000);
    }
}