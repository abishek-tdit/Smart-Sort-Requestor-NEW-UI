package com.AndroidTest.D2CollectionCenterDirectDeliveryOrderFlow;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.StepTimer;

import java.util.List;

public class ADirectQuantityOrdersPage extends BaseClassMobile {

    // Direct Quantity Orders Menu
    By directQuantityOrders = AppiumBy.androidUIAutomator(
            "new UiSelector().descriptionContains(\"Direct Quantity Orders\")");

    // All Orders
    By allOrders = By.xpath(
            "//android.view.View[contains(@content-desc,'Delivery:')]");

    // Proceed To Complete
    By proceedToComplete = AppiumBy.accessibilityId("Proceed to complete");

    // Proceed Order
    By proceedOrder = AppiumBy.accessibilityId("Proceed Order");

    // Aluminium KG
    By aluminiumKg = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.EditText\").instance(2)");

    // =========================================================
    // Click Direct Quantity Orders
    // =========================================================
    public void clickDirectQuantityOrders() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking Direct Quantity Orders...");

            WebElement orderMenu = wait.until(ExpectedConditions.elementToBeClickable(directQuantityOrders));

            orderMenu.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Direct Quantity Orders Clicked Successfully"
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("Failed to Click Direct Quantity Orders : "
                            + e.getMessage());

            throw new RuntimeException(e);
        }
    }

    // =========================================================
    // Click First (Most Recent) Order
    // =========================================================
    public void clickRecentOrder() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Finding Recent Order...");

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allOrders));

            List<WebElement> orders = driver.findElements(allOrders);

            if (orders.isEmpty()) {
                throw new RuntimeException("No Orders Found");
            }

            // First order = Most Recent
            WebElement recentOrder = orders.get(0);

            String orderName = recentOrder.getAttribute("content-desc");

            ExtentTestListener.logStep("Recent Order Selected : " + orderName);

            recentOrder.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Recent Order Clicked Successfully"
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("Failed to Click Recent Order : " + e.getMessage());

            throw new RuntimeException(e);
        }
    }

    // =========================================================
    // Click Proceed To Complete
    // =========================================================
    public void clickProceedToComplete() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking Proceed To Complete...");

            WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(proceedToComplete));

            proceedBtn.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Proceed To Complete Clicked Successfully"
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("Failed To Click Proceed To Complete : " + e.getMessage());

            throw new RuntimeException(e);
        }
    }

    // =========================================================
    // Click Proceed Order
    // =========================================================
    public void clickProceedOrder() {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Clicking Proceed Order...");

            WebElement proceedOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(proceedOrder));

            proceedOrderBtn.click();

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Proceed Order Clicked Successfully"
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        }
        catch (Exception e)
        {

            ExtentTestListener.logStep("Failed To Click Proceed Order : " + e.getMessage());

            throw new RuntimeException(e);
        }
    }

    // =========================================================
    // Enter Aluminium KG
    // =========================================================
    public void enterAluminiumKg(String kgValue) {

        try {

            StepTimer.startStep();

            ExtentTestListener.logStep("Entering Aluminium KG : " + kgValue);

            WebElement aluminiumField =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(aluminiumKg));

            aluminiumField.click();
            aluminiumField.clear();
            aluminiumField.sendKeys(kgValue);

            ExtentTestListener.logStep("Aluminium KG Entered Successfully");

            // Hide Keyboard
            try {
                driver.hideKeyboard();
            }
            catch (Exception e)
            {
                ExtentTestListener.logStep("Keyboard not visible");
            }

            double duration = StepTimer.endStep();

            ExtentTestListener.logStep("Aluminium KG Entry Completed"
                            + " | Time Taken : "
                            + String.format("%.2f", duration)
                            + " sec");

        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("Failed To Enter Aluminium KG : " + e.getMessage());

            throw new RuntimeException(e);
        }
    }
}