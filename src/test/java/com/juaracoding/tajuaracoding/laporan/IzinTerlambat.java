package com.juaracoding.tajuaracoding.laporan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.IzinTerlambatPage;
import com.juaracoding.tajuaracoding.pages.components.Navbar;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class IzinTerlambat extends BaseTest {

    private IzinTerlambatPage izinTerlambatPage;

    @Test()
    public void testVerifikasiHalamanIzinTerlambat() throws InterruptedException {
        Navbar navbar = new Navbar(DriverUtil.getDriver());
        navbar.openMenuLaporan();
        Thread.sleep(1000);
        navbar.clickIzinTelambat();
        Thread.sleep(2000);

         // Inisialisasi page object
        izinTerlambatPage = new IzinTerlambatPage(DriverUtil.getDriver());

        // Verifikasi URL atau Title jika diperlukan
        Assert.assertTrue(
            izinTerlambatPage.getCurrentUrl().contains("izin-terlambat"),
            "URL halaman tidak sesuai!"
        );

        // Verifikasi field pencarian
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(izinTerlambatPage.searchField).isDisplayed(),
            "Field pencarian tidak ditemukan!"
        );

        // Verifikasi filter tanggal (start & end)
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(izinTerlambatPage.startDateField).isDisplayed(),
            "Field start date tidak ditemukan!"
        );
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(izinTerlambatPage.endDateField).isDisplayed(),
            "Field end date tidak ditemukan!"
        );

        // Verifikasi tombol Search & Reset
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(izinTerlambatPage.searchButton).isDisplayed(),
            "Tombol Search tidak ditemukan!"
        );
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(izinTerlambatPage.resetButton).isDisplayed(),
            "Tombol Reset tidak ditemukan!"
        );

        // Verifikasi icon filter merah
        Assert.assertTrue(
            DriverUtil.getDriver().findElement(izinTerlambatPage.filterByUnitButton).isDisplayed(),
            "Icon filter merah tidak ditemukan!"
        );

        // Verifikasi tabel data dan pagination
        Assert.assertFalse(
            izinTerlambatPage.getStatusList().isEmpty(),
            "Tabel data tidak ditemukan atau kosong!"
        );

        izinTerlambatPage.clickLastPage(); // memastikan pagination ada dan bisa diklik
    }
}

