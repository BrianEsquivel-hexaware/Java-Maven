package org.example.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtils {

    public static String getScreenShotPath(WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File("C:/Users/2000145257/IdeaProjects/ReportsSS/actualSS.png");
        FileUtils.copyFile(srcFile, destFile);
        return destFile.getAbsolutePath();
    }
}
