package com.juaracoding.tajuaracoding.Login;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.LoginPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLogin extends BaseTest {

    @Test
    public void InvalidEmail (){
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        // Open the login page
        loginPage.openLoginPage();

        // Input Username and Password
        loginPage.inputUsername("admin123@gmail.com");
        loginPage.inputPassword("MagangSQA_JC@123");
        loginPage.clickButton();

        // Validate Error Message Not Null

        String actual = loginPage.getErrorMessage();
        System.out.println(actual);
        Assert.assertEquals(actual,"Akun tidak ditemukan");

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

        // Validate Error Message Not Null
        String actual = loginPage.getErrorMessage();
        Assert.assertNotNull(actual);

        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }

}
