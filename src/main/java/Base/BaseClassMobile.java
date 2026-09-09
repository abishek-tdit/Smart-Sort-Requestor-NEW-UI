package Base;

import com.aventstack.extentreports.ExtentReports;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;

public class BaseClassMobile {

    public static AndroidDriver driver;
    public static WebDriverWait wait;
    protected static ExtentReports extent;

    public static final int WAIT_TIME = 10;

    // =========================================================
    // Launch Application
    // =========================================================

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {

        ExtentTestListener.logStep(
                "========== MOBILE EXECUTION STARTED ==========");

        // Do not create another session if already running
        if (driver != null) {
            ExtentTestListener.logStep(
                    "Existing Mobile Session Reused");
            return;
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Platform
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability(
                "deviceName",
                "HA69Z5PJ8PROEYHA");

        // Application
        capabilities.setCapability(
                "appPackage",
                "com.abqaiq.smartsort");

        capabilities.setCapability(
                "appActivity",
                "com.abqaiq.smartsort.MainActivity");

        // Appium Settings
        capabilities.setCapability("noReset", true);
        capabilities.setCapability(
                "autoGrantPermissions",
                true);

        capabilities.setCapability(
                "ignoreHiddenApiPolicyError",
                true);

        capabilities.setCapability(
                "newCommandTimeout",
                300);

        capabilities.setCapability(
                "adbExecTimeout",
                120000);

        capabilities.setCapability(
                "uiautomator2ServerInstallTimeout",
                120000);

        capabilities.setCapability(
                "uiautomator2ServerLaunchTimeout",
                120000);

        // Create Driver
        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                capabilities);

        // Global Wait
        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(WAIT_TIME));

        extent = ExtentManager.getExtent();

        ExtentTestListener.logStep(
                "Mobile Application Launched Successfully");
    }

    // =========================================================
    // Get Driver
    // =========================================================

    public AndroidDriver getDriver() {
        return driver;
    }

    // =========================================================
    // Get Wait
    // =========================================================

    public WebDriverWait getWait() {
        return wait;
    }

    // =========================================================
    // Screenshot
    // =========================================================

    public static String takeScreenshot(String testName) {

        String folder =
                System.getProperty("user.dir")
                        + File.separator
                        + "screenshots";

        File directory = new File(folder);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName =
                testName
                        + "_"
                        + System.currentTimeMillis()
                        + ".png";

        String destinationPath =
                folder
                        + File.separator
                        + fileName;

        try {

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            File destination =
                    new File(destinationPath);

            FileUtils.copyFile(source, destination);

            ExtentTestListener.logStep(
                    "Screenshot Captured");

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Unable to Capture Screenshot : "
                            + e.getMessage());
        }

        return destinationPath;
    }

    // =========================================================
    // Scroll Down
    // =========================================================

    public void scrollDown() {

        Dimension size =
                driver.manage()
                        .window()
                        .getSize();

        int x = size.width / 2;

        int startY =
                (int) (size.height * 0.75);

        int endY =
                (int) (size.height * 0.35);

        PointerInput finger =
                new PointerInput(
                        PointerInput.Kind.TOUCH,
                        "finger");

        Sequence swipe =
                new Sequence(finger, 1);

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        x,
                        startY));

        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(500),
                        PointerInput.Origin.viewport(),
                        x,
                        endY));

        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(
                Collections.singletonList(swipe));
    }

    // =========================================================
    // Scroll Up
    // =========================================================

    public void scrollUp() {

        Dimension size =
                driver.manage()
                        .window()
                        .getSize();

        driver.executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", 0,
                        "top", 0,
                        "width", size.width,
                        "height", size.height,
                        "direction", "up",
                        "percent", 0.80
                ));
    }

    // =========================================================
    // Wait Until Visible
    // =========================================================

    public WebElement waitUntilVisible(
            By locator,
            int seconds) {

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(seconds))
                .until(
                        ExpectedConditions
                                .visibilityOfElementLocated(locator));
    }

    // =========================================================
    // Wait Until Clickable
    // =========================================================

    public WebElement waitUntilClickable(
            By locator,
            int seconds) {

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(seconds))
                .until(
                        ExpectedConditions
                                .elementToBeClickable(locator));
    }

    // =========================================================
    // Restart Session
    // =========================================================

    public static void restartSession() {

        try {

            System.out.println(
                    "Restarting Appium Session");

            if (driver != null) {

                driver.quit();
                driver = null;
            }

            DesiredCapabilities cap =
                    new DesiredCapabilities();

            cap.setCapability(
                    "platformName",
                    "Android");

            cap.setCapability(
                    "automationName",
                    "UiAutomator2");

            cap.setCapability(
                    "deviceName",
                    "HA69Z5PJ8PROEYHA");

            // Same application as setUp()
            cap.setCapability(
                    "appPackage",
                    "com.abqaiq.smartsort");

            cap.setCapability(
                    "appActivity",
                    "com.abqaiq.smartsort.MainActivity");

            cap.setCapability(
                    "autoLaunch",
                    true);

            cap.setCapability(
                    "noReset",
                    true);

            cap.setCapability(
                    "newCommandTimeout",
                    300);

            driver = new AndroidDriver(
                    new URL(
                            "http://127.0.0.1:4723"),
                    cap);

            wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(WAIT_TIME));

            System.out.println(
                    "Session Restarted");

        } catch (Exception e) {

            System.out.println(
                    "Unable to Restart Session : "
                            + e.getMessage());

            e.printStackTrace();
        }
    }

    // =========================================================
    // Close Application
    // =========================================================

    @AfterSuite(alwaysRun = true)
    public void tearDown() {

        try {

            if (driver != null) {

                driver.quit();

                driver = null;
                wait = null;

                ExtentTestListener.logStep(
                        "Application Closed Successfully");
            }

            if (extent != null) {
                extent.flush();
            }

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "Error While Closing Driver : "
                            + e.getMessage());
        }
    }
}