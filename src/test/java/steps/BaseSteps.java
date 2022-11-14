package steps;

import excel.ExcelSupport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.BasePage;
import pages.FlightsHomePage;
import pages.StaysHomePage;
import pages.components.HeaderComponent;
import tests.BaseTest;

import java.io.IOException;
import java.util.Map;

public class BaseSteps extends BaseTest {

    Map<String, String> data;

    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");

    String wait = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("wait");

    @Before
    public void setup() throws Exception {
        init(browser,wait);
        openBookingApp(env);
    }

    @After
    public void tearDown() throws IOException {
//        quit();
    }

    @Given("I verify that the booking page is open")
    public void iVerifyThatTheBookingPageIsOpen() {
        new BasePage(driver).checkUrlPage(data.get("urlBasePage"));
    }

    @Given("I read test data from {string} {string} by id {string}")
    public void iReadTestDataFromById(String fileName, String sheetName, String id) throws Exception {
        data = new ExcelSupport().getDataByID(fileName, sheetName, id);
    }

    @When("I enter destination location")
    public void iEnterDestinationLocation() {
        new StaysHomePage(driver).setLocation(data.get("location"));
    }

    @And("I enter check in date")
    public void iEnterCheckInDate() {
        StaysHomePage staysHomePage = new StaysHomePage(driver);

//        staysHomePage.openCalendar();
        staysHomePage.setDate(data.get("checkInDate"));
    }

    @And("I enter check out date")
    public void iEnterCheckOutDate() {
        new StaysHomePage(driver).setDate(data.get("checkOutDate"));
    }

    @And("I add adults")
    public void iAddAdults() {
        StaysHomePage staysHomePage = new StaysHomePage(driver);

        staysHomePage.openGuestMenu();
        staysHomePage.addAdults(data.get("numAdults"));
    }

    @And("I add children")
    public void iAddChildren() throws InterruptedException {
        StaysHomePage staysHomePage = new StaysHomePage(driver);
        staysHomePage.addChildren(data.get("numChildren"), data.get("childrenAges"));
    }

    @And("I add rooms")
    public void iAddRooms() {
        new StaysHomePage(driver).addRooms(data.get("numRooms"));
    }

    @And("I click search button")
    public void iClickSearchButton() {
        new StaysHomePage(driver).clickSearchButton();
    }

    @And("I verify found stays")
    public void iVerifyFoundStays() {
        new StaysHomePage(driver).verifyResults(data.get("verifResults"));
    }


    @And("I verify that I on flights page")
    public void iVerifyThatIOnFlightsPage() {
        new BasePage(driver).checkUrlPage(data.get("urlFlightPage"));
    }

    @And("I select multiple destination option")
    public void iSelectMultipleDestinationOption() {
        new FlightsHomePage(driver).clickMultipleDestinationOption();
    }

    @Given("I am on the booking flights page")
    public void iAmOnTheBookingFlightsPage() {
        new StaysHomePage(driver).headerComponent.navigateToFlightsPage();
    }

    @And("I enter destinations")
    public void iEnterDestinations() throws InterruptedException {
        new FlightsHomePage(driver).enterDestinations(data);
    }

    @And("I click search flights button")
    public void iClickSearchFlightsButton() {
        new FlightsHomePage(driver).search();
    }

    @And("I enter destination for round flight")
    public void iEnterDestinationForRoundFlight() {
        new FlightsHomePage(driver).enterDataReturnFlight(data.get("origin1"),data.get("destination1"));
    }

    @And("Check date for round flight")
    public void checkDateForReturnFlight() {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.openCalendarReturnFlight();
        hp.setDate(data.get("month3"),data.get("day3"));
        hp.setDate(data.get("month4"),data.get("day4"));
    }

    @Then("I verify round results")
    public void iVerifyRoundResults() throws InterruptedException {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.verifyRoundTripFlightResults(data.get("expectedText3"),data.get("expectedText4"));
    }

    @Then("I verify multiple results")
    public void iVerifyMultipleResults() throws InterruptedException {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.verifyMultipleTripFlightResults(data.get("expectedText3"),data.get("expectedText4"));
    }

    @And("I choose language")
    public void iChooseLanguage() throws InterruptedException {
        new StaysHomePage(driver).headerComponent.chooseLanguage(data.get("language"));
        pause(2);
    }

    @Given("I read test data from {string} {string} by row {string}")
    public void iReadTestDataFromByRow(String fileName, String sheetName, String rowNum) throws IOException {
        data = new ExcelSupport().getExcelByRow(fileName, sheetName, rowNum);
    }
}