package org.example.pages;

import org.example.utils.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class LoginPage extends BasePage{

    private WebDriver _driver;

    public static final By userInputXP = By.xpath("//input[@placeholder='Username']");
    public static final By passwordInputXP = By.xpath("//input[@placeholder='Password']");

    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
        _driver = driver;
    }

    public void login() throws IOException {
        String username = PropertyUtils.getProperty("user.name");
        String password = PropertyUtils.getProperty("user.pass");
        WebElement userInput = _driver.findElement(userInputXP);
        userInput.sendKeys(username);
        WebElement passwordInput = _driver.findElement(passwordInputXP);
        passwordInput.sendKeys(password);
        WebElement submitButton = _driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        submitButton.click();
    }
}
