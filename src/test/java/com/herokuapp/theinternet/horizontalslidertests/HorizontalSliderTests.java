package com.herokuapp.theinternet.horizontalslidertests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.page.HorizontalSliderPage;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HorizontalSliderTests extends TestUtilities {

    @Parameters({"value"})
    @Test
    public void sliderTest(double value) {

        // Open HorizontalSliderPage
        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(driver, log);
        horizontalSliderPage.openPage();

        // set slider value
        sleep(2000);
        horizontalSliderPage.setSliderTo(value);
        sleep(2000);

        // Verify slider value
        double sliderValue = horizontalSliderPage.getSliderValue();
        Assert.assertEquals(sliderValue, value,  "Range is not correct. It is: " + sliderValue);

    }

}
