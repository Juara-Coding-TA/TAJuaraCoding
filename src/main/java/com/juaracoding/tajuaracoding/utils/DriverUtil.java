package com.juaracoding.tajuaracoding.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverUtil {
  private static WebDriver driver;

  public static WebDriver getDriver() {
    if (driver == null) {
      FirefoxOptions options = new FirefoxOptions();
      // options.addArguments("--headless");
      driver = new FirefoxDriver(options);
      driver.manage().window().maximize();
    }
    return driver;
  }

  public WebDriver getDriverss() {
    return driver;
  }

  public static void initializeDriver() {
    initializeDriver("firefox");
  }

  public static void initializeDriver(String browserName) {
      // Placeholder for potential initialization logic based on browserName.
      // The getDriver() method already handles the primary initialization.
  }

  public static void quitDriver() {
      if (driver != null) {
          try {
              driver.quit();
          } catch (Exception e) {
              System.out.println("Error closing driver: " + e.getMessage());
          } finally {
              driver = null;
          }
      }
  }

  public static boolean isDriverInitialized() {
      return driver != null;
  }
}
