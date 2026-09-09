package com.AndroidTest.LOrderDetailsFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CRescheduledPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final int MAX_SWIPES = 5;

    // ============================================================
    // LOCATORS
    // ============================================================

    private final By rescheduledTab =
            AppiumBy.accessibilityId("Rescheduled");

    private final By rescheduledOrders =
            AppiumBy.xpath(
                    "//android.view.View[" +
                            "contains(@content-desc,'AB-RO-')" +
                            " and contains(@content-desc,'Rescheduled')" +
                            "]"
            );

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public CRescheduledPage(AndroidDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    // ============================================================
    // MAIN FLOW
    // ============================================================

    public void checkRescheduledOrders() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("START : Rescheduled Orders Validation");
        System.out.println("==========================================");

        // Swipe horizontally until Rescheduled is actually visible
        swipeUntilRescheduledVisible();

        // Click Rescheduled
        clickRescheduled();

        // Check Rescheduled orders
        checkOrders();

        System.out.println();
        System.out.println("==========================================");
        System.out.println("END : Rescheduled Orders Validation");
        System.out.println("==========================================");
    }

    // ============================================================
    // SWIPE UNTIL RESCHEDULED IS ACTUALLY VISIBLE
    // ============================================================

    private void swipeUntilRescheduledVisible() {

        for (int attempt = 1; attempt <= MAX_SWIPES; attempt++) {

            try {

                List<WebElement> elements =
                        driver.findElements(rescheduledTab);

                if (!elements.isEmpty()) {

                    WebElement element = elements.get(0);

                    /*
                     * Check actual screen position.
                     *
                     * Your Rescheduled tab should be around:
                     * X = 268 to 488
                     * Y = 192 to 250
                     *
                     * Screen width = 720
                     */
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

                    boolean visible =
                            x >= 0 &&
                                    y >= 0 &&
                                    x + width <= screenWidth &&
                                    y + height <= screenHeight &&
                                    element.isDisplayed();

                    if (visible) {

                        System.out.println(
                                "SUCCESS : Rescheduled is visible"
                        );

                        System.out.println(
                                "Rescheduled bounds : ["
                                        + x + ","
                                        + y + "]["
                                        + (x + width) + ","
                                        + (y + height) + "]"
                        );

                        return;
                    }
                }

            } catch (Exception ignored) {
            }

            // ====================================================
            // SWIPE TAB BAR LEFT
            // ====================================================

            System.out.println(
                    "ACTION : Swiping tabs left - attempt "
                            + attempt
            );

            try {

                driver.executeScript(
                        "mobile: swipeGesture",
                        Map.of(
                                "left", 30,
                                "top", 170,
                                "width", 660,
                                "height", 110,
                                "direction", "left",
                                "percent", 0.70
                        )
                );

                waitForPageStable();

            } catch (Exception e) {

                System.out.println(
                        "WARNING : Swipe failed : "
                                + e.getMessage()
                );
            }
        }

        // ========================================================
        // FINAL CHECK
        // ========================================================

        try {

            WebElement rescheduled =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    rescheduledTab
                            )
                    );

            if (!rescheduled.isDisplayed()) {

                throw new RuntimeException(
                        "Rescheduled tab is not visible"
                );
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Rescheduled tab could not be made visible after swiping",
                    e
            );
        }

        System.out.println(
                "SUCCESS : Rescheduled became visible"
        );
    }

    // ============================================================
    // CLICK RESCHEDULED
    // ============================================================

    private void clickRescheduled() {

        try {

            WebElement rescheduled =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    rescheduledTab
                            )
                    );

            rescheduled.click();

            System.out.println(
                    "SUCCESS : Rescheduled clicked"
            );

            waitForPageStable();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to click Rescheduled",
                    e
            );
        }
    }

    // ============================================================
    // CHECK RESCHEDULED ORDERS
    // ============================================================

    private void checkOrders() {

        List<WebElement> orders =
                driver.findElements(
                        rescheduledOrders
                );

        if (orders.isEmpty()) {

            System.out.println();
            System.out.println(
                    "NO DATA FOUND : No Rescheduled orders available"
            );

            return;
        }

        int count = 0;

        System.out.println();
        System.out.println(
                "SUCCESS : Rescheduled orders found"
        );

        for (WebElement order : orders) {

            try {

                if (!order.isDisplayed()) {
                    continue;
                }

                String details =
                        order.getAttribute(
                                "content-desc"
                        );

                if (details == null ||
                        details.isBlank()) {
                    continue;
                }

                count++;

                System.out.println();
                System.out.println(
                        "Rescheduled Order "
                                + count
                );

                System.out.println(
                        "Details : "
                                + details.replace(
                                "\n",
                                " | "
                        )
                );

            } catch (Exception ignored) {
            }
        }

        if (count == 0) {

            System.out.println(
                    "NO DATA FOUND : No visible Rescheduled orders"
            );

        } else {

            System.out.println();
            System.out.println(
                    "TOTAL RESCHEDULED ORDERS FOUND : "
                            + count
            );
        }
    }

    // ============================================================
    // STABILITY
    // ============================================================

    private void waitForPageStable() {

        try {

            Thread.sleep(300);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}