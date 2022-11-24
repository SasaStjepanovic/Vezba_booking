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

    @FindBy(xpath = "(//div[@data-visible='accommodation,flights,rentalcars']/parent::div)[3]")
    WebElement calendar;

    @FindBy(xpath = "//button[@class='sb-searchbox__button ']")
    WebElement search;

    @FindBy(xpath = "(//span[@class='c2-button-inner']/..)[2]")
    WebElement calendarNext;

    @FindBy(css = "[name='checkinTime']")
    WebElement pickupHours;

    @FindBy(css = "[name='checkinTimeMinutes']")
    WebElement pickupMinutes;

    @FindBy(css = "[name='checkoutTime']")
    WebElement dropHours;

    @FindBy(css = "[name='checkoutTimeMinutes']")
    WebElement dropMinutes;

    @FindBy(xpath = "//div[@data-visible='rentalcars']//div[@data-component='search/destination/input']//div[contains(@class,'fe_banner__message')]")
    WebElement wrongMessageLocation;

    @FindBy(xpath = "//form[@id='frm']//div[contains(@class,'bui-inline-container')]//div[contains(@class,'fe_banner__message')]")
    WebElement wrongMessageAges;

    @FindBy(xpath = "//span[contains(@class,'bui-checkbox__label')]")
    WebElement driverAgeBetween;

    @FindBy(css = "#driverAgeInput")
    WebElement driverAgeBeyond;

    public void verifyErrorCarsLocationMessage(String expectedText) throws InterruptedException {
        comparePartOfText(wrongMessageLocation, expectedText);
    }

    public void verifyErrorAgesMessage(String expectedText) throws InterruptedException {
        comparePartOfText(wrongMessageAges, expectedText);
    }

    public void setPickUpLocation(String location) {
        clickElement(pickUpFiled, "Pickup filed is entered");
        typeText(typeText, location, "Pickup locaton is entered: ");
        clickElement(firstElementBellow, "first element bellow is clicked");
    }

    public void changeDriversAge(String age){
        clickElement(driverAgeBetween, "Driver ager check box button is clicked");
        typeText(driverAgeBeyond,age, "Age is entered");
    }

    public void calendarCars() {
        clickElement(calendar, "Calendar is opened");
    }

    public void searchCars() {
        clickElement(search, "Search is pressed");
    }

    public void setDateCars(String month, String day) throws InterruptedException {
//        String xpath = "//div[@class='xp__dates-inner']/div[2]//div[@class='c2-month']//th[contains(text(),'"+month+"')]/../../..//span[text()='"+day+"']";
        String xpath = "(//*[@class='c2-month-table']//th[contains(text(),'" + month + "')]/../../..//*[text()='" + day + "'])[1]";

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

    public void pickupTime(String hour, String minutes) {
        Select dropdown1 = new Select(pickupHours);
        Select dropdown2 = new Select(pickupMinutes);
        dropdown1.selectByVisibleText(hour);
        dropdown2.selectByVisibleText(minutes);
        System.out.println("Pickup houres: " + hour);
        System.out.println("Pickup minutes: " + minutes);
    }

    public void dropTime(String hour1, String minutes1) {
        Select dropdown1 = new Select(dropHours);
        Select dropdown2 = new Select(dropMinutes);
        dropdown1.selectByVisibleText(hour1);
        dropdown2.selectByVisibleText(minutes1);
        System.out.println("Dropoff houres: " + hour1);
        System.out.println("Dropoff minutes: " + minutes1);
    }

    public void pickUpAndDropOffTime(String hour, String minutes, String hour1, String minutes1) {
        pickupTime(hour, minutes);
        dropTime(hour1, minutes1);
    }
}