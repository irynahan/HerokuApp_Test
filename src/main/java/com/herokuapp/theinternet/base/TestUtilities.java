package com.herokuapp.theinternet.base;

import org.testng.annotations.DataProvider;

public class TestUtilities extends BaseTest{

    // sleep for test debugging
    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // static data provider for delivery array of arrays object, to be downloaded
    @DataProvider(name="filesToDownload")
    protected static Object[][] files() {
        return new Object[][] {
                {1,"index.txt"},
                {2,"logo.txt"},
                {3,"text.txt"}
        };
    }

}
