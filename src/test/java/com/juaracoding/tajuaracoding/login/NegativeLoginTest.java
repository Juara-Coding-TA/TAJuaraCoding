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

        loginPage.inputUsername("hadir@gmail.com");
        loginPage.inputPassword("MagangSQA_JC@123");
        loginPage.clickButton();

        String actual = loginPage.getErrorMessage();
        System.out.println(actual);
        Assert.assertEquals(actual, "Akun tidak ditemukan");
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

    @Test
    public void InvalidFormatEmail() {
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
        // Langsung pakai loginPage dari BaseTest
        loginPage.openLoginPage();

        loginPage.inputUsername("adminhadir.com");
        loginPage.inputPassword("MagangSQA_JC@123");
        loginPage.clickButton();

        WebElement username = DriverUtil.getDriver().findElement(loginPage.emailField);
        String validationMessage = username.getAttribute("validationMessage");
        String actual = loginPage.getErrorMessage();
        System.out.println(validationMessage);
        Assert.assertEquals(validationMessage, "Please enter an email address.");
        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }

    @Test
    public void NullEmail(){
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        loginPage.openLoginPage();

        // Input Username and Password
        loginPage.inputUsername("");
        loginPage.inputPassword("MagangSQA_JC@123");
        loginPage.clickButton();

        // Validate Error Message Not Null
        String actual = loginPage.getErrorMessage();
        Assert.assertNotNull(actual);
        Assert.assertEquals(actual,"Akun tidak ditemukan");

        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }

    @Test
    public void NullPassword(){
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        loginPage.openLoginPage();

        // Input Username and Password
        loginPage.inputUsername("admin@hadir.com");
        loginPage.inputPassword("");
        loginPage.clickButton();

        // Validate Error Message Not Null
        String actual = loginPage.getErrorMessage();
        Assert.assertNotNull(actual);
        Assert.assertEquals(actual,"Email atau password salah");

        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }

    @Test
    public void NullEmailandPassword(){
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        loginPage.openLoginPage();

        // Input Username and Password
        loginPage.inputUsername("");
        loginPage.inputPassword("");
        loginPage.clickButton();

        // Validate Error Message Not Null
        String actual = loginPage.getErrorMessage();
        Assert.assertNotNull(actual);
        Assert.assertEquals(actual,"Akun tidak ditemukan");

        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }
}