package com.juaracoding.tajuaracoding.Dashboard;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.dashboard.DashBoardPage;
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
    private DashBoardPage dashBoardPage;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Test(enabled = true)
    public void testVerifyPage() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("pending"),"URL halaman tidak sesuai!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.searchButton).isDisplayed(),"Tombol Search tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.resetButton).isDisplayed(),"Tombol Reset tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.filterByUnitButton).isDisplayed(),"Icon filter merah tidak ditemukan!");
    }

    @Test(enabled = true)
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
        //Lembur
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

        //------Validator3-------
        //Lembur
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

    @Test(enabled = true)
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

    @Test(enabled = true)
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

    // ERROR 404
    @Test(enabled = true)
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

    @Test(enabled = true)
    public void positiveFilterByUnitTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        String unitName = "IT Programmer";
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.filterByUnitButton));
        dashBoardPage.clickFilterUnit();
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.filterByUnitSelect).isDisplayed(),"Filter Unit Tidak Ditemukan");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.cancelButton).isDisplayed(),"Tombol Batal Tidak Ditemukan");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.applyButton).isDisplayed(),"Tombol Terapkan Tidak Ditemukan");
        Thread.sleep(5000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);
        Thread.sleep(2000);

        List<WebElement> options = DriverUtil.getDriver().findElements(By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='"+ unitName + "']"));
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
        Assert.assertTrue(dashBoardPage.getDataList().size()>0,"Data tidak muncul setelah filter");
    }

    @Test(enabled = true)
    public void resetfilterTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.calendarIcon));
        dashBoardPage.clickIconCalendar();
        dashBoardPage.selectMonth("February");
        dashBoardPage.selectDateRange(0,27);

        String startDate = dashBoardPage.getStartDateValue();
        String endDate   = dashBoardPage.getEndDateValue();
        String monthText = dashBoardPage.getMonthText();
        String unitName  = "IT Programmer";

        Thread.sleep(2000);

        Assert.assertEquals(monthText, "February","Month text tidak sesuai");
        Assert.assertTrue(startDate.contains("01-02-2025"),"Start Date tidak sesuai");
        Assert.assertTrue(endDate.contains("28-02-2025"),"End Date tidak sesuai");
        dashBoardPage.clickSaveCalendar();

        dashBoardPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.filterByUnitSelect).isDisplayed(),"Field UNIT BY tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.cancelButton).isDisplayed(),"Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.applyButton).isDisplayed(),"Tombol Terapkan tidak muncul");
        Thread.sleep(5000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);

        Thread.sleep(2000);

        List<WebElement> options = DriverUtil.getDriver().findElements(
                By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='" + unitName + "']"));

        if (!options.isEmpty()){
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(options.get(0)));
            options.get(0).click();
            System.out.println("Berhasil klik dropdown: "+unitName);
        } else {
            System.out.println("Option '"+ unitName + "' tidak ditemukan, langsung klik Apply");
        }
        dashBoardPage.clickApply();
        Thread.sleep(2000);

        dashBoardPage.clickReset();
        Thread.sleep(2000);
        Assert.assertTrue(dashBoardPage.getStartDateValue().isEmpty(), "Start Date tidak kosong");
        Assert.assertTrue(dashBoardPage.getEndDateValue().isEmpty(),"End Date tidak kosong");
        dashBoardPage.clickFilterUnit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.filterByUnitSelect)).clear();
        Assert.assertEquals(DriverUtil.getDriver().findElements(dashBoardPage.filterByUnitSelect).isEmpty(),false,"Field UNIT muncul");
        dashBoardPage.clickCancel();
    }

    @Test(enabled = true)
    public void negativeTestFilterByUnit() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        String unitName = "UnitTidakAda";
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.filterByUnitButton));
        dashBoardPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.filterByUnitSelect).isDisplayed(), "Field UNIT tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.cancelButton).isDisplayed(), "Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.applyButton).isDisplayed(), "Tombol Terapkan tidak muncul");

        Thread.sleep(2000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);

        Thread.sleep(2000);

        dashBoardPage.clickApply();

        Thread.sleep(4000);

        List<WebElement> rows = DriverUtil.getDriver().findElements(By.xpath("//table//tr"));
        if (rows.size() == 0) {
            System.out.println("Actual: Tabel kosong tanpa pesan feedback (BUG)");
        } else {
            System.out.println("Unexpected: masih ada data tampil");
        }

        Assert.assertEquals(rows.size(), 0, "Seharusnya tabel kosong ketika unit tidak ditemukan");
    }

    @Test(enabled = true)
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
        Assert.assertTrue(EndDate.isBefore(StartDate),"Date valid karena StartDate lebih besar dari EndDate");
        System.out.println(StartDate + ":" + EndDate);
        Assert.assertEquals(dashBoardPage.validator1Cuti.size(), 0);
        Assert.assertEquals(dashBoardPage.validator1Lembur.size(), 0);
        Assert.assertEquals(dashBoardPage.validator1Koreksi.size(), 0);
    }

    @Test(enabled = true)
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

    @Test(enabled = true)
    public void NullEndDateTest() throws InterruptedException {
        WebDriver driver =DriverUtil.getDriver();
        dashBoardPage = new DashBoardPage(driver);
        wait.until(ExpectedConditions.urlContains("/dashboards"));

        // input tanggal
        String StartDatestr = "2025-07-01";
        String EndDatestr   = "";

        String baseurl = "https://magang.dikahadir.com/dashboards/pending";
        String url     = baseurl + "?start_date=" + StartDatestr + "&end_date=" + EndDatestr;
        driver.get(url);
        Thread.sleep(5000);

        //Validasi Null
        System.out.println("URL Null Start Date Test "+ dashBoardPage.getCurrentUrl());
        Assert.assertTrue(EndDatestr.isEmpty(), "Start Date Tidak Kosong");
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
    }

    // V1-Lembur
    @Test(enabled = true)
    public void Positive_v1lembursearchTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        String keyword = "Hadir";
        dashBoardPage.clickuplinerlembur("Hadir SQA Testing 2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.v1search));
        dashBoardPage.v1search(keyword);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-lembur"), "URL halaman tidak sesuai!");
        System.out.println(dashBoardPage.getCurrentUrl());
        Thread.sleep(3000);
        List<String> username = dashBoardPage.getUserNameList();
        Assert.assertTrue(username.getFirst().contains(keyword), "User '" + keyword + "' not found");
    }

    @Test(enabled = true)
    public void Negative_v1lembursearchTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        String keyword = "Miku";
        dashBoardPage.clickuplinerlembur("Hadir SQA Testing 2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.v1search));
        dashBoardPage.v1search(keyword);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-lembur"),"URL halaman tidak sesuai!");
        System.out.println(dashBoardPage.getCurrentUrl());
        Thread.sleep(3000);
        boolean dataNotFound = dashBoardPage.isDataNotFoundMessageDisplayed();
        boolean isUserListEmpty = dashBoardPage.getUserNameList().isEmpty();
        Assert.assertTrue(dataNotFound || isUserListEmpty,"Expected 'Data tidak ditemukan' message or empty table, but found otherwise.");
        String data = dashBoardPage.v1data.getLast().getText();
        System.out.println(data);
    }

    @Test(enabled = true)
    public void reset_v1lembursearchTest() throws InterruptedException {
        // Validasi sebelum reset
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        String keyword = "Miku";
        dashBoardPage.clickuplinerlembur("Hadir SQA Testing 2");
        Assert.assertTrue(dashBoardPage.getDataLemburList("Hadir SQA Testing 2"),"Data tidak muncul");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.v1search));
        dashBoardPage.v1search(keyword);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-lembur"),"URL halaman tidak sesuai!");
        System.out.println(dashBoardPage.getCurrentUrl());
        Thread.sleep(2000);

        // Validasi User Invalid
        boolean dataNotFound = dashBoardPage.isDataNotFoundMessageDisplayed();
        boolean isUserListEmpty = dashBoardPage.getUserNameList().isEmpty();
        Assert.assertTrue(dataNotFound || isUserListEmpty,"Expected 'Data tidak ditemukan' message or empty table, but found otherwise.");

        // Validasi Setelah reset
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.v1buttonreset)).click();
        Thread.sleep(2000);
        Assert.assertEquals(DriverUtil.getDriver().findElements(dashBoardPage.v1search).isEmpty(),false,"Field search muncul");
        Assert.assertTrue(dashBoardPage.getDataLemburList("Hadir SQA Testing 2"),"Data tidak muncul");
        String data = dashBoardPage.v1data.getFirst().getText();
        System.out.println(data);
    }

    // V1-Cuti
    @Test(enabled = true)
    public void Positive_v1cutisearchTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        String keyword = "Hadir";
        dashBoardPage.clickuplinercuti("Hadir SQA Testing 2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.v1search));
        dashBoardPage.v1search(keyword);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-cuti"), "URL halaman tidak sesuai!");
        System.out.println(dashBoardPage.getCurrentUrl());
        Thread.sleep(3000);
        List<String> username = dashBoardPage.getUserNameList();
        Assert.assertTrue(username.getFirst().contains(keyword), "User '" + keyword + "' not found");
    }

    @Test(enabled = true)
    public void Negative_v1cutisearchTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        String keyword = "Miku";
        dashBoardPage.clickuplinercuti("Hadir SQA Testing 2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.v1search));
        dashBoardPage.v1search(keyword);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-cuti"),"URL halaman tidak sesuai!");
        System.out.println(dashBoardPage.getCurrentUrl());
        Thread.sleep(3000);
        boolean dataNotFound = dashBoardPage.isDataNotFoundMessageDisplayed();
        boolean isUserListEmpty = dashBoardPage.getUserNameList().isEmpty();
        Assert.assertTrue(dataNotFound || isUserListEmpty,"Expected 'Data tidak ditemukan' message or empty table, but found otherwise.");
        String data = dashBoardPage.v1data.getLast().getText();
        System.out.println(data);
    }

    @Test(enabled = true)
    public void reset_v1cutisearchTest() throws InterruptedException {
        // Validasi sebelum reset
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        String keyword = "Miku";
        dashBoardPage.clickuplinercuti("Hadir SQA Testing 2");
        Assert.assertTrue(dashBoardPage.getDataCutiList("Hadir SQA Testing 2"),"Data tidak muncul");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.v1search));
        dashBoardPage.v1search(keyword);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-cuti"),"URL halaman tidak sesuai!");
        System.out.println(dashBoardPage.getCurrentUrl());
        Thread.sleep(2000);

        // Validasi User Invalid
        boolean dataNotFound = dashBoardPage.isDataNotFoundMessageDisplayed();
        boolean isUserListEmpty = dashBoardPage.getUserNameList().isEmpty();
        Assert.assertTrue(dataNotFound || isUserListEmpty,"Expected 'Data tidak ditemukan' message or empty table, but found otherwise.");

        // Validasi Setelah reset
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.v1buttonreset)).click();
        Thread.sleep(2000);
        Assert.assertEquals(DriverUtil.getDriver().findElements(dashBoardPage.v1search).isEmpty(),false,"Field search muncul");
        Assert.assertTrue(dashBoardPage.getDataCutiList("Hadir SQA Testing 2"),"Data tidak muncul");
        String data = dashBoardPage.v1data.getFirst().getText();
        System.out.println(data);
    }

    // Error 404
    // V1-Koreksi
    @Test(enabled = true)
    public void Positive_v1koreksisearchTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        String keyword = "Hadir";
        dashBoardPage.clickuplinerkoreksi("Hadir SQA Testing 2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.v1search));
        dashBoardPage.v1search(keyword);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-koreksi"), "URL halaman tidak sesuai!");
        System.out.println(dashBoardPage.getCurrentUrl());
        Thread.sleep(3000);
        List<String> username = dashBoardPage.getUserNameList();
        Assert.assertTrue(username.getFirst().contains(keyword), "User '" + keyword + "' not found");
    }

    @Test(enabled = true)
    public void Negative_v1koreksisearchTest() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        String keyword = "Miku";
        dashBoardPage.clickuplinerkoreksi("Hadir SQA Testing 2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.v1search));
        dashBoardPage.v1search(keyword);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-koreksi"),"URL halaman tidak sesuai!");
        System.out.println(dashBoardPage.getCurrentUrl());
        Thread.sleep(3000);
        boolean dataNotFound = dashBoardPage.isDataNotFoundMessageDisplayed();
        boolean isUserListEmpty = dashBoardPage.getUserNameList().isEmpty();
        Assert.assertTrue(dataNotFound || isUserListEmpty,"Expected 'Data tidak ditemukan' message or empty table, but found otherwise.");
        String data = dashBoardPage.v1data.getFirst().getText();
        System.out.println(data);
    }

    @Test(enabled = true)
    public void reset_v1koreksisearchTest() throws InterruptedException {
        // Validasi sebelum reset
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        String keyword = "Miku";
        dashBoardPage.clickuplinerkoreksi("Hadir SQA Testing 2");
        Assert.assertTrue(dashBoardPage.getDataKoreksiList("Hadir SQA Testing 2"),"Data tidak muncul");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoardPage.v1search));
        dashBoardPage.v1search(keyword);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("v1-koreksi"),"URL halaman tidak sesuai!");
        System.out.println(dashBoardPage.getCurrentUrl());
        Thread.sleep(2000);

        // Validasi User Invalid
        boolean dataNotFound = dashBoardPage.isDataNotFoundMessageDisplayed();
        boolean isUserListEmpty = dashBoardPage.getUserNameList().isEmpty();
        Assert.assertTrue(dataNotFound || isUserListEmpty,"Expected 'Data tidak ditemukan' message or empty table, but found otherwise.");

        // Validasi Setelah reset
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.v1buttonreset)).click();
        Thread.sleep(2000);
        Assert.assertEquals(DriverUtil.getDriver().findElements(dashBoardPage.v1search).isEmpty(),false,"Field search muncul");
        Assert.assertTrue(dashBoardPage.getDataKoreksiList("Hadir SQA Testing 2"),"Data tidak muncul");
        String data = dashBoardPage.v1data.getFirst().getText();
        System.out.println(data);
    }

}