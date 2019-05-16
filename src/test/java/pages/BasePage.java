package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by henrrich on 2017-07-31.
 */
abstract public class BasePage {

    public static final String BASE_URL = "https://stage0.preply.org";

    protected AppiumDriver driver;

    protected BasePage(AppiumDriver driver){
        this.driver = driver;
    }

    abstract public String getPageUrl();

    public void waitUntil(ExpectedCondition conditionToBe) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(conditionToBe);
    }

    public void waitForUrlToBeLoaded() {
        waitUntil(ExpectedConditions.urlToBe(getPageUrl()));
    }
}

