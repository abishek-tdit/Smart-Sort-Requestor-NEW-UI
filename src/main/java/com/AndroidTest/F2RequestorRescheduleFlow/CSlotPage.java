package com.AndroidTest.F2RequestorRescheduleFlow;

import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CSlotPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public CSlotPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void selectSlotTiming() throws Exception {

        Thread.sleep(5000);

        boolean clicked = false;

        for (int i = 0; i < 5; i++) {

            try {

                // Select Tomorrow Date
                List<WebElement> dates = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                AppiumBy.xpath("//android.view.View[@clickable='true']")
                        ));


                if (dates.size() < 2) {
                    throw new RuntimeException("Tomorrow date not found.");
                }

                WebElement tomorrow = dates.get(1);

                wait.until(ExpectedConditions.elementToBeClickable(tomorrow));
                tomorrow.click();

                ExtentTestListener.logStep(
                        "Tomorrow selected : " + tomorrow.getAttribute("content-desc"));

                // Select First Slot
                List<WebElement> slots = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                AppiumBy.xpath("//android.view.View[contains(@content-desc,'AM') or contains(@content-desc,'PM')]")
                        ));

                if (slots.isEmpty()) {
                    throw new RuntimeException("No slot timings available.");
                }

                WebElement firstSlot = slots.get(0);

                ExtentTestListener.logStep(
                        "Selecting Slot : " + firstSlot.getAttribute("content-desc"));

                wait.until(ExpectedConditions.elementToBeClickable(firstSlot));

                firstSlot.click();

                Thread.sleep(1000);

                ExtentTestListener.logStep("Tomorrow date and first slot selected successfully.");

                clicked = true;
                break;

            } catch (Exception e) {

                System.out.println("Retry : " + (i + 1));

                driver.executeScript("mobile: swipeGesture",
                        Map.of(
                                "left", 100,
                                "top", 300,
                                "width", 500,
                                "height", 1000,
                                "direction", "up",
                                "percent", 0.5));

                Thread.sleep(2000);
            }
        }

        if (!clicked) {
            throw new RuntimeException("Unable to select tomorrow's date or slot timing.");
        }
    }

    // Confirm Reschedule
    public void confirmReschedule() throws Exception {

        // Wait 2 seconds after slot selection
        Thread.sleep(2000);

        WebElement confirmBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Confirm")
                )
        );

        confirmBtn.click();

        ExtentTestListener.logStep(
                "Confirm button clicked successfully"
        );

        Thread.sleep(3000);

        WebElement okBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("OK")
                )
        );

        okBtn.click();

        ExtentTestListener.logStep(
                "OK popup clicked successfully"
        );
    }
}