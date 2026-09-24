package com.AndroidTest.BSmartSortRegistrationArabicFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.StepTimer;
import java.time.Duration;
import java.util.Map;

public class ASignUpArabicPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;
    private final BMobileNumber mobileUtil;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ASignUpArabicPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        this.mobileUtil = new BMobileNumber();
    }

    // =========================================================
    // WAIT FOR ELEMENT
    // =========================================================

    private WebElement waitForElement(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    // =========================================================
    // CLICK ELEMENT
    // =========================================================

    private void waitAndClick(By locator) {

        for (int attempt = 1; attempt <= 3; attempt++) {

            try {

                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

                element.click();

                return;

            } catch (StaleElementReferenceException e) {

                System.out.println("Stale element detected while clicking : "
                        + locator + " | Attempt : " + attempt);

                if (attempt == 3) {
                    throw e;
                }
            }
        }
    }

    // =========================================================
    // SEND KEYS
    // =========================================================

    private void waitAndSendKeys(
            By locator,
            String text) {

        for (int attempt = 1; attempt <= 3; attempt++) {

            try {

                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

                element.click();

                element.clear();

                element.sendKeys(text);

                return;
            }
            catch (StaleElementReferenceException e)
            {
                System.out.println("Stale element detected while entering text : "
                                + locator
                                + " | Attempt : "
                                + attempt);

                if (attempt == 3) {
                    throw e;
                }
            }
        }
    }

    // =========================================================
    // OPTIONAL FIELD
    // =========================================================

    private void sendKeysIfVisible(
            By locator,
            String text,
            String fieldName) {

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

            for (int attempt = 1; attempt <= 3; attempt++) {

                try {

                    WebElement element = shortWait.until
                            (ExpectedConditions.visibilityOfElementLocated(locator));

                    element.click();
                    element.clear();
                    element.sendKeys(text);

                    ExtentTestListener.logStep(fieldName + " entered successfully");

                    return;
                }
                catch (StaleElementReferenceException e)
                {
                    if (attempt == 3) {
                        throw e;
                    }
                }
            }
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep(fieldName + " not visible - Skipped");
        }
    }

    // =========================================================
    // HIDE KEYBOARD
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

    // =========================================================
    // SCROLL UP
    // =========================================================

    private void scrollUp(double percent) {

        driver.executeScript("mobile: swipeGesture",
                Map.of("left", 300,
                        "top", 1100,
                        "width", 400,
                        "height", 600,
                        "direction", "up",
                        "percent", percent));
    }

    // =========================================================
    // WAIT 5 SECONDS
    // =========================================================

    private void waitFiveSeconds() {

        long startTime = System.currentTimeMillis();

        WebDriverWait fiveSecondWait = new WebDriverWait(driver, Duration.ofSeconds(6));

        fiveSecondWait.until(driver -> {

            long elapsed = System.currentTimeMillis()
                    - startTime;
            return elapsed >= 5000;
        });
    }

    // =========================================================
    // ARABIC SIGN UP FLOW
    // =========================================================

    public void signUp() throws Exception {

        System.out.println("========== ARABIC REGISTRATION FLOW STARTED ==========");

        double duration;

        // =====================================================
        // ENGLISH LANGUAGE BUTTON
        // =====================================================
        Thread.sleep(6000);
        StepTimer.startStep();

        By englishButtonLocator = AppiumBy.accessibilityId("English");

        waitAndClick(englishButtonLocator);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("English language button clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // SELECT ARABIC
        // =====================================================

        StepTimer.startStep();

        By arabicButton = AppiumBy.xpath("//android.widget.Button[@content-desc='AR\nالعربية']");

        waitAndClick(arabicButton);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic language selected | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // WAIT FOR ARABIC START PAGE
        // =====================================================

        By arabicStartNowLocator = AppiumBy.accessibilityId("ابدأ الآن");

        StepTimer.startStep();

        wait.until
                (ExpectedConditions.visibilityOfElementLocated(arabicStartNowLocator));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic Start page loaded | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // ARABIC START NOW
        // =====================================================

        StepTimer.startStep();

        waitAndClick(arabicStartNowLocator);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic Start Now button clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");


        // =====================================================
        // OPEN CITY DROPDOWN
        // =====================================================
        Thread.sleep(6000);
        StepTimer.startStep();

        By cityDropdown = AppiumBy.xpath("//android.widget.Button[@content-desc='المدينة']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement city = wait.until(ExpectedConditions.presenceOfElementLocated(cityDropdown));

        city.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic City dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // WAIT FOR CITY LIST
        // =====================================================
        By bqaiqCity = AppiumBy.xpath("//android.widget.Button[@content-desc='بقيق']");

        wait.until(ExpectedConditions.presenceOfElementLocated(bqaiqCity));

        // =====================================================
        // SELECT BQAIQ
        // =====================================================
        StepTimer.startStep();

        WebElement bqaiq = wait.until(ExpectedConditions.visibilityOfElementLocated(bqaiqCity));

        bqaiq.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic city 'بقيق' selected successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // REQUESTOR TYPE
        // =====================================================
        Thread.sleep(3000);
        StepTimer.startStep();

        By requestorTypeDropdown = AppiumBy.accessibilityId("نوع الحساب");

        waitAndClick(requestorTypeDropdown);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Requestor Type dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec"
        );

        // =====================================================
        // DOMESTIC REQUESTOR
        // =====================================================
        Thread.sleep(3000);
        StepTimer.startStep();

        By domesticRequestor =
                AppiumBy.accessibilityId("حساب فردي");

        waitAndClick(domesticRequestor);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Domestic Requestor selected successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec"
        );

        // =====================================================
        // SCROLL TO REGISTRATION FIELDS
        // =====================================================

        StepTimer.startStep();

        scrollUp(0.75);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Scrolled to registration fields | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // FIRST NAME
        // =====================================================

        StepTimer.startStep();

        waitAndSendKeys
                (AppiumBy.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[1]"), "رام");

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("First Name entered | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        hideKeyboard();

        // =====================================================
        // MIDDLE NAME
        // =====================================================

        StepTimer.startStep();

        waitAndSendKeys
                (AppiumBy.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[2]"), "كومار");

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Middle Name entered | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        hideKeyboard();

        // =====================================================
        // LAST NAME
        // =====================================================

        StepTimer.startStep();

        waitAndSendKeys
                (AppiumBy.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[3]"), "كومار");

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Last Name entered | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        hideKeyboard();

        // =====================================================
        // GENDER DROPDOWN
        // =====================================================

        StepTimer.startStep();

        By genderDropdown = AppiumBy.xpath("//android.widget.Button[@content-desc='الجنس']");

        waitAndClick(genderDropdown);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Gender dropdown clicked successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // SELECT MALE
        // =====================================================

        StepTimer.startStep();

        waitAndClick(AppiumBy.accessibilityId("ذكر"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Male selected | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // SCROLL TO MOBILE SECTION
        // =====================================================

        StepTimer.startStep();

        scrollUp(0.80);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Scrolled to Mobile section | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // GENERATE MOBILE NUMBER
        // =====================================================

        String mobile = mobileUtil.getUniqueMobileNumber("bqaiq");

        ExtentTestListener.logStep("Generated Mobile Number : " + mobile);

        // =====================================================
        // MOBILE NUMBER
        // HINT BASED LOCATOR
        // =====================================================

        StepTimer.startStep();

        By mobileLocator = AppiumBy.xpath("//android.widget.EditText[@hint='أدخل رقم جوالك']");

        WebElement mobileField = wait.until
                (ExpectedConditions.visibilityOfElementLocated(mobileLocator));

        ExtentTestListener.logStep("Arabic Mobile field found successfully");

        mobileField.click();
        mobileField.clear();
        mobileField.sendKeys(mobile);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic Mobile Number entered successfully : "
                + mobile + " | Time Taken : "
                + String.format("%.2f", duration) + " sec");

        hideKeyboard();

        // =====================================================
        // EMAIL
        // =====================================================

        StepTimer.startStep();

        By emailLocator = AppiumBy.xpath("//android.widget.EditText[@hint='أدخل عنوان بريدك الإلكتروني']");

        sendKeysIfVisible(emailLocator,
                "abishek251295@gmail.com", "Email Address");

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Email step completed | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        hideKeyboard();

        // =====================================================
        // CHECKBOX
        // =====================================================

        StepTimer.startStep();

        waitAndClick(AppiumBy.className("android.widget.CheckBox"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Checkbox selected | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // REGISTER
        // =====================================================

        StepTimer.startStep();

        waitAndClick(AppiumBy.accessibilityId("تسجيل"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic Register button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // CONFIRM REGISTRATION POPUP
        // =====================================================

        StepTimer.startStep();

        waitAndClick(AppiumBy.accessibilityId("تأكيد"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Confirmation popup clicked successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // GET OTP FROM DATABASE
        // =====================================================

        StepTimer.startStep();

        String otp = mobileUtil.getOTP(mobile);

        duration = StepTimer.endStep();

        if (otp == null || otp.trim().isEmpty()) {

            throw new RuntimeException("OTP was not received for mobile : " + mobile);
        }

        ExtentTestListener.logStep("OTP received from database : "
                        + otp
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        // =====================================================
        // MANUAL OTP ENTRY
        // =====================================================

        ExtentTestListener.logStep("Please enter OTP manually on the mobile device");

        System.out.println("==============================================");
        System.out.println("PLEASE ENTER OTP MANUALLY");
        System.out.println("OTP : " + otp);
        System.out.println("After OTP entry, automation will wait 5 seconds.");
        System.out.println("==============================================");

        // =====================================================
        // WAIT 5 SECONDS AFTER MANUAL OTP ENTRY
        // =====================================================

        StepTimer.startStep();

        waitFiveSeconds();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("5 seconds wait completed after manual OTP entry | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        // =====================================================
        // CLICK VERIFY OTP - ARABIC
        // =====================================================
        Thread.sleep(4000);

        StepTimer.startStep();

        By verifyOtpButton = AppiumBy.accessibilityId("التحقق من رمز التحقق");

        waitAndClick(verifyOtpButton);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic OTP Verify button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // SUCCESS CONFIRMATION
        // =====================================================
        Thread.sleep(4000);
        StepTimer.startStep();

        waitAndClick(AppiumBy.accessibilityId("تأكيد"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Registration success confirmation clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // SELECT ARABIC LANGUAGE
        // =====================================================
        Thread.sleep(6000);
        StepTimer.startStep();

        waitAndClick(AppiumBy.xpath(
                "//android.view.View[@content-desc='العربية']"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic language button clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // SWITCH BACK TO ENGLISH
        // =====================================================

        StepTimer.startStep();

        waitAndClick(AppiumBy.xpath(
                        "//android.widget.Button[contains(@content-desc,'English')]"));

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("English language selected | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // =====================================================
        // FLOW COMPLETED
        // =====================================================

        ExtentTestListener.logStep("====== Arabic Registration Flow completed successfully ======");
        System.out.println("========== ARABIC REGISTRATION FLOW COMPLETED ==========");
    }
}
