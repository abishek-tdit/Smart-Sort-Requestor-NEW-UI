package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import utilities.StepTimer;

import java.util.List;

public class G2UploadPackedMaterialsImagePage extends BaseClassMobile {

    // =========================================================
    // Enter Weight - OPTIONAL
    // =========================================================

    public void enterWeight() {

        scrollDown();
        StepTimer.startStep();

        try {

            List<WebElement> weightFields =
                    driver.findElements(
                            AppiumBy.className("android.widget.EditText"));

            // =================================================
            // WEIGHT FIELD NOT AVAILABLE
            // =================================================

            if (weightFields.isEmpty()
                    || !weightFields.get(0).isDisplayed()) {

                double duration = StepTimer.endStep();

                ExtentTestListener.logStep(
                        "Weight field not visible - Skipping | Time Taken : "
                                + String.format("%.2f", duration)
                                + " sec");

                return;
            }

            // =================================================
            // WEIGHT FIELD AVAILABLE
            // =================================================

            WebElement weightField = weightFields.get(0);

            System.out.println("Weight field visible - Entering weight");

            weightField.click();

            weightField.clear();

            weightField.sendKeys("4");

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Weight entered : 4 KG | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            // =================================================
            // WAIT FOR MANUAL PHOTO UPLOAD
            // =================================================

            ExtentTestListener.logStep(
                    "Waiting 10 seconds for manual photo upload...");

            Thread.sleep(10000);

            ExtentTestListener.logStep(
                    "10 seconds completed - Continuing automation");

        }
        catch (Exception e) {

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Weight / manual photo upload not available - Skipping | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");
        }
    }
}
