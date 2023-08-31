package com.herokuapp.theinternet.page;

import com.herokuapp.theinternet.base.TestUtilities;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class EditorPage extends BasePageObject {
    public EditorPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By frame = By.tagName("iframe");
    private By editorLocator = By.id("tinymce");

    /** Get text from TinyMCE WYSIWYG Editor */
    public String getEditorText() {
        switchToFrame(frame);
        String text = find(editorLocator).getText();
        log.info("Text from TinyMCE WYSIWYG Editor: " + text);
        return text;
    }

}
