package Testcase;

import Base.BaseClassMobile;
import com.AndroidTest.KSettingFlow.*;
import org.testng.annotations.Test;

public class KSettingFlowTest extends BaseClassMobile {

    @Test
    public void languageChangeEnglishToArabicToEnglish() throws InterruptedException {

        System.out.println("========== LANGUAGE FLOW STARTED ==========");

//        //
//        ALanguagePage languagePage = new ALanguagePage(driver);
//        languagePage.completeLanguageFlow();
//
//        //NOTIFICATION:
//        BNotificationPage notificationPage = new BNotificationPage(driver);
//        notificationPage.openAndValidateNotifications();
//
//        //
//        CThemePage themePage = new CThemePage(driver);
//        themePage.selectDarkTheme();
//        Thread.sleep(3000);
//        themePage.selectLightTheme();
//
//        //
//        DPrivacyPolicyPage privacyPolicyPage = new DPrivacyPolicyPage(driver);
//        privacyPolicyPage.clickPrivacyPolicyAndGoBack();
//
//        //
//        ETermsAndConditionsPage termsPage = new ETermsAndConditionsPage(driver);
//        termsPage.openValidateAndGoBack();
//
//        //
//        FAboutUsPage aboutUsPage = new FAboutUsPage(driver);
//        aboutUsPage.openAboutUsAndBack();

        //
        GReportsPage reportsPage = new GReportsPage(driver);
        reportsPage.clickReport();
        reportsPage.validateReportPage();
        reportsPage.openAndValidateReport();

        System.out.println("========== SETTING FLOW COMPLETED ==========");
    }
}