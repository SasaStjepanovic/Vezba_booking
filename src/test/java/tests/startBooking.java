package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class startBooking extends BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) throws Exception {
        init(browser);

    }

    @Test
    @Parameters({"env"})
    public void startBook (String env) throws Exception {
        openBookingApp(env);
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        quit();
    }


}