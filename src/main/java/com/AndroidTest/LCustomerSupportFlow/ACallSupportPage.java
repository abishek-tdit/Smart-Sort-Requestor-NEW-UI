package com.AndroidTest.LCustomerSupportFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class ACallSupportPage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By SUPPORT_CENTER = AppiumBy.xpath("//*[contains(@content-desc,'Customer Support')"
                    + " and contains(@content-desc,'Support Center')]");

    private final By CALL_NOW = AppiumBy.xpath("//*[contains(@content-desc,'Call Now')"
                    + " or contains(@text,'Call Now')]");

    private final By CALL_BUTTON = AppiumBy.xpath("//*[contains(@content-desc,'Call')"
                    + " or @text='Call']");

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ACallSupportPage() {

        this.driver = BaseClassMobile.driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================================================
    // CLICK SUPPORT CENTER
    // =========================================================

    public void clickSupportCenter() {

        System.out.println("========== SUPPORT CENTER FLOW STARTED ==========");

        int maxScrolls = 6;
        int scrollCount = 0;

        while (
                driver.findElements(SUPPORT_CENTER).isEmpty()
                        && scrollCount < maxScrolls)
        {

            driver.executeScript("mobile: scrollGesture",
                    Map.of("left", 50,
                            "top", 300,
                            "width", 620,
                            "height", 1000,
                            "direction", "down",
                            "percent", 0.8));

            scrollCount++;

            System.out.println("Scrolled down - attempt " + scrollCount);
        }

        WebElement supportCenter =
                wait.until(ExpectedConditions.elementToBeClickable(SUPPORT_CENTER));

        supportCenter.click();

        System.out.println("Support Center clicked successfully");

        ExtentTestListener.logStep("Support Center clicked successfully");
    }

    // =========================================================
    // CLICK CALL NOW
    // =========================================================

    public void clickCallNow() {

        System.out.println("Waiting for Call Now...");

        WebElement callNow =
                wait.until(ExpectedConditions.elementToBeClickable(CALL_NOW));

        System.out.println("Call Now element found: "
                + callNow.getAttribute("contentDescription"));
        callNow.click();

        System.out.println("Call Now clicked successfully");
        ExtentTestListener.logStep("Call Now clicked successfully");
    }

    // =========================================================
    // CONFIRM CALL POPUP
    // =========================================================

    public void confirmCall() {

        System.out.println("Waiting for Call confirmation...");

        WebElement callButton =
                wait.until(ExpectedConditions.elementToBeClickable(CALL_BUTTON));
        callButton.click();

        System.out.println("Call button clicked successfully");
        ExtentTestListener.logStep("Call button clicked successfully");
    }

    // =========================================================
    // RETURN TO APP
    // =========================================================

    public void returnToApp() {

        System.out.println("Going back from Call Log...");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        System.out.println("Android BACK - 1 pressed");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        System.out.println("Android BACK - 2 pressed");
        ExtentTestListener.logStep("Android BACK pressed 2 times");
    }

    // =========================================================
    // COMPLETE CALL SUPPORT FLOW
    // =========================================================

    public void callSupportCenter() {

        clickSupportCenter();
        clickCallNow();
        confirmCall();
        returnToApp();
    }
}
