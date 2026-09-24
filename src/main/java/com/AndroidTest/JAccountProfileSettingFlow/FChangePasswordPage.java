package com.AndroidTest.JAccountProfileSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class FChangePasswordPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By account =
            AppiumBy.accessibilityId("Account");

    private final By userName =
            AppiumBy.xpath("//*[contains(@content-desc,'abishek251295@gmail.com')]");


    private final By changePassword =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().descriptionContains(\"Change Password\")"
            );

    // Fallback XPath
    private final By changePasswordXPath =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'Change Password')]"
            );

    private final By currentPassword =
            AppiumBy.xpath(
                    "(//android.widget.EditText)[1]"
            );

    private final By newPassword =
            AppiumBy.xpath(
                    "(//android.widget.EditText)[2]"
            );

    private final By confirmPassword =
            AppiumBy.xpath(
                    "(//android.widget.EditText)[3]"
            );

    private final By confirmButton =
            AppiumBy.accessibilityId("Confirm");


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public FChangePasswordPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }


    // =========================================================
    // CLICK ACCOUNT
    // =========================================================

    public void clickAccount() throws InterruptedException {
Thread.sleep(5000);
        wait.until(
                ExpectedConditions.elementToBeClickable(account)
        ).click();

        System.out.println(
                "Account clicked successfully"
        );
    }


    // =========================================================
    // CLICK USER NAME
    // =========================================================

    public void clickUserName() {

        for (int i = 0; i < 6; i++) {

            try {

                WebElement element =
                        driver.findElement(userName);

                if (element.isDisplayed()) {

                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    userName
                            )
                    ).click();

                    System.out.println(
                            "User name clicked successfully"
                    );

                    /*
                     * Give the next screen time to appear using
                     * explicit polling instead of Thread.sleep.
                     */
                    waitForProfileScreen();

                    return;
                }

            } catch (Exception ignored) {
            }

            scrollDown();
        }

        throw new RuntimeException(
                "User name was not found: abishek251295@gmail.com"
        );
    }


    // =========================================================
    // WAIT FOR PROFILE SCREEN
    // =========================================================

    private void waitForProfileScreen() {

        try {

            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10)
            ).until(
                    d -> {

                        try {

                            return !d.findElements(
                                    changePassword
                            ).isEmpty();

                        } catch (Exception e) {

                            return false;
                        }
                    }
            );

            System.out.println(
                    "Profile screen loaded successfully"
            );

        } catch (Exception e) {

            System.out.println(
                    "Change Password not immediately available. " +
                            "Will search using scrolling."
            );
        }
    }


    // =========================================================
    // CLICK CHANGE PASSWORD
    // =========================================================

    public void clickChangePassword() {

        System.out.println(
                "Searching for Change Password..."
        );

        /*
         * First try UiSelector descriptionContains.
         */
        if (tryClickChangePassword(changePassword)) {
            return;
        }

        /*
         * Second try XPath contains(@content-desc,...)
         */
        if (tryClickChangePassword(changePasswordXPath)) {
            return;
        }

        /*
         * If still not found, scroll and try again.
         */
        for (int i = 0; i < 8; i++) {

            System.out.println(
                    "Change Password not visible. " +
                            "Scrolling... " + (i + 1)
            );

            scrollDown();

            if (tryClickChangePassword(changePassword)) {
                return;
            }

            if (tryClickChangePassword(changePasswordXPath)) {
                return;
            }
        }

        /*
         * Print current page source information to help
         * identify the actual locator if the app changed.
         */
        System.out.println(
                "Change Password could not be found."
        );

        throw new RuntimeException(
                "Change Password was not found. " +
                        "Expected content-desc containing: Change Password"
        );
    }


    // =========================================================
    // TRY CLICK CHANGE PASSWORD
    // =========================================================

    private boolean tryClickChangePassword(By locator) {

        try {

            var elements =
                    driver.findElements(locator);

            if (elements.isEmpty()) {
                return false;
            }

            for (WebElement element : elements) {

                if (element.isDisplayed()) {

                    System.out.println(
                            "Change Password found: "
                                    + element.getAttribute(
                                    "contentDescription"
                            )
                    );

                    try {

                        wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        locator
                                )
                        ).click();

                    } catch (Exception e) {


                        element.click();
                    }

                    System.out.println(
                            "Change Password clicked successfully"
                    );

                    return true;
                }
            }

        } catch (Exception ignored) {
        }

        return false;
    }


    // =========================================================
    // ENTER CURRENT PASSWORD
    // =========================================================

    public void enterCurrentPassword(String password) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        currentPassword
                )
        );

        element.click();
        element.clear();
        element.sendKeys(password);

        System.out.println(
                "Current password entered successfully"
        );
    }


    // =========================================================
    // ENTER NEW PASSWORD
    // =========================================================

    public void enterNewPassword(String password) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        newPassword
                )
        );

        element.click();
        element.clear();
        element.sendKeys(password);

        System.out.println(
                "New password entered successfully"
        );
    }


    // =========================================================
    // ENTER CONFIRM PASSWORD
    // =========================================================

    public void enterConfirmPassword(String password) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        confirmPassword
                )
        );

        element.click();
        element.clear();
        element.sendKeys(password);

        System.out.println(
                "Confirm password entered successfully"
        );
    }


    // =========================================================
    // CLICK CONFIRM
    // =========================================================

    public void clickConfirm() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        confirmButton
                )
        ).click();

        System.out.println(
                "Confirm button clicked successfully"
        );
    }


    // =========================================================
    // FIRST PASSWORD CHANGE
    //
    // Admin@194 -> Admin@199
    // =========================================================

    public void changePasswordToNewPassword() {

        System.out.println(
                "========== FIRST PASSWORD CHANGE =========="
        );

        clickChangePassword();

        enterCurrentPassword("Admin@194");

        enterNewPassword("Admin@199");

        enterConfirmPassword("Admin@199");

        clickConfirm();

        System.out.println(
                "First password change completed successfully"
        );
    }


    // =========================================================
    // SECOND PASSWORD CHANGE
    //
    // Admin@199 -> Admin@194
    // =========================================================

    public void changePasswordBackToOldPassword() {

        System.out.println(
                "========== RESTORING ORIGINAL PASSWORD =========="
        );

        /*
         * Wait until Change Password appears again.
         */
        try {

            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10)
            ).until(
                    d ->
                            !d.findElements(
                                    changePassword
                            ).isEmpty()
            );

        } catch (Exception ignored) {
        }

        clickChangePassword();

        enterCurrentPassword("Admin@199");

        enterNewPassword("Admin@194");

        enterConfirmPassword("Admin@194");

        clickConfirm();

        System.out.println(
                "Password restored successfully"
        );
    }


    // =========================================================
    // SCROLL DOWN
    // =========================================================

    private void scrollDown() {

        driver.executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", 50,
                        "top", 200,
                        "width", 620,
                        "height", 1200,
                        "direction", "down",
                        "percent", 0.50
                )
        );
    }
}