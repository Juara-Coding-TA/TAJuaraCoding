package com.juaracoding.tajuaracoding.pages.actions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class UnitFilterAction implements CompositeAction {
    private final String unitName;
    private final boolean shouldFindResults;
    private boolean completed = false;
    private String result = "";
    
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
            
            // Input unit name
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='job_departement']")));
            input.click();
            input.sendKeys(unitName);      
            
            Thread.sleep(2000);
            
            // Try to select from dropdown if exists
            List<WebElement> options = driver.findElements(
                By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='" + unitName + "']"));
    
            if (!options.isEmpty()) {
                wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(8));
                wait.until(ExpectedConditions.visibilityOf(options.get(0)));
                options.get(0).click();
                System.out.println("Berhasil klik dropdown: " + unitName);
            } else {
                System.out.println("Option '" + unitName + "' tidak ditemukan, langsung klik Apply");
            }
            
            // Click apply
            driver.findElement(By.xpath("//button[normalize-space()='Terapkan']")).click();
            
            Thread.sleep(2000);
            
            // Verify results
            List<WebElement> dataRows = driver.findElements(By.xpath("//table//tbody//tr"));
            boolean hasData = !dataRows.isEmpty();
            
            if (shouldFindResults) {
                Assert.assertTrue(hasData, "Expected to find data for unit: " + unitName);
                // Verify all rows contain the unit name
                for (WebElement row : dataRows) {
                    Assert.assertTrue(row.getText().contains(unitName), 
                        "Row doesn't contain unit " + unitName + ": " + row.getText());
                }
            } else {
                Assert.assertFalse(hasData, "Expected no data for unit: " + unitName);
            }
            
            completed = true;
            result = "Unit filter applied successfully for: " + unitName + (shouldFindResults ? " (data found)" : " (no data as expected)");
            
        } catch (Exception e) {
            completed = false;
            result = "Unit filter failed: " + e.getMessage();
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
