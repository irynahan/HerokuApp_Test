package com.herokuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.page.LoginPage;
import com.herokuapp.theinternet.page.SecureAreaPage;
import com.herokuapp.theinternet.page.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeLogInTests extends BaseTest {

    @Parameters ({"username", "password", "expectedMessage"})
    @Test
    public void negativeLogInTest(String username, String password, String expectedMessage) {

        log.info("Starting negative logIn test");

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

        // Click on Form Authentication link
        LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();

        // execute log in
        loginPage.negativeLogIn(username, password);

        // wait for error message
        loginPage.waitForErrorMessage();
        String actualLogInErrorMessage = loginPage.getErrorMessageText();


        // Verifications
        // verify error message

        Assert.assertTrue(actualLogInErrorMessage.contains(expectedMessage), "Actual error message does not meet expected message. /n Actual: " + actualLogInErrorMessage + "/n Expected:"  + expectedMessage);

    }
}
