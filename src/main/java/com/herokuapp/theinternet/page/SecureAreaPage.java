package com.herokuapp.theinternet.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject {

    public SecureAreaPage(WebDriver driver, Logger log) {

        super(driver, log);
    }

    // expexted page URL
    private String pageUrl = "http://the-internet.herokuapp.com/secure";

    private By logOutButton = By.xpath("//a[@class='button secondary radius']");
    private By message = By.id("flash-messages");

    // get URL variable from PageObject (expected page URL)
    public String getPageUrl() {
        return pageUrl;
    }

    // verification if logOutButton is visible on the page
    public boolean isLogOutButtonVisible() {
        return find(logOutButton).isDisplayed();
    }

    // return text from success message
    public String getSuccessMessageText() {
        return find(message).getText();
    }

}
