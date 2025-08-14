package com.juaracoding.tajuaracoding.Login;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.LoginPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;

public class NegativeLogin extends BaseTest {

    @Test
    public void InvalidEmail() throws InterruptedException {
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        // Open the login page
        loginPage.openLoginPage();

        // Input Username and Password
        loginPage.inputUsername("admin123hadir.com");
        loginPage.inputPassword("MagangSQA_JC@123");

        WebElement emailfield = DriverUtil.getDriver().findElement(By.id("email"));
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        String validateMsg = (String) js.executeScript("return arguments[0].validationMessage;", emailfield);

        // Validate Error Message
        if (validateMsg != null && !validateMsg.trim().isEmpty()) {
            System.out.println("HTML5 Email Validate Message: " + validateMsg);
            Assert.assertTrue(validateMsg.toLowerCase().contains("email") || validateMsg.contains("@"),"Expected HTML5 email validation message");
        } else {
            loginPage.clickButton();
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(10));
            WebElement actual = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.errorMessageAccountNotFound));
            String actuals = actual.getText();
            Assert.assertEquals(actuals, "Akun tidak ditemukan");
        }

        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }

    @Test
    public void InvalidPassword(){
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        // Open the login page
        loginPage.openLoginPage();

        // Input Username and Password
        loginPage.inputUsername("admin@hadir.com");
        loginPage.inputPassword("Ampun dah");
        loginPage.clickButton();

        // Validate Error Message
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
        WebElement actual = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.errorMessageAccountNotFound));
        String actuals = actual.getText();
        Assert.assertNotNull(actual);
        Assert.assertEquals(actuals,"Email atau password salah");

        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }



}
