package com.AndroidTest.C1RequestorOrderPlacingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EAddressPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // LOCATORS
    // =========================================================

    // Edit icon
    private final By EDIT_ICON = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.view.View\").instance(11)"
    );

    // Select location from saved addresses
    private final By SAVED_ADDRESS_DROPDOWN =
            AppiumBy.accessibilityId("Select location from saved addresses");

    // Saved address
    // normalize-space() handles the leading space in content-desc
    private final By SAVED_ADDRESS = By.xpath(
            "//android.view.View[normalize-space(@content-desc)=\"حي, Buqayq, 33262, Saudi Arabia\"]"
    );

    // OK popup
    private final By OK_BUTTON =
            AppiumBy.accessibilityId("OK");


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public EAddressPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    // =========================================================
    // EDIT ICON
    // =========================================================

    public void clickEditIcon() {

        WebElement editIcon = wait.until(
                ExpectedConditions.presenceOfElementLocated(EDIT_ICON)
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
                ExpectedConditions.presenceOfElementLocated(
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
                ExpectedConditions.presenceOfElementLocated(
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
                ExpectedConditions.presenceOfElementLocated(
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
