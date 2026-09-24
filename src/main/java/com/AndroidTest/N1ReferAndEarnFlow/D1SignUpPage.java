package com.AndroidTest.N1ReferAndEarnFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.D1ReferEarnReferralCode;
import utilities.MobileUtilities;
import utilities.StepTimer;

import java.time.Duration;
import java.util.Map;

public class D1SignUpPage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final MobileUtilities util;
    private final WebDriverWait wait;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public D1SignUpPage(AndroidDriver driver) {

        this.driver = driver;
        this.util = new MobileUtilities(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================================================
    // SIGN UP
    // =========================================================

    public String signUp() throws Exception {

        double duration;

        String city = "Bqaiq";

        // =====================================================
        // START NOW
        // =====================================================

        StepTimer.startStep();

        util.click(
                util.waitForAccessibilityId("Start Now")
        );

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Home Start Now button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // SELECT CITY
        // =====================================================

        StepTimer.startStep();

        WebElement selectCity = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Select City")
                )
        );

        selectCity.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Select City dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // WAIT FOR CITY LIST
        // =====================================================

        By cityLocator = AppiumBy.accessibilityId(city);

        try {

            WebElement cityElement = wait.until(
                    ExpectedConditions.elementToBeClickable(cityLocator)
            );

            cityElement.click();

        } catch (TimeoutException e) {

            ExtentTestListener.logStep(
                    "City list not opened. Clicking Select City again..."
            );

            selectCity = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Select City")
                    )
            );

            selectCity.click();

            WebElement cityElement = wait.until(
                    ExpectedConditions.elementToBeClickable(cityLocator)
            );

            cityElement.click();
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                city + " selected successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // REQUESTOR TYPE
        // =====================================================

        StepTimer.startStep();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Select Requestor Type")
                )
        ).click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Requestor Type dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        StepTimer.startStep();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Domestic Requestor")
                )
        ).click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Requestor Type selected successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // FIRST NAME
        // =====================================================

        StepTimer.startStep();

        WebElement firstName = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath(
                                "//android.widget.ScrollView/android.view.View/android.widget.EditText[1]"
                        )
                )
        );

        firstName.click();
        firstName.clear();
        firstName.sendKeys("Ragul");

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "First Name entered successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // =====================================================
        // LAST NAME
        // =====================================================

        StepTimer.startStep();

        WebElement lastName = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath(
                                "//android.widget.ScrollView/android.view.View/android.widget.EditText[3]"
                        )
                )
        );

        lastName.click();
        lastName.clear();
        lastName.sendKeys("Raja");

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Last Name entered successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // =====================================================
        // GENDER
        // =====================================================

        StepTimer.startStep();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Select Gender")
                )
        ).click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Gender dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        StepTimer.startStep();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Male")
                )
        ).click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Male selected successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // MOBILE NUMBER
        // =====================================================

        D2MobileNumber mobileUtil = new D2MobileNumber();

        String mobile = mobileUtil.getUniqueMobileNumber(city);

        D2MobileNumber.generatedMobileNumber = mobile;

        ExtentTestListener.logStep(
                "Generated Mobile Number : " + mobile
        );


        StepTimer.startStep();

        WebElement mobileNumber = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath(
                                "//android.widget.EditText[@hint='Enter your mobile number']"
                        )
                )
        );

        mobileNumber.click();
        mobileNumber.clear();
        mobileNumber.sendKeys(mobile);

        hideKeyboard();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Mobile Number entered successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // EMAIL
        // =====================================================

        try {

            StepTimer.startStep();

            WebElement email = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath(
                                    "//android.widget.EditText[@hint='Enter your email address']"
                            )
                    )
            );

            email.click();
            email.clear();
            email.sendKeys("abishek251295@gmail.com");

            hideKeyboard();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Email Address entered successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );

        } catch (TimeoutException e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Email field not available. Skipping... | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );
        }




        // =====================================================
        // REFERRAL CODE
        // =====================================================

        String savedReferralCode = D1ReferEarnReferralCode.referralCode;

        if (savedReferralCode == null || savedReferralCode.trim().isEmpty()) {
            throw new RuntimeException(
                    "Referral code is not available in D1ReferEarnReferralCode"
            );
        }

        WebElement referralCode = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath(
                                "//android.widget.EditText[contains(@hint,'Referral')]"
                        )
                )
        );

        referralCode.click();
        referralCode.clear();
        referralCode.sendKeys(savedReferralCode.trim());

        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }

        ExtentTestListener.logStep(
                "Referral Code entered successfully : "
                        + savedReferralCode.trim()
        );

        // =====================================================
        // PASSWORD
        // =====================================================

        StepTimer.startStep();

        try {

            WebElement password = new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath(
                                    "//android.widget.EditText[@hint='Password']"
                            )
                    )
            );

            password.click();
            password.clear();
            password.sendKeys("Admin@194");

            hideKeyboard();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Password entered successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );

        } catch (TimeoutException e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Password field not visible - Skipped | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );
        }


        // =====================================================
        // SCROLL TO CONFIRM PASSWORD
        // =====================================================

        try {

            WebElement confirmPasswordField = driver.findElement(
                    AppiumBy.xpath(
                            "//android.widget.EditText[@hint='Confirm Password']"
                    )
            );

            if (!confirmPasswordField.isDisplayed()) {

                swipeUp();
            }

        } catch (Exception e) {

            swipeUp();
        }


        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        StepTimer.startStep();

        try {

            WebElement confirmPassword = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(5)
            ).until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath(
                                    "//android.widget.EditText[@hint='Confirm Password']"
                            )
                    )
            );

            confirmPassword.click();
            confirmPassword.clear();
            confirmPassword.sendKeys("Admin@194");

            hideKeyboard();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Confirm Password entered successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );

        } catch (TimeoutException e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Confirm Password field not visible - Skipped | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );
        }


        // =====================================================
        // TERMS CHECKBOX
        // =====================================================

        StepTimer.startStep();

        By termsCheckbox = AppiumBy.className(
                "android.widget.CheckBox"
        );

        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        termsCheckbox
                )
        );

        String before = checkbox.getAttribute("checked");

        if (!"true".equalsIgnoreCase(before)) {
            checkbox.click();
        }

        wait.until(driver ->
                "true".equalsIgnoreCase(
                        driver.findElement(
                                termsCheckbox
                        ).getAttribute("checked")
                )
        );

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Terms checkbox selected successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // REGISTER BUTTON
        // =====================================================

        StepTimer.startStep();

        WebElement registerBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Register")
                )
        );

        registerBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Register button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // OTP POPUP
        // =====================================================

        StepTimer.startStep();

        WebElement okBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("OK")
                )
        );

        okBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "OTP sent successfully popup OK clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // GET OTP
        // =====================================================

        StepTimer.startStep();

        String otp = mobileUtil.getOTP(mobile);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Generated OTP : " + otp
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        ExtentTestListener.logStep(
                "Please enter OTP"
        );


        // =====================================================
        // WAIT FOR OTP SCREEN
        // =====================================================

        StepTimer.startStep();

        WebElement verifyOtpBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath(
                                "//android.widget.Button[@content-desc='Verify OTP']"
                        )
                )
        );

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Verify OTP button available | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // VERIFY OTP
        // =====================================================
        Thread.sleep(5000);
        StepTimer.startStep();

        verifyOtpBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Verify OTP button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // SUCCESS POPUP
        // =====================================================

        StepTimer.startStep();

        WebElement successOkBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath(
                                "//android.widget.Button[@content-desc='OK']"
                        )
                )
        );

        successOkBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Registration Success OK button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // COMPLETED
        // =====================================================

        System.out.println(
                "========== SIGN UP COMPLETED =========="
        );

        return mobile;
    }


    // =========================================================
    // HIDE KEYBOARD
    // =========================================================

    private void hideKeyboard() {

        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }
    }


    // =========================================================
    // SWIPE UP
    // =========================================================

    private void swipeUp() {

        driver.executeScript(
                "mobile: swipeGesture",
                Map.of(
                        "left", 300,
                        "top", 1200,
                        "width", 400,
                        "height", 600,
                        "direction", "up",
                        "percent", 0.50
                )
        );
    }
}