package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.ERequestorRedeemFlow.*;
import org.testng.annotations.Test;

public class ERequestorRedeemFlowTest extends BaseClassMobile {

    @Test(priority = 10)
    public void requestorRedeemFlow() throws Exception {

        //=========================================================
        // Launch SmartSort App
        //=========================================================
        ExtentTestListener.logStep("Start Redeem Flow");

        driver.activateApp("com.abqaiq.smartsort");
        Thread.sleep(8000);

        //Select Location A1:
        A1LocationPage locationPage1 = new A1LocationPage(driver);
        locationPage1.selectLocation();
        ExtentTestListener.logStep("STEP 1 COMPLETED : Location Selected");

        //Click Redeem Now  A2:
        Thread.sleep(5000);
        A2RedeemNow redeemNow1 = new A2RedeemNow(driver);
        redeemNow1.clickRedeemExcitingRewards();
        ExtentTestListener.logStep("STEP 2 COMPLETED : Redeem Now Clicked");

        //Redeem Partner Store A3:
        A3RedeemPartnerStore redeemPartnerStore = new A3RedeemPartnerStore();
        redeemPartnerStore.proceedRedeem();
        ExtentTestListener.logStep("STEP 3 COMPLETED : Redeem Partner Store");

        Thread.sleep(5000);

        //Select Location B1:
        B1LocationPage locationPage2 = new B1LocationPage(driver);
        locationPage2.selectLocation();
        ExtentTestListener.logStep("STEP 4 COMPLETED : Location Selected");

        //Click Redeem Now  B2:
        Thread.sleep(5000);
        B2RedeemNow redeemNow2 = new B2RedeemNow(driver);

        redeemNow2.clickRedeemExcitingRewards();
        ExtentTestListener.logStep("STEP 5 COMPLETED : Redeem Now Clicked");

        //Redeem Voucher  B3:
        B3RedeemVouchers redeemVouchers = new B3RedeemVouchers();
        redeemVouchers.redeemVouchers();
        ExtentTestListener.logStep("STEP 6 COMPLETED : Redeem Voucher Clicked");
        Thread.sleep(5000);

        //Select Location C1:
        C1LocationPage locationPage3 = new C1LocationPage(driver);
        locationPage3.selectLocation();
        ExtentTestListener.logStep("STEP 7 COMPLETED : Location Selected");

        //Click Redeem Now  C2:
        Thread.sleep(5000);
        C2RedeemNow redeemNow3 = new C2RedeemNow(driver);

        redeemNow3.clickRedeemExcitingRewards();
        ExtentTestListener.logStep("STEP 8 COMPLETED : Redeem Now Clicked");

        //Redeem Voucher  C3:
        C3RedeemConvertToCash redeemVouchers3 = new C3RedeemConvertToCash();
        redeemVouchers3.convertToCash();
        ExtentTestListener.logStep("STEP 9 COMPLETED : Redeem Convert To Cash Completed");

        ExtentTestListener.logStep("========== E-REQUESTOR REDEEM FLOW COMPLETED ==========");
    }
}