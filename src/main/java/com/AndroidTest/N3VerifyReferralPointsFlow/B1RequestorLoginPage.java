package com.AndroidTest.N3VerifyReferralPointsFlow;

import Base.BasePage;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.StepTimer;

import java.time.Duration;

public class B1RequestorLoginPage extends BasePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =====================================================
    // LOCATORS
    // =====================================================

    private final By HAVE_AN_ACCOUNT =
            AppiumBy.accessibilityId("I have an Account");

    private final By MOBILE =
            AppiumBy.xpath(
                    "//android.widget.EditText[@hint='Enter your mobile number']"
            );

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public B1RequestorLoginPage(AndroidDriver driver) {
        super(driver);

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );
    }

    // =====================================================
    // LOGIN
    // =====================================================

    public void login(String mobile) {

        System.out.println(
                "========== LOGIN STARTED =========="
        );

        // =====================================================
        // I HAVE AN ACCOUNT
        // =====================================================

        StepTimer.startStep();

        WebElement haveAnAccountButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                HAVE_AN_ACCOUNT
                        )
                );

        haveAnAccountButton.click();

        double duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "I have an Account button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        // =====================================================
        // MOBILE NUMBER
        // =====================================================

        StepTimer.startStep();

        WebElement mobileField =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                MOBILE
                        )
                );

        mobileField.click();
        mobileField.clear();
        mobileField.sendKeys(mobile);

        duration =
                StepTimer.endStep();

        ExtentTestListener.logStep(
                "Mobile Number entered in Login : "
                        + mobile
                        + " | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec"
        );

        System.out.println(
                "Mobile Number entered : "
                        + mobile
        );

        // =====================================================
        // HIDE KEYBOARD
        // =====================================================

        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }
    }
}