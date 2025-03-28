package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.utils.JavaScriptUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends BasePage {

    // Variables
    private JavaScriptUtils scriptUtils;

    // Select WebElement
    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//div[contains(@class, 'oxd-alert-content--error')]//p")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message' and text()='Required'])[1]")
    private WebElement requiredMessage;

    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement dashboardText;

    // Constructors
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.scriptUtils = new JavaScriptUtils(driver);
    }

    // Getter
    public WebElement getDashboardText(){
        customWait.waitForVisibilityOfElement(dashboardText);
        return dashboardText;
    }

    public WebElement getRequiredMessage(){
        customWait.waitForVisibilityOfElement(requiredMessage);
        return requiredMessage;
    }

    public WebElement getLoginButton(){
        customWait.waitForVisibilityOfElement(loginButton);
        return loginButton;
    }

    public WebElement getErrorMessage(){
        customWait.waitForVisibilityOfElement(errorMessage);
        return errorMessage;
    }

    public WebElement getUsername(){
        customWait.waitForVisibilityOfElement(username);
        return username;
    }

    public WebElement getPassword(){
        customWait.waitForVisibilityOfElement(password);
        return password;
    }

    // Page Actions
    public String readDashboardText(){
        try{
            return getDashboardText().getText();
        }catch (Exception e){
            LOGGER.error("Failed to read the dashboard text.", e);
            throw e;
        }
    }

    public String readRequiredMessage(){
        try{
            return getRequiredMessage().getText();
        }catch (Exception e){
            LOGGER.error("Failed to read the error message.", e);
            throw e;
        }
    }

    public void clickOnLoginButton(){
        try {
            getLoginButton().click();
            LOGGER.info("Clicked on the login button.");
        }catch (Exception e){
            LOGGER.error("Failed to click the login button.", e);
            throw e;
        }
    }

    public String readErrorMessage(){
        try{
            return getErrorMessage().getText();
        }catch (Exception e){
            LOGGER.error("Failed to read the error message.", e);
            throw e;
        }
    }

    public void enterUsername(String username){
        try{
            getUsername().clear();
            getUsername().sendKeys(username);
            LOGGER.info("Entered username: {}" ,username);
        }catch (Exception e){
            LOGGER.error("Failed when enter username.", e);
            throw e;
        }
    }

    public void enterPassword(String password){
        try{
            getPassword().clear();
            getPassword().sendKeys(password);
            LOGGER.info("Entered password: {}" ,password);
        }catch (Exception e){
            LOGGER.error("Failed to enter password.", e);
            throw e;
        }
    }

    public boolean verifyPresenceOfElementsOnPage(List<String> fieldNames) {
        boolean result = true;
        int count = 0;
        for (String fieldName : fieldNames){
            WebElement element = getElementByFieldName(fieldName);

            if (element == null){
                System.out.println(
                        "Element is null, so please make sure you have added corresponding WebElement for this field "
                                + fieldName + "in excel"
                );
                result = false;
                continue;
            }

            if (element.isDisplayed()) { // No need to check elementState here
                System.out.println(fieldName + " is present on the page.");
            } else {
                System.out.println(fieldName + " is not present on the page.");
                count++;
            }
        }
        if (count != 0){
            result = false;
        }
        return result;
    }

    private WebElement getElementByFieldName(String fieldName) {
        WebElement element = null;
        try{
            switch (fieldName.toLowerCase()){
                case "username":
                    element = getUsername();;
                    break;

                case "password inputbox":
                    element = getPassword();
                    break;

                case "login button":
                    element = getLoginButton();
                    break;

                default:
                    throw new IllegalArgumentException("Invalid field name: " + fieldName);
            }
        }catch (IllegalArgumentException ie){
            System.out.println(ie.getMessage());
        }
        return element;
    }


}