package com.AndroidTest.JAccountProfileSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DNameChangePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // LOCATORS - FIRST CHANGE
    // =========================================================

    // Full Name
    // Handles dynamic name and newline in content-desc
    private final By fullName =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'Full Name')]"
            );

    // Last Name = abi
    private final By lastNameAbi =
            AppiumBy.xpath(
                    "//android.widget.EditText[@text='abi']"
            );

    // =========================================================
    // LOCATORS - SECOND CHANGE
    // =========================================================

    // Full Name after changing abi -> abishek
    // Do NOT use exact content-desc because Android may expose
    // "Full Name\nAbiram abishek"
    private final By fullNameAbishek =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'Full Name') " +
                            "and contains(@content-desc,'abishek')]"
            );

    // Last Name = abishek
    private final By lastNameAbishek =
            AppiumBy.xpath(
                    "//android.widget.EditText[@text='abishek']"
            );

    // Update button
    private final By updateButton =
            AppiumBy.accessibilityId("Update");

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DNameChangePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================================================
    // CHANGE LAST NAME
    // ABI -> ABISHEK -> ABI
    // =========================================================

    public void changeLastName() {

        // =====================================================
        // FIRST CHANGE
        // ABI -> ABISHEK
        // =====================================================

        System.out.println("===== FIRST NAME CHANGE =====");

        // Find Full Name
        WebElement fullNameElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(fullName)
        );

        System.out.println(
                "Full Name found: " +
                        fullNameElement.getAttribute("contentDescription")
        );

        // Click Full Name
        clickElement(fullNameElement, fullName);

        System.out.println("Full Name clicked successfully");

        // -----------------------------------------------------
        // FIND LAST NAME = ABI
        // -----------------------------------------------------

        WebElement lastNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameAbi)
        );

        // Change ABI -> ABISHEK
        lastNameField.click();
        lastNameField.clear();
        lastNameField.sendKeys("abishek");

        System.out.println("Last Name changed: abi -> abishek");

        // -----------------------------------------------------
        // CLICK UPDATE
        // -----------------------------------------------------

        WebElement update = wait.until(
                ExpectedConditions.elementToBeClickable(updateButton)
        );

        update.click();

        System.out.println("First Update clicked successfully");

        // =====================================================
        // SECOND CHANGE
        // ABISHEK -> ABI
        // =====================================================

        System.out.println("===== SECOND NAME CHANGE =====");

        // -----------------------------------------------------
        // WAIT FOR UPDATED FULL NAME
        // -----------------------------------------------------

        WebElement updatedFullName = wait.until(
                ExpectedConditions.presenceOfElementLocated(fullNameAbishek)
        );

        System.out.println(
                "Updated Full Name found: " +
                        updatedFullName.getAttribute("contentDescription")
        );

        // -----------------------------------------------------
        // CLICK UPDATED FULL NAME
        // -----------------------------------------------------

        clickElement(updatedFullName, fullNameAbishek);

        System.out.println(
                "Updated Full Name clicked successfully"
        );

        // -----------------------------------------------------
        // FIND LAST NAME = ABISHEK
        // -----------------------------------------------------

        WebElement updatedLastNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lastNameAbishek
                )
        );

        // Change ABISHEK -> ABI
        updatedLastNameField.click();
        updatedLastNameField.clear();
        updatedLastNameField.sendKeys("abi");

        System.out.println(
                "Last Name changed: abishek -> abi"
        );

        // -----------------------------------------------------
        // CLICK UPDATE AGAIN
        // -----------------------------------------------------

        WebElement secondUpdate = wait.until(
                ExpectedConditions.elementToBeClickable(
                        updateButton
                )
        );

        secondUpdate.click();

        System.out.println(
                "Second Update clicked successfully"
        );

        System.out.println(
                "===== NAME CHANGE COMPLETED ====="
        );

        System.out.println(
                "Final Last Name: abi"
        );
    }

    // =========================================================
    // COMMON CLICK METHOD
    // =========================================================

    private void clickElement(WebElement element, By locator) {

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(locator)
            ).click();

        } catch (Exception e) {

            // If the located element itself is not clickable,
            // try its parent.
            try {

                WebElement parent = element.findElement(
                        By.xpath("..")
                );

                wait.until(
                        ExpectedConditions.elementToBeClickable(parent)
                ).click();

            } catch (Exception parentException) {

                throw new RuntimeException(
                        "Unable to click element: " + locator,
                        parentException
                );
            }
        }
    }
}
