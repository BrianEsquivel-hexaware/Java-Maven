package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.io.IOException;

public class BaseTest {

    ExtentReports extent;
    ExtentTest test;

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

    @AfterTest
    public void teardown(){
        extent.flush();
    }

    public void navigateToApp(WebDriver driver) throws InterruptedException, IOException {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        Thread.sleep(2000);
    }
}
