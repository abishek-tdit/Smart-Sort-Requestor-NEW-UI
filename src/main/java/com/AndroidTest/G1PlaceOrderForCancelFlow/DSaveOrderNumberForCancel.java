package com.AndroidTest.G1PlaceOrderForCancelFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.COrderDataCancel;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DSaveOrderNumberForCancel extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

    private final By ORDERS =
            AppiumBy.accessibilityId("Orders");

    private final By PENDING =
            AppiumBy.accessibilityId("Pending");

    private final By FIRST_PENDING_ORDER =
            By.xpath("(//android.widget.ImageView[contains(@content-desc,'AB-RO-')])[1]");


    public void saveRecentlyPlacedOrderNumber() {

        try {

            System.out.println("Saving Recently Placed Order Number...");

            // 1. Orders
            wait.until(ExpectedConditions.elementToBeClickable(ORDERS))
                    .click();

            System.out.println("Orders clicked");

            // 2. Pending
            wait.until(ExpectedConditions.elementToBeClickable(PENDING))
                    .click();

            System.out.println("Pending clicked");

            // 3. First pending order
            WebElement order = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            FIRST_PENDING_ORDER));

            // 4. Get order number
            String contentDesc = order.getAttribute("content-desc");

            if (contentDesc == null || contentDesc.isBlank()) {
                throw new RuntimeException(
                        "Order content-desc is empty");
            }

            Matcher matcher =
                    Pattern.compile("AB-RO-\\d+")
                            .matcher(contentDesc);

            if (!matcher.find()) {
                throw new RuntimeException(
                        "Order number not found in : " + contentDesc);
            }

            // 5. Save order number
            COrderDataCancel.orderNumberCancel = matcher.group();

            System.out.println(
                    "Saved Order Number : "
                            + COrderDataCancel.orderNumberCancel);

            ExtentTestListener.logStep(
                    "Order Number Saved : "
                            + COrderDataCancel.orderNumberCancel);

            // 6. Go back
            waitForBackToComplete();

            System.out.println("Navigated back successfully");

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Failed to Save Order Number : " + e.getMessage());

            throw new RuntimeException(
                    "Failed to save recently placed order number", e);
        }
    }


    private void waitForBackToComplete() {

        driver.navigate().back();

        // Wait only until Orders button is available again
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(ORDERS));
        } catch (Exception ignored) {
            // Back navigation itself is already completed
        }
    }
}