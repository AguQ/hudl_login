import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.tinylog.Logger;
import pages.*;
import parameters.LoginDataProviders;

public class HudlLoginTest extends BaseTest {

    private static final String HUDL_URL = "https://www.hudl.com/";
    private static final String HUDL_LOGIN_URL = "https://www.hudl.com/login";
    WebDriver driver;

    @BeforeMethod
    public void setup() {

        driver = initDriver();
    }

    @Test
    public void verifyLoginButtonAtHudlPage() {
        Logger.info("Navigating to URL");
        driver.get(HUDL_URL);

        HudlPage hudlPage = new HudlPage(driver);
        hudlPage.clickOnLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageCurrent(), "FAILURE :: User was not redirected to LoginPage.");
    }

    @Test(dataProvider = "LoginCredentials", dataProviderClass= LoginDataProviders.class)
    public void verifyUserLogin(String email, String password) {
        Logger.info("Navigating to URL");
        driver.get(HUDL_LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "FAILURE :: Login button is missing.");
        Assert.assertTrue(loginPage.isEmailInputDisplayed(), "FAILURE :: Email input is missing.");
        Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "FAILURE :: Password input is missing.");

        loginPage.performLogin(email, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageCurrent(), "FAILURE :: Redirect to Home page is incorrect.");
        Assert.assertTrue(homePage.isUserNameDisplayed(), "FAILURE :: User Name is not displayed. Verify login.");
    }

    @Test(dataProvider = "InvalidLoginCredentials", dataProviderClass= LoginDataProviders.class)
    public void verifyUserLoginWithInvalidCredentials(String email, String password) {
        Logger.info("Navigating to URL");
        driver.get(HUDL_LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "FAILURE :: Login button is missing.");
        Assert.assertTrue(loginPage.isEmailInputDisplayed(), "FAILURE :: Email input is missing.");
        Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "FAILURE :: Password input is missing.");

        loginPage.performLogin(email, password);
        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed());
    }

    @Test(dataProvider = "InvalidSSOLoginCredentials", dataProviderClass= LoginDataProviders.class)
    public void verifyOrganizationLoginWithInvalidCredentials(String companyEmail) {
        Logger.info("Navigating to URL");
        driver.get(HUDL_LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginWithOrgButtonDisplayed(), "FAILURE :: Login with Organization button is missing.");

        loginPage.clickOnLoginWithOrgButton();

        LoginOrgPage loginOrgPage = new LoginOrgPage(driver);
        Assert.assertTrue(loginOrgPage.isLoginWithOrgPageCurrent(), "FAILURE :: User was not redirected to Login With Org Page.");
        Assert.assertTrue(loginOrgPage.isLoginButtonDisplayed(), "FAILURE :: Login button is missing.");
        Assert.assertTrue(loginOrgPage.isEmailInputDisplayed(), "FAILURE :: Email input is missing.");

        loginOrgPage.performLoginWithSSO(companyEmail);

        Assert.assertTrue(loginPage.isLoginPageCurrent(), "FAILURE :: User was not redirected to LoginPage.");
        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed(), "FAILURE :: User was not redirected to LoginPage.");
    }

    @Test()
    public void verifyLogInWithEmailPasswordButton() {
        Logger.info("Navigating to URL");
        driver.get(HUDL_LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginWithOrgButtonDisplayed(), "FAILURE :: Login with Organization button is missing.");

        loginPage.clickOnLoginWithOrgButton();

        LoginOrgPage loginOrgPage = new LoginOrgPage(driver);
        Assert.assertTrue(loginOrgPage.isLoginWithOrgPageCurrent(), "FAILURE :: User was not redirected to Login With Org Page.");

        loginOrgPage.clickOnLoginWithEmailPasswordButton();

        Assert.assertTrue(loginPage.isLoginPageCurrent(), "FAILURE :: User was not redirected to LoginPage.");
    }

    @Test(dataProvider = "ResetPasswordCredentials", dataProviderClass= LoginDataProviders.class)
    public void verifyNeedHelpOption(String email) {

        Logger.info("Navigating to URL");
        driver.get(HUDL_LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnNeedHelpLink();

        HelpPage helpPage = new HelpPage(driver);
        Assert.assertTrue(helpPage.isHelpPageCurrent());
        Assert.assertTrue(helpPage.isCustomerSupportEmailDisplayed());

        helpPage.resetPassword(email);

        CheckEmailPage checkEmailPage = new CheckEmailPage(driver);
        Assert.assertTrue(checkEmailPage.isHelpPageCurrent());
        Assert.assertTrue(checkEmailPage.isConfirmationTextDisplayed());

    }

    @AfterMethod
    public void tearDown() {

        quitDriver();
    }

}