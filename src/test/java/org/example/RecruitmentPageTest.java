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
    public void checksForVacancies_available() throws InterruptedException, IOException {
        test = extent.createTest("Test for a vacancy availability");
        navigateToApp();
        RecruitmentPage recruitmentPage = new RecruitmentPage(_driver);
        recruitmentPage.moveToSection(recruitmentPage.title);
        WebElement title = recruitmentPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(recruitmentPage.title));
        List<WebElement> jobs = recruitmentPage.returnJobsList("Sales Representative");
        Assert.assertFalse(jobs.isEmpty());

        recruitmentPage.logout();
        Thread.sleep(2000);
    }

    @Test
    public void checksForVacancies_notAvailable() throws InterruptedException, IOException {
        test = extent.createTest("Test for a vacancy not-available");
        navigateToApp();
        RecruitmentPage recruitmentPage = new RecruitmentPage(_driver);
        recruitmentPage.moveToSection(recruitmentPage.title);
        WebElement title = recruitmentPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(recruitmentPage.title));
        Assert.assertTrue(recruitmentPage.noRecordsFoundSearch("qwer"));

        recruitmentPage.logout();
        Thread.sleep(2000);
    }

    @Test
    public void addsNewVacancyAndSearches_Available() throws InterruptedException, IOException {
        test = extent.createTest("Creates a vacancy and searches for it");
        navigateToApp();
        RecruitmentPage recruitmentPage = new RecruitmentPage(_driver);
        recruitmentPage.moveToSection(recruitmentPage.title);
        WebElement title = recruitmentPage.getSectionTitle();
        Assert.assertTrue(title.getText().contains(recruitmentPage.title));
        recruitmentPage.addVacancy("Astronauta", "IT Manager", "Timothy Lewis Amiano", "1");
        List<WebElement> vacancies = recruitmentPage.returnVacancyList("Astronauta");
        Assert.assertFalse(vacancies.isEmpty());

        recruitmentPage.logout();
        Thread.sleep(2000);

    }

}
