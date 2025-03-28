package com.orangehrm.tests;

import com.orangehrm.basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgotPasswordPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpPageForgotPassword(){
        homePage.clickOnForgotYourPassword();
    }

    @Test(groups = { "sanity1" })
    public void testForgotPasswordHeaderText(){
        Assert.assertEquals(forgotPasswordPage.readHeaderText(), "Reset Password",
                "\n Actual & Expected Header Text do not match. \n");
    }

}