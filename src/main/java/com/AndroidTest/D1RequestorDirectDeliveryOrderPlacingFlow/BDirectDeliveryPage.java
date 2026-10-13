package com.AndroidTest.D1RequestorDirectDeliveryOrderPlacingFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utilities.StepTimer;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

public class BDirectDeliveryPage extends Base.BaseClassMobile {

    // =====================================================
    // LOCATORS
    // =====================================================
    private final By directDelivery =
            AppiumBy.accessibilityId("Direct Delivery");

    private final By directDeliveryOld =
            AppiumBy.accessibilityId(
                    "Direct Delivery\nDeliver your items directly\nLearn More"
            );

    private final By startDirectDelivery =
            AppiumBy.accessibilityId("Start Direct Delivery");

    private final By TTFVaasan =
            AppiumBy.xpath(
                    "//android.widget.ImageView[starts-with(@content-desc,'TTFVaasan ')]"
            );

    private final By checkbox =
            AppiumBy.className("android.widget.CheckBox");


    // =====================================================
    // HORIZONTAL SWIPE - DELIVERY METHOD
    // =====================================================

    private void swipeDeliveryMethodLeft() {

        System.out.println("Starting horizontal swipe...");


        PointerInput finger =
                new PointerInput(
                        PointerInput.Kind.TOUCH,
                        "finger"
                );

        Sequence swipe =
                new Sequence(finger, 0);

        // -------------------------------------------------
        // Move finger to starting point
        // -------------------------------------------------

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        450,
                        1050
                )
        );

        // -------------------------------------------------
        // Finger DOWN
        // -------------------------------------------------

        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        // -------------------------------------------------
        // Swipe LEFT
        // -------------------------------------------------

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(800),
                        PointerInput.Origin.viewport(),
                        100,
                        1050
                )
        );

        // -------------------------------------------------
        // Finger UP
        // -------------------------------------------------

        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        driver.perform(
                Collections.singletonList(swipe)
        );

        System.out.println("Horizontal swipe completed");
    }


    // =====================================================
    // FIND DIRECT DELIVERY
    // =====================================================

    private WebElement findDirectDelivery() {


        try {

            System.out.println(
                    "Checking Direct Delivery using current locator..."
            );

            return waitUntilClickable(
                    directDelivery,
                    5
            );

        } catch (Exception e) {

            System.out.println(
                    "Current Direct Delivery locator not found."
            );
        }


        /*
         * Fallback for older application version.
         */

        try {

            System.out.println(
                    "Checking Direct Delivery using old locator..."
            );

            return waitUntilClickable(
                    directDeliveryOld,
                    5
            );

        } catch (Exception e) {

            System.out.println(
                    "Old Direct Delivery locator also not found."
            );
        }

        return null;
    }


    // =====================================================
    // CLICK DIRECT DELIVERY
    // =====================================================

    private void clickDirectDelivery() {

        WebElement directDeliveryElement =
                findDirectDelivery();

        /*
         * If not visible after first search,
         * perform horizontal swipe and search again.
         */

        if (directDeliveryElement == null) {

            System.out.println(
                    "Direct Delivery not visible."
            );

            System.out.println(
                    "Swiping Delivery Method carousel..."
            );

            swipeDeliveryMethodLeft();

            /*
             * Search again after swipe.
             */

            directDeliveryElement =
                    findDirectDelivery();
        }


        /*
         * Final safety check.
         */

        if (directDeliveryElement == null) {

            throw new RuntimeException(
                    "Direct Delivery was not found even after horizontal swipe."
            );
        }


        System.out.println(
                "Direct Delivery found successfully."
        );


        /*
         * Try Appium clickGesture first.
         * Normal click is used as fallback.
         */

        try {

            driver.executeScript(
                    "mobile: clickGesture",
                    Map.of(
                            "elementId",
                            ((RemoteWebElement) directDeliveryElement)
                                    .getId()
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "clickGesture failed. Using normal click..."
            );

            directDeliveryElement.click();
        }


        System.out.println(
                "Direct Delivery clicked successfully."
        );
    }


    // =====================================================
    // DIRECT DELIVERY FLOW
    // =====================================================

    public void selectDirectDelivery() throws InterruptedException {

        double duration;


        // =================================================
        // STEP 1 - DIRECT DELIVERY
        // =================================================

        StepTimer.startStep();

        System.out.println(
                "Selecting Direct Delivery..."
        );

        clickDirectDelivery();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Direct Delivery Clicked Successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =================================================
        // STEP 2 - START DIRECT DELIVERY
        // =================================================

        StepTimer.startStep();

        System.out.println(
                "Waiting for Start Direct Delivery..."
        );

        WebElement startDeliveryElement =
                waitUntilClickable(
                        startDirectDelivery,
                        15
                );

        System.out.println(
                "Start Direct Delivery found."
        );

        try {

            driver.executeScript(
                    "mobile: clickGesture",
                    Map.of(
                            "elementId",
                            ((RemoteWebElement) startDeliveryElement)
                                    .getId()
                    )
            );

        } catch (Exception e) {

            startDeliveryElement.click();
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Start Direct Delivery Clicked Successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =================================================
        // STEP 3 - SELECT TTF VAASAN
        // =================================================
        StepTimer.startStep();

        System.out.println("Waiting for TTF Collection Center...");

        // Dynamic TTFVaasan locator
        WebElement ttfElement = waitUntilClickable(TTFVaasan, 15);

        System.out.println("TTF Collection Center found.");

        // Click TTFVaasan
        try {

            driver.executeScript("mobile: clickGesture",
                    Map.of("elementId", ((RemoteWebElement) ttfElement).getId()));
        }
        catch (Exception e)
        {
            System.out.println("clickGesture failed. Using normal click...");
            ttfElement.click();
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("TTFVaasan Collection Center Selected Successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =================================================
        // STEP 4 - CHECKBOX
        // =================================================

        StepTimer.startStep();

        System.out.println(
                "Waiting for checkbox..."
        );

        WebElement checkboxElement =
                waitUntilClickable(
                        checkbox,
                        15
                );

        System.out.println(
                "Checkbox found."
        );

        try {

            driver.executeScript(
                    "mobile: clickGesture",
                    Map.of(
                            "elementId",
                            ((RemoteWebElement) checkboxElement)
                                    .getId()
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "clickGesture failed. Using normal click..."
            );

            checkboxElement.click();
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Checkbox Clicked Successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =================================================
        // COMPLETED
        // =================================================

        ExtentTestListener.getTest().info(
                "Direct Delivery Collection Center Selection Completed Successfully"
        );

        System.out.println(
                "Direct Delivery Collection Center Selection Completed Successfully"
        );
    }
}