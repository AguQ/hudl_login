package pages;

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
import static data.Constants.LOGIN_PATH;

public class LoginPage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "logIn")
    private WebElement loginButton;

    @FindBy(xpath = "//p[@data-qa-id='error-display']")
    private WebElement loginErrorMessage;

    @FindBy(xpath = "//button[@data-qa-id='log-in-with-organization-btn']")
    private WebElement loginWithOrg;

    @FindBy(xpath = "//a[@data-qa-id='need-help-link']")
    private WebElement needHelpLink;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public boolean isLoginPageCurrent() {
        Logger.info("Verify if Login Page is current.");
        return wait.until(ExpectedConditions.urlContains(LOGIN_PATH));
    }

    public boolean isEmailInputDisplayed() {
        Logger.info("Verify email input is displayed.");
        return emailInput.isDisplayed();
    }

    public boolean isPasswordInputDisplayed() {
        Logger.info("Verify password input is displayed.");
        return passwordInput.isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        Logger.info("Verify login button is displayed.");
        return isElementDisplayed(wait, loginButton);
    }

    public void performLogin(String email, String password) {

        Logger.info("Login with user credentials.");
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public boolean isLoginErrorMessageDisplayed() {
        Logger.info("Verify login error message is displayed.");
        return isElementDisplayed(wait, loginErrorMessage);
    }

    public boolean isLoginWithOrgButtonDisplayed() {
        Logger.info("Verify login with SSO is displayed.");
        return isElementDisplayed(wait, loginWithOrg);
    }

    public void clickOnLoginWithOrgButton() {
        Logger.info("Click on Login With Organization button.");
        loginWithOrg.click();
    }

    public void clickOnNeedHelpLink() {
        try {
            wait.until(ExpectedConditions.visibilityOf(needHelpLink)).click();
        } catch (TimeoutException te) {
            Logger.info("Need Help Link not visible: " + te);
        }
    }

}