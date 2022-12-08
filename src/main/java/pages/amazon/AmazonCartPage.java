package pages.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DriverUtil;

public class AmazonCartPage {
    private static WebDriver driver;
    public AmazonCartPage(){
        driver = DriverUtil.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span#sc-subtotal-amount-activecart>span")
    private WebElement subTotal;

    public int getSubTotal(){
        String[] price = subTotal.getText().replace(",", "").trim().split("\\.");
        return Integer.parseInt(price[0]);
    }
}
