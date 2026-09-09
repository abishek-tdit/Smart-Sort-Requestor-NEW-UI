package com.AndroidTest.ERequestorRedeemFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class B3RedeemVouchers extends BaseClassMobile {

    //=========================================================
    // Locators
    //=========================================================

    // Checkbox
    private final By checkBox =
            AppiumBy.className("android.widget.CheckBox");

    // Vouchers
    private final By vouchers =
            AppiumBy.accessibilityId("Vouchers");

    // 1000
    private final By amount1000 =
            AppiumBy.accessibilityId("1000");

    // Redeem Voucher
    private final By redeemVoucherBtn =
            AppiumBy.accessibilityId("Redeem Voucher");

    // OK Popup
    private final By okBtn =
            AppiumBy.accessibilityId("OK");


    //=========================================================
    // Redeem Vouchers
    //=========================================================

    public void redeemVouchers() {

        ExtentTestListener.logStep(
                "========== REDEEM VOUCHERS STARTED ==========");


        //=====================================================
        // 1. Select Checkbox
        //=====================================================

        WebElement checkbox =
                waitUntilClickable(
                        checkBox,
                        WAIT_TIME
                );

        checkbox.click();

        ExtentTestListener.logStep(
                "Checkbox Selected");


        //=====================================================
        // 2. Click Vouchers
        //=====================================================

        WebElement voucher =
                waitUntilClickable(
                        vouchers,
                        WAIT_TIME
                );

        voucher.click();

        ExtentTestListener.logStep(
                "Clicked Vouchers");


        //=====================================================
        // 3. Click 1000
        //=====================================================

        WebElement amount =
                waitUntilClickable(
                        amount1000,
                        WAIT_TIME
                );

        amount.click();

        ExtentTestListener.logStep(
                "Selected Voucher Amount: 1000");


        //=====================================================
        // 4. Click Redeem Voucher
        //=====================================================

        WebElement redeemVoucher =
                waitUntilClickable(
                        redeemVoucherBtn,
                        WAIT_TIME
                );

        redeemVoucher.click();

        ExtentTestListener.logStep(
                "Clicked Redeem Voucher");


        //=====================================================
        // 5. Wait For OK Popup
        //=====================================================

        WebElement okPopup =
                waitUntilClickable(
                        okBtn,
                        WAIT_TIME
                );

        ExtentTestListener.logStep(
                "OK Popup Displayed");


        //=====================================================
        // 6. Click OK
        //=====================================================

        okPopup.click();

        ExtentTestListener.logStep(
                "Clicked OK Popup");


        //=====================================================
        // COMPLETED
        //=====================================================

        ExtentTestListener.logStep(
                "========== REDEEM VOUCHERS COMPLETED ==========");
    }
}