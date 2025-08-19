package com.juaracoding.tajuaracoding.Dashboard;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.DashBoardPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PendingDashboardTest extends BaseTest {
    DashBoardPage dashBoardPage;

    @Test
    public void testVerifyPage() throws InterruptedException {
        dashBoardPage = new DashBoardPage(DriverUtil.getDriver());
        Thread.sleep(2000);
        Assert.assertTrue(dashBoardPage.getCurrentUrl().contains("pending"),"URL halaman tidak sesuai!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.searchButton).isDisplayed(),"Tombol Search tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.resetButton).isDisplayed(),"Tombol Reset tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(dashBoardPage.filterByUnitButton).isDisplayed(),"Icon filter merah tidak ditemukan!");
    }

    @Test
    public void DateRangeFilter() throws InterruptedException {
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

    @Test
    public void validator1Lembur() throws InterruptedException {
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

    @Test
    public void validator1cuti() throws InterruptedException {
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

    @Test
    public void validator1koreksi() throws InterruptedException {
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


}