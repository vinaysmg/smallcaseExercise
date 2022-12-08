package pages.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DriverUtil;

public class AmazonHomePage {
    private static WebDriver driver;
    public AmazonHomePage(){
        driver = DriverUtil.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement submitSearch;

    public void enterKeysOnSearchBox(String keys){
        searchBox.sendKeys(keys);
    }

    public void clickOnSubmitButton(){
        submitSearch.click();
    }

    public AmazonSearchPage searchKeyword(String key){
        enterKeysOnSearchBox(key);
        clickOnSubmitButton();
        return new AmazonSearchPage();
    }
}
