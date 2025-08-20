package com.juaracoding.tajuaracoding.pages.components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navbar {
    private WebDriver driver;

    private By menuLaporan = By.xpath("//p[contains(text(),'Laporan')]");
    private By subMenuIzinTerlambat = By.xpath("//p[normalize-space()='Izin Terlambat']");
    private By subMenuIzinPulangCepat = By.xpath("//div[p[normalize-space(text())='Izin Pulang Cepat']]");
    private By subMenuCuti = By.xpath("//div[@class='MuiBox-root css-zb4pxb']//p[normalize-space(.)='Cuti']");
    private By subMenuDashboard = By.xpath("//*[@id=\"__next\"]/div/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[1]/div/div[2]/div/div/div/div[1]/div[2]/p");
    private By subMenuSemua = By.xpath("//div[@class='MuiBox-root css-1pd2x36']//p[normalize-space(.)='Semua']");    
    private By subMenuKehadiran = By.xpath("//div[@class='MuiBox-root css-1pd2x36']//p[normalize-space(.)='Kehadiran']"); 


    public Navbar(WebDriver driver) {
        this.driver = driver;
    }

    public void openMenuLaporan() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(menuLaporan));
        driver.findElement(menuLaporan).click();
    }

    public void clickIzinTelambat() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement izin = wait.until(ExpectedConditions.elementToBeClickable(subMenuIzinTerlambat));
        izin.click();
    }

    public void clickIzinPulangCepat() {
        driver.findElement(subMenuIzinPulangCepat).click();
    }

    public void clickCuti() {
        driver.findElement(subMenuCuti).click();
    }

    public void clickDashboard(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashoard = wait.until(ExpectedConditions.elementToBeClickable(subMenuDashboard));
        dashoard.click();
    }

      public void clickSemua() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Semua = wait.until(ExpectedConditions.elementToBeClickable(subMenuSemua));
        Semua.click();
    }

    public void clickKehadiran() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Kehadiran = wait.until(ExpectedConditions.elementToBeClickable(subMenuKehadiran));
        Kehadiran.click();
    }

}
