package com.AndroidTest.C5CollectionCenterUpdateQuantityFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.FSaveDONumberPage;
import utilities.StepTimer;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class BPickupQuantityOrdersPage extends BaseClassMobile {

    private final WebDriverWait pageWait =
            new WebDriverWait(driver, Duration.ofSeconds(20));


    // =========================================================
    // LOCATORS
    // =========================================================

    private final By PICKUP_QUANTITY_ORDERS = AppiumBy.xpath
            ("//*[contains(@content-desc,'Pickup Quantity Orders')" +
                    " or contains(@text,'Pickup Quantity Orders')]");

    private final By UPDATE_QUANTITY =
            AppiumBy.accessibilityId("Update Quantity");

    private final By WEIGHT_FIELD =
            AppiumBy.className("android.widget.EditText");

    private final By SUBMIT =
            AppiumBy.accessibilityId("Submit");

    private final By OK_BUTTON =
            AppiumBy.accessibilityId("OK");


    // =========================================================
    // COMPLETE FLOW
    // =========================================================

    public void clickPickupQuantityOrders() {

        try {

            // =================================================
            // STEP 1 - Wait after Login
            // =================================================

            ExtentTestListener.logStep("Waiting for Collection Center dashboard...");

            pageWait.until(driver ->
                    driver.findElements(PICKUP_QUANTITY_ORDERS).size() > 0
                            ||
                            driver.findElements(AppiumBy.accessibilityId("Pickup Quantity Orders")).size() > 0);

            ExtentTestListener.logStep("Collection Center dashboard loaded successfully");


            // =================================================
            // STEP 2 - Click Pickup Quantity Orders
            // =================================================

            ExtentTestListener.logStep("Clicking Pickup Quantity Orders...");

            StepTimer.startStep();

            WebElement pickupQuantityOrders = findPickupQuantityOrders();

            pickupQuantityOrders.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Pickup Quantity Orders clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =================================================
            // STEP 3 - Get Saved DO Number
            // =================================================

            ExtentTestListener.logStep("Getting Saved Delivery Order Number...");

            StepTimer.startStep();

            String savedDONumber =
                    FSaveDONumberPage.getDONumber();

            if (savedDONumber == null ||
                    savedDONumber.trim().isEmpty()) {

                StepTimer.endStep();

                throw new RuntimeException("Saved DO Number is null or empty.");
            }

            savedDONumber = savedDONumber.trim();

            ExtentTestListener.logStep("Saved DO Number : " + savedDONumber);


            // =================================================
            // STEP 4 - Click Saved Delivery Order
            // =================================================

            ExtentTestListener.logStep("Clicking Saved Delivery Order...");

            StepTimer.startStep();

            By savedDO =
                    AppiumBy.xpath("//*[contains(@content-desc,'" + savedDONumber
                    + "') "
                    + "or contains(@text,'"
                    + savedDONumber
                    + "')]");

            WebElement order =
                    wait.until(ExpectedConditions.elementToBeClickable(savedDO));

            order.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Saved Delivery Order Clicked Successfully : "
                            + savedDONumber
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =================================================
            // STEP 5 - Click Update Quantity
            // =================================================

            ExtentTestListener.logStep("Clicking Update Quantity...");

            StepTimer.startStep();

            WebElement updateQuantity =
                    wait.until(ExpectedConditions.elementToBeClickable(UPDATE_QUANTITY));

            updateQuantity.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Update Quantity clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =================================================
            // STEP 6 - Enter KG
            // =================================================

            ExtentTestListener.logStep("Entering Quantity : 10 KG...");

            StepTimer.startStep();

            WebElement weightField =
                    wait.until(ExpectedConditions.elementToBeClickable(WEIGHT_FIELD));

            weightField.click();
            weightField.clear();
            weightField.sendKeys("10");

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Weight entered : 10 KG | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =================================================
            // STEP 7 - Hide Keyboard
            //=================================================
            try {
                driver.hideKeyboard();
            }
            catch (Exception ignored) {
            }


            // =================================================
            // STEP 8 - Click Submit
            // =================================================

            ExtentTestListener.logStep("Clicking Submit...");

            StepTimer.startStep();

            WebElement submitButton =
                    wait.until(ExpectedConditions.elementToBeClickable(SUBMIT));

            submitButton.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Submit clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =================================================
            // STEP 9 - Click OK
            // =================================================

            ExtentTestListener.logStep("Waiting for OK Popup...");

            StepTimer.startStep();

            WebElement okButton =
                    wait.until(ExpectedConditions.elementToBeClickable(OK_BUTTON));
            okButton.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("OK Popup clicked successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =================================================
            // FLOW COMPLETED
            // =================================================

            ExtentTestListener.logStep("Collection Center Quantity Updated Successfully");
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep
                    ("Collection Center Quantity Update Failed : " + e.getMessage());

            throw new RuntimeException("Collection Center Quantity Update Flow Failed", e);
        }
    }


    // =========================================================
    // FIND PICKUP QUANTITY ORDERS
    // =========================================================

    private WebElement findPickupQuantityOrders() {

        // -----------------------------------------------------
        // First try normal locator
        // -----------------------------------------------------

        try {

            WebElement element = new WebDriverWait
                            (driver, Duration.ofSeconds(5)).until
                    (ExpectedConditions.elementToBeClickable(PICKUP_QUANTITY_ORDERS));

            if (element.isDisplayed()) {
                return element;
            }
        }
        catch (Exception ignored) {
        }


        // -----------------------------------------------------
        // Second try accessibilityId
        // -----------------------------------------------------

        try {

            WebElement element =
                    new WebDriverWait(driver, Duration.ofSeconds(5)).until
                            (ExpectedConditions.elementToBeClickable
                                    (AppiumBy.accessibilityId("Pickup Quantity Orders")));

            if (element.isDisplayed()) {
                return element;
            }
        }
        catch (Exception ignored) {
        }


        // -----------------------------------------------------
        // Third - scroll down and search
        // -----------------------------------------------------

        ExtentTestListener.logStep("Pickup Quantity Orders not currently visible. Scrolling...");

        for (int attempt = 1; attempt <= 5; attempt++) {

            try {

                ExtentTestListener.logStep
                        ("Scrolling to Pickup Quantity Orders : attempt " + attempt);

                WebElement element = findVisiblePickupQuantityOrders();

                if (element != null) {
                    return element;
                }
                swipeUp();
            }
            catch (Exception e)
            {
                ExtentTestListener.logStep("Scroll attempt "
                                + attempt
                                + " failed : "
                                + e.getMessage());
            }
        }


        throw new RuntimeException("Pickup Quantity Orders was NOT found after scrolling.");
    }


    // =========================================================
    // FIND VISIBLE PICKUP QUANTITY ORDERS
    // =========================================================

    private WebElement findVisiblePickupQuantityOrders() {

        var elements = driver.findElements(PICKUP_QUANTITY_ORDERS);

        for (WebElement element : elements) {

            try {
                if (element.isDisplayed()) {
                    return element;
                }
            }
            catch (Exception ignored) {
            }
        }
        return null;
    }


    // =========================================================
    // SWIPE UP
    // =========================================================

    private void swipeUp() {

        Dimension screen = driver.manage()
                .window()
                .getSize();

        int width = screen.getWidth();
        int height = screen.getHeight();

        driver.executeScript("mobile: swipeGesture",
                Map.of("left", 0,
                        "top", 100,
                        "width", width,
                        "height", height - 200,
                        "direction", "up",
                        "percent", 0.70));
    }
}