package com.AndroidTest.F2RequestorRescheduleFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DLoginCollectorPage extends BaseClassMobile {

    public void login(String mobileNumber, String password) {


        try {

            // Wait until Mobile Number field is visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

            By mobileFieldLocator = AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.widget.EditText\")");

            WebElement mobileField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(mobileFieldLocator));

            ExtentTestListener.logStep("Mobile Number field is visible");

            //Enter Mobile Number
            mobileField.click();
            mobileField.clear();
            mobileField.sendKeys(mobileNumber);

            ExtentTestListener.logStep("Mobile Number Entered");

            //Enter Password
            WebElement passwordField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().className(\"android.widget.EditText\").instance(1)")));

            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys(password);
            ExtentTestListener.logStep("Password Entered");

            driver.hideKeyboard();

            // Click Sign In
            ExtentTestListener.logStep("Clicking Sign In Button...");

            WebElement signInButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Sign In")));

            signInButton.click();

            ExtentTestListener.logStep("Collector logged in successfully.");

        } catch (Exception e) {
            ExtentTestListener.logStep("Collector Login Failed: " + e.getMessage());
            throw new RuntimeException("Collector Login Flow Failed", e);
        }
    }
}