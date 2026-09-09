package com.AndroidTest.BSmartSortRegistrationArabicFlow;

import Base.ExtentTestListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import utilities.MobileUtilities;
import utilities.StepTimer;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class ASignUpArabicPage {

    private final AndroidDriver driver;
    private final MobileUtilities util;
    private final WebDriverWait wait;
    private final BMobileNumber mobileUtil;

    // ==========================================================
    // CONSTRUCTOR
    // ==========================================================

    public ASignUpArabicPage(AndroidDriver driver) {

        this.driver = driver;
        this.util = new MobileUtilities(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.mobileUtil = new BMobileNumber();
    }


    // ==========================================================
    // WAIT FOR ELEMENT
    // ==========================================================

    private WebElement waitForElement(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }


    // ==========================================================
    // WAIT AND CLICK
    // ==========================================================

    private void waitAndClick(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );

        element.click();
    }


    // ==========================================================
    // WAIT AND SEND KEYS
    // ==========================================================

    private void waitAndSendKeys(By locator, String text) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        element.click();
        element.clear();
        element.sendKeys(text);
    }


    // ==========================================================
    // OPTIONAL SEND KEYS
    // ==========================================================

    private void sendKeysIfVisible(
            By locator,
            String text,
            String fieldName) {

        try {

            WebDriverWait shortWait =
                    new WebDriverWait(driver, Duration.ofSeconds(3));

            WebElement element = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            );

            if (element.isDisplayed()) {

                element.click();
                element.clear();
                element.sendKeys(text);

                ExtentTestListener.logStep(
                        fieldName + " entered successfully"
                );
            }

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    fieldName + " not visible - Skipped"
            );
        }
    }


    // ==========================================================
    // OPTIONAL CLICK
    // ==========================================================

    private boolean clickIfVisible(
            By locator,
            String fieldName) {

        try {

            WebDriverWait shortWait =
                    new WebDriverWait(driver, Duration.ofSeconds(3));

            WebElement element = shortWait.until(
                    ExpectedConditions.elementToBeClickable(locator)
            );

            if (element.isDisplayed()) {

                element.click();

                ExtentTestListener.logStep(
                        fieldName + " clicked successfully"
                );

                return true;
            }

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    fieldName + " not visible - Skipped"
            );
        }

        return false;
    }


    // ==========================================================
    // HIDE KEYBOARD
    // ==========================================================

    private void hideKeyboard() {

        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }
    }


    // ==========================================================
    // SCROLL UP
    // ==========================================================

    private void scrollUp(double percent) {

        driver.executeScript(
                "mobile: swipeGesture",
                Map.of(
                        "left", 300,
                        "top", 1200,
                        "width", 400,
                        "height", 600,
                        "direction", "up",
                        "percent", percent
                )
        );
    }


    // ==========================================================
    // SIGN UP FLOW
    // ==========================================================

    public void signUp() throws Exception {


        // CLICK ENGLISH
        Thread.sleep(6000);
        StepTimer.startStep();

        WebElement englishButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.accessibilityId("English")));

        englishButton.click();

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep("English language button clicked | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

        // ==========================================================
        // SELECT ARABIC
        // ==========================================================

        StepTimer.startStep();

        By arabicButton = AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='AR\nالعربية']");

        WebElement arabic = wait.until(
                        ExpectedConditions.elementToBeClickable(arabicButton));

        arabic.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Arabic language selected | Time Taken : "
                        + String.format("%.2f", duration) + " sec");


        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement signUpBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.view.View[contains(@content-desc,'ليس لديك حساب')]")
                    )
            );

            ((JavascriptExecutor) driver).executeScript(
                    "mobile: clickGesture",
                    Map.of(
                            "elementId",
                            ((RemoteWebElement) signUpBtn).getId()
                    )
            );

            System.out.println("Arabic Sign Up button clicked");

        } catch (Exception e) {

            throw new RuntimeException(
                    "Arabic Sign-Up button not found",
                    e
            );
        }

        // ==========================================================
        // CITY DROPDOWN
        // ==========================================================

        StepTimer.startStep();

        By cityDropdown =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='المدينة']"
                );

        WebElement cityButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                cityDropdown
                        )
                );

        cityButton.click();

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic City dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // SELECT BQAIQ CITY
        // ==========================================================

        StepTimer.startStep();

        By bqaiqCity =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='بقيق']"
                );

        WebElement cityOption =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                bqaiqCity
                        )
                );

        cityOption.click();

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic city 'بقيق' selected successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // REQUESTOR TYPE DROPDOWN
        // ==========================================================

        StepTimer.startStep();

        By requestorTypeDropdown =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='نوع العميل']"
                );

        WebElement requestorButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                requestorTypeDropdown
                        )
                );

        requestorButton.click();

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Requestor Type dropdown clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // SELECT DOMESTIC REQUESTOR
        // ==========================================================

        StepTimer.startStep();

        By domesticRequestor =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='مستخدم فردي']"
                );

        WebElement domesticOption =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                domesticRequestor
                        )
                );

        domesticOption.click();

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Domestic Requestor selected successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // SCROLL TO REGISTRATION FIELDS
        // ==========================================================

        StepTimer.startStep();

        scrollUp(0.75);

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Scrolled to registration fields | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // FIRST NAME
        // ==========================================================

        StepTimer.startStep();

        waitAndSendKeys(
                AppiumBy.xpath(
                        "//android.widget.ScrollView/android.view.View/android.widget.EditText[1]"
                ),
                "رام"
        );

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "First Name entered | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // ==========================================================
        // MIDDLE NAME
        // ==========================================================

        StepTimer.startStep();

        waitAndSendKeys(
                AppiumBy.xpath(
                        "//android.widget.ScrollView/android.view.View/android.widget.EditText[2]"
                ),
                "كومار"
        );

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Middle Name entered | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // ==========================================================
        // LAST NAME
        // ==========================================================

        StepTimer.startStep();

        waitAndSendKeys(
                AppiumBy.xpath(
                        "//android.widget.ScrollView/android.view.View/android.widget.EditText[3]"
                ),
                "كومار"
        );

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Last Name entered | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // ==========================================================
        // GENDER
        // ==========================================================

        StepTimer.startStep();

        By genderDropdown =
                AppiumBy.xpath(
                        "//android.widget.Button[@content-desc='الجنس']"
                );

        WebElement genderButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                genderDropdown
                        )
                );

        genderButton.click();

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Gender dropdown clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // SELECT MALE
        // ==========================================================

        StepTimer.startStep();

        waitAndClick(
                AppiumBy.accessibilityId("ذكر")
        );

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Male selected | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // SCROLL TO MOBILE SECTION
        // ==========================================================

        StepTimer.startStep();

        scrollUp(0.80);

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Scrolled to Mobile section | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // MOBILE NUMBER
        // ==========================================================

        String mobile =
                mobileUtil.getUniqueMobileNumber("bqaiq");

        ExtentTestListener.logStep(
                "Generated Mobile Number : " + mobile
        );

        StepTimer.startStep();

        waitAndSendKeys(
                AppiumBy.androidUIAutomator(
                        "new UiSelector()"
                                + ".className(\"android.widget.EditText\")"
                                + ".instance(3)"
                ),
                mobile
        );

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Mobile Number entered successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // ==========================================================
        // EMAIL
        // ==========================================================

        StepTimer.startStep();

        sendKeysIfVisible(
                AppiumBy.androidUIAutomator(
                        "new UiSelector()"
                                + ".className(\"android.widget.EditText\")"
                                + ".instance(4)"
                ),
                "abishek251295@gmail.com",
                "Email Address"
        );

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Email entered successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        hideKeyboard();


        // ==========================================================
        // CHECKBOX
        // ==========================================================

        StepTimer.startStep();

        waitAndClick(
                AppiumBy.xpath(
                        "//android.widget.CheckBox"
                )
        );

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Checkbox selected | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // REGISTER BUTTON
        // ==========================================================

        StepTimer.startStep();

        waitAndClick(
                AppiumBy.accessibilityId("تسجيل")
        );

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic Register button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // CONFIRM POPUP
        // ==========================================================

        StepTimer.startStep();

        waitAndClick(
                AppiumBy.accessibilityId("تأكيد")
        );

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Confirmation popup clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // WAIT FOR OTP
        // ==========================================================

        Thread.sleep(5000);


        // ==========================================================
        // GET OTP
        // ==========================================================

        StepTimer.startStep();

        String otp =
                mobileUtil.getOTP(mobile);

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "OTP from DB : "
                        + otp
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // WAIT BEFORE OTP CONFIRM
        // ==========================================================

        Thread.sleep(6000);


        // ==========================================================
        // VERIFY OTP
        // ==========================================================

        StepTimer.startStep();

        WebElement verifyOtpButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                AppiumBy.accessibilityId(
                                        "Verify OTP"
                                )
                        )
                );

        verifyOtpButton.click();

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Verify OTP button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ==========================================================
        // OK POPUP - تأكيد
        // ==========================================================

        StepTimer.startStep();

        WebElement okPopupButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                AppiumBy.accessibilityId(
                                        "تأكيد"
                                )
                        )
                );

        okPopupButton.click();

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "OK popup Confirm button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        // CHANGE LANGUAGE ARABIC -> ENGLISH
        Thread.sleep(6000);
        StepTimer.startStep();

        WebElement arabicButton1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.xpath(
                                "//android.view.View[@content-desc='العربية']"
                        )
                )
        );

        arabicButton1.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Arabic language button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        // ==========================================================
// CLICK ENGLISH
// ==========================================================

        StepTimer.startStep();

        WebElement englishButton1 = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath(
                                "//android.widget.Button[contains(@content-desc,'English')]"
                        )
                )
        );

        englishButton1.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "English language selected | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        // ==========================================================
        // FLOW COMPLETED
        // ==========================================================

        ExtentTestListener.logStep(
                "====== Arabic Registration Flow completed successfully ======"
        );
    }
}