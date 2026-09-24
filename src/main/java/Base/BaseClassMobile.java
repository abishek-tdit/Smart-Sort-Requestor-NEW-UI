package Base;

import com.aventstack.extentreports.ExtentReports;
import io.appium.java_client.AppiumBy;
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
    // App Packages
    // =========================================================

    public static final String REQUESTOR_PACKAGE =
            "com.abqaiq.smartsort";

    public static final String UTILITY_PACKAGE =
            "com.smartsort.utilities";

    // =========================================================
    // Device
    // =========================================================

    private static final String DEVICE_NAME =
            "HA69Z5PJ8PROEYHA";

    private static final String APPIUM_SERVER =
            "http://127.0.0.1:4723";

    // =========================================================
    // Launch Requestor Application
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

        DesiredCapabilities capabilities =
                new DesiredCapabilities();

        // ---------------------------------------------------------
        // Platform
        // ---------------------------------------------------------

        capabilities.setCapability(
                "platformName",
                "Android");

        capabilities.setCapability(
                "automationName",
                "UiAutomator2");

        capabilities.setCapability(
                "deviceName",
                DEVICE_NAME);

        capabilities.setCapability(
                "udid",
                DEVICE_NAME);

        // ---------------------------------------------------------
        // Requestor Application
        // ---------------------------------------------------------

        capabilities.setCapability(
                "appPackage",
                REQUESTOR_PACKAGE);

        capabilities.setCapability(
                "appActivity",
                "com.abqaiq.smartsort.MainActivity");

        // ---------------------------------------------------------
        // Appium Settings
        // ---------------------------------------------------------

        capabilities.setCapability(
                "noReset",
                true);

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

        // ---------------------------------------------------------
        // Create Driver
        // ---------------------------------------------------------

        driver = new AndroidDriver(
                new URL(APPIUM_SERVER),
                capabilities);

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

        File directory =
                new File(folder);

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
                            .getScreenshotAs(
                                    OutputType.FILE);

            File destination =
                    new File(destinationPath);

            FileUtils.copyFile(
                    source,
                    destination);

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

        int x =
                size.width / 2;

        int startY =
                (int) (size.height * 0.75);

        int endY =
                (int) (size.height * 0.35);

        PointerInput finger =
                new PointerInput(
                        PointerInput.Kind.TOUCH,
                        "finger");

        Sequence swipe =
                new Sequence(
                        finger,
                        1);

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
                        "left",
                        0,
                        "top",
                        0,
                        "width",
                        size.width,
                        "height",
                        size.height,
                        "direction",
                        "up",
                        "percent",
                        0.80
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
                                .visibilityOfElementLocated(
                                        locator));
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
                                .elementToBeClickable(
                                        locator));
    }

    // =========================================================
    // Restart Requestor Session
    //
    // Keep this method for flows that use Requestor.
    // =========================================================

    public static void restartSession() {

        try {

            System.out.println(
                    "Restarting Requestor Appium Session");

            if (driver != null) {

                try {
                    driver.quit();
                } catch (Exception ignored) {
                }

                driver = null;
                wait = null;
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
                    DEVICE_NAME);

            cap.setCapability(
                    "udid",
                    DEVICE_NAME);

            cap.setCapability(
                    "appPackage",
                    REQUESTOR_PACKAGE);

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
                    "autoGrantPermissions",
                    true);

            cap.setCapability(
                    "ignoreHiddenApiPolicyError",
                    true);

            cap.setCapability(
                    "newCommandTimeout",
                    300);

            cap.setCapability(
                    "adbExecTimeout",
                    120000);

            cap.setCapability(
                    "uiautomator2ServerInstallTimeout",
                    120000);

            cap.setCapability(
                    "uiautomator2ServerLaunchTimeout",
                    120000);

            driver =
                    new AndroidDriver(
                            new URL(APPIUM_SERVER),
                            cap);

            wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(WAIT_TIME));

            System.out.println(
                    "Requestor Session Restarted");

        } catch (Exception e) {

            System.out.println(
                    "Unable to Restart Requestor Session : "
                            + e.getMessage());

            throw new RuntimeException(
                    "Requestor Appium session restart failed",
                    e);
        }
    }

    // =========================================================
    // Restart SS Utility Session
    //
    // IMPORTANT:
    // This method does NOT use the Requestor appPackage.
    // It creates a fresh Android session and then activates
    // the Utility application by package name.
    // =========================================================

    public static void restartUtilitySession() {

        try {

            System.out.println(
                    "Restarting SS Utility Appium Session");

            ExtentTestListener.logStep(
                    "Restarting SS Utility Appium Session");

            // -------------------------------------------------
            // Close old crashed session
            // -------------------------------------------------

            if (driver != null) {

                try {

                    driver.quit();

                } catch (Exception ignored) {

                    System.out.println(
                            "Old Appium session already unavailable");
                }

                driver = null;
                wait = null;
            }

            // -------------------------------------------------
            // Create fresh UiAutomator2 session
            //
            // No appPackage/appActivity here.
            // This prevents Requestor from being launched.
            // -------------------------------------------------

            DesiredCapabilities utilityCapabilities =
                    new DesiredCapabilities();

            utilityCapabilities.setCapability(
                    "platformName",
                    "Android");

            utilityCapabilities.setCapability(
                    "automationName",
                    "UiAutomator2");

            utilityCapabilities.setCapability(
                    "deviceName",
                    DEVICE_NAME);

            utilityCapabilities.setCapability(
                    "udid",
                    DEVICE_NAME);

            utilityCapabilities.setCapability(
                    "noReset",
                    true);

            utilityCapabilities.setCapability(
                    "autoGrantPermissions",
                    true);

            utilityCapabilities.setCapability(
                    "ignoreHiddenApiPolicyError",
                    true);

            utilityCapabilities.setCapability(
                    "newCommandTimeout",
                    1800);

            utilityCapabilities.setCapability(
                    "adbExecTimeout",
                    180000);

            utilityCapabilities.setCapability(
                    "uiautomator2ServerInstallTimeout",
                    180000);

            utilityCapabilities.setCapability(
                    "uiautomator2ServerLaunchTimeout",
                    180000);

            utilityCapabilities.setCapability(
                    "disableWindowAnimation",
                    true);

            utilityCapabilities.setCapability(
                    "dontStopAppOnReset",
                    true);

            // -------------------------------------------------
            // Create new AndroidDriver
            // -------------------------------------------------

            driver =
                    new AndroidDriver(
                            new URL(APPIUM_SERVER),
                            utilityCapabilities);

            wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(WAIT_TIME));

            ExtentTestListener.logStep(
                    "Fresh UiAutomator2 Session Created");

            // -------------------------------------------------
            // IMPORTANT:
            // Activate SS Utility explicitly
            // -------------------------------------------------

            driver.activateApp(
                    UTILITY_PACKAGE);

            ExtentTestListener.logStep(
                    "SS Utility App Activated After Restart");

            // -------------------------------------------------
            // Verify correct application
            // -------------------------------------------------

            String currentPackage =
                    driver.getCurrentPackage();

            ExtentTestListener.logStep(
                    "Package After Utility Restart = "
                            + currentPackage);

            if (!UTILITY_PACKAGE.equals(
                    currentPackage)) {

                throw new RuntimeException(
                        "Wrong application after Utility restart. "
                                + "Expected: "
                                + UTILITY_PACKAGE
                                + " | Actual: "
                                + currentPackage);
            }

            ExtentTestListener.logStep(
                    "SS Utility Appium Session Restarted Successfully");

        } catch (Exception e) {

            ExtentTestListener.logStep(
                    "SS Utility Appium Session Restart Failed : "
                            + e.getMessage());

            throw new RuntimeException(
                    "Unable to restart SS Utility Appium session",
                    e);
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