package com.orangehrm.tests;

import com.orangehrm.basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginPageTest extends BaseTest {

    @Test(groups = { "sanity" })
    public void testLogoPresence(){
        Assert.assertTrue(loginPage.isLogoDisplayed(), "Logo is not present on the page");
    }

    @Test(groups = { "sanity" })
    public void testLoginPageTitle(){
        Assert.assertEquals(loginPage.getPageTitle(),"OrangeHRM",
                "\n Actual and Expected title do not match. \n");
    }

    @Test(groups = { "regression" })
    public void testLoginWithEmptyFields(){
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.readRequiredMessage(),"Required",
                "\n Actual & Expected message do not match. \n");
    }

    @Test(groups = { "regression" })
    public void testLoginWithUnregisterUsernameAndPassword(){
        loginPage.enterUsername("dsadsa");
        loginPage.enterPassword("123asds");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.readErrorMessage(),"Invalid credentials",
                "\n Actual & Expected message do not match. \n");
    }

    @Test(groups = { "sanity","regression" })
    public void testLoginWithValidCredentials(){
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.readDashboardText(),"Dashboard",
                "\n Actual & Expected Dashboard text do not match. \n");
    }

    @Test(groups = { "regression" })
    public void testLoginWithSQLInjection(){
        loginPage.enterUsername("' OR 1=1 --");
        loginPage.enterPassword("dasdsdsadsa");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.readErrorMessage(),"Invalid credentials",
                "\n Actual & Expected message do not match. \n");
    }

    @Test(groups = { "sanity" })
    public void fieldsOnThePageTest() throws IOException {
        List<String> fieldNames = excelReader.getFieldNamesFromExcel("LoginPage");
        Assert.assertTrue(loginPage.verifyPresenceOfElementsOnPage(fieldNames), "Some elements are not present on the SignIn page!");
    }
}