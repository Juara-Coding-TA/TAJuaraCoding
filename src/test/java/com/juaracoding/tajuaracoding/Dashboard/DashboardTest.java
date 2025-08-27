package com.juaracoding.tajuaracoding.Dashboard;

import com.juaracoding.tajuaracoding.BaseTest;
import com.juaracoding.tajuaracoding.pages.components.Navbar;
import com.juaracoding.tajuaracoding.pages.dashboard.DashBoardPage;
import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    DashBoardPage dashboard;

    @Test(enabled = true)
    public void VerificationDashboardTest() throws InterruptedException {
        Navbar navbar = new Navbar(DriverUtil.getDriver());
        navbar.clickDashboard();
        Thread.sleep(2000);

        dashboard = new DashBoardPage(DriverUtil.getDriver());

        int totalkaryawan = dashboard.getvalue(dashboard.TotalKaryawan);
        int totalabsen    = dashboard.getvalue(dashboard.TotalAbsen);
        int wfh           = dashboard.getvalue(dashboard.WFH);
        int sakit         = dashboard.getvalue(dashboard.Sakit);
        int izin          = dashboard.getvalue(dashboard.Izin);
        int cuti          = dashboard.getvalue(dashboard.Cuti);

        Assert.assertTrue(totalkaryawan>=0,"Total Karyawan must Equals or more than 0");
        Assert.assertNotNull(totalabsen);
        Assert.assertNotNull(wfh);
        Assert.assertNotNull(sakit);
        Assert.assertNotNull(izin);
        Assert.assertNotNull(cuti);
        Assert.assertTrue(totalabsen <= totalkaryawan);
    }
}
