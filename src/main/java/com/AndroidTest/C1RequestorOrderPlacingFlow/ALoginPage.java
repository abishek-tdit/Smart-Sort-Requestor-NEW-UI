package com.AndroidTest.C1RequestorOrderPlacingFlow;

import Base.BasePage;
import Base.ExtentTestListener;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.StepTimer;

public class ALoginPage extends BasePage {

    // =========================================================
    // Constructor
    // =========================================================

    public ALoginPage(AndroidDriver driver) {
        super(driver);
    }


    // =========================================================
    // Locators
    // =========================================================

    private final By HAVE_AN_ACCOUNT = AppiumBy.accessibilityId("I have an Account");

    private final By MOBILE = By.xpath("(//android.widget.EditText)[1]");

    private final By PASSWORD = By.xpath("(//android.widget.EditText)[2]");

    private final By LOGIN = AppiumBy.accessibilityId("Login");


    // =========================================================
    // LOGIN FLOW
    // =========================================================

    public void login(String mobileNo, String passwordText) {

        // =====================================================
        // I HAVE AN ACCOUNT
        // =====================================================

        StepTimer.startStep();

        WebElement haveAnAccountButton =
                wait.until(ExpectedConditions.elementToBeClickable(HAVE_AN_ACCOUNT));

        haveAnAccountButton.click();

        double duration = StepTimer.endStep();

        ExtentTestListener.logStep("I have an Account button clicked | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");


        // =====================================================
        // MOBILE NUMBER
        // =====================================================

        StepTimer.startStep();

        sendKeys(MOBILE, mobileNo);

        duration = StepTimer.endStep();

        ExtentTestListener.logStep("Mobile Number Entered | Time Taken : "
                        + String.format("%.2f", duration)
                        + " sec");

        hideKeyboard();


        // =====================================================
        // WAIT FOR OTP TO BE GENERATED
        // =====================================================

        ExtentTestListener.logStep("Waiting 5 seconds for OTP to be generated...");

        try {
            Thread.sleep(7000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }


        // =====================================================
        // GET OTP FROM DATABASE
        // =====================================================

        StepTimer.startStep();

        BLoginOTP loginOTP = new BLoginOTP();

        String otp = loginOTP.getOTP(mobileNo);

        duration = StepTimer.endStep();


        // =====================================================
        // PRINT OTP IN CONSOLE
        // =====================================================

        if (otp != null && !otp.isEmpty()) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("LOGIN OTP : " + otp);
            System.out.println("PLEASE ENTER OTP MANUALLY");
            System.out.println("========================================");
            System.out.println();


            // =================================================
            // EXTENT REPORT
            // =================================================

            ExtentTestListener.logStep("Login OTP Generated : "
                            + otp
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            ExtentTestListener.logStep("Login OTP displayed in console. "
                            + "Please enter OTP manually.");
        }
        else
        {
            ExtentTestListener.logStep("Login OTP was not found for mobile : " + mobileNo);
        }


        // =====================================================
        // OPTIONAL PASSWORD
        // =====================================================

        if (isVisible(PASSWORD)) {

            StepTimer.startStep();

            sendKeys(PASSWORD, passwordText);

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Password Entered | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

            hideKeyboard();
        }
        else
        {
            ExtentTestListener.logStep("Password field NOT visible | "
                            + "Skipping Password Entry");
        }


        // =====================================================
        // OPTIONAL LOGIN BUTTON
        // =====================================================

        if (isVisible(LOGIN)) {

            StepTimer.startStep();

            WebElement loginButton =
                    wait.until(ExpectedConditions.elementToBeClickable(LOGIN));

            loginButton.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Login Button Clicked | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");
        }
        else
        {
            ExtentTestListener.logStep("Login button NOT visible | " + "Skipping Login Button");
        }


        // =====================================================
        // MANUAL OTP MESSAGE
        // =====================================================

        ExtentTestListener.logStep("Automation stopped after OTP retrieval. "
                        + "Please enter OTP manually in the application.");
    }


    // =========================================================
    // CHECK ELEMENT VISIBILITY
    // =========================================================

    private boolean isVisible(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }
}