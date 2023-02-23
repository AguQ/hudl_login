package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tinylog.Logger;

import java.time.Duration;

import static data.Constants.DRIVER_WAIT_SECONDS;
import static data.Constants.HELP_PATH;

public class HelpPage extends BasePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@data-qa-id='password-reset-input']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@data-qa-id='password-reset-submit-btn']")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//p[@data-qa-id='still-trouble-copy']")
    private WebElement stillTroubleText;


    public HelpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public boolean isHelpPageCurrent() {
        Logger.info("Verify if Help Page is current.");
        return wait.until(ExpectedConditions.urlContains(HELP_PATH));
    }

    public boolean isCustomerSupportEmailDisplayed() {
        Logger.info("Verify customer support email is displayed.");
        try {
            wait.until(ExpectedConditions.visibilityOf(stillTroubleText));
            return stillTroubleText.findElement(By.tagName("a")).isDisplayed();
        } catch (TimeoutException te) {
            return false;
        }
    }

    public void resetPassword(String email) {
        Logger.info("Reset account Password.");
        emailInput.sendKeys(email);
        resetPasswordButton.click();
    }
}