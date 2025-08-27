package com.juaracoding.tajuaracoding.pages.login;

import com.juaracoding.tajuaracoding.utils.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("form button[type='submit']");
    private By errorMessage = By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div[1]/div/div/div/div[2]/p");
    public By menubutton = By.xpath("/html/body/div[1]/div/header/div/button[3]");
    public By logoutbutton = By.xpath("/html/body/div[2]/div[3]/ul/div/button[2]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openLoginPage() {
        driver.get("https://magang.dikahadir.com/authentication/login");
        driver.manage().window().maximize();
    }

    public void inputUsername(String username) {
        sendKeys(emailField, username);
    }

    public void inputPassword(String password) {
        sendKeys(passwordField, password);
    }

    private void sendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void clickButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        scrollIntoView(button);
        try {
            button.click();
        } catch (Exception e) {
            // fallback click via JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element
        );
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return errorElement.getText();
        } catch (Exception e) {
            return "Error message not found";
        }
    }

    public boolean isLoginButtonEnabled() {
        try {
            WebElement button = driver.findElement(loginButton);
            return button.isEnabled() && !button.getAttribute("class").contains("Mui-disabled");
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForButtonToBeEnabled() {
        wait.until(driver -> isLoginButtonEnabled());
    }

    public void menubuton(){
        DriverUtil.getDriver().findElement(menubutton).click();
    }
    public void logoutbutton(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutbutton)).click();
    }
}







