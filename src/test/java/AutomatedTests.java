import enums.ConfigPropertyKeys;
import linseners.RetryListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.amazon.AmazonCartPage;
import pages.amazon.AmazonHomePage;
import pages.amazon.AmazonProductPage;
import pages.amazon.AmazonSearchPage;
import pages.flipkart.CartPage;
import pages.flipkart.HomePage;
import pages.flipkart.ProductPage;
import pages.flipkart.SearchPage;
import reports.ExtentReportLogger;
import utility.DriverUtil;
import utility.PropertyUtil;

import java.util.Scanner;

public class AutomatedTests extends BaseTest{

    @DataProvider
    public String[] searchkey(){
        String[] str = new String[]{
                "vu tv 80cm"
        };
        return str;
    }

    @Test(dataProvider = "searchkey", retryAnalyzer = RetryListener.class)
    public void scenario1Test(String searckkey){
        DriverUtil.getDriver().get(PropertyUtil.getProperty(ConfigPropertyKeys.flipkart_url));
        ExtentReportLogger.info("Launched browser", true);

        HomePage homePage = new HomePage();
        SearchPage searchPage = homePage.closeLoginPopup().searchForKeyword(searckkey);
        ExtentReportLogger.info("Searched for "+searckkey, true);

        int price1 = searchPage.getItemPriceof(0);
        ProductPage productPage = searchPage.clickOnItem(0);
        ExtentReportLogger.info("Selected 1st item from search result", true);

        CartPage cartPage = productPage.switchToWindow().addProductTocart();
        ExtentReportLogger.info("Added product to cart",true);

        cartPage.addDeliveryPincode("110001").increateQty(0);
        ExtentReportLogger.info("Increased qty by 1", true);

        int price2 = cartPage.getItmePriceOf(0);
        ExtentReportLogger.info("Final price is "+price2);
    }

    @Test(dataProvider = "searchkey", retryAnalyzer = RetryListener.class)
    public void scenario2Test(String searckkey){
        DriverUtil.getDriver().get(PropertyUtil.getProperty(ConfigPropertyKeys.flipkart_url));
        ExtentReportLogger.info("Launched browser", true);

        HomePage homePage = new HomePage();
        SearchPage searchPage = homePage.closeLoginPopup().searchForKeyword(searckkey);
        ExtentReportLogger.info("Searched for "+searckkey, true);

        ProductPage productPage = searchPage.clickOnItem(0);
        ExtentReportLogger.info("Selected 1st item from search result", true);

        int flipkartPrice1 = productPage.switchToWindow().getItemPrice();
        CartPage cartPage = productPage.addProductTocart();
        int flipkartPrice2 = cartPage.getItmePriceOf(0);
        ExtentReportLogger.info("Added product to cart, and price is "+flipkartPrice2);
        DriverUtil.getDriver().close();
        homePage.switchToWindow();


        DriverUtil.getDriver().get(PropertyUtil.getProperty(ConfigPropertyKeys.amazon_url));
        AmazonHomePage homeAmazon = new AmazonHomePage();
        ExtentReportLogger.info("Launced Amazon website", true);

        AmazonSearchPage searchPageAmazon = homeAmazon.searchKeyword(searckkey);
        ExtentReportLogger.info("Searched foe keyword "+searckkey, true);

        AmazonProductPage amazonProductPage = searchPageAmazon.chooseItemSimiliarToSearchKey(searckkey);
        String amazonPrice1 = amazonProductPage.switchToWindow().getItemPrice();
        System.out.println("amazonPrice1 = "+amazonPrice1);
        ExtentReportLogger.info("Selected item similiar to seach key", true);

        AmazonCartPage amazonCartPage = amazonProductPage.addProductToCart().proceedToCartPage();
        int amazonPrice2 = amazonCartPage.getSubTotal();
        System.out.println("amazonPrice2 = "+amazonPrice2);
        ExtentReportLogger.info("Added product to cart, price is "+amazonPrice2, true);

        if(flipkartPrice2 > amazonPrice2){
            System.out.println("Price in amazon is cheaper, "+amazonPrice2);
            ExtentReportLogger.info("Price in amazon is cheaper, "+amazonPrice2);
        }
        else if(flipkartPrice2 < amazonPrice2){
            System.out.println("Price in flipkart is cheaper, "+flipkartPrice2);
            ExtentReportLogger.info("Price inflipkart is cheaper, "+flipkartPrice2);
        }
        else{
            System.out.println("Price is same in flipkart and amazon, flipkartPrice2"+flipkartPrice2+" , amazonPrice2"+amazonPrice2);
            ExtentReportLogger.info("Price is same in flipkart and amazon, flipkartPrice2"+flipkartPrice2+" , amazonPrice2"+amazonPrice2);
        }
    }
}
