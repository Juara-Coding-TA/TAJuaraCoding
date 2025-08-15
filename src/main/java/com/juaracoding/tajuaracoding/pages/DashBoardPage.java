package com.juaracoding.tajuaracoding.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashBoardPage{
    private WebDriver driver;

    public By TotalKaryawan = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[1]/div/div[2]/div[1]/p");
    public By TotalAbsen    = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[2]/div/div[2]/div[1]/p");
    public By WFH           = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[3]/div/div[2]/div[1]/p");
    public By Cuti          = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[4]/div/div[2]/div[1]/p");
    public By Sakit         = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[5]/div/div[2]/div[1]/p");
    public By Izin          = By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div/div[6]/div/div[2]/div[1]/p");

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getvalue(By locator){
        WebElement element = driver.findElement(locator);
        return  Integer.parseInt(element.getText().trim());
    }
}
