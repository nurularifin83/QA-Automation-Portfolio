package com.orangehrm.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    //Variables
    private WebDriver driver;
    private JavascriptExecutor js;

    //Constructors
    public JavaScriptUtils(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // Click an element using Javascript
    public void clickElementJS(WebElement element){
        try {
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.err.println("JavaScript click failed: " + e.getMessage());
        }
    }

    // Scroll to an element
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Enter text into a field using JavaScript
    public void enterTextJS(WebElement element, String text) {
        js.executeScript("arguments[0].value=arguments[1];", element, text);
    }
}