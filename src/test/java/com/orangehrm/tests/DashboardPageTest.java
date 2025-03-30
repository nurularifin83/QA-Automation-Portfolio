package com.orangehrm.tests;

import com.orangehrm.basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpPage(){
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickOnLoginButton();
    }

    @Test(groups = { "sanity" })
    public void testLogoutPage(){
       dashboardPage.clickOnProfileName();
       dashboardPage.clickOnLogOutButton();
        Assert.assertEquals(loginPage.getPageTitle(), "OrangeHRM",
                "\n Actual & Expected title do not match. \n");
    }

    @Test(groups = { "sanity" })
    public void testAboutButton(){
        dashboardPage.clickOnProfileName();
        dashboardPage.clickOnAboutButton();
        Assert.assertEquals(dashboardPage.readAboutHeaderText(),"About",
                "\n Actual & Expected text do not match.\n");
        dashboardPage.clickOnCloseButton();
    }

    @Test(groups = { "sanity" })
    public void testSupportButton(){
        dashboardPage.clickOnProfileName();
        dashboardPage.clickOnSupportButton();
        Assert.assertEquals(dashboardPage.readGettingStartedText(),"Getting Started with OrangeHRM",
                "\n Actual & Expected text do not match. \n");
    }

    @Test(groups = { "sanity" })
    public void testChangePasswordWithIncorrectCurrentPassword(){
        dashboardPage.clickOnProfileName();
        dashboardPage.clickOnChangePasswordButton();
        dashboardPage.enterCurrentPassword("dsadsad");
        dashboardPage.enterNewPassword("B35t4r1f1n@123");
        dashboardPage.enterConfirmPassword("B35t4r1f1n@123");
        logger.info("Please enter the correct current password.");
    }

    @Test(groups = { "sanity" })
    public void testChangePasswordWithIncorrectConfirmPassword(){
        dashboardPage.clickOnProfileName();
        dashboardPage.clickOnChangePasswordButton();
        dashboardPage.enterCurrentPassword("admin123");
        dashboardPage.enterNewPassword("B35t4r1f1n@123");
        dashboardPage.enterConfirmPassword("B35t4r1f1n@3");
        Assert.assertEquals(dashboardPage.readErrorConfirmPassword(),"Passwords do not match",
                "\n Actual & Expected error message do not match. \n");
    }

    @Test(groups = { "sanity" })
    public void testChangePasswordWithValidCredentials(){
        dashboardPage.clickOnProfileName();
        dashboardPage.clickOnChangePasswordButton();
        dashboardPage.enterCurrentPassword("admin123");
        dashboardPage.enterNewPassword("admin123");
        dashboardPage.enterConfirmPassword("admin123");
        logger.info("Password updated successfully.");
    }
}
