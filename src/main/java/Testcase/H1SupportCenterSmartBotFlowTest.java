package Testcase;

import Base.BaseClassMobile;
import Base.ExtentTestListener;
import com.AndroidTest.H1SupportCenterSmartBotFlow.AChatWithAI;
import com.AndroidTest.H1SupportCenterSmartBotFlow.BStartNewChat;
import com.AndroidTest.H1SupportCenterSmartBotFlow.CChatConversationPage;
import org.testng.annotations.Test;

public class H1SupportCenterSmartBotFlowTest extends BaseClassMobile {

    @Test
    public void chatWithAI() throws InterruptedException {

        ExtentTestListener.logStep("========== SMARTBOT TEST STARTED ==========");

        // 1. Scroll and click Chat Now
        Thread.sleep(3000);
        AChatWithAI chat = new AChatWithAI();
        chat.openAIChat();

        // 2. Open SmartBot and start new chat
        BStartNewChat smartBot = new BStartNewChat(driver);
        smartBot.openSmartBotAndStartChat();

        // 3. Ask question and wait for response
        CChatConversationPage chatPage = new CChatConversationPage(driver);
        chatPage.askQuestion("Where is my collector?");
        chatPage.closeSmartBot();

        ExtentTestListener.logStep("Question 1 Completed");
        ExtentTestListener.logStep("========== SMARTBOT TEST COMPLETED ==========");

        Thread.sleep(5000);
    }
}