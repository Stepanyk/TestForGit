package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by henrrich on 2017-07-31.
 */
public class FindOwnerPage extends BasePage {

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement findOwnerButton;

    public FindOwnerPage(AppiumDriver driver) {
        super(driver);
    }

    public String getPageUrl() {
        return BASE_URL + "/owners/find";
    }

    public void enterLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void clickFindOwnerButton() {
        findOwnerButton.click();
    }
}
