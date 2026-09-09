package com.AndroidTest.JAccountProfileSettingFlow;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CLanguagePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public CLanguagePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================================================
    // LOCATORS
    // =========================================================

    private final By languageEnglish =
            AppiumBy.xpath(
                    "//android.view.View[" +
                            "contains(@content-desc,'Language')" +
                            " and contains(@content-desc,'English')" +
                            "]"
            );

    // Arabic option in language popup
    private final By arabicOption =
            AppiumBy.xpath(
                    "//android.widget.Button[" +
                            "contains(@content-desc,'AR') and " +
                            "contains(@content-desc,'العربية')" +
                            "]"
            );

    // Arabic language
    private final By arabicLanguage =
            AppiumBy.xpath(
                    "//android.view.View[contains(@content-desc,'اللغة') and contains(@content-desc,'العربية')]" );

    // English option in Arabic language popup
    private final By englishOption =
            AppiumBy.xpath(
                    "//*[contains(@content-desc,'EN') and " +
                            "contains(@content-desc,'English')]"
            );


    // =========================================================
    // CLICK LANGUAGE ENGLISH
    // =========================================================

    public void clickLanguageEnglish() {

        WebElement language = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        languageEnglish
                )
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        language
                )
        );

        language.click();

        System.out.println(
                "Language English clicked successfully"
        );
    }


    // =========================================================
    // SELECT ARABIC
    // =========================================================

    public void selectArabic() {

        System.out.println(
                "Waiting for Arabic option..."
        );

        WebElement arabic = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        arabicOption
                )
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        arabic
                )
        );

        arabic.click();

        System.out.println(
                "AR العربية selected successfully"
        );

        // Wait until Arabic language text appears
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        arabicLanguage
                )
        );

        System.out.println(
                "Arabic language displayed successfully"
        );
    }


    // =========================================================
    // CLICK ARABIC LANGUAGE
    // =========================================================

    public void clickArabicLanguage() {

        WebElement arabic = wait.until( ExpectedConditions.elementToBeClickable(arabicLanguage) );

        arabic.click();
        System.out.println("AR العربية selected successfully");
    }


    // =========================================================
    // SELECT ENGLISH
    // =========================================================

    public void selectEnglish() {

        System.out.println(
                "Waiting for EN English option..."
        );

        /*
         * After clicking اللغة العربية,
         * the popup may take time to render.
         *
         * Wait for the EN English button to appear.
         */
        WebElement english = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        englishOption
                )
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        english
                )
        );

        english.click();

        System.out.println(
                "EN English selected successfully"
        );

        // Wait until English language is displayed again
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        languageEnglish
                )
        );

        System.out.println(
                "English language restored successfully"
        );
    }


    // =========================================================
    // COMPLETE LANGUAGE FLOW
    // =========================================================

    public void changeArabicAndBackToEnglish() {

        // Step 1
        clickLanguageEnglish();

        // Step 2
        selectArabic();

        // Step 3
        clickArabicLanguage();

        // Step 4
        selectEnglish();

        System.out.println(
                "Language flow completed successfully: " +
                        "English → Arabic → English"
        );
    }
}
