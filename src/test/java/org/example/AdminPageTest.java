package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.AdminPage;
import org.example.utils.PropertyUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.example.utils.ScreenShotUtils.clearFolder;

public class AdminPageTest extends BaseTest {

    @BeforeTest
    public void setup() throws IOException {
        String basePath = PropertyUtils.getProperty("reports.source");
        ExtentSparkReporter spark = new ExtentSparkReporter(basePath+"AdminPageTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        clearFolder(PropertyUtils.getProperty("reportSS.source"));
    }

    @Test
    public void testAdminPage() throws InterruptedException, IOException {
        test = extent.createTest("Test of the Admin Page");
        navigateToApp();
        AdminPage adminPage = new AdminPage(_driver);
        adminPage.moveToSection(adminPage.title);
        WebElement title = adminPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(adminPage.title));

        adminPage.logout();
        Thread.sleep(2000);
    }

}
