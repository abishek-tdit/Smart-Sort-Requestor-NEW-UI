package com.AndroidTest.F1PlaceOrderForRescheduleFlow;

import Base.BaseClassMobile;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CAddressPage extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(20));

    // =========================================================
    // LOCATORS
    // =========================================================

    // Edit icon
    private final By EDIT_ICON = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.view.View\").instance(11)"
    );

    // Select location from saved addresses
    private final By SAVED_ADDRESS_DROPDOWN =
            AppiumBy.accessibilityId(
                    "Select location from saved addresses"
            );

    // Saved address
    private final By SAVED_ADDRESS = By.xpath(
            "//android.view.View[" +
                    "normalize-space(@content-desc)=" +
                    "\"حي, Buqayq, 33262, Saudi Arabia\"" +
                    "]"
    );

    // OK popup
    private final By OK_BUTTON =
            AppiumBy.accessibilityId("OK");


    // =========================================================
    // EDIT ICON
    // =========================================================

    public void clickEditIcon() {

        WebElement editIcon = wait.until(
                ExpectedConditions.elementToBeClickable(EDIT_ICON)
        );

        System.out.println("Edit icon found");

        editIcon.click();

        System.out.println("PASS : Edit icon clicked");
    }


    // =========================================================
    // SAVED ADDRESS DROPDOWN
    // =========================================================

    public void clickSavedAddressDropdown() {

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        SAVED_ADDRESS_DROPDOWN
                )
        );

        System.out.println("Saved address dropdown found");

        dropdown.click();

        System.out.println(
                "PASS : Select location from saved addresses clicked"
        );
    }


    // =========================================================
    // SELECT SAVED ADDRESS
    // =========================================================

    public void selectSavedAddress() {

        System.out.println("Waiting for saved address...");

        WebElement savedAddress = wait.until(
                ExpectedConditions.elementToBeClickable(
                        SAVED_ADDRESS
                )
        );

        System.out.println("Saved address found");

        savedAddress.click();

        System.out.println(
                "PASS : Saved address selected - Buqayq"
        );
    }


    // =========================================================
    // CLICK OK
    // =========================================================

    public void clickOkPopup() {

        WebElement okButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        OK_BUTTON
                )
        );

        System.out.println("OK popup found");

        okButton.click();

        System.out.println("PASS : OK button clicked");
    }


    // =========================================================
    // COMPLETE FLOW
    // =========================================================

    public void updateAddressFromSavedAddress() {

        System.out.println("========== ADDRESS UPDATE STARTED ==========");

        clickEditIcon();

        clickSavedAddressDropdown();

        selectSavedAddress();

        clickOkPopup();

        System.out.println(
                "========== ADDRESS UPDATE COMPLETED =========="
        );
    }
}
