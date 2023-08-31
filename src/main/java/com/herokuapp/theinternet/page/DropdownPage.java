package com.herokuapp.theinternet.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePageObject{
    public DropdownPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By dropdown = By.id("dropdown");


    /** This method selects given option from dropdown */
    public void selectOption (int i) {
        log.info("Selecting option " + i + " from dropdown");

        // create object of Select class
        WebElement dropdownElement = find(dropdown);
        Select dropdown = new Select(dropdownElement);

        // select option from dropdown list, there 3 ways:

        // 1 option - with index
        dropdown.selectByIndex(i);

        // 2 option - with parameter value
        //dropdown.selectByValue(String.valueOf(i));

        // 3 option - with visible text;
        //dropdown.selectByVisibleText("Option " + i);

    }

    /** This method returns selected option in dropdown as a string */
    public String getSelectedOption(){
        // create object of Select class
        WebElement dropdownElement = find(dropdown);
        Select dropdown = new Select(dropdownElement);
        // get text of selected option
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        //String selectedOption = dropdownElement.getText();
        log.info(selectedOption + " is selected in dropdown");
        return selectedOption;

    }






}
