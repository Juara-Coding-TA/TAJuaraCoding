package com.juaracoding.tajuaracoding.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverUtil {
  private static WebDriver driver;

  public static WebDriver getDriver() {
    if (driver == null) {
      ChromeOptions options = new ChromeOptions();
      // options.addArguments("--headless");
      driver = new ChromeDriver(options);
      driver.manage().window().maximize();
    }
    return driver;
  }

  public WebDriver getDriverss() {
    return driver;
  }

  public static void initializeDriver() {
    initializeDriver("chrome");
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
          }
      }
  }

  public static boolean isDriverInitialized() {
      return driver != null;
  }
}
