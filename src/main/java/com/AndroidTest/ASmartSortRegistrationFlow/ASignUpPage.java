package com.AndroidTest.ASmartSortRegistrationFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.MobileUtilities;
import utilities.StepTimer;
import java.time.Duration;

public class ASignUpPage {

    private final AndroidDriver driver;
    private final MobileUtilities util;
    private final WebDriverWait wait;

    public ASignUpPage(AndroidDriver driver) {

        this.driver = driver;
        this.util = new MobileUtilities(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================================================
    // SIGN UP FLOW
    // =========================================================

    public void signUp() throws Exception {

        double duration;

        String city = "Bqaiq";


        // START NOW
        StepTimer.startStep();

        util.click(util.waitForAccessibilityId("Start Now"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Home Start Now button clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");



        // SELECT CITY
        Thread.sleep(6000);
        StepTimer.startStep();

        WebDriverWait cityWait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for Select City
        WebElement selectCity = cityWait.until
                (ExpectedConditions.elementToBeClickable
                        (AppiumBy.accessibilityId("Select City")));

        selectCity.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Select City dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");



        // WAIT FOR CITY LIST TO OPEN
        By cityLocator = AppiumBy.accessibilityId(city);

        try {

            // Wait until Bqaiq is actually displayed
            WebElement cityElement = cityWait.until
                    (ExpectedConditions.visibilityOfElementLocated(cityLocator));
            cityElement.click();
        }
        catch (TimeoutException e)
        {
            // Dropdown did not open properly
            ExtentTestListener.logStep("City list not opened. Clicking Select City again...");

            selectCity = cityWait.until
                    (ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Select City")));
            selectCity.click();

            // Wait again for Bqaiq
            WebElement cityElement = cityWait.until
                    (ExpectedConditions.visibilityOfElementLocated(cityLocator));

            cityElement.click();
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(city + " selected successfully | Time Taken : "
                                        + String.format("%.2f", duration) + " sec");

        // REQUESTOR TYPE
        StepTimer.startStep();

        util.click(util.waitForAccessibilityId("Select Requestor Type"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Requestor Type dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        StepTimer.startStep();

        util.click(util.waitForAccessibilityId("Domestic Requestor"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Requestor Type selected successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");


        // FIRST NAME
        StepTimer.startStep();

        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable
                        (AppiumBy.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[1]")));

        firstName.click();
        firstName.sendKeys("Nirmal");

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "First Name entered successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        hideKeyboard();


        // LAST NAME
        StepTimer.startStep();

        WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable
                (AppiumBy.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[3]")));

        lastName.click();
        lastName.sendKeys("Kimkin");

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Last Name entered successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        hideKeyboard();

        // =====================================================
        // GENDER
        // =====================================================

        StepTimer.startStep();

        util.click(util.waitForAccessibilityId("Select Gender"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Gender dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        StepTimer.startStep();

        util.click(util.waitForAccessibilityId("Male"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Male selected successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");


        // MOBILE NUMBER
        BMobileNumber mobileUtil = new BMobileNumber();

        String mobile = mobileUtil.getUniqueMobileNumber(city);

        ExtentTestListener.logStep("Generated Mobile Number : " + mobile);

        StepTimer.startStep();

        WebElement mobileNumber = wait.until(ExpectedConditions.elementToBeClickable
                (AppiumBy.xpath("//android.widget.EditText[@hint='Enter your mobile number']")));

        mobileNumber.click();
        mobileNumber.clear();
        mobileNumber.sendKeys(mobile);

        hideKeyboard();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Mobile Number entered successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // EMAIL
        // =====================================================

        StepTimer.startStep();

        try {

            WebElement email = new WebDriverWait(driver, Duration.ofSeconds(5)).until
                    (ExpectedConditions.elementToBeClickable
                            (AppiumBy.xpath("//android.widget.EditText[@hint='Enter your email address']")));

            email.click();
            email.clear();
            email.sendKeys("abishek251295@gmail.com");

            hideKeyboard();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Email Address entered successfully | Time Taken : "
                            + String.format("%.2f", duration) + " sec");

        } catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Email field not available - Skipped | Time Taken : "
                            + String.format("%.2f", duration) + " sec");
        }

        // =====================================================
        // PASSWORD
        // =====================================================

        StepTimer.startStep();

        try {

            WebElement password = new WebDriverWait(driver,
                    Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable
                            (AppiumBy.xpath("//android.widget.EditText[@hint='Password']")));

            password.click();
            password.clear();
            password.sendKeys("Admin@194");

            hideKeyboard();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Password entered successfully | Time Taken : "
                            + String.format("%.2f", duration) + " sec");

        } catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Password field not visible - Skipped | Time Taken : "
                            + String.format("%.2f", duration) + " sec");
        }

        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        StepTimer.startStep();

        try {

            WebElement confirmPassword = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(
                                    "//android.widget.EditText[@hint='Confirm Password']")));

            confirmPassword.click();
            confirmPassword.clear();
            confirmPassword.sendKeys("Admin@194");

            hideKeyboard();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Confirm Password entered successfully | Time Taken : "
                            + String.format("%.2f", duration) + " sec");

        } catch (Exception e) {

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Confirm Password field not visible - Skipped | Time Taken : "
                            + String.format("%.2f", duration) + " sec");
        }

        // =====================================================
        // TERMS CHECKBOX
        // =====================================================

        StepTimer.startStep();

        By termsCheckbox = AppiumBy.className("android.widget.CheckBox");

        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox));

        if (!"true".equalsIgnoreCase(
                checkbox.getAttribute("checked"))) {

            checkbox.click();
        }

        wait.until(driver ->
                "true".equalsIgnoreCase(driver.findElement(termsCheckbox).getAttribute("checked")));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Terms checkbox selected successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");


        // REGISTER
        StepTimer.startStep();

        WebElement registerBtn = wait.until
                (ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Register")));

        registerBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Register button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        // =====================================================
        // OTP SENT POPUP
        // =====================================================

        StepTimer.startStep();

        waitForOtpPopup();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("OTP sent successfully popup displayed | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        WebElement otpPopupOk = wait.until
                (ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("OK")));

        otpPopupOk.click();

        ExtentTestListener.logStep("OTP popup OK clicked");

        // =====================================================
        // GET OTP
        // =====================================================

        StepTimer.startStep();

        String otp = mobileUtil.getOTP(mobile);

        duration = StepTimer.endStep();

        if (otp == null || otp.trim().isEmpty()) {

            throw new RuntimeException(
                    "OTP was not received for mobile : " + mobile);
        }

        ExtentTestListener.logStep("OTP received successfully : " + otp
                        + " | Time Taken : " + String.format("%.2f", duration) + " sec");

        // =====================================================
        // VERIFY OTP
        // =====================================================
        Thread.sleep(5000);
        StepTimer.startStep();

        By verifyOtpLocator = AppiumBy.accessibilityId("Verify OTP");

        WebElement verifyOtp =
                wait.until(ExpectedConditions.elementToBeClickable(verifyOtpLocator));

        verifyOtp.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Verify OTP button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // WAIT FOR VERIFICATION RESPONSE
        // =====================================================

        try {

            By successOkLocator =
                    AppiumBy.xpath("//android.widget.Button[@content-desc='OK']");

            WebElement successOkBtn = wait.until(ExpectedConditions.elementToBeClickable(successOkLocator));

            ExtentTestListener.logStep("OTP verification successful popup displayed");

            // =================================================
            // SUCCESS POPUP OK
            // =================================================

            StepTimer.startStep();

            successOkBtn.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Registration Success OK button clicked | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        } catch (TimeoutException e) {

            ExtentTestListener.logStep("OTP verification popup was not displayed after Verify OTP");

            throw e;
        }
    }

    // =========================================================
    // WAIT FOR OTP POPUP
    // =========================================================

    private void waitForOtpPopup() {

        By okButton = AppiumBy.accessibilityId("OK");

        wait.until(ExpectedConditions.presenceOfElementLocated(okButton));
    }

    // =========================================================
    // HIDE KEYBOARD SAFELY
    // =========================================================

    private void hideKeyboard() {

        try {
            if (driver.isKeyboardShown()) {
                driver.hideKeyboard();
            }
        }
        catch (Exception ignored) {
        }
    }
}
