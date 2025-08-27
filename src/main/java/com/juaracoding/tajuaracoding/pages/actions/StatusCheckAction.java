package com.juaracoding.tajuaracoding.pages.actions;

import java.util.*;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.testng.Assert;

import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class StatusCheckAction implements CompositeAction {
    private final List<String> expectedStatuses;
    private final By headersLocator = By.xpath("//table//thead//th");
    private final By nextButtonLocator = By.xpath("//button[@aria-label='Go to next page']");

    private boolean completed = false;
    private String result = "";

    public StatusCheckAction(List<String> expectedStatuses) {
        // simpan expected dalam lowercase biar gampang compare
        this.expectedStatuses = expectedStatuses.stream()
                .map(s -> s.trim().toLowerCase())
                .collect(Collectors.toList());
    }

    @Override
    public void execute() {
        Set<String> foundStatuses = new HashSet<>();

        // cari kolom "STATUS"
        List<WebElement> headers = DriverUtil.getDriver().findElements(headersLocator);
        int statusColIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase("STATUS")) {
                statusColIndex = i + 1;
                break;
            }
        }
        if (statusColIndex == -1) {
            throw new RuntimeException("Kolom STATUS tidak ditemukan!");
        }

        // loop tiap halaman
        while (true) {
            List<String> currentStatuses = DriverUtil.getDriver().findElements(
                    By.xpath("//table//tbody//tr//td[" + statusColIndex + "]//*[self::span or self::div]"))
                .stream()
                .map(element -> element.getText().trim().toLowerCase()) // lowercase biar ignore case
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());

            foundStatuses.addAll(currentStatuses);

            // stop kalau sudah ketemu semua expected
            if (foundStatuses.containsAll(expectedStatuses)) break;

            try {
                WebElement nextBtn = DriverUtil.getDriver().findElement(nextButtonLocator);
                if (nextBtn.isEnabled() && !nextBtn.getAttribute("class").contains("Mui-disabled")) {
                    nextBtn.click();
                    Thread.sleep(2000); // bisa diganti WebDriverWait untuk lebih stabil
                } else {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }

        // validasi final
        Assert.assertTrue(foundStatuses.containsAll(expectedStatuses),
                "Tidak semua status ditemukan. Found: " + foundStatuses + ", Expected: " + expectedStatuses);

        completed = true;
        result = "Semua status ditemukan: " + foundStatuses;
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
