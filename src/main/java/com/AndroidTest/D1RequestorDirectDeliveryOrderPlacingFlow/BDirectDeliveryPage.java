package com.AndroidTest.D1RequestorDirectDeliveryOrderPlacingFlow;

import Base.BaseClassMobile;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BDirectDeliveryPage extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

    // Direct Delivery card on Home screen
    private final By directDelivery = AppiumBy.accessibilityId(
            "Direct Delivery\nDeliver your items directly\nLearn More");

    // Direct Delivery screen
    private final By startDirectDelivery =
            AppiumBy.accessibilityId("Start Direct Delivery");

    // Collection center
    private final By ttfVaasan =
            AppiumBy.accessibilityId("TTFVaasan 0.02 km away");

    // Checkbox
    private final By checkbox =
            AppiumBy.className("android.widget.CheckBox");


    public void selectDirectDelivery() {

        // STEP 1 - Click Direct Delivery
        scrollDown();

        WebElement directDeliveryElement =
                wait.until(ExpectedConditions.elementToBeClickable(directDelivery));

        directDeliveryElement.click();

        System.out.println("Direct Delivery Clicked Successfully");


        // STEP 2 - Click Start Direct Delivery
        WebElement startDeliveryElement =
                wait.until(ExpectedConditions.elementToBeClickable(startDirectDelivery));

        startDeliveryElement.click();

        System.out.println("Start Direct Delivery Clicked Successfully");


        // STEP 3 - Select TTFVaasan
        WebElement ttfVaasanElement =
                wait.until(ExpectedConditions.elementToBeClickable(ttfVaasan));

        ttfVaasanElement.click();

        System.out.println("TTFVaasan Selected Successfully");


        // STEP 4 - Click Checkbox
        WebElement checkboxElement =
                wait.until(ExpectedConditions.elementToBeClickable(checkbox));

        checkboxElement.click();

        System.out.println("Checkbox Clicked Successfully");
    }
}
