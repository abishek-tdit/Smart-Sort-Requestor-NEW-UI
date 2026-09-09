package com.AndroidTest.KSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ALanguagePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Language English
    private final By languageEnglish =
            AppiumBy.xpath("//android.view.View[@content-desc='Language English']");

    private final By arabicLanguage =
            AppiumBy.xpath("//android.widget.Button[@content-desc='AR العربية']");

    private final By languageArabic =
            AppiumBy.xpath("//android.view.View[@content-desc='اللغة العربية']");

    private final By englishLanguage =
            AppiumBy.xpath("//android.widget.Button[@content-desc='EN English']");

    public ALanguagePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void completeLanguageFlow() throws InterruptedException {

        // Click Language English
        WebElement language = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Language\nEnglish"))
        );
        language.click();

        System.out.println("Language English clicked");

        // Select Arabic
        WebElement arabic = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("AR\nالعربية"))
        );
        arabic.click();

        System.out.println("Arabic selected");

        // Wait for page reload
        Thread.sleep(3000);

        // Click Arabic Language
        WebElement arabicPage = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("اللغة\nالعربية"))
        );
        arabicPage.click();

        System.out.println("Arabic Language clicked");

        // Select English
        WebElement english = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("EN\nEnglish"))
        );
        english.click();

        System.out.println("English selected");
    }
}