package com.herokuapp.theinternet.dropdowntests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.page.DropdownPage;
import com.herokuapp.theinternet.page.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DropdownTest extends TestUtilities {

    @Parameters({"option"})
    @Test
    public void selectOptionTest(int option){

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        // Click on Dropdown link
        DropdownPage dropdownPage = welcomePage.clickDropdownLink();

        // Select Option 2
        dropdownPage.selectOption(option);

        // Verify option  is selected

        String selectedOption = dropdownPage.getSelectedOption();
        Assert.assertTrue(selectedOption.contains(String.valueOf(option)), "Option is not selected. Instead selected - \" + selectedOption");

    }

}
