package com.juaracoding.tajuaracoding.pages.actions;

import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UnitFilterAction implements CompositeAction {
    private final String unitName;
    private final boolean shouldFindResults;

    private boolean completed;
    private String result;

    public UnitFilterAction(String unitName, boolean shouldFindResults) {
        this.unitName = unitName;
        this.shouldFindResults = shouldFindResults;
    }

    @Override
    public void execute() {
        try {
            WebDriver driver = DriverUtil.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Click filter button 
            driver.findElement(By.xpath("//button[contains(@class, 'MuiButton-containedSecondary')]")).click(); 
            
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Klik input unit filter
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@id='job_departement']")));
            input.click();
            input.sendKeys(unitName); 
            Thread.sleep(1500);

            // Pilih option di dropdown kalau ada
            List<WebElement> options = driver.findElements(By.xpath(
                "//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='" + unitName + "']"
            ));
    
            if (!options.isEmpty()) {
                wait.until(ExpectedConditions.visibilityOf(options.get(0)));
                options.get(0).click();
                System.out.println("Klik dropdown: " + unitName);
            } else {
                System.out.println("Option '" + unitName + "' tidak ditemukan, lanjut klik Terapkan");
            }

            // Klik tombol Terapkan
            driver.findElement(By.xpath("//button[normalize-space()='Terapkan']")).click();
            Thread.sleep(2000);

            verifyResults();

            completed = true;
            result = "Unit filter berhasil untuk: " + unitName;

        } catch (Exception e) {
            completed = false;
            result = "Unit filter gagal untuk: " + unitName + " | Error: " + e.getMessage();
        }
    }

    private void verifyResults() {
        WebDriver driver = DriverUtil.getDriver();
        List<WebElement> rows = driver.findElements(By.xpath("//table//tbody/tr"));

        if (shouldFindResults && rows.isEmpty()) {
            throw new RuntimeException("Hasil filter kosong padahal seharusnya ada data untuk: " + unitName);
        }

        if (!shouldFindResults && !rows.isEmpty()) {
            throw new RuntimeException("Hasil filter ada padahal seharusnya kosong untuk: " + unitName);
        }
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String getResult() {
        return result;
    }
}
