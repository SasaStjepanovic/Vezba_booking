package pages;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.HeaderComponent;

public class StaysHomePage extends BasePage {

    public HeaderComponent headerComponent;
    public FooterComponent footerComponent;

    public StaysHomePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        footerComponent = new FooterComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='ss']")
    WebElement locationInput;

    @FindBy(xpath = "//div[@class='xp__fieldset js--sb-fieldset accommodation ']/div[2]//div")
    WebElement dates;

    @FindBy(css = "[data-bui-ref='calendar-next']")
    WebElement calendarNext;

    @FindBy(css = ".xp__guests__count")
    WebElement guestCount;

    @FindBy(css = ".sb-group__field-adults .bui-stepper__subtract-button")
    WebElement adultsMinus;

    @FindBy(css = ".sb-group__field-adults .bui-stepper__add-button")
    WebElement adultsPlus;

    @FindBy(css = ".sb-group-children .bui-stepper__add-button")
    WebElement childrenPlus;

    @FindBy(css = ".sb-group__field-rooms .bui-stepper__add-button")
    WebElement roomsPlus;

    @FindBy(css = ".sb-searchbox__button ")
    WebElement searchButton;

    @FindBy(xpath = "//div//h1[@class='e1f827110f d3a14d00da']")
    WebElement verificationField;

    @FindBy(xpath = "//ul[@data-testid='autocomplete-results']/li[1]")
    WebElement clickFirstStayI;

    @FindBy(xpath = "//ul[@data-testid='autocomplete-results']/li[2]")
    WebElement clickFirstStayII;

    public void setLocation(String location) {
        clickElement(locationInput);
        typeText(locationInput, location, "Location Input");
    }

    public void openCalendar() {
        clickElement(dates);
    }

    public void setDate(String date) {
        String monthYear = date.split(" ")[0] + " " + date.split(" ")[1];
        String day = date.split(" ")[2];

        if (driver.findElements(By.xpath("//div[text()='" + monthYear + "']/..//span[text()='" + day + "']")).size() > 0) {
            clickElement(driver.findElements(By.xpath("//div[text()='" + monthYear + "']/..//span[text()='" + day + "']")).get(0));
        } else {
            while (driver.findElements(By.xpath("//div[text()='" + monthYear + "']/..//span[text()='" + day + "']")).size() == 0) {
                clickElement(calendarNext);
                if (driver.findElements(By.xpath("//div[text()='" + monthYear + "']/..//span[text()='" + day + "']")).size() > 0) {
                    clickElement(driver.findElements(By.xpath("//div[text()='" + monthYear + "']/..//span[text()='" + day + "']")).get(0));
                }
            }
        }
    }

    public void openGuestMenu(){
        clickElement(guestCount);
    }

    public void setGuestInfo(String adultNum){
        openGuestMenu();
        addAdults(adultNum);
    }

    public void addAdults(String num){
        if(num.equalsIgnoreCase("1")){
            clickElement(adultsMinus);
        } else if(num.equalsIgnoreCase("2")){
            //do nothing
        } else {
            for (int i = 2; i < Integer.parseInt(num);i++){
                clickElement(adultsPlus);
            }
        }
    }

    public void addChildren(String childrenNum, String childrenAges) throws InterruptedException {
        String[] ages = childrenAges.split(" ");

        if(childrenNum.equalsIgnoreCase("0")){
            //do nothing
        }else {
            for(int i = 0; i<Integer.parseInt(childrenNum);i++){
                clickElement(childrenPlus);
                Thread.sleep(500);
                Select s = new Select(driver.findElement(By.cssSelector("[data-group-child-age='"+i+"']")));
                s.selectByValue(ages[i]);
            }
        }
    }

    public void addRooms(String roomNum) {
        if(roomNum.equalsIgnoreCase("1")){
            //do nothing
        } else {
            for (int i = 1; i<Integer.parseInt(roomNum);i++){
                clickElement(roomsPlus);
            }
        }
    }

    public void clickSearchButton() {
        clickElement(searchButton);
    }

    public void verifyResults(String expextedText){
        comparePartOfText(verificationField,expextedText);
    }
}