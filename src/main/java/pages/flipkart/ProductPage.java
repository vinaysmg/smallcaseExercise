package pages.flipkart;

import enums.WaitStatergy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import utility.DriverUtil;

import static enums.WaitStatergy.clickable;

public class ProductPage extends BasePage {
    private static WebDriver driver;
    private String windowId;
    public ProductPage(){
        driver = DriverUtil.getDriver();
        PageFactory.initElements(driver, this);
        windowId =(String) driver.getWindowHandles().toArray()[1];
    }


    @FindBy(xpath = "//button[text()='ADD TO CART']")
    private WebElement addToCart;

    @FindBy(css = "._30jeq3._16Jk6d")
    private WebElement itemPrice;

    public ProductPage switchToWindow(){
        System.out.println("Windows opened"+driver.getWindowHandles().toArray());
        driver.switchTo().window(windowId);
        return this;
    }

    public void clickAddToCart(){
        waitTill(3);
       addToCart.click();
    }

    public CartPage addProductTocart(){
        clickAddToCart();
        return new CartPage();
    }

    public int getItemPrice(){
        String price = itemPrice.getText().substring(1).replace(",", "");
        System.out.println("Itm price in ProductPage is "+price);
        return Integer.parseInt(price);
    }
}
