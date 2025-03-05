package org.example;

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
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.example.utils.PropertyUtils.getUserApp;
import static org.example.utils.PropertyUtils.getUserPass;

public class AppTest {

    private void navigateToApp(WebDriver driver) throws InterruptedException {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        Thread.sleep(2000);
    }

    @Test
    public void testDashboard() throws InterruptedException {
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

}
