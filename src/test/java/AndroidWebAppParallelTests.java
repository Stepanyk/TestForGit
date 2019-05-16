import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.net.URL;

/**
 * Created by henrrich on 2017-07-31.
 */
public class AndroidWebAppParallelTests {

//    private final static String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";

    private AppiumDriver driver;


    @BeforeTest(alwaysRun = true)
    @Parameters({"platform", "udid", "chromeDriverPort", "chromeDriverPath", "appiumurl"})
    public void setup(String platform, String udid, String chromeDriverPort, @Optional String chromeDriverPath, String appiumurl) throws Exception {
//        URL url = new URL(APPIUM_SERVER_URL);
        URL url = new URL(appiumurl);

        String[] platformInfo = platform.split(" ");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformInfo[0]);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformInfo[1]);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.UDID, udid);

        // systemPort is optional when testing android web app with driver "Appium"

        capabilities.setCapability("chromeDriverPort", chromeDriverPort);

        if (chromeDriverPath != null) {
            capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromeDriverPath);
        }

        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);

        driver = new AndroidDriver<MobileElement>(url, capabilities);
    }

    @Test
    public void testFindOwner() {
        launchHomePage();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("preply"));
    }

    @AfterTest(alwaysRun = true)
    public void teardown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    private BasePage launchHomePage() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getPageUrl());
        homePage.waitForUrlToBeLoaded();
        return homePage;
    }

}
