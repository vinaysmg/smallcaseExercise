package utility;

import enums.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

public class DriverUtil {
    private static WebDriver driver;
    private static ThreadLocal<WebDriver> driverTL = new ThreadLocal<>();

    public static synchronized void intiBrowser(Browsers browser){
        if( Objects.isNull(driverTL.get()) ){
            switch (browser){
                case chrome -> {
                    WebDriverManager.chromedriver().setup();
                    driverTL.set(new ChromeDriver());
                    break;
                }
                case firefox -> {
                    WebDriverManager.firefoxdriver().setup();
                    driverTL.set(new FirefoxDriver());
                    break;
                }
                default -> throw new RuntimeException("Invalid browser values, Supports only chrome and firefox");
            }
        }
    }

    public static void quitBrowser(){
        driverTL.get().quit();
        driverTL.remove();
    }

    public static WebDriver getDriver(){
        return driverTL.get();
    }
}
