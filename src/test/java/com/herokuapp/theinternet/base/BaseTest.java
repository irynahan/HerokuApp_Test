package com.herokuapp.theinternet.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BaseTest {

    protected WebDriver driver;



    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){

        // create driver
        System.out.println("Create driver: " + browser);

        switch (browser):
        case "chrome":
            System.setProperty("webdriver.chrome.driver", "");

    }

    @Test
    public void printTest() {
        System.out.println("Method is executed");
    }


}
