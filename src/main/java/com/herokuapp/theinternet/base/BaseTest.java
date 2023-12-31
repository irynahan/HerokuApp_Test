package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Listeners({com.herokuapp.theinternet.base.TestListener.class})

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;

    // parameters for the path of takeScreenshot method in Test Utilities
    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    @Parameters({"browser", "chromeProfile", "deviceName"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser, @Optional String profile, @Optional String deviceName, ITestContext ctx) {

        // ctx - context is used to get current test name for log acc. Xml file;
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        //
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        if (profile != null) {
            driver = factory.createChromeWithProfile(profile);
        } else if (deviceName != null) {
            driver = factory.createChromeWithMobileEmulation(deviceName);
        } else {
            driver = factory.createDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        this.testSuiteName = ctx.getSuite().getName();
        this.testName = testName;
        this.testMethodName = method.getName();

    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        // close browser
        log.info("Close driver");
        driver.quit();

    }

}




