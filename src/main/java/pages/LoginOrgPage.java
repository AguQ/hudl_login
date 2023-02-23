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
import static data.Constants.LOGIN_WITH_ORG_PATH;

public class LoginOrgPage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "uniId_1")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@data-qa-id='log-in-with-sso']")
    private WebElement loginButton;

    @FindBy(xpath = "//button[@data-qa-id='log-in-with-email-and-password']")
    private WebElement loginWithEmailPasswordButton;

    public LoginOrgPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public boolean isLoginWithOrgPageCurrent() {
        Logger.info("Verify if Login With SSO Page is current.");
        return wait.until(ExpectedConditions.urlContains(LOGIN_WITH_ORG_PATH));
    }

    public boolean isEmailInputDisplayed() {
        return emailInput.isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        Logger.info("Verify login button is displayed.");
        return isElementDisplayed(wait, loginButton);
    }

    public void performLoginWithSSO(String email) {

        Logger.info("Login with company credentials.");
        emailInput.sendKeys(email);
        loginButton.click();
    }

    public void clickOnLoginWithEmailPasswordButton() {
        Logger.info("Click on Login With Email and Password button.");
        clickOnWebElement(wait, loginWithEmailPasswordButton);
    }
}