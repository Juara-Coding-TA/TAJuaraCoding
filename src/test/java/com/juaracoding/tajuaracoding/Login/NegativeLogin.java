package com.juaracoding.tajuaracoding.Login;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.LoginPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;

public class NegativeLogin extends BaseTest {

    @Test
    public void InvalidEmail() {
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        // Open the login page
        loginPage.openLoginPage();

        // Input Username and Password
        loginPage.inputUsername("admin123@gmail.com");
        loginPage.inputPassword("MagangSQA_JC@123");
        loginPage.clickButton();

        // Validate Error Message
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
        WebElement actual = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.errorMessageAccountNotFound));
        String actuals = actual.getText();
        Assert.assertNotNull(actual);
        Assert.assertEquals(actuals,"Akun tidak ditemukan");

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
