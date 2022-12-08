package pages.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DriverUtil;

import java.util.List;

public class AmazonSearchPage {
    private static WebDriver driver;
    public AmazonSearchPage(){
        driver = DriverUtil.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h2>a>span")
    private List<WebElement> itemsName;

    @FindBy(css = "a[target='_blank']>span.a-price>span>span.a-price-whole")
    private List<WebElement> itemsPrice;

    public AmazonProductPage clickOnItem(int itemPosition){
        itemsName.get(itemPosition).click();
        return new AmazonProductPage();
    }

    public AmazonProductPage chooseItemSimiliarToSearchKey(String key){
        String[] split = key.split("\\s");
        int count = 0;
        for(int i=0; i<itemsName.size(); i++){
            boolean matched = false;
            for(String str : split){
                System.out.println("str = "+str+", is similiar to "+itemsName.get(i).getText());
                if(itemsName.get(i).getText().toLowerCase().replace(" ","").contains(str.toLowerCase()))
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
