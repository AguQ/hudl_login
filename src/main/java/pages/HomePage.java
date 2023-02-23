package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tinylog.Logger;

import java.time.Duration;

import static data.Constants.DRIVER_WAIT_SECONDS;
import static data.Constants.HOME_PATH;

public class HomePage extends BasePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "hui-globaluseritem__display-name")
    private WebElement userName;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public boolean isHomePageCurrent() {
        Logger.info("Verify if Home Page is current.");
        return wait.until(ExpectedConditions.urlContains(HOME_PATH));
    }
    public boolean isUserNameDisplayed() {
        Logger.info("Verify user name is displayed.");
        return isElementDisplayed(wait, userName);
    }

}