package com.AndroidTest.JAccountProfileSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GAddressPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final String APP_PACKAGE = "com.abqaiq.smartsort";

    // ================= LOCATORS =================

    private final By address =
            AppiumBy.accessibilityId("Address");

    private final By selectCity =
            AppiumBy.accessibilityId("Select City");

    private final By chennai =
            AppiumBy.accessibilityId("Chennai");

    private final By selectDistrict =
            AppiumBy.accessibilityId("Select district");

    private final By ambattur =
            AppiumBy.xpath(
                    "(//android.widget.ImageView[@content-desc='Ambattur'])[1]"
            );

    // Dynamic address - do NOT use the complete content-desc
    private final By chooseLocation =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'Choose location from map')]"
            );

    private final By ok =
            AppiumBy.accessibilityId("OK");

    private final By save =
            AppiumBy.accessibilityId("Save");

    private final By showMenu =
            AppiumBy.accessibilityId("Show menu");

    private final By deleteAddress =
            AppiumBy.accessibilityId("Delete Address");


    // ================= CONSTRUCTOR =================

    public GAddressPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    // ================= WAIT =================

    private WebElement waitForVisible(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }


    // ================= STABLE CLICK =================

    private void click(By locator) {

        WebElement element = waitForVisible(locator);

        try {
            element.click();
            return;
        } catch (Exception ignored) {
        }

        // Fallback click using coordinates
        try {

            int x = element.getRect().getX()
                    + element.getRect().getWidth() / 2;

            int y = element.getRect().getY()
                    + element.getRect().getHeight() / 2;

            Map<String, Object> point = new HashMap<>();
            point.put("x", x);
            point.put("y", y);

            driver.executeScript(
                    "mobile: clickGesture",
                    point
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to click: " + locator,
                    e
            );
        }
    }


    // ================= APP FOREGROUND =================

    private void ensureAppForeground() {

        try {

            String currentPackage =
                    driver.getCurrentPackage();

            if (!APP_PACKAGE.equals(currentPackage)) {

                driver.activateApp(APP_PACKAGE);

                wait.until(d ->
                        APP_PACKAGE.equals(
                                driver.getCurrentPackage()
                        )
                );
            }

        } catch (Exception e) {

            driver.activateApp(APP_PACKAGE);

            wait.until(d ->
                    APP_PACKAGE.equals(
                            driver.getCurrentPackage()
                    )
            );
        }
    }


    // ================= SAFE SWIPE =================

    private void scrollDown() {

        ensureAppForeground();

        PointerInput finger =
                new PointerInput(
                        PointerInput.Kind.TOUCH,
                        "finger"
                );

        Sequence swipe =
                new Sequence(finger, 0);

        // Start inside application
        swipe.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        360,
                        1250
                )
        );

        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        // Move UP = scroll page DOWN
        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(600),
                        PointerInput.Origin.viewport(),
                        360,
                        600
                )
        );

        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );

        driver.perform(
                Collections.singletonList(swipe)
        );

        ensureAppForeground();
    }


    // ================= SCROLL UNTIL VISIBLE =================

    private void scrollUntilVisible(By locator) {

        for (int i = 0; i < 6; i++) {

            ensureAppForeground();

            try {

                WebElement element =
                        driver.findElement(locator);

                if (element.isDisplayed()) {
                    return;
                }

            } catch (Exception ignored) {
            }

            scrollDown();
        }

        // Final wait
        waitForVisible(locator);
    }


    // ================= WAIT ADDRESS PAGE =================

    private void waitForAddressPage() {

        wait.until(d -> {

            try {

                return !driver.findElements(address).isEmpty()
                        || !driver.findElements(selectCity).isEmpty();

            } catch (Exception e) {
                return false;
            }
        });
    }


    // ================= MAIN FLOW =================

    public void addAndDeleteAddress() throws InterruptedException {
//
//        // 1. Make sure app is open
//        ensureAppForeground();

        // 2. Scroll down to Address
        scrollUntilVisible(address);

        // 3. Click Address
        click(address);

        // 4. Select City
        click(selectCity);

        // 5. Select Chennai
        click(chennai);

        // 6. Select District
        click(selectDistrict);

        // 7. Select Ambattur
        click(ambattur);

        // 8. Wait for location
        waitForVisible(chooseLocation);

        // 9. Click Choose Location from Map
        click(chooseLocation);

        // 10. First OK
        click(ok);

        // 11. Save
        click(save);

        // 12. Wait for Address page
        waitForAddressPage();

        // 13. Scroll down again
        scrollUntilVisible(address);

        // 14. Click Address
        click(address);

        // 15. Click three dots
        click(showMenu);
        Thread.sleep(3000);
        // 16. Delete Address
        click(deleteAddress);
        Thread.sleep(3000);
        // 17. First confirmation OK
        click(ok);
        Thread.sleep(3000);
        // 18. Second confirmation OK
        click(ok);
        Thread.sleep(3000);
        // 19. Go back
        ensureAppForeground();
        driver.navigate().back();
    }
}