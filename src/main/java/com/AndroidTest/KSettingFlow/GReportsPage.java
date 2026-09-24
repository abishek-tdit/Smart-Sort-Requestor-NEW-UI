package com.AndroidTest.KSettingFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GReportsPage extends BaseClassMobile {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

// ============================================================
// CONSTRUCTOR
// ============================================================

    public GReportsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

// ============================================================
// LOCATORS
// ============================================================

    // Report menu in Settings
    private final By REPORT =
            AppiumBy.accessibilityId("Report");

    // Reports page title
    private final By REPORT_PAGE_TITLE =
            By.xpath("//android.view.View[@content-desc='Reports']");

    // ScrollView used by Settings / Reports page
    private final By SCROLL_VIEW =
            By.xpath("//android.widget.ScrollView[@scrollable='true']");

// ============================================================
// MAIN FLOW
// ============================================================

    public void validateReportsPage() {

        System.out.println();
        System.out.println("==================================================");
        ExtentTestListener.logStep("          REPORT PAGE VALIDATION STARTED");
        System.out.println("==================================================");

        // --------------------------------------------------------
        // STEP 1 - Validate Report menu item
        // --------------------------------------------------------

        System.out.println();
        ExtentTestListener.logStep("----- STEP 1 : VALIDATE REPORT MENU -----");

        WebElement reportElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(REPORT)
        );

        System.out.println("Report element found");
        System.out.println("Report displayed : " + reportElement.isDisplayed());
        System.out.println("Report enabled   : " + reportElement.isEnabled());

        if (!reportElement.isDisplayed()) {
            throw new RuntimeException("Report element is NOT visible");
        }

        if (!reportElement.isEnabled()) {
            throw new RuntimeException("Report element is NOT enabled");
        }

        ExtentTestListener.logStep("Report validation PASSED");

        // --------------------------------------------------------
        // STEP 2 - Click Report
        // --------------------------------------------------------

        System.out.println();
        System.out.println("----- STEP 2 : CLICK REPORT -----");

        reportElement.click();

        ExtentTestListener.logStep("Report clicked successfully");

        // --------------------------------------------------------
        // STEP 3 - Wait for Reports page
        // --------------------------------------------------------

        System.out.println();
        System.out.println("----- STEP 3 : WAIT FOR REPORTS PAGE -----");

        wait.until(driver ->
                driver.getPageSource().contains("Reports")
                        || driver.getPageSource().contains("Report")
        );

        ExtentTestListener.logStep("Reports page loaded successfully");

        // --------------------------------------------------------
        // STEP 4 - Validate initial visible elements
        // --------------------------------------------------------

        System.out.println();
        ExtentTestListener.logStep("----- STEP 4 : INITIAL REPORT PAGE -----");

        validateVisibleElements("INITIAL REPORT PAGE");

        // --------------------------------------------------------
        // STEP 5 - Scroll Reports page
        // --------------------------------------------------------

        System.out.println();
        ExtentTestListener.logStep("----- STEP 5 : SCROLL REPORT PAGE -----");

        scrollReportsPage();

        // --------------------------------------------------------
        // STEP 6 - Validate elements after scroll
        // --------------------------------------------------------

        System.out.println();
        ExtentTestListener.logStep("----- STEP 6 : AFTER SCROLL -----");

        validateVisibleElements("AFTER SCROLL");

        // --------------------------------------------------------
        // FINAL RESULT
        // --------------------------------------------------------

        System.out.println();
        System.out.println("==================================================");
        ExtentTestListener.logStep("       REPORT PAGE VALIDATION COMPLETED");
        System.out.println("==================================================");
    }

// ============================================================
// PRINT + VALIDATE ALL VISIBLE ELEMENTS
// ============================================================

    private void validateVisibleElements(String sectionName) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("----- " + sectionName + " -----");
        System.out.println("==================================================");

        List<WebElement> elements = driver.findElements(
                By.xpath("//*")
        );

        int totalElements = 0;
        int visibleElements = 0;
        int enabledElements = 0;

        for (WebElement element : elements) {

            try {

                if (!element.isDisplayed()) {
                    continue;
                }

                visibleElements++;

                String className = element.getAttribute("className");
                String text = element.getText();
                String contentDesc = element.getAttribute("content-desc");
                String resourceId = element.getAttribute("resource-id");
                String clickable = element.getAttribute("clickable");
                String enabled = element.getAttribute("enabled");
                String bounds = element.getAttribute("bounds");

                if ("true".equalsIgnoreCase(enabled)) {
                    enabledElements++;
                }

                System.out.println();
                System.out.println("--------------------------------------------------");
                System.out.println("VISIBLE ELEMENT #" + visibleElements);
                System.out.println("--------------------------------------------------");

                System.out.println("Class        : " + safeValue(className));
                System.out.println("Text         : " + safeValue(text));
                System.out.println("Content-Desc : " + safeValue(contentDesc));
                System.out.println("Resource-ID  : " + safeValue(resourceId));
                System.out.println("Clickable    : " + safeValue(clickable));
                System.out.println("Enabled      : " + safeValue(enabled));
                System.out.println("Displayed    : true");
                System.out.println("Bounds       : " + safeValue(bounds));

                totalElements++;

            } catch (Exception e) {

                System.out.println(
                        "Unable to validate one element : "
                                + e.getMessage()
                );
            }
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("VISIBLE ELEMENT SUMMARY");
        System.out.println("==================================================");
        System.out.println("Total elements inspected : " + totalElements);
        System.out.println("Visible elements         : " + visibleElements);
        System.out.println("Enabled elements         : " + enabledElements);
        System.out.println("==================================================");
    }

// ============================================================
// SCROLL REPORT PAGE
// ============================================================

    private void scrollReportsPage() {

        try {

            WebElement scrollView = wait.until(
                    ExpectedConditions.presenceOfElementLocated(SCROLL_VIEW)
            );

            ExtentTestListener.logStep("Reports ScrollView found");

            String scrollable =
                    scrollView.getAttribute("scrollable");

            ExtentTestListener.logStep("ScrollView scrollable : " + scrollable);

            // Scroll using UiScrollable
            driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable("
                                    + "new UiSelector().className(\"android.widget.ScrollView\")"
                                    + ").scrollForward()"
                    )
            );

            ExtentTestListener.logStep("Reports page scrolled successfully");

        } catch (Exception e) {

            System.out.println(
                    "UiScrollable could not be used."
            );

            ExtentTestListener.logStep(
                    "Using Appium scrollGesture..."
            );

            try {

                driver.executeScript(
                        "mobile: scrollGesture",
                        java.util.Map.of(
                                "left", 0,
                                "top", 200,
                                "width", 720,
                                "height", 1050,
                                "direction", "down",
                                "percent", 0.75
                        )
                );

                System.out.println(
                        "Reports page scrolled using scrollGesture"
                );

            } catch (Exception scrollException) {

                throw new RuntimeException(
                        "Unable to scroll Reports page: "
                                + scrollException.getMessage(),
                        scrollException
                );
            }
        }
    }

// ============================================================
// SAFE VALUE
// ============================================================

    private String safeValue(String value) {

        if (value == null || value.trim().isEmpty()) {
            return "<empty>";
        }

        return value;
    }
}
