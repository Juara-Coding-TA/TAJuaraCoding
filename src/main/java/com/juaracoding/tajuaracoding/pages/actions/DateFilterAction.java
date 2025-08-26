package com.juaracoding.tajuaracoding.pages.actions;

import java.util.List;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class DateFilterAction implements CompositeAction {
    private final String month;
    private final int startDateIndex;
    private final int endDateIndex;
    private final String expectedStartDate;
    private final String expectedEndDate;
    private boolean completed = false;
    private String result = "";
    
    public DateFilterAction(String month, int startDateIndex, int endDateIndex, String expectedStartDate, String expectedEndDate) {
        this.month = month;
        this.startDateIndex = startDateIndex;
        this.endDateIndex = endDateIndex;
        this.expectedStartDate = expectedStartDate;
        this.expectedEndDate = expectedEndDate;
    }
    
    @Override
    public void execute() {
        try {
            WebDriver driver = DriverUtil.getDriver();
            
            // Click calendar icon
            driver.findElement(By.xpath("//button//*[local-name()='svg' and contains(@class,'feather-calendar')]")).click();
            
            // Select month if specified
            if (month != null && !month.isEmpty()) {
                WebElement monthDropdown = driver.findElement(By.xpath("//span[@class='rdrMonthPicker']//select"));
                Select select = new Select(monthDropdown);
                select.selectByVisibleText(month);
            }
            
            // Select date range
            List<WebElement> dateButtons = driver.findElements(
                By.xpath("//button[contains(@class, 'rdrDay') and not(contains(@tabindex, '-1'))]"));
            dateButtons.get(startDateIndex).click();
            dateButtons.get(endDateIndex).click();
            
            Thread.sleep(1000);
            
            // Verify selected dates
            String actualStartDate = driver.findElement(By.xpath("(//input[@placeholder='Start Date'])[1]")).getAttribute("value");
            String actualEndDate = driver.findElement(By.xpath("(//input[@placeholder='End Date'])[1]")).getAttribute("value");
            
            Assert.assertTrue(actualStartDate.contains(expectedStartDate), 
                "Start Date tidak sesuai. Expected: " + expectedStartDate + ", Actual: " + actualStartDate);
            Assert.assertTrue(actualEndDate.contains(expectedEndDate), 
                "End Date tidak sesuai. Expected: " + expectedEndDate + ", Actual: " + actualEndDate);
            
            // Save and search
            driver.findElement(By.xpath("(//button[normalize-space()='save'])[1]")).click();
            driver.findElement(By.xpath("//button[.//*[@data-testid='SearchIcon']]")).click();
            
            Thread.sleep(2000);
            
            completed = true;
            result = "Date filter applied successfully: " + actualStartDate + " to " + actualEndDate;
            
        } catch (Exception e) {
            completed = false;
            result = "Date filter failed: " + e.getMessage();
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
