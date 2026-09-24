package Testcase;

import Base.BaseClassMobile;

import Base.ExtentTestListener;
import com.AndroidTest.C2CollectorUtilityOrderCompleteFlow.FGenerateQR;
import com.AndroidTest.N2CollectorUtilityOrderCompleteFlow.*;
import org.testng.annotations.Test;

import static utilities.MobileUtilities.generatedPassword;

public class N2CollectorUtilityOrderCompleteFlowTest extends BaseClassMobile {

    @Test
    public void collectorUtilityOrderCompleteFlow() throws Exception {

        //=========================================================
        // Launch SS Utility App
        //=========================================================
        driver.activateApp("com.smartsort.utilities");
        ExtentTestListener.logStep("Launch Utilities");

        // ==========================================================
        // 1. LOGIN
        // ==========================================================
        ALoginPage loginPage = new ALoginPage();
        loginPage.login("9043422841",
                generatedPassword);

        // ==========================================================
        // 2. OPEN SAVED REQUEST ORDER
        // ==========================================================
        Thread.sleep(7000);
        new BRequestOrderROPage1().openLatestRequestOrder();

        // ==========================================================
        // 3. START NAVIGATION → REACHED → DASHBOARD
        // ==========================================================
        new CNavigationPage().completeNavigation();

        // ==========================================================
        // 4. OPEN SAME REQUEST ORDER
        // ==========================================================
        new DRequestOrderROPage2().completeOrder();

        // =========================================================
        // Generate QR
        // =========================================================

        EGenerateQR generateQR = new EGenerateQR();
        generateQR.clickGenerateQR();

        ExtentTestListener.logStep("Generate QR Completed Successfully");

        // ==========================================================
        // 5. ENTER WEIGHT → SUBMIT → DASHBOARD
        // ==========================================================
        new FUploadPackedMaterialsImagePage().uploadImage();
    }
}