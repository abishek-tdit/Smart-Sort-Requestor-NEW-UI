package com.AndroidTest.N2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class EGenerateQR extends BaseClassMobile {

    private static final int LONG_WAIT = 120;

    public void clickGenerateQR() {

        try {

            ExtentTestListener.logStep(
                    "Waiting for Generate QR"
            );

            WebElement generateQR =
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver,
                            Duration.ofSeconds(LONG_WAIT)
                    )
                            .pollingEvery(Duration.ofMillis(500))
                            .until(
                                    ExpectedConditions.elementToBeClickable(
                                            AppiumBy.accessibilityId(
                                                    "Generate QR"
                                            )
                                    )
                            );

            generateQR.click();

            ExtentTestListener.logStep(
                    "Generate QR Clicked"
            );

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Generate QR Failed : " +
                            e.getMessage()
            );

            throw new RuntimeException(
                    "Generate QR Flow Failed",
                    e
            );
        }
    }
}