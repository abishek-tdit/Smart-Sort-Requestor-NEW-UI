package com.AndroidTest.KSettingFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BNotificationPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // LOCATORS
    // =========================================================
    private final By notificationsTab =
            AppiumBy.accessibilityId("Notifications");
    private final By notificationsTitle =
            AppiumBy.accessibilityId("Notifications");
    private final By notificationCards = AppiumBy.xpath(
            "//android.view.View[@content-desc and @clickable='true']");

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public BNotificationPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                        driver,
                        Duration.ofSeconds(15));
    }

    // =========================================================
    // OPEN NOTIFICATIONS
    // =========================================================

    public void openAndValidateNotifications() {

        ExtentTestListener.logStep(
                "========== NOTIFICATION FLOW STARTED ==========");

        /*
         * Click Notifications.
         */
        WebElement notificationTab = wait.until
                (ExpectedConditions.elementToBeClickable(notificationsTab));

        notificationTab.click();

        ExtentTestListener.logStep(
                "Notifications tab clicked successfully."
        );

        /*
         * Wait for Notifications page title.
         */
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        notificationsTitle
                )
        );

        ExtentTestListener.logStep(
                "Notifications screen displayed."
        );

        /*
         * Wait until at least one notification card is
         * available.
         *
         * We no longer wait for a scrollable container.
         */
        wait.until(
                driver ->
                        !driver.findElements(
                                notificationCards
                        ).isEmpty()
        );

        ExtentTestListener.logStep(
                "Notification cards loaded successfully."
        );

        /*
         * Validate first 10 notifications.
         */
        validateFirst10Notifications();

        /*
         * Return to previous page.
         */
        driver.navigate().back();

        /*
         * Wait until Notifications title disappears.
         */
        try {

            new WebDriverWait(driver,
                    Duration.ofSeconds(10)).until
                    (ExpectedConditions.invisibilityOfElementLocated
                            (notificationsTitle));

        } catch (Exception ignored) {
            /*
             * Some Flutter pages can keep the previous
             * accessibility node temporarily.
             */
        }

        ExtentTestListener.logStep(
                "Returned from Notifications screen."
        );

        ExtentTestListener.logStep(
                "========== NOTIFICATION FLOW COMPLETED =========="
        );
    }

    // =========================================================
    // VALIDATE FIRST 10 NOTIFICATIONS
    // =========================================================

    private void validateFirst10Notifications() {

        final int REQUIRED_NOTIFICATIONS = 10;

        int notificationCount = 0;

        /*
         * Stores the notification text that has already
         * been processed.
         *
         * This prevents the same card from being counted
         * again after scrolling.
         */
        Set<String> processedNotifications =
                new HashSet<>();

        /*
         * Stores the previous visible notification list.
         */
        List<String> previousScreen =
                new ArrayList<>();

        int noChangeCount = 0;

        /*
         * Maximum number of swipe attempts.
         */
        final int MAX_SCROLLS = 15;

        for (
                int scroll = 0;
                scroll <= MAX_SCROLLS
                        && notificationCount < REQUIRED_NOTIFICATIONS;
                scroll++
        ) {

            ExtentTestListener.logStep(
                    "Reading notification screen..."
            );

            /*
             * Read currently visible notification cards.
             */
            List<String> currentScreen =
                    getVisibleNotifications();

            ExtentTestListener.logStep(
                    "Visible notification cards: "
                            + currentScreen.size()
            );

            /*
             * If no cards were found, wait for them.
             */
            if (currentScreen.isEmpty()) {

                try {

                    wait.until(
                            driver ->
                                    !driver.findElements(
                                            notificationCards
                                    ).isEmpty()
                    );

                    currentScreen =
                            getVisibleNotifications();

                } catch (Exception ignored) {
                    /*
                     * Continue to next attempt.
                     */
                }
            }

            /*
             * Process currently visible notifications.
             */
            for (String notification : currentScreen) {

                if (
                        notificationCount
                                >= REQUIRED_NOTIFICATIONS
                ) {
                    break;
                }

                /*
                 * Ignore empty values.
                 */
                if (
                        notification == null
                                || notification.trim().isEmpty()
                ) {
                    continue;
                }

                /*
                 * Ignore date separators.
                 */
                if (isDateSeparator(notification)) {
                    continue;
                }

                /*
                 * Count only a notification that has not
                 * already been processed.
                 */
                if (
                        processedNotifications.add(
                                notification
                        )
                ) {

                    notificationCount++;

                    System.out.println(
                            "Notification "
                                    + notificationCount
                                    + " : "
                                    + notification
                    );
                }
            }

            /*
             * Stop after finding 10 notifications.
             */
            if (
                    notificationCount
                            >= REQUIRED_NOTIFICATIONS
            ) {
                break;
            }

            /*
             * Check whether the current screen changed.
             */
            if (
                    currentScreen.equals(
                            previousScreen
                    )
            ) {

                noChangeCount++;

                System.out.println(
                        "Notification screen did not change. "
                                + "Attempt: "
                                + noChangeCount
                );

            } else {

                noChangeCount = 0;

                previousScreen =
                        new ArrayList<>(
                                currentScreen
                        );
            }

            /*
             * If the screen did not change several times,
             * stop scrolling.
             */
            if (noChangeCount >= 3) {

                System.out.println(
                        "Notification list is no longer moving."
                );

                break;
            }

            /*
             * Do not scroll after the last possible attempt.
             */
            if (scroll >= MAX_SCROLLS) {
                break;
            }

            /*
             * Perform swipe.
             */
            System.out.println(
                    "Scrolling notification list..."
            );

            swipeNotificationList();

            /*
             * Wait for notification content to change.
             *
             * No Thread.sleep() is used.
             */
            waitForNotificationListUpdate(
                    previousScreen
            );
        }

        /*
         * Final validation.
         */
        if (
                notificationCount
                        < REQUIRED_NOTIFICATIONS
        ) {

            throw new RuntimeException(
                    "Only "
                            + notificationCount
                            + " notification cards found. "
                            + "Expected "
                            + REQUIRED_NOTIFICATIONS
                            + "."
            );
        }

        System.out.println(
                "Notification validation completed. "
                        + "Total validated: "
                        + notificationCount
        );
    }

    // =========================================================
    // GET VISIBLE NOTIFICATIONS
    // =========================================================

    private List<String> getVisibleNotifications() {

        List<String> notifications =
                new ArrayList<>();

        List<WebElement> cards =
                driver.findElements(
                        notificationCards
                );

        for (WebElement card : cards) {

            try {

                /*
                 * Get content-desc.
                 */
                String contentDescription =
                        card.getAttribute(
                                "content-desc"
                        );

                if (
                        contentDescription == null
                                || contentDescription
                                .trim()
                                .isEmpty()
                ) {
                    continue;
                }

                /*
                 * Clean notification text.
                 */
                String clean =
                        cleanText(
                                contentDescription
                        );

                if (clean.isEmpty()) {
                    continue;
                }

                /*
                 * Ignore date separators.
                 */
                if (isDateSeparator(clean)) {
                    continue;
                }

                /*
                 * Add notification.
                 */
                notifications.add(clean);

            } catch (Exception ignored) {
                /*
                 * Ignore stale/invalid element and continue.
                 */
            }
        }

        return notifications;
    }

    // =========================================================
    // SWIPE NOTIFICATION LIST
    // =========================================================

    private void swipeNotificationList() {

        /*
         * Real W3C touch action.
         *
         * Device:
         * 720 x 1604
         *
         * Swipe starts near the bottom and moves upward.
         */
        PointerInput finger =
                new PointerInput(
                        PointerInput.Kind.TOUCH,
                        "notificationFinger"
                );

        Sequence swipe =
                new Sequence(
                        finger,
                        1
                );

        /*
         * Move finger to starting position.
         */
        swipe.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        360,
                        1350
                )
        );

        /*
         * Finger down.
         */
        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        /*
         * Move upward.
         */
        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(800),
                        PointerInput.Origin.viewport(),
                        360,
                        550
                )
        );

        /*
         * Finger up.
         */
        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        /*
         * Perform swipe.
         */
        driver.perform(
                List.of(swipe)
        );
    }

    // =========================================================
    // WAIT FOR LIST UPDATE
    // =========================================================

    private void waitForNotificationListUpdate(
            List<String> oldScreen
    ) {

        try {

            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(5)
            ).until(
                    driver -> {

                        List<String> currentScreen =
                                getVisibleNotifications();

                        /*
                         * Return true when the visible
                         * notification content changes.
                         */
                        return !currentScreen.equals(
                                oldScreen
                        );
                    }
            );

        } catch (Exception ignored) {

            /*
             * Do not fail immediately if the list does
             * not expose an easily detectable update.
             *
             * The next loop will read the screen again.
             */
        }
    }

    // =========================================================
    // DATE SEPARATOR CHECK
    // =========================================================

    private boolean isDateSeparator(
            String text
    ) {

        if (
                text == null
                        || text.trim().isEmpty()
        ) {
            return false;
        }

        return text.matches(
                "(January|February|March|April|May|June|July|August|"
                        + "September|October|November|December)"
                        + "\\s+\\d{1,2},\\s+\\d{4}"
        );
    }

    // =========================================================
    // CLEAN TEXT
    // =========================================================

    private String cleanText(
            String text
    ) {

        if (text == null) {
            return "";
        }

        return text
                /*
                 * Remove invisible Unicode formatting
                 * characters.
                 */
                .replaceAll(
                        "\\p{Cf}",
                        ""
                )

                /*
                 * Convert line breaks to spaces.
                 */
                .replace(
                        "\n",
                        " "
                )

                /*
                 * Normalize multiple spaces.
                 */
                .replaceAll(
                        "\\s+",
                        " "
                )

                /*
                 * Remove leading/trailing spaces.
                 */
                .trim();
    }
}
