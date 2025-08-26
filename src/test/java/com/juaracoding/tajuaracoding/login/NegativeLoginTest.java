package com.juaracoding.tajuaracoding.login;

import com.juaracoding.tajuaracoding.pages.login.LoginPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTest {

    @Test
    public void InvalidEmail() {
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
        // Langsung pakai loginPage dari BaseTest
        loginPage.openLoginPage();

        loginPage.inputUsername("adminhadir.com");
        loginPage.inputPassword("MagangSQA_JC@123");
        loginPage.clickButton();

        WebElement username = DriverUtil.getDriver().findElement(loginPage.emailField);
        String validationMessage = username.getAttribute("validationMessage");
        String actual = loginPage.getErrorMessage();

        if(validationMessage != null && !validationMessage.isEmpty()) {
            System.out.println(validationMessage);
            Assert.assertEquals(validationMessage, "Please enter an email address.");
        } else {
            System.out.println(actual);
            Assert.assertEquals(actual, "Akun tidak ditemukan");
        }
        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }

    @Test
    public void InvalidPassword() {
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        loginPage.openLoginPage();

        // Input Username and Password
        loginPage.inputUsername("admin@hadir.com");
        loginPage.inputPassword("Ampun dah");
        loginPage.clickButton();

        // Validate Error Message Not Null
        String actual = loginPage.getErrorMessage();
        Assert.assertNotNull(actual);

        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }
}