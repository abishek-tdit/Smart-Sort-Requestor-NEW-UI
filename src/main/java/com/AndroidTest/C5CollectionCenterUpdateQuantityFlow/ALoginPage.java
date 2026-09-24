package com.AndroidTest.C5CollectionCenterUpdateQuantityFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.StepTimer;


public class ALoginPage extends BaseClassMobile {

    public void login(String mobileNumber, String password) throws InterruptedException {

        try {

            // =========================================================
            // ENTER MOBILE NUMBER
            // =========================================================
            ExtentTestListener.logStep("Entering Mobile Number...");

            StepTimer.startStep();

            WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));

            mobileField.click();
            mobileField.clear();
            mobileField.sendKeys(mobileNumber);

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Mobile Number Entered | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =========================================================
            // ENTER PASSWORD
            // =========================================================
            ExtentTestListener.logStep("Entering Password...");

            StepTimer.startStep();

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated
                            (AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));

            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys(password);

            driver.hideKeyboard();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Password Entered | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");


            // =========================================================
            // CLICK SIGN IN
            // =========================================================
            ExtentTestListener.logStep("Clicking Sign In Button...");

            StepTimer.startStep();

            WebElement signInButton =
                    wait.until(ExpectedConditions.elementToBeClickable
                            (AppiumBy.accessibilityId("Sign In")));

            signInButton.click();

            duration = StepTimer.endStep();

            ExtentTestListener.logStep("Login Button Clicked Successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("Login Failed : " + e.getMessage());

            throw new RuntimeException("Login Flow Failed", e);
        }
    }
}