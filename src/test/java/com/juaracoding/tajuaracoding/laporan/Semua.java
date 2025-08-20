package com.juaracoding.tajuaracoding.laporan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.SemuaPage;
import com.juaracoding.tajuaracoding.pages.components.Navbar;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class Semua extends BaseTest {
    
    private SemuaPage SemuaPage;

    @Test
    public void testVerifikasiHalamanSemua() throws InterruptedException {
        Navbar navbar = new Navbar(DriverUtil.getDriver());
        navbar.openMenuLaporan();
        Thread.sleep(1000);
        navbar.clickSemua();
        Thread.sleep(2000);       

        // Inisialisasi page object
        SemuaPage = new SemuaPage(DriverUtil.getDriver());
        
        // Verifikasi URL atau Title jika diperlukan
        String currentUrl = SemuaPage.getCurrentUrl().toLowerCase();
        System.out.println("Current URL: " + currentUrl);
        Assert.assertTrue(
            currentUrl.contains("/all"),
            "URL halaman tidak sesuai! Diharapkan mengandung '/all'"
        );


        // Verifikasi field pencarian
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(SemuaPage.searchField).isDisplayed(),
            "Field pencarian tidak ditemukan!"
        );
        
        // Verifikasi filter tanggal (start & end)
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(SemuaPage.startDateField).isDisplayed(),
            "Field start date tidak ditemukan!"
        );

        Assert.assertTrue(
            DriverUtil.getDriver().findElement(SemuaPage.endDateField).isDisplayed(),
            "Field end date tidak ditemukan!"
        );

        // Verifikasi tombol Search & Reset
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(SemuaPage.searchButton).isDisplayed(),
            "Tombol Search tidak ditemukan!"
        );

        Assert.assertTrue(
            DriverUtil.getDriver().findElement(SemuaPage.resetButton).isDisplayed(),
            "Tombol Reset tidak ditemukan!"
        );

        // Verifikasi icon filter merah
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(SemuaPage.filterByUnitButton).isDisplayed(),
            "Icon filter merah tidak ditemukan!"
        );

        // // Verifikasi tabel data dan pagination
        // Assert.assertFalse(
        //     SemuaPage.getStatusList().isEmpty(),
        //     "Tabel data tidak ditemukan atau kosong!"
        // );
    }

}
