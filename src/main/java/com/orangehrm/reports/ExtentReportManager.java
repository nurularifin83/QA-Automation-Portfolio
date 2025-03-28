package com.orangehrm.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangehrm.utils.ConfigReader;

public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static final ConfigReader configReader = new ConfigReader();
    private static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();


    public static void setupExtentReport(){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./extent-reports/extent.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Orangehrm Automation Report");
        extentReports.setSystemInfo("Browser", configReader.getBrowser());
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    }

    public static void createTest(String testName, String groups, String className, String xmlTestName, String author){
        ExtentTest extentTest = extentReports
                .createTest("<b>"+testName+"</b>").
                assignCategory(groups, className, xmlTestName).
                assignAuthor(author).
                assignDevice(configReader.getBrowser());
        testThreadLocal.set(extentTest);
    }

    public static ExtentTest getTest(){
        return testThreadLocal.get();
    }

    public static ExtentReports getReport(){
        return extentReports;
    }

    public static void flushReport(){
        extentReports.flush();
    }

    public static void createCustomTable(String totalTests, String passedTests, String failedTests, String skippedTests, String passPercentage, String browser, String url){
        String customTable = "<table style='width:100%; border: 2px solid black; border-callapse: collapse;'>"
                +"<tr style='background-color:#ff355e'>"
                +"<th style='border: 1px solid brown; text-align: center; font-size: 10px; width: 15%;color: #000000'>Total Tests</th>"
                +"<th style='border: 1px solid brown; text-align: center; font-size: 10px; width: 12%;color: #000000'>Passed</th>"
                +"<th style='border: 1px solid brown; text-align: center; font-size: 10px; width: 10%;color: #000000'>Failed</th>"
                +"<th style='border: 1px solid brown; text-align: center; font-size: 10px; width: 12%;color: #000000'>Skipped</th>"
                +"<th style='border: 1px solid brown; text-align: center; font-size: 10px; width: 15%;color: #000000'>Passed%</th>"
                +"<th style='border: 1px solid brown; text-align: center; font-size: 10px; width: 15%;color: #000000'>Browser</th>"
                +"<th style='border: 1px solid brown; text-align: center; font-size: 10px; width: 21%;color: #000000'>URL</th>"
                +"</tr>"
                +"<tr>"
                +"<td style='border: 1px solid black; text-align: center; font-size: 10px; font-weight: bold; width: 15%;'>"+totalTests+"</td>"
                +"<td style='border: 1px solid black; text-align: center; font-size: 10px; font-weight: bold; width: 12%;color: green;'>"+passedTests+"</td>"
                +"<td style='border: 1px solid black; text-align: center; font-size: 10px; font-weight: bold; width: 10%;color: red'>"+failedTests+"</td>"
                +"<td style='border: 1px solid black; text-align: center; font-size: 10px; font-weight: bold; width: 12%;color: orange'>"+skippedTests+"</td>"
                +"<td style='border: 1px solid black; text-align: center; font-size: 10px; font-weight: bold; width: 15%;color: blue'>"+passPercentage+"</td>"
                +"<td style='border: 1px solid black; text-align: center; font-size: 10px; font-weight: bold; width: 15%;color: blue'>"+browser+"</td>"
                +"<td style='border: 1px solid black; text-align: center; font-size: 10px; font-weight: bold; width: 21%;color: blue'>"+url+"</td>"
                +"</tr>"
                +"</table>";
        String testSummaryKey = "<div style='text-align: center; font-weight: bold; font-size: 14px'>Test<br/>Summary</div>";
        extentReports.setSystemInfo(testSummaryKey, customTable);
    }
}