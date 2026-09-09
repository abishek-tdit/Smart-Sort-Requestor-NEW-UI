package com.AndroidTest.KSettingFlow;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BNotificationPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By notificationsTab =
            AppiumBy.accessibilityId("Notifications");

    private final By notificationsTitle =
            AppiumBy.accessibilityId("Notifications");

    /*
     * Notification list.
     *
     * From Appium XML:
     * bounds = [0,392][720,1604]
     * scrollable = true
     */
    private final By notificationContainer =
            AppiumBy.xpath("//android.view.View[@scrollable='true']");

    /*
     * Only direct children of notification list.
     */
    private final By notificationCards =
            AppiumBy.xpath(
                    "//android.view.View[@scrollable='true']"
                            + "/android.view.View[@content-desc and @clickable='true']"
            );

    public BNotificationPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openAndValidateNotifications() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        notificationsTab
                )
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        notificationsTitle
                )
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        notificationContainer
                )
        );

        System.out.println(
                "Notifications tab clicked successfully."
        );

        System.out.println(
                "Notifications screen displayed."
        );

        validateFirst10Notifications();

        driver.navigate().back();

        System.out.println(
                "Returned from Notifications screen."
        );
    }

    /**
     * Validate first 10 notification cards.
     */
    private void validateFirst10Notifications() {

        int notificationCount = 0;

        /*
         * Track how many times each notification text
         * has already been processed.
         *
         * This allows duplicate notifications such as:
         *
         * Pickup Today
         * Pickup Today
         *
         * to be counted separately.
         */
        Map<String, Integer> occurrences =
                new HashMap<>();

        /*
         * Stores a notification identity:
         *
         * notification text + occurrence number
         */
        Set<String> processed =
                new HashSet<>();

        /*
         * Prevent endless scrolling if the screen really
         * does not move.
         */
        List<String> previousScreen =
                new ArrayList<>();

        int noChangeCount = 0;

        for (int scroll = 0;
             scroll < 15 && notificationCount < 10;
             scroll++) {

            List<WebElement> cards =
                    driver.findElements(notificationCards);

            List<String> currentScreen =
                    new ArrayList<>();

            for (WebElement card : cards) {

                String text =
                        card.getAttribute("content-desc");

                if (text == null ||
                        text.trim().isEmpty()) {
                    continue;
                }

                String clean =
                        cleanText(text);

                /*
                 * Ignore date headers.
                 */
                if (isDateSeparator(clean)) {
                    continue;
                }

                currentScreen.add(clean);
            }

            /*
             * Process visible notifications.
             */
            for (String notification : currentScreen) {

                if (notificationCount >= 10) {
                    break;
                }

                int occurrence =
                        occurrences.getOrDefault(
                                notification, 0
                        ) + 1;

                occurrences.put(
                        notification,
                        occurrence
                );

                String key =
                        notification + "#" + occurrence;

                /*
                 * Only add the notification if this exact
                 * occurrence has not already been processed.
                 */
                if (processed.add(key)) {

                    notificationCount++;

                    System.out.println(
                            "Notification "
                                    + notificationCount
                                    + " : "
                                    + notification
                    );
                }
            }

            if (notificationCount >= 10) {
                break;
            }

            /*
             * Check whether the visible screen actually
             * changed.
             */
            if (currentScreen.equals(previousScreen)) {

                noChangeCount++;

            } else {

                noChangeCount = 0;
                previousScreen =
                        new ArrayList<>(currentScreen);
            }

            /*
             * Swipe inside notification list.
             */
            System.out.println(
                    "Scrolling notification list..."
            );

            swipeNotificationList();

            /*
             * Give Android/Appium time to update the UI
             * without using Thread.sleep().
             */
            waitForNotificationListUpdate(
                    previousScreen
            );

            /*
             * If several attempts produce exactly the same
             * screen, scrolling is genuinely not working.
             */
            if (noChangeCount >= 3) {

                System.out.println(
                        "Notification list did not move."
                );

                break;
            }
        }

        if (notificationCount < 10) {

            throw new RuntimeException(
                    "Only "
                            + notificationCount
                            + " notification cards found. "
                            + "Expected 10."
            );
        }

        System.out.println(
                "Notification validation completed. "
                        + "Total validated: "
                        + notificationCount
        );
    }

    /**
     * Perform a real W3C touch swipe.
     *
     * Swipe completely inside:
     *
     * X = 0 to 720
     * Y = 392 to 1604
     */
    private void swipeNotificationList() {

        PointerInput finger =
                new PointerInput(
                        PointerInput.Kind.TOUCH,
                        "finger"
                );

        Sequence swipe =
                new Sequence(finger, 1);

        /*
         * Start near bottom of notification list.
         */
        swipe.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        360,
                        1350
                )
        );

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
                        Duration.ofMillis(700),
                        PointerInput.Origin.viewport(),
                        360,
                        550
                )
        );

        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        driver.perform(
                List.of(swipe)
        );
    }

    /**
     * Wait until the notification list changes.
     */
    private void waitForNotificationListUpdate(
            List<String> oldScreen) {

        try {

            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(3)
            ).until(driver -> {

                List<WebElement> cards =
                        driver.findElements(
                                notificationCards
                        );

                List<String> current =
                        new ArrayList<>();

                for (WebElement card : cards) {

                    String text =
                            card.getAttribute(
                                    "content-desc"
                            );

                    if (text == null ||
                            text.trim().isEmpty()) {
                        continue;
                    }

                    String clean =
                            cleanText(text);

                    if (isDateSeparator(clean)) {
                        continue;
                    }

                    current.add(clean);
                }

                return !current.equals(oldScreen);
            });

        } catch (Exception ignored) {
            /*
             * Do not fail immediately.
             * The next loop iteration will check the screen.
             */
        }
    }

    /**
     * Ignore date separator rows.
     */
    private boolean isDateSeparator(
            String text) {

        return text.matches(
                "(January|February|March|April|May|June|July|August|"
                        + "September|October|November|December)"
                        + "\\s+\\d{1,2},\\s+\\d{4}"
        );
    }

    /**
     * Clean content-desc.
     */
    private String cleanText(
            String text) {

        return text
                .replaceAll("\\p{Cf}", "")
                .replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }
}
