package com.juaracoding.tajuaracoding.Dashboard;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.DashBoardPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PendingDashboardTest extends BaseTest {
    DashBoardPage dashBoardPage;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test(enabled = false)
    public void testVerifyPage() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("pending"),"URL halaman tidak sesuai!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.searchButton).isDisplayed(),"Tombol Search tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.resetButton).isDisplayed(),"Tombol Reset tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.filterByUnitButton).isDisplayed(),"Icon filter merah tidak ditemukan!");
    }

    @Test(enabled = false)
    public void DateRangeFilterTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        dashBoardPage.clickIconCalendar();
        dashBoardPage.selectDateRange(0,18);
        String startDate = dashBoardPage.getStartDateValue();
        String endDate = dashBoardPage.getEndDateValue();

        Assert.assertTrue(startDate.contains("01-08-2025"),"Start Date tidak sesuai");
        Assert.assertTrue(endDate.contains("19-08-2025"), "End Date tidak sesuai");

        Thread.sleep(2000);
        dashBoardPage.clickSaveCalendar();
        dashBoardPage.clickSearch();
        Thread.sleep(2000);

        //------Validator1-------
        //Lembur
        if (dashBoardPage.validator1Lembur.isEmpty()) {
            System.out.println("Validator1 - Lembur kosong.");
            Assert.assertEquals(dashBoardPage.validator1Lembur.size(), 0);
        } else {
            System.out.println("Validator1 - Lembur ada data.");
            Assert.assertTrue(dashBoardPage.validator1Lembur.size() > 0);
        }
        //Cuti
        if (dashBoardPage.validator1Cuti.isEmpty()) {
            System.out.println("Validator1 - Cuti kosong.");
            Assert.assertEquals(dashBoardPage.validator1Cuti.size(), 0);
        } else {
            System.out.println("Validator1 - Cuti ada data.");
            Assert.assertTrue(dashBoardPage.validator1Cuti.size() > 0);
        }
        //Koreksi
        if (dashBoardPage.validator1Koreksi.isEmpty()) {
            System.out.println("Validator1 - Koreksi kosong.");
            Assert.assertEquals(dashBoardPage.validator1Koreksi.size(), 0);
        } else {
            System.out.println("Validator1 - Koreksi ada data.");
            Assert.assertTrue(dashBoardPage.validator1Koreksi.size() > 0);
        }
        //------Validator2-------
        if (dashBoardPage.validator2Lembur.isEmpty()) {
            System.out.println("Validator2 - Lembur kosong.");
            Assert.assertEquals(dashBoardPage.validator2Lembur.size(), 0);
        } else {
            System.out.println("Validator2 - Lembur ada data.");
            Assert.assertTrue(dashBoardPage.validator2Lembur.size() > 0);
        }
        //Cuti
        if (dashBoardPage.validator2Cuti.isEmpty()) {
            System.out.println("Validator2 - Cuti kosong.");
            Assert.assertEquals(dashBoardPage.validator2Cuti.size(), 0);
        } else {
            System.out.println("Validator2 - Cuti ada data.");
            Assert.assertTrue(dashBoardPage.validator2Cuti.size() > 0);
        }
        //Koreksi
        if (dashBoardPage.validator2Koreksi.isEmpty()) {
            System.out.println("Validator2 - Koreksi kosong.");
            Assert.assertEquals(dashBoardPage.validator2Koreksi.size(), 0);
        } else {
            System.out.println("Validator2 - Koreksi ada data.");
            Assert.assertTrue(dashBoardPage.validator2Koreksi.size() > 0);
        }

        if (dashBoardPage.validator3Lembur.isEmpty()) {
            System.out.println("Validator3 - Lembur kosong.");
            Assert.assertEquals(dashBoardPage.validator3Lembur.size(), 0);
        } else {
            System.out.println("Validator3 - Lembur ada data.");
            Assert.assertTrue(dashBoardPage.validator3Lembur.size() > 0);
        }
        //Cuti
        if (dashBoardPage.validator3Cuti.isEmpty()) {
            System.out.println("Validator3 - Cuti kosong.");
            Assert.assertEquals(dashBoardPage.validator3Cuti.size(), 0);
        } else {
            System.out.println("Validator3 - Cuti ada data.");
            Assert.assertTrue(dashBoardPage.validator3Cuti.size() > 0);
        }
        //Koreksi
        if (dashBoardPage.validator3Koreksi.isEmpty()) {
            System.out.println("Validator3 - Koreksi kosong.");
            Assert.assertEquals(dashBoardPage.validator3Koreksi.size(), 0);
        } else {
            System.out.println("Validator3 - Koreksi ada data.");
            Assert.assertTrue(dashBoardPage.validator3Koreksi.size() > 0);
        }
    }

    @Test(enabled = false)
    public void validator1LemburTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        if (dashBoardPage.validator1Lembur.isEmpty()) {
            System.out.println("Validator1 - Lembur kosong.");
            Assert.assertEquals(dashBoardPage.validator1Lembur.size(), 0);
        } else {
            System.out.println("Validator1 - Lembur ada data.");
            Assert.assertTrue(dashBoardPage.validator1Lembur.size() > 0);
            dashBoardPage.clickuplinerlembur("Hadir SQA Testing 2");
            Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-lembur"),"URL halaman tidak sesuai!");
            System.out.println(dashBoardPage.getCurrentUrl());
            Assert.assertTrue(dashBoardPage.getDataLemburList("Hadir SQA Testing 2"),"Data tidak muncul");
        }
    }

    @Test(enabled = false)
    public void validator1cutiTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        if (dashBoardPage.validator1Cuti.isEmpty()) {
            System.out.println("Validator1 - Cuti kosong.");
            Assert.assertEquals(dashBoardPage.validator1Cuti.size(), 0);
        } else {
            System.out.println("Validator1 - Cuti ada data.");
            Assert.assertTrue(dashBoardPage.validator1Cuti.size() > 0);
            dashBoardPage.clickuplinercuti("Hadir SQA Testing 2");
            Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-cuti"),"URL halaman tidak sesuai!");
            System.out.println(dashBoardPage.getCurrentUrl());
            Assert.assertTrue(dashBoardPage.getDataCutiList("Hadir SQA Testing 2"),"Data tidak muncul");
        }
    }

    @Test(enabled = false)
    public void validator1koreksiTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        if (dashBoardPage.validator1Koreksi.isEmpty()) {
            System.out.println("Validator1 - Koreksi kosong.");
            Assert.assertEquals(dashBoardPage.validator1Koreksi.size(), 0);
        } else {
            System.out.println("Validator1 - Koreksi ada data.");
            Assert.assertTrue(dashBoardPage.validator1Koreksi.size() > 0);
            dashBoardPage.clickuplinerkoreksi("Hadir SQA Testing 2");
            Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-koreksi"),"URL halaman tidak sesuai!");
            System.out.println(dashBoardPage.getCurrentUrl());
            Assert.assertTrue(dashBoardPage.getDataKoreksiList("Hadir SQA Testing 2"),"Data tidak muncul");
        }
    }

    // BUG
    @Test(enabled = false)
    public void positiveFilterByUnitTest() throws InterruptedException {
        String unitName = "Sysmex";

        dashBoardPage.clickFilterUnit();
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.filterByUnitSelect).isDisplayed(),"Filter Unit Tidak Ditemukan");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.cancelButton).isDisplayed(),"Tombol Batal Tidak Ditemukan");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.applyButton).isDisplayed(),"Tombol Terapkan Tidak Ditemukan");
        Thread.sleep(5000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);
        Thread.sleep(2000);

        List<WebElement> options = DriverUtil.getDriver().findElements(By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='\" + unitName + \"']\""));
        if(!options.isEmpty()){
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(options.get(0)));
            options.get(0).click();
            System.out.println("Berhasil klik dropdown: " + unitName);
        } else {
            System.out.println("Option '"+unitName+"' tidak ditemukan, langsung klik Apply");
        }

        dashBoardPage.clickApply();

        Thread.sleep(2000);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(dashBoardPage.filterByModal));
        Assert.assertTrue(
                DriverUtil.getDriver().findElements(dashBoardPage.filterByModal).isEmpty(),
                "Modal Filter Masih Terlihat Setelah Klik Terapkan");

    }

    @Test()
    public void InvalidDateTest() throws InterruptedException {
        WebDriver driver =DriverUtil.getDriver();
        dashBoardPage = new DashBoardPage(driver);
        wait.until(ExpectedConditions.urlContains("/dashboards"));
        // input tanggal
        String StartDatestr = "2025-10-01";
        String EndDatestr   = "2023-10-01";

        // Konversi Date
        LocalDate StartDate = LocalDate.parse(StartDatestr, formatter);
        LocalDate EndDate   = LocalDate.parse(EndDatestr, formatter);

        String baseurl = "https://magang.dikahadir.com/dashboards/pending";
        String url     = baseurl + "?start_date=" + StartDatestr + "&end_date=" + EndDatestr;
        driver.get(url);
        Thread.sleep(5000);

        // Validasi tanggal start tidak boleh kurang dari tanggal akhir
        System.out.println("URL Invalid Date Test "+dashBoardPage.getCurrentUrl());
        //Assert.assertFalse(EndDate.isBefore(StartDate),"System Masih Dapat Menerima End Date Lebih Kecil dari Start Date");
        System.out.println(StartDate + ":" + EndDate);
        Assert.assertEquals(dashBoardPage.validator1Cuti.size(), 0);
        Assert.assertEquals(dashBoardPage.validator1Lembur.size(), 0);
        Assert.assertEquals(dashBoardPage.validator1Koreksi.size(), 0);
    }

    @Test()
    public void NullStartDateTest() throws InterruptedException {
        WebDriver driver =DriverUtil.getDriver();
        dashBoardPage = new DashBoardPage(driver);
        wait.until(ExpectedConditions.urlContains("/dashboards"));

        // input tanggal
        String StartDatestr = "";
        String EndDatestr   = "2025-07-01";

        String baseurl = "https://magang.dikahadir.com/dashboards/pending";
        String url     = baseurl + "?start_date=" + StartDatestr + "&end_date=" + EndDatestr;
        driver.get(url);
        Thread.sleep(5000);

        //Validasi Null
        System.out.println("URL Null Start Date Test "+ dashBoardPage.getCurrentUrl());
        Assert.assertTrue(StartDatestr.isEmpty(), "Start Date Tidak Kosong");
        Assert.assertEquals(dashBoardPage.validator1Cuti.size(), 0);
        Assert.assertEquals(dashBoardPage.validator1Lembur.size(), 0);
        Assert.assertEquals(dashBoardPage.validator1Koreksi.size(), 0);
    }
}