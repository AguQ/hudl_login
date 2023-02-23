package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static data.Constants.DRIVER_WAIT_SECONDS;

public class HudlPage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@data-qa-id='login']")
    private WebElement loginButton;

    public HudlPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public void clickOnLoginButton() {
        clickOnWebElement(wait, loginButton);
    }

}