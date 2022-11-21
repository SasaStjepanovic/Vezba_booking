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
import pages.CarsRentalPage;
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
    String ScrShoot1 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ScrShoot1");
    String ScrShootDesc = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ScrShootDesc");
    String ScrYesOrNo = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ScrYesOrNo");

    @Before
    public void setup() throws Exception {
        init(browser,wait);
        openBookingApp(env);
        new BasePage(driver).reportScreenshotAllure(ScrShoot1, ScrShootDesc, ScrYesOrNo);
    }

    @After
    public void tearDown() throws IOException {
        new BasePage(driver).takeScreenshot(ScrShoot1, ScrYesOrNo);
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

        staysHomePage.openCalendar();
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
    public void iVerifyFoundStays() throws InterruptedException {
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
    public void iClickSearchFlightsButton() throws InterruptedException {
        new FlightsHomePage(driver).search();
    }

    @And("I enter destination for round flight")
    public void iEnterDestinationForRoundFlight() throws InterruptedException {
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

    @And("I enter destination for one way flight")
    public void iEnterDestinationForOneWayFlight() {
        new FlightsHomePage(driver).enterOneWayFlight(data.get("origin1"),data.get("destination1"));
    }

    @And("Check data for one way flight")
    public void checkDataForOneWayFlight() {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.openCalendarOneWayFlight();
        hp.setDate(data.get("month3"),data.get("day3"));
    }

    @Then("I verify one way results")
    public void iVerifyOneWayResults() {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.verifyOneWayFlightResults(data.get("expectedText3"));
    }

    @And("I change flight class")
    public void iChangeFlightClass() {
        new FlightsHomePage(driver).flightClass(data.get("flightClass2"));
    }

    @And("I verify flight class I")
    public void iVerifyFlightClassI() {
        new FlightsHomePage(driver).verifyFlightClass(data.get("flightClass1"),data.get("expectedFlightClass1"));
    }

    @And("I verify flight class II")
    public void iVerifyFlightClassII() {
        new FlightsHomePage(driver).verifyFlightClass(data.get("flightClass2"),data.get("expectedFlightClass2"));

    }

    @Then("I verify negative round results")
    public void iVerifyNegativeRoundResults() throws InterruptedException {
        new FlightsHomePage(driver).verifyPremiumNegativeScenario(data.get("expectedTextPremium"));
    }

    @And("I change flight class III")
    public void iChangeFlightClassIII() {
        new FlightsHomePage(driver).flightClass(data.get("flightClass3"));
    }

    @And("I verify flight class III")
    public void iVerifyFlightClassIII() {
        new FlightsHomePage(driver).verifyFlightClass(data.get("flightClass3"),data.get("expectedFlightClass3"));
    }

    @And("I change flight class IV")
    public void iChangeFlightClassIV() {
        new FlightsHomePage(driver).flightClass(data.get("flightClass4"));
    }

    @And("I verify flight class IV")
    public void iVerifyFlightClassIV() {
        new FlightsHomePage(driver).verifyFlightClass(data.get("flightClass4"),data.get("expectedFlightClass4"));
    }

    @And("I verify negative round results II")
    public void iVerifyNegativeRoundResultsII() throws InterruptedException {
        new FlightsHomePage(driver).verifyFirstClassNegativeScenario(data.get("expectedTextFirstClass"));

    }

    @When("I enter pickup location")
    public void iEnterPickupLocation() {
        new CarsRentalPage(driver).setPickUpLocation(data.get("origin1"));
    }

    @Given("I am on the booking car page")
    public void iAmOnTheBookingCarPage() {
        new HeaderComponent(driver).navigateToCarsPage();
    }

    @And("I choose pickup date")
    public void iChoosePickupDate() throws InterruptedException {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.setDateCars(data.get("month3"),data.get("day3"));
        crp.setDateCars(data.get("month4"),data.get("day4"));
        Thread.sleep(4000);
        crp.searchCars();
    }

    @And("I choose calendar")
    public void iChooseCalendar() {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.calendarCars();
    }

    @And("I choose pickup time")
    public void iChoosePickupTime() {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.pickupTime(data.get("pickupHour"),data.get("pickupMinutes"));
    }

    @And("I choose pickup and dropoff time")
    public void iChoosePickupAndDropoffTime() {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.pickUpAndDropOffTime(data.get("pickupHour"),data.get("pickupMinutes"),data.get("dropHour"),data.get("dropMinutes"));
    }
}