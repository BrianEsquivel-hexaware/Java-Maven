package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class RecruitmentPage extends BasePage {

    private WebDriver _driver;
    public final String title = "Recruitment";

    public static final By vacanciesLink = By.xpath("//a[normalize-space()='Vacancies']");
    public static final By jobTitle = By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]");
    public static final By jobSpecificTitleDiv = By.xpath("//div[@role='listbox']");
    public static final By submitButton = By.xpath("//button[@type='submit']");
    public static final By vacanciesDiv = By.xpath("//div[@class='oxd-table-card']");
    public static final By noRecordsXP = By.xpath("//span[normalize-space()='No Records Found']");
    public static final By addBtnXP = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
    public static final By addHeaderXP = By.xpath("//h6[normalize-space()='Add Vacancy']");
    public static final By vacancyNameInputXP = By.xpath("//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/form[@class='oxd-form']/div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[@class='oxd-grid-item oxd-grid-item--gutters']/div[@class='oxd-input-group oxd-input-field-bottom-space']/div/input[1]");
    public static final By addJobTitleDiv = By.xpath("//div[@class='oxd-select-text--after']");
    public static final By managerInputXP = By.xpath("//input[@placeholder='Type for hints...']");
    public static final By amountInputXP = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");
    public static final By saveButtonXP = By.xpath("//button[normalize-space()='Save']");
    public static final By vacancyDropXP = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]");



    public RecruitmentPage(WebDriver driver) {
        super(driver);
        _driver = driver;
    }

    public By xpathBySpecificTitle(String jobTitle){
        return By.xpath("//*[normalize-space()='"+jobTitle+"']");
    }

    public void searchSpecificJob(String jobTitleText) throws InterruptedException {
        _driver.findElement(vacanciesLink).click();
        Thread.sleep(2000);
        _driver.findElement(jobTitle).click();
        _driver.findElement(jobSpecificTitleDiv).findElement(xpathBySpecificTitle(jobTitleText)).click();
        _driver.findElement(submitButton).click();
        Thread.sleep(2000);
    }

    public List<WebElement> returnJobsList(String jobTitleText) throws InterruptedException {
        searchSpecificJob(jobTitleText);
        try{
            return _driver.findElements(vacanciesDiv);
        }
        catch(Exception e){
            return Collections.emptyList();
        }
    }

    public boolean noRecordsFoundSearch(String jobTitleText) throws InterruptedException {
        searchSpecificJob(jobTitleText);
        try {
            _driver.findElement(noRecordsXP);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void addVacancy(String vacancyName, String jobTitleText, String managerName, String vacAmount) throws InterruptedException {
        _driver.findElement(vacanciesLink).click();
        Thread.sleep(2000);
        _driver.findElement(addBtnXP).click();
        WebDriverWait waitForResults = new WebDriverWait(_driver, Duration.ofSeconds(10));
        waitForResults.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(addHeaderXP));
        WebElement vacancyInput = _driver.findElement(vacancyNameInputXP);
        vacancyInput.sendKeys(vacancyName);
        _driver.findElement(addJobTitleDiv).click();
        _driver.findElement(jobSpecificTitleDiv).findElement(xpathBySpecificTitle(jobTitleText)).click();
        WebElement managerInput = _driver.findElement(managerInputXP);
        managerInput.sendKeys(managerName);
        managerInput.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(3000);
        _driver.findElement(jobSpecificTitleDiv).findElement(xpathBySpecificTitle(managerName)).click();
        WebElement amountInput = _driver.findElement(amountInputXP);
        amountInput.sendKeys(vacAmount);
        _driver.findElement(saveButtonXP).click();
        Thread.sleep(3000);
    }

    public void searchSpecificVacancy(String vacancyText) throws InterruptedException {
        _driver.findElement(vacancyDropXP).click();
        _driver.findElement(jobSpecificTitleDiv).findElement(xpathBySpecificTitle(vacancyText)).click();
        _driver.findElement(submitButton).click();
        Thread.sleep(2000);
    }

    public List<WebElement> returnVacancyList(String vacancyText) throws InterruptedException {
        searchSpecificVacancy(vacancyText);
        try{
            return _driver.findElements(vacanciesDiv);
        }
        catch(Exception e){
            return Collections.emptyList();
        }
    }
}
