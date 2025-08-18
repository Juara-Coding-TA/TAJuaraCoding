package com.juaracoding.tajuaracoding.pages;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.juaracoding.tajuaracoding.utils.DriverUtil;
import java.time.Duration;

public class IzinTerlambatPage {
    private WebDriver driver;
    private WebDriverWait wait; 

    // Locators
    public By lastPageButton = By.xpath("//button[@title='Go to last page']");
    public By searchField = By.id("search");
    public By searchButton = By.xpath("//button[.//*[@data-testid='SearchIcon']]");
    public By resetButton = By.xpath("//button[normalize-space()='Reset']");
    public By table = By.xpath("(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-true MuiGrid-grid-lg-12 css-1a249as'])[2]");

    // Locator
    private By calendarIcon = By.xpath("//button//*[local-name()='svg' and contains(@class,'feather-calendar')]");
    private By dateButtons = By.xpath("//button[contains(@class, 'rdrDay') and not(contains(@tabindex, '-1'))]");
    private By startDateInput = By.xpath("(//input[@placeholder='Start Date'])[1]");
    private By endDateInput = By.xpath("(//input[@placeholder='End Date'])[1]");
    private By saveButton = By.xpath("(//button[normalize-space()='save'])[1]");
    private By cancelButtonCalendar = By.xpath("(//button[normalize-space()='cancel'])[1]");
    private By dataRows = By.xpath("//table/tbody/tr");

    // Month/Year navigation Calendar
    public By monthDropdown = By.xpath("//span[@class='rdrMonthPicker']//select"); 
    public By yearDropdown = By.xpath("//div[@role='dialog']//select[2]");
    public By leftArrow = By.xpath("(//button[@class='rdrNextPrevButton rdrPprevButton'])[1]");
    public By rightArrow = By.xpath("(//button[@class='rdrNextPrevButton rdrNextButton'])[1]");

    // Icon Red Filter
    public By filterByUnitButton = By.xpath("//button[contains(@class, 'MuiButton-containedSecondary') and contains(@class, 'MuiButton-sizeMedium')]");
    public By filterByUnitSelect = By.xpath("//input[@id='job_departement']");
    public By applyButton = By.xpath("(//button[normalize-space()='Terapkan'])[1]");
    public By cancelButton = By.xpath("(//button[normalize-space()='Batal'])[1]");
    public By dataNotFoundMessage = By.xpath("//p[contains(text(), 'Data tidak ditemukan')]");

    public IzinTerlambatPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Get status list from STATUS column
    public List<String> getStatusList() {
        List<WebElement> headers = driver.findElements(By.xpath("//table//thead//th"));
        int statusColIndex = -1;

        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase("STATUS")) {
                statusColIndex = i + 1;
                break;
            }
        }

        if (statusColIndex == -1) {
            throw new RuntimeException("Kolom STATUS tidak ditemukan!");
        }

        return driver.findElements(
            By.xpath("//table//tbody//tr//td[" + statusColIndex + "]//*[self::span or self::div]"))
            .stream().map(element -> element.getText().trim().split("\\s+")[0])
            .collect(Collectors.toList());
        }

        public List<String> getUserNameList() {
            return driver.findElements(By.xpath("//table//tbody//tr//td[2]")).stream().map(WebElement::getText).collect(Collectors.toList());
        }

        public boolean isDataNotFoundMessageDisplayed() {
            try {
                return driver.findElement(dataNotFoundMessage).isDisplayed();
            } catch (org.openqa.selenium.NoSuchElementException e) {
                return false;
            }
        }

        public void printTableContents() {
            System.out.println("--- Table Contents ---");
            List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));
            if (rows.isEmpty()) {
                System.out.println("No rows found in the table body.");
                return;
            }
            for (int i = 0; i < rows.size(); i++) {
                List<WebElement> cells = rows.get(i).findElements(By.xpath(".//td"));
                String rowContent = "Row " + (i + 1) + ": ";
                for (int j = 0; j < cells.size(); j++) {
                    rowContent += cells.get(j).getText() + (j < cells.size() - 1 ? " | " : "");
                }
                System.out.println(rowContent);
            }
            System.out.println("----------------------");
        }

        public void clickLastPage() {
            WebElement lastPage = driver.findElement(lastPageButton);
            if (lastPage.isEnabled()) {
                lastPage.click();
            } else {
                System.out.println("Last page button is not enabled.");
            }
        }

        public void search(String keyword) {
            WebElement searchInput = driver.findElement(searchField);
            searchInput.clear();
            searchInput.sendKeys(keyword);
            driver.findElement(searchButton).click();
        }

        public void searchButton() {
            driver.findElement(searchButton).click();
        }

        public void clickReset() {
            driver.findElement(resetButton).click();
        }

        public String getCurrentUrl() {
            return driver.getCurrentUrl();
        }

        public String getPageTitle() {
            return driver.getTitle();
        }

        public By unitOption(String unit) {
            return By.xpath("//div[contains(@class,'dropdown') or contains(@class,'option')][normalize-space(text())='" + unit + "']");
        }
        
        public void clickApply() {
            driver.findElement(applyButton).click();
        }

        public void clickCancel() {
            driver.findElement(cancelButton).click();
        }

        public void clickIconCalendar() {
            DriverUtil.getDriver().findElement(calendarIcon).click();
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

        public void clickCancelCalendar() {
            DriverUtil.getDriver().findElement(cancelButtonCalendar).click();
        }

        public void clickSearch() {
            DriverUtil.getDriver().findElement(searchButton).click();
        }

        public List<WebElement> getDataList() {
            return DriverUtil.getDriver().findElements(dataRows);
        }

        public void clickFilterUnit() {
            DriverUtil.getDriver().findElement(filterByUnitButton).click();
        }

        public Collection<String> getDataRows() {
            return DriverUtil.getDriver().findElements(dataRows).stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }

        public void selectMonth(String month) {
            WebElement dropdown = driver.findElement(monthDropdown);
            Select select = new Select(dropdown);
            select.selectByVisibleText(month); 
}
       
}
