package com.AndroidTest.C1RequestorOrderPlacingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.StepTimer;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class FSlotPage extends BaseClassMobile {

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    // =========================================================
    // LOCATORS
    // =========================================================
    private final By mixedMaterials = AppiumBy.accessibilityId("Mixed-Materials");

//    private final By textile = AppiumBy.accessibilityId("Textile");
//
//    private final By battery = AppiumBy.accessibilityId("Battery");

    private final By slotTiming = AppiumBy.xpath("//android.widget.ImageView["
                    + "contains(@content-desc,'08:00')" + " and "
                    + "contains(@content-desc,'11:45')" + "]" );

    private final By PICKUP_INSTRUCTIONS = AppiumBy.xpath("//android.widget.ImageView["
            + "contains(@content-desc,'Pickup Instructions')"
            + " and "
            + "contains(@content-desc,'Add any specific instructions for the Collector')" + "]");
    private final By waitOutside = AppiumBy.accessibilityId("Wait outside");
    private final By noteTextBox = AppiumBy.className("android.widget.EditText");
    private final By startRecording = AppiumBy.accessibilityId("Start recording");
    private final By recordingInProgress = AppiumBy.accessibilityId("Recording in progress...");
    private final By saveButton = AppiumBy.accessibilityId("Save");
    private final By confirmOrder = AppiumBy.accessibilityId("Confirm Order");
    private final By okButton = AppiumBy.accessibilityId("OK");



    // SELECT MATERIAL CATEGORY
    public void selectMaterialCategory() {

        System.out.println("==============================================");
        ExtentTestListener.logStep("Waiting for Material Category...");
        System.out.println("==============================================");


        // 1. MIXED-MATERIALS
        WebElement material =
                wait.until(ExpectedConditions.elementToBeClickable(mixedMaterials));

        System.out.println("Mixed-Materials found");

        material.click();

        ExtentTestListener.logStep("PASS : Mixed-Materials selected successfully");

//        // 2. TEXTILE - CLICK ONLY IF VISIBLE
//        List<WebElement> textileElements = driver.findElements(textile);
//
//        if (!textileElements.isEmpty()
//                && textileElements.get(0).isDisplayed()
//                && textileElements.get(0).isEnabled()) {
//
//            System.out.println("Textile is visible - selecting...");
//
//            textileElements.get(0).click();
//
//            ExtentTestListener.logStep("PASS : Textile selected successfully");
//
//        } else {
//
//            System.out.println("SKIP : Textile is not visible");
//
//            ExtentTestListener.logStep("SKIP : Textile is not visible");
//        }
//
//
//        // 3. BATTERY - CLICK ONLY IF VISIBLE
//        List<WebElement> batteryElements = driver.findElements(battery);
//
//        if (!batteryElements.isEmpty()
//                && batteryElements.get(0).isDisplayed()
//                && batteryElements.get(0).isEnabled()) {
//
//            System.out.println("Battery is visible - selecting...");
//
//            batteryElements.get(0).click();
//
//            ExtentTestListener.logStep("PASS : Battery selected successfully");
//
//        } else {
//
//            System.out.println("SKIP : Battery is not visible");
//
//            ExtentTestListener.logStep("SKIP : Battery is not visible");
//        }
//
//
//        System.out.println("PASS : Material category selection completed");
//
//        ExtentTestListener.logStep("PASS : Material category selection completed");

    }
    // =========================================================
    // CLICK SLOT TIMING
    // =========================================================
    public void clickSlotTiming() {
        System.out.println("==============================================");
        ExtentTestListener.logStep("Waiting for Slot Timing...");
        System.out.println("==============================================");
        StepTimer.startStep();
        // -----------------------------------------------------
        // WAIT FOR SLOT
        // -----------------------------------------------------
        System.out.println( "Searching for Slot Timing : 08:00 AM - 11:45 PM" );
        WebElement slot = wait.until
                ( ExpectedConditions.visibilityOfElementLocated( slotTiming ) );

        System.out.println( "Slot Timing found : " + slot.getAttribute("contentDescription") );
        // -----------------------------------------------------
        // VERIFY ELEMENT
        // -----------------------------------------------------
        if (!slot.isDisplayed()) { throw new RuntimeException( "Slot Timing found but not displayed" );
        }
        if (!slot.isEnabled()) { throw new RuntimeException( "Slot Timing found but not enabled" ); }
        // -----------------------------------------------------
        // CLICK SLOT
        // -----------------------------------------------------

        try { driver.executeScript( "mobile: clickGesture",
                Map.of( "elementId", ((RemoteWebElement) slot).getId() ) );
            System.out.println( "Slot Timing clicked using clickGesture" );
        } catch (Exception e) {
            System.out.println( "clickGesture failed. Trying normal click..." );
            slot.click();
            System.out.println( "Slot Timing clicked using normal click" );
        } double duration = StepTimer.endStep();
        // -----------------------------------------------------
        // LOG
        // -----------------------------------------------------
        ExtentTestListener.logStep( "PASS : Slot Timing selected successfully | "
                + "08:00 AM - 11:45 PM | Time Taken : "
                + String.format("%.2f", duration) + " sec" );
        System.out.println( "PASS : Slot Timing clicked successfully" ); }


    // =========================================================
    // PICKUP INSTRUCTIONS
    // =========================================================
    public void enterPickupInstructions() {

        System.out.println("==============================================");
        ExtentTestListener.logStep("Pickup Instructions Flow Started");
        System.out.println("==============================================");



        // 1. PICKUP INSTRUCTIONS
        System.out.println("Waiting for Pickup Instructions...");

        WebElement pickupButton = wait.until
                (ExpectedConditions.elementToBeClickable(PICKUP_INSTRUCTIONS));

        System.out.println("Pickup Instructions found");

        pickupButton.click();

        ExtentTestListener.logStep("PASS : Pickup Instructions clicked");


        // 2. WAIT OUTSIDE
        System.out.println("Waiting for Wait outside...");

        WebElement outside =
                wait.until(ExpectedConditions.elementToBeClickable(waitOutside));

        System.out.println("Wait outside found");

        outside.click();

        ExtentTestListener.logStep("PASS : Wait outside selected");


        // -----------------------------------------------------
        // 3. ENTER NOTE
        // -----------------------------------------------------
        System.out.println("Waiting for Pickup Note...");

        WebElement note =
                wait.until(ExpectedConditions.elementToBeClickable(noteTextBox));

        StepTimer.startStep();

        note.click();
        note.clear();
        note.sendKeys("Beware of dogs");

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep("Pickup Note Entered | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        ExtentTestListener.logStep("PASS : Pickup note entered successfully");

        // 4. HIDE KEYBOARD
        try {

            if (driver.isKeyboardShown()) {
                driver.pressKey(new KeyEvent(AndroidKey.BACK));

                System.out.println("Keyboard hidden successfully");
            }
            else
            {
                System.out.println("Keyboard is already hidden");
            }
        }
        catch (Exception e)
        {
            System.out.println("Unable to hide keyboard: " + e.getMessage());
        }


        // -----------------------------------------------------
        // 5. START RECORDING
        // -----------------------------------------------------
        ExtentTestListener.logStep("Waiting for Start Recording...");

        WebElement start =
                wait.until(ExpectedConditions.elementToBeClickable(startRecording));

        start.click();

        ExtentTestListener.logStep("PASS : Recording started");


        // -----------------------------------------------------
        // 6. WAIT FOR RECORDING IN PROGRESS
        // -----------------------------------------------------
        System.out.println("Waiting for Recording in progress...");

        WebElement recording = wait.until
                (ExpectedConditions.visibilityOfElementLocated(recordingInProgress));

        ExtentTestListener.logStep("Recording in progress");


        // -----------------------------------------------------
        // 7. STOP RECORDING
        // -----------------------------------------------------
        wait.until(driver -> recording.isDisplayed());

        recording.click();

        ExtentTestListener.logStep("PASS : Recording stopped");


        // -----------------------------------------------------
        // 8. SAVE
        // -----------------------------------------------------
        System.out.println("Waiting for Save button...");

        WebElement save =
                wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        System.out.println("Save button is visible and ready");

        save.click();

        ExtentTestListener.logStep("PASS : Pickup Instructions saved");


        System.out.println("==============================================");
        ExtentTestListener.logStep("Pickup Instructions Flow Completed");
        System.out.println("==============================================");
    }


    // =========================================================
    // CONFIRM ORDER
    // =========================================================
    public void clickConfirmOrder() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Waiting for Confirm Order...");

        WebElement confirm =
                wait.until(ExpectedConditions.elementToBeClickable(confirmOrder));

        System.out.println("Confirm Order is visible and ready");

        confirm.click();

        ExtentTestListener.logStep("PASS : Confirm Order clicked");
    }


    // =========================================================
    // OK POPUP
    // =========================================================
    public void clickOKPopup() {

        System.out.println("Checking for OK popup...");

        List<WebElement> okButtons = driver.findElements(okButton);

        if (!okButtons.isEmpty() && okButtons.get(0).isDisplayed()) {

            okButtons.get(0).click();

            ExtentTestListener.logStep("PASS : OK popup clicked");
            System.out.println("OK popup clicked successfully");

        } else {

            ExtentTestListener.logStep("OK popup not visible - skipped");
            System.out.println("OK popup not visible - skipped");
        }
    }


    // =========================================================
    // COMPLETE SLOT FLOW
    // =========================================================
    public void selectSlotConfirmAndOK() throws InterruptedException {

        System.out.println("==============================================");
        ExtentTestListener.logStep("Slot Selection Flow Started");
        System.out.println("==============================================");

        selectMaterialCategory();
        clickSlotTiming();
        enterPickupInstructions();
        clickConfirmOrder();
        clickOKPopup();

        System.out.println("==============================================");
        ExtentTestListener.logStep("Slot Selection Flow Completed");
        System.out.println("==============================================");
    }
}