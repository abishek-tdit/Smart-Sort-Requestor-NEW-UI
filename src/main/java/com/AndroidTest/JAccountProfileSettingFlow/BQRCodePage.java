package com.AndroidTest.JAccountProfileSettingFlow;

import Base.BaseClassMobile;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class BQRCodePage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public BQRCodePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By account =
            AppiumBy.accessibilityId("Account");

    private final By userName =
            AppiumBy.xpath(
                    "//*[contains(@text,'abishek251295@gmail.com') " +
                            "or contains(@content-desc,'abishek251295@gmail.com')]"
            );

    private final By qrCode =
            AppiumBy.xpath("//android.widget.ScrollView/android.view.View[2]");


    // =========================================================
    // COMPLETE FLOW
    // =========================================================

    public void openQRCodeAndGoBack() throws InterruptedException {

        // STEP 1 : Account
        Thread.sleep(4000);

        wait.until(
                ExpectedConditions.elementToBeClickable(account)
        ).click();

        System.out.println("Account clicked successfully");


        // STEP 2 : User Name
        clickUserName();


        // STEP 3 : QR Code
        wait.until(ExpectedConditions.elementToBeClickable(qrCode)).click();

        System.out.println("QR Code clicked successfully");


        // STEP 4 : Wait 1 second and go back
        Thread.sleep(1000);

        driver.navigate().back();

        System.out.println("QR Code closed and returned successfully");
    }


    // =========================================================
    // USER NAME
    // =========================================================

    private void clickUserName() {

        for (int i = 0; i < 5; i++) {

            if (!driver.findElements(userName).isEmpty()) {

                wait.until(
                        ExpectedConditions.elementToBeClickable(userName)
                ).click();

                System.out.println("User name clicked successfully");
                return;
            }

            scrollDown();
        }

        throw new RuntimeException(
                "User name/email was not found on Account page"
        );
    }
}