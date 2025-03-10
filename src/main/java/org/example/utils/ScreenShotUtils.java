package org.example.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ScreenShotUtils {

    public static String getScreenShotPath(WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        Random rand = new Random();
        File destFile = new File("C:/Users/2000145257/IdeaProjects/ReportsSS/"+rand.toString()+".png");
        FileUtils.copyFile(srcFile, destFile);
        return destFile.getAbsolutePath();
    }
}
