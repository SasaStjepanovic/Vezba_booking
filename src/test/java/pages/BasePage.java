package pages;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BasePage {
    public WebDriver driver;
    int waitTime = 45;
    int waitTime2 = 15;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void explicitWait(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void explicitWaitPopup(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime2);
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
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

    public void clickElement(WebElement element){
        explicitWait(element);
        try {
            scrollToElement(element);
            new Actions(driver).moveToElement(element).perform();
            element.click();
        }catch (Exception e){
            e.printStackTrace();
            element.click();
        }
    }

    public void clickElementJS(WebElement element, String log) {
        explicitWait(element);

        try {
            scrollToElement(element);
            new Actions(driver).moveToElement(element).build().perform();
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
            System.out.println("Clicked element: " + log);
        } catch (Exception e) {
            e.printStackTrace();
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
            System.out.println("Clicked element: " + log);
        }
    }

    public void scrollToElement (WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void checkUrlPage (String url){
        String expextedUrl = "https://www.booking.com/" + url;
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Current url address: " + actualUrl);
        Assert.assertTrue(actualUrl.contains(expextedUrl), actualUrl);
    }
    public void comparePartOfText(WebElement element, String expectedText) throws InterruptedException {
        String actualTitle = element.getText();
        System.out.println("Actual title is: " + actualTitle);
        Thread.sleep(500);
        Assert.assertTrue(actualTitle.contains(expectedText), actualTitle);
    }

    public void compareData(WebElement element, String expectedData) {
        String actualData = element.getAttribute("value");
        System.out.println("Value atribiute is: " + actualData);
        Assert.assertTrue(element.isDisplayed(), "Verification test is not displayed");
        Assert.assertEquals(actualData, expectedData);
        System.out.println("Actual displayed data is: " + actualData);
    }

    public void typeText(WebElement element, String text, String log){
        explicitWait(element);

        try {
            scrollToElement(element);
            new Actions(driver).moveToElement(element).perform();
            element.click();
//            element.clear();
            element.sendKeys(text);
            System.out.println("Entered text: "+text+" to element: " + log);
        }catch (Exception e){
            e.printStackTrace();
            element.sendKeys(text);
            System.out.println("Entered text: "+text+" to element: " + log);
        }
    }

    public void typeTextJS(WebElement element, String log){
        explicitWait(element);

        try {
            new Actions(driver).moveToElement(element).build().perform();
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
//            executor.executeScript("arguments[0].value='" + text +"';'", element);
            executor.executeScript("arguments[0].value='IDEMOOOO'", element);
//            System.out.println("Entered text: "+text+" to element: " + log);
        }catch (Exception e){
            e.printStackTrace();
            JavascriptExecutor executor = (JavascriptExecutor)driver;
//            executor.executeScript("arguments[0].value='" + text +"''", element);
//            element.sendKeys(text);
//            System.out.println("Entered text: "+text+" to element: " + log);
        }
    }

    public void takeScreenshot(String name, String yesNo) throws IOException {
        if(yesNo.equalsIgnoreCase("YES")) {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("src/results/screenshots/"+name+".png"));
        }
    }

    public void reportScreenshotAllure(String name, String desc, String yesOrNo) throws IOException {
        if(yesOrNo.equalsIgnoreCase("YES")) {
            long finish = System.currentTimeMillis();
            takeScreenshot(name+ "_" +finish, yesOrNo);
            Path path = Paths.get("src/results/screenshots/"+name+"_"+finish+".png");
            InputStream is = Files.newInputStream(path);
            Allure.addAttachment(desc,is);
        }
    }
}
