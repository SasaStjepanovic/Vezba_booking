package selenium;

import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(String type) throws Exception {
        DriverManager driverManager;

        switch (type.toUpperCase()){
            case "CHROME":{
                driverManager = new ChromeDriverManager();
            }
            break;
            case "CHROME_H":{
                driverManager = new Chrome_HeadlessDriverManager();
            }
            break;
            case "FIREFOX":{
                driverManager = new FirefoxDriverManager();
            }
            break;
            default: throw new Exception("No such Browser configuration!");
        }
        return driverManager;
    }

}