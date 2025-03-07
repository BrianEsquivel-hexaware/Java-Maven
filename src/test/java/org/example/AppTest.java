package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.AdminPage;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.pages.RecruitmentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AppTest extends BaseTest {

    @Test
    public void testDashboard() throws InterruptedException, IOException {
        test = extent.createTest("Test Dashboard");
        WebDriver driver = new FirefoxDriver();
        navigateToApp(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        WebElement title = dashboardPage.getSectionTitle();
        Assert.assertEquals(title.getText(), dashboardPage.title);
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(DashboardPage.dashboardsXP));
        List<WebElement> dashboardsResult = dashboardPage.getDashboards();
        for (WebElement dashboard : dashboardsResult) {
            Assert.assertFalse(dashboard.getText().isEmpty());
        }

        dashboardPage.logout();
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void testAdminPage() throws InterruptedException, IOException {
        test = extent.createTest("Test Admin Page");
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

    @Test
    public void testRecruitmentPage() throws InterruptedException, IOException {
        test = extent.createTest("Test Recruitment Page");
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
