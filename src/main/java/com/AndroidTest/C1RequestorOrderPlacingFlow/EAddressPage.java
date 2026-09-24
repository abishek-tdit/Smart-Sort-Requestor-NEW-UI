package com.AndroidTest.C1RequestorOrderPlacingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EAddressPage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By EDIT_ICON = AppiumBy.xpath
            ("//android.view.View[contains(@content-desc,'Default Address')" +
                    "]/android.view.View[@clickable='true']");

    // =========================================================
    // SAVED ADDRESS DROPDOWN
    // =========================================================

    private final By SAVED_ADDRESS_DROPDOWN =
            AppiumBy.accessibilityId("Select location from saved addresses");

    // =========================================================
    // SAVED ADDRESS
    // =========================================================
    private final By SAVED_ADDRESS = AppiumBy.xpath
            ("//android.view.View[" +
                    "normalize-space(@content-desc)=" +
                    "\"حي, Buqayq, 33262, Saudi Arabia\"" + "]");

    // =========================================================
    // OK POPUP
    // =========================================================

    private final By OK_BUTTON = AppiumBy.accessibilityId("OK");


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

        System.out.println("Waiting for Default Address edit icon...");

        WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(EDIT_ICON));

        ExtentTestListener.logStep("Default Address edit icon found");

        editIcon.click();

        ExtentTestListener.logStep("PASS : Default Address edit icon clicked");
    }


    // =========================================================
    // SAVED ADDRESS DROPDOWN
    // =========================================================

    public void clickSavedAddressDropdown() {

        ExtentTestListener.logStep("Waiting for saved address dropdown...");

        WebElement dropdown =
                wait.until(ExpectedConditions.elementToBeClickable(SAVED_ADDRESS_DROPDOWN));

        System.out.println("Saved address dropdown found");

        dropdown.click();

        ExtentTestListener.logStep("PASS : Select location from saved addresses clicked");
    }


    // =========================================================
    // SELECT SAVED ADDRESS
    // =========================================================

    public void selectSavedAddress() {

        ExtentTestListener.logStep("Waiting for Buqayq saved address...");

        WebElement savedAddress =
                wait.until(ExpectedConditions.elementToBeClickable(SAVED_ADDRESS));

        System.out.println("Buqayq saved address found");

        savedAddress.click();

        ExtentTestListener.logStep("PASS : Saved address selected - " +
                        "حي, Buqayq, 33262, Saudi Arabia");
    }


    // =========================================================
    // CLICK OK
    // =========================================================

    public void clickOkPopup() {

        ExtentTestListener.logStep("Waiting for OK popup...");

        WebElement okButton =
                wait.until(ExpectedConditions.elementToBeClickable(OK_BUTTON));

        System.out.println("OK popup found");

        okButton.click();

        ExtentTestListener.logStep("PASS : OK button clicked");
    }


    // =========================================================
    // COMPLETE ADDRESS UPDATE FLOW
    // =========================================================

    public void updateAddressFromSavedAddress() {

        System.out.println("========== ADDRESS UPDATE STARTED ==========");

        clickEditIcon();
        clickSavedAddressDropdown();
        selectSavedAddress();
        clickOkPopup();

        ExtentTestListener.logStep("========== ADDRESS UPDATE COMPLETED ==========");
    }
}
