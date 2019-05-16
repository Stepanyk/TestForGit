package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by henrrich on 2017-07-31.
 */
public class MainNavigationBar extends BasePage {

    @FindBy(xpath = "//button[@data-target='#main-navbar']")
    private WebElement toggleButton;

    @FindBy(xpath = "//div[@id='main-navbar']//ul//li//a[@title='home page']")
    private WebElement homeButton;

    @FindBy(xpath = "//div[@id='main-navbar']//ul//li//a[@title='find owners']")
    private WebElement findOwnerButton;

    @FindBy(xpath = "//div[@id='main-navbar']//ul//li//a[@title='veterinarians']")
    private WebElement veterinariansButton;

    @FindBy(xpath = "//div[@id='main-navbar']//ul//li//a[@title='trigger a RuntimeException to see how it is handled']")
    private WebElement errorButton;

    public MainNavigationBar(AppiumDriver driver) {
        super(driver);
    }

    public String getPageUrl() {
        return null;
    }

    public boolean isNavigationMenuExpanded() {
        return Boolean.valueOf(toggleButton.getAttribute("aria-expanded"));
    }

    public void openNavigationMenu() {
        if (!isNavigationMenuExpanded()) {
            toggleButton.click();
            waitUntil(ExpectedConditions.attributeToBe(toggleButton, "aria-expanded", "true"));
        }
    }

    public void clickHomeButton() {
        homeButton.click();
    }

    public void clickFindOwnerButton() {
        findOwnerButton.click();
    }

    public void clickVeterinariansButton() {
        veterinariansButton.click();
    }

    public void clickErrorButton() {
        errorButton.click();
    }
}
