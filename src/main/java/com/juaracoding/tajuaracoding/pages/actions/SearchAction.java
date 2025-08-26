package com.juaracoding.tajuaracoding.pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class SearchAction implements CompositeAction {
    private final String keyword;
    private final boolean shouldFindResults;
    private boolean completed = false;
    private String result = "";
    
    public SearchAction(String keyword, boolean shouldFindResults) {
        this.keyword = keyword;
        this.shouldFindResults = shouldFindResults;
    }
    
    @Override
    public void execute() {
        try {
            WebDriver driver = DriverUtil.getDriver();
            
            // Clear and input search keyword
            WebElement searchField = driver.findElement(By.id("search"));
            searchField.clear();
            searchField.sendKeys(keyword);
            
            // Click search button
            driver.findElement(By.xpath("//button[.//*[@data-testid='SearchIcon']]")).click();
            
            Thread.sleep(2000);
            
            // Verify results based on expectation
            boolean hasResults = !driver.findElements(By.xpath("//table//tbody//tr")).isEmpty();
            boolean dataNotFound = !driver.findElements(By.xpath("//p[contains(text(), 'Data tidak ditemukan')]")).isEmpty();
            
            if (shouldFindResults) {
                if (!hasResults || dataNotFound) {
                    throw new AssertionError("Expected to find results for keyword: " + keyword);
                }
            } else {
                if (hasResults && !dataNotFound) {
                    throw new AssertionError("Expected no results for keyword: " + keyword);
                }
            }
            
            completed = true;
            result = "Search completed for keyword: " + keyword + 
                    (shouldFindResults ? " (results found)" : " (no results as expected)");
            
        } catch (Exception e) {
            completed = false;
            result = "Search failed: " + e.getMessage();
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
