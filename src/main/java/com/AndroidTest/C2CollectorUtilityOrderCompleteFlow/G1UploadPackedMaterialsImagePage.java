package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.StepTimer;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class G1UploadPackedMaterialsImagePage extends BaseClassMobile {

    public void uploadImage() throws InterruptedException {

        // =========================================================
        // Enter Weight
        // =========================================================

        StepTimer.startStep();

        WebElement weightField = wait.until
                (ExpectedConditions.presenceOfElementLocated
                        (AppiumBy.className("android.widget.EditText")));

        weightField.click();

        Thread.sleep(800);

        weightField.clear();
        weightField.sendKeys("5");

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep("Weight entered: 10 KG | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // Hide Keyboard
        StepTimer.startStep();
        try {
            driver.hideKeyboard();
        }
        catch (Exception ignored) {
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Keyboard Hidden | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        StepTimer.startStep();

        Thread.sleep(2000);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Wait After Weight Entry | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // Manual Photo
        // =========================================================

        StepTimer.startStep();

        ExtentTestListener.logStep("Please manually take photo and return to app...");

        Thread.sleep(20000);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Manual Photo Wait Completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =========================================================
        // Check Appium Session
        // =========================================================

        boolean sessionRestarted = false;

        StepTimer.startStep();

        try {

            String currentPackage = driver.getCurrentPackage();

            ExtentTestListener.logStep("Current Package = " + currentPackage);

            if (!"com.smartsort.utilities".equals(currentPackage)) {

                ExtentTestListener.logStep
                        ("Wrong application detected. Expected SS Utility but found : " + currentPackage);
                try {
                    driver.activateApp("com.smartsort.utilities");

                    ExtentTestListener.logStep("SS Utility app activated successfully");
                }
                catch (Exception activateException)
                {
                    ExtentTestListener.logStep("Unable to activate SS Utility app");
                }
            }

            driver.getPageSource();

            ExtentTestListener.logStep("UiAutomator2 session is alive");
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("UiAutomator2 session crashed");

            try {

                BaseClassMobile.restartUtilitySession();

                sessionRestarted = true;

                ExtentTestListener.logStep("SS Utility Appium session restarted successfully");
            }
            catch (Exception restartException)
            {
                ExtentTestListener.logStep("SS Utility Appium session restart failed");
                throw new RuntimeException
                        ("Unable to restart SS Utility Appium session", restartException);
            }
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Appium Session Check Completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Appium Session Check Completed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

    }
}