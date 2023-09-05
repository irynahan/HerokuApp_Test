package com.herokuapp.theinternet.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HorizontalSliderPage extends BasePageObject {

    public HorizontalSliderPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By rangeLocator = By.id("range");
    private By sliderLocator = By.tagName("input");

    private String pageUrl = "http://the-internet.herokuapp.com/horizontal_slider";

    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    /**
     * Move slider to specified value
     */
    public void setSliderTo(double value) {
        log.info("Moving slider to " + value);

        // Move slider normal method
        // Find the xOffset using given value
        // getting width of slider
        int width = find(sliderLocator).getSize().getWidth();
        // where should be moved to in % (max value of slider is 5)
        double percent = value / 5;
        int xOffset = (int) (width * percent);

        // dragAndDropBy brings to the middle of the element, so to go to the start ( - width/2)
        int realMove = xOffset - width/2;

        Actions action = new Actions(driver);
        action.dragAndDropBy(find(sliderLocator), realMove, 0).build().perform();

        // Second method
        // Calculate number of steps
        //int steps = (int) (value / 0.5);

        // Perform steps
        //pressKey(sliderLocator, Keys.ENTER);
        //for (int i = 0; i < steps; i++) {
        //    pressKey(sliderLocator, Keys.ARROW_RIGHT);
        //}
    }

    /**
     * Getting slider value
     */
    public double getSliderValue() {
        double value = Double.parseDouble(find(rangeLocator).getText());
        log.info("Slider value is " + value);
        return value;
    }


}
