package com.AndroidTest.ERequestorRedeemFlow;

import Base.BaseClassMobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class B2RedeemNow extends BaseClassMobile {

    // =====================================================
    // LOCATOR - REDEEM EXCITING REWARDS
    // =====================================================

    private final By REDEEM_EXCITING_REWARDS =
            AppiumBy.xpath(
                    "//android.widget.ImageView[" +
                            "contains(@content-desc,'Redeem Exciting Rewards') and " +
                            "contains(@content-desc,'Redeem Now')" +
                            "]"
            );

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public B2RedeemNow(AndroidDriver driver) {
        this.driver = driver;
    }

    // =====================================================
    // CLICK REDEEM EXCITING REWARDS
    // =====================================================

    public void clickRedeemExcitingRewards() {

        System.out.println("STEP 1 : Searching for Redeem Exciting Rewards...");

        scrollDown();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement redeemCard = null;

        // =====================================================
        // SEARCH + SCROLL
        // =====================================================

        for (int attempt = 1; attempt <= 5; attempt++) {

            System.out.println("Searching Redeem card - Attempt " + attempt);

            try {

                redeemCard = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (REDEEM_EXCITING_REWARDS));

                if (redeemCard.isDisplayed()) {

                    System.out.println("Redeem Exciting Rewards found.");
                    break;
                }
            }
            catch (Exception e)
            {

                System.out.println("Redeem card not visible. Scrolling...");

                driver.executeScript("mobile: swipeGesture",
                        Map.of("left", 100,
                                "top", 700,
                                "width", 520,
                                "height", 700,
                                "direction", "up",
                                "percent", 0.70));
            }
        }

        // =====================================================
        // FINAL VALIDATION
        // =====================================================

        if (redeemCard == null) {

            throw new RuntimeException("Redeem Exciting Rewards card was not found after scrolling.");
        }

        // =====================================================
        // CLICK
        // =====================================================

        WebElement clickableRedeemCard = wait.until
                (ExpectedConditions.elementToBeClickable
                        (REDEEM_EXCITING_REWARDS));

        System.out.println("STEP 2 : Redeem Exciting Rewards found.");

        clickableRedeemCard.click();

        System.out.println("STEP 3 : Redeem Exciting Rewards clicked.");
    }
}
