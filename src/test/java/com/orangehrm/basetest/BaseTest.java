package com.orangehrm.basetest;

import com.aventstack.extentreports.Status;
import com.orangehrm.base.BasePage;
import com.orangehrm.pages.*;
import com.orangehrm.reports.ExtentReportManager;
import com.orangehrm.testdatareaders.ExcelReader;
import com.orangehrm.tests.ForgotPasswordPageTest;
import com.orangehrm.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    private static ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    public BasePage basePage;
    public WebDriver driver;

    public HomePage homePage;
    public LoginPage loginPage;
    public ForgotPasswordPage forgotPasswordPage;
    public AdminPage adminPage;
    public BuzzPage buzzPage;
    public ClaimPage claimPage;
    public DashboardPage dashboardPage;
    public DirectoryPage directoryPage;
    public EmployeePage employeePage;
    public LeavePage leavePage;
    public MyInfoPage myInfoPage;
    public PerformancePage performancePage;
    public RecruitmentPage recruitmentPage;
    public TimePage timePage;

    public ConfigReader configReader;
    public ExcelReader excelReader;

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    private static int skippedTests = 0;

    public WebDriver getLocalDriver(){
        return localDriver.get();
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context){
        configReader = new ConfigReader();
        if (configReader.getParallelMode().equalsIgnoreCase("Yes")){
            context.getSuite().getXmlSuite().setThreadCount(Integer.parseInt(configReader.getMaxParallelTests()));
        }

        String suiteName = context.getSuite().getName();
        String groupsList[] = context.getIncludedGroups();
        String includedGroupsList = String.join(",", groupsList);
        logger.info("Starting Execution for Test Suite => " + suiteName + " with Tag => " + includedGroupsList);
        ExtentReportManager.setupExtentReport();
        ExtentReportManager.getReport().setSystemInfo("Suite Name", suiteName);
    }

    @BeforeTest(alwaysRun = true)
    public void setUpTest(ITestContext context){
        String testName = context.getName();
        logger.info("Starting Execution for Test=> "+testName);
        ExtentReportManager.getReport().setSystemInfo("Test Name", testName);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        configReader = new ConfigReader();
        basePage = new BasePage(getLocalDriver());
        WebDriver driver = basePage.getDriver(configReader.getBrowser());
        logger.info("Opening the browser - " + configReader.getBrowser());
        localDriver.set(driver);
        getLocalDriver().get(configReader.getUrl());

        // Print the session ID, ThreadID
        long threadId = Thread.currentThread().threadId();
        String sessionId = ((RemoteWebDriver)getLocalDriver()).getSessionId().toString();
        System.out.println("Thread ID: " +threadId+ "- Starting Session ID: " + sessionId);

        homePage = new HomePage(getLocalDriver());
        loginPage = new LoginPage(getLocalDriver());
        forgotPasswordPage = new ForgotPasswordPage(getLocalDriver());
        adminPage = new AdminPage(getLocalDriver());
        buzzPage = new BuzzPage(getLocalDriver());
        claimPage = new ClaimPage(getLocalDriver());
        dashboardPage = new DashboardPage(getLocalDriver());
        directoryPage = new DirectoryPage(getLocalDriver());
        employeePage = new EmployeePage(getLocalDriver());
        leavePage = new LeavePage(getLocalDriver());
        myInfoPage = new MyInfoPage(getLocalDriver());
        performancePage = new PerformancePage(getLocalDriver());
        recruitmentPage = new RecruitmentPage(getLocalDriver());
        timePage = new TimePage(getLocalDriver());

        excelReader = new ExcelReader("./src/test/resources/test-data/"+configReader.getFieldsVerificationExcelName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result){

        totalTests++;

        if (result.getStatus() == ITestResult.FAILURE){
            ExtentReportManager.getTest().fail(result.getThrowable());
            failedTests++;
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReportManager.getTest().skip(result.getThrowable());
            skippedTests++;
        } else {
            ExtentReportManager.getTest().pass("Test Passed");
            passedTests++;
        }

        ExtentReportManager.getTest().log(Status.INFO, "Test Method Execution completed");

        getLocalDriver().quit();
        localDriver.remove();
        logger.info("Closing the browser - " + configReader.getBrowser());
    }

    @AfterSuite(alwaysRun = true)
    public void generateReport(){
        double passPercentage = (double) passedTests / (totalTests-skippedTests);
        passPercentage = passPercentage*100;

        ExtentReportManager.createCustomTable(
                String.valueOf(totalTests-skippedTests),
                String.valueOf(passedTests),
                String.valueOf(failedTests),
                String.valueOf(skippedTests),
                String.format("%.2f",passPercentage),
                configReader.getBrowser(),
                configReader.getUrl());
        // Generate and add the custom table before flushing the report

        ExtentReportManager.flushReport();
    }
}