package com.orangehrm.customelisteners;

import com.orangehrm.utils.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {

    ConfigReader configReader = new ConfigReader();
    private int retryCount = 0;
    private final int max_retryCount = Integer.parseInt(configReader.getMaxRetries());

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < max_retryCount && !result.isSuccess()){
            retryCount++;
            System.out.println("Retrying the Test ==> " + result.getName() + " ==> Attempt No = " + retryCount);
            return true;
        }
        return false;
    }
}