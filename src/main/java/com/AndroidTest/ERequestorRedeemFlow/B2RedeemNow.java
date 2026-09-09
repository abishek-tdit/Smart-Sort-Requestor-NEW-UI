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

public class B2RedeemNow {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By REDEEM_EXCITING_REWARDS =
            AppiumBy.xpath(
                    "//android.widget.ImageView[" +
                            "contains(@content-desc,'Redeem Exciting Rewards')" +
                            "]"
            );

    private final By REDEEM_NOW =
            AppiumBy.accessibilityId("Redeem Now");

    private final By REWARD_POINTS_TITLE =
            AppiumBy.accessibilityId("Reward Points");

    public B2RedeemNow(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void redeemNow() {

        System.out.println("STEP 1 : Searching for Redeem Exciting Rewards...");

        scrollToRedeemRewards();

        WebElement rewards = wait.until(
                ExpectedConditions.elementToBeClickable(
                        REDEEM_EXCITING_REWARDS
                )
        );

        rewards.click();

        System.out.println("STEP 2 : Redeem Exciting Rewards clicked.");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        REWARD_POINTS_TITLE
                )
        );

        System.out.println("STEP 3 : Reward Points page displayed.");

        WebElement redeemNow = wait.until(
                ExpectedConditions.elementToBeClickable(
                        REDEEM_NOW
                )
        );

        redeemNow.click();

        System.out.println("STEP 4 : Redeem Now clicked successfully.");
    }

    private void scrollToRedeemRewards() {

        for (int i = 0; i < 6; i++) {

            if (isVisible(REDEEM_EXCITING_REWARDS)) {
                System.out.println(
                        "Redeem Exciting Rewards found."
                );
                return;
            }

            System.out.println(
                    "Scrolling... Attempt " + (i + 1)
            );

            swipeUp();
        }

        if (!isVisible(REDEEM_EXCITING_REWARDS)) {
            throw new RuntimeException(
                    "Redeem Exciting Rewards was not found after scrolling."
            );
        }
    }

    private void swipeUp() {

        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        360,
                        1150
                )
        );

        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(500),
                        PointerInput.Origin.viewport(),
                        360,
                        650
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

    private boolean isVisible(By locator) {

        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}