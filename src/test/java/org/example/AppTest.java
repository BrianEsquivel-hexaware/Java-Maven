package org.example;

import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class AppTest {

    @Test
    public void testApp() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
    }

}
