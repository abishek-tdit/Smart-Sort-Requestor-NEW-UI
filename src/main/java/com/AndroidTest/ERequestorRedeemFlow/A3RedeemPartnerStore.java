package com.AndroidTest.ERequestorRedeemFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Collections;

public class A3RedeemPartnerStore extends BaseClassMobile {

    // =========================================================
    // LOCATORS
    // =========================================================
    private final By AGREE_CHECKBOX =
            AppiumBy.xpath("//android.widget.CheckBox[@checkable='true']");

    private final By OK_BUTTON =
            AppiumBy.accessibilityId("OK");

    private final By MOST_POPULAR =
            AppiumBy.accessibilityId("Most Popular");

    private final By SEARCH_BOX =
            AppiumBy.xpath("//android.widget.EditText");

    private final By REDEEM_BUTTON =
            AppiumBy.accessibilityId("Redeem");

    private final By REDEEM_GIFT_BUTTON =
            AppiumBy.accessibilityId("Redeem Gift");


    // =========================================================
    // CHECKBOX COORDINATES
    // =========================================================

    private static final int CHECKBOX_X = 144;
    private static final int CHECKBOX_Y = 730;


    // =========================================================
    // REDEEM PARTNER STORE
    // =========================================================

    public void proceedRedeem() throws InterruptedException {

        ExtentTestListener.logStep("========== REDEEM PARTNER STORE STARTED ==========");

        // =====================================================
        // 1. SELECT TERMS AND CONDITIONS
        // =====================================================

        ExtentTestListener.logStep("Waiting for Terms and Conditions checkbox...");

        WebElement checkbox = wait.until
                (ExpectedConditions.presenceOfElementLocated(AGREE_CHECKBOX));

        ExtentTestListener.logStep("Terms and Conditions checkbox displayed");


        // =====================================================
        // 2. CHECK CURRENT CHECKBOX STATUS
        // =====================================================

        String checkedStatus = checkbox.getAttribute("checked");

        ExtentTestListener.logStep("Checkbox current status: " + checkedStatus);


        // =====================================================
        // 3. TAP ACTUAL VISIBLE CHECKBOX
        // =====================================================

        if (!"true".equalsIgnoreCase(checkedStatus)) {

            ExtentTestListener.logStep(
                    "Tapping actual visible checkbox at X="
                            + CHECKBOX_X
                            + ", Y="
                            + CHECKBOX_Y
            );

            tapAtCoordinates(
                    CHECKBOX_X,
                    CHECKBOX_Y
            );


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
                    "I agree checkbox selected successfully"
            );

        } else {

            ExtentTestListener.logStep(
                    "I agree checkbox was already selected"
            );
        }


        // =====================================================
        // 5. WAIT FOR OK BUTTON TO BECOME ENABLED
        // =====================================================

        ExtentTestListener.logStep(
                "Waiting for OK button..."
        );

        WebElement okButton =
                waitUntilClickable(
                        OK_BUTTON,
                        WAIT_TIME
                );

        ExtentTestListener.logStep(
                "OK button is enabled"
        );


        // =====================================================
        // 6. CLICK OK
        // =====================================================

        okButton.click();

        ExtentTestListener.logStep(
                "OK button clicked"
        );


        // =====================================================
        // 7. CLICK MOST POPULAR
        // =====================================================

        WebElement popular =
                waitUntilClickable(
                        MOST_POPULAR,
                        WAIT_TIME
                );

        popular.click();

        ExtentTestListener.logStep(
                "Clicked Most Popular"
        );


        // =====================================================
        // 8. SEARCH AMAZON
        // =====================================================

        WebElement search =
                waitUntilVisible(
                        SEARCH_BOX,
                        WAIT_TIME
                );

        search.click();

        ExtentTestListener.logStep(
                "Search Box Clicked"
        );

        search.clear();

        search.sendKeys("Amazon");

        ExtentTestListener.logStep(
                "Entered Amazon in Search Box"
        );


        // =====================================================
        // 9. HIDE KEYBOARD
        // =====================================================

        hideKeyboardSafely(
                "Keyboard Hidden",
                "Keyboard Already Hidden"
        );


        // =====================================================
        // 10. SCROLL DOWN
        // =====================================================

        scrollDown();

        ExtentTestListener.logStep(
                "Scrolled Down"
        );


        // =====================================================
        // 11. CLICK REDEEM
        // =====================================================

        WebElement redeem =
                waitUntilClickable(
                        REDEEM_BUTTON,
                        WAIT_TIME
                );

        redeem.click();

        ExtentTestListener.logStep(
                "Clicked Redeem"
        );


        // =====================================================
        // 12. ENTER SAR AMOUNT
        // =====================================================

        WebElement amountField =
                waitUntilVisible(
                        SEARCH_BOX,
                        WAIT_TIME
                );

        amountField.click();

        amountField.clear();

        amountField.sendKeys("10");

        ExtentTestListener.logStep(
                "Entered SAR Amount: 10"
        );


        // =====================================================
        // 13. HIDE KEYBOARD
        // =====================================================

        hideKeyboardSafely(
                "Keyboard Hidden After Amount Entry",
                "Keyboard Already Hidden"
        );


        // =====================================================
        // 14. CLICK REDEEM GIFT
        // =====================================================

        WebElement redeemGift =
                waitUntilClickable(
                        REDEEM_GIFT_BUTTON,
                        WAIT_TIME
                );

        redeemGift.click();

        ExtentTestListener.logStep(
                "Clicked Redeem Gift"
        );


        // =====================================================
        // 15. WAIT FOR FINAL OK POPUP
        // =====================================================

        ExtentTestListener.logStep(
                "Waiting for final OK popup..."
        );

        WebElement finalOk =
                waitUntilClickable(
                        OK_BUTTON,
                        WAIT_TIME
                );

        ExtentTestListener.logStep(
                "Final OK Popup Displayed"
        );


        // =====================================================
        // 16. CLICK FINAL OK
        // =====================================================

        finalOk.click();

        ExtentTestListener.logStep(
                "Clicked Final OK Popup"
        );


        // =====================================================
        // COMPLETED
        // =====================================================

        ExtentTestListener.logStep(
                "========== REDEEM PARTNER STORE STEP COMPLETED =========="
        );
    }


    // =========================================================
    // TAP AT SCREEN COORDINATES
    // =========================================================

    private void tapAtCoordinates(int x, int y) {

        PointerInput finger =
                new PointerInput(
                        PointerInput.Kind.TOUCH,
                        "finger"
                );

        Sequence tap =
                new Sequence(finger, 1);

        tap.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        x,
                        y
                )
        );

        tap.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        tap.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        driver.perform(
                Collections.singletonList(tap)
        );
    }


    // =========================================================
    // SAFE KEYBOARD HANDLING
    // =========================================================

    private void hideKeyboardSafely(
            String successMessage,
            String alreadyHiddenMessage
    ) {

        try {

            driver.hideKeyboard();

            ExtentTestListener.logStep(
                    successMessage
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    alreadyHiddenMessage
            );
        }
    }
}