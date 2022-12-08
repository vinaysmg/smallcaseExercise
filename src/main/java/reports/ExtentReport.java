package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {
    private ExtentReport(){}

    private static ExtentReports extent= new ExtentReports();
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static void intiReport(){
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/output/"+timeStamp+"-ExtentReport.html");
        spark.config().setDocumentTitle("SmallCase QA Exercise");
        extent.attachReporter(spark);
    }

    public static void buildReport(){
        extent.flush();
    }

    public static void createTest(String testName){
        ExtentTest test = extent.createTest(testName);
        extentTestThreadLocal.set(test);
    }

    public static ExtentTest getTest(){
        return extentTestThreadLocal.get();
    }

    public static void removeTest(){
        extentTestThreadLocal.remove();
    }
}
