package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RecruitmentPage extends BasePage {

    private WebDriver _driver;
    public final String title = "Recruitment";

    public RecruitmentPage(WebDriver driver) {
        super(driver);
        _driver = driver;
    }
}
