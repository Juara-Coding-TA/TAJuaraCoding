package com.juaracoding.tajuaracoding.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.LoginPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class PositiveLogin extends BaseTest {

    @Test
    public void testLogin() throws InterruptedException {

        // Verify successful login
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);

        Thread.sleep(2000); 

        // Asumsi URL yang benar setelah login adalah "https://magang.dikahadir.com/dashboards/pending"
        String expectedUrl = "https://magang.dikahadir.com/dashboards/pending";
        Assert.assertEquals(currentUrl, expectedUrl, "URL tidak sesuai setelah login");        
    }
}
