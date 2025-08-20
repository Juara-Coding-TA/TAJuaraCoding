package com.juaracoding.tajuaracoding.pages.laporan;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class IzinTerlambatPage extends BaseLaporanPage {
    private WebDriverWait wait;
    public IzinTerlambatPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}