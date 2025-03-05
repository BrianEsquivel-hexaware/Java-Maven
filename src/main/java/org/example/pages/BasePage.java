package org.example.pages;
import org.openqa.selenium.WebDriver;

public class BasePage {

    private WebDriver _driver;

    public BasePage(WebDriver driver) {
        _driver = driver;
    }

    //Hacer metodo para que obtenga el titulo de la pagina ej: Dashboard, PI, Time, etc.
}
