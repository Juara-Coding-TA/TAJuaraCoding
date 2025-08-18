package com.juaracoding.tajuaracoding.login;

import com.juaracoding.tajuaracoding.pages.LoginPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTest {

    @Test
        public void InvalidEmail() {
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
        // Langsung pakai loginPage dari BaseTest
        loginPage.openLoginPage();

        loginPage.inputUsername("admin123@gmail.com");
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
}