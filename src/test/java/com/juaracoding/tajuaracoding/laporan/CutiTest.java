package com.juaracoding.tajuaracoding.laporan;

import org.testng.annotations.Test;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.actions.CompositeActionBuilder;
import com.juaracoding.tajuaracoding.pages.actions.TotalDataAction;

public class CutiTest extends BaseTest {

    @Test(enabled = true)
    public void testIzinTerlambatPageVerification() {
        new CompositeActionBuilder()
            .navigateTo("cuti")
            .verifyPage("cuti", "search-field", "search-button", "reset-button", "filter-button")
            .execute();
    }
    
    @Test(enabled = true)
    public void testPositiveSearch() {
        new CompositeActionBuilder()
            .navigateTo("cuti")
            .search("ADITYA RIDWAN NUGRAHA", true)
            .execute();
    }
    
    @Test(enabled = true)
    public void testNegativeSearch() {
        new CompositeActionBuilder()
            .navigateTo("cuti")
            .search("UserTidakAda123", false)
            .execute();
    }
    
    @Test(enabled = true)
    public void testDateRangeFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("cuti")
            .captureDataBefore("cuti-data")
            .filterByDate("", 0, 14, "01 Agt 2025", "15 Agt 2025")
            .captureDataAfter("cuti-data")
            .validateFilterReduction("cuti-data")
            .execute();
    }
    
    @Test(enabled = true)
    public void testPositiveUnitFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("cuti")
            .captureDataBefore("cuti-data")
            .filterCutiByUnit("Sysmex", true)  // Expect results
            .captureDataAfter("cuti-data")
            .validateFilterReduction("cuti-data")
            .execute();
    }
    
    @Test(enabled = true)
    public void testNegativeUnitFilter() {
        new CompositeActionBuilder()
            .navigateTo("cuti")
            .filterCutiByUnit("UnitTidakAda", false)
            .execute();
    }
    
    @Test(enabled = true)
    public void testCombinationFilter() {
        TotalDataAction.clearGlobalDataStore();
        new CompositeActionBuilder()
            .navigateTo("cuti")
            .captureDataBefore("cuti-data")
            .search("Hadir", true)
            .filterByDate("June", 0, 29, "01 Jun 2025", "30 Jun 2025")
            .filterCutiByUnit("Sysmex", true)
            .captureDataAfter("cuti-data")
            .validateFilterReduction("cuti-data")
            .execute();
    }
    
    @Test(enabled = true)
    public void testResetAllFilters() {
        new CompositeActionBuilder()
            .navigateTo("cuti")
            .search("Hadir", true)
            .filterByDate("February", 0, 27, "01 Feb 2025", "28 Feb 2025")
            .filterCutiByUnit("Sysmex", true)
            .resetFilters()
            .execute();
    }

    @Test(enabled = true)
    public void testCheckSpecificStatuses() {
        new CompositeActionBuilder()
            .navigateTo("cuti")
            .statusCheckAction(
                "Pending",
                "APPROVED",
                "Menunggu Approval V1",
                "Menunggu Approval V2",
                "Menunggu Approval V3",
                "Dibatalkan",
                "Rejected V1"
            )
            .execute();
    }

}
