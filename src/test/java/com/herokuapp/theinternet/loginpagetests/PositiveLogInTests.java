package com.herokuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.page.LoginPage;
import com.herokuapp.theinternet.page.SecureAreaPage;
import com.herokuapp.theinternet.page.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveLogInTests extends TestUtilities {

    @Test
    public void logInTest() {

        log.info("Starting logIn test");

        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

        // execute log in
        SecureAreaPage secureAreaPage =  loginPage.logIn("tomsmith", "SuperSecretPassword!");
        takeScreenshot("LoginAndPassword");

        // Verifications
        // New page url is expected
        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        // log out button is visible
        Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(), "LogOut Button is not visible.");

        // Successful log in message
        String expectedSuccessMessage = "You logged into a secure area!";
        String actualSuccessMessage = secureAreaPage.getSuccessMessageText();
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
                "actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
                        + expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);
    }

}
