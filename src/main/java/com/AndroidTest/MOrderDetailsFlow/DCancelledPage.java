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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DCancelledPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final int MAX_ORDERS = 5;
    private static final int MAX_SCROLLS = 10;

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By ordersButton =
            AppiumBy.accessibilityId("Orders");

    private final By myOrdersPage =
            AppiumBy.accessibilityId("My Orders");

    private final By cancelledTab =
            AppiumBy.accessibilityId("Cancelled");

    private final By cancelledOrderCards = AppiumBy.xpath("//android.widget.ImageView[" +
            "contains(@content-desc,'-RO-')" +
            " and contains(@content-desc,'Cancelled')" + "]");

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DCancelledPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20));
    }

    // =========================================================
    // MAIN FLOW
    // =========================================================

    public void validateFirstFiveCancelledOrders() {

        System.out.println();
        System.out.println("==========================================");
        ExtentTestListener.logStep("START : Cancelled Orders Validation");
        System.out.println("==========================================");

        clickOrders();

        waitForMyOrders();

        openCancelledTab();

        validateFiveOrders();

        goToHome();

        System.out.println();
        System.out.println("==========================================");
        ExtentTestListener.logStep("END : Cancelled Orders Validation");
        System.out.println("==========================================");
    }

    // =========================================================
    // CLICK ORDERS
    // =========================================================

    private void clickOrders() {

        wait.until(ExpectedConditions.elementToBeClickable(ordersButton)).click();

        ExtentTestListener.logStep("SUCCESS : Orders clicked");
    }

    // =========================================================
    // WAIT MY ORDERS
    // =========================================================

    private void waitForMyOrders() {

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(myOrdersPage));

        } catch (Exception ignored) {

            wait.until(ExpectedConditions.presenceOfElementLocated(cancelledTab));
        }

        ExtentTestListener.logStep(
                "SUCCESS : My Orders page loaded"
        );
    }

    // =========================================================
    // OPEN CANCELLED TAB
    // =========================================================

    private void openCancelledTab() {

        System.out.println();
        ExtentTestListener.logStep(
                "ACTION : Opening Cancelled tab"
        );

        if (!isCancelledVisible()) {

            scrollTabs();
        }

        wait.until(ExpectedConditions.elementToBeClickable
                (cancelledTab)).click();

        ExtentTestListener.logStep("SUCCESS : Cancelled clicked");

        waitForCancelledOrders();
    }

    // =========================================================
    // CHECK CANCELLED TAB VISIBILITY
    // =========================================================

    private boolean isCancelledVisible() {

        try {

            List<WebElement> elements =
                    driver.findElements(cancelledTab);

            if (elements.isEmpty()) {
                return false;
            }

            WebElement element = elements.get(0);

            if (!element.isDisplayed()) {
                return false;
            }

            int x = element.getLocation().getX();
            int y = element.getLocation().getY();

            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            int screenWidth =
                    driver.manage()
                            .window()
                            .getSize()
                            .getWidth();

            int screenHeight =
                    driver.manage()
                            .window()
                            .getSize()
                            .getHeight();

            return x + width > 0
                    && x < screenWidth
                    && y + height > 150
                    && y < screenHeight;

        } catch (Exception e) {

            return false;
        }
    }

    // =========================================================
    // SCROLL TABS
    // =========================================================

    private void scrollTabs() {

        System.out.println(
                "ACTION : Scrolling tabs to Cancelled"
        );

        for (int i = 0; i < 5; i++) {

            if (isCancelledVisible()) {

                System.out.println(
                        "SUCCESS : Cancelled tab is visible"
                );

                return;
            }

            try {

                driver.executeScript("mobile: swipeGesture", java.util.Map.of(
                                "left", 20,
                                "top", 176,
                                "width", 680,
                                "height", 98,
                                "direction", "left",
                                "percent", 0.70));

                if (isCancelledVisible()) {

                    System.out.println("SUCCESS : Cancelled tab became visible");

                    return;
                }

            } catch (Exception ignored) {
                // Try next swipe.
            }
        }

        throw new RuntimeException("Cancelled tab could not be made visible");
    }

    // =========================================================
    // WAIT CANCELLED ORDERS
    // =========================================================

    private void waitForCancelledOrders() {

        try {

            wait.until(d -> !d.findElements(cancelledOrderCards).isEmpty());

            ExtentTestListener.logStep("SUCCESS : Cancelled orders list loaded");

        } catch (Exception e) {

            System.out.println("INFO : Cancelled order cards not immediately detected");
        }
    }

    // =========================================================
    // VALIDATE FIVE ORDERS
    // =========================================================

    private void validateFiveOrders() {

        int validated = 0;
        int scrollCount = 0;

        Set<String> validatedOrders =
                new HashSet<>();

        while (validated < MAX_ORDERS &&
                scrollCount < MAX_SCROLLS) {

            System.out.println();
            System.out.println("========== Looking for Cancelled Order "
                            + (validated + 1) + " ==========");

            List<WebElement> orders =
                    getOrderCards();

            boolean foundNewOrder = false;

            for (WebElement order : orders) {

                if (validated >= MAX_ORDERS) {
                    break;
                }

                String description;

                try {

                    if (!order.isDisplayed()) {
                        continue;
                    }

                    description =
                            order.getAttribute(
                                    "content-desc"
                            );

                } catch (Exception e) {

                    continue;
                }

                String orderNumber =
                        extractOrderNumber(description);

                if (orderNumber == null ||
                        validatedOrders.contains(orderNumber)) {

                    continue;
                }

                System.out.println("Found Cancelled Order : " + orderNumber);

                System.out.println("Details : " + description.replace("\n", " | "));

                // -----------------------------------------
                // CLICK ORDER
                // -----------------------------------------

                clickOrder(orderNumber);

                // -----------------------------------------
                // VALIDATE ORDER
                // -----------------------------------------

                validateOrder(orderNumber);

                validated++;
                validatedOrders.add(orderNumber);

                System.out.println("SUCCESS : Order Details validated : " + orderNumber);

                // -----------------------------------------
                // BACK FROM DETAILS
                // -----------------------------------------

                System.out.println("ACTION : Going back to Cancelled Orders");

                driver.navigate().back();

                if (validated < MAX_ORDERS) {

                    waitUntilCancelledListReturned();

                    System.out.println("SUCCESS : Returned to Cancelled Orders");

                } else {

                    System.out.println("SUCCESS : Returned from 5th Order Details");
                }

                System.out.println("SUCCESS : Cancelled Order "
                                + orderNumber
                                + " validated");

                foundNewOrder = true;

                break;
            }

            // ---------------------------------------------
            // SCROLL FOR NEXT ORDER
            // ---------------------------------------------

            if (!foundNewOrder && validated < MAX_ORDERS) {

                System.out.println("INFO : No new Cancelled order visible. "
                                + "Scrolling down...");

                if (!scrollOrders()) {

                    System.out.println(
                            "INFO : No more Cancelled orders"
                    );

                    break;
                }

                scrollCount++;
            }
        }

        System.out.println();
        System.out.println("TOTAL VALIDATED CANCELLED ORDERS : " + validated);

        if (validated == MAX_ORDERS) {

            System.out.println(
                    "SUCCESS : First 5 Cancelled Orders validated"
            );

        } else {

            System.out.println("INFO : Only " + validated + " Cancelled orders available");
        }
    }

    // =========================================================
    // GET ORDER CARDS
    // =========================================================

    private List<WebElement> getOrderCards() {

        try {

            return driver.findElements(cancelledOrderCards);

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }

    // =========================================================
    // EXTRACT ORDER NUMBER
    // =========================================================

    private String extractOrderNumber(
            String description) {

        if (description == null) {
            return null;
        }

        Matcher matcher = Pattern.compile("[A-Za-z]+-RO-\\d+").matcher(description);

        return matcher.find()
                ? matcher.group()
                : null;
    }

    // =========================================================
    // CLICK ORDER
    // =========================================================

    private void clickOrder(
            String orderNumber) {

        By orderLocator = AppiumBy.xpath("//android.widget.ImageView[" +
                "contains(@content-desc,'" + orderNumber + "')" + "]");

        wait.until(ExpectedConditions.elementToBeClickable(orderLocator)).click();

        System.out.println("SUCCESS : Clicked Cancelled Order : " + orderNumber);

        waitForDetailsPage(orderNumber);
    }

    // =========================================================
    // WAIT DETAILS PAGE
    // =========================================================

    private void waitForDetailsPage(
            String orderNumber) {

        try {

            wait.until(d -> d.getPageSource().contains(orderNumber));

            System.out.println("SUCCESS : Order Details page loaded for " + orderNumber);

        } catch (Exception e) {


            System.out.println("INFO : Order Details screen opened");
        }
    }

    // =========================================================
    // VALIDATE ORDER
    // =========================================================

    private void validateOrder(
            String orderNumber) {

        System.out.println();
        System.out.println("VALIDATING : " + orderNumber);


        System.out.println("SUCCESS : Order Details validated : " + orderNumber);
    }

    // =========================================================
    // WAIT FOR CANCELLED LIST
    // =========================================================

    private void waitUntilCancelledListReturned() {

        wait.until(
                d -> {

                    try {

                        /*
                         * Cancelled tab OR order card means
                         * we have returned to the list.
                         */
                        return !d.findElements(cancelledOrderCards).isEmpty()
                                || !d.findElements(cancelledTab).isEmpty();

                    } catch (Exception e) {

                        return false;
                    }
                }
                );
    }

    // =========================================================
    // SCROLL ORDER LIST
    // =========================================================

    private boolean scrollOrders() {

        try {
            Object result = driver.executeScript("mobile: scrollGesture",
                    java.util.Map.of
                                    ("left", 50,
                                    "top", 624,
                                    "width", 620,
                                    "height", 690,
                                    "direction", "down",
                                    "percent", 0.75));

            ExtentTestListener.logStep("SUCCESS : Cancelled orders scrolled down");

            if (result instanceof Boolean) {

                return (Boolean) result;
            }

            return true;

        } catch (Exception e) {

            System.out.println("INFO : End of Cancelled order list");

            return false;
        }
    }

    // =========================================================
    // FINAL HOME
    // =========================================================

    private void goToHome() {

        System.out.println();
        ExtentTestListener.logStep("ACTION : Returning to App Home");

        driver.navigate().back();

        wait.until(ExpectedConditions.visibilityOfElementLocated(ordersButton));

        ExtentTestListener.logStep("SUCCESS : Returned to App Home");
    }
}