package com.juaracoding.tajuaracoding.pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class ResetFilterAction implements CompositeAction {
    private boolean completed = false;
    private String result = "";
    
    @Override
    public void execute() {
        try {
            WebDriver driver = DriverUtil.getDriver();

            // Click reset button 
            driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click(); 
            
            Thread.sleep(1000);

            // Ambil value sebelum reset
            String searchValue = driver.findElement(By.id("search")).getAttribute("value");
            String startDateValue = driver.findElement(By.xpath("(//input[@placeholder='Start Date'])[1]")).getAttribute("value");
            String endDateValue = driver.findElement(By.xpath("(//input[@placeholder='End Date'])[1]")).getAttribute("value");

            Assert.assertTrue(searchValue == null || searchValue.isEmpty(), "Search field tidak kosong setelah reset"); 
            Assert.assertTrue(startDateValue == null || startDateValue.isEmpty(), "Start Date tidak kosong setelah reset"); 
            Assert.assertTrue(endDateValue == null || endDateValue.isEmpty(), "End Date tidak kosong setelah reset");
            
            // Klik tombol reset
            WebElement resetButton = driver.findElement(By.xpath("//button[contains(@class, 'MuiButton-containedSecondary')]"));
            resetButton.click();

            Thread.sleep(1000); // tunggu efek reset

            // Cek field Unit Name setelah reset
            String unitNameValue = driver.findElement(By.xpath("//input[@id='job_departement']")).getAttribute("value");

            Assert.assertTrue(unitNameValue.isEmpty(), "Unit Name masih ada value setelah reset: " + unitNameValue);

            completed = true;
            result = "Reset filter completed successfully";

        } catch (Exception e) {
            completed = false;
            result = "Reset filter failed: " + e.getMessage();
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
