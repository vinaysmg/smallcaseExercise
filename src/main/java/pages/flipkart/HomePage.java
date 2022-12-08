package pages.flipkart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DriverUtil;

public class HomePage {
    private WebDriver driver;
    private String windowId;
    public HomePage(){
        driver = DriverUtil.getDriver();
        PageFactory.initElements(driver, this);
        windowId = driver.getWindowHandle();
    }

    @FindBy(css = "._2MlkI1")
    private WebElement loginPopup;

    @FindBy(css = "._2KpZ6l._2doB4z")
    private WebElement closeLoginPopup;

    @FindBy(css = "input[title='Search for products, brands and more']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    public HomePage switchToWindow(){
        driver.switchTo().window(windowId);
        return this;
    }

    public boolean isLoginPopupOpen(){
        return closeLoginPopup.isEnabled();
    }

    public HomePage closeLoginPopup(){
        closeLoginPopup.click();
        return this;
    }

    public void enterKeyInSearchBox(String value){
        searchBox.sendKeys(value);
    }

    public void clickSearchButton(){
        searchButton.click();
    }

    public SearchPage searchForKeyword(String keyword){
        enterKeyInSearchBox(keyword);
        clickSearchButton();
        return new SearchPage();
    }
}
