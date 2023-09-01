package com.herokuapp.theinternet.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePageObject {


    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private String pageUrl = "http://the-internet.herokuapp.com/";

    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private By checkboxesLinkLocator = By.linkText("Checkboxes");
    private By javaScriptAlertsLinkLocator = By.linkText("JavaScript Alerts");
    private By dropdownLinkLocator = By.linkText("Dropdown");
    private By multipleWindowsLinkLocator = By.linkText("Multiple Windows");
    private By editorLinkLocator = By.linkText("WYSIWYG Editor");

    /**
     * Open WelcomePage with it's url
     */
    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    /**
     * Open LoginPage by clicking on Form Authentication Link
     */
    public LoginPage clickFormAuthenticationLink() {
        log.info("Clicking Form Authentication link on Welcome Page");
        click(formAuthenticationLinkLocator);
        return new LoginPage(driver, log);
    }

    /**
     * Open Ceckboxes by clicking on Checkboxes Link
     */
    public CheckboxesPage clickCheckboxesLink(){
        log.info("Clicking Checkboxes link on Welcome Page");
        click(checkboxesLinkLocator);
        return new CheckboxesPage(driver, log);

    }

    /**
     * Open JavaScript Alerts by clicking on JavaScript Alerts Link
     */
    public JavaScriptAlertsPage clickJavaScriptAlertsLink(){
        log.info("Clicking JavaScript Alerts link on Welcome Page");
        click(javaScriptAlertsLinkLocator);
        return new JavaScriptAlertsPage(driver, log);

    }

    /**
     * Open Dropdown Link by clicking on Dropdown
     */
    public DropdownPage clickDropdownLink() {
        log.info("Clicking Dropdown link on Welcome Page");
        click(dropdownLinkLocator);
        return new DropdownPage(driver, log);
    }

    /**
     * Open Multiple Windows link by clicking on Multiple Windows
     */
    public WindowsPage clickMultipleWindowsLink() {
        log.info("Clicking Multiple Windows link on Welcome Page");
        click(multipleWindowsLinkLocator);
        return new WindowsPage(driver, log);
    }

    /**
     * Open WYSIWYG Editor link by clicking on WYSIWYG Editor
     */
    public EditorPage clickWYSIWYGEditorLink() {
        log.info("Clicking WYSIWYG Editor link on Welcome Page");
        click(editorLinkLocator);
        return new EditorPage(driver, log);
    }

    public void scrollToEditorLink(){
        scrollToWebElement(editorLinkLocator);
    }


}
