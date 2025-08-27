package com.juaracoding.tajuaracoding.listeners;

import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotTestListener implements ITestListener {
    
    private static final String SCREENSHOT_PATH = "target/screenshots/";
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        
        // Take screenshot on failure
        String screenshotPath = captureScreenshot(result.getMethod().getMethodName() + "_failure");
        
        if (screenshotPath != null) {
            // Set system property for ReportNG to include screenshot
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            
            // Print HTML for screenshot in console/report
            System.out.println("<div style='margin: 10px; padding: 10px; border: 1px solid red; background-color: #ffebee;'>");
            System.out.println("<h3 style='color: red; margin-top: 0;'>Test Failure Screenshot</h3>");
            System.out.println("<p><strong>Test:</strong> " + result.getMethod().getMethodName() + "</p>");
            System.out.println("<p><strong>Error:</strong> " + result.getThrowable().getMessage() + "</p>");
            System.out.println("<p><strong>Screenshot:</strong></p>");
            System.out.println("<a href='" + screenshotPath + "' target='_blank'>");
            System.out.println("<img src='" + screenshotPath + "' style='max-width: 600px; height: auto; border: 1px solid #ddd; box-shadow: 0 2px 4px rgba(0,0,0,0.1);'/>");
            System.out.println("</a>");
            System.out.println("</div>");
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }
    
    /**
     * Capture screenshot and return file path
     */
    private String captureScreenshot(String fileName) {
        try {
            // Create screenshots directory if it doesn't exist
            new File(SCREENSHOT_PATH).mkdirs();
            
            WebDriver driver = DriverUtil.getDriver();
            if (driver == null) {
                System.out.println("Driver is null, cannot take screenshot");
                return null;
            }
            
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
            String screenshotFileName = fileName + "_" + timestamp + ".png";
            File destFile = new File(SCREENSHOT_PATH + screenshotFileName);
            
            FileUtils.copyFile(sourceFile, destFile);
            
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath();
            
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}