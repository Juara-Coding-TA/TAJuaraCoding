package com.juaracoding.tajuaracoding.Login;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.LoginPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;


public class PositiveLogin extends BaseTest{

    @Test
    @Parameters({"username", "password"})
    public void testLogin(String username, String password) throws InterruptedException {
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
        
        // Open the login page
        loginPage.openLoginPage();
        
        // Perform login
        loginPage.login(username, password);
        Thread.sleep(1000);
        // Verify successful login by checking the URL or any other element that indicates success
        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);
        
        // Optionally, you can add assertions here to validate the successful login
    }

    @Test
    @Parameters({"username", "password"})
    public void Logout(String username, String password) throws InterruptedException {
        // Initialize the LoginPage with the current driver
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        // Open the login page
        loginPage.openLoginPage();

        // Perform login
        loginPage.login(username, password);
        Thread.sleep(1000);
        // Verify successful login by checking the URL or any other element that indicates success
        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);

        // Clicking Button For Logout
        loginPage.miniButton();
        Thread.sleep(100);
        loginPage.logoutButton();

        // Verify successful logout by checking the URL
        String currentUrls = loginPage.getCurrentUrl();
        System.out.println("Current URL: " + currentUrls);
        Assert.assertEquals(currentUrls,"https://magang.dikahadir.com/authentication/login");
    }
}
