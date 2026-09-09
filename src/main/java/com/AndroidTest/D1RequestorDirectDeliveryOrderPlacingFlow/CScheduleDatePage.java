package com.AndroidTest.D1RequestorDirectDeliveryOrderPlacingFlow;

import Base.BaseClassMobile;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CScheduleDatePage extends BaseClassMobile {

    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

    private final By confirmButton =
            AppiumBy.accessibilityId("Confirm");

    private final By okButton =
            AppiumBy.accessibilityId("OK");

    public void selectTodayDate() {
        scrollDown();
        LocalDate today = LocalDate.now();

        String month = today.format(
                DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH));

        String day = String.valueOf(today.getDayOfMonth());

        String weekDay = today.format(
                DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH));

        String dateDescription =
                month + "\n" + day + "\n" + weekDay;

        By dateLocator = AppiumBy.xpath(
                "//android.view.View[@content-desc='" +
                        dateDescription + "']"
        );

        wait.until(
                        ExpectedConditions.elementToBeClickable(dateLocator))
                .click();

        System.out.println(
                "Schedule Date Selected : " +
                        month + " " + day + " " + weekDay);
    }

    public void selectTime() {

        scrollDown();

        By nextHour = AppiumBy.xpath(
                "//android.view.View[@content-desc='10']/android.view.View[1]"
        );

        WebElement hourButton = wait.until(
                ExpectedConditions.elementToBeClickable(nextHour)
        );

        hourButton.click();

        System.out.println("Next Hour Selected");
    }

    public void confirmTime() {

        wait.until(driver -> {
            WebElement button = driver.findElement(confirmButton);

            return button.isDisplayed()
                    && button.isEnabled()
                    && button.isEnabled();
        });

        driver.findElement(confirmButton).click();

        System.out.println("Schedule Time Confirmed");
    }

    public void clickOK() {

        wait.until(
                        ExpectedConditions.elementToBeClickable(okButton))
                .click();

        System.out.println("OK Popup Clicked");
    }

    public void selectTodayDateAndConfirm() {

        selectTodayDate();
        selectTime();
        confirmTime();
        clickOK();

        System.out.println(
                "Schedule Date & Time Completed Successfully");
    }
}