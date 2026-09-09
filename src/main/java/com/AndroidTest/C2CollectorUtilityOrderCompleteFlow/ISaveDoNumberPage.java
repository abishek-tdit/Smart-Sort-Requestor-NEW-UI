package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import utilities.FSaveDONumberPage;
import utilities.StepTimer;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;
import java.util.List;

public class ISaveDoNumberPage extends BaseClassMobile {

    // =========================================================
    // Delivery Orders (DO) Locators
    // =========================================================

    private final By deliveryOrdersAccessibility =
            AppiumBy.accessibilityId("Delivery Orders (DO)");

    private final By deliveryOrdersText =
            By.xpath("//*[@text='Delivery Orders (DO)']");

    private final By deliveryOrdersContentDesc =
            By.xpath("//*[@content-desc='Delivery Orders (DO)']");

    private final By deliveryOrdersContains =
            By.xpath(
                    "//*[contains(@content-desc,'Delivery Orders') " +
                            "or contains(@text,'Delivery Orders')]"
            );

    // Extra Android UIAutomator fallback
    private final By deliveryOrdersUiAutomator =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"Delivery Orders\")"
            );

    // =========================================================
    // Delivery Order Card
    // =========================================================

    private final By doCards =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'AB-DO-') " +
                            "or contains(@text,'AB-DO-')]"
            );

    // =========================================================
    // Constructor
    // =========================================================

    public ISaveDoNumberPage() {
        this.driver = driver;
    }

    // =========================================================
    // Main Method
    // =========================================================

    public void clickDeliveryOrderAndSaveDONumber() {

        // =====================================================
        // Step 1 : Click Delivery Orders (DO)
        // =====================================================

        StepTimer.startStep();

        try {

            System.out.println(
                    "Waiting for Delivery Orders (DO)..."
            );

            WebElement deliveryOrders =
                    waitForDeliveryOrdersButton();

            if (deliveryOrders == null) {
                throw new RuntimeException(
                        "Delivery Orders (DO) button not found"
                );
            }

            System.out.println(
                    "Delivery Orders (DO) found successfully"
            );

            // Re-check element before clicking
            try {
                if (!deliveryOrders.isDisplayed()) {
                    throw new RuntimeException(
                            "Delivery Orders (DO) is not displayed"
                    );
                }

                if (!deliveryOrders.isEnabled()) {
                    throw new RuntimeException(
                            "Delivery Orders (DO) is not enabled"
                    );
                }

                deliveryOrders.click();

            } catch (StaleElementReferenceException e) {

                System.out.println(
                        "Delivery Orders element became stale. Re-finding..."
                );

                WebElement retryElement =
                        waitForDeliveryOrdersButton();

                if (retryElement == null) {
                    throw new RuntimeException(
                            "Delivery Orders (DO) disappeared before click"
                    );
                }

                retryElement.click();
            }

            // Wait until DO page/cards start loading
            waitForDOPage();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Delivery Orders (DO) Clicked Successfully | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );

            System.out.println(
                    "Delivery Orders (DO) clicked successfully"
            );

        } catch (Exception e) {

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Failed to click Delivery Orders (DO) | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );

            throw new RuntimeException(
                    "Delivery Orders (DO) button not found",
                    e
            );
        }


        // =====================================================
        // Step 2 : Find First DO Card
        // =====================================================

        StepTimer.startStep();

        try {

            System.out.println(
                    "Waiting for Delivery Order cards..."
            );

            WebElement firstDO =
                    waitForFirstDOCard();

            if (firstDO == null) {

                throw new RuntimeException(
                        "No Delivery Order card found"
                );
            }

            // =================================================
            // Extract Content Description / Text
            // =================================================

            String contentDescription = null;

            try {
                contentDescription =
                        firstDO.getAttribute("content-desc");
            } catch (Exception ignored) {
            }

            String text = null;

            try {
                text = firstDO.getAttribute("text");
            } catch (Exception ignored) {
            }

            System.out.println(
                    "First DO Content Description : "
                            + contentDescription
            );

            System.out.println(
                    "First DO Text : "
                            + text
            );

            ExtentTestListener.logStep(
                    "First Delivery Order Card Found"
            );

            // =================================================
            // Extract DO Number
            // =================================================

            String doNumber =
                    extractDONumber(contentDescription);

            // Fallback to text
            if (doNumber == null || doNumber.isEmpty()) {
                doNumber = extractDONumber(text);
            }

            // Final fallback: inspect all visible DO elements
            if (doNumber == null || doNumber.isEmpty()) {

                doNumber = findDONumberFromScreen();
            }

            if (doNumber == null || doNumber.isEmpty()) {

                double duration = StepTimer.endStep();

                ExtentTestListener.logStep(
                        "DO Number could not be extracted | Time Taken : "
                                + String.format("%.2f", duration)
                                + " sec"
                );

                throw new RuntimeException(
                        "DO Number not found in DO card"
                );
            }

            System.out.println(
                    "First DO Number : " + doNumber
            );

            ExtentTestListener.logStep(
                    "DO Number Found : " + doNumber
            );

            // =================================================
            // Save DO Number
            // =================================================

            FSaveDONumberPage.saveDONumber(doNumber);

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "DO Number Saved : "
                            + doNumber
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );

            System.out.println(
                    "DO Number Saved Successfully : "
                            + doNumber
            );

        } catch (Exception e) {

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep(
                    "Failed to find/save Delivery Order Number | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec"
            );

            throw new RuntimeException(
                    "Unable to save Delivery Order Number",
                    e
            );
        }
    }


    // =========================================================
    // Wait For Delivery Orders Button
    // =========================================================

    private WebElement waitForDeliveryOrdersButton() {

        System.out.println(
                "Searching for Delivery Orders (DO)..."
        );

        final long timeoutSeconds = 15;

        long endTime =
                System.currentTimeMillis()
                        + Duration.ofSeconds(timeoutSeconds).toMillis();

        boolean scrolled = false;

        while (System.currentTimeMillis() < endTime) {

            // -------------------------------------------------
            // 1. Accessibility ID
            // -------------------------------------------------

            WebElement element =
                    findVisibleEnabled(deliveryOrdersAccessibility);

            if (element != null) {

                System.out.println(
                        "Delivery Orders found using Accessibility ID"
                );

                return element;
            }

            // -------------------------------------------------
            // 2. Exact Text
            // -------------------------------------------------

            element =
                    findVisibleEnabled(deliveryOrdersText);

            if (element != null) {

                System.out.println(
                        "Delivery Orders found using Text"
                );

                return element;
            }

            // -------------------------------------------------
            // 3. Content Description
            // -------------------------------------------------

            element =
                    findVisibleEnabled(deliveryOrdersContentDesc);

            if (element != null) {

                System.out.println(
                        "Delivery Orders found using Content Description"
                );

                return element;
            }

            // -------------------------------------------------
            // 4. Partial Text / Content Description
            // -------------------------------------------------

            element =
                    findVisibleEnabled(deliveryOrdersContains);

            if (element != null) {

                System.out.println(
                        "Delivery Orders found using Partial Locator"
                );

                return element;
            }

            // -------------------------------------------------
            // 5. Android UIAutomator
            // -------------------------------------------------

            element =
                    findVisibleEnabled(deliveryOrdersUiAutomator);

            if (element != null) {

                System.out.println(
                        "Delivery Orders found using UiAutomator"
                );

                return element;
            }

            // -------------------------------------------------
            // Scroll only once if not visible
            // -------------------------------------------------

            if (!scrolled) {

                System.out.println(
                        "Delivery Orders not visible. Scrolling..."
                );

                scrollDown1();

                scrolled = true;
            }

            // Small polling interval without Thread.sleep
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println(
                "Delivery Orders (DO) could not be found"
        );

        return null;
    }


    // =========================================================
    // Find Visible + Enabled Element
    // =========================================================

    private WebElement findVisibleEnabled(By locator) {

        try {

            List<WebElement> elements =
                    driver.findElements(locator);

            for (WebElement element : elements) {

                try {

                    if (element.isDisplayed()
                            && element.isEnabled()) {

                        return element;
                    }

                } catch (StaleElementReferenceException ignored) {
                } catch (Exception ignored) {
                }
            }

        } catch (Exception ignored) {
        }

        return null;
    }


    // =========================================================
    // Scroll Down
    // =========================================================

    private void scrollDown1() {

        try {

            driver.executeScript(
                    "mobile: scrollGesture",
                    java.util.Map.of(
                            "left", 50,
                            "top", 300,
                            "width", 620,
                            "height", 1100,
                            "direction", "down",
                            "percent", 0.65
                    )
            );

            System.out.println(
                    "Dashboard scroll completed"
            );

        } catch (Exception e) {

            System.out.println(
                    "Scroll failed : "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // Wait For DO Page
    // =========================================================

    private void waitForDOPage() {

        System.out.println(
                "Waiting for Delivery Orders page..."
        );

        try {

            wait.until(driver -> {

                try {

                    List<WebElement> cards =
                            driver.findElements(doCards);

                    return !cards.isEmpty();

                } catch (Exception e) {

                    return false;
                }
            });

            System.out.println(
                    "Delivery Orders page loaded successfully"
            );

        } catch (TimeoutException e) {

            System.out.println(
                    "DO cards not immediately visible. Continuing..."
            );
        }
    }


    // =========================================================
    // Wait For First DO Card
    // =========================================================

    private WebElement waitForFirstDOCard() {

        long endTime =
                System.currentTimeMillis()
                        + Duration.ofSeconds(15).toMillis();

        while (System.currentTimeMillis() < endTime) {

            try {

                List<WebElement> cards =
                        driver.findElements(doCards);

                for (WebElement card : cards) {

                    try {

                        if (card.isDisplayed()) {

                            System.out.println(
                                    "First Delivery Order found"
                            );

                            return card;
                        }

                    } catch (StaleElementReferenceException ignored) {
                    }
                }

            } catch (Exception ignored) {
            }

            // Scroll if cards are not visible
            try {

                driver.executeScript(
                        "mobile: scrollGesture",
                        java.util.Map.of(
                                "left", 50,
                                "top", 300,
                                "width", 620,
                                "height", 1100,
                                "direction", "down",
                                "percent", 0.50
                        )
                );

            } catch (Exception ignored) {
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        return null;
    }


    // =========================================================
    // Find DO Number Anywhere On Current Screen
    // =========================================================

    private String findDONumberFromScreen() {

        try {

            List<WebElement> elements =
                    driver.findElements(
                            By.xpath(
                                    "//*[contains(@content-desc,'AB-DO-') " +
                                            "or contains(@text,'AB-DO-')]"
                            )
                    );

            for (WebElement element : elements) {

                try {

                    String contentDesc =
                            element.getAttribute("content-desc");

                    String doNumber =
                            extractDONumber(contentDesc);

                    if (doNumber != null
                            && !doNumber.isEmpty()) {

                        return doNumber;
                    }

                    String text =
                            element.getAttribute("text");

                    doNumber =
                            extractDONumber(text);

                    if (doNumber != null
                            && !doNumber.isEmpty()) {

                        return doNumber;
                    }

                } catch (StaleElementReferenceException ignored) {
                }
            }

        } catch (Exception ignored) {
        }

        return null;
    }


    // =========================================================
    // Extract DO Number
    // =========================================================

    private String extractDONumber(String value) {

        if (value == null
                || value.trim().isEmpty()) {

            return null;
        }

        // -----------------------------------------------------
        // Split lines
        // -----------------------------------------------------

        String[] lines =
                value.split("\\r?\\n");

        for (String line : lines) {

            String text =
                    line.trim();

            if (text.startsWith("AB-DO-")) {

                // Remove extra text after whitespace
                String[] parts =
                        text.split("\\s+");

                return parts[0].trim();
            }
        }

        // -----------------------------------------------------
        // Fallback: Find AB-DO- anywhere
        // -----------------------------------------------------

        int start =
                value.indexOf("AB-DO-");

        if (start >= 0) {

            String result =
                    value.substring(start);

            // Stop at newline
            int newline =
                    result.indexOf("\n");

            if (newline > 0) {
                result =
                        result.substring(0, newline);
            }

            // Stop at whitespace
            String[] parts =
                    result.trim().split("\\s+");

            if (parts.length > 0) {

                return parts[0].trim();
            }
        }

        return null;
    }
}
