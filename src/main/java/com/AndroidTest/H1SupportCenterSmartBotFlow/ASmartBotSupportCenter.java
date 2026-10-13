package com.AndroidTest.H1SupportCenterSmartBotFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.StepTimer;

import java.time.Duration;

public class ASmartBotSupportCenter extends BaseClassMobile {

    // =====================================================
    // LOCATORS
    // =====================================================

    // SmartBot floating icon
    private final By SMARTBOT_ICON =
            AppiumBy.xpath(
                    "//android.widget.FrameLayout[@resource-id='android:id/content']" +
                            "/android.widget.FrameLayout/android.widget.FrameLayout" +
                            "/android.view.View/android.view.View/android.view.View" +
                            "/android.view.View/android.view.View[3]/android.widget.ImageView"
            );

    // Chat Now button - accessibility ID
    private final By CHAT_NOW_ACCESSIBILITY_ID = AppiumBy.accessibilityId
            ("Chat with AI Get instant answers from SmartBot Chat Now");

    // Chat Now button - partial content description
    private final By CHAT_NOW_PARTIAL_DESCRIPTION = AppiumBy.xpath
            ("//*[contains(@content-desc,'Chat Now')]");

    // Chat Now button - text fallback
    private final By CHAT_NOW_TEXT = AppiumBy.xpath
            ("//*[@text='Chat Now' or @content-desc='Chat Now']");

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ASmartBotSupportCenter() {

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    // =====================================================
    // STEP 1 - CLICK SMARTBOT ICON
    // =====================================================

    public void clickSmartBotIcon() {

        StepTimer.startStep();

        System.out.println("STEP 1: Waiting for SmartBot icon");

        WebElement smartBotIcon = wait.until
                (ExpectedConditions.elementToBeClickable(SMARTBOT_ICON));

        smartBotIcon.click();

        System.out.println("SmartBot icon clicked successfully");

        ExtentTestListener.logStep("SmartBot icon clicked successfully");

        // Wait for the SmartBot panel to render.
        wait.until(driver ->
                !driver.findElements(CHAT_NOW_ACCESSIBILITY_ID).isEmpty()
                        || !driver.findElements(CHAT_NOW_PARTIAL_DESCRIPTION).isEmpty()
                        || !driver.findElements(CHAT_NOW_TEXT).isEmpty());
    }

    // =====================================================
    // STEP 2 - FIND CHAT NOW BUTTON
    // =====================================================

    private WebElement findChatNowButton() {

        By[] locators = {
                CHAT_NOW_ACCESSIBILITY_ID,
                CHAT_NOW_PARTIAL_DESCRIPTION,
                CHAT_NOW_TEXT };

        for (By locator : locators) {

            try {

                WebElement element = new WebDriverWait(
                        driver,
                        Duration.ofSeconds(5)
                ).until(d -> {

                    for (WebElement candidate :
                            d.findElements(locator)) {

                        try {

                            if (candidate.isDisplayed()
                                    && candidate.isEnabled()) {

                                return candidate;
                            }

                        } catch (StaleElementReferenceException ignored) {
                            // Retry if the UI changes during lookup.
                        }
                    }

                    return null;
                });

                if (element != null) {
                    System.out.println(
                            "Chat Now button found using: " + locator
                    );

                    return element;
                }

            } catch (TimeoutException ignored) {
                System.out.println(
                        "Locator did not match: " + locator
                );
            }
        }

        throw new NoSuchElementException(
                "Chat Now button was not found. " +
                        "Inspect the current page's Appium Inspector hierarchy " +
                        "and verify the button's text and content-desc."
        );
    }

    // =====================================================
    // STEP 3 - CLICK CHAT NOW
    // =====================================================

    public void clickChatNow() {

        StepTimer.startStep();

        System.out.println("STEP 2: Waiting for Chat Now button");

        try {

            WebElement chatNowButton = findChatNowButton();

            // Wait until the selected element is clickable.
            wait.until(
                    ExpectedConditions.elementToBeClickable(chatNowButton)
            );

            chatNowButton.click();

            System.out.println(
                    "Chat Now button clicked successfully"
            );

            ExtentTestListener.logStep(
                    "Chat Now button clicked successfully"
            );

        } catch (TimeoutException | NoSuchElementException e) {

            System.out.println(
                    "ERROR: Chat Now button could not be located or clicked."
            );

            System.out.println("Reason: " + e.getMessage());

            ExtentTestListener.logStep(
                    "Failed to locate or click Chat Now button: "
                            + e.getMessage()
            );

            // Capture the current screen for debugging.
            System.out.println(
                    "Chat Now button could not be located. Check the Appium Inspector hierarchy."
            );

            throw e;
        }
    }

    // =====================================================
    // COMPLETE FLOW
    // =====================================================

    public void openSmartBotChat() {

        clickSmartBotIcon();

        clickChatNow();

        System.out.println(
                "SmartBot Support Center flow completed successfully"
        );

        ExtentTestListener.logStep(
                "SmartBot Support Center flow completed successfully"
        );
    }
}