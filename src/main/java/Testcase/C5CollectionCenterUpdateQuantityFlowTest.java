package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.C5CollectionCenterUpdateQuantityFlow.ALoginPage;
import com.AndroidTest.C5CollectionCenterUpdateQuantityFlow.BPickupQuantityOrdersPage;
import org.testng.annotations.Test;

import static utilities.MobileUtilities.generatedPassword;

public class C5CollectionCenterUpdateQuantityFlowTest extends BaseClassMobile {

    @Test(priority = 7)
    public void collectionCenterQuantityUpdateTest() throws Exception {

        // TTFVaasan : 0500123001

        // Login
        ALoginPage loginPage = new ALoginPage();
        loginPage.login("0500123001",
                generatedPassword);


        // Pickup Quantity - Orders
        BPickupQuantityOrdersPage pickupQuantityOrdersPage =
                new BPickupQuantityOrdersPage();

        pickupQuantityOrdersPage.clickPickupQuantityOrders();

        ExtentTestListener.logStep("Collection Center Update Quantity 4th Flow Completed");
    }
}
