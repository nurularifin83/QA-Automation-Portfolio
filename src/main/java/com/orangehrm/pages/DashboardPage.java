package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.utils.JavaScriptUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

    // Variables
    private JavaScriptUtils scriptUtils;

    // Select WebElement
    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    private WebElement profileName;

    @FindBy(xpath = "//a[text()='Logout']\n")
    private WebElement logOutButton;

    @FindBy(xpath = "//a[text()='About']")
    private WebElement aboutButton;

    @FindBy(xpath = "//h6[text()='About']")
    private WebElement aboutHeaderText;

    @FindBy(xpath = "//button[@class='oxd-dialog-close-button oxd-dialog-close-button-position']")
    private WebElement closeButton;

    @FindBy(xpath = "//a[text()='Support']")
    private WebElement supportButton;

    @FindBy(xpath = "//h6[text()='Getting Started with OrangeHRM']")
    private WebElement gettingStartedText;

    @FindBy(xpath = "//a[text()='Change Password']")
    private WebElement changePasswordButton;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    private WebElement currentPassword;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    private WebElement newPassword;

    @FindBy(xpath = "(//input[@type='password'])[3]")
    private WebElement confirmPassword;

    @FindBy(xpath = "//span[text()='Passwords do not match']")
    private WebElement errorConfirmPassword;

    // Constructors
    public DashboardPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.scriptUtils = new JavaScriptUtils(driver);
    }

    // Getter
    public WebElement getErrorConfirmPassword(){
        customWait.waitForVisibilityOfElement(errorConfirmPassword);
        return errorConfirmPassword;
    }

    public WebElement getCurrentPassword(){
        customWait.waitForVisibilityOfElement(currentPassword);
        return currentPassword;
    }

    public WebElement getNewPassword(){
        customWait.waitForVisibilityOfElement(newPassword);
        return newPassword;
    }

    public WebElement getConfirmPassword(){
        customWait.waitForVisibilityOfElement(confirmPassword);
        return confirmPassword;
    }

    public WebElement getChangePasswordButton(){
        customWait.waitForElementToBeClickable(changePasswordButton);
        return changePasswordButton;
    }

    public WebElement getGettingStartedText(){
        customWait.waitForVisibilityOfElement(gettingStartedText);
        return gettingStartedText;
    }

    public WebElement getSupportButton(){
        customWait.waitForElementToBeClickable(supportButton);
        return supportButton;
    }

    public WebElement getCloseButton(){
        customWait.waitForElementToBeClickable(closeButton);
        return closeButton;
    }

    public WebElement getAboutTextHeader(){
        customWait.waitForVisibilityOfElement(aboutHeaderText);
        return aboutHeaderText;
    }

    public WebElement getAboutButton(){
        customWait.waitForElementToBeClickable(aboutButton);
        return aboutButton;
    }

    public WebElement getLogOutButton(){
        customWait.waitForElementToBeClickable(logOutButton);
        return logOutButton;
    }

    public WebElement getProfileName(){
        customWait.waitForVisibilityOfElement(profileName);
        return profileName;
    }

    // Page Actions
    public String readErrorConfirmPassword(){
        try{
            return getErrorConfirmPassword().getText();
        }catch (Exception e){
            LOGGER.error("Failed to read the error confirm password.", e);
            throw e;
        }
    }

    public void enterCurrentPassword(String currentPassword){
        try{
            getCurrentPassword().sendKeys(currentPassword);
            LOGGER.info("Entered a current password. {}", currentPassword);
        }catch (Exception e){
            LOGGER.error("Failed to enter current password.", e);
            throw e;
        }
    }

    public void enterNewPassword(String newPassword){
        try{
            getNewPassword().sendKeys(newPassword);
            LOGGER.info("Entered a new password. {}", newPassword);
        }catch (Exception e){
            LOGGER.error("Failed to enter new password.", e);
            throw e;
        }
    }

    public void enterConfirmPassword(String confirmPassword){
        try{
            getConfirmPassword().sendKeys(confirmPassword);
            LOGGER.info("Entered a confirm password. {}", confirmPassword);
        }catch (Exception e){
            LOGGER.error("Failed to enter confirm password.", e);
            throw e;
        }
    }

    public void clickOnChangePasswordButton(){
        try{
            getChangePasswordButton().click();
        }catch (Exception e){
            LOGGER.error("Failed to click on Change password button.", e);
            throw e;
        }
    }

    public String readGettingStartedText(){
        try{
            return getGettingStartedText().getText();
        }catch (Exception e){
            LOGGER.error("Failed to read the text.", e);
            throw e;
        }
    }

    public void clickOnSupportButton(){
        try{
            getSupportButton().click();
            LOGGER.info("Clicked on Support button.");
        }catch (Exception e){
            LOGGER.error("Failed to click on Support Button.", e);
            throw e;
        }
    }

    public void clickOnCloseButton(){
        try{
            scriptUtils.clickElementJS(getCloseButton());
        }catch (Exception e){
            LOGGER.error("Failed to click on close button.", e);
            throw e;
        }
    }

    public String readAboutHeaderText(){
        try{
            return getAboutTextHeader().getText();
        }catch (Exception e){
            LOGGER.error("Failed to read header text.", e);
            throw e;
        }
    }

    public void clickOnAboutButton(){
        try{
            getAboutButton().click();
            LOGGER.info("Clicked on About button.");
        }catch (Exception e){
            LOGGER.error("Failed to click on About Button.", e);
            throw e;
        }
    }

    public void clickOnLogOutButton(){
        try{
            getLogOutButton().click();
            LOGGER.info("Clicked on logout button.");
        }catch (Exception e){
            LOGGER.error("Failed to click on LogOut Button.", e);
            throw e;
        }
    }

    public void clickOnProfileName(){
        try{
            getProfileName().click();
            LOGGER.info("Clicked on profile name.");
        }catch (Exception e){
            LOGGER.error("Failed to click on profile name.", e);
            throw e;
        }
    }
}
