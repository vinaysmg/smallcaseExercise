package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import utility.ScreenShotUtil;

import java.util.Objects;

public class ExtentReportLogger {
    private ExtentReportLogger(){}

    private static ExtentTest getExtentTest(){
        if( Objects.isNull(ExtentReport.getTest())){
            return new ExtentReports().createTest("test");
        }else{
            return ExtentReport.getTest();
        }
    }

    public static void pass(String message){
        getExtentTest().pass(message);
    }

    public static void fail(String message){
        getExtentTest().fail(message);
    }

    public static void skip(String message){
        getExtentTest().skip(message);
    }

    public static void info(String message){
        getExtentTest().info(message);
    }

    public static void pass(String message, boolean attachImage){
        if(attachImage)
            getExtentTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenShotUtil.takeScreenShotBase64(), "Click to view screenShot").build());
        else
            pass(message);
    }

    public static void fail(String message, boolean attachImage){
        if(attachImage)
            getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenShotUtil.takeScreenShotBase64(), "Click to view screenShot").build());
        else
            fail(message);
    }

    public static void info(String message, boolean attachImage){
        if(attachImage)
            getExtentTest().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenShotUtil.takeScreenShotBase64(), "Click to view screenShot").build());
        else
            info(message);
    }

    public static void skip(String message, boolean attachImage){
        if(attachImage)
            getExtentTest().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenShotUtil.takeScreenShotBase64(), "Click to view screenShot").build());
        else
            skip(message);
    }
}
