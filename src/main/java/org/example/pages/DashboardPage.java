package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage extends BasePage {

    private WebDriver _driver;
    public final String title = "Dashboard";

    public static final By dashboardsXP = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']");

    public DashboardPage(WebDriver driver) {
        super(driver);
        _driver = driver;
    }

    public List<WebElement> getDashboards(){
        return _driver.findElements(dashboardsXP);
    }
}
