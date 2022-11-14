package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.components.HeaderComponent;

import java.util.Map;

public class FlightsHomePage extends BasePage {

    public HeaderComponent headerComponent;
    public FooterComponent footerComponent;

    public FlightsHomePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        footerComponent = new FooterComponent(driver);
        PageFactory.initElements(driver, this);
    }



    @FindBy(css = ".css-k008qs")
    WebElement sourceOneWayDestinationClicked;

    @FindBy(xpath = "//*[@class='css-17g2hv0-radio-group']/li[3]")
    WebElement multipleDestinationRadio;

    @FindBy(xpath = "//*[@class='css-17g2hv0-radio-group']/li[2]")
    WebElement oneWayDestinationRadio;

    @FindBy(css = "[data-testid='add_flight']")
    WebElement addFlight;

    @FindBy(css = ".Calendar-module__control--next___N9ipu")
    WebElement calendarNext;

    @FindBy(css = "[data-testid='searchbox_submit']")
    WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'css-171z175')]/div[1]")
    WebElement originReturnFlight;

    @FindBy(xpath = "//div[contains(@class,'css-171z175')]/div[3]")
    WebElement destinationReturnFlight;

    @FindBy(xpath = "//div[contains(@class,'css-171z175')]/div[4]//button[1]//input")
    WebElement openCalendarReturnFlight;

    @FindBy(xpath = "//input[@class='css-g0pg3f-SearchboxInput']")
    WebElement openCalendarOneWayFlight;

    @FindBy(xpath = "//div[contains(@class,'css-171z175')]/div[4]//button[2]//input")
    WebElement returnReturnFlightInfo;

    @FindBy(xpath = "//div[contains(@class,'Stack-module__root--direction-column___2y5oZ')]/div[3]/div[4]//input")
    WebElement multipleFlightInfo3;

    @FindBy(xpath = "//div[contains(@class,'Stack-module__root--direction-column___2y5oZ')]/div[4]/div[4]//input")
    WebElement multipleFlightInfo4;

    String odaklePutujete = "//div[contains(@class,'Stack-module__root--direction-column___2y5oZ')]/div[$]/div[1]";
    String gdePutujete = "//div[contains(@class,'Stack-module__root--direction-column___2y5oZ')]/div[$]/div[3]";
    String kadaPutujete = "//div[contains(@class,'Stack-module__root--direction-column___2y5oZ')]/div[$]/div[4]";

    public void verifyRoundTripFlightResults(String expectedText3, String expectedText4) throws InterruptedException {
        compareData(openCalendarReturnFlight, expectedText3);
        Thread.sleep(1000);
        compareData(returnReturnFlightInfo, expectedText4);
    }

    public void verifyMultipleTripFlightResults(String expectedText3, String expectedText4) throws InterruptedException {
        compareData(multipleFlightInfo3, expectedText3);
        Thread.sleep(1000);
        compareData(multipleFlightInfo4, expectedText4);
    }

    public void verifyOneWayFlightResults(String expectedText3){
        compareData(openCalendarReturnFlight, expectedText3);
    }
    public void openCalendarReturnFlight(){
        clickElement(openCalendarReturnFlight);
    }

    public void openCalendarOneWayFlight(){
        clickElement(openCalendarOneWayFlight);
    }

    public void clickMultipleDestinationOption() {
        clickElement(multipleDestinationRadio);
    }

    public void enterDestinations(Map<String, String> data) throws InterruptedException {
        int destinations = Integer.parseInt(data.get("numDestinations"));

        for (int i = 1; i <= destinations; i++) {
            if(i>2){
                clickElement(addFlight);
            }
            setOrigin(data.get("origin"+i),String.valueOf(i));
            setDestination(data.get("destination"+i),String.valueOf(i));
            setFlightDate(data.get("month"+i), data.get("day"+i),String.valueOf(i));
            Thread.sleep(500);
        }
    }

    public void enterDataReturnFlight (String origin, String destination){
        clickElement(originReturnFlight);
        clickElement(driver.findElement(By.cssSelector("div.css-hboir5")));
        typeText(driver.findElement(By.cssSelector("[data-testid='searchbox_origin_input_0']")), origin, "");
        clickElement(driver.findElement(By.xpath("//div[@class='css-1tli02a-autocompleteResults']//li[1]")));

        typeText(driver.findElement(By.cssSelector("[data-testid='searchbox_destination_input_0']")), destination, "Round destination is entered: ");
        clickElement(driver.findElement(By.xpath("//div[@class='css-1tli02a-autocompleteResults']//li[1]")));
    }

    public void enterOneWayFlight (String origin, String destination){
        clickElement(oneWayDestinationRadio);
        clickElement(driver.findElement(By.xpath("//div[@class='css-171z175']//div[1]")));
        typeText(driver.findElement(By.cssSelector("[data-testid='searchbox_origin_input_0']")), origin, "");
        clickElement(driver.findElement(By.xpath("//div[@class='css-171z175']/div[3]")));

        typeText(driver.findElement(By.cssSelector("[data-testid='searchbox_destination_input_0']")), destination, "One-way destination is entered: ");
        clickElement(driver.findElement(By.xpath("//div[@class='css-1tli02a-autocompleteResults']//li[1]")));
    }

    private void setFlightDate(String month1, String day1, String i) {
        driver.findElement(By.xpath(kadaPutujete.replace("$", i))).click();
        setDate(month1, day1);
    }

    public void setDate(String month, String day) {
        String xpath = "//h3[contains(text(),'" + month + "')]/..//span[text()='" + day + "']";

        if (driver.findElements(By.xpath(xpath)).size() > 0) {
            clickElement(driver.findElements(By.xpath(xpath)).get(0));
        } else {
            while (driver.findElements(By.xpath(xpath)).size() == 0) {
                clickElement(calendarNext);
                if (driver.findElements(By.xpath(xpath)).size() > 0) {
                    clickElement(driver.findElements(By.xpath(xpath)).get(0));
                }
            }
        }
    }

    public void search() {
        clickElement(searchButton);
    }

    public void setOrigin(String origin, String i) {
        driver.findElement(By.xpath(odaklePutujete.replace("$", i))).click();
        typeText(driver.findElement(By.cssSelector("[data-testid='searchbox_origin_input_" + (Integer.parseInt(i) - 1) + "']")), origin, "");
        clickElement(driver.findElement(By.xpath("//div[@class='css-1tli02a-autocompleteResults']//li[1]")));
    }

    public void setDestination(String destination, String i) {
        driver.findElement(By.xpath(gdePutujete.replace("$", i))).click();
        typeText(driver.findElement(By.cssSelector("[data-testid='searchbox_destination_input_" + (Integer.parseInt(i) - 1) + "']")), destination, "");
        clickElement(driver.findElement(By.xpath("//div[@class='css-1tli02a-autocompleteResults']//li[1]")));
    }


}