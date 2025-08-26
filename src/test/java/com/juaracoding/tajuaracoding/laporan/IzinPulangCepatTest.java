package com.juaracoding.tajuaracoding.laporan;

import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.actions.CompositeActionBuilder;
import com.juaracoding.tajuaracoding.pages.actions.TotalDataAction;

public class IzinPulangCepatTest extends BaseTest {

    @Test(enabled = true)
    public void testIzinTerlambatPageVerification() {
        new CompositeActionBuilder()
            .navigateTo("izin-pulang-cepat")
            .verifyPage("izin-pulang-cepat", "search-field", "search-button", "reset-button", "filter-button")
            .execute();
    }
    
    @Test(enabled = true)
    public void testPositiveSearch() {
        new CompositeActionBuilder()
            .navigateTo("izin-pulang-cepat")
            .search("Tejo", true)
            .execute();
    }
    
    @Test(enabled = true)
    public void testNegativeSearch() {
        new CompositeActionBuilder()
            .navigateTo("izin-pulang-cepat")
            .search("UserTidakAda123", false)
            .execute();
    }
    
    @Test(enabled = true)
    public void testDateRangeFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("izin-pulang-cepat")
            .captureDataBefore("data")
            .filterByDate("", 0, 14, "01 Agt 2025", "15 Agt 2025")
            .captureDataAfter("data")
            .validateFilterReduction("data")
            .execute();
    }
    
    @Test(enabled = true)
    public void testPositiveUnitFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("izin-pulang-cepat")
            .captureDataBefore("data")
            .filterByUnit("Sysmex", true)
            .captureDataAfter("data")
            .validateFilterReduction("data")
            .execute();
    }
    
    @Test(enabled = true)
    public void testNegativeUnitFilter() {
        new CompositeActionBuilder()
            .navigateTo("izin-pulang-cepat")
            .filterByUnit("UnitTidakAda", false)
            .execute();
    }
    
    @Test(enabled = true)
    public void testCombinationFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("izin-pulang-cepat")
            .captureDataBefore("data")
            .search("Hadir", true)
            .filterByDate("June", 0, 29, "01 Jun 2025", "30 Jun 2025")
            .filterByUnit("Sysmex", true)
            .captureDataAfter("data")
            .validateFilterReduction("data")
            .execute();
    }
    
    @Test(enabled = true)
    public void testResetAllFilters() {
        new CompositeActionBuilder()
            .navigateTo("izin-pulang-cepat")
            .search("Hadir", true)
            .filterByDate("February", 0, 27, "01 Feb 2025", "28 Feb 2025")
            .filterByUnit("Sysmex", true)
            .resetFilters()
            .execute();
    }

    @Test(enabled = true)
    public void testCheckSpecificStatuses() {
        new CompositeActionBuilder()
            .navigateTo("izin-pulang-cepat")
            .statusCheckAction("PENDING", "APPROVED", "REJECT")
            .execute();
    }
}
