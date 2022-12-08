package pages;

import enums.ConfigPropertyKeys;
import enums.WaitStatergy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.DriverUtil;
import utility.PropertyUtil;

import java.time.Duration;

public class BasePage {

    public void waitTill(WaitStatergy waitStatergy, WebElement element){
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(),
                Duration.ofSeconds(Integer.parseInt(PropertyUtil.getProperty(ConfigPropertyKeys.implicit_wait))));
        switch (waitStatergy){
            case visible -> {
                wait.until(ExpectedConditions.visibilityOf(element));
            }
            case clickable -> {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            }
        }
    }

    public void waitTill(int seconds){
        try{
            System.out.println("Waiting for "+seconds+"sec");
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void click(WaitStatergy waitStatergy, WebElement element){
        waitTill(waitStatergy, element);
        element.click();
    }

    public void sendKeys(WaitStatergy waitStatergy, WebElement element, String value){
        waitTill(waitStatergy, element);
        element.sendKeys(value);
    }
}
