package org.example.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {

    private WebDriver _driver;

    private static final By sectionTitleXP = By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");
    private static final By dropDownMenuXP = By.xpath("//li[@class='oxd-userdropdown']");
    private static final By logoutButtonXP = By.xpath("//a[normalize-space()='Logout']");

    public BasePage(WebDriver driver) {
        _driver = driver;
    }

    public WebElement getSectionTitle() {
        return _driver.findElement(sectionTitleXP);
    }

    public void logout(){
        _driver.findElement(dropDownMenuXP).click();
        _driver.findElement(logoutButtonXP).click();
    }

    public void moveToSection(String sectionTitle) {
        _driver.findElement(By.xpath("//aside[@class='oxd-sidepanel']//li//a//span[text()='"+sectionTitle+"']")).click();
    }
}
