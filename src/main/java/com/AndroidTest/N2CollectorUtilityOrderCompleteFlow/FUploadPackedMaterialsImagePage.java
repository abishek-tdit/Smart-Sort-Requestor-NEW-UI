package com.AndroidTest.N2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.android.AndroidDriver;
import utilities.StepTimer;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FUploadPackedMaterialsImagePage extends BaseClassMobile {

    private static final String UTILITY_PACKAGE =
            "com.smartsort.utilities";

    public void uploadImage() throws InterruptedException {

        // =========================================================
        // Enter Weight
        // =========================================================

        StepTimer.startStep();

        WebElement weightField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.className("android.widget.EditText")
                )
        );

        weightField.click();

        Thread.sleep(800);

        weightField.clear();
        weightField.sendKeys("15");

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Weight entered: 15 KG | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =========================================================
        // Hide Keyboard
        // =========================================================

        StepTimer.startStep();

        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Keyboard Hidden | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =========================================================
        // Wait Before Camera / Video
        // =========================================================

        StepTimer.startStep();

        Thread.sleep(2000);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Ready for Camera / Video | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =========================================================
        // Manual Camera / Video
        // =========================================================

        StepTimer.startStep();

        ExtentTestListener.logStep(
                "Please manually take photo/video and return to SS Utility..."
        );

        /*
         * Camera / video is outside the SmartSort Utility UI.
         *
         * Keep this wait because the user is manually recording.
         */

        Thread.sleep(20000);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Manual Camera / Video Wait Completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =========================================================
        // Recover SmartSort Utility After Camera / Video
        // =========================================================

        boolean sessionRestarted = recoverUtilitySession();


        // =========================================================
        // Select Reason - OPTIONAL
        // =========================================================

        StepTimer.startStep();

        try {

            List<WebElement> reasonElements =
                    driver.findElements(
                            AppiumBy.accessibilityId("Select Reason")
                    );

            if (!reasonElements.isEmpty()
                    && reasonElements.get(0).isDisplayed()) {

                reasonElements.get(0).click();

                ExtentTestListener.logStep(
                        "Select Reason Clicked"
                );

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                AppiumBy.accessibilityId(
                                        "Technical issue"
                                )
                        )
                ).click();

                ExtentTestListener.logStep(
                        "Reason Selected : Technical issue"
                );

                scrollDown();

            } else {

                ExtentTestListener.logStep(
                        "Select Reason not visible - Skipping"
                );
            }

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Select Reason not available - Continuing to Submit"
            );
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Reason Selection Completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =========================================================
        // Click Submit
        // =========================================================

        StepTimer.startStep();

        try {

            WebElement submit = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Submit")
                    )
            );

            submit.click();

            duration = StepTimer.endStep();

            if (sessionRestarted) {

                ExtentTestListener.logStep(
                        "Submit Clicked After Utility Session Recovery | Time Taken : "
                                + String.format("%.2f", duration)
                                + " sec"
                );

            } else {

                ExtentTestListener.logStep(
                        "Submit Clicked | Time Taken : "
                                + String.format("%.2f", duration)
                                + " sec"
                );
            }

        } catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Submit button not available | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );

            throw new RuntimeException(
                    "Submit button not found after camera/video recovery",
                    e
            );
        }


        // =========================================================
        // Wait For Go To Dashboard
        // =========================================================

        StepTimer.startStep();

        WebElement dashboard = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId(
                                "Go to Dashboard"
                        )
                )
        );

        dashboard.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Dashboard Clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );
    }


    // =============================================================
    // Recover SmartSort Utility After Camera / Video
    // =============================================================

    private boolean recoverUtilitySession() {

        StepTimer.startStep();

        boolean sessionRestarted = false;

        try {

            ExtentTestListener.logStep(
                    "Checking SmartSort Utility after Camera / Video"
            );

            String currentPackage =
                    driver.getCurrentPackage();

            ExtentTestListener.logStep(
                    "Current Package : " + currentPackage
            );


            // =====================================================
            // Camera still open / Utility not foreground
            // =====================================================

            if (!UTILITY_PACKAGE.equals(currentPackage)) {

                ExtentTestListener.logStep(
                        "Camera / external screen detected"
                );

                try {

                    driver.activateApp(
                            UTILITY_PACKAGE
                    );

                    ExtentTestListener.logStep(
                            "SmartSort Utility activated"
                    );

                } catch (Exception e) {

                    ExtentTestListener.logStep(
                            "Unable to activate SmartSort Utility"
                    );
                }
            }


            // =====================================================
            // Verify UiAutomator2 Session
            // =====================================================

            try {

                driver.getPageSource();

                ExtentTestListener.logStep(
                        "UiAutomator2 session is alive"
                );

            } catch (Exception sessionException) {

                ExtentTestListener.logStep(
                        "UiAutomator2 session is not responding"
                );

                // =================================================
                // Restart ONLY Utility Session
                // =================================================

                try {

                    BaseClassMobile.restartUtilitySession();

                    sessionRestarted = true;

                    ExtentTestListener.logStep(
                            "SmartSort Utility session restarted successfully"
                    );

                } catch (Exception restartException) {

                    throw new RuntimeException(
                            "Unable to restart SmartSort Utility session",
                            restartException
                    );
                }
            }



            // Wait Until Utility App Is Back
            wait.until(driver -> {

                try {
                    return UTILITY_PACKAGE.equals(
                            ((AndroidDriver) driver).getCurrentPackage()
                    );

                } catch (Exception e) {
                    return false;
                }
            });

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Utility recovery failed : "
                            + e.getMessage()
            );

            throw new RuntimeException(
                    "Unable to recover SmartSort Utility after Camera / Video",
                    e
            );

        } finally {

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Utility Recovery Completed | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );
        }

        return sessionRestarted;
    }
}