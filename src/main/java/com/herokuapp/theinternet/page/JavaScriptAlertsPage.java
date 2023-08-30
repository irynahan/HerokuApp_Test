package com.herokuapp.theinternet.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavaScriptAlertsPage extends BasePageObject{

    public JavaScriptAlertsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    // By.xpath("//button[text()='Click for JS Alert']");
    private By clickForJSAlertButtonLocator = By.xpath("//button[@onclick = 'jsAlert()']");
    private By clickForJSConfirmButtonLocator = By.xpath("//button[@onclick = 'jsConfirm()']");
    private By clickForJSPromptButtonLocator = By.xpath("//button[@onclick = 'jsPrompt()']");
    private By resultTextLocator = By.id("result");

    /** Clicking on 'Click for JS Alert' button to open alert */
    public void openJSAlert() {
        log.info("Clicking on 'Click for JS Alert' button to open alert");
        click(clickForJSAlertButtonLocator);

    }

    /** Clicking on 'Click for JS Confirm' button to open alert */
    public void openJSConfirm() {
        log.info("Clicking on 'Click for JS Confirm' button to open alert");
        click(clickForJSConfirmButtonLocator);
    }

    /** Clicking on 'Click for JS Prompt' button to open alert */
    public void openJSPrompt() {
        log.info("Clicking on 'Click for JS Prompt' button to open alert");
        click(clickForJSPromptButtonLocator);
    }

    /** Switch to alert and get it's message */
    public String getAlertText() {
        Alert alert = switchToAlert();
        String alertText = alert.getText();
        log.info("Alert says: " + alertText);
        return alertText;
    }

    /** Switch to alert and press OK */
    public void acceptAlert() {
        log.info("Switching to alert and pressing OK");
        Alert alert = switchToAlert();
        alert.accept();
    }

    /** Switch to alert and press Cancel */
    public void dismissAlert() {
        log.info("Switching to alert and pressing Cancel");
        Alert alert = switchToAlert();
        alert.dismiss();
    }

    /** Type text into alert and press OK */
    public void typeTextIntoAlert(String text) {
        log.info("Typing text into alert and pressing OK");
        Alert alert = switchToAlert();
        alert.sendKeys(text);
        alert.accept();
    }

    /** Get result text */
    public String getResultText() {
        String result = find(resultTextLocator).getText();
        log.info("Result text: " + result);
        return result;
    }

}
