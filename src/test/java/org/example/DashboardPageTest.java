package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.AdminPage;
import org.example.pages.DashboardPage;
import org.example.pages.RecruitmentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class DashboardPageTest extends BaseTest {

    @BeforeTest
    public void setup() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter("C:/Users/2000145257/IdeaProjects/Reports/DashboardPageTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test
    public void testDashboards() throws InterruptedException, IOException {
        test = extent.createTest("Test of the Dashboard Page");
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

}
