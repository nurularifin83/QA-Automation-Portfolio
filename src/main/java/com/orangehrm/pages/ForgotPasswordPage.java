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

    // Getter
    public WebElement getHeaderText(){
        customWait.waitForVisibilityOfElement(headerText);
        return headerText;
    }

    // Page actions
    public String readHeaderText(){
        try{
            return getHeaderText().getText();
        }catch (Exception e){
            LOGGER.error("Header text element is not found.", e);
            throw e;
        }
    }

}
