package com.juaracoding.tajuaracoding.laporan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.KehadiranPage;
import com.juaracoding.tajuaracoding.pages.components.Navbar;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class Kehadiran extends BaseTest {
    
    private KehadiranPage KehadiranPage;

    @Test
    public void testVerifikasiHalamanKehadiran() throws InterruptedException {
        Navbar navbar = new Navbar(DriverUtil.getDriver());
        navbar.openMenuLaporan();
        Thread.sleep(1000);
        navbar.clickKehadiran();
        Thread.sleep(2000);       

        // Inisialisasi page object
        KehadiranPage = new KehadiranPage(DriverUtil.getDriver());
        
        // Verifikasi URL atau Title jika diperlukan
        String currentUrl = KehadiranPage.getCurrentUrl().toLowerCase();
        System.out.println("Current URL: " + currentUrl);
        Assert.assertTrue(
            currentUrl.contains("/activity"),
            "URL halaman tidak sesuai! Diharapkan mengandung '/activity'"
        );

        // Verifikasi field pencarian
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(KehadiranPage.searchField).isDisplayed(),
            "Field pencarian tidak ditemukan!"
        );
        
        // Verifikasi filter tanggal (start & end)
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(KehadiranPage.startDateField).isDisplayed(),
            "Field start date tidak ditemukan!"
        );

        Assert.assertTrue(
            DriverUtil.getDriver().findElement(KehadiranPage.endDateField).isDisplayed(),
            "Field end date tidak ditemukan!"
        );

        // Verifikasi tombol Search & Reset
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(KehadiranPage.searchButton).isDisplayed(),
            "Tombol Search tidak ditemukan!"
        );

        Assert.assertTrue(
            DriverUtil.getDriver().findElement(KehadiranPage.resetButton).isDisplayed(),
            "Tombol Reset tidak ditemukan!"
        );

        // Verifikasi icon filter merah
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(KehadiranPage.filterByUnitButton).isDisplayed(),
            "Icon filter merah tidak ditemukan!"
        );

    }
}
