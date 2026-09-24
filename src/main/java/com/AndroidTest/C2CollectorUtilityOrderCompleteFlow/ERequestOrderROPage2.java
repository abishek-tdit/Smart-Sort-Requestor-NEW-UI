package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.AOrderDataDoorPickup;

import java.time.Duration;

public class ERequestOrderROPage2 extends BaseClassMobile {

    private final WebDriverWait wait;

    public ERequestOrderROPage2(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Request Orders (RO):
    private final By requestOrders = AppiumBy.accessibilityId("Request Orders (RO)");

    // Click Request Orders (RO):
    public void clickRequestOrders() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(requestOrders));
        wait.until(ExpectedConditions.elementToBeClickable(requestOrders)).click();

        ExtentTestListener.logStep("Request Orders (RO) Clicked Successfully");
    }

    // Click saved Request Order:
    public void clickSavedOrder() {

        String orderNumber = AOrderDataDoorPickup.orderNumberDoorPickup;

        if (orderNumber == null || orderNumber.isBlank()) {
            throw new RuntimeException("Saved Order Number is NULL or EMPTY");
        }

        System.out.println("Searching Saved Order : " + orderNumber);

        By savedOrder = AppiumBy.xpath
                ("//android.view.View[contains(@content-desc,'" + orderNumber + "')]");

        // Wait until saved order is displayed:
        wait.until(ExpectedConditions.visibilityOfElementLocated(savedOrder));

        System.out.println("Saved Order Found : " + orderNumber);

        // Wait until clickable and click:
        wait.until(ExpectedConditions.elementToBeClickable(savedOrder)).click();

        ExtentTestListener.logStep("Saved Request Order Clicked Successfully : " + orderNumber);
    }
}