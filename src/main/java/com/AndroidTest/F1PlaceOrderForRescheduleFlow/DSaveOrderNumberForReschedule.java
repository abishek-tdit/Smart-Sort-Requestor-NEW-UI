package com.AndroidTest.F1PlaceOrderForRescheduleFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BOrderDataForReschedule;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DSaveOrderNumberForReschedule extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

    // Orders
    private final By ORDERS =
            AppiumBy.accessibilityId("Orders");

    // Pending
    private final By PENDING =
            AppiumBy.accessibilityId("Pending");

    // First Pending Order
    private final By FIRST_PENDING_ORDER =
            By.xpath(
                    "(//android.widget.ImageView[contains(@content-desc,'AB-RO-')])[1]"
            );


    // =========================================================
    // SAVE RECENTLY PLACED ORDER NUMBER
    // =========================================================

    public void saveRecentlyPlacedOrderNumber() {

        try {

            ExtentTestListener.logStep(
                    "Saving Recently Placed Order Number..."
            );

            // 1. Click Orders
            wait.until(
                    ExpectedConditions.elementToBeClickable(ORDERS)
            ).click();

            ExtentTestListener.logStep(
                    "Orders clicked successfully"
            );


            // 2. Click Pending
            wait.until(
                    ExpectedConditions.elementToBeClickable(PENDING)
            ).click();

            ExtentTestListener.logStep(
                    "Pending clicked successfully"
            );


            // 3. Find first pending order
            WebElement firstOrder = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            FIRST_PENDING_ORDER
                    )
            );

            ExtentTestListener.logStep(
                    "First Pending Order Found"
            );


            // 4. Get content description
            String contentDescription =
                    firstOrder.getAttribute("content-desc");

            if (contentDescription == null ||
                    contentDescription.isBlank()) {

                throw new RuntimeException(
                        "Order content-desc is empty"
                );
            }

            System.out.println(
                    "Order Content Description : "
                            + contentDescription
            );


            // 5. Extract order number
            Matcher matcher =
                    Pattern.compile("AB-RO-\\d+")
                            .matcher(contentDescription);

            if (!matcher.find()) {

                throw new RuntimeException(
                        "Order number AB-RO-xxxxx was not found in content-desc : "
                                + contentDescription
                );
            }


            // 6. Save order number
            String orderNumber = matcher.group();

            BOrderDataForReschedule.orderNumberForReschedule =
                    orderNumber;

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "Saved Order Number : "
                            + BOrderDataForReschedule.orderNumberForReschedule
            );

            System.out.println(
                    "=============================================="
            );

            ExtentTestListener.logStep(
                    "Order Number Saved : "
                            + BOrderDataForReschedule.orderNumberForReschedule
            );


            // 7. Navigate back
            driver.navigate().back();

            System.out.println(
                    "Navigated back successfully"
            );

            ExtentTestListener.logStep(
                    "Order Number Saved Successfully : "
                            + BOrderDataForReschedule.orderNumberForReschedule
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to save recently placed order number"
            );

            ExtentTestListener.logStep(
                    "Failed to Save Order Number : "
                            + e.getMessage()
            );

            throw new RuntimeException(
                    "Failed to save recently placed order number",
                    e
            );
        }
    }
}