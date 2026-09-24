package com.AndroidTest.JAccountProfileSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class EEmailValidationPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // LOCATORS
    // =========================================================

    // Email
    // DO NOT use complete email value
    private final By emailEdit =
            AppiumBy.xpath(
                    "//android.view.View[contains(@content-desc,'Email')]"
            );

    // Email input field
    private final By emailInput =
            AppiumBy.className("android.widget.EditText");

    // Send OTP
    private final By sendOtpButton =
            AppiumBy.accessibilityId("Send OTP");

    // OK popup
    private final By okButton =
            AppiumBy.accessibilityId("OK");

    // ---------------------------------------------------------
    // OTP CONFIRM / VERIFY
    // ---------------------------------------------------------

    // Actual button displayed after OTP entry
    private final By confirmOtpButton =
            AppiumBy.accessibilityId("Confirm");

    // Fallback in case some app versions use Verify OTP
    private final By verifyOtpButton =
            AppiumBy.accessibilityId("Verify OTP");


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public EEmailValidationPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );
    }


    // =========================================================
    // VALIDATE EMAIL
    // =========================================================

    public void validateEmail() throws InterruptedException {

        System.out.println("===== EMAIL VALIDATION STARTED =====");


        // =====================================================
        // 1. FIND EMAIL
        // =====================================================

        WebElement email = findEmail();

        System.out.println("Email edit found successfully");


        // =====================================================
        // 2. CLICK EMAIL
        // =====================================================

        clickElement(email);

        System.out.println("Email edit clicked successfully");


        // =====================================================
        // 3. FIND EMAIL INPUT
        // =====================================================

        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput)
        );

        System.out.println("Email input field found successfully");


        // =====================================================
        // 4. ENTER EMAIL
        // =====================================================

        input.click();
        input.clear();

        input.sendKeys("abishek251295@gmail.com");

        System.out.println(
                "Email entered: abishek251295@gmail.com"
        );


        // =====================================================
        // 5. CLICK SEND OTP
        // =====================================================

        WebElement sendOtp = wait.until(
                ExpectedConditions.elementToBeClickable(sendOtpButton)
        );

        sendOtp.click();

        System.out.println(
                "Send OTP clicked successfully"
        );


        // =====================================================
        // 6. WAIT FOR OK POPUP
        // =====================================================

        WebElement ok = wait.until(
                ExpectedConditions.elementToBeClickable(okButton)
        );

        ok.click();

        System.out.println(
                "OK popup clicked successfully"
        );


        // =====================================================
        // 7. WAIT FOR MANUAL OTP ENTRY
        // =====================================================

        System.out.println(
                "Waiting for manual OTP entry..."
        );
        Thread.sleep(20000);
        System.out.println(
                "Please enter the OTP manually on the mobile."
        );

        //
        WebElement otpButton = waitForOtpButton();

        System.out.println(
                "OTP confirmation button found successfully"
        );


        // =====================================================
        // 8. CLICK CONFIRM / VERIFY OTP
        // =====================================================

        clickElement(otpButton);

        System.out.println(
                "OTP confirmation button clicked successfully"
        );


        // =====================================================
        // 9. WAIT FOR FINAL OK POPUP
        // =====================================================

        WebElement finalOk = wait.until(
                ExpectedConditions.elementToBeClickable(okButton)
        );

        finalOk.click();

        System.out.println(
                "Final OK popup clicked successfully"
        );


        // =====================================================
        // COMPLETED
        // =====================================================

        System.out.println(
                "===== EMAIL VALIDATION COMPLETED ====="
        );
    }


    // =========================================================
    // FIND OTP BUTTON
    // =========================================================

    private WebElement waitForOtpButton() {

        /*
         * Give the user enough time to manually enter OTP.
         *
         * Instead of Thread.sleep(), continuously check
         * whether Confirm or Verify OTP becomes available.
         */
        WebDriverWait otpWait = new WebDriverWait(
                driver,
                Duration.ofSeconds(60)
        );

        return otpWait.until(driver -> {

            // -------------------------------------------------
            // FIRST: LOOK FOR CONFIRM
            // -------------------------------------------------

            try {

                WebElement confirm =
                        driver.findElement(confirmOtpButton);

                if (confirm.isDisplayed()
                        && confirm.isEnabled()) {

                    System.out.println(
                            "Confirm button found"
                    );

                    return confirm;
                }

            } catch (Exception ignored) {
                // Continue checking
            }


            // -------------------------------------------------
            // SECOND: LOOK FOR VERIFY OTP
            // -------------------------------------------------

            try {

                WebElement verify =
                        driver.findElement(verifyOtpButton);

                if (verify.isDisplayed()
                        && verify.isEnabled()) {

                    System.out.println(
                            "Verify OTP button found"
                    );

                    return verify;
                }

            } catch (Exception ignored) {
                // Continue checking
            }


            // -------------------------------------------------
            // THIRD: SCROLL DOWN
            // -------------------------------------------------

            try {

                scrollDown();

            } catch (Exception ignored) {
                // Continue waiting
            }

            return null;
        });
    }


    // =========================================================
    // FIND EMAIL - SCROLL IF NECESSARY
    // =========================================================

    private WebElement findEmail() {

        for (int i = 0; i < 5; i++) {

            try {

                WebElement element =
                        driver.findElement(emailEdit);

                if (element.isDisplayed()) {

                    return element;
                }

            } catch (Exception ignored) {

                // Continue scrolling
            }

            scrollDown();
        }


        throw new RuntimeException(
                "Email edit element was not found after scrolling"
        );
    }


    // =========================================================
    // STABLE CLICK
    // =========================================================

    private void clickElement(WebElement element) {

        wait.until(driver -> {

            try {

                return element.isDisplayed()
                        && element.isEnabled();

            } catch (Exception e) {

                return false;
            }
        });


        element.click();
    }


    // =========================================================
    // SCROLL DOWN
    // =========================================================

    private void scrollDown() {

        Map<String, Object> params =
                new HashMap<>();

        params.put("left", 100);
        params.put("top", 400);
        params.put("width", 520);
        params.put("height", 900);

        params.put("direction", "down");

        params.put("percent", 0.70);

        driver.executeScript(
                "mobile: scrollGesture",
                params
        );
    }
}
