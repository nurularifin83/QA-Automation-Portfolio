package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.utils.JavaScriptUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    // Variables
    private JavaScriptUtils scriptUtils;

    // Select WebElement
    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
    private WebElement forgotYourPasswordLink;

    // Constructors
    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.scriptUtils = new JavaScriptUtils(driver);
    }

    // Getter
    public WebElement getForgotYourPasswordLink(){
        customWait.waitForVisibilityOfElement(forgotYourPasswordLink);
        return forgotYourPasswordLink;
    }

    // Page Actions
    public void clickOnForgotYourPassword(){
        try{
            getForgotYourPasswordLink().click();
            LOGGER.info("Clicked on the Forgot Password Link.");
        }catch (Exception e){
            LOGGER.error("Failed to click on the link.", e);
            throw e;
        }
    }
}
