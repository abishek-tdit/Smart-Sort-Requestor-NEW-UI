package com.AndroidTest.N1ReferAndEarnFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.D1ReferEarnReferralCode;
import utilities.StepTimer;

import java.time.Duration;

public class BReferAndEarnPage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public BReferAndEarnPage() {
        this.driver = BaseClassMobile.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By referAFriend = AppiumBy.xpath(
            "//android.widget.ImageView[contains(@content-desc,'Refer a Friend & Earn')]"
    );

    // Referral code itself is the clickable View.
    // Example content-desc: AAK996
    private final By referralCode = AppiumBy.androidUIAutomator(
            "new UiSelector()" +
                    ".className(\"android.view.View\")" +
                    ".clickable(true)" +
                    ".enabled(true)" +
                    ".descriptionMatches(\"[A-Z]{3}[0-9]{3}\")"
    );

    // =========================================================
    // CLICK REFER A FRIEND & SAVE REFERRAL CODE
    // =========================================================
    public void clickReferAFriendAndSaveCode() {

        StepTimer.startStep();

        // -----------------------------------------------------
        // Scroll to Refer a Friend
        // -----------------------------------------------------
        scrollDown();

        // -----------------------------------------------------
        // Click Refer a Friend & Earn
        // -----------------------------------------------------
        wait.until(
                ExpectedConditions.elementToBeClickable(referAFriend)
        ).click();

        ExtentTestListener.logStep(
                "Clicked Refer a Friend & Earn"
        );

        // -----------------------------------------------------
        // Wait for Referral Code
        // -----------------------------------------------------
        WebElement codeElement = wait.until(
                ExpectedConditions.elementToBeClickable(referralCode)
        );

        // -----------------------------------------------------
        // Read Referral Code from content-desc
        // Example: AAK996
        // -----------------------------------------------------
        String displayedCode =
                codeElement.getAttribute("contentDescription");

        if (displayedCode == null ||
                !displayedCode.trim().matches("[A-Z]{3}[0-9]{3}")) {

            throw new RuntimeException(
                    "Invalid referral code found: " + displayedCode
            );
        }

        displayedCode = displayedCode.trim();

        System.out.println(
                "Referral Code Displayed : " + displayedCode
        );

        // -----------------------------------------------------
        // Click Referral Code
        // This copies the referral code
        // -----------------------------------------------------
        codeElement.click();

        ExtentTestListener.logStep(
                "Referral Code clicked : " + displayedCode
        );

        // -----------------------------------------------------
        // Read copied value from clipboard
        // -----------------------------------------------------
        String copiedCode = driver.getClipboardText();

        if (copiedCode == null || copiedCode.trim().isEmpty()) {

            // Fallback to the value exposed by the UI.
            copiedCode = displayedCode;
        }

        copiedCode = copiedCode.trim();

        // -----------------------------------------------------
        // Validate copied code
        // -----------------------------------------------------
        if (!copiedCode.matches("[A-Z]{3}[0-9]{3}")) {

            throw new RuntimeException(
                    "Invalid referral code copied: " + copiedCode
            );
        }

        // -----------------------------------------------------
        // SAVE COPIED CODE
        // -----------------------------------------------------
        D1ReferEarnReferralCode.referralCode = copiedCode;

        System.out.println(
                "Saved Referral Code : " +
                        D1ReferEarnReferralCode.referralCode
        );

        ExtentTestListener.logStep(
                "Referral Code Saved : " +
                        D1ReferEarnReferralCode.referralCode
        );

        // -----------------------------------------------------
        // Navigate Back
        // -----------------------------------------------------
        driver.navigate().back();

        ExtentTestListener.logStep(
                "Returned from Refer a Friend page"
        );
    }
}