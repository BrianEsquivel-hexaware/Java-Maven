package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.DashboardPage;
import org.example.utils.PropertyUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.example.utils.ScreenShotUtils.clearFolder;

public class DashboardPageTest extends BaseTest {

    @BeforeTest
    public void setup() throws IOException {
        String basePath = PropertyUtils.getProperty("reports.source");
        ExtentSparkReporter spark = new ExtentSparkReporter(basePath+"DashboardPageTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        clearFolder(PropertyUtils.getProperty("reportSS.source"));
    }

    @Test
    public void testDashboards() throws InterruptedException, IOException {
        test = extent.createTest("Test of the Dashboard Page");
        navigateToApp();
        DashboardPage dashboardPage = new DashboardPage(_driver);
        WebElement title = dashboardPage.getSectionTitle();
        Assert.assertEquals(title.getText(), dashboardPage.title);
        WebDriverWait waitForResults = new WebDriverWait(_driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(DashboardPage.dashboardsXP));
        List<WebElement> dashboardsResult = dashboardPage.getDashboards();
        for (WebElement dashboard : dashboardsResult) {
            Assert.assertFalse(dashboard.getText().isEmpty());
        }

        dashboardPage.logout();
        Thread.sleep(2000);
    }

}
