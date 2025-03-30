package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.utils.JavaScriptUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends BasePage {

    // Variables
    private JavaScriptUtils scriptUtils;

    // Constructors
    public ForgotPasswordPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.scriptUtils = new JavaScriptUtils(driver);
    }

    // Select WebElement
    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-forgot-password-title']")
    private WebElement headerText;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    private WebElement requiredErrorMessage;

    @FindBy(xpath = "//button[@type='button']")
    private WebElement cancelButton;

    // Getter
    public WebElement getCancelButton(){
        customWait.waitForElementToBeClickable(cancelButton);
        return cancelButton;
    }

    public WebElement getRequiredErrorMessage(){
        customWait.waitForVisibilityOfElement(requiredErrorMessage);
        return requiredErrorMessage;
    }

    public WebElement getResetPassword(){
        customWait.waitForElementToBeClickable(resetPasswordButton);
        return resetPasswordButton;
    }

    public WebElement getUsername(){
        customWait.waitForVisibilityOfElement(username);
        return username;
    }

    public WebElement getHeaderText(){
        customWait.waitForVisibilityOfElement(headerText);
        return headerText;
    }

    // Page actions
    public void clickOnCancelButton(){
        try{
            getCancelButton().click();
            LOGGER.info("Clicked on Cancel Button.");
        }catch (Exception e){
            LOGGER.error("Failed to click on Cancel Button.", e);
            throw e;
        }
    }

    public String readRequiredErrorMessage(){
        try{
            return getRequiredErrorMessage().getText();
        }catch (Exception e){
            LOGGER.error("Required Error Message element is not found.", e);
            throw e;
        }
    }

    public void clickOnResetPasswordButton(){
        try{
            getResetPassword().click();
            LOGGER.info("Clicked on the Reset Password Button.");
        }catch (Exception e){
            LOGGER.error("Failed to click the Reset Password button.", e);
            throw e;
        }
    }

    public void enterUsername(String username){
        try{
            getUsername().sendKeys(username);
            LOGGER.info("Entering a username: {}", username);
        }catch (Exception e){
            LOGGER.error("Failed to enter a username.", e);
            throw e;
        }
    }

    public String readHeaderText(){
        try{
            return getHeaderText().getText();
        }catch (Exception e){
            LOGGER.error("Header text element is not found.", e);
            throw e;
        }
    }

}
