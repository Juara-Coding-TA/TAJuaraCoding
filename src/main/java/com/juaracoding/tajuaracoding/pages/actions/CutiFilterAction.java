package com.juaracoding.tajuaracoding.pages.actions;

import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class CutiFilterAction implements CompositeAction {
    
    private final String unitName;
    private final boolean expectResults;
    private boolean completed = false;
    private String result = "";
    private boolean unitFoundInTable = false;
    private int totalRecordsAfterFilter = 0;
    
    public CutiFilterAction(String unitName, boolean expectResults) {
        this.unitName = unitName;
        this.expectResults = expectResults;
    }
    
    public CutiFilterAction(String unitName) {
        this(unitName, true); // Default expect results
    }

    @Override
    public void execute() {
        try {
            WebDriver driver = DriverUtil.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Klik filter button
            driver.findElement(By.xpath("//button[contains(@class, 'MuiButton-containedSecondary')]")).click();

            // Input unit name
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@id='job_departement']")));
            input.click();
            input.sendKeys(unitName); 
            input.sendKeys(Keys.BACK_SPACE);   
            Thread.sleep(1500);

            // Pilih option di dropdown kalau ada
            List<WebElement> options = driver.findElements(By.xpath(
                "//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option') and normalize-space()='" + unitName + "']"
            ));
    
            if (!options.isEmpty()) {
                wait.until(ExpectedConditions.visibilityOf(options.get(0)));
                options.get(0).click();
                System.out.println("Klik dropdown: " + unitName);
            } else {
                System.out.println("Option '" + unitName + "' tidak ditemukan, lanjut klik Terapkan");
            }
            
            // Klik tombol Terapkan
            driver.findElement(By.xpath("//button[normalize-space()='Terapkan']")).click();
            Thread.sleep(2000);

            // Tunggu table update
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
            Thread.sleep(1500);

            // Hitung hasil setelah filter
            totalRecordsAfterFilter = getTotalRecordsFromPagination();
            unitFoundInTable = checkUnitInTable();

            // Validasi hasil sesuai ekspektasi
            if (expectResults) {
                if (totalRecordsAfterFilter > 0) {
                    completed = true;
                    result = "Filter sukses: ditemukan " + totalRecordsAfterFilter 
                           + " records (unit ditemukan? " + unitFoundInTable + ")";
                } else {
                    completed = false;
                    result = "Filter gagal: tidak ada data untuk " + unitName;
                }
            } else {
                if (totalRecordsAfterFilter == 0) {
                    completed = true;
                    result = "Filter sukses: tidak ada data untuk " + unitName + " (sesuai ekspektasi)";
                } else {
                    completed = false;
                    result = "Filter tidak sesuai: ditemukan " + totalRecordsAfterFilter + " records untuk " + unitName;
                }
            }

        } catch (Exception e) {
            completed = false;
            result = "Error dalam CutiFilterAction: " + e.getMessage();
            e.printStackTrace();
        }
    }

    /** Hitung total dari pagination (fallback ke row count kalau gagal) */
    private int getTotalRecordsFromPagination() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
            String[] paginationSelectors = {
                "//div[@class='MuiBox-root css-1dkxazs']",
                "//div[contains(@class, 'MuiTablePagination-displayedRows')]",
                "//span[contains(@class, 'pagination')]",
                "//div[contains(text(), 'of') or contains(text(), 'dari')]"
            };
            
            for (String selector : paginationSelectors) {
                try {
                    WebElement paginationElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
                    String paginationText = paginationElement.getText().trim();
                    if (!paginationText.isEmpty()) {
                        String[] patterns = {
                            "(?i).*of\\s+(\\d+).*",
                            "(?i).*dari\\s+(\\d+).*",
                            "(?s).*?(\\d+)\\s*$"
                        };
                        for (String pattern : patterns) {
                            String totalStr = paginationText.replaceAll(pattern, "$1");
                            if (!totalStr.equals(paginationText) && totalStr.matches("\\d+")) {
                                return Integer.parseInt(totalStr);
                            }
                        }
                    }
                } catch (TimeoutException ignore) {}
            }
            // fallback → hitung rows
            List<WebElement> rows = DriverUtil.getDriver().findElements(By.xpath("//table//tbody//tr[td]"));
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /** Cek apakah unitName muncul di tabel */
    private boolean checkUnitInTable() {
        try {
            List<WebElement> cells = DriverUtil.getDriver().findElements(By.xpath("//table//tbody//td"));
            return cells.stream().anyMatch(cell -> cell.getText().trim().contains(unitName));
        } catch (Exception e) {
            return false;
        }
    }

    @Override public boolean isCompleted() { return completed; }
    @Override public String getResult() { return result; }

    // Getter untuk assert di test
    public boolean isUnitFoundInTable() { return unitFoundInTable; }
    public int getTotalRecordsAfterFilter() { return totalRecordsAfterFilter; }
    public String getUnitName() { return unitName; }
    public boolean isExpectingResults() { return expectResults; }
}
