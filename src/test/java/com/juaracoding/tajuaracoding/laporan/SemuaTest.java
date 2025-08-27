package com.juaracoding.tajuaracoding.laporan;

import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.actions.CompositeActionBuilder;


public class SemuaTest extends BaseTest {

    @Test(enabled = true, priority = 1)
    public void testSemuaPageVerification() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .verifyPage("all", "search-field", "search-button", "reset-button", "filter-button")
            .execute();
    }

    @Test(enabled = true, priority = 2)
    public void testDateRangeFilter() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .filterByDate("", 7, 21, "08 Agt 2025", "22 Agt 2025")
            .execute();
    }

    @Test(enabled = true, priority = 3)
    public void testPositiveSearch() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .search("Hadir", true)
            .execute();
    }

    @Test(enabled = true, priority = 4)
    public void testNegativeSearch() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .search("UserNihil", false)    
            .execute();
    }

    @Test(enabled = true, priority = 5)
    public void testPositiveSearchWithDateRangeFilter () {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .filterByDate("", 7, 21, "08 Agt 2025", "22 Agt 2025")
            .search("Hadir", true)
            .execute();
    }

    @Test(enabled = true, priority = 6)
    public void testPositiveUnitFilter() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .filterByUnit("Sysmex", true)
            .execute();
    }   

    @Test(enabled = true, priority = 7)
    public void testNegativeUnitFilter() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .filterByUnit("UnitNihil", false)
            .execute();
    }

    @Test(enabled = true, priority = 8)
    public void testPositiveUnitFilterWithDateRangeFilter() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .filterByDate("", 0, 30, "01 Agt 2025", "31 Agt 2025")
            .filterByUnit("Sysmex", true)
            .sleep(2000) // delay 2 detik
            .execute();
    }   

    @Test(enabled = true, priority = 9)
    public void testCombinationFilter() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .sleep(2000) // delay 2 detik
            .filterByDate("", 7, 21, "08 Agt 2025", "22 Agt 2025")
            .filterByUnit("Sysmex", true)
            .sleep(2000) // delay 2 detik
            .search("Testing", true)
            .execute();
    }

    @Test(enabled = true, priority = 10)
    public void testResetAllFilters() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .filterByDate("", 7, 21, "08 Agt 2025", "22 Agt 2025")
            .sleep(2000) // delay 2 detik
            .filterByUnit("Sysmex", true)
            .sleep(2000) // delay 2 detik
            .search("Testing", true)
            .resetFilters()
            .execute();
    }

    @Test(enabled = true, priority = 11)
    public void testStatusCheck() {
        new CompositeActionBuilder()
            .navigateTo("Semua")
            .filterByDate("", 0, 30, "01 Agt 2025", "31 Agt 2025")
            .statusCheckAction("Sakit", "Cuti", "lembur")
            .sleep(5000) // delay 5 detik
            .execute();
    }


}
