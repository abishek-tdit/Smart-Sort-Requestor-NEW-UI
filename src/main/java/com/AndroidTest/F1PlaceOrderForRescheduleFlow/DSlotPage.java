package com.AndroidTest.F1PlaceOrderForRescheduleFlow;

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

public class DSlotPage extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(20));

    // =========================================================
    // AVAILABLE SLOT
    // =========================================================

    private final By availableSlots = By.xpath(
            "//*[contains(@content-desc,'AM') or " +
                    "contains(@content-desc,'PM')]"
    );

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

        System.out.println(
                "=============================================="
        );
        System.out.println(
                "Searching for First Available Slot..."
        );
        System.out.println(
                "=============================================="
        );

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
    // CONFIRM ORDER
    // =========================================================

    public void clickConfirmOrder() {

        System.out.println(
                "Waiting for Confirm Order..."
        );

        WebElement confirm = wait.until(
                ExpectedConditions.elementToBeClickable(
                        confirmOrder
                )
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
                ExpectedConditions.elementToBeClickable(
                        okButton
                )
        );

        ok.click();

        System.out.println(
                "OK popup clicked"
        );
    }


    // =========================================================
    // COMPLETE SLOT FLOW
    // =========================================================

    public void selectSlotConfirmAndOK() {

        System.out.println(
                "=============================================="
        );
        System.out.println(
                "Slot Selection Flow Started"
        );
        System.out.println(
                "=============================================="
        );

        // 1. Select first available slot
        clickFirstAvailableSlot();

        // 2. Confirm Order
        clickConfirmOrder();

        // 3. OK Popup
        clickOKPopup();

        System.out.println("==============================================");
        System.out.println("Slot Selection Flow Completed");
        System.out.println("==============================================");
    }
}
