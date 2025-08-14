package com.juaracoding.tajuaracoding.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public By emailField = By.id("email");
    public By passwordField = By.id("password");
    public By loginButton = By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div[2]/form/button");
    public By logoutButton = By.xpath("//*[@id=\"profile-menu\"]/div[3]/ul/div/button[2]");
    public By miniButton = By.xpath("//*[@id=\"__next\"]/div/header/div/button[3]");
    public By errorMessageAccountNotFound = By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div[1]/div/div/div/div[2]/p");
    public By errorMessageEmailorPassword = By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div[1]/div/div/div/div[2]/p");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        driver.get("https://magang.dikahadir.com/authentication/login");
        driver.manage().window().maximize();
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

    public void miniButton(){
        driver.findElement(miniButton).click();
    }

    public void logoutButton(){
        driver.findElement(logoutButton).click();
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

    public void login(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        clickButton();
    }

    
    

}
