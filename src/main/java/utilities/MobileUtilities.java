package utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class MobileUtilities {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public static String referralCode = "";
    public static String generatedMobileNumber = "";
    public static String generatedPassword =
            "Admin@194";

    private static final int WAIT_TIME = 40;

    // =========================================================
    // Constructor
    // =========================================================

    public MobileUtilities(AndroidDriver driver) {

        if (driver == null) {

            throw new IllegalArgumentException(
                    "AndroidDriver is NULL. " +
                            "Check BasePage constructor.");
        }

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(WAIT_TIME));
    }

    // =========================================================
    // Wait Until Visible
    // =========================================================

    public WebElement waitForVisible(By locator) {

        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(locator));
    }

    // =========================================================
    // Wait Until Clickable
    // =========================================================

    public WebElement waitForClickable(By locator) {

        return wait.until(
                ExpectedConditions
                        .elementToBeClickable(locator));
    }

    // =========================================================
    // Send Keys
    // =========================================================

    public void sendKeys(
            By locator,
            String value) {

        WebElement element =
                waitForVisible(locator);

        element.click();
        element.clear();
        element.sendKeys(value);
    }

    // =========================================================
    // Normal Click
    // =========================================================

    public void click(By locator) {

        waitForClickable(locator).click();
    }

    // =========================================================
    // Mobile Click Gesture
    // =========================================================

    public void mobileClick(By locator) {

        WebElement element =
                wait.until(
                        ExpectedConditions
                                .presenceOfElementLocated(locator));

        driver.executeScript(
                "mobile: clickGesture",
                Map.of(
                        "elementId",
                        ((RemoteWebElement) element)
                                .getId()));
    }

    // =========================================================
    // Hide Keyboard
    // =========================================================

    public void hideKeyboard() {

        try {

            driver.hideKeyboard();

        } catch (Exception e) {

            System.out.println(
                    "Keyboard already hidden.");
        }
    }

    // =========================================================
    // Delay
    // =========================================================

    public void delay(int seconds) {

        try {

            Thread.sleep(seconds * 1000L);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    // =========================================================
    // Wait for Element
    // =========================================================

    public WebElement waitForElement(
            WebElement element) {

        return wait.until(
                ExpectedConditions
                        .visibilityOf(element));
    }

    // =========================================================
    // Wait by Accessibility ID
    // =========================================================

    public WebElement waitForAccessibilityId(
            String id) {

        return wait.until(
                ExpectedConditions
                        .presenceOfElementLocated(
                                AppiumBy.accessibilityId(id)));
    }

    // =========================================================
    // Click WebElement
    // =========================================================

    public void click(WebElement element) {

        wait.until(
                        ExpectedConditions
                                .elementToBeClickable(element))
                .click();
    }

    // =========================================================
    // Scroll To Text
    // =========================================================

    public void scrollToText(String text) {

        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable("
                                + "new UiSelector().scrollable(true))"
                                + ".setMaxSearchSwipes(10)"
                                + ".scrollIntoView("
                                + "new UiSelector().textContains(\""
                                + text
                                + "\"))"));
    }

    // =========================================================
    // Scroll To Description
    // =========================================================

    public void scrollToDescription(
            String description) {

        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable("
                                + "new UiSelector().scrollable(true))"
                                + ".setMaxSearchSwipes(10)"
                                + ".scrollIntoView("
                                + "new UiSelector()"
                                + ".descriptionContains(\""
                                + description
                                + "\"))"));
    }

    // =========================================================
    // Swipe Up
    // =========================================================

    public void swipeUp(double percentage) {

        Dimension size =
                driver.manage()
                        .window()
                        .getSize();

        driver.executeScript(
                "mobile: swipeGesture",
                Map.of(
                        "left", 0,
                        "top", 0,
                        "width", size.width,
                        "height", size.height,
                        "direction", "up",
                        "percent", percentage
                ));
    }

    // =========================================================
    // Swipe Down
    // =========================================================

    public void swipeDown() {

        Dimension size =
                driver.manage()
                        .window()
                        .getSize();

        driver.executeScript(
                "mobile: swipeGesture",
                Map.of(
                        "left", 0,
                        "top", 0,
                        "width", size.width,
                        "height", size.height,
                        "direction", "down",
                        "percent", 0.80
                ));
    }

    // =========================================================
    // Long Press
    // =========================================================

    public void longPress(By locator) {

        WebElement element =
                wait.until(
                        ExpectedConditions
                                .presenceOfElementLocated(locator));

        driver.executeScript(
                "mobile: longClickGesture",
                Map.of(
                        "elementId",
                        ((RemoteWebElement) element)
                                .getId(),
                        "duration",
                        2000
                ));
    }
}