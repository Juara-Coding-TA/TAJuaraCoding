package com.juaracoding.tajuaracoding.login;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.login.LoginPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @Test
    public void logoutTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverUtil.getDriver());

        // verifikasi login telah berhasil
        Thread.sleep(2000);
        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);
        Assert.assertTrue(loginPage.getCurrentUrl().contains("dashboard"),"Url Tidak Sesuai");
        Thread.sleep(2000);

        loginPage.menubuton();
        Thread.sleep(1000);
        loginPage.logoutbutton();
        Thread.sleep(1000);

        Assert.assertTrue(loginPage.getCurrentUrl().contains("login"),"Url Tidak Sesuai");
    }
}
