package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage extends BasePage {

    private WebDriver _driver;
    public final String title = "Admin";

    public AdminPage(WebDriver driver) {
        super(driver);
        _driver = driver;
    }
}
