package com.herokuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.page.LoginPage;
import com.herokuapp.theinternet.page.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class NegativeLogInTests extends BaseTest {

    @Parameters ({"username", "password", "expectedMessage"})
    @Test (priority = 1)
    public void negativeLogInTestParam(String username, String password, String expectedMessage) {

        log.info("Starting negativeLogInTestParam");

        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

        // execute log in
        loginPage.negativeLogIn(username, password);

        // wait for error message
        loginPage.waitForErrorMessage();
        String actualLogInErrorMessage = loginPage.getErrorMessageText();


        // Verifications
        // verify error message

        Assert.assertTrue(actualLogInErrorMessage.contains(expectedMessage), "Actual error message does not meet expected message. /n Actual: " + actualLogInErrorMessage + "/n Expected:"  + expectedMessage);

    }

    // login test with data csv provider

    @Test (enabled = true, priority = 2, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeLogInTest(Map<String, String> testData) {
        // Data
        String no = testData.get("no");
        String username  = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");

        log.info("Starting negativeLogInTest #" + no + " for " + description);

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

        // execute log in
        loginPage.negativeLogIn(username, password);

        // wait for error message
        loginPage.waitForErrorMessage();
        String actualLogInErrorMessage = loginPage.getErrorMessageText();


        // Verifications
        // verify error message

        Assert.assertTrue(actualLogInErrorMessage.contains(expectedErrorMessage), "Actual error message does not meet expected message. /n Actual: " + actualLogInErrorMessage + "/n Expected:"  + expectedErrorMessage);

    }

}
