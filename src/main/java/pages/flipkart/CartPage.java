package pages.flipkart;

import enums.WaitStatergy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DriverUtil;

import java.util.List;

public class CartPage {
    private static WebDriver driver;
    private static  JavascriptExecutor js;
    private String windowId;
    public CartPage(){
        driver = DriverUtil.getDriver();
        js =  (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        windowId = driver.getWindowHandle();
    }

    public CartPage switchToWindow(){
        driver.switchTo().window(windowId);
        return this;
    }

    @FindBy(xpath = "//button[text()=\"+\"]")
    private List<WebElement> increaseQty;

    @FindBy(css = "_2nQDXZ")
    private List<WebElement> items;

    @FindBy(css = "._3fSRat>._2-ut7f._1WpvJ7")
    private List<WebElement> itemsPrice;

    @FindBy(xpath = "//button[text()=\"Enter Delivery Pincode\"]")
    private WebElement enterDeliveryPincode;

    @FindBy(css = "input[type='TEXT'][ maxlength=\"6\" ]")
    private WebElement pincodeTextBox;

    @FindBy(xpath = "//div[text()='Submit']")
    private WebElement submitPincode;

    public CartPage increateQty(int itemsPosition){
        js.executeScript("arguments[0].scrollIntoView();", increaseQty.get(itemsPosition));
        increaseQty.get(itemsPosition).click();
        return this;
    }

    public int getItmePriceOf(int itemsPosition){
        js.executeScript("arguments[0].scrollIntoView();", itemsPrice.get(itemsPosition));
        String price = itemsPrice.get(itemsPosition).getText().replace(",","").substring(1);
        System.out.println("Cart page "+itemsPosition+"th Item price = "+Integer.parseInt(price));
        return Integer.parseInt(price);
    }

    public void clickDeliveryPincodeButton(){
        enterDeliveryPincode.click();
    }

    public void enterPincode(String pincode){
        pincodeTextBox.sendKeys(pincode);
    }

    public void clickSubmitPincode(){
        submitPincode.click();
    }

    public CartPage addDeliveryPincode(String pincode){
        clickDeliveryPincodeButton();
        enterPincode(pincode);
        clickSubmitPincode();
        return this;
    }
}
