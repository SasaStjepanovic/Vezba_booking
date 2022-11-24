package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#react-burger-menu-btn")
    WebElement menu;

    @FindBy(css = "[data-ga-track='click|Product Expansion|accommodation|booking (index)']")
    WebElement stays;
    @FindBy(css = "[data-decider-header='flights']")
    WebElement flights;
    @FindBy(xpath = "(//ul[contains(@data-et-click,'  customGoal:')])/li[3]")
    WebElement cars;

    @FindBy(css = "[data-decider-header='attractions']")
    WebElement attractions;

    @FindBy(css = "[aria-controls='airport_taxis']")
    WebElement airportTaxis;

    @FindBy(xpath = "//span//div[@class='bui-avatar bui-avatar--small']")
    WebElement chooseLanguage;

    public void clickMenu() {
        clickElement(menu, "Menu button");
    }

    public void navigateToPage(String pageName) throws Exception {
        switch (pageName.toLowerCase()) {
            case "stays": {
                navigateToStaysPage();
            }
            break;
            case "flights": {
                navigateToFlightsPage();
            }
            break;
            case "cars": {
                navigateToCarsPage();
            }
            break;
            case "attractions": {
                navigateToAttractionPage();
            }
            break;
            case "airports": {
                navigateToAirportPage();
            }
            break;
            default:
                throw new Exception("No such page: " + pageName);
        }
    }

    public void navigateToStaysPage() {
        clickElement(stays, "Stays button");
    }

    public void navigateToFlightsPage() {
        clickElement(flights, "Flights button");
    }

    public void navigateToCarsPage() {
        clickElement(cars, "Car rentals button");
    }

    public void navigateToAttractionPage() {
        clickElement(attractions, "Attractions button");
    }

    public void navigateToAirportPage() {
        clickElement(airportTaxis, "Airport taxis button");
    }

    public void chooseLanguage(String language) {
        clickElement(chooseLanguage, "Choose language button");
        clickElement(driver.findElement(By.xpath("//div[@class='bui-group bui-group--large']/div[last()]//div/div[last()]//div[@class='bui-inline-container bui-inline-container--align']//div[contains(text(),'" + language + "')]")), "Clicked on: " + language + " language");

    }


}