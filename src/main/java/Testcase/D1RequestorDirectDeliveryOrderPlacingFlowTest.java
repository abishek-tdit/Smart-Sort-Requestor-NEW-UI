package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.D1RequestorDirectDeliveryOrderPlacingFlow.ALocationPage;
import com.AndroidTest.D1RequestorDirectDeliveryOrderPlacingFlow.BDirectDeliveryPage;
import com.AndroidTest.D1RequestorDirectDeliveryOrderPlacingFlow.CScheduleDatePage;
import org.testng.annotations.Test;

public class D1RequestorDirectDeliveryOrderPlacingFlowTest
        extends BaseClassMobile {

    @Test(priority = 8)
    public void smartSortDirectDeliveryOrderFlow() throws Exception {

        ExtentTestListener.logStep("Smart Sort Direct Delivery Order Flow Started");
        //=========================================================
        // Launch SmartSort App
        //=========================================================
        driver.activateApp("com.abqaiq.smartsort");
        ExtentTestListener.logStep("Smart Sort App Launched Successfully");
        Thread.sleep(6000);

        //Location
        ALocationPage locationPage = new ALocationPage(driver);
        locationPage.selectLocation();

        ExtentTestListener.logStep("Location Selected Successfully");

        // Direct Delivery
        BDirectDeliveryPage directDeliveryPage = new BDirectDeliveryPage();
        directDeliveryPage.selectDirectDelivery();

        ExtentTestListener.logStep("Direct Delivery Selected Successfully");

        //Schedule Date & Time
        CScheduleDatePage scheduleDatePage = new CScheduleDatePage();

        scheduleDatePage.clickNextHour();
        scheduleDatePage.clickConfirm();
        scheduleDatePage.clickOK();

        ExtentTestListener.logStep("Direct Delivery Order Created Successfully");

        ExtentTestListener.logStep("Smart Sort Direct Delivery Order Flow Completed Successfully");
    }
}