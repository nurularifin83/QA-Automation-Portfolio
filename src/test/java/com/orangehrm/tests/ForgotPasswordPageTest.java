package com.orangehrm.tests;

import com.orangehrm.basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgotPasswordPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpPage(){
        homePage.clickOnForgotYourPassword();
    }

    @Test(groups = { "sanity" })
    public void testForgotPasswordHeaderText(){
        Assert.assertEquals(forgotPasswordPage.readHeaderText(), "Reset Password",
                "\n Actual & Expected Header Text do not match. \n");
    }

    @Test(groups = { "sanity" })
    public void testLeaveUsernameEmpty(){
        forgotPasswordPage.enterUsername("");
        forgotPasswordPage.clickOnResetPasswordButton();
        Assert.assertEquals(forgotPasswordPage.readRequiredErrorMessage(),"Required",
                "\n Actual & Expected message do not match. \n");
    }

    @Test(groups = { "sanity" })
    public void shouldAllowPasswordResetForValidUsername(){
        forgotPasswordPage.enterUsername("Admin");
        forgotPasswordPage.clickOnResetPasswordButton();
        Assert.assertEquals(forgotPasswordPage.readHeaderText(),"Reset Password link sent successfully",
                "\n Actual & Expected Header text do not match. \n");
    }

    @Test(groups = { "sanity" })
    public void testCancelButton(){
        forgotPasswordPage.clickOnCancelButton();
        Assert.assertEquals(loginPage.getPageTitle(), "OrangeHRM",
                "\n Actual & Expected text do not match. \n");
    }

}