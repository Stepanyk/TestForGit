import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by henrrich on 26/06/17.
 */
public class AndroidNativeParallelTests {

    private final static String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";

    private static final String LOGIN_SUCCESS_MESSAGE = "You are logged on as admin";
    private static final String CORRECT_USER_NAME = "admin";
    private static final String CORRECT_PASSWORD = "password";

    private AndroidDriver driver;

    @BeforeTest(alwaysRun = true)
    @Parameters({"platform", "udid", "systemPort"})
    public void setup(String platform, String udid, String systemPort) throws Exception {

        URL url = new URL(APPIUM_SERVER_URL);

        String[] platformInfo = platform.split(" ");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformInfo[0]);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformInfo[1]);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/henrrich/Documents/work/jsta/appium/demo-apps/demo.apk");
        capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);

        driver = new AndroidDriver<MobileElement>(url, capabilities);

        // Setup implicit wait to 10 seconds for waiting mobile elements.
        // Use a higher value if your mobile elements take time to show up
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testLoginAndLogout() throws InterruptedException {
        navigateToCategory("drawer_login_page");

        MobileElement usernameInput = (MobileElement) driver.findElementByAccessibilityId("Username Input Field");
        MobileElement passwordInput = (MobileElement) driver.findElementByAccessibilityId("Password Input Field");
        MobileElement loginButton = (MobileElement) driver.findElementByAccessibilityId("Login Button");

        usernameInput.clear();
        usernameInput.sendKeys(CORRECT_USER_NAME);

        passwordInput.click();
        passwordInput.sendKeys(CORRECT_PASSWORD);

        loginButton.click();

        Assert.assertEquals(getMessage(), LOGIN_SUCCESS_MESSAGE);

        driver.findElementByAccessibilityId("Alt Button").click();
        Assert.assertTrue(loginButton.isDisplayed() && usernameInput.isDisplayed() && passwordInput.isDisplayed());
    }

    private String getMessage() {
        return driver.findElementByAccessibilityId("Alt Message").getText();
    }

    @AfterTest(alwaysRun = true)
    public void teardown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    private void navigateToCategory(String categoryName) throws InterruptedException {
        MobileElement toggle = (MobileElement) driver.findElementByAccessibilityId("ReferenceApp");
        toggle.click();

        MobileElement categoryItem = (MobileElement) driver.findElementById(categoryName);
        categoryItem.click();

        TimeUnit.SECONDS.sleep(1);
    }

}

