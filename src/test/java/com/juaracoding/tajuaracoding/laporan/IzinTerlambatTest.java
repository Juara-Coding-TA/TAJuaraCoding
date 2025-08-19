package com.juaracoding.tajuaracoding.laporan;

import java.util.List;
import java.util.Set;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;

import com.juaracoding.tajuaracoding.pages.IzinTerlambatPage;
import com.juaracoding.tajuaracoding.pages.components.Navbar;
import com.juaracoding.tajuaracoding.utils.DriverUtil;


public class IzinTerlambatTest extends BaseTest {

    private IzinTerlambatPage izinTerlambatPage;

    private void navigateToIzinTerlambatPage() throws InterruptedException {
        Navbar navbar = new Navbar(DriverUtil.getDriver());
        navbar.openMenuLaporan();
        Thread.sleep(1000); 
        navbar.clickIzinTelambat();
        Thread.sleep(2000); 
        izinTerlambatPage = new IzinTerlambatPage(DriverUtil.getDriver());
    }

    @Test(enabled = true)
    public void testVerifyPage() throws InterruptedException {
        navigateToIzinTerlambatPage();

        Assert.assertTrue(izinTerlambatPage.getCurrentUrl().contains("izin-terlambat"),"URL halaman tidak sesuai!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.searchField).isDisplayed(),"Field pencarian tidak ditesmukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.searchButton).isDisplayed(),"Tombol Search tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.resetButton).isDisplayed(),"Tombol Reset tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.filterByUnitButton).isDisplayed(),"Icon filter merah tidak ditemukan!");
        Assert.assertFalse(izinTerlambatPage.getStatusList().isEmpty(),"Tabel data tidak ditemukan atau kosong!");
    }

    @Test(enabled = true)
    public void testPencarianUserValid() throws InterruptedException {
        navigateToIzinTerlambatPage();
        
        String keyword = "Ahlan Rezki";
        izinTerlambatPage.search(keyword);
        Thread.sleep(2000);
        List<String> userNames = izinTerlambatPage.getUserNameList();
        Assert.assertTrue(userNames.contains(keyword), "User '" + keyword + "' not found in search results.");
    }

    @Test(enabled = true)
    public void testPencarianUserTidakAda() throws InterruptedException {
        navigateToIzinTerlambatPage();

        String keyword = "UserTidakAda123";
        izinTerlambatPage.search(keyword);
        Thread.sleep(2000); 

        boolean dataNotFound = izinTerlambatPage.isDataNotFoundMessageDisplayed();
        boolean isUserListEmpty = izinTerlambatPage.getUserNameList().isEmpty();

        Assert.assertTrue(dataNotFound || isUserListEmpty, "Expected 'Data tidak ditemukan' message or empty table, but found otherwise.");
    }

   @Test(enabled = true)
    public void testDateRangeFilter() throws InterruptedException {
        navigateToIzinTerlambatPage();
        izinTerlambatPage.clickIconCalendar();

        // Pilih tanggal awal & akhir, Pilih tanggal (misal index ke-0 & ke-14)
        izinTerlambatPage.selectDateRange(0, 14);

        Thread.sleep(2000); 

        String startDate = izinTerlambatPage.getStartDateValue();
        String endDate   = izinTerlambatPage.getEndDateValue();

        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        Assert.assertTrue(startDate.contains("01 Agt 2025"), "Start Date tidak sesuai");
        Assert.assertTrue(endDate.contains("15 Agt 2025"), "End Date tidak sesuai");

        izinTerlambatPage.clickSaveCalendar();

        izinTerlambatPage.clickSearch();

        Assert.assertTrue(izinTerlambatPage.getDataList().size() > 0, "Data tidak muncul setelah filter");
    }

    @Test(enabled = true)
    public void positiveTestFilterByUnit() throws InterruptedException {
        navigateToIzinTerlambatPage();
        String unitName = "Sysmex";

        izinTerlambatPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.filterByUnitSelect).isDisplayed(), "Field UNIT tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.cancelButton).isDisplayed(), "Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.applyButton).isDisplayed(), "Tombol Terapkan tidak muncul");

        Thread.sleep(5000);
        
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(izinTerlambatPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);

        Thread.sleep(2000);

        List<WebElement> options = DriverUtil.getDriver().findElements(
            By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='" + unitName + "']"));

        if (!options.isEmpty()) {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(options.get(0)));
            options.get(0).click();
            System.out.println("Berhasil klik dropdown: " + unitName);
        } else {
            System.out.println("Option '" + unitName + "' tidak ditemukan, langsung klik Apply");
        }

        izinTerlambatPage.clickApply();
        
        Thread.sleep(2000);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(izinTerlambatPage.filterByModal));
        Assert.assertTrue(
                DriverUtil.getDriver().findElements(izinTerlambatPage.filterByModal).isEmpty(),
                "Modal filter masih terlihat setelah klik Terapkan"
        );

        Assert.assertTrue(izinTerlambatPage.getDataList().size() > 0, "Data tidak muncul setelah filter");
        System.out.println("Jumlah data setelah filter: " + izinTerlambatPage.getDataList().size());

        for (WebElement row : izinTerlambatPage.getDataList()) {
            String rowText = row.getText();
            Assert.assertTrue(rowText.contains(unitName),"Ditemukan row yang bukan unit " + unitName + ": " + rowText);
        }
    }


    @Test(enabled = true)
    public void negativeTestFilterByUnit() throws InterruptedException {
        navigateToIzinTerlambatPage();
        String unitName = "UnitTidakAda";

        izinTerlambatPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.filterByUnitSelect).isDisplayed(), "Field UNIT tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.cancelButton).isDisplayed(), "Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.applyButton).isDisplayed(), "Tombol Terapkan tidak muncul");

        Thread.sleep(2000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(izinTerlambatPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);

        Thread.sleep(2000);

        izinTerlambatPage.clickApply();

        List<WebElement> rows = DriverUtil.getDriver().findElements(By.xpath("//table//tr"));
        if (rows.size() == 0) {
            System.out.println("Actual: Tabel kosong tanpa pesan feedback (BUG)");
        } else {
            System.out.println("Unexpected: masih ada data tampil");
        }

        Assert.assertEquals(rows.size(), 0, "Seharusnya tabel kosong ketika unit tidak ditemukan");
    }

    @Test(enabled = true)
    public void testCombinationFilterNameDateUnit() throws InterruptedException {
        navigateToIzinTerlambatPage();
        izinTerlambatPage.search("Hadir");

        izinTerlambatPage.clickIconCalendar();
        izinTerlambatPage.selectMonth("June");
        izinTerlambatPage.selectDateRange(0, 29);

        String startDate = izinTerlambatPage.getStartDateValue();
        String endDate   = izinTerlambatPage.getEndDateValue();
        String monthText = izinTerlambatPage.getMonthText();   // Ambil text ("June")
        String unitName  = "Sysmex";

        Thread.sleep(2000);

        Assert.assertEquals(monthText, "June", "Month text tidak sesuai");
        Assert.assertTrue(startDate.contains("01 Jun 2025"), "Start Date tidak sesuai");
        Assert.assertTrue(endDate.contains("30 Jun 2025"), "End Date tidak sesuai");

        izinTerlambatPage.clickSaveCalendar();
        izinTerlambatPage.clickSearch();
        izinTerlambatPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.filterByUnitSelect).isDisplayed(), "Field UNIT tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.cancelButton).isDisplayed(), "Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.applyButton).isDisplayed(), "Tombol Terapkan tidak muncul");

        Thread.sleep(5000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(izinTerlambatPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);

        Thread.sleep(2000);

        List<WebElement> options = DriverUtil.getDriver().findElements(
            By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='" + unitName + "']"));

        if (!options.isEmpty()) {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(options.get(0)));
            options.get(0).click();
            System.out.println("Berhasil klik dropdown: " + unitName);
        } else {
            System.out.println("Option '" + unitName + "' tidak ditemukan, langsung klik Apply");
        }

        izinTerlambatPage.clickApply();

        Thread.sleep(2000);

        Collection<String> rows = izinTerlambatPage.getDataRows();

        Assert.assertFalse(rows.isEmpty(), "Tidak ada data yang muncul setelah filter");

        Assert.assertTrue(
            rows.stream().allMatch(row ->
                row.contains("Hadir") &&        
                row.contains(unitName) &&         
                row.contains("Jun 2025")          
            ),
            "Data tidak sesuai filter kombinasi (Name, Date, Unit)"
        );
    }

    @Test(enabled = true)
        public void resetTestAllFilter () throws InterruptedException {
        navigateToIzinTerlambatPage();
        izinTerlambatPage.search("Hadir");

        izinTerlambatPage.clickIconCalendar();
        izinTerlambatPage.selectMonth("February");
        izinTerlambatPage.selectDateRange(0, 27);

        String startDate = izinTerlambatPage.getStartDateValue();
        String endDate   = izinTerlambatPage.getEndDateValue();
        String monthText = izinTerlambatPage.getMonthText();   // Ambil text ("June")
        String unitName  = "Sysmex";

        Thread.sleep(2000);

        Assert.assertEquals(monthText, "February", "Month text tidak sesuai");
        Assert.assertTrue(startDate.contains("01 Feb 2025"), "Start Date tidak sesuai");
        Assert.assertTrue(endDate.contains("28 Feb 2025"), "End Date tidak sesuai");

        izinTerlambatPage.clickSaveCalendar();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.searchField).isDisplayed(), "Field pencarian tidak muncul");
        izinTerlambatPage.clickSearch();
        izinTerlambatPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.filterByUnitSelect).isDisplayed(), "Field UNIT tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.cancelButton).isDisplayed(), "Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinTerlambatPage.applyButton).isDisplayed(), "Tombol Terapkan tidak muncul");

        Thread.sleep(5000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(izinTerlambatPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);

        Thread.sleep(2000);

        List<WebElement> options = DriverUtil.getDriver().findElements(
            By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='" + unitName + "']"));

        if (!options.isEmpty()) {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(options.get(0)));
            options.get(0).click();
            System.out.println("Berhasil klik dropdown: " + unitName);
        } else {
            System.out.println("Option '" + unitName + "' tidak ditemukan, langsung klik Apply");
        }

        izinTerlambatPage.clickApply();

        Thread.sleep(2000);

        izinTerlambatPage.clickReset();

        Assert.assertTrue(izinTerlambatPage.getSearchResult().isEmpty(), "Keyword tidak kosong");
        Assert.assertTrue(izinTerlambatPage.getStartDateValue().isEmpty(), "Start Date tidak kosong");
        Assert.assertTrue(izinTerlambatPage.getEndDateValue().isEmpty(), "End Date tidak kosong");

        izinTerlambatPage.clickFilterUnit();

        Assert.assertEquals(DriverUtil.getDriver().findElement(izinTerlambatPage.filterByUnitSelect).isDisplayed(), false, "Field UNIT muncul");

        izinTerlambatPage.clickCancel();   
    }

    @Test
    public void testCheckSpecificStatuses() throws InterruptedException {
        navigateToIzinTerlambatPage();

        List<String> expectedStatuses = Arrays.asList("PENDING", "APPROVED", "REJECT");
        Set<String> foundStatuses = new HashSet<>();

        while (true) {
            List<String> currentStatuses = izinTerlambatPage.getStatusListAlt();
            System.out.println("Status di halaman ini: " + currentStatuses);

            for (String status : currentStatuses) {
                Assert.assertTrue(expectedStatuses.contains(status), "Status tidak sesuai: '" + status + "' - Expected: " + expectedStatuses);
                foundStatuses.add(status);
            }

            if (foundStatuses.containsAll(expectedStatuses)) {
                System.out.println("Semua status sudah ditemukan: " + foundStatuses);
                break;
            }

            try {
                WebElement nextBtn = DriverUtil.getDriver().findElement(izinTerlambatPage.nextPageButton);
                if (nextBtn.isEnabled() && !nextBtn.getAttribute("class").contains("Mui-disabled")) {
                    ((JavascriptExecutor) DriverUtil.getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", nextBtn);
                    Thread.sleep(300);
                    nextBtn.click();
                    Thread.sleep(1500);
                } else {
                    System.out.println("Sudah di halaman terakhir");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Next button tidak ditemukan - sudah di halaman terakhir");
                break;
            }
        }

        Assert.assertTrue(foundStatuses.containsAll(expectedStatuses), "Tidak semua status ditemukan. Found: " + foundStatuses + ", Expected: " + expectedStatuses);
        System.out.println("🎉 Test selesai! Total status dicek: " + foundStatuses.size());
    }

    
}
