package com.juaracoding.tajuaracoding.pages.actions;

import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class TotalDataAction implements CompositeAction {

    private final List<String> entityNames;
    private final Map<String, Integer> totals = new HashMap<>();
    private boolean completed = false;
    private String result = "";

    private int totalBefore = -1;
    private int totalAfter = -1;
    private int rowsBefore = -1;
    private int rowsAfter = -1;
    private ValidationType validationType = ValidationType.AFTER_FILTER;

    private static final Map<String, Map<String, Integer>> globalDataStore = new HashMap<>();

    public enum ValidationType { BEFORE_FILTER, AFTER_FILTER, COMPARISON, CURRENT }

    public TotalDataAction(String... entityNames) {
        this.entityNames = Arrays.asList(entityNames);
    }

    public TotalDataAction(ValidationType validationType, String... entityNames) {
        this(entityNames);
        this.validationType = validationType;
    }

    @Override
    public void execute() {
        try {
            switch (validationType) {
                case BEFORE_FILTER:
                    captureData(true);
                    break;
                case AFTER_FILTER:
                    captureData(false);
                    break;
                case COMPARISON:
                    performComparison();
                    break;
                default:
                    captureCurrentData();
                    break;
            }
        } catch (Exception e) {
            completed = false;
            result = "❌ Gagal ambil total data: " + e.getMessage();
        }
    }

    /** Ambil data sebelum/atau sesudah filter */
    private void captureData(boolean before) throws InterruptedException {
        Thread.sleep(before ? 1000 : 2000); // jeda untuk load/filter
        int total = getTotalDataFromPagination();
        int rows = getRowCount();

        if (before) {
            totalBefore = total;
            rowsBefore = rows;
        } else {
            totalAfter = total;
            rowsAfter = rows;
        }

        for (String entity : entityNames) {
            totals.put(entity + "_" + (before ? "before" : "after"), total);
            globalDataStore.computeIfAbsent(entity, k -> new HashMap<>())
                        .put("total" + (before ? "Before" : "After"), total);
            globalDataStore.get(entity).put("rows" + (before ? "Before" : "After"), rows);
        }

        completed = true;
        result = "✅ Data " + (before ? "Before" : "After") + " filter berhasil diambil: total=" + total + ", rows=" + rows;
    }
    /** Bandingkan data sebelum dan sesudah filter */
    private void performComparison() {
        int beforeTotal = totalBefore, afterTotal = totalAfter;
        int beforeRows = rowsBefore, afterRows = rowsAfter;

        for (String entity : entityNames) {
            Map<String, Integer> data = globalDataStore.get(entity);
            if (data != null) {
                if (beforeTotal == -1) beforeTotal = data.getOrDefault("totalBefore", -1);
                if (afterTotal == -1) afterTotal = data.getOrDefault("totalAfter", -1);
                if (beforeRows == -1) beforeRows = data.getOrDefault("rowsBefore", -1);
                if (afterRows == -1) afterRows = data.getOrDefault("rowsAfter", -1);
                break;
            }
        }

        if (beforeTotal == -1 || afterTotal == -1) {
            result = "❌ Tidak bisa compare: data BEFORE/AFTER tidak tersedia";
            completed = false;
            return;
        }

        totalBefore = beforeTotal;
        totalAfter = afterTotal;
        rowsBefore = beforeRows;
        rowsAfter = afterRows;

        int totalDiff = afterTotal - beforeTotal;
        for (String entity : entityNames) {
            totals.put(entity + "_before", beforeTotal);
            totals.put(entity + "_after", afterTotal);
            totals.put(entity + "_diff", totalDiff);
        }

        completed = true;
        result = (afterTotal <= beforeTotal)
                ? "✅ Filter valid: data berkurang dari " + beforeTotal + " ke " + afterTotal
                : "❌ Filter invalid: data bertambah dari " + beforeTotal + " ke " + afterTotal;
    }

    /** Ambil data saat ini */
    private void captureCurrentData() {
        try {
            int total = getTotalDataFromPagination();
            int rows = getRowCount();
            entityNames.forEach(e -> totals.put(e, total));
            completed = true;
            result = "✅ Data berhasil diambil: total=" + total + ", rows=" + rows;
        } catch (Exception e) {
            completed = false;
            result = "❌ Gagal ambil data: " + e.getMessage();
        }
    }

    @Override
    public boolean isCompleted() { return completed; }

    @Override
    public String getResult() { return result; }

    public Map<String, Integer> getTotals() { return new HashMap<>(totals); }

    public int getTotalBefore() { return totalBefore; }

    public int getTotalAfter() { return totalAfter; }

    public boolean isFilterValid() { return totalAfter <= totalBefore; }

    public int getDataReduction() { return totalBefore - totalAfter; }

    public static void clearGlobalDataStore() { globalDataStore.clear(); }

    public static Map<String, Map<String, Integer>> getGlobalDataStore() {
        return new HashMap<>(globalDataStore);
    }

    /** Ambil total data dari pagination UI */
    public int getTotalDataFromPagination() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
            String[] selectors = {
                "//div[@class='MuiBox-root css-1dkxazs']",
                "//div[contains(@class, 'MuiTablePagination-displayedRows')]",
                "//span[contains(@class, 'MuiTablePagination-displayedRows')]",
                "//div[contains(text(), 'of') or contains(text(), 'dari')]"
            };

            String text = "";
            for (String sel : selectors) {
                try {
                    text = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sel))).getText().trim();
                    if (!text.isEmpty()) break;
                } catch (Exception ignored) {}
            }
            if (text.isEmpty()) throw new NoSuchElementException("Pagination not found");

            String[] patterns = {
                "(?i).*of\\s+(\\d+).*", "(?i).*dari\\s+(\\d+).*",
                "(?i).*total[^\\d]+(\\d+).*", "(?s).*?(\\d+)\\s*$"
            };
            for (String p : patterns) {
                String extracted = text.replaceAll(p, "$1");
                if (!extracted.equals(text) && extracted.matches("\\d+"))
                    return Integer.parseInt(extracted);
            }
            throw new NumberFormatException("Cannot extract total from: " + text);

        } catch (Exception e) {
            return getRowCount(); // fallback
        }
    }

    /** Hitung jumlah row di tabel */
    public int getRowCount() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
            List<WebElement> rows = DriverUtil.getDriver().findElements(By.xpath("//table//tbody//tr[td]"));
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }
}