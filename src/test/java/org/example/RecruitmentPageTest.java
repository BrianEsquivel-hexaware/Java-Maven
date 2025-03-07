package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.RecruitmentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class RecruitmentPageTest extends BaseTest{

    @BeforeTest
    public void setup() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter("C:/Users/2000145257/IdeaProjects/Reports/RecruitmentPageTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test
    public void testRecruitmentPage() throws InterruptedException, IOException {
        test = extent.createTest("Test of the Recruitment Page");
        WebDriver driver = new FirefoxDriver();
        navigateToApp(driver);
        RecruitmentPage recruitmentPage = new RecruitmentPage(driver);
        recruitmentPage.moveToSection(recruitmentPage.title);
        WebElement title = recruitmentPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(recruitmentPage.title));
        List<WebElement> jobs = recruitmentPage.searchSpecificJob();
        Assert.assertFalse(jobs.isEmpty());

        recruitmentPage.logout();
        Thread.sleep(2000);
        driver.quit();
    }

}
