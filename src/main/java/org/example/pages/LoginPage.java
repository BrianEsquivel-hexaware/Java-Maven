package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private WebDriver _driver;

    public static final By userInputXP = By.xpath("//input[@placeholder='Username']");
    public static final By passwordInputXP = By.xpath("//input[@placeholder='Password']");

    public LoginPage(WebDriver driver) {
        super(driver);
        _driver = driver;
    }

    public void login() {
        WebElement userInput = _driver.findElement(userInputXP);
        userInput.sendKeys("Admin");
        WebElement passwordInput = _driver.findElement(passwordInputXP);
        passwordInput.sendKeys("admin123");
        WebElement submitButton = _driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        submitButton.click();
    }
}
