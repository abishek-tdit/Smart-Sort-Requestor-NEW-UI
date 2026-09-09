package com.AndroidTest.ERequestorRedeemFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class A2RedeemNow {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Scrollable page
    private final By SCROLL_VIEW =
            AppiumBy.className("android.widget.ScrollView");

    // Redeem Exciting Rewards card
    private final By REDEEM_EXCITING_REWARDS =
            AppiumBy.xpath(
                    "//android.widget.ImageView[" +
                            "contains(@content-desc,'Redeem Exciting Rewards')" +
                            "]"
            );

    // Redeem Now button
    private final By REDEEM_NOW =
            AppiumBy.accessibilityId("Redeem Now");

    // Reward Points page
    private final By REWARD_POINTS_TITLE =
            AppiumBy.accessibilityId("Reward Points");


    public A2RedeemNow(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    /**
     * Flow:
     * 1. Scroll down until Redeem Exciting Rewards is visible
     * 2. Click Redeem Exciting Rewards
     * 3. Wait for Reward Points page
     * 4. Click Redeem Now
     */
    public void redeemNow() {

        System.out.println("STEP 1 : Searching for Redeem Exciting Rewards...");

        scrollToRedeemExcitingRewards();

        System.out.println("STEP 2 : Redeem Exciting Rewards found.");

        WebElement redeemRewards =
                wait.until(ExpectedConditions.elementToBeClickable(
                        REDEEM_EXCITING_REWARDS
                ));

        redeemRewards.click();

        System.out.println("STEP 3 : Redeem Exciting Rewards clicked.");

        // Wait until Reward Points page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                REWARD_POINTS_TITLE
        ));

        System.out.println("STEP 4 : Reward Points page displayed.");

        // Wait for Redeem Now
        WebElement redeemNow =
                wait.until(ExpectedConditions.elementToBeClickable(
                        REDEEM_NOW
                ));

        redeemNow.click();

        System.out.println("STEP 5 : Redeem Now clicked.");
    }


    /**
     * Scrolls only inside the actual ScrollView.
     * This avoids swiping near the Android navigation/home area.
     */
    private void scrollToRedeemExcitingRewards() {

        // First check whether it is already visible
        if (isVisible(REDEEM_EXCITING_REWARDS)) {
            return;
        }

        for (int i = 0; i < 6; i++) {

            System.out.println(
                    "Scrolling down... attempt " + (i + 1)
            );

            swipeInsideScrollView();

            // Check after every swipe
            if (isVisible(REDEEM_EXCITING_REWARDS)) {
                return;
            }
        }

        throw new RuntimeException(
                "Redeem Exciting Rewards was not found after scrolling."
        );
    }


    /**
     * Performs an upward swipe inside the ScrollView.
     *
     * Screen size: 720 x 1604
     * ScrollView: starts around Y=64
     * Bottom navigation starts around Y=1392
     *
     * Therefore swipe is kept safely inside:
     * X = 360
     * Y = 1200 -> 750
     */
    private void swipeInsideScrollView() {

        PointerInput finger =
                new PointerInput(
                        PointerInput.Kind.TOUCH,
                        "finger"
                );

        Sequence swipe =
                new Sequence(finger, 1);

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        360,
                        1200
                )
        );

        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(600),
                        PointerInput.Origin.viewport(),
                        360,
                        700
                )
        );

        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        driver.perform(
                Collections.singletonList(swipe)
        );
    }


    /**
     * Checks whether an element exists and is currently displayed.
     */
    private boolean isVisible(By locator) {

        try {
            return !driver.findElements(locator).isEmpty()
                    && driver.findElement(locator).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}
