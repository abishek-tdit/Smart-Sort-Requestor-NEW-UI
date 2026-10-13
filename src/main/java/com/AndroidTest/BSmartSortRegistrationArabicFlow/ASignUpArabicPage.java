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

    // =========================================================
    // DRIVER / WAIT / UTILITY
    // =========================================================

    private final AndroidDriver driver;
    private final WebDriverWait wait;
    private final BMobileNumber mobileUtil;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ASignUpArabicPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        this.mobileUtil = new BMobileNumber();
    }


    // =========================================================
    // WAIT FOR VISIBLE ELEMENT
    // =========================================================

    private WebElement waitForElement(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }


    // =========================================================
    // WAIT AND CLICK
    // =========================================================

    private void waitAndClick(By locator) {

        for (int attempt = 1; attempt <= 3; attempt++) {

            try {

                WebElement element =
                        wait.until(
                                ExpectedConditions.elementToBeClickable(locator)
                        );

                element.click();

                return;

            } catch (StaleElementReferenceException e) {

                System.out.println(
                        "Stale element detected while clicking : "
                                + locator
                                + " | Attempt : "
                                + attempt
                );

                if (attempt == 3) {
                    throw e;
                }
            }
        }
    }


    // =========================================================
    // WAIT AND SEND KEYS
    // =========================================================

    private void waitAndSendKeys(
            By locator,
            String text) {

        for (int attempt = 1; attempt <= 3; attempt++) {

            try {

                WebElement element =
                        wait.until(
                                ExpectedConditions.visibilityOfElementLocated(locator)
                        );

                element.click();

                element.clear();

                element.sendKeys(text);

                return;

            } catch (StaleElementReferenceException e) {

                System.out.println(
                        "Stale element detected while entering text : "
                                + locator
                                + " | Attempt : "
                                + attempt
                );

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

            WebDriverWait shortWait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(5)
                    );

            for (int attempt = 1; attempt <= 3; attempt++) {

                try {

                    WebElement element =
                            shortWait.until(
                                    ExpectedConditions
                                            .visibilityOfElementLocated(locator)
                            );

                    element.click();

                    element.clear();

                    element.sendKeys(text);

                    ExtentTestListener.logStep(
                            fieldName
                                    + " entered successfully : "
                                    + text
                    );

                    return;

                } catch (StaleElementReferenceException e) {

                    if (attempt == 3) {
                        throw e;
                    }
                }
            }

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    fieldName
                            + " not visible - Skipped"
            );
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

        } catch (Exception ignored) {
        }
    }


    // =========================================================
    // SCROLL UP
    // =========================================================

    private void scrollUp(double percent) {

        driver.executeScript(
                "mobile: swipeGesture",
                Map.of(
                        "left", 300,
                        "top", 1100,
                        "width", 400,
                        "height", 600,
                        "direction", "up",
                        "percent", percent
                )
        );
    }


    // =========================================================
    // WAIT 5 SECONDS
    // =========================================================

    private void waitFiveSeconds() {

        long startTime =
                System.currentTimeMillis();

        WebDriverWait fiveSecondWait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(6)
                );

        fiveSecondWait.until(driver -> {

            long elapsed =
                    System.currentTimeMillis()
                            - startTime;

            return elapsed >= 5000;
        });
    }


    // =========================================================
    // ARABIC SIGN UP FLOW
    // =========================================================

    public void signUp() throws Exception {

        System.out.println(
                "========== ARABIC REGISTRATION FLOW STARTED =========="
        );

        double duration;


        // =====================================================
        // GENERATE REGISTRATION DATA
        // =====================================================

        String firstNameValue =
                mobileUtil.generateRandomArabicFirstName();

        String middleNameValue =
                mobileUtil.generateRandomArabicMiddleName();

        String lastNameValue =
                mobileUtil.generateRandomArabicLastName();

        String emailValue =
                mobileUtil.generateEmail(
                        firstNameValue,
                        middleNameValue,
                        lastNameValue
                );


        // =====================================================
        // LOG GENERATED DATA
        // =====================================================

        ExtentTestListener.logStep(
                "Generated First Name : "
                        + firstNameValue
        );

        ExtentTestListener.logStep(
                "Generated Middle Name : "
                        + middleNameValue
        );

        ExtentTestListener.logStep(
                "Generated Last Name : "
                        + lastNameValue
        );

        ExtentTestListener.logStep(
                "Generated Email : "
                        + emailValue
        );


        // =====================================================
        // STEP 1 - ENGLISH LANGUAGE BUTTON
        // =====================================================

        Thread.sleep(6000);

        StepTimer.startStep();

        By englishButtonLocator =
                AppiumBy.accessibilityId("English");

        waitAndClick(englishButtonLocator);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "English language button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 2 - SELECT ARABIC
        // =====================================================

        StepTimer.startStep();

        By arabicButton =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='AR\nالعربية']"
                );

        waitAndClick(arabicButton);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic language selected | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 3 - WAIT FOR ARABIC START PAGE
        // =====================================================

        By arabicStartNowLocator =
                AppiumBy.accessibilityId("ابدأ الآن");

        StepTimer.startStep();

        waitForElement(arabicStartNowLocator);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Start page loaded | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 4 - ARABIC START NOW
        // =====================================================

        StepTimer.startStep();

        waitAndClick(arabicStartNowLocator);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Start Now button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 5 - CITY DROPDOWN
        // =====================================================

        StepTimer.startStep();

        By cityDropdown =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='المدينة']"
                );

        waitAndClick(cityDropdown);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic City dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 6 - WAIT FOR CITY LIST
        // =====================================================

        By bqaiqCity =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='بقيق']"
                );

        waitForElement(bqaiqCity);


        // =====================================================
        // STEP 7 - SELECT BQAIQ
        // =====================================================

        StepTimer.startStep();

        waitAndClick(bqaiqCity);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic city 'بقيق' selected successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 8 - REQUESTOR / USER TYPE
        // =====================================================

        StepTimer.startStep();

        By requestorTypeDropdown =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='نوع الحساب']"
                );

        waitAndClick(requestorTypeDropdown);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Account Type dropdown clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 9 - DOMESTIC REQUESTOR
        // =====================================================
        StepTimer.startStep();

        By domesticRequestor =
                AppiumBy.accessibilityId("حساب فردي");

        waitAndClick(domesticRequestor);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Individual Account selected successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );



        // =====================================================
        // STEP 10 - SCROLL TO REGISTRATION FIELDS
        // =====================================================

        StepTimer.startStep();

        scrollUp(0.75);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Scrolled to registration fields | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 11 - FIRST NAME
        // =====================================================

        StepTimer.startStep();

        By firstNameLocator =
                AppiumBy.xpath(
                        "//android.widget.EditText[@hint='الاسم الأول']"
                );

        waitAndSendKeys(
                firstNameLocator,
                firstNameValue
        );

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "First Name entered : "
                        + firstNameValue
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // =====================================================
        // STEP 12 - MIDDLE NAME
        // =====================================================

        StepTimer.startStep();

        By middleNameLocator =
                AppiumBy.xpath(
                        "//android.widget.EditText[@hint='الاسم الأوسط']"
                );

        waitAndSendKeys(
                middleNameLocator,
                middleNameValue
        );

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Middle Name entered : "
                        + middleNameValue
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // =====================================================
        // STEP 13 - LAST NAME
        // =====================================================

        StepTimer.startStep();

        By lastNameLocator =
                AppiumBy.xpath(
                        "//android.widget.EditText[@hint='اسم العائلة']"
                );

        waitAndSendKeys(
                lastNameLocator,
                lastNameValue
        );

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Last Name entered : "
                        + lastNameValue
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // =====================================================
        // STEP 14 - GENDER DROPDOWN
        // =====================================================

        StepTimer.startStep();

        By genderDropdown =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='الجنس']"
                );

        waitAndClick(genderDropdown);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Gender dropdown clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 15 - SELECT MALE
        // =====================================================

        StepTimer.startStep();

        By maleOption =
                AppiumBy.accessibilityId("ذكر");

        waitAndClick(maleOption);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Male selected | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 16 - SCROLL TO MOBILE SECTION
        // =====================================================

        StepTimer.startStep();

        scrollUp(0.80);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Scrolled to Mobile section | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 17 - GENERATE MOBILE NUMBER
        // =====================================================

        String mobile =
                mobileUtil.getUniqueMobileNumber("bqaiq");

        ExtentTestListener.logStep(
                "Generated Mobile Number : "
                        + mobile
        );


        // =====================================================
        // STEP 18 - MOBILE NUMBER
        // =====================================================

        StepTimer.startStep();

        By mobileLocator =
                AppiumBy.xpath(
                        "//android.widget.EditText[@hint='أدخل رقم جوالك']"
                );

        WebElement mobileField =
                waitForElement(mobileLocator);

        ExtentTestListener.logStep(
                "Arabic Mobile field found successfully"
        );

        mobileField.click();

        mobileField.clear();

        mobileField.sendKeys(mobile);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Mobile Number entered successfully : "
                        + mobile
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // =====================================================
        // STEP 19 - EMAIL
        // =====================================================

        StepTimer.startStep();

        By emailLocator =
                AppiumBy.xpath(
                        "//android.widget.EditText[@hint='أدخل عنوان بريدك الإلكتروني']"
                );

        sendKeysIfVisible(
                emailLocator,
                emailValue,
                "Email Address"
        );

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Email step completed : "
                        + emailValue
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // =====================================================
        // STEP 20 - CHECKBOX
        // =====================================================

        StepTimer.startStep();

        By checkboxLocator =
                AppiumBy.className(
                        "android.widget.CheckBox"
                );

        waitAndClick(checkboxLocator);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Checkbox selected | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 21 - REGISTER
        // =====================================================

        StepTimer.startStep();

        By registerButton =
                AppiumBy.accessibilityId("تسجيل");

        waitAndClick(registerButton);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Register button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 22 - CONFIRM REGISTRATION POPUP
        // =====================================================

        StepTimer.startStep();

        By confirmationButton =
                AppiumBy.accessibilityId("تأكيد");

        waitAndClick(confirmationButton);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Confirmation popup clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 23 - GET OTP FROM DATABASE
        // =====================================================

        StepTimer.startStep();

        String otp =
                mobileUtil.getOTP(mobile);

        duration = StepTimer.endStep();


        if (otp == null ||
                otp.trim().isEmpty()) {

            throw new RuntimeException(
                    "OTP was not received for mobile : "
                            + mobile
            );
        }


        ExtentTestListener.logStep(
                "OTP received from database : "
                        + otp
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 24 - MANUAL OTP ENTRY
        // =====================================================

        ExtentTestListener.logStep(
                "Please enter OTP manually on the mobile device"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "PLEASE ENTER OTP MANUALLY"
        );

        System.out.println(
                "OTP : " + otp
        );

        System.out.println(
                "After OTP entry, automation will wait 5 seconds."
        );

        System.out.println(
                "=============================================="
        );


        // =====================================================
        // STEP 25 - WAIT 5 SECONDS
        // =====================================================

        StepTimer.startStep();

        waitFiveSeconds();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "5 seconds wait completed after manual OTP entry | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 26 - VERIFY OTP
        // =====================================================

        StepTimer.startStep();

        By verifyOtpButton =
                AppiumBy.accessibilityId(
                        "التحقق من رمز التحقق"
                );

        waitAndClick(verifyOtpButton);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic OTP Verify button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 27 - SUCCESS CONFIRMATION
        // =====================================================

        StepTimer.startStep();

        By successConfirmation =
                AppiumBy.accessibilityId("تأكيد");

        waitAndClick(successConfirmation);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Registration success confirmation clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 28 - SELECT ARABIC LANGUAGE
        // =====================================================
        Thread.sleep(6000);
        StepTimer.startStep();

        By arabicLanguage =
                AppiumBy.xpath(
                        "//android.view.View[@content-desc='العربية']"
                );

        waitAndClick(arabicLanguage);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic language button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // STEP 29 - SWITCH BACK TO ENGLISH
        // =====================================================

        StepTimer.startStep();

        By englishLanguage =
                AppiumBy.xpath(
                        "//android.widget.Button[contains(@content-desc,'English')]"
                );

        waitAndClick(englishLanguage);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "English language selected | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // =====================================================
        // FLOW COMPLETED
        // =====================================================

        ExtentTestListener.logStep(
                "====== Arabic Registration Flow completed successfully ======"
        );

        System.out.println(
                "========== ARABIC REGISTRATION FLOW COMPLETED =========="
        );
    }
}

