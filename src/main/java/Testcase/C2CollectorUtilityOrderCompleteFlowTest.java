package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.C2CollectorUtilityOrderCompleteFlow.*;
import org.testng.annotations.Test;

import static utilities.MobileUtilities.generatedPassword;

public class C2CollectorUtilityOrderCompleteFlowTest extends BaseClassMobile {

    @Test(priority = 4)
    public void collectorUtilityOrderCompleteFlow() throws Exception {

        //Launch SS Utility App
        driver.activateApp("com.smartsort.utilities");
        ExtentTestListener.logStep("SS Utility App Launched Successfully");

        //Login
        ALoginPage loginPage = new ALoginPage();
        loginPage.login("0500055447",
                              generatedPassword);

        ExtentTestListener.logStep("Collector Utility Login Successful");

        //Open Latest Request Order
        BRequestOrderROPage1 requestOrderPage = new BRequestOrderROPage1();
        requestOrderPage.openLatestRequestOrder();

        ExtentTestListener.logStep("Latest Request Order Opened Successfully");

        //Navigation Flow
        CNavigationPage navigationPage = new CNavigationPage();
        navigationPage.completeNavigation();

        ExtentTestListener.logStep("Navigation Flow Completed Successfully");

        //Pickup OTP Verification
        DPickupOTPVerificationPage pickupVerificationPage = new DPickupOTPVerificationPage();
        pickupVerificationPage.verifyPickup();

        ExtentTestListener.logStep("Pickup OTP Verification Completed Successfully");

        //Complete Order
        ERequestOrderROPage2 page = new ERequestOrderROPage2(driver);
        page.clickRequestOrders();
        page.clickSavedOrder();

        ExtentTestListener.logStep("Order Completion Completed Successfully");

        //Generate QR
        FGenerateQR generateQR = new FGenerateQR();
        generateQR.clickGenerateQR();

        ExtentTestListener.logStep("Generate QR  Completed Successfully");

        //Upload Packed Materials Image
        GUploadPackedMaterialsImagePage imageUploadPage = new GUploadPackedMaterialsImagePage();
        imageUploadPage.uploadImage();

        ExtentTestListener.logStep("Packed Materials Image Uploaded Successfully");

        //Assign Order to Collection Center
        HDeliveryOrderAssignToCollectionCenterPage deliveryOrderPage = new HDeliveryOrderAssignToCollectionCenterPage(driver);
        deliveryOrderPage.openRequestOrdersAndRODO();

        ExtentTestListener.logStep("Delivery Order Assignment Completed Successfully");

        //Save DO Number
        Thread.sleep(10000);
        ISaveDoNumberPage saveDoNumberPage = new ISaveDoNumberPage();
        saveDoNumberPage.clickDeliveryOrderAndSaveDONumber();

        ExtentTestListener.logStep("First Delivery Order Number Saved Successfully");

        //Logout
        JLogoutPage logoutPage = new JLogoutPage(driver);
        logoutPage.logout();

        ExtentTestListener.logStep("Collector Order 1st Flow Completed Successfully");
    }


}