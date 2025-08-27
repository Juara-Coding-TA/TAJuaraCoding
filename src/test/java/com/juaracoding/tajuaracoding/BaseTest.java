package com.juaracoding.tajuaracoding;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.juaracoding.tajuaracoding.utils.DriverUtil;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
    protected WebDriverWait wait;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private static final String SCREENSHOT_PATH = "target/screenshots/";
    private static final String REPORT_PATH = "target/extent-reports/";

    @BeforeSuite
    public void setUpSuite() {
        // Create directories if they don't exist
        new File(SCREENSHOT_PATH).mkdirs();
        new File(REPORT_PATH).mkdirs();
        
        // Initialize ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH + "ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("TAJuaraCoding Test Report - Hadir Application");
        sparkReporter.config().setReportName("Selenium Automation Test Results");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Hadir - Magang System");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Base URL", "https://magang.dikahadir.com");
    }

    @BeforeMethod
    @Parameters({ "baseURL", "username", "password" })
    public void setup(String baseURL, String username, String password, ITestResult result) throws InterruptedException {
        
        // Create ExtentTest instance for current test
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        test = extent.createTest(className + "." + testName);
        test.info("Starting test: " + testName);
        
        // Initialize driver for current thread
        DriverUtil.initializeDriver();
        WebDriver driver = DriverUtil.getDriver();
        
        // Initialize wait for current thread
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            test.info("Navigating to: " + baseURL);
            driver.get(baseURL);
            
            test.info("Waiting for login page elements to load");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
            
            test.info("Entering username: " + username);
            WebElement inputUsername = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
            inputUsername.clear();
            inputUsername.sendKeys(username);

            test.info("Entering password");
            WebElement inputPassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
            inputPassword.clear();
            inputPassword.sendKeys(password);

            test.info("Clicking login button");
            WebElement buttonLogin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div[2]/form/button")));
            buttonLogin.click();
            
            test.pass("Login setup completed successfully");
            
        } catch (Exception e) {
            test.fail("Setup failed: " + e.getMessage());
            System.out.println("Setup failed for thread " + Thread.currentThread().getName() + ": " + e.getMessage());
            
            // Take screenshot on setup failure
            String screenshotPath = takeScreenshot("setup_failure_" + testName);
            if (screenshotPath != null) {
                try {
                    test.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception ex) {
                    test.warning("Failed to attach screenshot: " + ex.getMessage());
                }
            }
            
            DriverUtil.quitDriver();
            throw e;
        }
    }
    
    @AfterMethod
    public void teardown(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        
        try {
            // Handle test result
            if (result.getStatus() == ITestResult.FAILURE) {
                test.fail("Test Failed: " + result.getThrowable().getMessage());
                
                // Take screenshot on failure
                String screenshotPath = takeScreenshot("failed_" + testName);
                if (screenshotPath != null) {
                    try {
                        test.addScreenCaptureFromPath(screenshotPath);
                        
                        // For ReportNG - embed screenshot in HTML
                        System.setProperty("org.uncommons.reportng.escape-output", "false");
                        System.out.println("<br><div style='border: 1px solid #ccc; padding: 10px; margin: 10px;'>");
                        System.out.println("<h4 style='color: red;'>Screenshot on Failure:</h4>");
                        System.out.println("<a href='" + screenshotPath + "' target='_blank'>");
                        System.out.println("<img src='" + screenshotPath + "' style='max-width: 400px; height: auto; border: 1px solid #ddd;'/>");
                        System.out.println("</a></div><br>");
                        
                    } catch (Exception e) {
                        test.warning("Failed to attach screenshot: " + e.getMessage());
                    }
                }
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test Passed Successfully");
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.skip("Test Skipped: " + result.getThrowable().getMessage());
            }
            
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            test.warning("Thread interrupted during teardown");
        } finally {
            DriverUtil.quitDriver();
        }
    }
    
    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    /**
     * Take screenshot and return file path
     */
    public String takeScreenshot(String screenshotName) {
        try {
            WebDriver driver = DriverUtil.getDriver();
            if (driver == null) {
                return null;
            }
            
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";
            File destFile = new File(SCREENSHOT_PATH + fileName);
            
            FileUtils.copyFile(sourceFile, destFile);
            
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Take screenshot with description for ExtentReports
     */
    public void captureScreenshot(String description) {
        String screenshotPath = takeScreenshot(description.replaceAll(" ", "_"));
        if (screenshotPath != null && test != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath, description);
            } catch (Exception e) {
                test.warning("Failed to attach screenshot: " + e.getMessage());
            }
        }
    }
    
    /**
     * Log info message
     */
    public void logInfo(String message) {
        if (test != null) {
            test.info(message);
        }
        System.out.println("[INFO] " + message);
    }
    
    /**
     * Log pass message
     */
    public void logPass(String message) {
        if (test != null) {
            test.pass(message);
        }
        System.out.println("[PASS] " + message);
    }
    
    /**
     * Log fail message
     */
    public void logFail(String message) {
        if (test != null) {
            test.fail(message);
        }
        System.err.println("[FAIL] " + message);
    }
    
    /**
     * Log warning message
     */
    public void logWarning(String message) {
        if (test != null) {
            test.warning(message);
        }
        System.out.println("[WARNING] " + message);
    }
}