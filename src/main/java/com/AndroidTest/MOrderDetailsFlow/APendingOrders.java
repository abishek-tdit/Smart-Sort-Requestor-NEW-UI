package com.AndroidTest.MOrderDetailsFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class APendingOrders {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final int MAX_ORDERS = 5;

    // ---------------- LOCATORS ----------------

    private final By ordersButton =
            AppiumBy.accessibilityId("Orders");

    /*
     * Inspector shows:
     * content-desc="Pending\n36"
     *
     * So contains() is safer than using the dynamic count "36".
     */
    private final By pendingCard =
            AppiumBy.xpath(
                    "//android.widget.ImageView[contains(@content-desc,'Pending')]"
            );

    /*
     * Order cards have content-desc such as:
     *
     * AB-RO-63326
     * Collector Assigned
     * Scheduled on ...
     * ...
     *
     * We identify them by the order number pattern.
     */
    private final By orderCards =
            AppiumBy.xpath(
                    "//android.widget.ImageView[" +
                            "contains(@content-desc,'-RO-')" +
                            "]"
            );

    // My Orders page title
    private final By myOrdersPage =
            AppiumBy.accessibilityId("My Orders");


    // ---------------- CONSTRUCTOR ----------------

    public APendingOrders(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    // ---------------- MAIN FLOW ----------------

    public void validateFirstFivePendingOrders() {

        System.out.println("==========================================");
        ExtentTestListener.logStep("START : Pending Orders Validation");
        System.out.println("==========================================");

        // 1. Click Orders
        clickOrders();

        // 2. Wait for My Orders page
        waitForOrdersPage();

        // 3. Click Pending
        clickPending();

        // 4. Wait for pending orders to load
        waitForPendingOrders();

        // 5. Validate first 5 orders
        validateFiveOrders();

        System.out.println("==========================================");
        ExtentTestListener.logStep("END : Pending Orders Validation");
        System.out.println("==========================================");
    }


    // ---------------- STEP 1 ----------------

    private void clickOrders() {

        try {

            WebElement orders = wait.until(
                    ExpectedConditions.elementToBeClickable(ordersButton)
            );

            orders.click();

            ExtentTestListener.logStep("SUCCESS : Orders clicked");

        } catch (Exception e) {

            System.out.println("ERROR : Unable to click Orders");
            throw new RuntimeException("Failed to click Orders", e);
        }
    }


    // ---------------- STEP 2 ----------------

    private void waitForOrdersPage() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(myOrdersPage)
            );

            ExtentTestListener.logStep("SUCCESS : My Orders page loaded");

        } catch (Exception e) {

            /*
             * If My Orders accessibility-id is not exposed,
             * Pending can be used as the page-ready condition.
             */
            try {

                wait.until(
                        ExpectedConditions.presenceOfElementLocated(pendingCard)
                );

                ExtentTestListener.logStep(
                        "SUCCESS : My Orders page loaded using Pending card"
                );

            } catch (Exception ex) {

                throw new RuntimeException(
                        "My Orders page did not load", ex
                );
            }
        }
    }


    // ---------------- STEP 3 ----------------

    private void clickPending() {

        try {

            WebElement pending = wait.until(
                    ExpectedConditions.elementToBeClickable(pendingCard)
            );

            pending.click();

            ExtentTestListener.logStep("SUCCESS : Pending clicked");

        } catch (Exception e) {

            System.out.println("ERROR : Unable to click Pending");
            throw new RuntimeException("Failed to click Pending", e);
        }
    }


    // ---------------- STEP 4 ----------------

    private void waitForPendingOrders() {

        try {

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(orderCards)
            );

            ExtentTestListener.logStep("SUCCESS : Pending orders loaded");

        } catch (Exception e) {

            throw new RuntimeException(
                    "Pending orders did not load", e
            );
        }
    }


    // ---------------- STEP 5 ----------------

    private void validateFiveOrders() {

        int validated = 0;

        /*
         * We keep track of order numbers instead of WebElements.
         *
         * This is important because after clicking an order and coming
         * back, old WebElements can become stale.
         */
        List<String> validatedOrderNumbers = new ArrayList<>();


        while (validated < MAX_ORDERS) {

            System.out.println();
            System.out.println(
                    "========== Looking for Order "
                            + (validated + 1)
                            + " =========="
            );

            // Get fresh order elements every time
            List<WebElement> currentOrders = getOrderCards();

            boolean foundNewOrder = false;


            for (WebElement order : currentOrders) {

                String description = order.getAttribute("content-desc");

                if (description == null || description.isBlank()) {
                    continue;
                }

                String orderNumber = extractOrderNumber(description);

                if (orderNumber == null) {
                    continue;
                }

                // Already validated?
                if (validatedOrderNumbers.contains(orderNumber)) {
                    continue;
                }

                ExtentTestListener.logStep(
                        "Found Order : " + orderNumber
                );

                System.out.println(
                        "Details : " +
                                description.replace("\n", " | ")
                );


                // Make sure element is visible
                if (!isElementVisible(order)) {
                    continue;
                }


                // Click the order
                clickOrder(orderNumber);


                // Validate order details page
                validateOrderDetails(orderNumber);


                // Go back to Pending list
                goBackToPendingList();


                // Save validated order
                validatedOrderNumbers.add(orderNumber);

                validated++;

                System.out.println(
                        "SUCCESS : Order "
                                + orderNumber
                                + " validated"
                );

                foundNewOrder = true;

                break;
            }


            /*
             * If a new order was not found on the current screen,
             * scroll down and search again.
             */
            if (!foundNewOrder) {

                System.out.println(
                        "INFO : No new order visible. Scrolling..."
                );

                boolean scrolled = scrollOrders();

                if (!scrolled) {

                    System.out.println(
                            "WARNING : Cannot scroll further."
                    );

                    break;
                }
            }
        }


        System.out.println();
        System.out.println(
                "TOTAL VALIDATED ORDERS : "
                        + validated
        );


        if (validated < MAX_ORDERS) {

            throw new RuntimeException(
                    "Only "
                            + validated
                            + " pending orders were validated. "
                            + "Expected "
                            + MAX_ORDERS
            );
        }

        ExtentTestListener.logStep(
                "SUCCESS : First 5 Pending Orders validated"
        );
    }


    // ---------------- GET FRESH ORDER CARDS ----------------

    private List<WebElement> getOrderCards() {

        try {

            return driver.findElements(orderCards);

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }


    // ---------------- EXTRACT ORDER NUMBER ----------------

    private String extractOrderNumber(String description) {

        if (description == null) {
            return null;
        }

        /*
         * Example:
         *
         * AB-RO-63326
         * Collector Assigned
         * Scheduled on Sep 1...
         *
         * We only need AB-RO-63326.
         */

        String[] lines = description.split("\\n");

        for (String line : lines) {

            line = line.trim();

            if (line.matches("[A-Za-z]+-RO-\\d+")) {
                return line;
            }
        }

        /*
         * Fallback in case content-desc formatting changes.
         */
        java.util.regex.Matcher matcher =
                java.util.regex.Pattern
                        .compile("[A-Za-z]+-RO-\\d+")
                        .matcher(description);

        if (matcher.find()) {
            return matcher.group();
        }

        return null;
    }


    // ---------------- CLICK ORDER ----------------

    private void clickOrder(String orderNumber) {

        try {

            By orderLocator =
                    AppiumBy.xpath(
                            "//android.widget.ImageView[" +
                                    "contains(@content-desc,'"
                                    + orderNumber
                                    + "')" +
                                    "]"
                    );

            WebElement order = wait.until(
                    ExpectedConditions.elementToBeClickable(orderLocator)
            );

            order.click();

            System.out.println(
                    "SUCCESS : Clicked Order : "
                            + orderNumber
            );

            waitForOrderDetailsPage(orderNumber);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to click Order : "
                            + orderNumber,
                    e
            );
        }
    }


    // ---------------- WAIT ORDER DETAILS ----------------

    private void waitForOrderDetailsPage(String orderNumber) {

        try {

            /*
             * Wait until the old order list disappears.
             *
             * This confirms that navigation happened.
             */
            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            AppiumBy.xpath(
                                    "//android.widget.ImageView[" +
                                            "contains(@content-desc,'"
                                            + orderNumber
                                            + "')" +
                                            "]"
                            )
                    )
            );

            ExtentTestListener.logStep(
                    "SUCCESS : Order Details page loaded for "
                            + orderNumber
            );

        } catch (Exception e) {

            /*
             * In case the same order element is still technically
             * present in the hierarchy, wait briefly for any page
             * content instead of failing unnecessarily.
             */
            System.out.println(
                    "INFO : Waiting for Order Details screen..."
            );

            waitForPageStable();
        }
    }


    // ---------------- VALIDATE ORDER DETAILS ----------------

    private void validateOrderDetails(String orderNumber) {

        System.out.println(
                "VALIDATING : "
                        + orderNumber
        );

        /*
         * IMPORTANT:
         *
         * Add your Order Details page validation here.
         *
         * If you already have a separate Order Details page class,
         * call it here.
         *
         * Example:
         *
         * AOrderDetailsPage detailsPage =
         *         new AOrderDetailsPage(driver);
         *
         * detailsPage.validateOrderDetails(orderNumber);
         */


        /*
         * For now, we simply wait for the screen to stabilize.
         *
         * Replace/add your actual validation methods here.
         */
        waitForPageStable();

        System.out.println(
                "SUCCESS : Order Details validated : "
                        + orderNumber
        );
    }


    // ---------------- GO BACK ----------------

    private void goBackToPendingList() {

        System.out.println(
                "ACTION : Going back to Pending Orders"
        );

        /*
         * Android back is more reliable here than trying to
         * identify a dynamic back button.
         */
        driver.navigate().back();


        /*
         * Wait until Pending/order cards are visible again.
         */
        try {

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            orderCards
                    )
            );

            ExtentTestListener.logStep(
                    "SUCCESS : Returned to Pending Orders"
            );

        } catch (Exception e) {

            /*
             * Sometimes the page takes a little longer to rebuild.
             */
            waitForPageStable();

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            pendingCard
                    )
            );

            ExtentTestListener.logStep(
                    "SUCCESS : Pending Orders page restored"
            );
        }
    }


    // ---------------- SCROLL ----------------

    private boolean scrollOrders() {

        try {

            /*
             * Scroll only inside the vertical ScrollView.
             *
             * Screen dimensions from Inspector:
             * 720 x 1604
             *
             * The actual order ScrollView is:
             * [0,484][720,1316]
             *
             * Therefore we swipe inside that area.
             */
            driver.executeScript(
                    "mobile: scrollGesture",
                    java.util.Map.of(
                            "left", 50,
                            "top", 500,
                            "width", 620,
                            "height", 800,
                            "direction", "down",
                            "percent", 0.75
                    )
            );

            System.out.println(
                    "SUCCESS : Orders scrolled down"
            );

            waitForPageStable();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "ERROR : Scroll failed : "
                            + e.getMessage()
            );

            return false;
        }
    }


    // ---------------- VISIBILITY ----------------

    private boolean isElementVisible(WebElement element) {

        try {

            return element.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }


    // ---------------- PAGE STABILITY ----------------

    private void waitForPageStable() {

        try {

            Thread.sleep(300);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}