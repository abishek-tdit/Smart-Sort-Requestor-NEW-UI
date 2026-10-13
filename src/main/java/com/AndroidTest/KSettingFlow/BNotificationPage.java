package com.AndroidTest.KSettingFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BNotificationPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =====================================================
    // LOCATORS
    // =====================================================

    private final By NOTIFICATIONS_BUTTON =
            AppiumBy.accessibilityId("Notifications");

    // Match notification cards only.
    // The content description starts with a notification title.
    // This avoids matching Home, Orders, Rewards and Account.
    private final By NOTIFICATION_ITEMS =
            AppiumBy.xpath(
                    "//android.widget.ImageView[@content-desc and " +
                            "(contains(@content-desc,'Points') or " +
                            "contains(@content-desc,'Redeem') or " +
                            "contains(@content-desc,'Order') or " +
                            "contains(@content-desc,'Reward') or " +
                            "contains(@content-desc,'Collection') or " +
                            "contains(@content-desc,'Request') or " +
                            "contains(@content-desc,'Conversion'))]"
            );

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public BNotificationPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    // =====================================================
    // STEP 1 - OPEN NOTIFICATIONS
    // =====================================================

    public void clickNotifications() {

        System.out.println(
                "STEP 1: Waiting for Notifications button"
        );

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        NOTIFICATIONS_BUTTON
                )
        );

        button.click();

        ExtentTestListener.logStep(
                "Notifications button clicked successfully"
        );

        System.out.println(
                "Notifications button clicked successfully"
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        NOTIFICATION_ITEMS
                )
        );

        ExtentTestListener.logStep(
                "Notifications screen displayed successfully"
        );

        System.out.println(
                "Notifications screen displayed successfully"
        );
    }

    // =====================================================
    // STEP 2 - VALIDATE FIRST 10 NOTIFICATIONS
    // =====================================================

    public void validateFirst10Notifications() {

        System.out.println(
                "========== NOTIFICATION VALIDATION STARTED =========="
        );

        Set<String> validatedNotifications =
                new LinkedHashSet<>();

        int maxScrollAttempts = 15;
        int scrollAttempts = 0;

        while (validatedNotifications.size() < 10
                && scrollAttempts < maxScrollAttempts) {

            // Find currently available notification cards.
            List<WebElement> notifications =
                    driver.findElements(NOTIFICATION_ITEMS);

            int previousCount =
                    validatedNotifications.size();

            for (WebElement notification : notifications) {

                try {

                    String text =
                            notification.getAttribute("content-desc");

                    if (text == null || text.trim().isEmpty()) {
                        continue;
                    }

                    text = text.trim();

                    // Add each unique notification in display order.
                    if (validatedNotifications.add(text)) {

                        int number =
                                validatedNotifications.size();

                        System.out.println(
                                "Notification " + number + ": " + text
                        );

                        ExtentTestListener.logStep(
                                "Notification " + number +
                                        " validated: " + text
                        );
                    }

                    if (validatedNotifications.size() == 10) {
                        break;
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Unable to read notification: "
                                    + e.getMessage()
                    );
                }
            }

            if (validatedNotifications.size() >= 10) {
                break;
            }

            // Stop if the scroll limit has been reached.
            if (scrollAttempts >= maxScrollAttempts) {
                break;
            }

            // =================================================
            // SCROLL DOWN
            // =================================================

            scrollAttempts++;

            System.out.println(
                    "Scrolling notification screen. Attempt: "
                            + scrollAttempts
            );

            try {

                // Swipe on the screen rather than trying to scroll
                // a container that might not be correctly identified.
                driver.executeScript(
                        "mobile: swipeGesture",
                        java.util.Map.of(
                                "left", 30,
                                "top", 250,
                                "width", driver.manage()
                                        .window()
                                        .getSize()
                                        .getWidth() - 60,
                                "height", driver.manage()
                                        .window()
                                        .getSize()
                                        .getHeight() - 500,
                                "direction", "up",
                                "percent", 0.75
                        )
                );

                // Wait briefly for the screen to update.
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    throw new RuntimeException(
                            "Interrupted while scrolling notifications",
                            e
                    );
                }

            } catch (Exception e) {

                throw new RuntimeException(
                        "Failed to scroll notification screen",
                        e
                );
            }

            // Log whether scrolling exposed new notification messages.
            int currentCount = validatedNotifications.size();

            if (currentCount == previousCount) {

                System.out.println(
                        "No new notification found after scroll attempt "
                                + scrollAttempts
                );

            } else {

                System.out.println(
                        "New notifications found. Total collected: "
                                + currentCount
                );
            }
        }

        // =====================================================
        // STEP 3 - FINAL VALIDATION
        // =====================================================

        List<String> results =
                new ArrayList<>(validatedNotifications);

        System.out.println(
                "Total unique notification messages found: "
                        + results.size()
        );

        if (results.size() < 10) {

            String error =
                    "Expected 10 notifications but found "
                            + results.size()
                            + ". Check the notification XPath and "
                            + "whether more notifications exist.";

            ExtentTestListener.logStep(error);

            throw new AssertionError(error);
        }

        for (int i = 0; i < 10; i++) {

            if (results.get(i).trim().isEmpty()) {

                throw new AssertionError(
                        "Notification " + (i + 1)
                                + " is empty"
                );
            }

            System.out.println(
                    "PASS: Notification " + (i + 1)
            );
        }

        System.out.println(
                "========== NOTIFICATION VALIDATION PASSED =========="
        );

        ExtentTestListener.logStep(
                "Successfully validated the first 10 notifications"
        );
    }

    // =====================================================
    // STEP 3 - COMPLETE FLOW
    // =====================================================

    public void validateNotificationsFlow() {

        clickNotifications();

        validateFirst10Notifications();
    }
}