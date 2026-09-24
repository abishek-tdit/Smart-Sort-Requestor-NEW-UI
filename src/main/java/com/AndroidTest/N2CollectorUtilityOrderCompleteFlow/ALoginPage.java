package com.AndroidTest.N2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ALoginPage extends BaseClassMobile {

    public void login(String mobileNumber, String password) throws InterruptedException {

        try {
            //ENTER MOBILE NO
            ExtentTestListener.logStep("ENTER MOBILE NO");

            WebElement mobileField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().className(\"android.widget.EditText\").instance(0)")));

            mobileField.click();
            mobileField.clear();
            mobileField.sendKeys(mobileNumber);

            //PASSWORD
            ExtentTestListener.logStep("PASSWORD");

            WebElement passwordField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().className(\"android.widget.EditText\").instance(1)")));

            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys(password);
            driver.hideKeyboard();


            // Click Sign In
            ExtentTestListener.logStep("Click Sign In");

            WebElement signInButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Sign In")));

            signInButton.click();

            ExtentTestListener.logStep("Sign In Done");

        }
        catch (Exception e)
        {

            ExtentTestListener.logStep("Login Flow Completed");
            throw new RuntimeException("Login Flow Failed", e);
        }
    }
}