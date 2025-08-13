package com.juaracoding.tajuaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.juaracoding.tajuaracoding.utils.DriverUtil;
import java.time.Duration;

public class BaseTest {
    protected WebDriverWait wait;

    @BeforeMethod
    @Parameters({ "baseURL", "username", "password" })
    public void setup(String baseURL, String username, String password) throws InterruptedException {
        
        // Initialize driver for current thread
        DriverUtil.initializeDriver();
        WebDriver driver = DriverUtil.getDriver();
        
        // Initialize wait for current thread
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            // Navigate to base URL
            driver.get(baseURL);
            
            // Wait for page to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
            
            // Perform login
            WebElement inputUsername = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
            inputUsername.clear();
            inputUsername.sendKeys(username);

            WebElement inputPassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
            inputPassword.clear();
            inputPassword.sendKeys(password);

            WebElement buttonLogin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div[2]/form/button")));
            buttonLogin.click();
            
            // wait.until(ExpectedConditions.urlContains("inventory"));
            
        } catch (Exception e) {

            System.out.println("Setup failed for thread " + Thread.currentThread().getName() + ": " + e.getMessage());
            DriverUtil.quitDriver();
            throw e;
        }
    }
    
    @AfterMethod
    public void teardown() {
        try {
            // Small delay to see results (optional)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Always quit driver for current thread
            DriverUtil.quitDriver();
        }
    }

}
