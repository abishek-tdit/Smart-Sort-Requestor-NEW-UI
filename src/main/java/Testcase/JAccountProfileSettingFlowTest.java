package Testcase;

import Base.BaseClassMobile;
import com.AndroidTest.JAccountProfileSettingFlow.*;
import org.testng.annotations.Test;

public class JAccountProfileSettingFlowTest extends BaseClassMobile {

    @Test
    public void accountProfileSettingFlowTest() throws InterruptedException {

//        // =====================================================
//        // A - PHOTO UPLOAD
//        // =====================================================
//
//        APhotoUploadPage photoUploadPage =
//                new APhotoUploadPage(driver);
//
//        photoUploadPage.uploadPhoto();
//
//
//        // =====================================================
//        // B - QR CODE
//        // =====================================================
//
//        BQRCodePage qrCodePage =
//                new BQRCodePage(driver);
//
//        qrCodePage.openQRCodeAndGoBack();
//
//
//        // =====================================================
//        // C - LANGUAGE
//        // =====================================================
//
//        CLanguagePage languagePage =
//                new CLanguagePage(driver);
//
//        languagePage.changeArabicAndBackToEnglish();
//
//
//        // =====================================================
//        // D - NAME CHANGE
//        // =====================================================
//
//        DNameChangePage nameChangePage =
//                new DNameChangePage(driver);
//
//        nameChangePage.changeLastName();
//
//
//        // =====================================================
//        // E - EMAIL VALIDATION
//        // =====================================================
//
//        EEmailValidationPage emailPage =
//                new EEmailValidationPage(driver);
//
//        emailPage.validateEmail();
//
//
//        // =====================================================
//        // F - CHANGE PASSWORD
//        // =====================================================
//
//        FChangePasswordPage changePasswordPage = new FChangePasswordPage(driver);
//
//        changePasswordPage.clickAccount();
//
//        changePasswordPage.clickUserName();
//
//        // Admin@194 -> Admin@199
//        changePasswordPage.changePasswordToNewPassword();
//
//        // Admin@199 -> Admin@194
//        changePasswordPage.changePasswordBackToOldPassword();
//
//        //
//        GAddressPage addressPage = new GAddressPage(driver);
//        addressPage.addAndDeleteAddress();
//
//        //
//        HCheckForUpdatePage checkForUpdatePage = new HCheckForUpdatePage(driver);
//        checkForUpdatePage.checkForUpdate();

        //
        IFreeUpSpacePage freeUpSpacePage = new IFreeUpSpacePage(driver);
        freeUpSpacePage.completeFreeUpSpaceFlow();

        System.out.println("=======================================");
        System.out.println("ACCOUNT PROFILE SETTING FLOW COMPLETED");
        System.out.println("=======================================");
    }
}