import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

/**
 * Created by henrrich on 26/06/17.
 */
public class IOSNativeParallelTests {

    private final static String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";

    private IOSDriver driver;

    @BeforeTest(alwaysRun = true)
    @Parameters({"platform", "udid", "deviceName", "wdaLocalPort"})
    public void setup(String platform, String udid, String deviceName, String wdaLocalPort) throws Exception {

        URL url = new URL(APPIUM_SERVER_URL);

        String[] platformInfo = platform.split(" ");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformInfo[0]);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformInfo[1]);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability("wdaLocalPort", wdaLocalPort);
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/henrrich/Documents/work/jsta/appium/demo-apps/TaskApplication.app");
        capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);

        driver = new IOSDriver<MobileElement>(url, capabilities);
    }

    @Test
    public void testAddAndDeleteTask() {

        MobileElement tasksTab = (MobileElement) driver.findElementById("Tasks");
        MobileElement addTaskTab = (MobileElement) driver.findElementById("Add Tasks");

        addTaskTab.click();

        MobileElement taskNameInput = (MobileElement) driver.findElementById("Task Name");
        MobileElement taskDescriptionInput = (MobileElement) driver.findElementById("Task Description");
        MobileElement saveButton = (MobileElement) driver.findElementById("Save");

        enterText(taskNameInput, "Task1");
        enterText(taskDescriptionInput, "description1");
        saveButton.click();

        enterText(taskNameInput, "Task2");
        enterText(taskDescriptionInput, "description2");
        saveButton.click();

        enterText(taskNameInput, "Task3");
        enterText(taskDescriptionInput, "description3");
        saveButton.click();

        tasksTab.click();

        List<MobileElement> taskList = driver.findElementsByClassName("XCUIElementTypeCell");

        Assert.assertTrue(taskList.size() == 3);

        Assert.assertEquals("Task1", getTaskNameAtIndex(taskList, 0));
        Assert.assertEquals("description1", getTaskDescriptionAtIndex(taskList,0));

        Assert.assertEquals("Task2", getTaskNameAtIndex(taskList, 1));
        Assert.assertEquals("description2", getTaskDescriptionAtIndex(taskList, 1));

        Assert.assertEquals("Task3", getTaskNameAtIndex(taskList, 2));
        Assert.assertEquals("description3", getTaskDescriptionAtIndex(taskList, 2));
    }

    private void enterText(MobileElement input, String name) {
        input.clear();
        input.sendKeys(name);
    }

    public String getTaskNameAtIndex(List<MobileElement> taskList, int index) {
        return taskList.get(index).findElementsByClassName("XCUIElementTypeStaticText").get(0).getText();
    }

    public String getTaskDescriptionAtIndex(List<MobileElement> taskList, int index) {
        return taskList.get(index).findElementsByClassName("XCUIElementTypeStaticText").get(1).getText();
    }

    @AfterTest(alwaysRun = true)
    public void teardown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

}
