package com.AndroidTest.LOrderDetailsFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BCompletedPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final int MAX_ORDERS = 5;
    private static final int MAX_SCROLL_ATTEMPTS = 10;

    // ============================================================
    // LOCATORS
    // ============================================================

    private final By completedTab =
            AppiumBy.accessibilityId("Completed");

    private final By completedOrders =
            AppiumBy.xpath(
                    "//android.view.View[" +
                            "contains(@content-desc,'AB-RO-')" +
                            " and contains(@content-desc,'Completed')" +
                            " and contains(@content-desc,'Received Points')" +
                            "]"
            );

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public BCompletedPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    // ============================================================
    // MAIN FLOW
    // ============================================================

    public void validateFirstFiveCompletedOrders() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("START : Completed Orders Validation");
        System.out.println("==========================================");

        clickCompleted();

        waitForCompletedOrders();

        validateFiveOrders();

        System.out.println();
        System.out.println("==========================================");
        System.out.println("END : Completed Orders Validation");
        System.out.println("==========================================");
    }

    // ============================================================
    // CLICK COMPLETED TAB
    // ============================================================

    private void clickCompleted() {

        try {

            WebElement completed =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    completedTab
                            )
                    );

            completed.click();

            System.out.println(
                    "SUCCESS : Completed clicked"
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to click Completed",
                    e
            );
        }
    }

    // ============================================================
    // WAIT FOR COMPLETED ORDERS
    // ============================================================

    private void waitForCompletedOrders() {

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        completedOrders
                )
        );

        System.out.println(
                "SUCCESS : Completed orders loaded"
        );
    }

    // ============================================================
    // VALIDATE FIRST 5 COMPLETED ORDERS
    // ============================================================

    private void validateFiveOrders() {

        Set<String> processedOrders =
                new HashSet<>();

        int validated = 0;
        int scrollAttempts = 0;

        while (validated < MAX_ORDERS &&
                scrollAttempts < MAX_SCROLL_ATTEMPTS) {

            System.out.println();
            System.out.println(
                    "========== Looking for Completed Order "
                            + (validated + 1)
                            + " =========="
            );

            List<WebElement> orders =
                    getCompletedOrders();

            boolean newOrderFound = false;

            // ====================================================
            // SEARCH CURRENT VISIBLE ORDERS
            // ====================================================

            for (WebElement order : orders) {

                try {

                    if (!order.isDisplayed()) {
                        continue;
                    }

                    String description =
                            order.getAttribute(
                                    "content-desc"
                            );

                    if (description == null ||
                            description.isBlank()) {
                        continue;
                    }

                    String orderNumber =
                            extractOrderNumber(
                                    description
                            );

                    if (orderNumber == null) {
                        continue;
                    }

                    // Skip already validated orders
                    if (processedOrders.contains(
                            orderNumber
                    )) {
                        continue;
                    }

                    System.out.println(
                            "Completed Order "
                                    + (validated + 1)
                                    + " : "
                                    + orderNumber
                    );

                    System.out.println(
                            "Details : "
                                    + description.replace(
                                    "\n",
                                    " | "
                            )
                    );

                    // =================================================
                    // CLICK ORDER
                    // =================================================

                    clickOrder(orderNumber);

                    System.out.println(
                            "SUCCESS : Clicked Completed Order : "
                                    + orderNumber
                    );

                    // =================================================
                    // VALIDATE ORDER DETAILS
                    // =================================================

                    validateOrderDetails(
                            orderNumber
                    );

                    // =================================================
                    // GO BACK
                    // =================================================

                    goBackToCompletedList();

                    // =================================================
                    // MARK ORDER AS PROCESSED
                    // =================================================

                    processedOrders.add(
                            orderNumber
                    );

                    validated++;

                    System.out.println(
                            "SUCCESS : Completed Order "
                                    + orderNumber
                                    + " validated"
                    );

                    newOrderFound = true;

                    // Reset scrolling after successful validation
                    scrollAttempts = 0;

                    break;

                } catch (Exception e) {

                    throw new RuntimeException(
                            "Failed while validating Completed Order",
                            e
                    );
                }
            }

            // ====================================================
            // 5 ORDERS COMPLETED
            // ====================================================

            if (validated >= MAX_ORDERS) {
                break;
            }

            // ====================================================
            // NO NEW ORDER FOUND -> SCROLL DOWN
            // ====================================================

            if (!newOrderFound) {

                System.out.println(
                        "INFO : No new Completed order visible."
                );

                scrollCompletedOrders();

                scrollAttempts++;

                System.out.println(
                        "Scroll attempt : "
                                + scrollAttempts
                );
            }
        }

        // ============================================================
        // FINAL RESULT
        // ============================================================

        System.out.println();
        System.out.println(
                "TOTAL VALIDATED COMPLETED ORDERS : "
                        + validated
        );

        if (validated < MAX_ORDERS) {

            throw new RuntimeException(
                    "Only "
                            + validated
                            + " Completed orders were validated. "
                            + "Expected "
                            + MAX_ORDERS
            );
        }

        System.out.println();
        System.out.println(
                "SUCCESS : First 5 Completed Orders validated"
        );
    }

    // ============================================================
    // GET COMPLETED ORDERS
    // ============================================================

    private List<WebElement> getCompletedOrders() {

        try {

            return driver.findElements(
                    completedOrders
            );

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }

    // ============================================================
    // EXTRACT ORDER NUMBER
    // ============================================================

    private String extractOrderNumber(
            String description) {

        if (description == null) {
            return null;
        }

        Matcher matcher =
                Pattern.compile(
                        "[A-Za-z]+-RO-\\d+"
                ).matcher(description);

        if (matcher.find()) {
            return matcher.group();
        }

        return null;
    }

    // ============================================================
    // CLICK ORDER
    // ============================================================

    private void clickOrder(
            String orderNumber) {

        By orderLocator =
                AppiumBy.xpath(
                        "//android.view.View[" +
                                "contains(@content-desc,'"
                                + orderNumber
                                + "')" +
                                "]"
                );

        WebElement order =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                orderLocator
                        )
                );

        order.click();
    }

    // ============================================================
    // VALIDATE ORDER DETAILS
    // ============================================================

    private void validateOrderDetails(
            String orderNumber) {

        System.out.println(
                "VALIDATING ORDER DETAILS : "
                        + orderNumber
        );

        try {

            wait.until(driver ->
                    driver.getPageSource()
                            .contains(orderNumber)
            );

            System.out.println(
                    "SUCCESS : Order Details validated : "
                            + orderNumber
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Order Details validation failed : "
                            + orderNumber,
                    e
            );
        }
    }

    // ============================================================
    // GO BACK TO COMPLETED LIST
    // ============================================================

    private void goBackToCompletedList() {

        System.out.println(
                "ACTION : Going back to Completed Orders"
        );

        driver.navigate().back();

        waitForCompletedOrders();

        System.out.println(
                "SUCCESS : Returned to Completed Orders"
        );
    }

    // ============================================================
    // SCROLL COMPLETED ORDER LIST
    // ============================================================

    private void scrollCompletedOrders() {

        try {

            System.out.println(
                    "ACTION : Scrolling Completed orders..."
            );

            /*
             * The orders are inside the vertical Android
             * ScrollView. Scroll that container directly.
             */
            driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(" +
                                    "new UiSelector()" +
                                    ".className(\"android.widget.ScrollView\")" +
                                    ")" +
                                    ".setAsVerticalList()" +
                                    ".scrollForward()"
                    )
            );

            waitForPageStable();

            System.out.println(
                    "SUCCESS : Completed orders scrolled down"
            );

        } catch (Exception e) {

            System.out.println(
                    "WARNING : Scroll failed"
            );

            System.out.println(
                    "Reason : "
                            + e.getMessage()
            );
        }
    }

    // ============================================================
    // PAGE STABILITY
    // ============================================================

    private void waitForPageStable() {

        try {

            Thread.sleep(300);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}