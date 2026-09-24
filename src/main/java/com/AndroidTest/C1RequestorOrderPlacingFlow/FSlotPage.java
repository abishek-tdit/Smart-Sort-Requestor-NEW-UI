package com.AndroidTest.C1RequestorOrderPlacingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.StepTimer;

import java.time.Duration;
import java.util.List;

public class FSlotPage extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(20));

    // =========================================================
    // MATERIAL CATEGORY
    // =========================================================
    private final By mixedMaterials = AppiumBy.accessibilityId("Mixed-Materials");

    private final By plastic = AppiumBy.accessibilityId("Plastic");

    private final By battery = AppiumBy.accessibilityId("Battery");


    // =========================================================
    // AVAILABLE SLOT
    // =========================================================
    private final By availableSlots = AppiumBy.xpath
            ("//android.widget.ScrollView" +
                    "//android.view.View[" +
                    "contains(@content-desc,'AM') or " +
                    "contains(@content-desc,'PM')" +
                    "]");


    // =========================================================
    // PICKUP INSTRUCTIONS
    // =========================================================
    private final By PICKUP_INSTRUCTIONS = AppiumBy.xpath(
            "//android.widget.ImageView[" +
                    "contains(@content-desc,'Pickup Instructions')" +
                    " and " +
                    "contains(@content-desc,'Add any specific instructions for the Collector')" +
                    "]");


    // =========================================================
    // WAIT OUTSIDE
    // =========================================================
    private final By waitOutside = AppiumBy.accessibilityId("Wait outside");


    // =========================================================
    // NOTE
    // =========================================================
    private final By noteTextBox =
            AppiumBy.className("android.widget.EditText");


    // =========================================================
    // RECORDING
    // =========================================================
    private final By startRecording =
            AppiumBy.accessibilityId("Start recording");

    private final By recordingInProgress =
            AppiumBy.accessibilityId("Recording in progress...");


    // =========================================================
    // SAVE
    // =========================================================
    private final By saveButton = AppiumBy.accessibilityId("Save");


    // =========================================================
    // CONFIRM ORDER
    // =========================================================
    private final By confirmOrder =
            AppiumBy.accessibilityId("Confirm Order");


    // =========================================================
    // OK
    // =========================================================
    private final By okButton = AppiumBy.accessibilityId("OK");


    // =========================================================
    // SELECT MATERIAL CATEGORY
    // =========================================================
    public void selectMaterialCategory() {

        System.out.println("==============================================");
        ExtentTestListener.logStep("Waiting for Material Category...");
        System.out.println("==============================================");

        // -----------------------------------------------------
        // 1. MIXED-MATERIALS
        // -----------------------------------------------------
        WebElement material =
                wait.until(ExpectedConditions.elementToBeClickable(mixedMaterials));

        System.out.println("Mixed-Materials found");

        material.click();

        ExtentTestListener.logStep("PASS : Mixed-Materials selected successfully");


        // -----------------------------------------------------
        // 2. PLASTIC - CLICK ONLY IF VISIBLE
        // -----------------------------------------------------
        List<WebElement> plasticElements = driver.findElements(plastic);

        if (!plasticElements.isEmpty()
                && plasticElements.get(0).isDisplayed()
                && plasticElements.get(0).isEnabled()) {

            System.out.println("Plastic is visible - selecting...");

            plasticElements.get(0).click();

            System.out.println("PASS : Plastic selected successfully");
        }
        else
        {
            System.out.println("SKIP : Plastic is not visible");
        }

        // -----------------------------------------------------
        // 3. BATTERY - CLICK ONLY IF VISIBLE
        // -----------------------------------------------------
        List<WebElement> batteryElements = driver.findElements(battery);

        if (!batteryElements.isEmpty()
                && batteryElements.get(0).isDisplayed()
                && batteryElements.get(0).isEnabled()) {

            System.out.println("Battery is visible - selecting...");

            batteryElements.get(0).click();

            System.out.println("PASS : Battery selected successfully");
        }
        else
        {
            System.out.println("SKIP : Battery is not visible");
        }
        System.out.println("PASS : Material category selection completed");
    }


    // =========================================================
    // CLICK FIRST AVAILABLE SLOT
    // =========================================================
    public void clickFirstAvailableSlot() {

        System.out.println("==============================================");
        ExtentTestListener.logStep("Searching for First Available Slot...");
        System.out.println("==============================================");

        scrollDown();

        for (int attempt = 1; attempt <= 5; attempt++) {

            List<WebElement> slots = driver.findElements(availableSlots);

            System.out.println("Attempt " + attempt +
                            " | Slot Elements Found : " +
                            slots.size());

            for (WebElement slot : slots) {
                try {
                    if (!slot.isDisplayed() || !slot.isEnabled()) {
                        continue;
                    }

                    String slotText = slot.getAttribute("content-desc");

                    if (slotText == null || slotText.trim().isEmpty())
                    {
                        continue;
                    }

                    slotText = slotText.trim();

                    // Make sure this is really a time slot
                    if (!slotText.matches(
                            ".*\\d{1,2}:\\d{2}.*(AM|PM).*"
                    )) {
                        continue;
                    }

                    System.out.println("Available Slot Found : " + slotText);

                    wait.until(driver ->
                            slot.isDisplayed() && slot.isEnabled());

                    System.out.println("Clicking Slot : " + slotText);

                    slot.click();

                    System.out.println("PASS : Slot clicked successfully : " + slotText);

                    return;

                } catch (Exception e) {

                    System.out.println("Unable to click slot : " + e.getMessage());
                }
            }


            // -------------------------------------------------
            // SCROLL ONLY IF NO SLOT FOUND
            // -------------------------------------------------
            if (attempt < 5) {

                System.out.println("No usable slot on current screen.");

                scrollDown();

                System.out.println("Scrolled down - Attempt " + attempt);
            }
        }

        throw new RuntimeException("ERROR : No available AM/PM slot was found");
    }


    // =========================================================
    // PICKUP INSTRUCTIONS
    // =========================================================
    public void enterPickupInstructions() throws InterruptedException {

        System.out.println("==============================================");
        ExtentTestListener.logStep("Pickup Instructions Flow Started");
        System.out.println("==============================================");


        // -----------------------------------------------------
        // 1. PICKUP INSTRUCTIONS
        // -----------------------------------------------------
        System.out.println("Waiting for Pickup Instructions...");

        WebElement pickupButton =
                wait.until(ExpectedConditions.elementToBeClickable(PICKUP_INSTRUCTIONS));

        System.out.println("Pickup Instructions found");

        pickupButton.click();

        ExtentTestListener.logStep("PASS : Pickup Instructions clicked");


        // -----------------------------------------------------
        // 2. WAIT OUTSIDE
        // -----------------------------------------------------
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


        // -----------------------------------------------------
        // 4. HIDE KEYBOARD
        // -----------------------------------------------------
        try {

            if (driver.isKeyboardShown()) {

                driver.pressKey(new KeyEvent(AndroidKey.BACK));

                System.out.println("Keyboard hidden successfully");
            }
            else
            {
                System.out.println("Keyboard is already hidden");
            }

        } catch (Exception e) {

            System.out.println("Unable to hide keyboard: " +
                            e.getMessage());
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

        WebElement recording =
                wait.until(ExpectedConditions.visibilityOfElementLocated(recordingInProgress));

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

        System.out.println("Waiting for Confirm Order...");
        Thread.sleep(4000);
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

        System.out.println("Waiting for OK popup...");

        WebElement ok =
                wait.until(ExpectedConditions.elementToBeClickable(okButton));

        System.out.println("OK popup found");

        ok.click();

        ExtentTestListener.logStep("PASS : OK popup clicked");
    }


    // =========================================================
    // COMPLETE SLOT FLOW
    // =========================================================
    public void selectSlotConfirmAndOK() throws InterruptedException {

        System.out.println("==============================================");
        ExtentTestListener.logStep("Slot Selection Flow Started");
        System.out.println("==============================================");

        selectMaterialCategory();
        clickFirstAvailableSlot();
        enterPickupInstructions();
        clickConfirmOrder();
        clickOKPopup();

        System.out.println("==============================================");
        ExtentTestListener.logStep("Slot Selection Flow Completed");
        System.out.println("==============================================");
    }
}
