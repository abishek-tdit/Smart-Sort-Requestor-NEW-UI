package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FGenerateQR extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(10));

    private final By generateQR =
            AppiumBy.accessibilityId("Generate QR");

    private final By proceedToOrder =
            AppiumBy.accessibilityId("Proceed to Order");

    public void clickGenerateQR() {

        scrollDown();

        wait.until(
                ExpectedConditions.elementToBeClickable(generateQR)
        ).click();

        System.out.println("Generate QR clicked successfully");

        wait.until(
                ExpectedConditions.elementToBeClickable(proceedToOrder)
        ).click();

        System.out.println("Proceed to Order clicked successfully");
    }
}