package com.AndroidTest.C3CollectionCenterAcceptOrderFlow;
import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ALoginPage extends BaseClassMobile{

    public void login(String mobileNumber, String password) throws InterruptedException {

        try {
            //Mob-no
            ExtentTestListener.logStep("Enter Mobile Number");

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

            ExtentTestListener.logStep("Login Done");

        }
        catch (Exception e)
        {
            System.out.println("Login Failed : " + e.getMessage());
            throw new RuntimeException("Login Flow Failed", e);
        }
    }
}