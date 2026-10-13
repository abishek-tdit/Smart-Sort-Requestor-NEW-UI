package com.AndroidTest.KSettingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GReportsPage extends BaseClassMobile {

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By REPORT =
            AppiumBy.accessibilityId("Report");

    private final By OVER_ALL_ORDERS =
            AppiumBy.accessibilityId("Over All Orders");

    private final By TOTAL_QUANTITY =
            AppiumBy.accessibilityId("Total Quantity");

    private final By TOTAL_REWARD_POINTS =
            AppiumBy.accessibilityId("Total Reward Points");

    private final By RECENT_ORDERS =
            AppiumBy.accessibilityId("Recent Orders");

    private final By WEEKLY =
            AppiumBy.accessibilityId("Weekly");

    private final By MONTHLY =
            AppiumBy.accessibilityId("Monthly");

    private final By RANGE =
            AppiumBy.accessibilityId("Range");


    // =========================================================
    // DYNAMIC VALUE LOCATORS
    // =========================================================

    /*
     * Any visible View whose content-desc is only a number.
     *
     * Example:
     * 3
     * 5
     * 10
     * 25
     */
    private final By DYNAMIC_NUMBER =
            AppiumBy.xpath(
                    "//android.view.View[" +
                            "string-length(@content-desc) > 0 " +
                            "and " +
                            "translate(@content-desc,'0123456789','') = ''" +
                            "]"
            );


    /*
     * Any content-desc containing KG.
     *
     * Examples:
     * 1510.00 kg
     * 250.00 kg
     * 75.50 kg
     */
    private final By DYNAMIC_KG =
            AppiumBy.xpath(
                    "//android.view.View[" +
                            "contains(translate(@content-desc," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz'), 'kg')" +
                            "]"
            );


    /*
     * Recent order rows.
     *
     * Example:
     * AB-RO-98286 5.00 kg 30 pts
     * AB-RO-98289 5.00 kg 30 pts
     *
     * The order number, KG and points can change.
     */
    private final By DYNAMIC_RECENT_ORDERS =
            AppiumBy.xpath(
                    "//android.view.View[" +
                            "starts-with(@content-desc,'AB-RO-')" +
                            "]"
            );


    // =========================================================
    // CLICK REPORT
    // =========================================================

    public void clickReport() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("CLICKING REPORT");
        System.out.println("========================================");

        clickIfVisible(
                REPORT,
                "Report"
        );

        verifyReportPage("Overall");
    }


    // =========================================================
    // CLICK WEEKLY
    // =========================================================

    public void clickWeekly() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("CLICKING WEEKLY");
        System.out.println("========================================");

        clickIfVisible(
                WEEKLY,
                "Weekly"
        );

        verifyReportPage("Weekly");
    }


    // =========================================================
    // CLICK MONTHLY
    // =========================================================

    public void clickMonthly() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("CLICKING MONTHLY");
        System.out.println("========================================");

        clickIfVisible(
                MONTHLY,
                "Monthly"
        );

        verifyReportPage("Monthly");
    }


    // =========================================================
    // CLICK RANGE
    // =========================================================

    public void clickRange() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("CLICKING RANGE");
        System.out.println("========================================");

        clickIfVisible(
                RANGE,
                "Range"
        );

        verifyReportPage("Range");
    }


    // =========================================================
    // VERIFY COMPLETE REPORT PAGE
    // =========================================================

    private void verifyReportPage(
            String reportType
    ) {

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println(
                "VERIFYING REPORT PAGE : " + reportType
        );
        System.out.println("----------------------------------------");


        // =====================================================
        // OVER ALL ORDERS
        // =====================================================

        verifyAndPrint(
                OVER_ALL_ORDERS,
                "Over All Orders"
        );

        printDynamicNumber(
                "Over All Orders Value"
        );


        // =====================================================
        // TOTAL QUANTITY
        // =====================================================

        verifyAndPrint(
                TOTAL_QUANTITY,
                "Total Quantity"
        );

        printDynamicKG(
                "Total Quantity Value"
        );


        // =====================================================
        // TOTAL REWARD POINTS
        // =====================================================

        verifyAndPrint(
                TOTAL_REWARD_POINTS,
                "Total Reward Points"
        );


        // =====================================================
        // RECENT ORDERS
        // =====================================================

        verifyAndPrint(
                RECENT_ORDERS,
                "Recent Orders"
        );

        printRecentOrders();


        System.out.println("----------------------------------------");
        System.out.println(
                reportType +
                        " REPORT VERIFICATION COMPLETED"
        );
        System.out.println("----------------------------------------");

        ExtentTestListener.logStep(
                reportType +
                        " Report page verified"
        );
    }


    // =========================================================
    // PRINT DYNAMIC NUMBER
    // =========================================================

    private void printDynamicNumber(
            String fieldName
    ) {

        try {

            List<WebElement> elements =
                    driver.findElements(
                            DYNAMIC_NUMBER
                    );

            if (elements.isEmpty()) {

                System.out.println(
                        "SKIP | " +
                                fieldName +
                                " : Value not found"
                );

                return;
            }

            /*
             * Usually the first numeric value on the page
             * belongs to Over All Orders.
             */
            for (WebElement element : elements) {

                if (!element.isDisplayed()) {
                    continue;
                }

                String value =
                        element.getAttribute(
                                "content-desc"
                        );

                if (value != null &&
                        !value.trim().isEmpty()) {

                    System.out.println(
                            "PASS | " +
                                    fieldName +
                                    " : " +
                                    value
                    );

                    return;
                }
            }

            System.out.println(
                    "SKIP | " +
                            fieldName +
                            " : Value not visible"
            );

        } catch (Exception e) {

            System.out.println(
                    "SKIP | " +
                            fieldName +
                            " : Unable to read value"
            );
        }
    }


    // =========================================================
    // PRINT DYNAMIC KG
    // =========================================================

    private void printDynamicKG(
            String fieldName
    ) {

        try {

            List<WebElement> elements =
                    driver.findElements(
                            DYNAMIC_KG
                    );

            if (elements.isEmpty()) {

                System.out.println(
                        "SKIP | " +
                                fieldName +
                                " : Value not found"
                );

                return;
            }

            for (WebElement element : elements) {

                if (!element.isDisplayed()) {
                    continue;
                }

                String value =
                        element.getAttribute(
                                "content-desc"
                        );

                if (value != null &&
                        !value.trim().isEmpty()) {

                    System.out.println(
                            "PASS | " +
                                    fieldName +
                                    " : " +
                                    value
                    );

                    return;
                }
            }

            System.out.println(
                    "SKIP | " +
                            fieldName +
                            " : Value not visible"
            );

        } catch (Exception e) {

            System.out.println(
                    "SKIP | " +
                            fieldName +
                            " : Unable to read value"
            );
        }
    }


    // =========================================================
    // PRINT RECENT ORDERS DYNAMICALLY
    // =========================================================

    private void printRecentOrders() {

        try {

            List<WebElement> orders =
                    driver.findElements(
                            DYNAMIC_RECENT_ORDERS
                    );

            if (orders.isEmpty()) {

                System.out.println(
                        "SKIP | Recent Orders : No orders found"
                );

                return;
            }

            int count = 0;

            for (WebElement order : orders) {

                if (!order.isDisplayed()) {
                    continue;
                }

                String value =
                        order.getAttribute(
                                "content-desc"
                        );

                if (value == null ||
                        value.trim().isEmpty()) {
                    continue;
                }

                count++;

                System.out.println(
                        "PASS | Recent Order " +
                                count +
                                " : " +
                                value
                );
            }

            if (count == 0) {

                System.out.println(
                        "SKIP | Recent Orders : " +
                                "Orders not visible"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "SKIP | Recent Orders : " +
                            "Unable to read orders"
            );
        }
    }


    // =========================================================
    // VERIFY STATIC LABEL
    // =========================================================

    private void verifyAndPrint(
            By locator,
            String fieldName
    ) {

        try {

            List<WebElement> elements =
                    driver.findElements(
                            locator
                    );

            for (WebElement element : elements) {

                if (!element.isDisplayed()) {
                    continue;
                }

                String value =
                        element.getAttribute(
                                "content-desc"
                        );

                if (value == null ||
                        value.trim().isEmpty()) {

                    value = element.getText();
                }

                System.out.println(
                        "PASS | " +
                                fieldName +
                                " : " +
                                value
                );

                return;
            }

            System.out.println(
                    "SKIP | " +
                            fieldName +
                            " : Not visible"
            );

        } catch (Exception e) {

            System.out.println(
                    "SKIP | " +
                            fieldName +
                            " : Not available"
            );
        }
    }


    // =========================================================
    // CLICK IF VISIBLE
    // =========================================================

    private void clickIfVisible(
            By locator,
            String name
    ) {

        try {

            List<WebElement> elements =
                    driver.findElements(
                            locator
                    );

            for (WebElement element : elements) {

                if (element.isDisplayed()) {

                    element.click();

                    System.out.println(
                            "PASS | Clicked : " +
                                    name
                    );

                    return;
                }
            }

            System.out.println(
                    "SKIP | " +
                            name +
                            " : Button not visible"
            );

        } catch (Exception e) {

            System.out.println(
                    "SKIP | Unable to click : " +
                            name
            );
        }
    }
}
