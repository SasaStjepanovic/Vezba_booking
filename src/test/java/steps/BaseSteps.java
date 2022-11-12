package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.Reporter;
import tests.BaseTest;

import java.io.IOException;

public class BaseSteps extends BaseTest {

    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");

    @Before
    public void setup() throws Exception {
        init(browser);
        openBookingApp(env);
    }

    @After
    public void tearDown() throws IOException {
        quit();
    }
}