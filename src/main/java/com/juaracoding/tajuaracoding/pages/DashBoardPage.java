package com.juaracoding.tajuaracoding.pages;

import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class DashBoardPage {
    private WebDriver driver;

    // Locator Dashboard
    public By TotalKaryawan = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[1]/div/div[2]/div[1]/p");
    public By TotalAbsen = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[2]/div/div[2]/div[1]/p");
    public By WFH = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[3]/div/div[2]/div[1]/p");
    public By Cuti = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[4]/div/div[2]/div[1]/p");
    public By Sakit = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[5]/div/div[2]/div[1]/p");
    public By Izin = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[6]/div/div[2]/div[1]/p");

    // Locator Pending Dashboard
    public By searchButton = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div/div[1]/div/form/div/div[4]/button");
    public By resetButton = By.xpath("//button[normalize-space()='Reset']");
    private By calendarIcon = By.xpath("//button//*[local-name()='svg' and contains(@class,'feather-calendar')]");
    private By dateButtons = By.xpath("//button[contains(@class, 'rdrDay') and not(contains(@tabindex, '-1'))]");
    private By startDateInput = By.xpath("(//input[@placeholder='Start Date'])[1]");
    private By endDateInput = By.xpath("(//input[@placeholder='End Date'])[1]");
    private By saveButton = By.xpath("(//button[normalize-space()='save'])[1]");
    private By cancelButtonCalendar = By.xpath("(//button[normalize-space()='cancel'])[1]");
    private By dataRows = By.xpath("//table/tbody/tr");

    // Month/Year navigation Calendar


    // Icon Red Filter
    public By filterByUnitButton = By.xpath("//button[contains(@class, 'MuiButton-containedSecondary') and contains(@class, 'MuiButton-sizeMedium')]");
    public By filterByUnitSelect = By.xpath("//input[@id='job_departement']");
    public By applyButton = By.xpath("(//button[normalize-space()='Terapkan'])[1]");
    public By cancelButton = By.xpath("(//button[normalize-space()='Batal'])[1]");
    public By dataNotFoundMessage = By.xpath("//p[contains(text(), 'Data tidak ditemukan')]");

    // Tabel Validator
    // -----Validator 1-------
    // Cuti
    @FindBy(xpath = "(//h2[contains(text(),'Validator 1')]/following::table[@aria-label='simple table'])[1]//tbody/tr")
    public List<WebElement> validator1Cuti;

    // Lembur
    @FindBy(xpath = "(//h2[contains(text(),'Validator 1')]/following::table[@aria-label='simple table'])[2]//tbody/tr")
    public List<WebElement> validator1Lembur;

    // Koreksi
    @FindBy(xpath = "(//h2[contains(text(),'Validator 1')]/following::table[@aria-label='simple table'])[3]//tbody/tr")
    public List<WebElement> validator1Koreksi;

    // -----Validator 1-------
    // Cuti
    @FindBy(xpath = "(//h2[contains(text(),'Validator 2')]/following::table[@aria-label='simple table'])[1]//tbody/tr")
    public List<WebElement> validator2Cuti;

    // Lembur
    @FindBy(xpath = "(//h2[contains(text(),'Validator 2')]/following::table[@aria-label='simple table'])[2]//tbody/tr")
    public List<WebElement> validator2Lembur;

    // Koreksi
    @FindBy(xpath = "(//h2[contains(text(),'Validator 2')]/following::table[@aria-label='simple table'])[3]//tbody/tr")
    public List<WebElement> validator2Koreksi;

    // Cuti
    @FindBy(xpath = "(//h2[contains(text(),'Validator 3')]/following::table[@aria-label='simple table'])[1]//tbody/tr")
    public List<WebElement> validator3Cuti;

    // Lembur
    @FindBy(xpath = "(//h2[contains(text(),'Validator 3')]/following::table[@aria-label='simple table'])[2]//tbody/tr")
    public List<WebElement> validator3Lembur;

    // Koreksi
    @FindBy(xpath = "(//h2[contains(text(),'Validator 3')]/following::table[@aria-label='simple table'])[3]//tbody/tr")
    public List<WebElement> validator3Koreksi;


    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getvalue(By locator) {
        WebElement element = driver.findElement(locator);
        return Integer.parseInt(element.getText().trim());
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickIconCalendar() {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(10));
        WebElement calendaricon = wait.until(ExpectedConditions.elementToBeClickable(calendarIcon));
        calendaricon.click();
    }

    public void selectDateRange(int startIndex, int endIndex) {
        List<WebElement> tanggalList = DriverUtil.getDriver().findElements(dateButtons);
        tanggalList.get(startIndex).click();
        tanggalList.get(endIndex).click();
    }

    public String getStartDateValue() {
        return DriverUtil.getDriver().findElement(startDateInput).getAttribute("value");
    }

    public String getEndDateValue() {
        return DriverUtil.getDriver().findElement(endDateInput).getAttribute("value");
    }

    public void clickSaveCalendar() {
        DriverUtil.getDriver().findElement(saveButton).click();
    }

    public void clickSearch() {
        DriverUtil.getDriver().findElement(searchButton).click();
    }

}
