package com.herokuapp.theinternet.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BasePageObject {

    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    // open page with given URL
    protected void openUrl(String url){
        driver.get(url);
    }

    // find element using given locator
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    // Find all elements using given locator
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    // click on element with given locator when its visible
    protected void click(By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).click();
    }

    // type given text into element with given locator
    protected void type(String text, By locator) {
        waitForVisibilityOf(locator, 5);
        //click(locator);
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    // get current page URL from a browser
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    //Get title of current page
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    // Get source of current page
    public String getCurrentPageSource() {
        return driver.getPageSource();
    }

    // Switch to iframe using it's locator
    protected void switchToFrame(By frameLocator){
        driver.switchTo().frame(find(frameLocator));
    }

    //Press Key on locator
    protected void pressKey(By locator, Keys key) {
        find(locator).sendKeys(key);
    }

    //Press Key using Actions class
    public void pressKeyWithActions(Keys key) {
        log.info("Pressing " + key.name() + " using Actions class");
        Actions action = new Actions(driver);
        action.sendKeys(key).build().perform();
    }


    // wait for specific ExpectedCondition for the given amount of time in seconds
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    // wait for given number of seconds for element with given locator to be visible on the page
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    // Wait for alert present and then switch to it
    protected Alert switchToAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    //
    public void switchToWindowWithTitle(String expectedTitle) {
        // Switching to new window
        String firstWindow = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> windowsIterator = allWindows.iterator();

        while (windowsIterator.hasNext()) {
            String windowHandle = windowsIterator.next().toString();
            if (!windowHandle.equals(firstWindow)) {
                driver.switchTo().window(windowHandle);
                if (getCurrentPageTitle().equals(expectedTitle)) {
                    break;
                }
            }
        }
    }

    // Perform scroll page to the bottom with JavaScript
    public void scrollToBottom() {
        log.info("Scrolling to the bottom of the page");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // "window.scrollTo(x,y)" scrolls to the particular location
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        // "window.scrollBy(x,y)" scrolls from the current location
    }

    // Perform scroll page to the web element with JavaScript
    public void scrollToWebElement(By locator) {
        log.info("Scrolling to the element " + locator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(locator));

    }

    // Drag 'from' element to 'to' element
    protected void performDragAndDrop(By from, By to) {

         //Actions action = new Actions(driver);
         //action.dragAndDrop(find(from), find(to)).build().perform();

        // if action does not work , can be used following JavaScript
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(
                "function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                        + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
                        + "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                        + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n"
                        + "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                        + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                        + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                        + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n"
                        + "}\n" + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                        + "var dragStartEvent =createEvent('dragstart');\n"
                        + "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n"
                        + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                        + "var dragEndEvent = createEvent('dragend');\n"
                        + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                        + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                        + "simulateHTML5DragAndDrop(source,destination);",
                find(from), find(to));
    }

    //Perform mouse hover over element
    protected void hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }



}
