package com.AndroidTest.C2CollectorUtilityOrderCompleteFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CNavigationPage extends BaseClassMobile {

    // =========================================================
    // Locators
    // =========================================================

    private final AppiumBy START_NAVIGATION =
            (AppiumBy) AppiumBy.accessibilityId("Start Navigation");

    private final AppiumBy OK_BUTTON =
            (AppiumBy) AppiumBy.accessibilityId("OK");

    private final AppiumBy REACHED_BUTTON =
            (AppiumBy) AppiumBy.accessibilityId("Reached");


    // =========================================================
    // Complete Navigation Flow
    // =========================================================

    public void completeNavigation() {

        try {

            // ================================================
            // Scroll down until FIRST Start Navigation
            // =================================================

            ExtentTestListener.logStep
                    ("Scrolling down until 1st Start Navigation is visible...");

            Thread.sleep(3000);
            WebElement firstStartNavigation =
                    scrollUntilVisible(START_NAVIGATION, 10);

            ExtentTestListener.logStep("1st Start Navigation found successfully");


            // =================================================
            // Click FIRST Start Navigation
            // =================================================
            Thread.sleep(3000);
            clickElement(firstStartNavigation);

            ExtentTestListener.logStep("1st Start Navigation clicked successfully");


            // =================================================
            // Wait for NEXT page
            // =================================================

            ExtentTestListener.logStep("Waiting for next Navigation page...");
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement secondStartNavigation = wait.until
                    (ExpectedConditions.elementToBeClickable(START_NAVIGATION));

            ExtentTestListener.logStep("2nd Start Navigation found on next page");


            // =================================================
            // Click SECOND Start Navigation
            // =================================================

            clickElement(secondStartNavigation);

            ExtentTestListener.logStep("2nd Start Navigation clicked successfully");


            // =================================================
            // Wait for OK popup
            // =================================================

            ExtentTestListener.logStep("Waiting for OK popup...");

            WebElement okButton =
                    wait.until(ExpectedConditions.elementToBeClickable(OK_BUTTON));

            ExtentTestListener.logStep("OK popup displayed successfully");


            // =================================================
            // Click OK
            // =================================================

            clickElement(okButton);

            ExtentTestListener.logStep("OK clicked successfully");


            // =================================================
            // STEP 7
            // Wait for Reached button
            // =================================================

            ExtentTestListener.logStep("Waiting for Reached button...");

            WebElement reachedButton =
                    wait.until(ExpectedConditions.elementToBeClickable(REACHED_BUTTON));

            ExtentTestListener.logStep("Reached button displayed successfully");


            // =================================================
            // Click Reached
            // =================================================

            clickElement(reachedButton);

            ExtentTestListener.logStep("Reached button clicked successfully");


            // =================================================
            // FLOW COMPLETED
            // =================================================

            ExtentTestListener.logStep("Navigation Flow completed successfully");
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("Navigation Flow Failed : " + e.getMessage());

            throw new RuntimeException("Navigation Flow Failed", e);
        }
    }


    // =========================================================
    // REAL PHYSICAL SCROLL
    // =========================================================

    private WebElement scrollUntilVisible(AppiumBy locator,
            int maxScrolls) {

        for (int i = 1; i <= maxScrolls; i++) {

            try {

                // ---------------------------------------------
                // Check whether element is currently visible
                // ---------------------------------------------

                List<WebElement> elements = driver.findElements(locator);

                if (!elements.isEmpty()) {
                    for (WebElement element : elements) {
                        try {
                            if (element.isDisplayed()) {

                                ExtentTestListener.logStep("Start Navigation visible after scroll attempt " + i);

                                return element;
                            }
                        }
                        catch (Exception ignored)
                        {
                        }
                    }
                }

                // ---------------------------------------------
                // Element not visible -> REAL SWIPE UP
                // ---------------------------------------------

                Dimension screenSize = driver.manage().window().getSize();

                int width = screenSize.getWidth();
                int height = screenSize.getHeight();

                int left = 0;
                int top = 100;

                int scrollWidth = width;
                int scrollHeight = height - 200;

                ExtentTestListener.logStep("Performing swipe up : attempt " + i);

                Boolean canScroll = (Boolean) driver.executeScript("mobile: swipeGesture",
                        Map.of("left", left,
                                "top", top,
                                "width", scrollWidth,
                                "height", scrollHeight,
                                "direction", "up",
                                "percent", 0.80));

                ExtentTestListener.logStep("Swipe completed : attempt "
                                + i
                                + " | canScroll = "
                                + canScroll);

                Thread.sleep(300);

                // If Android says it cannot scroll anymore,
                // check one final time before failing.
                if (Boolean.FALSE.equals(canScroll)) {

                    List<WebElement> finalElements = driver.findElements(locator);

                    for (WebElement element : finalElements) {
                        try {
                            if (element.isDisplayed()) {
                                return element;
                            }
                        }
                        catch (Exception ignored) {
                        }
                    }
                    break;
                }
            }
            catch (Exception e)
            {
                ExtentTestListener.logStep("Scroll attempt "
                                + i
                                + " failed : "
                                + e.getMessage());
            }
        }

        throw new RuntimeException("Start Navigation was NOT found after "
                        + maxScrolls
                        + " scroll attempts");
    }


    // =========================================================
    // CLICK ELEMENT USING APPIUM CLICK GESTURE
    // =========================================================

    private void clickElement(WebElement element) {

        try {

            driver.executeScript("mobile: clickGesture",
                    Map.of("elementId", ((RemoteWebElement) element).getId()));
        }
        catch (Exception e)
        {
            element.click();
        }
    }
}