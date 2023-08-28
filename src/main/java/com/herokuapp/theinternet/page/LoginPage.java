package com.herokuapp.theinternet.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By usernameLocator = By.id("username");
    private By passwordLocator = By.name("password");
    private By logInButtonLocator = By.tagName("button");
    private By errorMessage = By.id("flash");

    // Execute log in
    public SecureAreaPage logIn(String username, String password) {
        log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(logInButtonLocator);
        return new SecureAreaPage(driver, log);
    }

    public void negativeLogIn(String username, String password) {
        log.info("Executing Negative LogIn with username [" + username + "] and password [" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(logInButtonLocator);
    }

    // get error message
    public String getErrorMessageText() {
        return find(errorMessage).getText();
    }

    public void waitForErrorMessage() {
        waitForVisibilityOf(errorMessage, 5);
    }
}
