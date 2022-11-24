package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class FooterComponent extends BasePage {

    public FooterComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".social_twitter")
    WebElement twitterIcon;

    public void clickTwitterIcon() {
        clickElement(twitterIcon, "Twitter button");
    }

}
