package linseners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {
    int count=0;
    int max = 1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<max){
            count++;
            return true;
        }
        return false;
    }
}
