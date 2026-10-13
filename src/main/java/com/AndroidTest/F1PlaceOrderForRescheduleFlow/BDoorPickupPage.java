package com.AndroidTest.F1PlaceOrderForRescheduleFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BDoorPickupPage extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(30));

    // =========================================================
    // LOCATORS
    // =========================================================
    private final By doorstepPickup = AppiumBy.xpath(
            "//android.widget.ImageView[@content-desc='Doorstep Pickup\n" +
                    "We come to your place to collect your items']"
    );


    // Material Checkbox
    private final By materialCheckBox =
            AppiumBy.className("android.widget.CheckBox");

    // Continue
    private final By continueButton =
            AppiumBy.accessibilityId("Continue");


    // =========================================================
    // MAIN FLOW
    // =========================================================

    public void selectDoorstepPickupFlow() {

        System.out.println("==============================================");
        System.out.println("Doorstep Pickup Flow Started");
        System.out.println("==============================================");

        // DOORSTEP PICKUP
        WebElement doorstepPickupButton = wait.until(
                ExpectedConditions.elementToBeClickable(doorstepPickup)
        );

        doorstepPickupButton.click();

        ExtentTestListener.logStep(
                "Doorstep Pickup clicked successfully"
        );

        System.out.println("Doorstep Pickup clicked successfully");


        // 2. Select Material Checkbox
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        materialCheckBox
                )
        );

        checkbox.click();

        ExtentTestListener.logStep("Checkbox Selected");


        // 3. Click Continue
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueButton
                )
        );

        continueBtn.click();

        ExtentTestListener.logStep("Continue clicked");


        // 2.1 Select Material Checkbox (Optional)
        List<WebElement> checkboxes = driver.findElements(materialCheckBox);

        if (!checkboxes.isEmpty() && checkboxes.get(0).isDisplayed()) {

            checkboxes.get(0).click();

            ExtentTestListener.logStep("Checkbox Selected");

        } else {

            ExtentTestListener.logStep("Checkbox not visible. Skipping.");
        }


        // 3.1 Click Continue (Optional)
        List<WebElement> continueButtons = driver.findElements(continueButton);

        if (!continueButtons.isEmpty() && continueButtons.get(0).isDisplayed()) {

            continueButtons.get(0).click();

            ExtentTestListener.logStep("Continue clicked");
        }
        else
        {
            ExtentTestListener.logStep("Continue button not visible. Skipping.");
        }


        System.out.println("==============================================");
        ExtentTestListener.logStep("Doorstep Pickup Flow Completed");
        System.out.println("==============================================");
    }

}
