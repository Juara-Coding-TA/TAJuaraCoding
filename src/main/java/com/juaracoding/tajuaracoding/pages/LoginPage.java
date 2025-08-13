package com.juaracoding.tajuaracoding.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div[2]/form/button");
    private By errorMessageAccountNotFound = By
            .xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div[1]/div/div/div/div[2]/p");
    private By errorMessageEmailorPassword = By
            .xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div[1]/div/div/div/div[2]/p");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputUsername(String username) {
        driver.findElement(emailField).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickButton() {
        driver.findElement(loginButton).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessageAccountNotFound).getText();
    }

    public String getErrorMessageByXpath() {
        return driver.findElement(errorMessageEmailorPassword).getText();
    }
}
