package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tinylog.Logger;

public class BasePage {


    /**
     * Method used to wait for visibility and click on a web element.
     */
    protected void clickOnWebElement(WebDriverWait wait, WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).click();
        } catch (TimeoutException te) {
            Logger.info("Need Help Link not visible: " + te);
        }
    }

    /**
     * Method used to wait for visibility and display of a web element.
     */
    public boolean isElementDisplayed(WebDriverWait wait, WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException te) {
            Logger.info("Element not visible: " + te);
            return false;
        }
    }
}