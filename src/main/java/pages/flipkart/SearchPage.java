package pages.flipkart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DriverUtil;

import java.util.List;

public class SearchPage {
    private WebDriver driver;
    private String windowId;
    public SearchPage(){
        driver = DriverUtil.getDriver();
        PageFactory.initElements(driver, this);
        windowId = driver.getWindowHandle();
    }

    @FindBy(css = "._4rR01T")
    private List<WebElement> searchedItemsTitle;

    @FindBy(css = "._30jeq3._1_WHN1")
    private List<WebElement> itemsPrice;

    /**
     * This method switches window which was present during object creation.
     * @return {@link SearchPage}
     */
    public SearchPage switchToWindow(){
        driver.switchTo().window(windowId);
        return this;
    }

    /**
     * This item return price of item at given position in Search Page.
     * @param position
     * @return
     */
    public int getItemPriceof(int position){
        String textPrice = itemsPrice.get(position).getText();
        textPrice = textPrice.replace(",", "").substring(1);
        System.out.println("Item price = "+Integer.parseInt(textPrice));
        return Integer.parseInt(textPrice);
    }

    public ProductPage clickOnItem(int itemsPosition){
        searchedItemsTitle.get(itemsPosition).click();
        return new ProductPage();
    }

    /**
     *  This method checks whether each word of given key is present in item title
     * @param key key used in product search
     * @return {@link ProductPage}
     */
    public ProductPage chooseItemSimiliarToSearchKey(String key){
        String[] split = key.split("\\s");
        int count = 0;
        for(int i=0; i<searchedItemsTitle.size(); i++){
            boolean matched = false;
            for(String str : split){
                if(searchedItemsTitle.get(i).getText().toLowerCase().replace(" ","").contains(str.toLowerCase()))
                    matched = true;
                else{
                    matched = false;
                    break;
                }
            }
            if(matched){
                count = i;
                break;
            }
        }
        return clickOnItem(count);
    }
}
