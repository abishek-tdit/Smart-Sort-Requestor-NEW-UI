package com.AndroidTest.LCustomerSupportFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BContactUsPage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================================================
    // Constructor
    // =========================================================

    public BContactUsPage() {
        this.driver = BaseClassMobile.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // =========================================================
    // Locators
    // =========================================================

    // Contact Now
    private final By CONTACT_NOW =
            AppiumBy.accessibilityId("Contact Now");

    // Name
    private final By NAME_FIELD =
            By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[1]");

    // Email
    private final By EMAIL_FIELD =
            By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[2]");

    // Mobile Number
    private final By MOBILE_FIELD =
            By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[3]");

    // Message
    private final By MESSAGE_FIELD =
            By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[4]");

    // Send
    private final By SEND_BUTTON =
            AppiumBy.accessibilityId("Send");

    // OK Popup
    private final By OK_BUTTON =
            AppiumBy.accessibilityId("OK");


    // =========================================================
    // Click Contact Now
    // =========================================================

    public void clickContactNow() {

        long startTime = System.currentTimeMillis();

        try {

            WebElement contactNow = wait.until(
                    ExpectedConditions.elementToBeClickable(CONTACT_NOW)
            );

            contactNow.click();

            ExtentTestListener.logStep(
                    "Contact Now clicked successfully"
            );

            System.out.println(
                    "Contact Now clicked successfully"
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Failed to click Contact Now: " + e.getMessage()
            );

            throw e;

        } finally {

            long timeTaken =
                    System.currentTimeMillis() - startTime;

            System.out.println(
                    "Time Taken - Click Contact Now: "
                            + timeTaken + " ms"
            );
        }
    }


    // =========================================================
    // Enter Name
    // =========================================================

    public void enterName(String name) {

        long startTime = System.currentTimeMillis();

        try {

            WebElement nameField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(NAME_FIELD)
            );

            nameField.click();
            nameField.clear();
            nameField.sendKeys(name);

            // Hide keyboard after entering Name
            driver.hideKeyboard();

            ExtentTestListener.logStep(
                    "Name entered and keyboard hidden: " + name
            );

            System.out.println(
                    "Name entered and keyboard hidden: " + name
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Failed to enter name: " + e.getMessage()
            );

            throw e;

        } finally {

            long timeTaken =
                    System.currentTimeMillis() - startTime;

            System.out.println(
                    "Time Taken - Enter Name: "
                            + timeTaken + " ms"
            );
        }
    }


    // =========================================================
    // Enter Email
    // =========================================================

    public void enterEmail(String email) {

        long startTime = System.currentTimeMillis();

        try {

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD)
            );

            emailField.click();
            emailField.clear();
            emailField.sendKeys(email);

            // Hide keyboard after entering Email
            driver.hideKeyboard();

            ExtentTestListener.logStep(
                    "Email entered and keyboard hidden: " + email
            );

            System.out.println(
                    "Email entered and keyboard hidden: " + email
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Failed to enter email: " + e.getMessage()
            );

            throw e;

        } finally {

            long timeTaken =
                    System.currentTimeMillis() - startTime;

            System.out.println(
                    "Time Taken - Enter Email: "
                            + timeTaken + " ms"
            );
        }
    }


    // =========================================================
    // Enter Mobile Number
    // =========================================================

    public void enterMobileNumber(String mobileNumber) {

        long startTime = System.currentTimeMillis();

        try {

            WebElement mobileField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(MOBILE_FIELD)
            );

            mobileField.click();
            mobileField.clear();
            mobileField.sendKeys(mobileNumber);

            // Hide keyboard after entering Mobile Number
            driver.hideKeyboard();

            ExtentTestListener.logStep(
                    "Mobile number entered and keyboard hidden: "
                            + mobileNumber
            );

            System.out.println(
                    "Mobile number entered and keyboard hidden: "
                            + mobileNumber
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Failed to enter mobile number: "
                            + e.getMessage()
            );

            throw e;

        } finally {

            long timeTaken =
                    System.currentTimeMillis() - startTime;

            System.out.println(
                    "Time Taken - Enter Mobile Number: "
                            + timeTaken + " ms"
            );
        }
    }


    // =========================================================
    // Enter Message
    // =========================================================

    public void enterMessage(String message) {

        long startTime = System.currentTimeMillis();

        try {

            WebElement messageField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(MESSAGE_FIELD)
            );

            messageField.click();
            messageField.clear();
            messageField.sendKeys(message);

            // Hide keyboard after entering Message
            driver.hideKeyboard();

            ExtentTestListener.logStep(
                    "Message entered and keyboard hidden: " + message
            );

            System.out.println(
                    "Message entered and keyboard hidden: " + message
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Failed to enter message: "
                            + e.getMessage()
            );

            throw e;

        } finally {

            long timeTaken =
                    System.currentTimeMillis() - startTime;

            System.out.println(
                    "Time Taken - Enter Message: "
                            + timeTaken + " ms"
            );
        }
    }


    // =========================================================
    // Click Send
    // =========================================================

    public void clickSend() {

        long startTime = System.currentTimeMillis();

        try {

            WebElement sendButton = wait.until(
                    ExpectedConditions.elementToBeClickable(SEND_BUTTON)
            );

            sendButton.click();

            ExtentTestListener.logStep(
                    "Send button clicked successfully"
            );

            System.out.println(
                    "Send button clicked successfully"
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Failed to click Send: "
                            + e.getMessage()
            );

            throw e;

        } finally {

            long timeTaken =
                    System.currentTimeMillis() - startTime;

            System.out.println(
                    "Time Taken - Click Send: "
                            + timeTaken + " ms"
            );
        }
    }


    // =========================================================
    // Click OK Popup
    // =========================================================

    public void clickOk() {

        long startTime = System.currentTimeMillis();

        try {

            WebElement okButton = wait.until(
                    ExpectedConditions.elementToBeClickable(OK_BUTTON)
            );

            okButton.click();

            ExtentTestListener.logStep(
                    "OK button clicked successfully"
            );

            System.out.println(
                    "OK button clicked successfully"
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Failed to click OK: "
                            + e.getMessage()
            );

            throw e;

        } finally {

            long timeTaken =
                    System.currentTimeMillis() - startTime;

            System.out.println(
                    "Time Taken - Click OK: "
                            + timeTaken + " ms"
            );
        }
    }


    // =========================================================
    // Complete Contact Us Flow
    // =========================================================

    public void contactUsFlow() {

        long startTime = System.currentTimeMillis();

        try {

            System.out.println(
                    "========== CONTACT US FLOW STARTED =========="
            );

            ExtentTestListener.logStep(
                    "========== CONTACT US FLOW STARTED =========="
            );


            // Step 1 - Click Contact Now
            clickContactNow();


            // Step 2 - Enter Name -> Hide Keyboard
            enterName("Abishek");


            // Step 3 - Enter Email -> Hide Keyboard
            enterEmail("Abishek251295@gmail.com");


            // Step 4 - Enter Mobile Number -> Hide Keyboard
            enterMobileNumber("0526376363");


            // Step 5 - Enter Message -> Hide Keyboard
            enterMessage("Redeem process not working");


            // Step 6 - Click Send
            clickSend();


            // Step 7 - Click OK on popup
            clickOk();


            System.out.println(
                    "========== CONTACT US FLOW COMPLETED =========="
            );

            ExtentTestListener.logStep(
                    "========== CONTACT US FLOW COMPLETED =========="
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "CONTACT US FLOW FAILED: "
                            + e.getMessage()
            );

            System.out.println(
                    "CONTACT US FLOW FAILED: "
                            + e.getMessage()
            );

            throw e;

        }
        finally
        {
            long timeTaken = System.currentTimeMillis() - startTime;

            System.out.println("Total Time Taken - Contact Us Flow: " + timeTaken + " ms");
        }
    }
}