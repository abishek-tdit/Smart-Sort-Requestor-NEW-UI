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
    // LOCATORS
    // =========================================================

    // Full Name
    // Handles dynamic name and newline in content-desc
    private final By fullName =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'Full Name')]"
            );

    // ---------------------------------------------------------
    // LAST NAME FIELD
    // ---------------------------------------------------------

    private final By lastNameField =
            AppiumBy.xpath(
                    "//android.widget.EditText"
            );

    // =========================================================
    // UPDATED FULL NAME
    // =========================================================

    private final By fullNameAbishek =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'Full Name') " +
                            "and contains(@content-desc,'abishek')]"
            );

    // Update button
    private final By updateButton =
            AppiumBy.accessibilityId("Update");

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DNameChangePage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    // =========================================================
    // CHANGE LAST NAME
    //
    // ABI -> ABISHEK -> ABI
    // =========================================================

    public void changeLastName() {

        // =====================================================
        // FIRST CHANGE
        // ABI -> ABISHEK
        // =====================================================

        System.out.println("===== FIRST NAME CHANGE =====");

        // -----------------------------------------------------
        // FIND FULL NAME
        // -----------------------------------------------------

        WebElement fullNameElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        fullName
                )
        );

        System.out.println(
                "Full Name found: " +
                        fullNameElement.getAttribute("contentDescription")
        );

        // -----------------------------------------------------
        // CLICK FULL NAME
        // -----------------------------------------------------

        clickElement(
                fullNameElement,
                fullName
        );

        System.out.println(
                "Full Name clicked successfully"
        );

        // -----------------------------------------------------
        // WAIT FOR NAME EDIT SCREEN
        // -----------------------------------------------------

        WebElement lastName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lastNameField
                )
        );

        System.out.println(
                "Last Name field found successfully"
        );

        // -----------------------------------------------------
        // CHANGE ABI -> ABISHEK
        // -----------------------------------------------------

        lastName.click();

        lastName.clear();

        lastName.sendKeys("abishek");

        System.out.println(
                "Last Name changed: abi -> abishek"
        );

        // -----------------------------------------------------
        // CLICK UPDATE
        // -----------------------------------------------------

        WebElement update = wait.until(
                ExpectedConditions.elementToBeClickable(
                        updateButton
                )
        );

        update.click();

        System.out.println(
                "First Update clicked successfully"
        );

        // =====================================================
        // SECOND CHANGE
        // ABISHEK -> ABIRAM
        // =====================================================

        System.out.println(
                "===== SECOND NAME CHANGE ====="
        );

        // -----------------------------------------------------
        // WAIT UNTIL UPDATED FULL NAME IS DISPLAYED
        // -----------------------------------------------------

        WebElement updatedFullName = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        fullNameAbishek
                )
        );

        System.out.println(
                "Updated Full Name found: " +
                        updatedFullName.getAttribute(
                                "contentDescription"
                        )
        );

        // -----------------------------------------------------
        // CLICK UPDATED FULL NAME
        // -----------------------------------------------------

        clickElement(
                updatedFullName,
                fullNameAbishek
        );

        System.out.println(
                "Updated Full Name clicked successfully"
        );

        // -----------------------------------------------------
        // FIND LAST NAME FIELD AGAIN
        // -----------------------------------------------------

        WebElement updatedLastNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lastNameField
                )
        );

        System.out.println(
                "Updated Last Name field found successfully"
        );

        // -----------------------------------------------------
        // CHANGE ABISHEK -> ABI
        // -----------------------------------------------------

        updatedLastNameField.click();

        updatedLastNameField.clear();

        updatedLastNameField.sendKeys("Abiram");

        System.out.println(
                "Last Name changed: abishek -> Abiram"
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

        // -----------------------------------------------------
        // COMPLETED
        // -----------------------------------------------------

        System.out.println(
                "===== NAME CHANGE COMPLETED ====="
        );

        System.out.println(
                "Final Last Name: Abiram"
        );
    }

    // =========================================================
    // COMMON CLICK METHOD
    // =========================================================

    private void clickElement(
            WebElement element,
            By locator
    ) {

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            locator
                    )
            ).click();

        } catch (Exception e) {

            System.out.println(
                    "Normal click failed. Trying parent element..."
            );

            try {

                WebElement parent =
                        element.findElement(
                                By.xpath("..")
                        );

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                parent
                        )
                ).click();

            } catch (Exception parentException) {

                throw new RuntimeException(
                        "Unable to click element: " +
                                locator,
                        parentException
                );
            }
        }
    }
}