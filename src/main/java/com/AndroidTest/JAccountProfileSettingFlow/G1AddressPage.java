package com.AndroidTest.JAccountProfileSettingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class G1AddressPage extends BaseClassMobile {

    // =========================================================
    // DRIVER / WAIT
    // =========================================================

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final int WAIT_TIME = 30;

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By ADDRESS =
            AppiumBy.xpath(
                    "//android.view.View[" +
                            "starts-with(@content-desc,'Address')" +
                            "]"
            );

    private final By ADD_ADDRESS =
            AppiumBy.accessibilityId("Add Address");

    private final By SELECT_CITY =
            AppiumBy.accessibilityId("Select City");

    private final By BQAIQ =
            AppiumBy.accessibilityId("Bqaiq");

    private final By SELECT_DISTRICT =
            AppiumBy.accessibilityId("Select district");

    private final By AMBATTUR =
            AppiumBy.accessibilityId("Ambattur");

    private final By USE_CURRENT_LOCATION =
            AppiumBy.accessibilityId("Use Current Location");

    private final By SAVE_ADDRESS =
            AppiumBy.accessibilityId("Save Address");

    private final By OK_BUTTON =
            AppiumBy.accessibilityId("OK");

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public G1AddressPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    }

    // =========================================================
    // REAL TOUCH SWIPE UP
    // =========================================================

    private void performSwipeUp() {

        Dimension size =
                driver.manage().window().getSize();

        int width = size.getWidth();
        int height = size.getHeight();

        int startX = width / 2;
        int startY = (int) (height * 0.80);

        int endX = width / 2;
        int endY = (int) (height * 0.30);

        ExtentTestListener.logStep("REAL SWIPE UP : "
                + startX + "," + startY
                + " -> "
                + endX + "," + endY);

        PointerInput finger = new PointerInput
                (PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        startX,
                        startY));

        swipe.addAction(finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(finger.createPointerMove(
                        Duration.ofMillis(800),
                        PointerInput.Origin.viewport(),
                        endX,
                        endY));

        swipe.addAction
                (finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    // =========================================================
    // CHECK ELEMENT SAFE VISIBLE AREA
    // =========================================================

    private boolean isInSafeVisibleArea(
            WebElement element) {

        try {

            int y =
                    element.getRect().getY();

            int elementHeight =
                    element.getRect().getHeight();

            int centerY =
                    y + (elementHeight / 2);

            Dimension screen =
                    driver.manage()
                            .window()
                            .getSize();

            int screenHeight =
                    screen.getHeight();

            ExtentTestListener.logStep(
                    "Element Position | Y="
                            + y
                            + " | Height="
                            + elementHeight
                            + " | CenterY="
                            + centerY
                            + " | ScreenHeight="
                            + screenHeight
            );

            return centerY > 180
                    && centerY < screenHeight - 180;

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Unable to determine element position"
            );

            return false;
        }
    }

    // =========================================================
    // CLICK ADDRESS
    // =========================================================

    public G1AddressPage clickAddress() {

        long startTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "===== FIND ADDRESS SECTION ====="
        );

        for (int attempt = 1; attempt <= 6; attempt++) {

            ExtentTestListener.logStep(
                    "Address visibility attempt : "
                            + attempt
            );

            try {

                WebElement address =
                        driver.findElement(ADDRESS);

                ExtentTestListener.logStep(
                        "Dynamic Address element found"
                );

                // -------------------------------------------------
                // CHECK SAFE VISIBLE AREA
                // -------------------------------------------------

                if (!isInSafeVisibleArea(address)) {

                    ExtentTestListener.logStep(
                            "Address found but outside safe visible area"
                    );

                    performSwipeUp();
                    continue;
                }

                // -------------------------------------------------
                // WAIT UNTIL CLICKABLE
                // -------------------------------------------------

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                ADDRESS
                        )
                );

                // -------------------------------------------------
                // RE-FIND BEFORE CLICK
                // -------------------------------------------------

                address =
                        driver.findElement(ADDRESS);

                if (!isInSafeVisibleArea(address)) {

                    ExtentTestListener.logStep(
                            "Address moved after wait - scrolling again"
                    );

                    performSwipeUp();
                    continue;
                }

                // -------------------------------------------------
                // CLICK
                // -------------------------------------------------

                ExtentTestListener.logStep(
                        "CLICKING DYNAMIC ADDRESS"
                );

                address.click();

                long endTime =
                        System.currentTimeMillis();

                ExtentTestListener.logStep(
                        "Address clicked successfully"
                                + " | Time Taken : "
                                + (endTime - startTime)
                                + " ms"
                );

                return this;

            } catch (Exception e) {

                ExtentTestListener.logStep(
                        "Address not ready on attempt "
                                + attempt
                                + " | Reason : "
                                + e.getClass().getSimpleName()
                );

                if (attempt < 6) {
                    performSwipeUp();
                }
            }
        }

        throw new RuntimeException(
                "FAILED: Dynamic Address could not be clicked"
        );
    }

    // =========================================================
    // CLICK ADD ADDRESS
    // =========================================================

    public G1AddressPage clickAddAddress() {

        long startTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "Waiting for Add Address"
        );

        WebElement addAddress =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                ADD_ADDRESS
                        )
                );

        addAddress.click();

        long endTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "Add Address clicked successfully"
                        + " | Time Taken : "
                        + (endTime - startTime)
                        + " ms"
        );

        return this;
    }

    // =========================================================
    // SELECT CITY
    // =========================================================

    public G1AddressPage selectCity() {

        long startTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "Click Select City"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        SELECT_CITY
                )
        ).click();

        ExtentTestListener.logStep(
                "Searching for Bqaiq"
        );

        for (int attempt = 1; attempt <= 5; attempt++) {

            try {

                WebElement bqaiq =
                        driver.findElement(BQAIQ);

                if (!isInSafeVisibleArea(bqaiq)) {

                    ExtentTestListener.logStep(
                            "Bqaiq found but outside safe visible area"
                    );

                    performSwipeUp();
                    continue;
                }

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                BQAIQ
                        )
                );

                // Re-find immediately before click
                bqaiq =
                        driver.findElement(BQAIQ);

                if (!isInSafeVisibleArea(bqaiq)) {

                    ExtentTestListener.logStep(
                            "Bqaiq moved after wait - scrolling again"
                    );

                    performSwipeUp();
                    continue;
                }

                bqaiq.click();

                long endTime =
                        System.currentTimeMillis();

                ExtentTestListener.logStep(
                        "Bqaiq selected successfully"
                                + " | Time Taken : "
                                + (endTime - startTime)
                                + " ms"
                );

                return this;

            } catch (Exception e) {

                ExtentTestListener.logStep(
                        "Bqaiq not ready on attempt "
                                + attempt
                                + " | Reason : "
                                + e.getClass().getSimpleName()
                );

                if (attempt < 5) {
                    performSwipeUp();
                }
            }
        }

        throw new RuntimeException(
                "FAILED: Bqaiq could not be found"
        );
    }

    // =========================================================
    // SELECT DISTRICT
    // =========================================================

    public G1AddressPage selectDistrict() {

        long startTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "Click Select District"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        SELECT_DISTRICT
                )
        ).click();

        ExtentTestListener.logStep(
                "Searching for Ambattur"
        );

        for (int attempt = 1; attempt <= 5; attempt++) {

            try {

                WebElement ambattur =
                        driver.findElement(AMBATTUR);

                if (!isInSafeVisibleArea(ambattur)) {

                    ExtentTestListener.logStep(
                            "Ambattur found but outside safe visible area"
                    );

                    performSwipeUp();
                    continue;
                }

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                AMBATTUR
                        )
                );

                // Re-find immediately before click
                ambattur =
                        driver.findElement(AMBATTUR);

                if (!isInSafeVisibleArea(ambattur)) {

                    ExtentTestListener.logStep(
                            "Ambattur moved after wait - scrolling again"
                    );

                    performSwipeUp();
                    continue;
                }

                ambattur.click();

                long endTime =
                        System.currentTimeMillis();

                ExtentTestListener.logStep(
                        "Ambattur selected successfully"
                                + " | Time Taken : "
                                + (endTime - startTime)
                                + " ms"
                );

                return this;

            } catch (Exception e) {

                ExtentTestListener.logStep(
                        "Ambattur not ready on attempt "
                                + attempt
                                + " | Reason : "
                                + e.getClass().getSimpleName()
                );

                if (attempt < 5) {
                    performSwipeUp();
                }
            }
        }

        throw new RuntimeException(
                "FAILED: Ambattur could not be found"
        );
    }

    // =========================================================
    // USE CURRENT LOCATION
    // =========================================================

    public G1AddressPage useCurrentLocation() {

        long startTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "Searching for Use Current Location"
        );

        for (int attempt = 1; attempt <= 5; attempt++) {

            try {

                WebElement location =
                        driver.findElement(
                                USE_CURRENT_LOCATION
                        );

                if (!isInSafeVisibleArea(location)) {

                    ExtentTestListener.logStep(
                            "Use Current Location found but outside safe visible area"
                    );

                    performSwipeUp();
                    continue;
                }

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                USE_CURRENT_LOCATION
                        )
                );

                // Re-find immediately before click
                location =
                        driver.findElement(
                                USE_CURRENT_LOCATION
                        );

                if (!isInSafeVisibleArea(location)) {

                    ExtentTestListener.logStep(
                            "Use Current Location moved after wait - scrolling again"
                    );

                    performSwipeUp();
                    continue;
                }

                location.click();

                long endTime =
                        System.currentTimeMillis();

                ExtentTestListener.logStep(
                        "Use Current Location clicked successfully"
                                + " | Time Taken : "
                                + (endTime - startTime)
                                + " ms"
                );

                return this;

            } catch (Exception e) {

                ExtentTestListener.logStep(
                        "Use Current Location not ready on attempt "
                                + attempt
                                + " | Reason : "
                                + e.getClass().getSimpleName()
                );

                if (attempt < 5) {
                    performSwipeUp();
                }
            }
        }

        throw new RuntimeException(
                "FAILED: Use Current Location could not be found"
        );
    }

    // =========================================================
    // SAVE ADDRESS
    // =========================================================

    public G1AddressPage saveAddress() {

        long startTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "Searching for Save Address"
        );

        for (int attempt = 1; attempt <= 5; attempt++) {

            try {

                WebElement save =
                        driver.findElement(SAVE_ADDRESS);

                if (!isInSafeVisibleArea(save)) {

                    ExtentTestListener.logStep(
                            "Save Address found but outside safe visible area"
                    );

                    performSwipeUp();
                    continue;
                }

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                SAVE_ADDRESS
                        )
                );

                // Re-find immediately before click
                save =
                        driver.findElement(SAVE_ADDRESS);

                if (!isInSafeVisibleArea(save)) {

                    ExtentTestListener.logStep(
                            "Save Address moved after wait - scrolling again"
                    );

                    performSwipeUp();
                    continue;
                }

                save.click();

                long endTime =
                        System.currentTimeMillis();

                ExtentTestListener.logStep(
                        "Save Address clicked successfully"
                                + " | Time Taken : "
                                + (endTime - startTime)
                                + " ms"
                );

                return this;

            } catch (Exception e) {

                ExtentTestListener.logStep(
                        "Save Address not ready on attempt "
                                + attempt
                                + " | Reason : "
                                + e.getClass().getSimpleName()
                );

                if (attempt < 5) {
                    performSwipeUp();
                }
            }
        }

        throw new RuntimeException(
                "FAILED: Save Address could not be found"
        );
    }

    // =========================================================
    // CLICK OK
    // =========================================================

    public G1AddressPage clickOK() {

        long startTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "Waiting for OK Popup"
        );

        WebElement ok =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                OK_BUTTON
                        )
                );

        ok.click();

        long endTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "OK clicked successfully"
                        + " | Time Taken : "
                        + (endTime - startTime)
                        + " ms"
        );

        return this;
    }

    // =========================================================
    // COMPLETE ADDRESS FLOW
    // =========================================================

    public void completeAddressFlow() {

        long startTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "===== ADDRESS FLOW STARTED ====="
        );

        clickAddress();

        clickAddAddress();

        selectCity();

        selectDistrict();

        useCurrentLocation();

        saveAddress();

        clickOK();

        long endTime =
                System.currentTimeMillis();

        ExtentTestListener.logStep(
                "===== ADDRESS FLOW COMPLETED ====="
                        + " | Total Time Taken : "
                        + (endTime - startTime)
                        + " ms"
        );
    }
}