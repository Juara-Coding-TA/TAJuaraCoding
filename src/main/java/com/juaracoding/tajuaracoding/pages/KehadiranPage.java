package com.juaracoding.tajuaracoding.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KehadiranPage {
    
    private WebDriver driver;

    //locators and methods for Semua page can be added here
    public By lastPageButton = By.xpath("//div[@class=MuiTypography-root MuiTypography-body1 css-1kei35f']");
    public By searchField = By.id("search");
    public By searchButton = By.xpath("//button[.//*[@data-testid='SearchIcon']]");
    public By resetButton = By.xpath("//button[normalize-space()='Reset']");    
    public By startDateField = By.xpath("//input[@placeholder='Start Date']");
    public By startDateIcon = By.xpath("//button//*[local-name()='svg' and contains(@class,'feather-calendar')]");
    public By endDateField = By.xpath("//input[@placeholder='End Date']"); 
    public By endDateIcon = By.xpath("//button//*[local-name()='svg' and contains(@class,'feather-calendar')]");
    public By filterByUnitButton = By.xpath("//button[contains(@class, 'MuiButton-containedSecondary') and contains(@class, 'MuiButton-sizeMedium')]");
    public By filterByUnitSelect = By.xpath("//input[@id='job_departement']");    
    public By terapkanButton = By.xpath("//button[normalize-space()='Terapkan']");
    public By batalButton = By.xpath("//button[normalize-space()='Batal']");  

    public KehadiranPage(WebDriver driver) {
        this.driver = driver;
    }

    //get status list from STATUS column
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
                .stream()
                .map(element -> element.getText().trim().split("\\s+")[0]) // Ambil kata pertama
                .collect(Collectors.toList());
        
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

    public void clickReset() {
        driver.findElement(resetButton).click();
    }

    public void setStartDate(String date) {
        WebElement startDateInput = driver.findElement(startDateField);
        startDateInput.clear();
        startDateInput.sendKeys(date);
        driver.findElement(startDateIcon).click();
    }

    public void setEndDate(String date) {
        WebElement endDateInput = driver.findElement(endDateField);
        endDateInput.clear();
        endDateInput.sendKeys(date);
        driver.findElement(endDateIcon).click();
    }

    public void clickStartDateIcon() {
        driver.findElement(startDateIcon).click();
    }

    public void clickEndDateIcon() {
        driver.findElement(endDateIcon).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void selectUnit(String unitName) {
        WebElement unitSelect = driver.findElement(filterByUnitSelect);
        unitSelect.click();
        List<WebElement> options = driver.findElements(By.xpath("//li[contains(@class, 'MuiMenuItem-root')]"));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(unitName)) {
                option.click();
                break;
            }
        }
    }

    public void clickTerapkan() {
        driver.findElement(terapkanButton).click();
    }

    public void clickBatal() {
        driver.findElement(batalButton).click();
    }



}
