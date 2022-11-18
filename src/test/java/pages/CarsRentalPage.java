package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.components.HeaderComponent;

public class CarsRentalPage extends BasePage {

    public HeaderComponent headerComponent;
    public FooterComponent footerComponent;

    public CarsRentalPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        footerComponent = new FooterComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-view='rentalcars']//div[1]")
    WebElement pickUpFiled;

    @FindBy(css = "#ss_origin")
    WebElement typeText;

    @FindBy(xpath = "(//span[@class='search_hl_name']/parent::span)[2]")
    WebElement firstElementBellow;

    @FindBy(xpath = "(//div[@data-visible='accommodation,flights,rentalcars']/parent::div)[2]")
    WebElement calendar;

    @FindBy(xpath = "//button[@class='sb-searchbox__button ']")
    WebElement search;


    public void setPickUpLocation(String location) {
    clickElement(pickUpFiled, "Pickup filed is entered");
    typeText(typeText, location, "Pickup locaton is entered: ");
    clickElement(firstElementBellow, "first element bellow is clicked");
    }

    public void calendar(){
        clickElement(calendar, "Calendar is opened");
        clickElement(search, "Search is pressed");
    }
}