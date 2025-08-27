package com.juaracoding.tajuaracoding.pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.juaracoding.tajuaracoding.utils.DriverUtil;

public class ExportCancel implements CompositeAction {
    private boolean completed = false;
    private String result = "";

    @Override
    public void execute() {
        try {
            WebDriver driver = DriverUtil.getDriver();
            

            // Click export button
            driver.findElement(By.xpath("//button[normalize-space()='Export']"))
            .click();
            Thread.sleep(1000);

            // //click export to excel
            // driver.findElement(By.xpath("//button[@type='submit' and normalize-space()='Export']")).click();
            // Thread.sleep(1000);

            //click cancel button
            driver.findElement(By.xpath("//button[normalize-space()='Batal']")).click();
            Thread.sleep(1000);

            completed = true;
            result = "Export cancel action completed successfully";
        } catch (Exception e) {
            completed = false;
            result = "Export cancel action failed: " + e.getMessage();
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
