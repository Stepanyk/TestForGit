package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by henrrich on 2017-07-31.
 */
public class OwnersPage extends BasePage {

    private String lastName;

    @FindAll(
            @FindBy(xpath = "//table[@id='vets']//tbody//tr")
    )
    private List<WebElement> ownerList;

    public OwnersPage(AppiumDriver driver, String lastName) {
        super(driver);
        this.lastName = lastName;
    }

    public String getPageUrl() {
        return BASE_URL + "/owners?lastName=" + lastName;
    }

    public int getNumberOfOwners() {
        return ownerList.size();
    }

    public WebElement findOwnerWithName(String name) {
        for (WebElement owner : ownerList) {
            if (owner.findElement(By.tagName("a")).getText().equals(name)) {
                return owner;
            }
        }

        throw new RuntimeException("Owner named " + name + " is not found!");
    }

    public int getOwnerId(WebElement owner) {
        // e.g. href = "http://192.168.0.5:8080/owners/2"
        return Integer.parseInt(owner.findElement(By.tagName("a")).getAttribute("href").split("/")[4]);
    }

    public void clickOwnerLink(WebElement owner) {
        owner.findElement(By.tagName("a")).click();
    }
}
