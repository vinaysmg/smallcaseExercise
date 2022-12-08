package linseners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentReport;
import reports.ExtentReportLogger;

public class TestNGListener implements ITestListener, ISuiteListener {
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportLogger.pass(result.getName()+" is passed", true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportLogger.fail(result.getName()+" is failed", true);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportLogger.skip(result.getName()+" is skipped", true);
    }

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.intiReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.buildReport();
    }
}
