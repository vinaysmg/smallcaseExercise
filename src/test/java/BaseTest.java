import enums.Browsers;
import enums.ConfigPropertyKeys;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.DriverUtil;
import utility.PropertyUtil;

import java.time.Duration;

public class BaseTest {

    @BeforeMethod
    public void setUp(){
        DriverUtil.intiBrowser(Browsers.valueOf(PropertyUtil.getProperty(ConfigPropertyKeys.browser)));
        DriverUtil.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(PropertyUtil.getProperty(ConfigPropertyKeys.implicit_wait))));
        DriverUtil.getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        DriverUtil.quitBrowser();
    }
}
