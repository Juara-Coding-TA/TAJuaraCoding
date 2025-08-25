package com.juaracoding.tajuaracoding.pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class PageVerificationAction implements CompositeAction {
    private final String expectedUrlFragment;
    private final String[] requiredElements;
    private boolean completed = false;
    private String result = "";
    
    public PageVerificationAction(String expectedUrlFragment, String... requiredElements) {
        this.expectedUrlFragment = expectedUrlFragment;
        this.requiredElements = requiredElements;
    }
    
    @Override
    public void execute() {
        try {
            WebDriver driver = DriverUtil.getDriver();
            String currentUrl = driver.getCurrentUrl();
            
            // Verify URL
            Assert.assertTrue(currentUrl.contains(expectedUrlFragment), 
                "URL tidak sesuai! Expected: " + expectedUrlFragment + ", Actual: " + currentUrl);
            
            // Verify required elements
            for (String elementType : requiredElements) {
                verifyElement(driver, elementType);
            }
            
            completed = true;
            result = "Page verification completed successfully for " + expectedUrlFragment;
            
        } catch (Exception e) {
            completed = false;
            result = "Page verification failed: " + e.getMessage();
        }
    }
    
    private void verifyElement(WebDriver driver, String elementType) {
        By locator = getLocatorByType(elementType);
        Assert.assertTrue(driver.findElement(locator).isDisplayed(), 
            elementType + " tidak ditemukan!");
    }
    
    private By getLocatorByType(String elementType) {
        switch (elementType.toLowerCase()) {
            case "search-field":
                return By.id("search");
            case "search-button":
                return By.xpath("//button[.//*[@data-testid='SearchIcon']]");
            case "reset-button":
                return By.xpath("//button[normalize-space()='Reset']");
            case "filter-button":
                return By.xpath("//button[contains(@class, 'MuiButton-containedSecondary')]");
            case "start-date":
                return By.xpath("//input[@placeholder='Start Date']");
            case "end-date":
                return By.xpath("//input[@placeholder='End Date']");
            case "calendar-icon":
                return By.xpath("//button//*[local-name()='svg' and contains(@class,'feather-calendar')]");
            default:
                throw new IllegalArgumentException("Unknown element type: " + elementType);
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
