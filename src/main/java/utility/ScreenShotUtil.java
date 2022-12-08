package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShotUtil {
    private ScreenShotUtil(){}

    public static String takeScreenShotBase64(){
        return ((TakesScreenshot)DriverUtil.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
