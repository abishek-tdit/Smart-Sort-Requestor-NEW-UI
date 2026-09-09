package com.AndroidTest.C5CollectionCenterUpdateQuantityFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.FSaveDONumberPage;
import utilities.StepTimer;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BPickupQuantityOrdersPage extends BaseClassMobile {

    public void clickPickupQuantityOrders() throws InterruptedException {

        try {

            // Click Pickup Quantity Orders
            ExtentTestListener.logStep("Clicking Pickup Quantity Orders...");

            StepTimer.startStep();

            WebElement pickupQuantityOrders = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
                                    "new UiSelector().descriptionContains(\"Pickup Quantity Orders\")")));

            pickupQuantityOrders.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Pickup Quantity Orders clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // Click Saved Delivery Order
            ExtentTestListener.logStep("Clicking Saved Delivery Order...");

            StepTimer.startStep();

            String savedDONumber = FSaveDONumberPage.getDONumber();

            if (savedDONumber == null || savedDONumber.trim().isEmpty()) {

                StepTimer.endStep();

                ExtentTestListener.logStep("Saved DO Number is not available");

                throw new RuntimeException("Saved DO Number is null or empty. Cannot click Delivery Order.");
            }

            ExtentTestListener.logStep("Saved DO Number : " + savedDONumber);

            String savedDOXpath = "//android.view.View[contains(@content-desc,\""
                            + savedDONumber
                            + "\")]";

            WebElement order = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath(savedDOXpath)));

            order.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Saved Delivery Order Clicked Successfully : "
                            + savedDONumber
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // Click Update Quantity
            ExtentTestListener.logStep("Clicking Update Quantity...");

            StepTimer.startStep();

            WebElement updateQuantity = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Update Quantity")));

            updateQuantity.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Update Quantity clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // Click KG Textbox
            StepTimer.startStep();

            WebElement weightField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            AppiumBy.className("android.widget.EditText")));

            weightField.click();
            Thread.sleep(800);
            weightField.clear();
            weightField.sendKeys("10");

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Weight entered: 10 KG | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            // Hide Keyboard
            try {
                driver.hideKeyboard();
            }
            catch (Exception ignored) {
            }


            // Click Submit
            ExtentTestListener.logStep("Clicking Submit...");

            StepTimer.startStep();

            WebElement submitButton = wait.until
                    (ExpectedConditions.elementToBeClickable
                            (AppiumBy.accessibilityId("Submit")));

            submitButton.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Submit clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // Click OK Popup
            ExtentTestListener.logStep("Clicking OK Popup...");

            StepTimer.startStep();

            WebElement okButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("OK")));

            okButton.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("OK Popup clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            ExtentTestListener.logStep("Quantity updated successfully.");

        }
        catch (Exception e) {

            ExtentTestListener.logStep("Error while clicking Pickup Quantity Orders: "
                            + e.getMessage());

            throw e;
        }
    }
}