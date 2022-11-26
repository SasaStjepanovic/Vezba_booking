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

    String ScrShoot2 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ScrShoot2");
    String ScrShootDesc = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ScrShootDesc");
    String ScrYesOrNo = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ScrYesOrNo");

    @Before
    public void setup() throws Exception {
        init(browser, wait);
        openBookingApp(env);
        new BasePage(driver).reportScreenshotAllure(ScrShoot1, ScrShootDesc, ScrYesOrNo);
    }

    @After
    public void tearDown() throws IOException {
        new BasePage(driver).takeScreenshot(ScrShoot1, ScrYesOrNo);
        quit();
    }

    @Then("I verify one way results")
    public void iVerifyOneWayResults() {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.verifyOneWayFlightResults(data.get("expectedText3"));
    }

    @And("I verify flight class IV")
    public void iVerifyFlightClassIV() {
        new FlightsHomePage(driver).verifyFlightClass(data.get("flightClass4"), data.get("expectedFlightClass4"));
    }

    @And("I choose pickup date")
    public void iChoosePickupDate() throws InterruptedException {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.setDateCars(data.get("month3"), data.get("day3"));
        crp.setDateCars(data.get("month4"), data.get("day4"));
        crp.searchCars();
    }

    @And("I choose pickup time")
    public void iChoosePickupTime() {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.pickupTime(data.get("pickupHour"), data.get("pickupMinutes"));
    }

    @And("I choose invalid age")
    public void iChooseInvalidAge() {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.changeDriversAge(data.get("driverAges"));
    }

    @And("the booking page is opened")
    public void theBookingPageIsOpened() {
        new BasePage(driver).checkUrlPage(data.get("urlBasePage"));
    }

    @And("the language is chosen")
    public void theLanguageIsChosen() throws InterruptedException {
        new StaysHomePage(driver).headerComponent.chooseLanguage(data.get("language"));
        pause(1);
    }

    @When("a user enters destination location")
    public void aUserEntersDestinationLocation() throws InterruptedException {
        new StaysHomePage(driver).setLocation(data.get("location"));
    }

    @And("a user enters check in and checkout date")
    public void aUserEntersCheckInAndCheckoutDate() {
        StaysHomePage staysHomePage = new StaysHomePage(driver);
        staysHomePage.openCalendar();
        staysHomePage.setDate(data.get("checkInDate"));
        staysHomePage.setDate(data.get("checkOutDate"));
    }

    @And("a user add number of adults and children")
    public void aUserAddNumberOfAdultsAndChildren() throws InterruptedException {
        StaysHomePage staysHomePage = new StaysHomePage(driver);
        staysHomePage.openGuestMenu();
        staysHomePage.addAdults(data.get("numAdults"));
        staysHomePage.addChildren(data.get("numChildren"), data.get("childrenAges"));
    }

    @And("a user add number of rooms")
    public void aUserAddNumberOfRooms() {
        new StaysHomePage(driver).addRooms(data.get("numRooms"));
    }

    @And("a user click search button")
    public void aUserClickSearchButton() {
        new StaysHomePage(driver).clickSearchButton();
    }

    @Then("stay should be verified")
    public void stayShouldBeVerified() throws InterruptedException {
        new StaysHomePage(driver).verifyResults(data.get("verifResults"));
    }

    @Given("a user reads test data from {string} {string} by id {string}")
    public void aUserReadsTestDataFromById(String fileName, String sheetName, String id) throws Exception {
        data = new ExcelSupport().getDataByID(fileName, sheetName, id);
    }

    @And("the booking car page is opened")
    public void theBookingCarPageIsOpened() {
        new HeaderComponent(driver).navigateToCarsPage();
        new BasePage(driver).checkUrlPage(data.get("urlCarsPage"));
    }

    @When("a user enters pickup location")
    public void aUserEntersPickupLocation() {
        new CarsRentalPage(driver).setPickUpLocation(data.get("origin1"));
    }

    @And("a user clicks on calendar")
    public void aUserClicksOnCalendar() {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.calendarCars();
    }

    @When("a user add pickup and dropoff time")
    public void aUserAddPickupAndDropoffTime() {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.pickUpAndDropOffTime(data.get("pickupHour"), data.get("pickupMinutes"), data.get("dropHour"), data.get("dropMinutes"));
    }

    @And("a user click search car button")
    public void aUserClickSearchCarButton() {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.searchCars();
    }

    @Given("a user reads test data from {string} {string} by row {string}")
    public void aUserReadsTestDataFromByRow(String fileName, String sheetName, String rowNum) throws IOException {
        data = new ExcelSupport().getExcelByRow(fileName, sheetName, rowNum);
    }

    @Then("the ages are beyond of valid range, search is not possible should be verified")
    public void theAgesAreBeyondOfValidRangeSearchIsNotPossibleShouldBeVerified() throws InterruptedException {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.verifyErrorAgesMessage(data.get("wrongAgesMessage"));
    }

    @Then("the pickup location is empty, search is not possible should be verified")
    public void thePickupLocationIsEmptySearchIsNotPossibleShouldBeVerified() throws InterruptedException {
        CarsRentalPage crp = new CarsRentalPage(driver);
        crp.verifyErrorCarsLocationMessage(data.get("emptyLocatinMessage"));
    }

    @And("the booking flight page is opened")
    public void theBookingFlightPageIsOpened() {
        new StaysHomePage(driver).headerComponent.navigateToFlightsPage();
        new BasePage(driver).checkUrlPage(data.get("urlFlightPage"));
    }

    @When("a user add number of adult and children at the flights card")
    public void aUserAddNumberOfAdultAndChildrenAtTheFlightsCard() throws InterruptedException {
        FlightsHomePage flights = new FlightsHomePage(driver);
        flights.openPassengerMenu(data.get("numAdultsFlight"), data.get("numChildrenFlight"), data.get("childrenAgesFlight"));
    }

    @When("a user enters check in and checkout date for flights card")
    public void aUserEntersCheckInAndCheckoutDateForFlightsCard() {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.openCalendarReturnFlight();
        hp.setDate(data.get("month3"), data.get("day3"));
        hp.setDate(data.get("month4"), data.get("day4"));
    }

    @And("a user enters destination location for round-trip on flights card")
    public void aUserEntersDestinationLocationForRoundTripOnFlightsCard() throws InterruptedException {
        new FlightsHomePage(driver).enterDataReturnFlight(data.get("origin1"), data.get("destination1"));
    }

    @And("a user clicks search button on the flights card")
    public void aUserClicksSearchButtonOnTheFlightsCard() throws InterruptedException {
        new FlightsHomePage(driver).search();
    }

    @Then("round-trip should be verified")
    public void roundTripShouldBeVerified() throws InterruptedException {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.verifyRoundTripFlightResults(data.get("expectedText3"), data.get("expectedText4"));
    }

    @When("a user enters destination location for one-way on flights card")
    public void aUserEntersDestinationLocationForOneWayOnFlightsCard() {
        new FlightsHomePage(driver).enterOneWayFlight(data.get("origin1"), data.get("destination1"));
    }

    @When("a user sets date for one-way on flights card")
    public void aUserSetsDateForOneWayOnFlightsCard() {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.openCalendarOneWayFlight();
        hp.setDate(data.get("month3"), data.get("day3"));
    }

    @Then("one-way should be verified")
    public void oneWayShouldBeVerified() {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.verifyOneWayFlightResults(data.get("expectedText3"));
    }

    @Then("multiple should be verified")
    public void multipleShouldBeVerified() throws InterruptedException, IOException {
        FlightsHomePage hp = new FlightsHomePage(driver);
        new BasePage(driver).reportScreenshotAllure(ScrShoot2, ScrShootDesc, ScrYesOrNo);
        hp.verifyMultipleTripFlightResults(data.get("expectedText3"), data.get("expectedText4"));
    }

    @And("a user chosen multiple option")
    public void aUserChosenMultipleOption() {
        new FlightsHomePage(driver).clickMultipleDestinationOption();
    }

    @When("a user add destinations")
    public void aUserAddDestinations() throws InterruptedException {
        new FlightsHomePage(driver).enterDestinations(data);
    }

    @And("a user chosen I class")
    public void aUserChosenIClass() {
        new FlightsHomePage(driver).verifyFlightClass(data.get("flightClass1"), data.get("expectedFlightClass1"));
    }

    @When("a user chosen II class")
    public void aUserChosenIIClass() {
        new FlightsHomePage(driver).flightClass(data.get("flightClass2"));
    }

    @And("chosen II clas should be verified")
    public void chosenIIClasShouldBeVerified() throws InterruptedException {
        new FlightsHomePage(driver).verifyPremiumNegativeScenario(data.get("expectedTextPremium"));
    }

    @When("a user chosen III class")
    public void aUserChosenIIIClass() {
        new FlightsHomePage(driver).flightClass(data.get("flightClass3"));
    }

    @And("chosen III clas should be verified")
    public void chosenIIIClasShouldBeVerified() {
        new FlightsHomePage(driver).verifyFlightClass(data.get("flightClass3"), data.get("expectedFlightClass3"));
    }

    @When("a user chosen IV class")
    public void aUserChosenIVClass() {
        new FlightsHomePage(driver).flightClass(data.get("flightClass4"));
    }

    @Then("chosen IV clas should be verified")
    public void chosenIVClasShouldBeVerified() throws InterruptedException {
        new FlightsHomePage(driver).verifyFirstClassNegativeScenario(data.get("expectedTextFirstClass"));
    }
}