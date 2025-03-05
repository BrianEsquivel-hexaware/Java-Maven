package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.AdminPage;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.pages.RecruitmentPage;
import org.example.utils.PropertyUtils;
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

import java.time.Duration;
import java.util.List;

import static org.example.utils.PropertyUtils.getUserApp;
import static org.example.utils.PropertyUtils.getUserPass;

public class AppTest {

    ExtentReports extent;
    ExtentTest test;

    private void navigateToApp(WebDriver driver) throws InterruptedException {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        Thread.sleep(2000);
    }

    @BeforeTest
    public void setup(){
        ExtentSparkReporter spark = new ExtentSparkReporter("src/main/java/org/example/resources/application/TestsReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if(result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, "Successfully passed");
        } else if (result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL, "Test failed in: " + result.getThrowable().getMessage());
        } else if (result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP, "Skipped test");
        }
    }

    @Test
    public void testDashboard() throws InterruptedException {
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
    public void testAdminPage() throws InterruptedException {
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
    public void testRecruitmentPage() throws InterruptedException {
        test = extent.createTest("Test Recruitment Page");
        WebDriver driver = new FirefoxDriver();
        navigateToApp(driver);
        RecruitmentPage recruitmentPage = new RecruitmentPage(driver);
        recruitmentPage.moveToSection(recruitmentPage.title);
        WebElement title = recruitmentPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(recruitmentPage.title));
        recruitmentPage.logout();
        Thread.sleep(2000);
        driver.quit();
    }

    @AfterTest
    public void teardown(){
        extent.flush();
    }

}
