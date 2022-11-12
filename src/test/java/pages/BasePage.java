package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    int waitTime = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void explicitWait(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickElement(WebElement element, String log) {
        explicitWait(element);
        try {
            scrollToElement(element);
            new Actions(driver).moveToElement(element).perform();
            element.click();
            System.out.println("Clicked element: " + log);
        } catch (Exception e) {
            e.printStackTrace();
            element.click();
            System.out.println("Clicked element: " + log);
        }
    }
    public void scrollToElement (WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }
}
