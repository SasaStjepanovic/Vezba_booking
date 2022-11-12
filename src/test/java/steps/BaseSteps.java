package steps;

import excel.ExcelSupport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.testng.Reporter;
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
        quit();
    }

    @Given("I read test data from {string} {string} by id {string}")
    public void iReadTestDataFromById(String fileName, String sheetName, String rowNum) throws IOException {
        data = new ExcelSupport().getExcelRow(fileName, sheetName, rowNum);
    }


}