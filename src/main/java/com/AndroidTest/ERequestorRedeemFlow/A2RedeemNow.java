package com.AndroidTest.ERequestorRedeemFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class A2RedeemNow extends BaseClassMobile {

    // =========================================================
    // LOCATOR
    // =========================================================

    private final By REDEEM_EXCITING_REWARDS =
            AppiumBy.xpath(
                    "//android.widget.ImageView[" +
                            "contains(@content-desc,'Redeem Exciting Rewards') and " +
                            "contains(@content-desc,'Redeem Now')" +
                            "]"
            );

    // =========================================================
    // SETTINGS
    // =========================================================

    private static final int SEARCH_WAIT = 3;
    private static final int MAX_SCROLLS = 5;

    public A2RedeemNow(AndroidDriver driver) {
        this.driver = driver;
    }

    // =========================================================
    // CLICK REDEEM EXCITING REWARDS
    // =========================================================

    public void clickRedeemExcitingRewards() {

        System.out.println(
                "STEP 1 : Searching for Redeem Exciting Rewards..."
        );
        scrollDown();
        // =====================================================
        // SEARCH + SCROLL
        // =====================================================

        for (int attempt = 1; attempt <= MAX_SCROLLS; attempt++) {

            System.out.println(
                    "Searching Redeem card - Attempt " + attempt
            );

            try {

                WebDriverWait shortWait =
                        new WebDriverWait(
                                driver,
                                Duration.ofSeconds(SEARCH_WAIT)
                        );

                shortWait.pollingEvery(
                        Duration.ofMillis(250)
                );

                WebElement redeemCard =
                        shortWait.until(
                                ExpectedConditions
                                        .elementToBeClickable(
                                                REDEEM_EXCITING_REWARDS
                                        )
                        );

                System.out.println(
                        "STEP 2 : Redeem Exciting Rewards found."
                );

                redeemCard.click();

                System.out.println(
                        "STEP 3 : Redeem Exciting Rewards clicked."
                );

                ExtentTestListener.logStep(
                        "Redeem Exciting Rewards clicked"
                );

                return;

            } catch (Exception e) {

                System.out.println(
                        "Redeem card not found. Scrolling..."
                );

                if (attempt < MAX_SCROLLS) {

                    driver.executeScript(
                            "mobile: swipeGesture",
                            Map.of(
                                    "left", 100,
                                    "top", 700,
                                    "width", 520,
                                    "height", 700,
                                    "direction", "up",
                                    "percent", 0.70
                            )
                    );
                }
            }
        }

        // =====================================================
        // FINAL FAILURE
        // =====================================================

        throw new RuntimeException(
                "Redeem Exciting Rewards card was not found after "
                        + MAX_SCROLLS
                        + " scroll attempts."
        );
    }
}