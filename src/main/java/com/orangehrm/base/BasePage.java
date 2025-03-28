package com.orangehrm.base;

import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.CustomWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected CustomWait customWait;
    protected ConfigReader configReader;
    protected static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    @FindBy(xpath = "//div[@class='orangehrm-login-branding']/img[@alt='company-branding']\n")
    private WebElement logo;

    //Constructor
    public BasePage(WebDriver driver){
        configReader = new ConfigReader();
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.customWait = new CustomWait(driver, Duration.ofSeconds(configReader.getGlobalWaitValue()));
    }

    public WebDriver getDriver(String browser){
        if (browser.equalsIgnoreCase(configReader.getBrowser())){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public WebElement getLogo(){
        customWait.waitForVisibilityOfElement(logo);
        return logo;
    }

    public void quiteDriver(){
        driver.quit();
    }

    // Common methods

    // Logo
    public boolean isLogoDisplayed(){
        try{
            return getLogo().isDisplayed();
        }catch (NoSuchElementException e){
            LOGGER.error("Logo is not present on the page.", e);
            return false;
        }
    }

    // Verify title
    public String getPageTitle(){
        try{
            return driver.getTitle();
        }catch (WebDriverException e){
            LOGGER.error("Failed to retrieve the page title.", e);
            return "";
        }
    }

    //Current URL
    public String getCurrentPageURL(){
        return driver.getCurrentUrl();
    }
}