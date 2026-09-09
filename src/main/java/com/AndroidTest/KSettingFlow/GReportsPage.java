package com.AndroidTest.KSettingFlow;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class GReportsPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // =========================
    // Locators - Settings Page
    // =========================

    private final By reportMenu =
            AppiumBy.accessibilityId("Report");

    // =========================
    // Locators - Report Page
    // =========================

    private final By reportPageTitle =
            AppiumBy.accessibilityId("Report");

    private final By orderCount =
            AppiumBy.accessibilityId("1");

    private final By totalQuantity =
            AppiumBy.accessibilityId("10.00 kg");

    private final By totalRewardPoints =
            AppiumBy.accessibilityId("60");

    private final By recentOrders =
            AppiumBy.accessibilityId("Recent Orders");

    // Dynamic order card
    private final By recentOrderCard =
            AppiumBy.xpath(
                    "//android.view.View[contains(@content-desc,'AB-RO-') and " +
                            "contains(@content-desc,'kg') and " +
                            "contains(@content-desc,'pts')]"
            );

    // =========================
    // Constructor
    // =========================

    public GReportsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // =========================
    // Click Report
    // =========================

    public void clickReport() {

        System.out.println("STEP : Opening Report");

        WebElement report = wait.until(
                ExpectedConditions.elementToBeClickable(reportMenu)
        );

        report.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(reportPageTitle)
        );

        System.out.println("PASS : Report page displayed");
    }

    // =========================
    // Validate Report Page
    // =========================

    public void validateReportPage() {

        System.out.println("\n========== REPORT VALIDATION ==========");

        // Validate Order Count
        WebElement countElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(orderCount)
        );

        String count = countElement.getAttribute("content-desc");

        if ("1".equals(count)) {
            System.out.println("PASS : Overall Orders = " + count);
        } else {
            throw new RuntimeException(
                    "FAIL : Overall Orders expected 1 but found " + count
            );
        }

        // Validate Total Quantity
        WebElement quantityElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(totalQuantity)
        );

        String quantity = quantityElement.getAttribute("content-desc");

        if ("10.00 kg".equals(quantity)) {
            System.out.println("PASS : Total Quantity = " + quantity);
        } else {
            throw new RuntimeException(
                    "FAIL : Total Quantity expected 10.00 kg but found " + quantity
            );
        }

        // Validate Reward Points
        WebElement pointsElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(totalRewardPoints)
        );

        String points = pointsElement.getAttribute("content-desc");

        if ("60".equals(points)) {
            System.out.println("PASS : Total Reward Points = " + points);
        } else {
            throw new RuntimeException(
                    "FAIL : Total Reward Points expected 60 but found " + points
            );
        }

        // Validate Recent Orders
        WebElement recentOrdersElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(recentOrders)
        );

        String recentOrdersText =
                recentOrdersElement.getAttribute("content-desc");

        if ("Recent Orders".equals(recentOrdersText)) {
            System.out.println("PASS : " + recentOrdersText + " displayed");
        } else {
            throw new RuntimeException(
                    "FAIL : Recent Orders section not displayed"
            );
        }

        // Validate Recent Order
        WebElement orderElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(recentOrderCard)
        );

        String orderDetails =
                orderElement.getAttribute("content-desc");

        System.out.println("PASS : Recent Order found");
        System.out.println("ORDER DETAILS :");
        System.out.println(orderDetails.replace("\n", " | "));

        // Extract order number from the content-desc
        String[] orderData = orderDetails.split("\\n");

        if (orderData.length >= 3) {

            String orderNumber = orderData[0].trim();
            String orderQuantity = orderData[1].trim();
            String orderPoints = orderData[2].trim();

            System.out.println("Order Number  : " + orderNumber);
            System.out.println("Order Quantity: " + orderQuantity);
            System.out.println("Order Points  : " + orderPoints);

            if (orderNumber.startsWith("AB-RO-")) {
                System.out.println(
                        "PASS : Valid Order Number = " + orderNumber
                );
            } else {
                throw new RuntimeException(
                        "FAIL : Invalid Order Number = " + orderNumber
                );
            }

            if ("10.00 kg".equals(orderQuantity)) {
                System.out.println(
                        "PASS : Order Quantity = " + orderQuantity
                );
            } else {
                throw new RuntimeException(
                        "FAIL : Order Quantity expected 10.00 kg but found "
                                + orderQuantity
                );
            }

            if (orderPoints.equals("60 pts")) {
                System.out.println(
                        "PASS : Order Reward Points = " + orderPoints
                );
            } else {
                throw new RuntimeException(
                        "FAIL : Order Reward Points expected 60 pts but found "
                                + orderPoints
                );
            }

        } else {
            throw new RuntimeException(
                    "FAIL : Recent Order details format is incorrect: "
                            + orderDetails
            );
        }

        System.out.println("=======================================");
        System.out.println("PASS : Report page validation completed");
        System.out.println("=======================================\n");
    }

// =========================
// Go Back to Home Screen
// =========================

    public void goBackToHome() {

        System.out.println("STEP : Going back to Home screen");

        driver.navigate().back();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.accessibilityId("Home")
                )
        );

        System.out.println("PASS : Home screen displayed");
    }

// =========================
// Complete Flow
// =========================

    public void openAndValidateReport() {

        clickReport();
        validateReportPage();

        goBackToHome();
    }
}