package com.juaracoding.tajuaracoding.laporan;

import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.actions.CompositeActionBuilder;
import com.juaracoding.tajuaracoding.pages.actions.TotalDataAction;


public class KehadiranTest extends BaseTest {
    
    @Test(enabled = true, priority = 1)
    public void testKehadiranPageVerification() {
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .verifyPage("activity", "search-field", "search-button", "reset-button", "filter-button")
            .execute();
    }

    @Test(enabled = true, priority = 2)
    public void testDateRangeFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .captureDataBefore("data")
            .filterByDate("", 7, 21, "08 Agt 2025", "22 Agt 2025")
            .captureDataAfter("data")
            .validateFilterReduction("data")
            .execute();
    }

    @Test(enabled = true, priority = 3)
    public void testPositiveSearch() {
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .search("Hadir", true)
            .execute();
    }

    @Test(enabled = true, priority = 4)
    public void testNegativeSearch() {
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .search("UserNihil", false)    
            .execute();
    }

    @Test(enabled = true, priority = 5)
    public void testPositiveSearchWithDateRangeFilter () {
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .filterByDate("", 7, 21, "08 Agt 2025", "22 Agt 2025")
            .search("Hadir", true)
            .execute();
    }

    @Test(enabled = true, priority = 6)
    public void testPositiveUnitFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .captureDataBefore("data")
            .filterByUnit("Sysmex", true)
            .captureDataAfter("data")
            .validateFilterReduction("data")
            .execute();
    }

    @Test(enabled = true, priority = 7)
    public void testNegativeUnitFilter() {
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .filterByUnit("UnitTidakAda", false)
            .execute();
    }

    @Test(enabled = true, priority = 8)
    public void testPositiveUnitFilterWithDateRangeFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .captureDataBefore("data")
            .filterByDate("", 0, 30, "01 Agt 2025", "31 Agt 2025")
            .filterByUnit("Sysmex", true)
            .captureDataAfter("data")
            .validateFilterReduction("data")
            .execute();
    }

    @Test(enabled = true, priority = 9)
    public void testExportFunctionalityCancel() {
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .exportCancel()
            .sleep(2000) //delay 2 detik
            .execute();
    }

    @Test(enabled = true, priority = 10)
    public void testCombinationFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .captureDataBefore("data")
            .filterByDate("", 7, 21, "08 Agt 2025", "22 Agt 2025")
            .filterCutiByUnit("Sysmex", true)
            .search("Testing", true)
            .captureDataAfter("data")
            .validateFilterReduction("data")
            .exportCancel()
            .execute();
    }

    @Test(enabled = true, priority = 11)
    public void testResetAllFilters() {
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .filterByDate("August", 0, 30, "01 Agt 2025", "31 Agt 2025")
            .search("SQA", true)
            .exportCancel()
            .resetFilters()
            .execute();
    }

    @Test(enabled = true, dependsOnMethods = {"testResetAllFilters"})
    public void testExportFunctionality() {
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .filterByDate("", 19, 26, "20 Agt 2025", "27 Agt 2025")
            .exportAction()
            .sleep(15000) //delay 15 detik buat download
            .execute();
    }

    // masih error
    @Test(enabled = true, priority = 12)
    public void testStatusCheck() {
        new CompositeActionBuilder()
            .navigateTo("Kehadiran")
            .filterByDate("May", 0, 30, "01 Mei 2025", "31 Mei 2025")
            .sleep(2000) //delay 2 detik
            .statusCheckAction("Work From Office", "Work From Home")
            .execute();
    }
}

