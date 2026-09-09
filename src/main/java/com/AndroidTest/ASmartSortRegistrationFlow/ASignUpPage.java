package com.AndroidTest.ASmartSortRegistrationFlow;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Base.ExtentTestListener;
import utilities.MobileUtilities;
import utilities.StepTimer;
import java.time.Duration;

public class ASignUpPage {

    AndroidDriver driver;
    MobileUtilities util;
    private final WebDriverWait wait;

    //CONSTRUCTOR
    public ASignUpPage(AndroidDriver driver) {
        this.driver = driver;
        util = new MobileUtilities(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // SIGN UP FLOW
    public void signUp() throws Exception {

        // ================= REGISTER =================
        StepTimer.startStep();

        util.click(util.waitForAccessibilityId("Register"));

        double duration = StepTimer.endStep();
        ExtentTestListener.logStep("Home Register button clicked | Time Taken : "
                + String.format("%.2f", duration) + " sec");

        Thread.sleep(4000);
        // ================= CITY =================
        StepTimer.startStep();

        util.click(util.waitForAccessibilityId("Select City"));

        duration = StepTimer.endStep();
        ExtentTestListener.logStep("Select City dropdown clicked | Time Taken : "
                + String.format("%.2f", duration) + " sec");


        String city = "Bqaiq";

        StepTimer.startStep();

        util.click(util.waitForAccessibilityId(city));

        duration = StepTimer.endStep();
        ExtentTestListener.logStep("City selected successfully | Time Taken : "
                + String.format("%.2f", duration) + " sec");


        // ================= REQUESTOR TYPE =================
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


        // ================= FIRST NAME =================
        StepTimer.startStep();

        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath(
                        "//android.widget.ScrollView/android.view.View/android.widget.EditText[1]")));

        firstName.click();
        firstName.sendKeys("Naveen");

        duration = StepTimer.endStep();
        ExtentTestListener.logStep("First Name entered successfully | Time Taken : "
                + String.format("%.2f", duration) + " sec");

        try {
            driver.hideKeyboard();
        }
        catch (Exception ignored) {
        }

        //LAST NAME
        StepTimer.startStep();

        WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath(
                        "//android.widget.ScrollView/android.view.View/android.widget.EditText[3]")));

        lastName.click();
        lastName.sendKeys("Raja");

        duration = StepTimer.endStep();
        ExtentTestListener.logStep("Last Name entered successfully | Time Taken : "
                + String.format("%.2f", duration) + " sec");


        try {
            driver.hideKeyboard();
        }
        catch (Exception ignored) {
        }


        // ================= GENDER =================
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

        // ================= MOBILE =================

        BMobileNumber mobileUtil = new BMobileNumber();
        String mobile = mobileUtil.getUniqueMobileNumber(city);

        ExtentTestListener.logStep("Generated Mobile Number : " + mobile);

        StepTimer.startStep();

        WebElement mobileNumber = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("//android.widget.EditText[@hint='Enter your mobile number']")
                )
        );

        mobileNumber.click();
        mobileNumber.clear();
        mobileNumber.sendKeys(mobile);

        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Mobile Number entered successfully | Time Taken : "
                        + String.format("%.2f", duration) + " sec");

// ================= EMAIL =================

        try {

            StepTimer.startStep();

            WebElement email = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath(
                                    "//android.widget.EditText[@hint='Enter your email address']")
                    )
            );

            email.click();
            email.clear();
            email.sendKeys("abishek251295@gmail.com");

            try {
                driver.hideKeyboard();
            } catch (Exception ignored) {
            }

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Email Address entered successfully | Time Taken : "
                            + String.format("%.2f", duration) + " sec");

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Email field not available. Skipping... | " + e.getMessage());
        }

// ================= PASSWORD =================
        StepTimer.startStep();

        try {
            WebElement password = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.EditText[@hint='Password']")
                    ));

            password.click();
            password.clear();
            password.sendKeys("Admin@194");

            try {
                driver.hideKeyboard();
            } catch (Exception ignored) {
            }

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Password entered successfully | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

        } catch (Exception e) {
            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Password field not visible - Skipped | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );
        }


// ================= SCROLL TO CONFIRM PASSWORD =================
        try {
            WebElement confirmPasswordField = driver.findElement(
                    AppiumBy.xpath("//android.widget.EditText[@hint='Confirm Password']")
            );

            if (!confirmPasswordField.isDisplayed()) {
                driver.executeScript(
                        "mobile: swipeGesture",
                        java.util.Map.of(
                                "left", 300,
                                "top", 1200,
                                "width", 400,
                                "height", 600,
                                "direction", "up",
                                "percent", 0.50
                        )
                );
            }

        } catch (Exception ignored) {
            // Field not currently available - scroll
            driver.executeScript(
                    "mobile: swipeGesture",
                    java.util.Map.of(
                            "left", 300,
                            "top", 1200,
                            "width", 400,
                            "height", 600,
                            "direction", "up",
                            "percent", 0.50
                    )
            );
        }


// ================= CONFIRM PASSWORD =================
        StepTimer.startStep();

        try {
            WebElement confirmPassword = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.EditText[@hint='Confirm Password']")
                    ));

            confirmPassword.click();
            confirmPassword.clear();
            confirmPassword.sendKeys("Admin@194");

            try {
                driver.hideKeyboard();
            } catch (Exception ignored) {
            }

            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Confirm Password entered successfully | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );

        } catch (Exception e) {
            duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Confirm Password field not visible - Skipped | Time Taken : "
                            + String.format("%.2f", duration) + " sec"
            );
        }


// ================= TERMS CHECKBOX =================
        StepTimer.startStep();

        By TERMS_CHECKBOX = AppiumBy.className("android.widget.CheckBox");

        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(TERMS_CHECKBOX)
        );

        String before = checkbox.getAttribute("checked");

        if (!"true".equalsIgnoreCase(before)) {
            checkbox.click();
        }

        wait.until(driver ->
                "true".equalsIgnoreCase(
                        driver.findElement(TERMS_CHECKBOX).getAttribute("checked")
                )
        );

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Terms checkbox selected successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


        // ================= REGISTER BUTTON =================
        StepTimer.startStep();

        WebElement registerBtn = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Register")));

        registerBtn.click();

        duration = StepTimer.endStep();
        ExtentTestListener.logStep("Register button clicked successfully | Time Taken : "
                + String.format("%.2f", duration) + " sec");


        // ================= OTP POPUP =================
        StepTimer.startStep();

        WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("OK")));

        okBtn.click();

        duration = StepTimer.endStep();
        ExtentTestListener.logStep("OTP sent successfully popup OK clicked | Time Taken : "
                + String.format("%.2f", duration) + " sec");


        // ================= GET OTP =================
        StepTimer.startStep();

        String otp = mobileUtil.getOTP(mobile);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Generated OTP : " + otp
                + " | Time Taken : "
                + String.format("%.2f", duration) + " sec");

        ExtentTestListener.logStep("Please enter OTP");


        // ================= CONFIRM OTP =================
        Thread.sleep(6000);
        StepTimer.startStep();

        WebElement verifyOtpBtn = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        ).until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.Button[@content-desc=\"Verify OTP\"]")
        ));

        verifyOtpBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Verify OTP button clicked successfully | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );


// ================= SUCCESS POPUP =================
        StepTimer.startStep();

        WebElement successOkBtn = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        ).until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.Button[@content-desc=\"OK\"]")
        ));

        successOkBtn.click();

        duration = StepTimer.endStep();

        ExtentTestListener.logStep(
                "Registration Success OK button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );
    }
}