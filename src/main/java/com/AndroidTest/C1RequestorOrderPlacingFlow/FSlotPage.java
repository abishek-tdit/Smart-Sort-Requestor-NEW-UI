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
    // AVAILABLE SLOT
    // =========================================================
    private final By availableSlots = By.xpath(
            "//*[contains(@content-desc,'AM') or contains(@content-desc,'PM')]"
    );

    // =========================================================
    // PICKUP INSTRUCTIONS
    // =========================================================
    private final By pickupInstructions =
            AppiumBy.accessibilityId("Pickup Instructions");

    // =========================================================
    // WAIT OUTSIDE
    // =========================================================
    private final By waitOutside =
            AppiumBy.accessibilityId("Wait outside");

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
    private final By saveButton =
            AppiumBy.accessibilityId("Save");

    // =========================================================
    // CONFIRM ORDER
    // =========================================================
    private final By confirmOrder =
            AppiumBy.accessibilityId("Confirm Order");

    // =========================================================
    // OK
    // =========================================================
    private final By okButton =
            AppiumBy.accessibilityId("OK");


    // =========================================================
    // CLICK FIRST AVAILABLE SLOT
    // =========================================================
    public void clickFirstAvailableSlot() {

        System.out.println("==============================================");
        System.out.println("Searching for First Available Slot...");
        System.out.println("==============================================");

        for (int attempt = 1; attempt <= 5; attempt++) {

            List<WebElement> slots =
                    driver.findElements(availableSlots);

            System.out.println(
                    "Attempt " + attempt +
                            " | Slot Elements Found : " +
                            slots.size()
            );

            for (WebElement slot : slots) {

                try {

                    if (!slot.isDisplayed() ||
                            !slot.isEnabled()) {
                        continue;
                    }

                    String slotText =
                            slot.getAttribute("content-desc");

                    if (slotText == null ||
                            slotText.trim().isEmpty()) {
                        continue;
                    }

                    if (slotText.contains("AM") ||
                            slotText.contains("PM")) {

                        System.out.println(
                                "First Available Slot Selected : "
                                        + slotText
                        );

                        slot.click();

                        System.out.println(
                                "Slot clicked successfully"
                        );

                        return;
                    }

                } catch (Exception ignored) {
                }
            }

            if (attempt < 5) {

                scrollDown();

                System.out.println(
                        "Scrolled down - Attempt " +
                                attempt
                );
            }
        }

        throw new RuntimeException(
                "ERROR : No available AM/PM slot was found"
        );
    }


    // =========================================================
    // PICKUP INSTRUCTIONS
    // =========================================================
    public void enterPickupInstructions() {

        System.out.println(
                "Waiting for Pickup Instructions..."
        );

        // -----------------------------------------------------
        // 1. Pickup Instructions
        // -----------------------------------------------------
        WebElement pickupButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        pickupInstructions
                )
        );

        wait.until(driver ->
                pickupButton.isDisplayed() &&
                        pickupButton.isEnabled()
        );

        pickupButton.click();

        System.out.println(
                "Pickup Instructions clicked"
        );


        // -----------------------------------------------------
        // 2. WAIT OUTSIDE
        // -----------------------------------------------------
        System.out.println(
                "Waiting for Wait outside..."
        );

        WebElement outside = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        waitOutside
                )
        );

        wait.until(driver ->
                outside.isDisplayed() &&
                        outside.isEnabled()
        );

        System.out.println(
                "Wait outside found"
        );

        outside.click();

        System.out.println(
                "Wait outside selected"
        );


        // -----------------------------------------------------
        // 3. ENTER NOTE
        // -----------------------------------------------------
        System.out.println(
                "Waiting for Pickup Note..."
        );

        WebElement note = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        noteTextBox
                )
        );

        wait.until(driver ->
                note.isDisplayed() &&
                        note.isEnabled()
        );

        StepTimer.startStep();

        note.click();
        note.clear();
        note.sendKeys("Beware of dogs");

        double duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Pickup Note Entered | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        System.out.println(
                "Pickup note entered successfully"
        );


        // -----------------------------------------------------
        // 4. HIDE KEYBOARD
        // -----------------------------------------------------
        try {

            if (driver.isKeyboardShown()) {

                driver.pressKey(
                        new KeyEvent(AndroidKey.BACK)
                );

                System.out.println(
                        "Keyboard hidden successfully"
                );

            } else {

                System.out.println(
                        "Keyboard is already hidden"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Unable to hide keyboard: "
                            + e.getMessage()
            );
        }


        // -----------------------------------------------------
        // 5. START RECORDING
        // -----------------------------------------------------
        System.out.println(
                "Waiting for Start Recording..."
        );

        WebElement start = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        startRecording
                )
        );

        wait.until(driver ->
                start.isDisplayed() &&
                        start.isEnabled()
        );

        start.click();

        System.out.println(
                "Recording started"
        );


        // -----------------------------------------------------
        // 6. WAIT FOR RECORDING IN PROGRESS
        // -----------------------------------------------------
        System.out.println(
                "Waiting for Recording in progress..."
        );

        WebElement recording = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        recordingInProgress
                )
        );

        wait.until(driver ->
                recording.isDisplayed()
        );

        System.out.println(
                "Recording in progress"
        );


        // -----------------------------------------------------
        // 7. STOP RECORDING
        // -----------------------------------------------------
        recording.click();

        System.out.println(
                "Recording stopped"
        );


        // -----------------------------------------------------
        // 8. SAVE
        // -----------------------------------------------------
        System.out.println(
                "Waiting for Save button..."
        );

        WebElement save = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        saveButton
                )
        );

        wait.until(driver ->
                save.isDisplayed() &&
                        save.isEnabled()
        );

        System.out.println(
                "Save button is visible and ready"
        );

        save.click();

        System.out.println(
                "Pickup Instructions saved"
        );
    }



    // =========================================================
    // CONFIRM ORDER
    // =========================================================

    public void clickConfirmOrder() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(
                "Waiting for Confirm Order..."
        );

        WebElement confirm = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        confirmOrder
                )
        );

        wait.until(driver ->
                confirm.isDisplayed() &&
                        confirm.isEnabled()
        );

        System.out.println(
                "Confirm Order is visible"
        );

        confirm.click();

        System.out.println(
                "Confirm Order clicked"
        );
    }


    // =========================================================
    // OK POPUP
    // =========================================================
    public void clickOKPopup() {

        System.out.println(
                "Waiting for OK popup..."
        );

        WebElement ok = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        okButton
                )
        );

        wait.until(driver ->
                ok.isDisplayed() &&
                        ok.isEnabled()
        );

        ok.click();

        System.out.println(
                "OK popup clicked"
        );
    }


    // =========================================================
    // COMPLETE SLOT FLOW
    // =========================================================
    public void selectSlotConfirmAndOK() throws InterruptedException {

        System.out.println("==============================================");
        System.out.println("Slot Selection Flow Started");
        System.out.println("==============================================");

        // 1. Select first available slot
        clickFirstAvailableSlot();

        // 2. Pickup Instructions
        enterPickupInstructions();

        // 3. Confirm Order
        clickConfirmOrder();

        // 4. OK Popup
        clickOKPopup();

        System.out.println("==============================================");
        System.out.println("Slot Selection Flow Completed");
        System.out.println("==============================================");
    }
}