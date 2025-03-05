package org.example.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {

    private WebDriver _driver;

    private static final By sectionTitleXP = By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");

    public BasePage(WebDriver driver) {
        _driver = driver;
    }

    //Hacer metodo para que obtenga el titulo de la pagina ej: Dashboard, PI, Time, etc
    public WebElement getSectionTitle() {
        return _driver.findElement(sectionTitleXP);
    }
}
