package com.juaracoding.tajuaracoding.pages.actions;

import com.juaracoding.tajuaracoding.pages.components.Navbar;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class NavigationAction implements CompositeAction {
    private final String menuType;
    private boolean completed = false;
    private String result = "";
    
    public NavigationAction(String menuType) {
        this.menuType = menuType;
    }
    
    @Override
    public void execute() {
        try {
            Navbar navbar = new Navbar(DriverUtil.getDriver());
            navbar.openMenuLaporan();
            Thread.sleep(1000);
            
            switch (menuType.toLowerCase()) {
                case "izin-terlambat":
                    navbar.clickIzinTelambat();
                    break;
                case "semua":
                    navbar.clickSemua();
                    break;
                case "kehadiran":
                    navbar.clickKehadiran();
                    break;
                case "dashboard":
                    navbar.clickDashboard();
                    break;
                case "cuti":
                    navbar.clickCuti();
                    break;
                case "izin-pulang-cepat":
                    navbar.clickIzinPulangCepat();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown menu type: " + menuType);
            }
            
            Thread.sleep(2000);
            completed = true;
            result = "Navigation to " + menuType + " completed successfully";
            
        } catch (Exception e) {
            completed = false;
            result = "Navigation failed: " + e.getMessage();
        }
    }
    
    @Override
    public boolean isCompleted() {
        return completed;
    }
    
    @Override
    public String getResult() {
        return result;
    }
}
