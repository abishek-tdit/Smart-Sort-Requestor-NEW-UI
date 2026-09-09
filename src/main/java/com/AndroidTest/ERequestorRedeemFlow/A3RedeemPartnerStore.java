package com.AndroidTest.ERequestorRedeemFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class A3RedeemPartnerStore extends BaseClassMobile {

    //=========================================================
    // Locators
    //=========================================================

    // Checkbox
    private final By checkBox =
            AppiumBy.className("android.widget.CheckBox");

    // Most Popular
    private final By mostPopular =
            AppiumBy.accessibilityId("Most Popular");

    // Search Box
    private final By searchBox =
            AppiumBy.xpath("//android.widget.EditText");

    // Redeem Button
    private final By redeemBtn =
            AppiumBy.accessibilityId("Redeem");

    // Redeem Gift Button
    private final By redeemGiftBtn =
            AppiumBy.accessibilityId("Redeem Gift");

    // OK Popup Button
    private final By okBtn =
            AppiumBy.accessibilityId("OK");


    //=========================================================
    // Redeem Partner Store
    //=========================================================

    public void proceedRedeem() {

        ExtentTestListener.logStep(
                "========== REDEEM PARTNER STORE STARTED ==========");


        //=====================================================
        // 1. Select Checkbox
        //=====================================================

        WebElement checkbox = waitUntilClickable(checkBox, WAIT_TIME);

        checkbox.click();

        ExtentTestListener.logStep(
                "Checkbox Selected");


        //=====================================================
        // 2. Click Most Popular
        //=====================================================

        WebElement popular = waitUntilClickable(mostPopular, WAIT_TIME);

        popular.click();

        ExtentTestListener.logStep(
                "Clicked Most Popular");


        //=====================================================
        // 3. Enter Amazon in Search Box
        //=====================================================

        WebElement search =
                waitUntilVisible(searchBox, WAIT_TIME);

        search.click();

        ExtentTestListener.logStep(
                "Search Box Clicked");

        search.clear();

        search.sendKeys("Amazon");

        ExtentTestListener.logStep(
                "Entered Amazon in Search Box");


        //=====================================================
        // 4. Hide Keyboard
        //=====================================================

        try {

            driver.hideKeyboard();

            ExtentTestListener.logStep(
                    "Keyboard Hidden");

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Keyboard Already Hidden");
        }


        //=====================================================
        // 5. Scroll Down
        //=====================================================

        scrollDown();

        ExtentTestListener.logStep(
                "Scrolled Down");


        //=====================================================
        // 6. Click Redeem
        //=====================================================

        WebElement redeem =
                waitUntilClickable(redeemBtn, WAIT_TIME);

        redeem.click();

        ExtentTestListener.logStep(
                "Clicked Redeem");


        //=====================================================
        // 7. Enter SAR Amount
        //=====================================================

        WebElement amountField =
                waitUntilVisible(
                        AppiumBy.xpath("//android.widget.EditText"),
                        WAIT_TIME
                );

        amountField.click();

        amountField.clear();

        amountField.sendKeys("10");

        ExtentTestListener.logStep(
                "Entered SAR Amount: 10");


        //=====================================================
        // 8. Hide Keyboard
        //=====================================================

        try {

            driver.hideKeyboard();

            ExtentTestListener.logStep(
                    "Keyboard Hidden After Amount Entry");

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Keyboard Already Hidden");
        }


        //=====================================================
        // 9. Click Redeem Gift
        //=====================================================

        WebElement redeemGift =
                waitUntilClickable(
                        redeemGiftBtn,
                        WAIT_TIME
                );

        redeemGift.click();

        ExtentTestListener.logStep(
                "Clicked Redeem Gift");


        //=====================================================
        // 10. Wait For OK Popup
        //=====================================================

        WebElement okPopup = waitUntilClickable
                        (okBtn,
                        WAIT_TIME );

        ExtentTestListener.logStep("OK Popup Displayed");


        //=====================================================
        // 11. Click OK
        //=====================================================

        okPopup.click();

        ExtentTestListener.logStep("Clicked OK Popup");


        //=====================================================
        // COMPLETED
        //=====================================================

        ExtentTestListener.logStep("========== REDEEM PARTNER STORE STEP COMPLETED ==========");
    }
}