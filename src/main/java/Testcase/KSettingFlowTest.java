package Testcase;

import Base.BaseClassMobile;
import com.AndroidTest.KSettingFlow.*;
import org.testng.annotations.Test;

public class KSettingFlowTest extends BaseClassMobile {

    @Test
    public void languageChangeEnglishToArabicToEnglish() throws InterruptedException {

        System.out.println("========== LANGUAGE FLOW STARTED ==========");

        //Language
        ALanguagePage languagePage = new ALanguagePage(driver);
        languagePage.completeLanguageFlow();

        //NOTIFICATION:
        BNotificationPage notificationPage = new BNotificationPage(driver);
        notificationPage.validateNotificationsFlow();
        driver.navigate().back();


        //Theme
        CThemePage themePage = new CThemePage(driver);
        themePage.selectDarkTheme();
        Thread.sleep(3000);
        themePage.selectLightTheme();

        //PrivacyPolicy
        DPrivacyPolicyPage privacyPolicyPage = new DPrivacyPolicyPage(driver);
        privacyPolicyPage.clickPrivacyPolicyAndGoBack();

        //TermsAndConditions
        ETermsAndConditionsPage termsPage = new ETermsAndConditionsPage(driver);
        termsPage.openValidateAndGoBack();

        //AboutUs
        FAboutUsPage aboutUsPage = new FAboutUsPage(driver);
        aboutUsPage.openAboutUsAndBack();

        //Reports
        GReportsPage reportsPage = new GReportsPage();
        reportsPage.clickReport();
        reportsPage.clickWeekly();
        reportsPage.clickMonthly();
        reportsPage.clickRange();
        driver.navigate().back();
//      ========================
        driver.navigate().back();

        System.out.println("========== SETTING FLOW COMPLETED ==========");
    }
}