package Base;

import utilities.MobileUtilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

public class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected MobileUtilities utility;

    // =========================================================
    // Constructor
    // =========================================================

    public BasePage(AndroidDriver driver) {

        // IMPORTANT FIX
        this.driver = driver;

        this.wait = BaseClassMobile.wait;

        this.utility =
                new MobileUtilities(this.driver);
    }

    // =========================================================
    // Scroll Down
    // =========================================================

    public void scrollDown() {

        Dimension size =
                driver.manage()
                        .window()
                        .getSize();

        int x = size.width / 2;

        int startY =
                (int) (size.height * 0.80);

        int endY =
                (int) (size.height * 0.25);

        PointerInput finger =
                new PointerInput(
                        PointerInput.Kind.TOUCH,
                        "finger");

        Sequence swipe =
                new Sequence(finger, 1);

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        x,
                        startY));

        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(500),
                        PointerInput.Origin.viewport(),
                        x,
                        endY));

        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(
                Collections.singletonList(swipe));
    }

    // =========================================================
    // Scroll Up
    // =========================================================

    public void scrollUp() {

        Dimension size =
                driver.manage()
                        .window()
                        .getSize();

        driver.executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", 0,
                        "top", 0,
                        "width", size.width,
                        "height", size.height,
                        "direction", "up",
                        "percent", 0.80
                ));
    }

    // =========================================================
    // Click
    // =========================================================

    protected void click(By locator) {
        utility.click(locator);
    }

    // =========================================================
    // Mobile Click
    // =========================================================

    protected void mobileClick(By locator) {
        utility.mobileClick(locator);
    }

    // =========================================================
    // Send Keys
    // =========================================================

    protected void sendKeys(
            By locator,
            String value) {

        utility.sendKeys(locator, value);
    }

    // =========================================================
    // Hide Keyboard
    // =========================================================

    protected void hideKeyboard() {
        utility.hideKeyboard();
    }

    // =========================================================
    // Wait Visible
    // =========================================================

    protected void waitVisible(By locator) {
        utility.waitForVisible(locator);
    }

    // =========================================================
    // Wait Clickable
    // =========================================================

    protected void waitClickable(By locator) {
        utility.waitForClickable(locator);
    }

    // =========================================================
    // Get Element
    // =========================================================

    protected WebElement getElement(By locator) {

        return utility.waitForVisible(locator);
    }

    // =========================================================
    // Get Clickable Element
    // =========================================================

    protected WebElement getClickableElement(
            By locator) {

        return utility.waitForClickable(locator);
    }

    // =========================================================
    // Is Displayed
    // =========================================================

    protected boolean isDisplayed(By locator) {

        try {

            return utility
                    .waitForVisible(locator)
                    .isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // =========================================================
    // Get Text
    // =========================================================

    protected String getText(By locator) {

        return utility
                .waitForVisible(locator)
                .getText();
    }

    // =========================================================
    // Clear
    // =========================================================

    protected void clear(By locator) {

        utility
                .waitForVisible(locator)
                .clear();
    }

    // =========================================================
    // Delay
    // =========================================================

    protected void delay(int seconds) {

        utility.delay(seconds);
    }

    // =========================================================
    // Log
    // =========================================================

    protected void log(String message) {

        ExtentTestListener.logStep(message);
    }
}