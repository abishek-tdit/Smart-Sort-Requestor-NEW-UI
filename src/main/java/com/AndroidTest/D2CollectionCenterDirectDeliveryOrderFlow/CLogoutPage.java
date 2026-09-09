package com.AndroidTest.D2CollectionCenterDirectDeliveryOrderFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.StepTimer;

import java.time.Duration;
import java.util.Map;

public class CLogoutPage extends BaseClassMobile {

    AndroidDriver driver;
    WebDriverWait wait;

    public CLogoutPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void logout() throws Exception {

        // =========================================================
        // Scroll Down
        // =========================================================
        StepTimer.startStep();

        scrollDown();
        scrollDown();

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Scrolled Down"
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // Click Account
        // =========================================================
        StepTimer.startStep();

        WebElement account = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Account")));

        account.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Clicked Account"
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // Scroll Until Logout Is Visible
        // =========================================================
        StepTimer.startStep();

        WebElement logoutBtn = driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))"
                                + ".scrollIntoView(new UiSelector().description(\"logout\"))"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Scrolled to logout"
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // Wait And Click Logout
        // =========================================================
        StepTimer.startStep();

        Thread.sleep(3000);

        wait.until(
                ExpectedConditions.elementToBeClickable(logoutBtn));

        driver.executeScript(
                "mobile: clickGesture",
                Map.of(
                        "elementId",
                        ((RemoteWebElement) logoutBtn).getId()));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Clicked logout"
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");
    }
}