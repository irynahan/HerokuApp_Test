package com.herokuapp.theinternet.jserrortests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.page.JSErrorPage;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class JSErrorTests extends TestUtilities {

    // to find if there are JavaScript errors on the page

    @Test
    public void jsErrorTest() {

        SoftAssert softAssert = new SoftAssert();

        // Open JSErrorPage
        JSErrorPage jSErrorPage = new JSErrorPage(driver, log);
        jSErrorPage.openPage();

        // Get logs
        List<LogEntry> logs = getBrowserLogs();

        // Verifying there are no JavaScript errors in console
        for (LogEntry logEntry : logs) {
            if (logEntry.getLevel().toString().equals("SEVERE")) {
                softAssert.fail("Severe error: " + logEntry.getMessage());
            }
        }
        softAssert.assertAll();
    }

}
