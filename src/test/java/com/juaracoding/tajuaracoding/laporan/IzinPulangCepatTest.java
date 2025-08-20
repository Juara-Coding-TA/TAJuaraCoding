package com.juaracoding.tajuaracoding.laporan;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.components.Navbar;
import com.juaracoding.tajuaracoding.pages.laporan.IzinPulangCepatPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class IzinPulangCepatTest extends BaseTest {

    private IzinPulangCepatPage izinPulangCepatPage;

    private void navigateToIzinPulangCepatPage() throws InterruptedException {
        Navbar navbar = new Navbar(DriverUtil.getDriver());
        navbar.openMenuLaporan();
        Thread.sleep(1000);
        navbar.clickIzinPulangCepat();
        Thread.sleep(2000);
        izinPulangCepatPage = new IzinPulangCepatPage(DriverUtil.getDriver());
    }

    @Test(enabled = false)
    public void testVerifyPage() throws InterruptedException {
        navigateToIzinPulangCepatPage();

        Assert.assertTrue(izinPulangCepatPage.getCurrentUrl().contains("izin-pulang-cepat"),"URL halaman tidak sesuai!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.searchField).isDisplayed(),"Field pencarian tidak ditesmukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.searchButton).isDisplayed(),"Tombol Search tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.resetButton).isDisplayed(),"Tombol Reset tidak ditemukan!");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.filterByUnitButton).isDisplayed(),"Icon filter merah tidak ditemukan!");
        Assert.assertFalse(izinPulangCepatPage.getStatusList().isEmpty(),"Tabel data tidak ditemukan atau kosong!");
    }

    @Test(enabled = false)
    public void testPencarianUserValid() throws InterruptedException {
        navigateToIzinPulangCepatPage();
        
        String keyword = "juned";
        izinPulangCepatPage.search(keyword);
        Thread.sleep(2000);
        List<String> userNames = izinPulangCepatPage.getUserNameList();
        Assert.assertTrue(userNames.contains(keyword), "User '" + keyword + "' not found in search results.");
    }

    @Test(enabled = false)
    public void testPencarianUserTidakAda() throws InterruptedException {
        navigateToIzinPulangCepatPage();

        String keyword = "UserTidakAda123";
        izinPulangCepatPage.search(keyword);
        Thread.sleep(2000); 

        boolean dataNotFound = izinPulangCepatPage.isDataNotFoundMessageDisplayed();
        boolean isUserListEmpty = izinPulangCepatPage.getUserNameList().isEmpty();

        Assert.assertTrue(dataNotFound || isUserListEmpty, "Expected 'Data tidak ditemukan' message or empty table, but found otherwise.");
    }

   @Test(enabled = false)
    public void testDateRangeFilter() throws InterruptedException {
        navigateToIzinPulangCepatPage();
        izinPulangCepatPage.clickIconCalendar();

        // Pilih tanggal awal & akhir, Pilih tanggal (misal index ke-0 & ke-14)
        izinPulangCepatPage.selectDateRange(0, 14);

        Thread.sleep(2000); 

        String startDate = izinPulangCepatPage.getStartDateValue();
        String endDate   = izinPulangCepatPage.getEndDateValue();

        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        Assert.assertTrue(startDate.contains("01 Agt 2025"), "Start Date tidak sesuai");
        Assert.assertTrue(endDate.contains("15 Agt 2025"), "End Date tidak sesuai");

        izinPulangCepatPage.clickSaveCalendar();

        izinPulangCepatPage.clickSearch();

        Assert.assertTrue(izinPulangCepatPage.getDataList().size() > 0, "Data tidak muncul setelah filter");
    }

    @Test(enabled = false)
    public void positiveTestFilterByUnit() throws InterruptedException {
        navigateToIzinPulangCepatPage();
        String unitName = "Sysmex";

        izinPulangCepatPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.filterByUnitSelect).isDisplayed(), "Field UNIT tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.cancelButton).isDisplayed(), "Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.applyButton).isDisplayed(), "Tombol Terapkan tidak muncul");

        Thread.sleep(5000);
        
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(izinPulangCepatPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);

        Thread.sleep(2000);

        List<WebElement> options = DriverUtil.getDriver().findElements(
            By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='" + unitName + "']"));

        if (!options.isEmpty()) {
            // This WebDriverWait instance is local to this block and is not the one causing the NPE.
            // The issue is with the class-level 'wait' field.
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(options.get(0)));
            options.get(0).click();
            System.out.println("Berhasil klik dropdown: " + unitName);
        } else {
            System.out.println("Option '" + unitName + "' tidak ditemukan, langsung klik Apply");
        }

        izinPulangCepatPage.clickApply();
        
        Thread.sleep(2000);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(izinPulangCepatPage.filterByModal));
        Assert.assertTrue(
                DriverUtil.getDriver().findElements(izinPulangCepatPage.filterByModal).isEmpty(),
                "Modal filter masih terlihat setelah klik Terapkan"
        );

        Assert.assertTrue(izinPulangCepatPage.getDataList().size() > 0, "Data tidak muncul setelah filter");
        System.out.println("Jumlah data setelah filter: " + izinPulangCepatPage.getDataList().size());

        for (WebElement row : izinPulangCepatPage.getDataList()) {
            String rowText = row.getText();
            Assert.assertTrue(rowText.contains(unitName),"Ditemukan row yang bukan unit " + unitName + ": " + rowText);
        }
    }


    @Test(enabled = true)
    public void negativeTestFilterByUnit() throws InterruptedException {
        navigateToIzinPulangCepatPage();
        String unitName = "UnitTidakAda";

        izinPulangCepatPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.filterByUnitSelect).isDisplayed(), "Field UNIT tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.cancelButton).isDisplayed(), "Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.applyButton).isDisplayed(), "Tombol Terapkan tidak muncul");

        Thread.sleep(2000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(izinPulangCepatPage.filterByUnitSelect));
        input.click();
        input.sendKeys(unitName);

        Thread.sleep(2000);

        izinPulangCepatPage.clickApply();

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
        navigateToIzinPulangCepatPage();
        izinPulangCepatPage.search("Hadir");

        izinPulangCepatPage.clickIconCalendar();
        izinPulangCepatPage.selectMonth("June");
        izinPulangCepatPage.selectDateRange(0, 29);

        String startDate = izinPulangCepatPage.getStartDateValue();
        String endDate   = izinPulangCepatPage.getEndDateValue();
        String monthText = izinPulangCepatPage.getMonthText();   // Ambil text ("June")
        String unitName  = "Sysmex";

        Thread.sleep(2000);

        Assert.assertEquals(monthText, "June", "Month text tidak sesuai");
        Assert.assertTrue(startDate.contains("01 Jun 2025"), "Start Date tidak sesuai");
        Assert.assertTrue(endDate.contains("30 Jun 2025"), "End Date tidak sesuai");

        izinPulangCepatPage.clickSaveCalendar();
        izinPulangCepatPage.clickSearch();
        izinPulangCepatPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.filterByUnitSelect).isDisplayed(), "Field UNIT tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.cancelButton).isDisplayed(), "Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.applyButton).isDisplayed(), "Tombol Terapkan tidak muncul");

        Thread.sleep(5000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(izinPulangCepatPage.filterByUnitSelect));
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

        izinPulangCepatPage.clickApply();

        Thread.sleep(2000);

        Collection<String> rows = izinPulangCepatPage.getDataRows();

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

    @Test(enabled = false)
        public void resetTestAllFilter () throws InterruptedException {
        navigateToIzinPulangCepatPage();
        izinPulangCepatPage.search("Hadir");

        izinPulangCepatPage.clickIconCalendar();
        izinPulangCepatPage.selectMonth("February");
        izinPulangCepatPage.selectDateRange(0, 27);

        String startDate = izinPulangCepatPage.getStartDateValue();
        String endDate   = izinPulangCepatPage.getEndDateValue();
        String monthText = izinPulangCepatPage.getMonthText();   // Ambil text ("June")
        String unitName  = "Sysmex";

        Thread.sleep(2000);

        Assert.assertEquals(monthText, "February", "Month text tidak sesuai");
        Assert.assertTrue(startDate.contains("01 Feb 2025"), "Start Date tidak sesuai");
        Assert.assertTrue(endDate.contains("28 Feb 2025"), "End Date tidak sesuai");

        izinPulangCepatPage.clickSaveCalendar();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.searchField).isDisplayed(), "Field pencarian tidak muncul");
        izinPulangCepatPage.clickSearch();
        izinPulangCepatPage.clickFilterUnit();

        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.filterByUnitSelect).isDisplayed(), "Field UNIT tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.cancelButton).isDisplayed(), "Tombol Batal tidak muncul");
        Assert.assertTrue(DriverUtil.getDriver().findElement(izinPulangCepatPage.applyButton).isDisplayed(), "Tombol Terapkan tidak muncul");

        Thread.sleep(5000);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(izinPulangCepatPage.filterByUnitSelect));
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

        izinPulangCepatPage.clickApply();

        Thread.sleep(2000);

        izinPulangCepatPage.clickReset();

        Assert.assertTrue(izinPulangCepatPage.getSearchResult().isEmpty(), "Keyword tidak kosong");
        Assert.assertTrue(izinPulangCepatPage.getStartDateValue().isEmpty(), "Start Date tidak kosong");
        Assert.assertTrue(izinPulangCepatPage.getEndDateValue().isEmpty(), "End Date tidak kosong");

        izinPulangCepatPage.clickFilterUnit();

        Assert.assertEquals(DriverUtil.getDriver().findElement(izinPulangCepatPage.filterByUnitSelect).isDisplayed(), false, "Field UNIT muncul");

        izinPulangCepatPage.clickCancel();   
    }

    @Test(enabled = false)
    public void testCheckSpecificStatuses() throws InterruptedException {
        navigateToIzinPulangCepatPage();

        List<String> expectedStatuses = Arrays.asList("PENDING", "APPROVED", "REJECT");
        Set<String> foundStatuses = new HashSet<>();

        while (true) {
            List<String> currentStatuses = izinPulangCepatPage.getStatusListAlt();
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
                WebElement nextBtn = DriverUtil.getDriver().findElement(izinPulangCepatPage.nextPageButton);
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
        System.out.println("Test selesai! Total status dicek: " + foundStatuses.size());
    }
}
