package com.juaracoding.tajuaracoding.login;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.pages.login.LoginPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class PositiveLoginTest {

    @Test()
    public void testLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        // Input username & password
        loginPage.inputUsername("admin@hadir.com");
        loginPage.inputPassword("MagangSQA_JC@123");

        // Klik tombol login
        loginPage.clickButton();

        // Tunggu redirect ke dashboard
        String expectedUrl = "https://magang.dikahadir.com/dashboards/pending";
        new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.urlToBe(expectedUrl));

        // Ambil current URL
        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);

        // Assert URL
        Assert.assertEquals(currentUrl, expectedUrl, "URL tidak sesuai setelah login");
    }
}
