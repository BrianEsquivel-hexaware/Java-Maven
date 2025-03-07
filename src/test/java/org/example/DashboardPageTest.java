package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.DashboardPage;
import org.example.utils.ExcelReaderUtils;
import org.openqa.selenium.WebElement;
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

        String filePath = "src/main/java/org/example/utils/DataOrange.xlsx";
        String sheetName = "DataOrange";

        List<String[]> testData = ExcelReaderUtils.readExcel(filePath, sheetName);

        for (String[] row : testData){
            System.out.println(row[0]);
        }

        dashboardPage.logout();
        Thread.sleep(2000);
    }

}
