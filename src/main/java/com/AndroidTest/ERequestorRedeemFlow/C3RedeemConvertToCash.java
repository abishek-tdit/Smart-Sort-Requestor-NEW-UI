package com.AndroidTest.ERequestorRedeemFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class C3RedeemConvertToCash extends BaseClassMobile {

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By AGREE_CHECKBOX =
            AppiumBy.xpath("//android.widget.CheckBox[@checkable='true']");

    private final By OK_BUTTON =
            AppiumBy.accessibilityId("OK");

    // Use XPath as fallback because accessibility text can vary
    private final By convertToCash =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'Convert to Cash')" +
                            " or contains(@content-desc,'Convert to cash')" +
                            " or contains(@text,'Convert to Cash')" +
                            " or contains(@text,'Convert to cash')]"
            );

    private final By sar10 =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'SAR 10')" +
                            " and contains(@content-desc,'Points 200.0')]"
            );

    private final By bankTransfer =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'Bank Transfer')" +
                            " or contains(@text,'Bank Transfer')]"
            );

    private final By proceedBtn =
            AppiumBy.accessibilityId("Proceed");

    private final By confirmBtn =
            AppiumBy.accessibilityId("Confirm");

    private final By okBtn =
            AppiumBy.accessibilityId("OK");


    // =========================================================
    // CHECKBOX COORDINATES
    // =========================================================

    private static final int CHECKBOX_X = 144;
    private static final int CHECKBOX_Y = 730;

    // =========================================================
    // MAIN FLOW
    // =========================================================

    public void convertToCash() {

        ExtentTestListener.logStep(
                "========== REDEEM CONVERT TO CASH STARTED =========="
        );

        try {

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



            // =================================================
            // 2. FIND CONVERT TO CASH
            // =================================================

            ExtentTestListener.logStep(
                    "Searching for Convert to Cash..."
            );

            WebElement convertCash =
                    findConvertToCash();

            if (convertCash == null) {

                throw new RuntimeException(
                        "Convert to Cash button was not found after scrolling."
                );
            }


            // =================================================
            // 3. CLICK CONVERT TO CASH
            // =================================================

            clickElement(convertCash);

            ExtentTestListener.logStep(
                    "Clicked Convert to Cash"
            );


            // =================================================
            // 4. SELECT SAR 10
            // =================================================

            ExtentTestListener.logStep(
                    "Waiting for SAR 10 Points 200.0..."
            );

            WebElement sar =
                    waitUntilClickable(sar10, WAIT_TIME);

            clickElement(sar);

            ExtentTestListener.logStep(
                    "Clicked SAR 10 Points 200.0"
            );


            // =================================================
            // 5. SELECT BANK TRANSFER
            // =================================================

            ExtentTestListener.logStep(
                    "Waiting for Bank Transfer..."
            );

            WebElement bank =
                    waitUntilClickable(
                            bankTransfer,
                            WAIT_TIME
                    );

            clickElement(bank);

            ExtentTestListener.logStep(
                    "Selected Bank Transfer"
            );


            // =================================================
            // 6. HIDE KEYBOARD IF PRESENT
            // =================================================

            try {

                driver.hideKeyboard();

            } catch (Exception ignored) {

                // Keyboard already hidden
            }


            // =================================================
            // 7. PROCEED
            // =================================================

            ExtentTestListener.logStep(
                    "Waiting for Proceed..."
            );

            WebElement proceed =
                    waitUntilClickable(
                            proceedBtn,
                            WAIT_TIME
                    );

            clickElement(proceed);

            ExtentTestListener.logStep(
                    "Clicked Proceed"
            );


            // =================================================
            // 8. CONFIRM
            // =================================================

            ExtentTestListener.logStep(
                    "Waiting for Confirm..."
            );

            WebElement confirm =
                    waitUntilClickable(
                            confirmBtn,
                            WAIT_TIME
                    );

            clickElement(confirm);

            ExtentTestListener.logStep(
                    "Clicked Confirm"
            );


            // =================================================
            // 9. WAIT FOR OK POPUP
            // =================================================

            ExtentTestListener.logStep(
                    "Waiting for OK Popup..."
            );

            WebElement okPopup =
                    waitUntilClickable(
                            okBtn,
                            WAIT_TIME
                    );

            ExtentTestListener.logStep(
                    "OK Popup Displayed"
            );


            // =================================================
            // 10. CLICK OK
            // =================================================

            clickElement(okPopup);

            ExtentTestListener.logStep(
                    "Clicked OK Popup"
            );


            // =================================================
            // COMPLETED
            // =================================================

            ExtentTestListener.logStep(
                    "========== CONVERT TO CASH COMPLETED =========="
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "CONVERT TO CASH FAILED : "
                            + e.getClass().getSimpleName()
                            + " : "
                            + e.getMessage()
            );

            throw e;
        }
    }


    // =========================================================
    // FIND CONVERT TO CASH
    // =========================================================

    private WebElement findConvertToCash() {

        /*
         * First try without scrolling.
         */
        WebElement element = findVisibleElement(convertToCash);

        if (element != null) {

            ExtentTestListener.logStep(
                    "Convert to Cash Found"
            );

            return element;
        }


        /*
         * Horizontal scrolling can require more than one swipe.
         * Maximum 4 attempts keeps it fast while handling slow UI.
         */
        for (int attempt = 1; attempt <= 4; attempt++) {

            ExtentTestListener.logStep(
                    "Searching Convert to Cash - Swipe "
                            + attempt
            );

            try {

                driver.executeScript(
                        "mobile: swipeGesture",
                        Map.of(
                                "left", 40,
                                "top", 250,
                                "width", 640,
                                "height", 1000,
                                "direction", "left",
                                "percent", 0.65
                        )
                );

            } catch (Exception e) {

                ExtentTestListener.logStep(
                        "Horizontal swipe failed - trying scroll gesture"
                );

                try {

                    driver.executeScript(
                            "mobile: scrollGesture",
                            Map.of(
                                    "left", 40,
                                    "top", 250,
                                    "width", 640,
                                    "height", 1000,
                                    "direction", "left",
                                    "percent", 0.70
                            )
                    );

                } catch (Exception ignored) {
                }
            }


            /*
             * Give the UI a chance to render after swipe.
             * This is NOT Thread.sleep.
             */
            element = waitForElement(
                    convertToCash,
                    3
            );

            if (element != null) {

                ExtentTestListener.logStep(
                        "Convert to Cash Found After Swipe "
                                + attempt
                );

                return element;
            }
        }

        return null;
    }


    // =========================================================
    // FIND VISIBLE ELEMENT
    // =========================================================

    private WebElement findVisibleElement(By locator) {

        try {

            WebElement element =
                    driver.findElement(locator);

            if (element.isDisplayed()) {

                return element;
            }

        } catch (Exception ignored) {
        }

        return null;
    }


    // =========================================================
    // SHORT WAIT
    // =========================================================

    private WebElement waitForElement(
            By locator,
            int seconds) {

        long end =
                System.currentTimeMillis()
                        + (seconds * 1000L);

        while (System.currentTimeMillis() < end) {

            try {

                WebElement element =
                        driver.findElement(locator);

                if (element.isDisplayed()) {

                    return element;
                }

            } catch (Exception ignored) {
            }

            try {

                Thread.sleep(200);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                return null;
            }
        }

        return null;
    }


    // =========================================================
    // ROBUST CLICK
    // =========================================================

    private void clickElement(WebElement element) {

        /*
         * Normal click first.
         */
        try {

            element.click();

            return;

        } catch (Exception ignored) {
        }


        /*
         * Element may have moved slightly after rendering.
         * Re-find using its attributes is not possible from
         * the WebElement itself, so try JavaScript/mobile tap
         * using its center coordinates.
         */
        try {

            int centerX =
                    element.getRect().getX()
                            + element.getRect().getWidth() / 2;

            int centerY =
                    element.getRect().getY()
                            + element.getRect().getHeight() / 2;

            driver.executeScript(
                    "mobile: clickGesture",
                    Map.of(
                            "x", centerX,
                            "y", centerY
                    )
            );

            return;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to click element.",
                    e
            );
        }
    }
}