package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.utils.JavaScriptUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LeavePage extends BasePage {

    // Variables
    private JavaScriptUtils scriptUtils;

    // Select WebElement

    // Constructors
    public LeavePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.scriptUtils = new JavaScriptUtils(driver);
    }

    // Getter

    // Page Actions
}
