package pages.amazon;

import enums.Browsers;
import enums.ConfigPropertyKeys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DriverUtil;
import utility.PropertyUtil;

public class AmazonProductPage {
    private static WebDriver driver;
    private String windowId;
    private static JavascriptExecutor js;
    public AmazonProductPage(){
        driver = DriverUtil.getDriver();
        PageFactory.initElements(driver, this);
        windowId =(String) driver.getWindowHandles().toArray()[1];
        js =  (JavascriptExecutor) driver;
    }

    @FindBy(css = "span.priceToPay>span>span.a-price-whole")
    private WebElement priceToPay;

    @FindBy(css = "input#add-to-cart-button")
    private WebElement addToCart;

    @FindBy(id = "productTitle")
    private WebElement productTitle;

    @FindBy(id = "attach-accessory-proceed-to-checkout-text")
    private WebElement proceedToCheckout;

    @FindBy(css = "input.a-button-input[type='submit'][aria-labelledby='attach-sidesheet-view-cart-button-announce']")
    private WebElement proceedToCartPage;

    @FindBy(css = "div#sw-atc-buy-box>span#sw-gtc")
    private WebElement getProceedToCartPageFirefox;
    public AmazonProductPage switchToWindow(){
        driver.switchTo().window(windowId);
        return this;
    }

    public String getItemPrice(){
        return priceToPay.getText().replace(",","");
    }

    public void clickAddToCart(){
        addToCart.click();
    }

    public AmazonProductPage addProductToCart(){
        js.executeScript("arguments[0].scrollIntoView();",addToCart);
        clickAddToCart();
        return this;
    }

    public AmazonCartPage proceedToCartPage(){
        if( PropertyUtil.getProperty(ConfigPropertyKeys.browser).equalsIgnoreCase(Browsers.firefox.name()) )
            getProceedToCartPageFirefox.click();
        else
            proceedToCartPage.click();
        return new AmazonCartPage();
    }
}
