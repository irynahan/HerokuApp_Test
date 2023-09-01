package com.herokuapp.theinternet.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HoversPage extends BasePageObject{

    public HoversPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private String pageUrl = "http://the-internet.herokuapp.com/hovers";

    private By avatarLocator = By.xpath("//div[@class='figure']");
    private By viewProfileLinkLocator = By.xpath(".//a[contains(text(),'View profile')]");


    /** Open HoversPage with it's url */
    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    /** Open specified user profile */
    public void openUserProfile(int userNumber) {
        List<WebElement> avatars = findAll(avatarLocator);
        WebElement specifiedUserAvatar = avatars.get(userNumber - 1);
        hoverOverElement(specifiedUserAvatar);
        specifiedUserAvatar.findElement(viewProfileLinkLocator).click();
    }


}
