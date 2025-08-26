package com.juaracoding.tajuaracoding.pages.dashboard;

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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DashBoardPage {
    private WebDriver driver;
    private WebDriverWait wait;


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
    public By calendarIcon = By.xpath("//button//*[local-name()='svg' and contains(@class,'feather-calendar')]");
    public By dateButtons = By.xpath("//button[contains(@class, 'rdrDay') and not(contains(@tabindex, '-1'))]");
    public By startDateInput = By.xpath("(//input[@placeholder='Start Date'])[1]");
    public By endDateInput = By.xpath("(//input[@placeholder='End Date'])[1]");
    public By saveButton = By.xpath("(//button[normalize-space()='save'])[1]");
    public By cancelButtonCalendar = By.xpath("(//button[normalize-space()='cancel'])[1]");
    public By dataRows = By.xpath("//tbody/tr");
    public By filterByModal = By.xpath("/html/body/div[3]/div[3]/div");
    public By monthDropdown = By.xpath("//span[@class='rdrMonthPicker']//select");
    public By yearDropdown = By.xpath("//span[@class='rdrYearPicker']//select");

    // Locator Pending Dashboard Validator 1
    public By v1search = By.xpath("//*[@id=\"search\"]");
    public By v1buttonsearch = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div/button[2]");
    public By v1buttonreset  = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div/button[1]");


    // Icon Red Filter
    public By filterByUnitButton = By.xpath("//button[contains(@class, 'MuiButton-containedSecondary') and contains(@class, 'MuiButton-sizeMedium')]");
    public By filterByUnitSelect = By.xpath("//input[@id='job_departement']");
    public By applyButton = By.xpath("(//button[normalize-space()='Terapkan'])[1]");
    public By cancelButton = By.xpath("(//button[normalize-space()='Batal'])[1]");
    public By dataNotFoundMessage = By.xpath("//p[contains(text(), 'Data tidak ditemukan')]");


    // Tabel Validator
    @FindBy(xpath = "//*[@id=\"__next\"]/div/div/div/div[1]/div/div[1]/div/div[2]/div/table/tbody")
    public List<WebElement> v1data;

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

    // -----Validator 2-------
    // Cuti
    @FindBy(xpath = "(//h2[contains(text(),'Validator 2')]/following::table[@aria-label='simple table'])[1]//tbody/tr")
    public List<WebElement> validator2Cuti;

    // Lembur
    @FindBy(xpath = "(//h2[contains(text(),'Validator 2')]/following::table[@aria-label='simple table'])[2]//tbody/tr")
    public List<WebElement> validator2Lembur;

    // Koreksi
    @FindBy(xpath = "(//h2[contains(text(),'Validator 2')]/following::table[@aria-label='simple table'])[3]//tbody/tr")
    public List<WebElement> validator2Koreksi;

    // -----Validator 3-------
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

    public void clickuplinerlembur(String upliner){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = String.format("//p[normalize-space()='Lembur']/following::table[@aria-label='simple table'][1]//a[h6[contains(text(),'%s')]]", upliner);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        link.click();
    }

    public void clickuplinercuti(String upliner){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = String.format("//p[normalize-space()='Cuti']/following::table[@aria-label='simple table'][1]//a[h6[contains(text(),'%s')]]", upliner);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        link.click();
    }

    public boolean getDataCutiList(String upliner) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dataRows));
        List<WebElement> rows = driver.findElements(dataRows);

        for (WebElement row : rows){
            String rowText = row.getText().trim();
            if(rowText.toLowerCase().contains(upliner.toLowerCase().trim())){
                return true;
            }
        }
        return false;
    }

    public boolean getDataLemburList(String upliner) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dataRows));
        List<WebElement> rows = driver.findElements(dataRows);

        for (WebElement row : rows){
            String rowText = row.getText().trim();
            if(rowText.toLowerCase().contains(upliner.toLowerCase().trim())){
                return true;
            }
        }
        return false;
    }

    public void clickuplinerkoreksi(String upliner){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = String.format("//p[normalize-space()='Koreksi']/following::table[@aria-label='simple table'][1]//a[h6[contains(text(),'%s')]]", upliner);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        link.click();
    }

    public boolean getDataKoreksiList(String upliner) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dataRows));
        List<WebElement> rows = driver.findElements(dataRows);

        for (WebElement row : rows){
            String rowText = row.getText().trim();
            if(rowText.toLowerCase().contains(upliner.toLowerCase().trim())){
                return true;
            }
        }
        return false;
    }

    public Collection<String> getDataRows() {
        return DriverUtil.getDriver().findElements(dataRows).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickFilterUnit(){
        DriverUtil.getDriver().findElement(filterByUnitButton).click();
    }

    public void clickApply(){
        DriverUtil.getDriver().findElement(applyButton).click();
    }

    public String getMonthText() {
        WebElement dropdown = DriverUtil.getDriver().findElement(monthDropdown);
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void selectMonth(String month) {
        WebElement dropdown = driver.findElement(monthDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(month);
    }

    public void clickReset(){
        DriverUtil.getDriver().findElement(resetButton).click();
    }

    public void clickCancel(){
        DriverUtil.getDriver().findElement(cancelButton).click();
    }

    public String getYearText(){
        WebElement droprown = DriverUtil.getDriver().findElement(yearDropdown);
        Select select = new Select(droprown);
        return select.getFirstSelectedOption().getText();
    }

    public void selectYear(String year){
        WebElement dropdown = driver.findElement(yearDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(year);
    }

    public void v1search(String value) {
        WebElement input = driver.findElement(v1search);
        input.clear();
        input.sendKeys(value);
        driver.findElement(v1buttonsearch).click();
    }

    public void v1resetbutton(){
        DriverUtil.getDriver().findElement(v1buttonreset).click();
    }

    public List < String > getUserNameList() {
        return driver.findElements(By.xpath("//table//tbody//tr//td[1]")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean isDataNotFoundMessageDisplayed() {
        try {
            return driver.findElement(dataNotFoundMessage).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public List < WebElement > getDataList() {
        return DriverUtil.getDriver().findElements(dataRows);
    }

}