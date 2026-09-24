package com.AndroidTest.JAccountProfileSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class APhotoUploadPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public APhotoUploadPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================================================
    // LOCATORS
    // =========================================================

    // Account
    private final By account =
            AppiumBy.accessibilityId("Account");

    // User name / email
    private final By userName =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'abishek251295@gmail.com')]"
            );

    // =========================================================
    // PHOTO UPLOAD ICON
    // NEW XPATH PROVIDED
    // =========================================================
    private final By photoUploadIcon =
            AppiumBy.xpath(
                    "//android.widget.ScrollView/android.view.View[1]"
            );

    // Gallery
    private final By gallery =
            AppiumBy.accessibilityId("Gallery");

    // Done button
    private final By doneButton =
            AppiumBy.xpath(
                    "//androidx.compose.ui.platform.ComposeView" +
                            "/android.view.View" +
                            "/android.view.View" +
                            "/android.view.View" +
                            "/android.view.View" +
                            "/android.view.View[5]" +
                            "/android.view.View" +
                            "/android.view.View[3]" +
                            "/android.widget.Button"
            );

    // OK popup
    private final By okButton =
            AppiumBy.accessibilityId("OK");


    // =========================================================
    // CLICK ACCOUNT
    // =========================================================

    public void clickAccount() {

        WebElement accountElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(account)
                );

        accountElement.click();

        System.out.println("Account clicked successfully");
    }


    // =========================================================
    // CLICK USER NAME
    // =========================================================

    public void clickUserName() {

        // -----------------------------------------------------
        // First try without scrolling
        // -----------------------------------------------------
        if (isElementVisible(userName)) {

            wait.until(
                    ExpectedConditions.elementToBeClickable(userName)
            ).click();

            System.out.println("User name clicked successfully");
            return;
        }

        // -----------------------------------------------------
        // If not visible, scroll down
        // -----------------------------------------------------
        for (int i = 0; i < 5; i++) {

            scrollDown();

            if (isElementVisible(userName)) {

                wait.until(
                        ExpectedConditions.elementToBeClickable(userName)
                ).click();

                System.out.println("User name clicked successfully");
                return;
            }
        }

        throw new RuntimeException(
                "User name was not found: abishek251295@gmail.com"
        );
    }


    // =========================================================
    // CLICK PHOTO UPLOAD ICON
    // =========================================================

    public void clickPhotoUploadIcon() {

        WebElement photoIcon =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                photoUploadIcon
                        )
                );

        photoIcon.click();

        System.out.println(
                "Photo upload icon clicked successfully"
        );
    }


    // =========================================================
    // CLICK GALLERY
    // =========================================================

    public void clickGallery() {

        WebElement galleryElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(gallery)
                );

        galleryElement.click();

        System.out.println(
                "Gallery opened successfully"
        );
    }


//    // =========================================================
//    // WAIT FOR MANUAL IMAGE SELECTION
//    // THEN CLICK DONE
//    // =========================================================
//
//    public void clickDoneAfterImageSelection() {
//
//        System.out.println(
//                "Waiting for manual image selection..."
//        );
//
//        // -----------------------------------------------------
//        // Allow 60 seconds for manual image selection
//        // -----------------------------------------------------
//        WebDriverWait doneWait =
//                new WebDriverWait(
//                        driver,
//                        Duration.ofSeconds(60)
//                );
//
//        doneWait.until(
//                ExpectedConditions.elementToBeClickable(doneButton)
//        ).click();
//
//        System.out.println(
//                "Done button clicked successfully"
//        );
//    }


    // =========================================================
    // CLICK OK POPUP
    // =========================================================

    public void clickOkPopup() {

        wait.until(
                ExpectedConditions.elementToBeClickable(okButton)
        ).click();

        System.out.println(
                "OK popup clicked successfully"
        );
    }


    // =========================================================
    // SCROLL DOWN
    // =========================================================

    private void scrollDown() {

        driver.executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", 100,
                        "top", 300,
                        "width", 520,
                        "height", 1100,
                        "direction", "down",
                        "percent", 0.70
                )
        );
    }


    // =========================================================
    // CHECK ELEMENT VISIBILITY
    // =========================================================

    private boolean isElementVisible(By locator) {

        try {

            WebElement element =
                    driver.findElement(locator);

            return element.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }


    // =========================================================
    // COMPLETE PHOTO UPLOAD FLOW
    // =========================================================

    public void uploadPhoto() {

        clickAccount();

        clickUserName();

        clickPhotoUploadIcon();

        clickGallery();

//        clickDoneAfterImageSelection();

        clickOkPopup();
    }
}