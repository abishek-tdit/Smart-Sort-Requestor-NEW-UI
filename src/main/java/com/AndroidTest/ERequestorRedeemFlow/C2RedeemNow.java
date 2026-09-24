package com.AndroidTest.ERequestorRedeemFlow;

import Base.BaseClassMobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class C2RedeemNow extends BaseClassMobile {

    private final By REDEEM_EXCITING_REWARDS =
            AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'Redeem Exciting Rewards') " +
                    "and contains(@content-desc,'Turn your recycling into real benefits!') " +
                    "and contains(@content-desc,'Points')]");

    public C2RedeemNow(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickRedeemExcitingRewards() {

        System.out.println("STEP 1 : Searching for Redeem Exciting Rewards...");

        scrollDown();

        WebElement redeemCard = new WebDriverWait(
                driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        REDEEM_EXCITING_REWARDS));

        System.out.println("STEP 2 : Redeem Exciting Rewards found.");

        redeemCard.click();

        System.out.println("STEP 3 : Redeem Exciting Rewards clicked.");
    }
}