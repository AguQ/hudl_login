package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tinylog.Logger;

import java.time.Duration;

import static data.Constants.CHECK_EMAIL_PATH;
import static data.Constants.DRIVER_WAIT_SECONDS;

public class CheckEmailPage extends BasePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "uni-form--default")
    private WebElement confirmationTextContainer;

    public CheckEmailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public boolean isHelpPageCurrent() {
        Logger.info("Verify if Check Email Page is current.");
        return wait.until(ExpectedConditions.urlContains(CHECK_EMAIL_PATH));
    }

    public boolean isConfirmationTextDisplayed() {
        Logger.info("Verify confirmation text is displayed.");
        return isElementDisplayed(wait, confirmationTextContainer);
    }
}