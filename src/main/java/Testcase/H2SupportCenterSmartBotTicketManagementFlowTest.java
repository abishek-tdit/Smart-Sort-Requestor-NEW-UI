package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.H2SupportCenterSmartBotTicketManagementFlow.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.EOrderDataTicketManagement;

public class H2SupportCenterSmartBotTicketManagementFlowTest
        extends BaseClassMobile {

    @Test
    public void supportCenterSmartBotTicketManagementFlow() {

        WebDriver crmDriver = null;

        try {

            // =====================================================
            // 1. CHAT WITH AI
            // =====================================================
            ExtentTestListener.logStep("Starting Chat With AI flow");

            AChatWithAI chatWithAI = new AChatWithAI();
            chatWithAI.startChatWithAI();

            ExtentTestListener.logStep("Chat With AI flow completed successfully");

            // =====================================================
            // 2. RAISE TICKET
            // =====================================================
            ExtentTestListener.logStep("Starting Raise Ticket flow");

            BRaiseTicketPage raiseTicketPage = new BRaiseTicketPage();
            raiseTicketPage.raiseTicket();

            ExtentTestListener.logStep("Raise Ticket flow completed successfully");

            // =====================================================
            // 3. GET TICKET ID
            // =====================================================
            String ticketID = EOrderDataTicketManagement.ticketIDNo;
            Assert.assertNotNull(ticketID, "Ticket ID is NULL");
            Assert.assertFalse(ticketID.trim().isEmpty(), "Ticket ID is EMPTY");

            System.out.println("==============================================");
            System.out.println("SAVED TICKET ID : " + ticketID);
            System.out.println("==============================================");

            // =====================================================
            // 4. FIRST LEVEL APPROVAL
            // =====================================================

            ExtentTestListener.logStep("Starting First Level Approval");

            CFirstLevelApprovalPage firstLevelApproval = new CFirstLevelApprovalPage();
            firstLevelApproval.loginExecutive(
                    "ALLSOELEVELTWO",
                    "Soeleveltwo@123");

            ExtentTestListener.logStep("First Level Approval completed : " + ticketID);

            // =====================================================
            // 5. CRM LOGIN
            // =====================================================
            ExtentTestListener.logStep("Starting CRM Login");

            DCRMLoginPage crmLogin = new DCRMLoginPage();
            crmLogin.loginCRM("Admin",
                    "Admin@123");

            System.out.println("CRM Login completed successfully");

            ExtentTestListener.logStep("CRM Login completed successfully");


            // =====================================================
            // 6. GET SAME CRM DRIVER
            // =====================================================
            crmDriver = crmLogin.getDriver();
            Assert.assertNotNull(crmDriver, "CRM Driver is NULL");

            System.out.println("Using SAME CRM browser after login");


            // =====================================================
            // 7. SECOND LEVEL APPROVAL
            // =====================================================

            ExtentTestListener.logStep("Starting Second Level Approval");

            ESecondLevelApprovalPage secondLevelApproval = new ESecondLevelApprovalPage(crmDriver);
            secondLevelApproval.openTicketAssignment();

            ExtentTestListener.logStep("Ticket Assignment opened successfully");

            // =====================================================
            // 8. CHANGE TICKET TO IN PROGRESS
            // =====================================================
            ExtentTestListener.logStep("Starting Change Ticket To InProgress");

            FChangeToInProgress changeToInProgress = new FChangeToInProgress( crmLogin.getDriver() );
            changeToInProgress.changeTicketToInProgress();

            ExtentTestListener.logStep("Ticket changed to InProgress successfully : " + ticketID);

            // =====================================================
            // 9. CHANGE TICKET TO COMPLETED
            // =====================================================

            ExtentTestListener.logStep("Starting Change Ticket From InProgress To Completed");

            GChangeToCompleted changeToCompleted = new GChangeToCompleted(crmDriver);
            changeToCompleted.changeTicketToCompleted();

            ExtentTestListener.logStep("Ticket changed to Completed successfully : " + ticketID);

            // =====================================================
            // 10. CLOSE CRM AND RETURN TO MOBILE APP
            // =====================================================
            ExtentTestListener.logStep("CRM ticket completed. Returning to Mobile App");

            System.out.println("==============================================");
            System.out.println("CRM TICKET COMPLETED SUCCESSFULLY");
            System.out.println("Closing CRM Browser...");

            if (crmDriver != null) {
                crmDriver.quit();
                crmDriver = null;

                System.out.println("CRM Browser Closed Successfully");
            }

            System.out.println("==============================================");
            System.out.println("Returning to Mobile App...");
            System.out.println("==============================================");

            ExtentTestListener.logStep("Returned to Mobile App successfully");

            // =====================================================
            // 11. TICKET HISTORY VERIFICATION - MOBILE
            // =====================================================
            ExtentTestListener.logStep("Starting Ticket History Verification in Mobile App");

            System.out.println("==============================================");
            System.out.println("Starting Ticket History Verification");
            System.out.println("Ticket ID : " + ticketID);
            System.out.println("==============================================");

            HTicketHistoryVerificationPage ticketHistoryVerification =
                    new HTicketHistoryVerificationPage();

            ticketHistoryVerification.openSavedTicket();

            ExtentTestListener.logStep("Ticket History Verification completed successfully : " + ticketID);

            System.out.println("Saved Ticket opened successfully in Mobile App : " + ticketID);

            // =====================================================
            // FINAL RESULT
            // =====================================================

            System.out.println("==============================================");
            System.out.println("TICKET MANAGEMENT FLOW COMPLETED");
            System.out.println("==============================================");
            System.out.println("Ticket ID : " + ticketID);
            System.out.println("1st Level : APPROVED");
            System.out.println("CRM Login : SUCCESS");
            System.out.println("Ticket Assignment : OPENED");
            System.out.println("Ticket Status : InProgress -> Completed");
            System.out.println("Completion Reason : abi");
            System.out.println("CRM Browser : CLOSED");
            System.out.println("Returned To Mobile : SUCCESS");
            System.out.println("Ticket History : VERIFIED");
            System.out.println("Saved Ticket : OPENED SUCCESSFULLY");
            System.out.println("==============================================");
        }
        catch (Exception e)
        {
            ExtentTestListener.logStep("Ticket Management Flow Failed : " + e.getMessage());
            System.out.println("==============================================");
            System.out.println("TICKET MANAGEMENT FLOW FAILED");
            System.out.println("ERROR : " + e.getMessage());
            System.out.println("==============================================");
            throw e;
        }
        finally
        {
            // =====================================================
            // CLOSE CRM BROWSER IF STILL OPEN
            // =====================================================
            if (crmDriver != null) {
                try {
                    crmDriver.quit();
                    System.out.println("CRM Browser Closed Successfully in Finally");
                }
                catch (Exception e)
                {
                    System.out.println("CRM Browser was already closed");
                }
            }
        }
    }
}
