package com.herokuapp.theinternet.uploadtests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.page.FileUploaderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadTests extends TestUtilities {

    @Test
    public void textUploadTest() {
        log.info("Starting textUploadTest");

        // open File Uploader Page
        FileUploaderPage fileUploaderPage = new FileUploaderPage(driver, log);
        fileUploaderPage.openPage();

        // Select file
        String fileName = "logo.txt";
        fileUploaderPage.selectFile(fileName);

        // Push upload button
        fileUploaderPage.pushUploadButton();

        // Get uploaded files
        String fileNames = fileUploaderPage.getUploadedFilesNames();

        // Verify selected file uploaded
        Assert.assertTrue(fileNames.contains(fileName),
                "Our file (" + fileName + ") is not one of the uploaded (" + fileNames + ")");
    }

    // file upload tests with static data provider
    @Test (dataProvider = "filesToDownload")
    public void fileUploadTest( int testIndex, String fileName) {
        log.info("Starting fileUploadTest " + testIndex + " " + fileName );

        // open File Uploader Page
        FileUploaderPage fileUploaderPage = new FileUploaderPage(driver, log);
        fileUploaderPage.openPage();

        // Select file
        fileUploaderPage.selectFile(fileName);

        // Push upload button
        fileUploaderPage.pushUploadButton();

        // Get uploaded files
        String fileNames = fileUploaderPage.getUploadedFilesNames();

        // Verify selected file uploaded
        Assert.assertTrue(fileNames.contains(fileName),
                "Our file (" + fileName + ") is not one of the uploaded (" + fileNames + ")");
    }



}
