package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.AdminPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AdminPageTest extends BaseTest {

    @BeforeTest
    public void setup() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter("C:/Users/2000145257/IdeaProjects/Reports/AdminPageTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test
    public void testAdminPage() throws InterruptedException, IOException {
        test = extent.createTest("Test of the Admin Page");
        WebDriver driver = new FirefoxDriver();
        navigateToApp(driver);
        AdminPage adminPage = new AdminPage(driver);
        adminPage.moveToSection(adminPage.title);
        WebElement title = adminPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(adminPage.title));

        adminPage.logout();
        Thread.sleep(2000);
        driver.quit();
    }

}
