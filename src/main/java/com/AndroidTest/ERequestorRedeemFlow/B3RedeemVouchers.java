package com.AndroidTest.ERequestorRedeemFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;
import java.time.Duration;

public class B3RedeemVouchers extends BaseClassMobile {

    // =========================================================
    // LOCATORS
    // =========================================================

    // Terms & Conditions Checkbox
    private final By AGREE_CHECKBOX =
            AppiumBy.xpath("//android.widget.CheckBox[@checkable='true']");

    // Terms & Conditions Popup OK
    private final By TERMS_OK_BUTTON =
            AppiumBy.accessibilityId("OK");

    // Vouchers
    private final By VOUCHERS =
            AppiumBy.accessibilityId("Vouchers");

    // Voucher Amount
    private final By AMOUNT_1000 =
            AppiumBy.accessibilityId("1000");

    // Redeem Voucher
    private final By REDEEM_VOUCHER_BUTTON =
            AppiumBy.accessibilityId("Redeem Voucher");

    // Redemption Confirmation Popup OK
    private final By REDEEM_OK_BUTTON =
            AppiumBy.accessibilityId("OK");


    // =========================================================
    // CHECKBOX COORDINATES
    // =========================================================

    private static final int CHECKBOX_X = 144;
    private static final int CHECKBOX_Y = 730;


    // =========================================================
    // REDEEM VOUCHERS
    // =========================================================

    public void redeemVouchers() throws InterruptedException {

        ExtentTestListener.logStep(
                "========== REDEEM VOUCHERS STARTED =========="
        );


        // =====================================================
        // 1. WAIT FOR TERMS & CONDITIONS CHECKBOX
        // =====================================================

        ExtentTestListener.logStep(
                "Waiting for Terms and Conditions checkbox..."
        );

        WebElement checkbox =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                AGREE_CHECKBOX
                        )
                );

        ExtentTestListener.logStep(
                "Terms and Conditions checkbox displayed"
        );


        // =====================================================
        // 2. CHECK CURRENT CHECKBOX STATUS
        // =====================================================

        String checkedStatus =
                checkbox.getAttribute("checked");

        ExtentTestListener.logStep(
                "Checkbox current status: " + checkedStatus
        );


        // =====================================================
        // 3. SELECT CHECKBOX IF NOT ALREADY SELECTED
        // =====================================================

        if (!"true".equalsIgnoreCase(checkedStatus)) {

            ExtentTestListener.logStep(
                    "Tapping visible checkbox at X="
                            + CHECKBOX_X
                            + ", Y="
                            + CHECKBOX_Y
            );

            PointerInput finger = new PointerInput(
                    PointerInput.Kind.TOUCH,
                    "finger"
            );

            Sequence tap = new Sequence(finger, 1);

            tap.addAction(
                    finger.createPointerMove(
                            Duration.ZERO,
                            PointerInput.Origin.viewport(),
                            CHECKBOX_X,
                            CHECKBOX_Y
                    )
            );

            tap.addAction(
                    finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
            );

            tap.addAction(
                    finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
            );

            driver.perform(List.of(tap));


            // =================================================
            // 4. VERIFY CHECKBOX IS SELECTED
            // =================================================

            wait.until(driver -> {

                try {

                    WebElement currentCheckbox =
                            driver.findElement(AGREE_CHECKBOX);

                    return "true".equalsIgnoreCase(
                            currentCheckbox.getAttribute("checked")
                    );

                } catch (Exception e) {

                    return false;
                }
            });

            ExtentTestListener.logStep(
                    "I Agree checkbox selected successfully"
            );

        } else {

            ExtentTestListener.logStep(
                    "I Agree checkbox was already selected"
            );
        }


        // =====================================================
        // 5. WAIT FOR TERMS POPUP OK
        // =====================================================

        ExtentTestListener.logStep(
                "Waiting for Terms and Conditions OK button..."
        );

        WebElement termsOkButton =
                waitUntilClickable(
                        TERMS_OK_BUTTON,
                        WAIT_TIME
                );

        ExtentTestListener.logStep(
                "Terms and Conditions OK button is enabled"
        );


        // =====================================================
        // 6. CLICK TERMS POPUP OK
        // =====================================================

        termsOkButton.click();

        ExtentTestListener.logStep(
                "Clicked Terms and Conditions OK"
        );


        // =====================================================
        // 7. CLICK VOUCHERS
        // =====================================================

        ExtentTestListener.logStep(
                "Waiting for Vouchers..."
        );

        WebElement voucher =
                waitUntilClickable(
                        VOUCHERS,
                        WAIT_TIME
                );

        voucher.click();

        ExtentTestListener.logStep(
                "Clicked Vouchers"
        );


        // =====================================================
        // 8. SELECT 1000 VOUCHER
        // =====================================================

        ExtentTestListener.logStep(
                "Waiting for 1000 voucher..."
        );

        WebElement amount =
                waitUntilClickable(
                        AMOUNT_1000,
                        WAIT_TIME
                );

        amount.click();

        ExtentTestListener.logStep(
                "Selected Voucher Amount: 1000"
        );


        // =====================================================
        // 9. CLICK REDEEM VOUCHER
        // =====================================================

        ExtentTestListener.logStep(
                "Waiting for Redeem Voucher button..."
        );

        WebElement redeemVoucher =
                waitUntilClickable(
                        REDEEM_VOUCHER_BUTTON,
                        WAIT_TIME
                );

        redeemVoucher.click();

        ExtentTestListener.logStep(
                "Clicked Redeem Voucher"
        );


        // =====================================================
        // 10. WAIT FOR REDEMPTION OK POPUP
        // =====================================================

        ExtentTestListener.logStep(
                "Waiting for redemption confirmation popup..."
        );

        WebElement redeemOkButton =
                waitUntilClickable(
                        REDEEM_OK_BUTTON,
                        WAIT_TIME
                );

        ExtentTestListener.logStep(
                "Redemption confirmation popup displayed"
        );


        // =====================================================
        // 11. CLICK REDEMPTION OK
        // =====================================================

        redeemOkButton.click();

        ExtentTestListener.logStep(
                "Clicked redemption confirmation OK"
        );


        // =====================================================
        // COMPLETED
        // =====================================================

        ExtentTestListener.logStep(
                "========== REDEEM VOUCHERS COMPLETED =========="
        );
    }
}
